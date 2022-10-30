package com.rock.pokemon.gdx.model.animation;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    //站-图片map
    private Map<DirectionEnum, TextureRegion> standingMap;

    /**
     * 根据人物枚举初始化动画集合
     *
     * @param assetManager 资源管理器
     * @param personEnum   人物枚举
     */
    public PersonAnimationSet(AssetManager assetManager, PersonEnum personEnum) {
        //获取资源
        TextureAtlas walkTextureAtlas = assetManager.get("assets/packed/image/people/" + personEnum.getName() + "/walk/textures.atlas", TextureAtlas.class);
        TextureAtlas standTextureAtlas = assetManager.get("assets/packed/image/people/" + personEnum.getName() + "/stand/textures.atlas", TextureAtlas.class);

        //初始化原地踏步map(原地踏步,其实就是走路变慢)
        steppingMap = new HashMap<>();
        //循环方向枚举
        for (DirectionEnum walkDir : DirectionEnum.values()) {
            //载入动画 秒/帧(N图-1=帧),名字,模式,并组装
            steppingMap.put(walkDir, new Animation(2 * Person.ONCE_ANIM_TIME / 2F, walkTextureAtlas.findRegions(walkDir.getName()), Animation.PlayMode.LOOP_PINGPONG));
        }

        //初始化走路map
        walkingMap = new HashMap<>();
        //循环方向枚举
        for (DirectionEnum walkDir : DirectionEnum.values()) {
            //载入动画 秒/帧(N图-1=帧),名字,模式,并组装
            walkingMap.put(walkDir, new Animation(Person.ONCE_ANIM_TIME / 2F, walkTextureAtlas.findRegions(walkDir.getName()), Animation.PlayMode.LOOP_PINGPONG));
        }

        //初始化站立map
        standingMap = new HashMap<>();
        //循环
        for (DirectionEnum standDir : DirectionEnum.values()) {
            //载入图片,并组装
            standingMap.put(standDir, standTextureAtlas.findRegion(standDir.getName()));
        }

    }

    /**
     * 原地踏步
     *
     * @param directionEnum 根据方向获取踏步动画
     * @return
     */
    public Animation<TextureRegion> getStepping(DirectionEnum directionEnum) {
        //返回
        return this.steppingMap.get(directionEnum);
    }

    /**
     * 走路
     *
     * @param directionEnum 根据方向获取走路动画
     * @return
     */
    public Animation<TextureRegion> getWalking(DirectionEnum directionEnum) {
        //返回
        return this.walkingMap.get(directionEnum);
    }

    /**
     * 站立
     *
     * @param directionEnum 根据方向获取站立图片
     * @return
     */
    public TextureRegion getStanding(DirectionEnum directionEnum) {
        //返回
        return this.standingMap.get(directionEnum);
    }

}
