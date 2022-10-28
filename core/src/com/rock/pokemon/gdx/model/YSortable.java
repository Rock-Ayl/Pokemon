package com.rock.pokemon.gdx.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 人物、事物通用的接口,用来统一渲染层级关系
 * 地图块就不用了,它默认最下层
 *
 * @Author ayl
 * @Date 2022-10-23
 */
public interface YSortable {

    /**
     * 真实世界坐标x
     *
     * @return
     */
    float getWorldX();

    /**
     * 真实世界坐标y
     *
     * @return
     */
    float getWorldY();

    /**
     * 图片本身
     *
     * @return
     */
    TextureRegion getSprite();

    /**
     * 宽度
     *
     * @return
     */
    float getWidth();

    /**
     * 高度
     *
     * @return
     */
    float getHeight();

}
