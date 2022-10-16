package com.rock.pockmon.gdx.model.map;

import com.badlogic.gdx.math.Rectangle;
import com.rock.pockmon.gdx.enums.TileEnum;

/**
 * 单个地图块
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class Tile extends Rectangle {

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
    public Tile(int x, int y, TileEnum tileEnum) {

        //地图块枚举
        this.tileEnum = tileEnum;

        //坐标(视为在地图网格中的)
        this.x = x;
        this.y = y;

        //默认视为可通过地形
        this.wall = false;
        this.water = false;

        //设定地图块宽高,绿宝石中,所有都是1
        this.width = 1.0F;
        this.height = 1.0F;
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
