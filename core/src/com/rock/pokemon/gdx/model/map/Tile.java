package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pokemon.gdx.enums.TerrainEnum;
import com.rock.pokemon.gdx.model.people.Person;

/**
 * 单个地图块
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class Tile {

    //地形枚举
    private TerrainEnum terrainEnum;

    //当前地图块在地图网格的坐标(放弃用Rectangle是因为Rectangle是float类型的),有余地图块不能移动,所以它们也是世界坐标
    private int x;
    private int y;

    //地图块宽高,绿宝石中,所有都是1,之所以地图多0.001,是因为在极少数情况下,计算后的地图渲染会出现[地图裂缝]的问题,加0.001比例宽高,基本算是解决问题了
    private float width = 1.001F;
    private float height = 1.001F;

    //一个地图块上,最多有一个事物
    private WorldObject worldObject;

    //一个地图块上,同时最多有一个人
    private Person person;

    /**
     * 初始化地图块
     *
     * @param terrainEnum 地形枚举
     */
    public Tile(int x, int y, TerrainEnum terrainEnum) {

        //坐标(视为在地图网格中的)
        this.x = x;
        this.y = y;

        //枚举
        this.terrainEnum = terrainEnum;

    }

    /**
     * 获取当前地图块图片
     *
     * @param assetManager 资源管理器
     * @return
     */
    public TextureRegion getSprite(AssetManager assetManager) {
        //返回
        return this.terrainEnum.getImage(assetManager);
    }

    /**
     * Get Set 方法
     *
     * @return
     */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWorldX() {
        return x;
    }

    public int getWorldY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public WorldObject getWorldObject() {
        return worldObject;
    }

    public void setWorldObject(WorldObject worldObject) {
        this.worldObject = worldObject;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
