package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.animation.PersonAnimationSet;
import lombok.Getter;

import java.util.Optional;

/**
 * 人物移动 / 动画控制器
 * -
 * 把 Person 里和移动、坐标、碰撞、动画时间线相关的逻辑都集中到这里，
 * Person 只负责“数据 + 对外接口 + 委托”
 */
@Getter
public class PersonMovement {

    //人物
    private final Person person;

    // ================= 坐标相关 =================

    //当前人物在地图网格的坐标(整型, 用于与 Tile 交互)
    private int tileX;
    private int tileY;

    //当前人物在世界的真实坐标(考虑到移动、骑车、跑步等动作不是时刻在网格中)
    private float worldX;
    private float worldY;

    // ================= 移动 / 动画状态相关 =================

    //当前人物-脸的方向(可以是走也可以是站立,只是方向)
    private DirectionEnum facingState = DirectionEnum.SOUTH;
    //当前人物-动作状态
    private Person.ActionEnum actionState = Person.ActionEnum.STAND;
    //当前人物-走路状态(走、跑、骑车)
    private WalkEnum walkState = WalkEnum.STAND;
    //当前人物-是否为原地踏步
    private boolean steppingState;

    //移动起始坐标(基于 tile)
    private int srcX;
    private int srcY;
    //移动目标坐标(基于 tile)
    private int destX;
    private int destY;


    //动画持续时间
    private float animTime;
    //持续一个方向走路的时间
    private float continueWalkTime;
    //持续走路时,如果方向和之前相同,则为true,用于判定连续相同方向走路
    private boolean moveRequestThisFrame;

    /**
     * 初始化控制器
     *
     * @param person 人物
     * @param x      初始 tile X
     * @param y      初始 tile Y
     */
    public PersonMovement(Person person, int x, int y) {
        this.person = person;

        // 初始坐标
        this.tileX = x;
        this.tileY = y;
        this.worldX = x;
        this.worldY = y;
        this.steppingState = false;
    }

    // ================== 核心逻辑 ==================

    /**
     * 处理移动中时的动画(可以理解为补帧)
     *
     * @param delta 每帧的时间
     */
    public void update(float delta) {
        //根据状态处理
        switch (this.actionState) {
            //如果此时还在走
            case WALK:

                //根据走路状态,返回一次动画时间
                float onceAnimTime = PersonAnimationSet.getOnceAnimTime(this.walkState);

                //叠加本次走路、动画的持续时间
                this.animTime += delta;
                this.continueWalkTime += delta;

                //计算出其真实的世界坐标
                float progress = this.animTime / onceAnimTime;
                float worldX = Interpolation.linear.apply(this.srcX, this.destX, progress);
                float worldY = Interpolation.linear.apply(this.srcY, this.destY, progress);
                this.worldX = worldX;
                this.worldY = worldY;

                //每次持续动画时间结束时(如果继续走,代表要进行下一次动画了)
                if (this.animTime >= onceAnimTime) {

                    //计算出本次动画多出的那极少一部分时间(因为每次都会有极少的误差),
                    //给持续一个方向走路的时间, 让动画稳定
                    float overflow = this.animTime - onceAnimTime;
                    this.continueWalkTime -= overflow;

                    //结束本次走路, 并重新定位人物位置(确保精度)
                    this.walkEnd();

                    //如果此时要换方向走了
                    if (!this.moveRequestThisFrame) {
                        //不再按照该方向走路了, 那么持续走路时间归 0, 从头算起动画帧
                        this.continueWalkTime = 0F;
                    }
                }
                break;
            case STAND:
            default:
                //站立或其他：直接结束
                break;
        }

        //每次该方法判定, 都要固定重置为 false,
        //否则该人物会一直按照这个方向前进, 操控也会失灵
        this.moveRequestThisFrame = false;
    }

    /**
     * 人物移动判定
     *
     * @param directionEnum 接下来移动的方向
     * @param walkEnum      走路状态
     */
    public boolean move(DirectionEnum directionEnum, WalkEnum walkEnum) {
        //根据状态判定
        switch (this.actionState) {
            //走路中
            case WALK:
                //判断是否还是按照这个方向走路
                boolean sameDirection = this.facingState == directionEnum;
                this.moveRequestThisFrame = sameDirection;
                //只是继续走，不算“重新发起一次移动”
                return false;
            //默认、站立(或者说是刚走完上一步)
            case STAND:
            default:
                //开始走路判定
                this.walkStart(directionEnum, walkEnum);
                //移动成功
                return true;
        }
    }

    /**
     * 尝试停止走路
     */
    public void walkStop() {
        //如果移动状态是站立
        if (this.actionState == Person.ActionEnum.STAND) {
            //改变动作状态为站立
            this.walkState = WalkEnum.STAND;
        }
    }

    /**
     * 单纯的脸换个方向, 当然, 得站着的时候
     *
     * @param facing 方向枚举
     */
    public void changeFacingDir(DirectionEnum facing) {
        //如果不是站着, 无需换脸
        if (this.actionState != Person.ActionEnum.STAND) {
            return;
        }
        //变换当前脸的方向
        this.facingState = facing;
    }

    /**
     * 获取当前人物动画图片或帧图片
     */
    public TextureRegion getSprite() {
        //根据状态判定
        switch (this.walkState) {
            //跑步
            case RUN:
                return this.person.getAnimationSet()
                        .getRunning(this.facingState)
                        .getKeyFrame(this.continueWalkTime);
            //走路/踏步
            case WALK:
                if (this.steppingState) {
                    //踏步动画
                    return this.person.getAnimationSet()
                            .getStepping(this.facingState)
                            .getKeyFrame(this.continueWalkTime);
                } else {
                    //走路动画
                    return this.person.getAnimationSet()
                            .getWalking(this.facingState)
                            .getKeyFrame(this.continueWalkTime);
                }
                //默认站立
            case STAND:
            default:
                return this.person.getAnimationSet().getStanding(this.facingState);
        }
    }

    //================= 内部私有逻辑 =================

    /**
     * 尝试开始本次走路
     *
     * @param directionEnum 走的方向
     * @param walkEnum      走路的状态(走步, 跑步)
     */
    private void walkStart(DirectionEnum directionEnum, WalkEnum walkEnum) {

        /**
         * 计算出本次移动的目的地
         */

        //计算出移动完的目标坐标
        int destX = this.tileX + directionEnum.getDx();
        int destY = this.tileY + directionEnum.getDy();

        /**
         * 计算本次移动是否为原地踏步
         */
        boolean steppingState = this.calculateSteppingState(destX, destY);

        /**
         * 根据是否原地踏步, 开始处理逻辑
         */
        if (steppingState) {
            //强制变为走路
            walkEnum = WalkEnum.WALK;
            //尝试发出撞墙的音效
            this.person.getPokemonGame()
                    .getGameContext()
                    .getMySoundManager()
                    .play(Settings.SOUND_ID_NO_WALK);
        }

        /**
         * 移动判定 人物坐标
         */

        //校准当前坐标
        this.srcX = this.tileX;
        this.srcY = this.tileY;

        //如果是原地踏步
        if (steppingState) {
            this.destX = this.tileX;
            this.destY = this.tileY;
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
        this.actionState = Person.ActionEnum.WALK;
        //走路的状态
        this.walkState = walkEnum;
        //改变脸的方向
        this.facingState = directionEnum;
        //覆盖是否原地踏步的状态
        this.steppingState = steppingState;
    }

    /**
     * 计算是否需要原地踏步
     *
     * @param destX 目标 tile X
     * @param destY 目标 tile Y
     */
    private boolean calculateSteppingState(int destX, int destY) {
        //step 1 根据地图边界, 判断原地踏步
        boolean steppingState = destX < 0 || destY < 0
                || destX >= this.person.getWorld().getTileMap().getWidth()
                || destY >= this.person.getWorld().getTileMap().getHeight();
        //step 2 根据地图块“事物”，判断原地踏步
        steppingState = steppingState || Optional.ofNullable(this.person.getWorld())
                //获取地图块矩阵
                .map(World::getTileMap)
                //获取对应目的地
                .map(p -> p.getTile(destX, destY))
                //获取事物
                .map(Tile::getWorldObject)
                //获取这个是否可以走
                .map(WorldObject::isWalkable)
                //翻转 -> 不可走 则需要踏步
                .map(p -> !p)
                //默认
                .orElse(false);
        //step 3 根据地图块“人物”，判断原地踏步
        steppingState = steppingState || Optional.ofNullable(this.person.getWorld())
                //获取地图块矩阵
                .map(World::getTileMap)
                //获取对应目的地
                .map(p -> p.getTile(destX, destY))
                //获取人
                .map(Tile::getPerson)
                //如果人存在, 则不能走
                .map(obj -> true)
                //默认
                .orElse(false);
        //返回
        return steppingState;
    }

    /**
     * 结束走路
     */
    private void walkEnd() {

        /**
         * 移动判定, 人物坐标
         */

        //将当前坐标改为移动结束的坐标(这么做还有个好处, 该坐标可以转化为 int)
        this.worldX = this.destX;
        this.worldY = this.destY;
        this.tileX = this.destX;
        this.tileY = this.destY;

        //其他走路参数置 0
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;

        /**
         * 移动判定 人物动画状态
         */

        //动画持续时间重置
        this.animTime = 0F;
        //改变人物状态为站立
        this.actionState = Person.ActionEnum.STAND;
        //重置人物是否原地踏步状态
        this.steppingState = false;

        /**
         * 移动 地图块内 对应的人物实体
         */

        //获取当前世界的地图块
        TileMap tileMap = this.person.getWorld().getTileMap();
        //人物加入最新的地图块
        tileMap.setPerson(this.tileX, this.tileY, this.person);
        //以下尝试删除旧位置的人物
        tileMap.removePerson(this.tileX + 1, this.tileY, this.person);
        tileMap.removePerson(this.tileX - 1, this.tileY, this.person);
        tileMap.removePerson(this.tileX, this.tileY + 1, this.person);
        tileMap.removePerson(this.tileX, this.tileY - 1, this.person);
    }

}