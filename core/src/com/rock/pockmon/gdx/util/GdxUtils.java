package com.rock.pockmon.gdx.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pockmon.gdx.common.Settings;
import com.rock.pockmon.gdx.model.map.Tile;
import com.rock.pockmon.gdx.model.map.TileMap;
import com.rock.pockmon.gdx.model.people.Person;

/**
 * 自己封装的Gdx工具类
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class GdxUtils {

    /**
     * 渲染一个人
     *
     * @param batch       渲染器
     * @param person      人物实体
     * @param worldStartX 世界起点x,没有传0
     * @param worldStartY 世界起点y,没有传0
     */
    public static void drawPerson(SpriteBatch batch, Person person, float worldStartX, float worldStartY) {
        //实现
        draw(batch, person.getSprite(), person.getWorldX(), person.getWorldY(), person.getWidth(), person.getHeight(), worldStartX, worldStartY);
    }

    /**
     * 渲染一个地图块
     *
     * @param batch       渲染器
     * @param tile        单个地图块实体
     * @param worldStartX 世界起点x,没有传0
     * @param worldStartY 世界起点y,没有传0
     */
    public static void drawTile(SpriteBatch batch, Tile tile, float worldStartX, float worldStartY) {
        //实现
        draw(batch, tile.getSprite(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight(), worldStartX, worldStartY);
    }

    /**
     * 渲染一个地图网格
     *
     * @param batch       渲染器
     * @param tileMap     渲染一个地图网格
     * @param worldStartX 世界起点x
     * @param worldStartY 世界起点y
     */
    public static void drawTileMap(SpriteBatch batch, TileMap tileMap, float worldStartX, float worldStartY) {
        //循环1
        for (int x = 0; x < tileMap.getWidth(); x++) {
            //循环2
            for (int y = 0; y < tileMap.getHeight(); y++) {
                //当前地图块
                Tile tile = tileMap.getMap()[x][y];
                //渲染该地图块
                GdxUtils.drawTile(batch, tile, worldStartX, worldStartY);
            }
        }
    }

    /**
     * 根据信息统一渲染图片底层逻辑
     *
     * @param image       图片对象
     * @param x           坐标
     * @param y           坐标
     * @param width       宽
     * @param height      搞
     * @param worldStartX 世界起点x
     * @param worldStartY 世界起点y
     */
    private static void draw(SpriteBatch batch, TextureRegion image, float x, float y, float width, float height, float worldStartX, float worldStartY) {
        //获取网格倍率
        float scale = Settings.SCALED_TILE_SIZE;
        //实现
        batch.draw(
                //图片
                image,
                //真实坐标 * 网格倍率 + 当前坐标 * 网格倍率
                worldStartX * scale + x * scale,
                worldStartY * scale + y * scale,
                //使用地图块的宽高 * 网格倍率
                width * scale,
                height * scale
        );
    }

}
