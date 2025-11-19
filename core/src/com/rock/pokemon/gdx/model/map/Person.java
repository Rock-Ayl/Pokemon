package com.rock.pokemon.gdx.model.map;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.animation.PersonAnimationSet;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig.NpcMapNode;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig.NpcMapNodeEvent;
import com.rock.pokemon.gdx.model.map.renderer.YSortable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    //该npc对应的id
    private String npcId;

    //备注
    private String remark;

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
     * 人物动作枚举
     */
    @Getter
    public enum ActionEnum {

        STAND("stand", "站立"),
        WALK("walk", "走路"),

        ;

        //路径
        private String path;
        //中文名
        private String zhName;

        ActionEnum(String path, String zhName) {
            this.path = path;
            this.zhName = zhName;
        }

    }

    /**
     * 把移动/动画逻辑交给这个组件处理
     */
    private final PersonMovement movement;

    /**
     * 使用人物枚举初始化
     *
     * @param npcId       指定npcId
     * @param npcMapNode  npc配置
     * @param world       该人物所处的世界
     * @param x           人物初始坐标x
     * @param y           人物初始坐标y
     * @param pokemonGame 游戏对象
     */
    public Person(String npcId, NpcMapNode npcMapNode, World world, int x, int y, PokemonGame pokemonGame) {

        /**
         * 基本信息
         */

        //游戏对象
        this.pokemonGame = pokemonGame;
        this.npcId = npcId;
        this.remark = npcMapNode.getRemark();

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
        this.animationSet = new PersonAnimationSet(this.pokemonGame.getGameContext().getMyAssetManager(), npcMapNode);

        //记录事件列表
        this.eventList = npcMapNode.getEventList();

        /**
         * 与世界关联
         */

        //人物记录要加入的世界
        this.world = world;
        //人物加入世界
        this.world.addPerson(this);

        // 初始化移动组件
        this.movement = new PersonMovement(this);
    }

    // ================= 对外行为接口：全部委托给 PersonMovement =================

    /**
     * 处理移动中时的动画(可以理解为补帧)
     *
     * @param delta 每帧的时间
     */
    public void update(float delta) {
        this.movement.update(delta);
    }

    /**
     * 人物移动判定
     *
     * @param directionEnum 接下来移动的方向
     * @param walkEnum      走路状态
     * @return 是否成功发起一次移动(站立 - > 开始走)
     */
    public boolean move(DirectionEnum directionEnum, WalkEnum walkEnum) {
        return this.movement.move(directionEnum, walkEnum);
    }

    /**
     * 尝试停止走路
     */
    public void walkStop() {
        this.movement.walkStop();
    }

    /**
     * 单纯的脸换个方向, 当然, 得站着的时候
     *
     * @param facing 方向枚举
     */
    public void changeFacingDir(DirectionEnum facing) {
        this.movement.changeFacingDir(facing);
    }

    /**
     * 获取当前人物动画图片或帧图片
     */
    public TextureRegion getSprite() {
        return this.movement.getSprite();
    }

    /**
     * 世界坐标 getter 保留, 方便其它地方取用
     */
    public float getWorldX() {
        return this.worldX;
    }

    public float getWorldY() {
        return this.worldY;
    }

    /**
     * 仅用来调试展示
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("[npcId=%s][remark=%s]", this.npcId, this.remark);
    }

}
