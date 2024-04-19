package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.enums.ActionEnum;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.animation.PersonAnimationSet;
import com.rock.pokemon.gdx.model.map.renderer.YSortable;
import com.rock.pokemon.gdx.model.map.config.BoxMapConfig.BoxMapNode;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig.NpcMapNode;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig.NpcMapNodeEvent;
import com.rock.pokemon.gdx.model.ui.box.DialogueAndOptionBox;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 人物实体(可以是精灵、人物、甚至道具精灵球)
 *
 * @Author ayl
 * @Date 2022-10-13
 */
@Getter
@Setter
public class Person implements YSortable {

    /**
     * 基本信息
     */

    //游戏对象
    private final PokemonGame pokemonGame;

    //当前人物在地图网格的坐标(放弃用Rectangle是因为Rectangle是float类型的),这个在人类实体里仅用来判定移动时和地图块之间的关系
    private int x;
    private int y;

    //当前人物在世界的真实坐标(考虑到移动、骑车、跑步等动作不是时刻在网格中,所采用的世界坐标,运营到补帧动画)
    private float worldX;
    private float worldY;

    //人物宽高,设定用户宽高,绿宝石中,通常人物高度占接近1.5个地图网格
    private float width = 1.0F;
    private float height = 1.5F;

    /**
     * 移动相关
     */

    //该人物所处的世界
    private World world;

    //人物动画集合
    private PersonAnimationSet animationSet;

    //当前人物-脸的方向(可以是走也可以是站立,只是方向,根据方向+状态不同,有不同的判定)
    private DirectionEnum facingState;
    //当前人物-动作状态
    private ActionEnum actionState;
    //当前人物-走路状态(走、跑、骑车)
    private WalkEnum walkState;
    //当前人物-是否为原地踏步
    private boolean steppingState;

    //移动起始坐标
    private int srcX;
    private int srcY;
    //移动目标坐标
    private int destX;
    private int destY;

    //完成一次走步动画的总时间,单位秒
    public static final float WALK_ONCE_ANIM_TIME = 0.3F;
    //完成一次跑步动画的总时间,单位秒
    public static final float RUN_ONCE_ANIM_TIME = 0.15F;

    //动画持续时间
    private float animTime;
    //持续一个方向走路的时间
    private float continueWalkTime;
    //持续走路时,如果方向和之前相同,那么该参数会为true,让它会继续走下去,用来判定[连续相同方向走路],否则会停下
    private boolean moveRequestThisFrame;

    /**
     * 事件
     */

    //事件列表
    private List<NpcMapNodeEvent> eventList;

    /**
     * 使用人物枚举初始化
     *
     * @param npcMapNode  npc配置
     * @param world       该人物所处的世界
     * @param x           人物初始坐标x
     * @param y           人物初始坐标y
     * @param pokemonGame 游戏对象
     */
    public Person(NpcMapNode npcMapNode, World world, int x, int y, PokemonGame pokemonGame) {

        /**
         * 基本信息
         */

        //游戏对象
        this.pokemonGame = pokemonGame;

        //设置当前人物所在坐标
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;

        //人物动作-默认站立
        this.actionState = ActionEnum.STAND;
        //人物移动-默认站立
        this.walkState = WalkEnum.STAND;
        //人物方向-默认南
        this.facingState = DirectionEnum.SOUTH;

        //初始化人物动画集合
        this.animationSet = new PersonAnimationSet(this.pokemonGame.getMyAssetManager(), npcMapNode);

        //记录事件列表
        this.eventList = npcMapNode.getEventList();

        /**
         * 与世界关联
         */

        //人物记录要加入的世界
        this.world = world;
        //人物加入世界
        this.world.addPerson(this);

    }

    /**
     * 检查/对话
     */
    public void checkAndTalk() {

        /**
         * 判断是否满足事件条件
         */

        //如果行动不是站立
        if (this.actionState != ActionEnum.STAND) {
            //不执行
            return;
        }
        //当前人物坐标,和脸的方向,计算目标位置
        int x = this.x + this.facingState.getDx();
        int y = this.y + this.facingState.getDy();
        //如果越界
        if (x < 0 || y < 0 || x >= this.world.getTileMap().getWidth() || y >= this.world.getTileMap().getHeight()) {
            //过
            return;
        }
        //获取世界上层的城镇中,事件的盒子
        DialogueAndOptionBox dialogueAndOptionBox = this.world.getWorldScreen().getDialogueAndOptionBox();
        //如果没有结束
        if (dialogueAndOptionBox.isFinished() == false) {
            //过
            return;
        }

        /**
         * 获取各种事件,但只触发一个
         */

        //todo 事务事件、地图块事件等等

        //获取人物
        Person person = Optional.ofNullable(this.world)
                //获取地图块矩阵
                .map(World::getTileMap)
                //获取对应目的地
                .map(p -> p.getTile(x, y))
                //获取人物
                .map(Tile::getPerson)
                .orElse(null);
        //判空
        if (person == null) {
            //过
            return;
        }

        //获取人物对应事件
        NpcMapNodeEvent event = Optional.ofNullable(person)
                //获取人物上的事件列表
                .map(Person::getEventList)
                //默认
                .orElse(new ArrayList<>())
                .stream()
                //todo 以后可以做事件的开关、以及优先级
                .findFirst()
                .orElse(null);
        //如果没有任何事件
        if (event == null) {
            //过
            return;
        }

        /**
         * 实现
         */

        //根据当前人物,让对应事件人物转头
        switch (this.facingState) {
            case SOUTH:
                //怼脸
                person.changeFacingDir(DirectionEnum.NORTH);
                break;
            case NORTH:
                //怼脸
                person.changeFacingDir(DirectionEnum.SOUTH);
                break;
            case WEST:
                //怼脸
                person.changeFacingDir(DirectionEnum.EAST);
                break;
            case EAST:
                //怼脸
                person.changeFacingDir(DirectionEnum.WEST);
                break;
        }

        //读取事件配置
        BoxMapNode talkTestNode = this.pokemonGame.getMyAssetManager().getBoxMapConfig().getBoxMap().get(event.getBoxName());
        //这里直接用事件
        dialogueAndOptionBox.reset(talkTestNode);
        //开启
        dialogueAndOptionBox.nextNode();

    }

    /**
     * 处理移动中时的动画(可以理解为补帧)
     *
     * @param delta 每帧的时间
     */
    public void update(float delta) {
        //根据当前状态判定
        switch (this.actionState) {
            //如果此时还在走
            case WALK:
                //一次动画时间
                float onceAnimTime;
                //根据当前走路状态判定
                switch (this.walkState) {
                    //跑步
                    case RUN:
                        //使用跑步的
                        onceAnimTime = RUN_ONCE_ANIM_TIME;
                        break;
                    //走路
                    case WALK:
                    default:
                        //使用走路的
                        onceAnimTime = WALK_ONCE_ANIM_TIME;
                        break;
                }
                //叠加本次走路、动画的持续时间
                this.animTime += delta;
                this.continueWalkTime += delta;
                //计算出其真实的世界坐标,据说绿宝石是线性的,这里不太懂,但大体的意思是按照线性的逻辑不断计算出对应x,y坐标
                this.worldX = Interpolation.linear.apply(this.srcX, this.destX, this.animTime / onceAnimTime);
                this.worldY = Interpolation.linear.apply(this.srcY, this.destY, this.animTime / onceAnimTime);
                //每次持续动画时间结束时(如果继续走,代表要进行下一次动画了)
                if (this.animTime >= onceAnimTime) {
                    //计算出本次动画多出的那极少一部分时间(因为每次都会有极少的误差),给持续一个方向走路的时间,让动画稳定
                    this.continueWalkTime = this.continueWalkTime - (this.animTime - onceAnimTime);
                    //结束本次走路,并重新定位人物位置(确保精度)
                    walkEnd();
                    //如果此时要换方向走了
                    if (this.moveRequestThisFrame == false) {
                        //不再按照该方向走路了,那么持续走路时间归0,从头算起动画帧
                        this.continueWalkTime = 0F;
                    }
                }
                break;
            //站立或其他
            case STAND:
            default:
                //直接结束
                break;
        }
        //每次该方法判定,都要固定重置为false,否则该人物会一直按照这个方向前进,操控也会失灵
        this.moveRequestThisFrame = false;
    }

    /**
     * 人物移动判定
     *
     * @param directionEnum 接下来移动的方向
     * @param walkEnum      走路状态
     */
    public boolean move(DirectionEnum directionEnum, WalkEnum walkEnum) {
        //根据人物此时的行动状态判定
        switch (this.actionState) {
            //走路
            case WALK:
                //判断是否还是按照这个方向走路
                this.moveRequestThisFrame = this.facingState == directionEnum;
                //让他继续走下去吧
                return false;
            //默认、站立(或者说是刚走完上一步)
            case STAND:
            default:
                //开始走路判定
                walkStart(directionEnum, walkEnum);
                //移动成功
                return true;
        }
    }

    /**
     * 尝试开始本次走路
     *
     * @param directionEnum 走的方向
     * @param walkEnum      走路的状态(走步,跑步)
     */
    private void walkStart(DirectionEnum directionEnum, WalkEnum walkEnum) {

        /**
         * 计算出本次移动的目的地
         */

        //计算出移动完的目标坐标
        int destX = this.x + directionEnum.getDx();
        int destY = this.y + directionEnum.getDy();

        /**
         * 计算本次移动是否为原地踏步
         */

        //step 1 根据地图边界,判断原地踏步
        boolean steppingState = destX < 0 || destY < 0 || destX >= this.world.getTileMap().getWidth() || destY >= this.world.getTileMap().getHeight();

        //step 2 根据地图块事物,判断原地踏步
        steppingState = steppingState == true ? true : Optional.ofNullable(this.world)
                //获取地图块矩阵
                .map(World::getTileMap)
                //获取对应目的地
                .map(p -> p.getTile(destX, destY))
                //获取事物
                .map(Tile::getWorldObject)
                //获取这个是否是否可以取走
                .map(WorldObject::isWalkable)
                //翻转
                .map(p -> !p)
                //默认
                .orElse(false);

        //step 3 根据地图块人物,判断原地踏步
        steppingState = steppingState == true ? true : Optional.ofNullable(this.world)
                //获取地图块矩阵
                .map(World::getTileMap)
                //获取对应目的地
                .map(p -> p.getTile(destX, destY))
                //获取人
                .map(Tile::getPerson)
                //如果人是否存在
                .map(obj -> true)
                //默认
                .orElse(false);

        /**
         * 根据是否原地踏步,开始处理逻辑
         */

        //如果是原地踏步
        if (steppingState) {
            //强制变为走路
            walkEnum = WalkEnum.WALK;
            //尝试发出撞墙的音效
            this.pokemonGame.getMySoundManager().playNoWalk();
        }

        /**
         * 移动判定 人物坐标
         */

        //校准当前坐标
        this.srcX = this.x;
        this.srcY = this.y;
        //如果是原地踏步
        if (steppingState) {
            //原地踏步
            this.destX = this.x;
            this.destY = this.y;
        } else {
            //覆盖为目的地坐标
            this.destX = destX;
            this.destY = destY;
        }

        /**
         * 移动判定 人物动画状态
         */

        //初始化活动时间
        this.animTime = 0F;
        //人物动作变为走路
        this.actionState = ActionEnum.WALK;
        //走路的状态
        this.walkState = walkEnum;
        //改变脸的方向
        this.facingState = directionEnum;
        //覆盖是否原地踏步的状态
        this.steppingState = steppingState;

    }

    /**
     * 结束走路
     */
    private void walkEnd() {

        /**
         * 移动判定,人物坐标
         */

        //将当前坐标改为移动结束的坐标(这么做还有个好处,该坐标可以转化为int)
        this.worldX = this.destX;
        this.worldY = this.destY;
        this.x = this.destX;
        this.y = this.destY;

        //其他走路参数置0
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;

        /**
         * 移动判定 人物动画状态
         */

        //动画持续时间重置
        this.animTime = 0;
        //改变人物状态为站立
        this.actionState = ActionEnum.STAND;
        //重置人物是否原地踏步状态
        this.steppingState = false;

        /**
         * 移动 地图块内 对应的人物实体
         */

        //获取当前世界的地图块
        TileMap tileMap = this.world.getTileMap();
        //人物加入最新的地图块
        tileMap.setPerson(this.x, this.y, this);
        //以下尝试删除旧位置的人物
        tileMap.removePerson(this.x + 1, this.y, this);
        tileMap.removePerson(this.x - 1, this.y, this);
        tileMap.removePerson(this.x, this.y + 1, this);
        tileMap.removePerson(this.x, this.y - 1, this);

    }

    /**
     * 尝试停止走路
     */
    public void walkStop() {
        //如果移动状态是站立
        if (this.actionState == ActionEnum.STAND) {
            //改变动作状态为站立
            this.walkState = WalkEnum.STAND;
        }
    }

    /**
     * 单纯的脸换个方向,当然,得站着的时候
     *
     * @param facing 方向枚举
     * @return
     */
    public void changeFacingDir(DirectionEnum facing) {
        //如果不是站着,无需换脸
        if (this.actionState != ActionEnum.STAND) {
            //过
            return;
        }
        //变换当前脸的方向
        this.facingState = facing;
    }

    /**
     * 获取当前人物动画图片或帧图片
     *
     * @return
     */
    public TextureRegion getSprite() {
        //根据当前状态判定
        switch (this.walkState) {
            //跑步
            case RUN:
                //返回跑步动画帧图片
                return this.animationSet.getRunning(this.facingState).getKeyFrame(this.continueWalkTime);
            //走路/踏步
            case WALK:
                //如果是踏步
                if (this.steppingState) {
                    //返回踏步动画帧图片
                    return this.animationSet.getStepping(this.facingState).getKeyFrame(this.continueWalkTime);
                } else {
                    //返回走路动画帧图片
                    return this.animationSet.getWalking(this.facingState).getKeyFrame(this.continueWalkTime);
                }
                //默认站立
            case STAND:
            default:
                //返回站立图片
                return this.animationSet.getStanding(this.facingState);
        }
    }

    public float getWorldX() {
        return this.worldX;
    }

    public float getWorldY() {
        return this.worldY;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

}
