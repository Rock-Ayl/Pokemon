package com.rock.pokemon.gdx.screen.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.model.Camera;
import com.rock.pokemon.gdx.model.YSortable;
import com.rock.pokemon.gdx.model.map.Tile;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.map.WorldObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    //渲染器图层列表,这个将决定人物、事物的渲染
    private List<YSortable> sortList;

    /**
     * 初始化方法,要渲染,得有世界和图片资源吧.
     *
     * @param assetManager 资源管理器
     * @param world        世界实体
     */
    public WorldRenderer(AssetManager assetManager, World world) {
        this.assetManager = assetManager;
        this.world = world;
        this.sortList = new ArrayList<>();
    }

    /**
     * 使用渲染器和相机,计算世界坐标,然后计算人物、地图块、事物的图层,最后渲染
     *
     * @param batch  渲染器
     * @param camera 相机
     */
    public void render(SpriteBatch batch, Camera camera) {

        //计算出世界的坐标,让世界和相机始终以主角为中心
        float worldStartX = Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        /**
         * 先渲染地图网格(图层最下,无可争议)
         */

        //循环1
        for (int x = 0; x < this.world.getTileMap().getWidth(); x++) {
            //循环2
            for (int y = 0; y < this.world.getTileMap().getHeight(); y++) {
                //当前地图块对象
                Tile tile = this.world.getTileMap().getTileMap()[x][y];
                //渲染
                draw(batch, tile, worldStartX, worldStartY);
            }
        }

        /**
         * 再渲染可以行走的事物,并收集不可行走的事物进入sort列表,人物也要进入排序列表
         */

        //渲染事物列表
        for (WorldObject worldObject : this.world.getWorldObjectList()) {
            //如果可以行走,优先级仅仅比地图块高一点
            if (worldObject.isWalkable()) {
                //渲染
                draw(batch, worldObject, worldStartX, worldStartY);
                //本轮过
                continue;
            }
            //不可行走的记录到列表
            sortList.add(worldObject);
        }

        //sort列表加入所有的人物
        sortList.addAll(this.world.getPersonList());

        /**
         * 按照Y轴排序
         */

        Collections.sort(sortList, new WorldObjectYComparator());

        /**
         * 最后统一渲染人物、事物
         */

        //统一渲染人物、事物
        for (YSortable ySortable : this.sortList) {
            //渲染
            draw(batch, ySortable, worldStartX, worldStartY);
        }

        //结束时清理
        sortList.clear();

    }

    /**
     * 根据实现[YSortable]的class、渲染器、世界坐标,统一渲染世界的每一个图片
     *
     * @param batch       渲染器
     * @param ySortable   实现的class,可以是人物、事物、地图块
     * @param worldStartX 世界坐标x
     * @param worldStartY 世界坐标y
     */
    private void draw(Batch batch, YSortable ySortable, float worldStartX, float worldStartY) {
        //根据世界起点,渲染
        batch.draw(
                //图片
                ySortable.getSprite(),
                //世界坐标 + 当前坐标 * 网格倍率
                worldStartX + ySortable.getWorldX() * Settings.SCALED_TILE_SIZE,
                worldStartY + ySortable.getWorldY() * Settings.SCALED_TILE_SIZE,
                //人物的宽高 * 网格倍率
                ySortable.getWidth() * Settings.SCALED_TILE_SIZE,
                ySortable.getHeight() * Settings.SCALED_TILE_SIZE
        );
    }

}
