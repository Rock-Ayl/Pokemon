package com.rock.pokemon.gdx.model.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import lombok.Getter;

/**
 * todo 场景切换 渐变效果
 */
public class TransitionSwitchAnimation {

    //渲染器
    private ShapeRenderer shapeRenderer;

    //整个淡入淡出过程的持续时间(秒)
    private static final float DURATION = 2F;

    //动画当前持续时间
    private float animTime;

    //当前透明度
    private float alpha;

    //动画状态
    private StatusEnum status;

    /**
     * 状态枚举
     */
    @Getter
    private enum StatusEnum {

        //等待
        WAITING,

        //渐渐变暗
        DOING_DARK,

        //黑暗中
        IN_DARK,

        //渐渐变亮
        DOING_LIGHT,

        ;

    }

    /**
     * 初始化
     */
    public TransitionSwitchAnimation() {
        this.shapeRenderer = new ShapeRenderer();
        this.status = StatusEnum.WAITING;
    }

    public void update(float delta) {

        /**
         * 计算淡入淡出
         */

        //根据状态处理
        switch (this.status) {
            //渐渐变暗
            case DOING_DARK:
                //叠加时间
                animTime += delta;
                //计算透明度,最大为1
                alpha = Math.min(animTime / DURATION, 1F);
                //如果透明度达到极限
                if (alpha == 1F) {
                    //修改状态
                    this.status = StatusEnum.IN_DARK;
                }
                break;
            //渐渐变亮
            case DOING_LIGHT:
                //反向叠加时间
                animTime -= delta;
                //计算透明度,最小为0
                alpha = Math.max(animTime / DURATION, 0F);
                //如果透明度达到极限
                if (alpha == 0F) {
                    //修改状态
                    this.status = StatusEnum.WAITING;
                }
                break;
            //黑暗中
            case IN_DARK:
                //不需要跳过update,需要继续渲染
                break;
            //默认
            default:
                //跳过update
                return;
        }

        /**
         * 渲染
         */

        Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, alpha));
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(Gdx.gl.GL_BLEND);

    }

    /**
     * 进入黑屏
     */
    public void startFadingIn() {
        //如果是等待中
        if (this.status == StatusEnum.WAITING) {
            //修改状态
            this.status = StatusEnum.DOING_DARK;
            //重置动画时间
            this.animTime = 0;
        }
    }

    /**
     * 退出黑屏
     */
    public void startFadingOut() {
        //如果是等待中
        if (this.status == StatusEnum.IN_DARK) {
            //修改状态
            this.status = StatusEnum.DOING_LIGHT;
        }
    }

    /**
     * 销毁实现
     */
    public void dispose() {
        this.shapeRenderer.dispose();
    }

}