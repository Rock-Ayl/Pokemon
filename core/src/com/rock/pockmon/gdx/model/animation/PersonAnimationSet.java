package com.rock.pockmon.gdx.model.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pockmon.gdx.enums.DirectionEnum;

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

    //走-动画map
    private Map<DirectionEnum, Animation<TextureRegion>> walkingMap;
    //站-图片map
    private Map<DirectionEnum, TextureRegion> standingMap;

    //初始化
    public PersonAnimationSet(Animation<TextureRegion> walkNorth,
                              Animation<TextureRegion> walkSouth,
                              Animation<TextureRegion> walkEast,
                              Animation<TextureRegion> walkWest,
                              TextureRegion standNorth,
                              TextureRegion standSouth,
                              TextureRegion standEast,
                              TextureRegion standWest) {
        //初始化走路map
        walkingMap = new HashMap<>();
        walkingMap.put(DirectionEnum.NORTH, walkNorth);
        walkingMap.put(DirectionEnum.SOUTH, walkSouth);
        walkingMap.put(DirectionEnum.EAST, walkEast);
        walkingMap.put(DirectionEnum.WEST, walkWest);

        //初始化站立map
        standingMap = new HashMap<>();
        standingMap.put(DirectionEnum.NORTH, standNorth);
        standingMap.put(DirectionEnum.SOUTH, standSouth);
        standingMap.put(DirectionEnum.EAST, standEast);
        standingMap.put(DirectionEnum.WEST, standWest);
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
