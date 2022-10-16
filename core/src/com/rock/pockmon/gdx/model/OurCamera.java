package com.rock.pockmon.gdx.model;

/**
 * 我的相机对象,用来模拟自己的相机
 *
 * @Author ayl
 * @Date 2022-10-13
 */
public class OurCamera {

    //相机坐标
    private float x;
    private float y;

    /**
     * 更新相机坐标(几乎每帧都需要做),并返回本身
     *
     * @param x 新坐标x
     * @param y 新坐标y
     */
    public OurCamera update(float x, float y) {
        this.x = x;
        this.y = y;
        //返回本身
        return this;
    }

    /**
     * 以下是get set 方法
     *
     * @return
     */

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
