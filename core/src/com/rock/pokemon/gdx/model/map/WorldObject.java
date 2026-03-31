package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.rock.pokemon.gdx.enums.FileEnum;
import com.rock.pokemon.gdx.model.manager.MyAssetManager;
import com.rock.pokemon.gdx.model.map.config.EventMapConfig;
import com.rock.pokemon.gdx.model.map.config.WorldObjectMapConfig;
import com.rock.pokemon.gdx.model.map.renderer.YSortable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 事物实体 可以是一个树、一朵花、一个牌子、甚至是一个人物(动画)等等,在地图网格上面,可动可静,可以是图片也可以是动画
 *
 * @Author ayl
 * @Date 2022-10-21
 */
@Getter
@Setter
public class WorldObject implements YSortable {

    /**
     * 基本数据
     */

    //备注
    private String remark;

    //事物ID(用于事件精确定位)
    private String worldObjectId;

    //当前事物的坐标
    private int x;
    private int y;

    //当前事务占用地图网格的列表,可以理解为碰撞体积,1*1=1个点,2*5=10个点,当不可以行走时,这里的地图块就无法行走
    private List<GridPoint2> gridPointList;

    //一个事物实体的宽高,这个和人物、地图块不同,有的事物可以1*1(草),有的则是1*1.5(门),甚至可以是房子5*6等等
    private float width;
    private float height;

    //是否可以行走(草可以走过去,树不行)
    private boolean walkable;

    /**
     * 图片 or 动画
     */

    //该事物的图片帧
    private TextureRegion texture;

    //该事物的动画
    private Animation<TextureRegion> animation;

    //该事物的动画帧
    private float animationTimer;

    //是否连续播放(eg:花花草草会一直动=true,门只有事件控制动=false)
    private boolean layContinuously;

    //强制播放动画次数,一些不连续播放的事物,通过事件播放的开关
    private int forceAnimationTimes;

    /**
     * 事件
     */

    //门事件,一种事件类型,移动过程中即将碰到触发该事件
    private EventMapConfig.Event doorEvent;

    /**
     * 通过配置的初始化方式
     *
     * @param myAssetManager 资源管理器
     * @param mapNode        事物配置类
     * @param worldObjectId  事物id(选填)
     * @param x              坐标x
     * @param y              坐标y
     * @param doorEvent      门事件(选填)
     */
    public WorldObject(MyAssetManager myAssetManager, WorldObjectMapConfig.WorldObjectMapNode mapNode, String worldObjectId, int x, int y, EventMapConfig.Event doorEvent) {

        /**
         * 基础
         */

        //备注
        this.remark = mapNode.getRemark();
        //记录id
        this.worldObjectId = worldObjectId;

        /**
         * 事件
         */

        //直接记录门事件(节点对象已在加载阶段解析为具体类型)
        this.doorEvent = doorEvent;

        /**
         * 网格计算
         */

        //坐标
        this.x = x;
        this.y = y;

        //宽高
        this.width = mapNode.getWidth();
        this.height = mapNode.getHeight();

        //是否可以行走,默认可以行走
        this.walkable = Optional.ofNullable(mapNode)
                .map(WorldObjectMapConfig.WorldObjectMapNode::getWalkable)
                .orElse(true);

        //是否连续播放,事物默认都是连续播放
        this.layContinuously = Optional.ofNullable(mapNode)
                .map(WorldObjectMapConfig.WorldObjectMapNode::getLayContinuously)
                .orElse(true);

        //初始化
        this.gridPointList = new ArrayList<>();
        //循环碰撞体积
        for (WorldObjectMapConfig.Location location : mapNode.getTileList()) {
            //组装占用的地图网格
            this.gridPointList.add(new GridPoint2(location.getX(), location.getY()));
        }

        /**
         * 处理动画、图片资源
         */

        //默认
        this.animationTimer = 0F;
        this.forceAnimationTimes = 0;
        this.animation = null;
        this.texture = null;

        //获取资源路径
        String filePath = FileEnum.parseByCode(mapNode.getFileCode()).getPath();
        //如果有资源
        if (StringUtils.isNotBlank(filePath)) {
            //判断是动画还是静态
            if (mapNode.getFrameDuration() == null) {
                //读取图片资源
                this.texture = myAssetManager.getTextureAtlas(filePath).findRegion(mapNode.getRegionName());
            } else {
                //读取动画资源,暂时默认动画模式
                this.animation = new Animation<>(mapNode.getFrameDuration(),
                        myAssetManager.getTextureAtlas(filePath).findRegions(mapNode.getRegionName()),
                        Animation.PlayMode.LOOP_PINGPONG);
            }
        }

    }

    /**
     * 每帧更新
     *
     * @param delta 每帧时间
     */
    public void update(float delta) {
        //如果不存在动画
        if (this.animation == null) {
            //过
            return;
        }
        //如果不是连续播放 and 没有强制播放
        if (this.layContinuously == false && this.forceAnimationTimes < 1) {
            //过
            return;
        }
        //叠加动画帧时间
        this.animationTimer += delta;
        //动画总时长
        float duration = this.animation.getAnimationDuration();
        //异常保护
        if (duration <= 0F) {
            //过
            return;
        }
        //如果当前动画走过了一轮
        if (this.animationTimer < duration) {
            //过
            return;
        }
        //如果有强制播放次数
        if (this.forceAnimationTimes > 0) {
            //删除一次
            this.forceAnimationTimes--;
        }
        //当前播放模式
        Animation.PlayMode playMode = this.animation.getPlayMode();
        //单向播放结束后可停在终帧(开门保持/关门保持)
        boolean holdEndFrame = playMode == Animation.PlayMode.NORMAL || playMode == Animation.PlayMode.REVERSED;
        //仍需强制播放下一次
        if (this.forceAnimationTimes > 0) {
            //重置动画时间
            this.animationTimer = 0F;
            //过
            return;
        }
        //连续播放对象
        if (this.layContinuously == true) {
            //循环模式下保持流畅衔接
            this.animationTimer = this.animationTimer % duration;
            //过
            return;
        }
        //非连续播放：根据模式决定停在起帧还是终帧
        this.animationTimer = holdEndFrame ? duration : 0F;
    }

    /**
     * 返回动画帧
     *
     * @return
     */
    public TextureRegion getSprite() {
        //如果存在图片
        if (this.texture != null) {
            //返回图片
            return this.texture;
        }
        //如果动画存在
        if (this.animation != null) {
            //返回当前动画帧图片
            return this.animation.getKeyFrame(this.animationTimer);
        }
        //默认
        return null;
    }

    public float getWorldX() {
        return this.x;
    }

    public float getWorldY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    /**
     * 仅用来调试展示
     *
     * @return
     */
    @Override
    public String toString() {
        return this.remark;
    }

}
