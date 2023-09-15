package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.rock.pokemon.gdx.model.YSortable;
import com.rock.pokemon.gdx.model.mapConfig.WorldObjectMapNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 事物实体 可以是一个树、一朵花、一个牌子等等,在地图网格上面,可动可静,可以是图片也可以是动画
 *
 * @Author ayl
 * @Date 2022-10-21
 */
public class WorldObject implements YSortable {

    //当前事物的坐标
    private int x;
    private int y;

    //当前事务占用地图网格的列表,可以说是体积,1*1=1个点,2*5=10个点
    private List<GridPoint2> gridPointList;

    //该事物的图片帧
    private TextureRegion texture;

    //一个事物实体的宽高,这个和人物、地图块不同,有的事物可以1*1(草),有的则是1*1.5(门),甚至可以是房子5*6等等
    private float width;
    private float height;

    //是否可以行走(草可以走过去,树不行)
    private boolean walkable;

    /**
     * 通过配置的初始化方式
     *
     * @param assetManager 资源管理器
     * @param mapNode      事物配置类
     * @param x            坐标x
     * @param y            坐标y
     */
    public WorldObject(AssetManager assetManager, WorldObjectMapNode mapNode, int x, int y) {

        //坐标
        this.x = x;
        this.y = y;
        //读取图片资源
        this.texture = assetManager.get(mapNode.getFilePath(), TextureAtlas.class).findRegion(mapNode.getRegionName());
        //宽高
        this.width = mapNode.getWidth();
        this.height = mapNode.getHeight();

        //todo 是否可以行走
        this.walkable = mapNode.getTileDefaultWalkable();

        //初始化
        this.gridPointList = new ArrayList<>();
        //循环宽
        for (int i = 0; i < this.width; i++) {
            //循环高
            for (int j = 0; j < this.height; j++) {
                //组装占用的地图网格
                this.gridPointList.add(new GridPoint2(i, j));
            }
        }

        //todo
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

    public List<GridPoint2> getGridPointList() {
        return gridPointList;
    }

    public void setGridPointList(List<GridPoint2> gridPointList) {
        this.gridPointList = gridPointList;
    }

}
