package com.rock.pokemon.gdx.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 人物、事物通用的接口,用来统一渲染,及根据Y轴进行层级关系的排序
 * 1.地图块也实现了它,但它就不用了排序层级了,它默认最下层
 * 2.事物如果是可以行走的,那么也不用排序,默认在地图块上,其他下面
 * 3.剩下的人物、不可行走的事物,需要排序渲染
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
     * 获取图片
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
