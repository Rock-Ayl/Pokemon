package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Getter;
import lombok.Setter;

/**
 * 单个地图块
 *
 * @Author ayl
 * @Date 2022-10-16
 */
@Getter
@Setter
public class Tile implements YSortable {

    //当前地图块图片
    private TextureRegion image;

    //当前地图块在地图网格的坐标(放弃用Rectangle是因为Rectangle是float类型的),有余地图块不能移动,所以它们也是世界坐标
    private int x;
    private int y;

    //地图块宽高,绿宝石中,所有都是1,之所以地图多0.001,是因为在极少数情况下,计算后的地图渲染会出现[地图裂缝]的问题,加0.001比例宽高,基本算是解决问题了
    private final float width = 1.001F;
    private final float height = 1.001F;

    //一个地图块上,最多有一个事物,但是多个地图上,可以有同一个事物(比如房子5*5,25个地图块上有同一个事物)
    private WorldObject worldObject;

    //一个地图块上,同时最多有一个人
    private Person person;

    /**
     * 初始化地图块
     *
     * @param x 坐标
     * @param y 坐标
     */
    public Tile(int x, int y) {
        //坐标
        this.x = x;
        this.y = y;
    }

    /**
     * 如果是同一个人,删除之
     *
     * @param person
     */
    public void removePerson(Person person) {
        //如果是同一个对象
        if (this.person == person) {
            //删除之
            this.person = null;
        }
    }

    /**
     * 获取图片帧
     *
     * @return
     */
    @Override
    public TextureRegion getSprite() {
        return this.image;
    }

    public float getWorldX() {
        return this.x;
    }

    public float getWorldY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

}