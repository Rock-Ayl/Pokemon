package com.rock.pokemon.gdx.model.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.rock.pokemon.gdx.common.Settings;

/**
 * todo 渐变效果
 *
 * @Author ayl
 * @Date 2024-4-16
 */
public class GradientAnimation extends ApplicationAdapter {

    //渲染器
    private SpriteBatch batch;
    //测试背景
    private Texture backImage;
    //渐变基底图片
    private Texture img;
    //着色器
    private ShaderProgram shader;
    //时间
    private float time = 0.0f;
    //速度倍率
    private static final float SPEED = 0.5F;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.img = new Texture("assets/config/gradient/transition_3.png");
        this.backImage = new Texture("assets/config/gradient/transition_11.png");
        ShaderProgram.pedantic = false;
        this.shader = new ShaderProgram(
                Gdx.files.internal("assets/config/gradient/glsl/vertex.glsl"),
                Gdx.files.internal("assets/config/gradient/glsl/fragment.glsl"));
        if (!this.shader.isCompiled()) {
            System.err.println("Shader compilation failed: " + this.shader.getLog());
        }
    }

    @Override
    public void render() {

        //清除屏幕
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //计算速度倍率,叠加时间
        this.time += Gdx.graphics.getDeltaTime() * SPEED;

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
        this.shader.setUniformf("u_time", (this.time % 1));

        //使用shader
        this.batch.setShader(this.shader);
        this.batch.begin();
        this.batch.draw(this.img, 0, 0, Settings.WIDTH, Settings.HEIGHT);
        this.batch.end();

    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.img.dispose();
        this.backImage.dispose();
        this.shader.dispose();
    }

}