package com.rock.pokemon.gdx.screen.renderer;

import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.model.YSortable;
import com.rock.pokemon.gdx.model.map.Tile;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.map.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 世界渲染器,用它统一渲染世界的每个地图块、人物、事物
 *
 * @Author ayl
 * @Date 2022-10-23
 */
public class WorldRenderer {

    //一个完整的世界实体
    private World world;

    //渲染器图层列表,这个将决定人物、事物的渲染
    private List<YSortable> sortList;

    /**
     * 初始化方法,要渲染,得有世界和图片资源吧.
     *
     * @param world 世界实体
     */
    public WorldRenderer(World world) {
        this.world = world;
        this.sortList = new ArrayList<>();
    }

    /**
     * 使用渲染器和相机,计算世界坐标,然后计算人物、地图块、事物的图层,最后渲染
     *
     * @param pokemon 游戏对象
     */
    public void render(Pokemon pokemon) {

        //开始时清理
        this.sortList.clear();

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
                draw(pokemon, tile);
            }
        }

        /**
         * 再渲染可以行走的事物,并收集不可行走的事物进入sort列表,人物也要进入排序列表
         */

        //渲染事物列表
        for (WorldObject worldObject : this.world.getWorldObjectList()) {
            //如果可以行走,优先级仅仅比地图块高一级
            if (worldObject.isWalkable()) {
                //渲染
                draw(pokemon, worldObject);
                //本轮过
                continue;
            }
            //不可行走的记录到列表
            this.sortList.add(worldObject);
        }

        //sort列表加入所有的人物
        this.sortList.addAll(this.world.getPersonList());

        /**
         * 按照Y轴排序
         */

        this.sortList.sort(new YSortComparator());

        /**
         * 最后统一渲染人物、不可行走的事物
         */

        //统一渲染人物、事物
        for (YSortable ySortable : this.sortList) {
            //渲染
            draw(pokemon, ySortable);
        }

        //结束时清理
        this.sortList.clear();

    }

    /**
     * 根据实现了 {@link YSortable} 的类,统一渲染世界的每一个图片
     *
     * @param pokemon   游戏对象
     * @param ySortable 实现的class,可以是人物、事物、地图块
     */
    private void draw(Pokemon pokemon, YSortable ySortable) {
        //根据世界起点,渲染
        pokemon.getBatch().draw(
                //当前类的当前帧的图片
                ySortable.getSprite(),
                //当前坐标 * 当前网格倍率
                ySortable.getWorldX() * Settings.SCALE_TILE_SIZE,
                ySortable.getWorldY() * Settings.SCALE_TILE_SIZE,
                //人物的宽高 * 当前网格倍率
                ySortable.getWidth() * Settings.SCALE_TILE_SIZE,
                ySortable.getHeight() * Settings.SCALE_TILE_SIZE
        );
    }

}
