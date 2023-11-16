package com.rock.pokemon.gdx.model.animation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.PersonEnum;
import com.rock.pokemon.gdx.model.people.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个人物动画集合
 * 一个人物实体会有一个,存储着该人物所有的动画
 *
 * @Author ayl
 * @Date 2022-10-17
 */
public class PersonAnimationSet {

    /**
     * 每种动作的动画、图片缓存
     */

    //原地踏步-动画map
    private Map<DirectionEnum, Animation<TextureRegion>> steppingMap;

    //走-动画map
    private Map<DirectionEnum, Animation<TextureRegion>> walkingMap;

    //跑步-动画map
    private Map<DirectionEnum, Animation<TextureRegion>> runningMap;

    //站-图片map
    private Map<DirectionEnum, TextureRegion> standingMap;

    /**
     * 根据人物枚举初始化动画集合
     *
     * @param assetManager 资源管理器
     * @param personEnum   人物枚举
     */
    public PersonAnimationSet(AssetManager assetManager, PersonEnum personEnum) {

        //获取动画资源,没有也无所谓,毕竟不是所有人都有主角那么多的动作
        TextureAtlas walkTextureAtlas = assetManager.get(String.format(FilePaths.TEXTURES_ALTA_PEOPLE_WALK, personEnum.getName()), TextureAtlas.class, false);
        TextureAtlas standTextureAtlas = assetManager.get(String.format(FilePaths.TEXTURES_ALTA_PEOPLE_STAND, personEnum.getName()), TextureAtlas.class, false);
        TextureAtlas runTextureAtlas = assetManager.get(String.format(FilePaths.TEXTURES_ALTA_PEOPLE_RUN, personEnum.getName()), TextureAtlas.class, false);

        //初始化走路map
        this.walkingMap = new HashMap<>();
        //初始化原地踏步map(原地踏步,其实就是走路变慢)
        this.steppingMap = new HashMap<>();
        //初始化站立map
        this.standingMap = new HashMap<>();
        //初始化跑步map
        this.runningMap = new HashMap<>();

        //循环方向枚举
        for (DirectionEnum directionEnum : DirectionEnum.values()) {

            //如果有跑步资源
            if (runTextureAtlas != null) {
                //载入跑步动画 秒/帧(N图-1=帧),名字,模式,并组装
                this.runningMap.put(directionEnum, new Animation<>(Person.RUN_ONCE_ANIM_TIME / 2.2F, runTextureAtlas.findRegions(directionEnum.getName()), Animation.PlayMode.LOOP_PINGPONG));
            }

            //如果有走路资源
            if (walkTextureAtlas != null) {
                //载入走路动画 秒/帧(N图-1=帧),名字,模式,并组装
                this.walkingMap.put(directionEnum, new Animation<>(Person.WALK_ONCE_ANIM_TIME / 2F, walkTextureAtlas.findRegions(directionEnum.getName()), Animation.PlayMode.LOOP_PINGPONG));
                //载入原地踏步动画 秒/帧(N图-1=帧),名字,模式,并组装
                this.steppingMap.put(directionEnum, new Animation<>(Person.WALK_ONCE_ANIM_TIME, walkTextureAtlas.findRegions(directionEnum.getName()), Animation.PlayMode.LOOP_PINGPONG));
            }

            //如果有站立资源
            if (standTextureAtlas != null) {
                //载入站立图片,并组装
                this.standingMap.put(directionEnum, standTextureAtlas.findRegion(directionEnum.getName()));
            }

        }

    }

    /**
     * 获取原地踏步动画
     *
     * @param directionEnum 方向
     * @return
     */
    public Animation<TextureRegion> getStepping(DirectionEnum directionEnum) {
        //返回
        return this.steppingMap.get(directionEnum);
    }

    /**
     * 获取走路动画
     *
     * @param directionEnum 方向
     * @return
     */
    public Animation<TextureRegion> getWalking(DirectionEnum directionEnum) {
        //返回
        return this.walkingMap.get(directionEnum);
    }

    /**
     * 获取站立图片
     *
     * @param directionEnum 方向
     * @return
     */
    public TextureRegion getStanding(DirectionEnum directionEnum) {
        //返回
        return this.standingMap.get(directionEnum);
    }

    /**
     * 获取跑步动画
     *
     * @param directionEnum 方向
     * @return
     */
    public Animation<TextureRegion> getRunning(DirectionEnum directionEnum) {
        //返回
        return this.runningMap.get(directionEnum);
    }

}
