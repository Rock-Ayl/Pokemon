package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rock.pokemon.gdx.model.YSortable;
import com.rock.pokemon.gdx.model.people.Person;

/**
 * 单个地图块
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class Tile implements YSortable {

    //当前地图块图片
    private TextureRegion image;

    //当前地图块在地图网格的坐标(放弃用Rectangle是因为Rectangle是float类型的),有余地图块不能移动,所以它们也是世界坐标
    private int x;
    private int y;

    //地图块宽高,绿宝石中,所有都是1,之所以地图多0.001,是因为在极少数情况下,计算后的地图渲染会出现[地图裂缝]的问题,加0.001比例宽高,基本算是解决问题了
    private static float width = 1.001F;
    private static float height = 1.001F;

    //一个地图块上,最多有一个事物
    private WorldObject worldObject;

    //一个地图块上,同时最多有一个人
    private Person person;

    /**
     * 初始化地图块
     *
     * @param image 图片
     */
    public Tile(int x, int y, TextureRegion image) {
        //坐标
        this.x = x;
        this.y = y;
        //图片
        this.image = image;
    }

    /**
     * 获取动画图片
     *
     * @return
     */
    @Override
    public TextureRegion getSprite() {
        return image;
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
