package com.rock.pokemon.gdx.model.map;

/**
 * 地图网格
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class TileMap {

    //地图网格宽高
    private int width, height;

    //地图网格本身
    private Tile[][] map;

    /**
     * 初始化地图网格,目前只有草
     *
     * @param width  宽
     * @param height 高
     */
    public TileMap(int width, int height) {

        //初始化地图网格
        this.map = new Tile[width][height];

        //初始化地图网格
        this.width = width;
        this.height = height;

    }

    /**
     * 以下为 get set 方法
     *
     * @return
     */

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getTileMap() {
        return map;
    }

    public Tile getTile(int x, int y) {
        return map[x][y];
    }

}
