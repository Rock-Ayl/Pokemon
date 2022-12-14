package com.rock.pokemon.gdx.model.people;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.rock.pokemon.gdx.enums.ActionEnum;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.PersonEnum;
import com.rock.pokemon.gdx.model.SoundManager;
import com.rock.pokemon.gdx.model.YSortable;
import com.rock.pokemon.gdx.model.animation.PersonAnimationSet;
import com.rock.pokemon.gdx.model.map.Tile;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.map.WorldObject;

import java.util.Optional;

/**
 * 人物实体
 *
 * @Author ayl
 * @Date 2022-10-13
 */
public class Person implements YSortable {

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

    //人物宽高,设定用户宽高,绿宝石中,通常人物高度占接近1.5个地图网格
    private float width = 1.0F;
    private float height = 1.5F;

    /**
     * 音效
     */

    //通用的音效管理器
    private SoundManager soundManager;

    //上次发出音效的时间(时间戳)
    private long lastSoundTime;

    //音效之间的间隔 毫秒
    private static final long SOUND_TIME_INTERVAL = 500L;

    /**
     * 移动相关
     */

    //该人物所处的世界
    private World world;

    //人物动画集合
    private PersonAnimationSet animationSet;

    //人物当前脸的方向(可以是走也可以是站立,只是方向,根据方向+状态不同,有不同的判定)
    private DirectionEnum facing;
    //是否为原地踏步
    private boolean stepping;
    //当前状态(站立、走路、跑步、骑自行车、冲浪等等,与方向一起判定)
    private ActionEnum actionState;

    //起始坐标和目标坐标
    private int srcX, srcY;
    private int destX, destY;

    //动画持续时间
    private float animTime;
    //完成一次动画的总时间,单位秒
    public static final float ONCE_ANIM_TIME = 0.3F;

    //持续一个方向走路的时间
    private float continueWalkTime;
    //持续走路时,如果方向和之前相同,那么该参数会为true,让它会继续走下去,用来判定[连续相同方向走路],否则会停下
    private boolean moveRequestThisFrame;

    /**
     * 使用人物枚举初始化
     *
     * @param personEnum   人物枚举
     * @param world        该人物所处的世界
     * @param x            人物初始坐标x
     * @param y            人物初始坐标y
     * @param assetManager 资源管理器
     * @param soundManager 通用的音效管理器
     */
    public Person(PersonEnum personEnum, World world, int x, int y, AssetManager assetManager, SoundManager soundManager) {

        /**
         * 基本信息
         */

        //人物枚举
        this.personEnum = personEnum;

        //坐标
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;

        //记录该人物所处的世界
        this.world = world;
        //该世界也加入该人物
        this.world.addPerson(this);

        //人物动作状态
        this.actionState = ActionEnum.STAND;
        //人物方向
        this.facing = DirectionEnum.SOUTH;

        //记录通用音效
        this.soundManager = soundManager;

        //初始化人物动画集合
        this.animationSet = new PersonAnimationSet(assetManager, this.personEnum);

    }

    /**
     * 处理移动中时的动画(可以理解为补帧)
     *
     * @param delta 每帧的时间
     */
    public void update(float delta) {
        //根据当前状态判定
        switch (actionState) {
            case RUN:
                break;
            case CYCLING:
                break;
            case SURFING:
                break;
            //如果此时还在走路
            case WALK:
                //叠加本次走路、动画的持续时间
                this.animTime += delta;
                this.continueWalkTime += delta;
                //计算出其真实的世界坐标,据说绿宝石是线性的,这里不太懂,但大体的意思是按照线性的逻辑不断计算出对应x,y坐标
                this.worldX = Interpolation.linear.apply(srcX, destX, animTime / ONCE_ANIM_TIME);
                this.worldY = Interpolation.linear.apply(srcY, destY, animTime / ONCE_ANIM_TIME);
                //每次持续动画时间结束时(如果继续走,代表要进行下一次动画了)
                if (animTime >= ONCE_ANIM_TIME) {
                    //计算出本次动画多出的那极少一部分时间(因为每次都会有极少的误差),给持续一个方向走路的时间,让动画稳定
                    continueWalkTime = continueWalkTime - (animTime - ONCE_ANIM_TIME);
                    //结束本次走路
                    walkEnd();
                    //如果此时要继续按照这个方向走路
                    if (moveRequestThisFrame) {
                        //似乎是要重置移动
                        move(this.facing);
                    } else {
                        //不再按照该方向走路了,那么持续走路时间归0,从头算起动画帧
                        continueWalkTime = 0F;
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
     * 单纯的脸换个方向,当然,得站着的时候
     *
     * @param facing 方向枚举
     * @return
     */
    public void changeDir(DirectionEnum facing) {
        //如果不是站着
        if (actionState != ActionEnum.STAND) {
            //过
            return;
        }
        //变换当前脸的方向
        this.facing = facing;
    }

    /**
     * 人物移动判定
     *
     * @param directionEnum 接下来移动的方向
     */
    public boolean move(DirectionEnum directionEnum) {
        //根据人物此时的行动状态判定
        switch (actionState) {
            //走路
            case WALK:
                //如果此时接下来的走的方向和脸和方向一致
                if (this.facing == directionEnum) {
                    //设定继续按照该方向走路
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
                //首先,判断目标位置是否越界
                this.stepping = destX < 0 || destY < 0 || destX >= this.world.getTileMap().getWidth() || destY >= this.world.getTileMap().getHeight();
                //如果未越界
                if (this.stepping == false) {
                    //获取目标地图块
                    Tile tile = this.world.getTileMap().getTile(destX, destY);
                    //获取这个地图块是否可以行走(判断上面有没有阻挡的事物)
                    this.stepping = Optional.ofNullable(tile)
                            .map(Tile::getWorldObject)
                            //这里获取是否可行走
                            .map(WorldObject::isWalkable)
                            //相反的判定
                            .map(p -> !p)
                            //默认
                            .orElse(false);
                }
                //如果是原地踏步
                if (this.stepping) {
                    //发出撞墙的音效
                    playNoWalk();
                }
                //开始走路
                walkStart(directionEnum);
                //移动成功
                return true;
        }
    }

    /**
     * 开始走路
     *
     * @param directionEnum 走的方向
     */
    private void walkStart(DirectionEnum directionEnum) {
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
        //如果不是原地踏步
        if (this.stepping == false) {
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
        //结束走路后,预设不是原地踏步
        this.stepping = false;
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
                //如果是踏步
                if (this.stepping) {
                    //返回踏步动画帧图片
                    return this.animationSet.getStepping(this.facing).getKeyFrame(this.continueWalkTime);
                } else {
                    //返回走路动画帧图片
                    return this.animationSet.getWalking(this.facing).getKeyFrame(this.continueWalkTime);
                }
                //站立
            case STAND:
                //返回站立图片
                return this.animationSet.getStanding(this.facing);
            //其他
            default:
                //默认站立南
                return this.animationSet.getStanding(DirectionEnum.EAST);
        }
    }

    /**
     * 人物发出撞墙的音效
     */
    public void playNoWalk() {
        //当前时间戳
        long thisTime = System.currentTimeMillis();
        //如果距离上次发出音效时间没有过间隔期
        if (thisTime - SOUND_TIME_INTERVAL < lastSoundTime) {
            //不发出音效
            return;
        }
        //发出撞墙音效
        this.soundManager.getSoundNoWalk().play();
        //记录发出音效的时间
        lastSoundTime = thisTime;
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
