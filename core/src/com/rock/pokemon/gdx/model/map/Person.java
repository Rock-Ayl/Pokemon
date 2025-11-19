package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.enums.ActionEnum;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.animation.PersonAnimationSet;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig.NpcMapNode;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig.NpcMapNodeEvent;
import com.rock.pokemon.gdx.model.map.renderer.YSortable;
import lombok.Getter;

import java.util.List;

/**
 * 人物实体(可以是精灵、人物、甚至道具精灵球)
 *
 * @Author ayl
 * @Date 2022-10-13
 */
@Getter
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

    //人物宽高,设定用户宽高,绿宝石中,通常人物高度占接近1.5个地图网格
    private float width = 1.0F;
    private float height = 1.5F;

    /**
     * 世界、事件、动画相关
     */

    //该人物所处的世界
    private World world;

    //事件列表
    private List<NpcMapNodeEvent> eventList;

    //移动处理器
    private PersonMovement movement;

    //人物动画集合
    private PersonAnimationSet animationSet;

    /**
     * 使用人物枚举初始化
     *
     * @param npcId       指定npcId
     * @param npcMapNode  npc配置
     * @param world       该人物所处的世界
     * @param x           人物初始坐标x(地图格子)
     * @param y           人物初始坐标y(地图格子)
     * @param pokemonGame 游戏对象
     */
    public Person(String npcId, NpcMapNode npcMapNode, World world, int x, int y, PokemonGame pokemonGame) {

        /**
         * 基本信息
         */

        //初始化移动组件（负责坐标、状态、动画时间线等）
        this.movement = new PersonMovement(this, x, y);

        //游戏对象
        this.pokemonGame = pokemonGame;
        this.npcId = npcId;
        this.remark = npcMapNode.getRemark();

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

    }

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
     * 当前人物在地图网格的坐标
     */

    public int getX() {
        return this.movement.getTileX();
    }

    public int getY() {
        return this.movement.getTileY();
    }

    /**
     * 世界坐标
     */

    public float getWorldX() {
        return this.movement.getWorldX();
    }

    public float getWorldY() {
        return this.movement.getWorldY();
    }

    /**
     * 人物-脸方向
     *
     * @return
     */
    public DirectionEnum getFacingState() {
        return this.movement.getFacingState();
    }

    /**
     * 人物-动作状态
     *
     * @return
     */
    public ActionEnum getActionState() {
        return this.movement.getActionState();
    }

    /**
     * 仅用来调试展示
     */
    @Override
    public String toString() {
        return String.format("[npcId=%s][remark=%s]", this.npcId, this.remark);
    }

}