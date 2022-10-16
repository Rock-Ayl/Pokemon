package com.rock.pockmon.gdx.util;

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
        batch.draw(
                //图片
                person.getCurrentImage(),
                //当前坐标*网格倍率
                person.x * Settings.SCALED_TILE_SIZE,
                person.y * Settings.SCALED_TILE_SIZE,
                //使用人物的宽高*网格倍率
                person.width * Settings.SCALED_TILE_SIZE,
                person.height * Settings.SCALED_TILE_SIZE
        );
    }

    /**
     * 渲染一个地图块
     *
     * @param batch 渲染器
     * @param tile  单个地图块实体
     */
    public static void drawTile(SpriteBatch batch, Tile tile) {
        batch.draw(
                //图片
                tile.getTileEnum().getImage(),
                //当前坐标*网格倍率
                tile.x * Settings.SCALED_TILE_SIZE,
                tile.y * Settings.SCALED_TILE_SIZE,
                //使用地图块的宽高*网格倍率
                tile.width * Settings.SCALED_TILE_SIZE,
                tile.height * Settings.SCALED_TILE_SIZE
        );
    }

}
