package com.rock.pockmon.gdx.model.people;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.rock.pockmon.gdx.enums.ActionEnum;
import com.rock.pockmon.gdx.enums.DirectionEnum;
import com.rock.pockmon.gdx.enums.PersonEnum;
import com.rock.pockmon.gdx.model.animation.PersonAnimationSet;
import com.rock.pockmon.gdx.model.map.TileMap;

/**
 * 人物实体
 *
 * @Author ayl
 * @Date 2022-10-13
 */
public class Person {

    /**
     * 基本信息
     */

    //人物枚举
    private PersonEnum personEnum;

    //当前人物在地图网格的坐标(放弃用Rectangle是因为Rectangle是float类型的),这个在人类实体里仅用来判定移动时和地图块之间的关系
    private int x;
    private int y;

    //当前人物在世界的真实坐标(考虑到移动、骑车、跑步等动作不是时刻在网格中,所采用的世界坐标,运营到补帧动画)
    private float worldX;
    private float worldY;

    //人物宽高
    private float width;
    private float height;

    /**
     * 移动相关
     */

    //人物动画集合
    private PersonAnimationSet animationSet;

    //人物当前脸的方向(可以是走也可以是站立,只是方向,根据方向+状态不同,有不同的判定)
    private DirectionEnum facing;
    //当前状态(站立、走路、跑步、骑自行车、冲浪等等,与方向一起判定)
    private ActionEnum actionState;

    //起始坐标和目标坐标
    private int srcX, srcY;
    private int destX, destY;

    //动画持续时间
    private float animTime;
    //完成一次动画的总时间,单位秒
    private float onceAnimTime = 0.3F;

    //走路持续时间
    private float walkTime;
    //????
    private boolean moveRequestThisFrame;

    /**
     * 使用人物枚举初始化
     *
     * @param personEnum   人物枚举
     * @param assetManager 资源管理器
     */
    public Person(PersonEnum personEnum, AssetManager assetManager) {

        /**
         * 基本信息
         */

        //人物枚举
        this.personEnum = personEnum;

        //人物动作状态
        this.actionState = ActionEnum.STAND;
        //人物方向
        this.facing = DirectionEnum.SOUTH;

        //初始化人物动画集合
        this.animationSet = new PersonAnimationSet(assetManager, this.personEnum);

        //设定用户宽高,绿宝石中,通常人物高度占接近1.5个地图网格
        this.width = 1.0F;
        this.height = 1.5F;

    }

    /**
     * 处理移动中时的动画(可以理解为补帧)
     *
     * @param delta 每帧的时间
     */
    public void update(TileMap tileMap, float delta) {
        //根据当前状态判定
        switch (actionState) {
            case RUN:
                break;
            case CYCLING:
                break;
            case SURFING:
                break;
            //如果是走路
            case WALK:
                //叠加本次走路的动画时间
                this.animTime += delta;
                this.walkTime += delta;
                //计算出其真实的世界坐标,据说绿宝石是线性的,这里不太懂
                this.worldX = Interpolation.linear.apply(srcX, destX, animTime / onceAnimTime);
                this.worldY = Interpolation.linear.apply(srcY, destY, animTime / onceAnimTime);
                //如果动画时间结束了
                if (animTime >= onceAnimTime) {
                    //????
                    float leftOverTime = animTime - onceAnimTime;
                    //????
                    walkTime -= leftOverTime;
                    //结束走路
                    walkEnd();
                    //????????
                    if (moveRequestThisFrame) {
                        //似乎是要重置移动
                        move(tileMap, this.facing);
                    } else {
                        //???似乎是不动了
                        walkTime = 0F;
                    }
                }
                break;
            //站立或其他
            case STAND:
            default:
                //直接结束
                break;
        }
        //?????为什么呢
        this.moveRequestThisFrame = false;
    }

    /**
     * 人物移动判定
     *
     * @param tileMap       当前地图网格
     * @param directionEnum 接下来移动的方向
     */
    public boolean move(TileMap tileMap, DirectionEnum directionEnum) {
        //根据人物此时的行动状态判定
        switch (actionState) {
            //走路
            case WALK:
                //如果此时接下来的走的方向和脸和方向一致
                if (this.facing == directionEnum) {
                    //???
                    this.moveRequestThisFrame = true;
                }
                //让他继续走下去吧
                return false;
            //其他
            case CYCLING:
            case RUN:
            case STAND:
            case SURFING:
            default:
                //计算出移动完的目标坐标
                int destX = this.x + directionEnum.getDx();
                int destY = this.y + directionEnum.getDy();
                //判定是否要移动(越界)
                boolean move = !(destX < 0 || destY < 0 || destX >= tileMap.getWidth() || destY >= tileMap.getHeight());
                //开始走路
                walkStart(directionEnum, move);
                //移动成功
                return true;
        }
    }

    /**
     * 开始走路
     *
     * @param directionEnum 走的方向
     * @param move          是否移动(移动坐标会改变)
     */
    private void walkStart(DirectionEnum directionEnum, boolean move) {
        //改变人物状态为走路
        this.actionState = ActionEnum.WALK;
        //改变脸的方向
        this.facing = directionEnum;
        //起始坐标
        this.srcX = this.x;
        this.srcY = this.y;
        //目标坐标
        this.destX = this.x;
        this.destY = this.y;
        //如果要移动
        if (move) {
            //计算出移动后的目标坐标
            this.destX += directionEnum.getDx();
            this.destY += directionEnum.getDy();
        }
        //初始化活动时间
        this.animTime = 0F;
    }

    /**
     * 结束走路
     */
    private void walkEnd() {
        //改变人物状态为站立
        this.actionState = ActionEnum.STAND;
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
        this.animTime = 0;
    }

    /**
     * 获取当前人物动画图片或帧图片
     *
     * @return
     */
    public TextureRegion getSprite() {
        //根据当前状态判定
        switch (actionState) {
            //走路
            case WALK:
                //返回走路动画帧图片
                return this.animationSet.getWalking(this.facing).getKeyFrame(this.walkTime);
            //站立
            case SURFING:
                //返回站立图片
                return this.animationSet.getStanding(this.facing);
            //其他
            default:
                //默认站立南
                return this.animationSet.getStanding(DirectionEnum.SOUTH);
        }
    }

    /**
     * 以下是 get set 方法
     *
     * @return
     */

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
