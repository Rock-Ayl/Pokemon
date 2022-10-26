package com.rock.pokemon.gdx.screen.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.model.Camera;
import com.rock.pokemon.gdx.model.map.Tile;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.people.Person;

/**
 * 世界渲染器,用它统一渲染世界的每个地图块、人物、事物
 *
 * @Author ayl
 * @Date 2022-10-23
 */
public class WorldRenderer {

    //资源管理器
    private AssetManager assetManager;

    //一个完整的世界实体
    private World world;

    /**
     * 初始化方法,要渲染,得有世界和图片资源吧.
     *
     * @param assetManager 资源管理器
     * @param world        世界实体
     */
    public WorldRenderer(AssetManager assetManager, World world) {
        this.assetManager = assetManager;
        this.world = world;
    }

    /**
     * 使用渲染器和相机,计算世界坐标,然后计算人物、地图块、事物的图层,最后渲染
     *
     * @param batch  渲染器
     * @param camera 相机
     */
    public void render(SpriteBatch batch, Camera camera) {

        //计算出世界的真实起始点,让世界始终以主角(相机)为中心
        float worldStartX = Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        /**
         * 先渲染地图网格(图层最下)
         */

        //循环1
        for (int x = 0; x < this.world.getTileMap().getWidth(); x++) {
            //循环2
            for (int y = 0; y < this.world.getTileMap().getHeight(); y++) {
                //当前地图块对象
                Tile tile = this.world.getTileMap().getTileMap()[x][y];
                //渲染
                batch.draw(
                        //图片
                        tile.getSprite(this.assetManager),
                        //真实坐标 + 当前坐标 * 网格倍率
                        worldStartX + tile.getX() * Settings.SCALED_TILE_SIZE,
                        worldStartY + tile.getY() * Settings.SCALED_TILE_SIZE,
                        //使用地图块的宽高 * 网格倍率
                        tile.getWidth() * Settings.SCALED_TILE_SIZE,
                        tile.getHeight() * Settings.SCALED_TILE_SIZE
                );
            }
        }

        /**
         * 再渲染人物(图层在地图上)
         */

        //渲染人物列表
        for (Person person : this.world.getPersonList()) {
            //根据世界起点,渲染主角
            batch.draw(
                    //图片
                    person.getSprite(),
                    //真实坐标 + 当前坐标 * 网格倍率
                    worldStartX + person.getWorldX() * Settings.SCALED_TILE_SIZE,
                    worldStartY + person.getWorldY() * Settings.SCALED_TILE_SIZE,
                    //使用地图块的宽高 * 网格倍率
                    person.getWidth() * Settings.SCALED_TILE_SIZE,
                    person.getHeight() * Settings.SCALED_TILE_SIZE
            );
        }

    }

}
