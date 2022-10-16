package com.rock.pockmon.gdx.model.map;

import com.rock.pockmon.gdx.enums.TileEnum;

/**
 * 地图网格
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class TileMap {

    //网格属性
    private int width;
    private int height;
    //网格本身
    private Tile[][] map;

    /**
     * todo 初始化地图网格,目前只有草
     *
     * @param width  宽
     * @param height 高
     */
    public TileMap(int width, int height) {
        //初始化网格及其属性
        this.width = width;
        this.height = height;
        this.map = new Tile[width][height];
        //循环1
        for (int x = 0; x < this.width; x++) {
            //循环2
            for (int y = 0; y < this.height; y++) {
                //一个随机数
                double random = Math.random();
                //根据随机数生成草
                if (random < 0.5D) {
                    //草1
                    this.map[x][y] = new Tile(x, y, TileEnum.GRASS_1);
                } else {
                    //草2
                    this.map[x][y] = new Tile(x, y, TileEnum.GRASS_2);
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getMap() {
        return map;
    }

}
