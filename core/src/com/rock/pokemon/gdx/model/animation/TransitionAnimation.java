package com.rock.pokemon.gdx.model.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.enums.TransitionEnum;

/**
 * todo 渐变效果
 *
 * @Author ayl
 * @Date 2024-4-16
 */
public class TransitionAnimation {

    //游戏对象
    private PokemonGame pokemonGame;
    //着色器
    private ShaderProgram shader;

    /**
     * 动画参数
     */

    //动画持续时间
    private float animTime = 0F;
    //动画速度倍率
    private static final float SPEED = 0.5F;
    //渐变基底图片
    private Texture img;

    /**
     * 初始化方法
     *
     * @param pokemonGame    游戏对象
     * @param transitionEnum 渐变枚举(指定动画)
     */
    public TransitionAnimation(PokemonGame pokemonGame, TransitionEnum transitionEnum) {

        //游戏对象
        this.pokemonGame = pokemonGame;
        //初始化着色器
        this.shader = new ShaderProgram(
                Gdx.files.internal(FilePaths.TRANSITION_GLSL_VERTEX),
                Gdx.files.internal(FilePaths.TRANSITION_GLSL_FRAGMENT)
        );
        //指定渐变基底图片
        this.img = new Texture(String.format(FilePaths.TRANSITION_ANIMATION_IMAGE_PATH, transitionEnum.getNumber()));

    }

    /**
     * 过程实现
     *
     * @param delta 帧时间
     * @param x     坐标
     * @param y     坐标
     */
    public void update(float delta, float x, float y) {

        /**
         * 渐变
         */

        //绑定着色器
        this.shader.bind();
        //计算速度倍率,并叠加动画时间
        this.animTime += delta * SPEED;
        //循环动画的时间参数，使动画连续播放,假设动画循环周期为1秒
        this.shader.setUniformf("u_time", (this.animTime % 1F));

        //使用shader
        this.pokemonGame.getBatch().setShader(this.shader);

        //渲染渐变
        this.pokemonGame.getBatch().begin();
        this.pokemonGame.getBatch().draw(this.img, x, y, Settings.WIDTH, Settings.HEIGHT);
        this.pokemonGame.getBatch().end();

        //删除shader
        this.pokemonGame.getBatch().setShader(null);

    }

    /**
     * 销毁实现
     */
    public void dispose() {
        this.img.dispose();
        this.shader.dispose();
    }

}