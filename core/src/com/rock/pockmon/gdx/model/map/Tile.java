package com.rock.pockmon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 单个地图块
 *
 * @Author ayl
 * @Date 2022-10-16
 */
public class Tile {

    //当前地图块在地图网格的坐标(放弃用Rectangle是因为Rectangle是float类型的)
    private int x;
    private int y;

    //地图块宽高,绿宝石中,所有都是1,之所以地图多0.001,是因为在极少数情况下,计算后的地图渲染会出现[地图裂缝]的问题,加0.001比例宽高,基本算是解决问题了
    private float width = 1.001F;
    private float height = 1.001F;

    //对应地图块动画
    private TextureRegion image;

    /**
     * 初始化
     *
     * @param image 动画
     */
    public Tile(int x, int y, TextureRegion image) {

        //坐标(视为在地图网格中的)
        this.x = x;
        this.y = y;

        //记录图片
        this.image = image;

    }

    /**
     * 获取当前地图块图片
     *
     * @return
     */
    public TextureRegion getSprite() {
        //返回
        return this.image;
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

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
