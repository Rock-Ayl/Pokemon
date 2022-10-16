package com.rock.pockmon.gdx.model.map;

import com.rock.pockmon.gdx.enums.TileEnum;

/**
 * 单个地图块
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class Tile {

    //对应地图块枚举
    private TileEnum tileEnum;

    //是否是一个墙
    private boolean wall;

    //是否是一个水
    private boolean water;

    /**
     * 根据枚举初始化
     *
     * @param tileEnum 地图块枚举
     */
    public Tile(TileEnum tileEnum) {
        //记录
        this.tileEnum = tileEnum;
        //默认视为可通过地形
        this.wall = false;
        this.water = false;
    }

    /**
     * Get Set 方法
     *
     * @return
     */

    public TileEnum getTileEnum() {
        return tileEnum;
    }

    public boolean isWall() {
        return wall;
    }

    public boolean isWater() {
        return water;
    }

}
