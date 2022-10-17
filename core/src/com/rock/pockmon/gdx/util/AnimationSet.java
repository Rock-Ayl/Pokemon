package com.rock.pockmon.gdx.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pockmon.gdx.enums.DirectionEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 动画集合
 *
 * @Author ayl
 * @Date 2022-10-17
 */
public class AnimationSet {

    //记录方向和动画的缓存
    private Map<DirectionEnum, Animation<TextureRegion>> walkingMap;
    private Map<DirectionEnum, TextureRegion> standingMap;

    //初始化
    public AnimationSet(Animation<TextureRegion> walkNorth,
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

    //走路
    public Animation<TextureRegion> getWalking(DirectionEnum directionEnum) {
        //返回
        return this.walkingMap.get(directionEnum);
    }

    //站立
    public TextureRegion getStanding(DirectionEnum directionEnum) {
        //返回
        return this.standingMap.get(directionEnum);
    }

}
