package com.rock.pockmon.gdx.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rock.pockmon.gdx.common.Settings;
import com.rock.pockmon.gdx.model.map.Tile;
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
     * @param batch  渲染器
     * @param person 人物实体
     */
    public static void drawPerson(SpriteBatch batch, Person person) {
        //实现
        draw(batch, person.getCurrentImage(), person.x, person.y, person.width, person.height);
    }

    /**
     * 渲染一个地图块
     *
     * @param batch 渲染器
     * @param tile  单个地图块实体
     */
    public static void drawTile(SpriteBatch batch, Tile tile) {
        //实现
        draw(batch, tile.getTileEnum().getImage(), tile.x, tile.y, tile.width, tile.height);
    }

    /**
     * 根据信息统一渲染图片底层逻辑
     *
     * @param image  图片对象
     * @param x      坐标
     * @param y      坐标
     * @param width  宽
     * @param height 搞
     */
    private static void draw(SpriteBatch batch, Texture image, float x, float y, float width, float height) {
        //获取网格倍率
        float scale = Settings.SCALED_TILE_SIZE;
        //实现
        batch.draw(
                //图片
                image,
                //当前坐标 * 网格倍率
                x * scale,
                y * scale,
                //使用地图块的宽高 * 网格倍率
                width * scale,
                height * scale
        );
    }

}
