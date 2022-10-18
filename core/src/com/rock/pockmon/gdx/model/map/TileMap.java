package com.rock.pockmon.gdx.model.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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

    //地图网格本身
    private Tile[][] map;

    /**
     * 初始化地图网格,目前只有草
     *
     * @param assetManager 资源管理器
     * @param width        宽
     * @param height       高
     */
    public TileMap(AssetManager assetManager, int width, int height) {

        //获取资源
        TextureAtlas grass = assetManager.get("assets/packed/image/map/grass/textures.atlas", TextureAtlas.class);
        //获取草1,草2
        TextureAtlas.AtlasRegion grass1 = grass.findRegion("1");
        TextureAtlas.AtlasRegion grass2 = grass.findRegion("2");

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
                if (random < 0.9D) {
                    //草1
                    this.map[x][y] = new Tile(x, y, grass1);
                } else {
                    //草2
                    this.map[x][y] = new Tile(x, y, grass2);
                }
            }
        }
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

    public Tile[][] getMap() {
        return map;
    }

}
