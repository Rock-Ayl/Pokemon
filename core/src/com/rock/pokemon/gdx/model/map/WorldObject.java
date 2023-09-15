package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pokemon.gdx.model.YSortable;

/**
 * 事物实体 可以是一个树、一朵花、一个牌子等等,在地图网格上面,可动可静,可以是图片也可以是动画
 *
 * @Author ayl
 * @Date 2022-10-21
 */
public class WorldObject implements YSortable {

    //当前事物的坐标,由于事物不能移动,所以x,y也是其世界坐标
    private int x;
    private int y;

    //该事物的图片帧
    private TextureRegion texture;

    //一个事物实体的宽高,这个和人物、地图块不同,有的事物可以1*1(草),有的则是1*1.5(门),甚至可以是房子5*6等等
    private float width;
    private float height;

    //是否可以行走(草可以走过去,树不行)
    private boolean walkable;

    /**
     * 基本的初始化方法
     *
     * @param x        坐标x
     * @param y        坐标y
     * @param texture  图片
     * @param width    宽
     * @param height   搞
     * @param walkable 是否可以行走
     */
    public WorldObject(int x, int y, TextureRegion texture, float width, float height, boolean walkable) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.walkable = walkable;
    }

    /**
     * 每帧更新
     *
     * @param delta 每帧时间
     */
    public void update(float delta) {
        //todo
    }

    /**
     * 返回动画帧
     *
     * @return
     */
    public TextureRegion getSprite() {
        return texture;
    }

    /**
     * 以下是 get set 方法
     */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getWorldX() {
        return x;
    }

    public float getWorldY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean isWalkable() {
        return walkable;
    }

}
