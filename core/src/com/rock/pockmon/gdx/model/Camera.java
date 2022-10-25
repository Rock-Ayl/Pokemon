package com.rock.pockmon.gdx.model;

/**
 * 一个自定义的相机
 */
public class Camera {

    //相机坐标
    private float cameraX = 0F;
    private float cameraY = 0F;

    /**
     * 更新方法
     *
     * @param newCamX 新的坐标x
     * @param newCamY 新的坐标y
     */
    public void update(float newCamX, float newCamY) {
        this.cameraX = newCamX;
        this.cameraY = newCamY;
    }

    /**
     * 以下是 get set 方法
     *
     * @return
     */

    public float getCameraX() {
        return cameraX;
    }

    public float getCameraY() {
        return cameraY;
    }

}
