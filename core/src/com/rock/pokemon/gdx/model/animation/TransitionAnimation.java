package com.rock.pokemon.gdx.model.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.enums.TransitionEnum;

/**
 * todo 渐变效果
 *
 * @Author ayl
 * @Date 2024-4-16
 */
public class TransitionAnimation extends ApplicationAdapter {

    //渲染器
    private SpriteBatch batch;
    //着色器
    private ShaderProgram shader;

    /**
     * 动画参数
     */

    //帧时间
    private float delta = 0F;
    //速度倍率
    private static final float SPEED = 0.5F;

    //渐变基底枚举
    private TransitionEnum transitionEnum;
    //渐变基底图片
    private Texture img;

    //todo delete 测试背景图片
    private Texture backImage;

    /**
     * 初始化方法
     *
     * @param transitionEnum 渐变枚举(指定动画)
     */
    public TransitionAnimation(TransitionEnum transitionEnum) {
        this.transitionEnum = transitionEnum;
    }

    @Override
    public void create() {

        //渲染器
        this.batch = new SpriteBatch();
        //初始化着色器
        this.shader = new ShaderProgram(
                Gdx.files.internal(FilePaths.TRANSITION_GLSL_VERTEX),
                Gdx.files.internal(FilePaths.TRANSITION_GLSL_FRAGMENT)
        );

        //指定渐变基底图片
        this.img = new Texture(String.format(FilePaths.TRANSITION_ANIMATION_IMAGE_PATH, transitionEnum.getNumber()));
        //todo delete 背景图片,随意用一张图
        this.backImage = new Texture(String.format(FilePaths.TRANSITION_ANIMATION_IMAGE_PATH, 11));

    }

    /**
     * 过程
     */
    @Override
    public void render() {

        //清除屏幕
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //计算速度倍率,叠加时间
        this.delta += Gdx.graphics.getDeltaTime() * SPEED;

        /**
         * 测试背景
         */

        //先不使用shader
        this.batch.setShader(null);
        this.batch.begin();
        //测试背景
        this.batch.draw(this.backImage, 0, 0, Settings.WIDTH, Settings.HEIGHT);
        this.batch.end();

        /**
         * 渐变
         */

        this.shader.bind();
        //循环动画的时间参数，使动画连续播放,假设动画循环周期为1秒
        this.shader.setUniformf("u_time", (this.delta % 1F));

        //使用shader
        this.batch.setShader(this.shader);
        this.batch.begin();
        this.batch.draw(this.img, 0, 0, Settings.WIDTH, Settings.HEIGHT);
        this.batch.end();

    }

    /**
     * 销毁实现
     */
    @Override
    public void dispose() {
        this.batch.dispose();
        this.img.dispose();
        this.backImage.dispose();
        this.shader.dispose();
    }

}