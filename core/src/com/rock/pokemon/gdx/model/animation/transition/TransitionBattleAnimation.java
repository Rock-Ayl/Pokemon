package com.rock.pokemon.gdx.model.animation.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.enums.TransitionEnum;
import lombok.Getter;

/**
 * 战斗 渐变效果
 *
 * @Author ayl
 * @Date 2024-4-16
 */
public class TransitionBattleAnimation {

    //游戏对象
    private PokemonGame pokemonGame;
    //着色器
    private ShaderProgram shader;

    /**
     * 动画参数
     */

    //动画持续时间
    private float animTime = 0F;
    //动画结束时间
    private static final float ANIM_TIME_FINISH = 1F;
    //动画速度倍率
    private static final float SPEED = 0.5F;
    //渐变基底图片
    private Texture img;
    //动画状态
    private StatusEnum status;

    /**
     * 状态枚举
     */
    @Getter
    private enum StatusEnum {

        //等待
        WAITING,
        //动画进行中
        DOING,

        ;

    }

    /**
     * 初始化方法
     *
     * @param pokemonGame 游戏对象
     */
    public TransitionBattleAnimation(PokemonGame pokemonGame) {

        //游戏对象
        this.pokemonGame = pokemonGame;
        //初始化着色器
        this.shader = new ShaderProgram(
                Gdx.files.internal(FilePaths.TRANSITION_GLSL_VERTEX),
                Gdx.files.internal(FilePaths.TRANSITION_GLSL_FRAGMENT)
        );
        //默认状态,等待
        this.status = StatusEnum.WAITING;

    }

    /**
     * 开始动画
     *
     * @param transitionEnum 渐变枚举(指定动画)
     */
    public void start(TransitionEnum transitionEnum) {
        //如果不是等待状态
        if (this.status != StatusEnum.WAITING) {
            //过
            return;
        }
        //开启状态
        this.status = StatusEnum.DOING;
        this.animTime = 0F;
        //指定渐变基底图片
        this.img = new Texture(String.format(FilePaths.TRANSITION_ANIMATION_IMAGE_PATH, transitionEnum.getNumber()));
    }

    /**
     * 过程实现
     *
     * @param delta  帧时间
     * @param camera 相机
     */
    public void update(float delta, Camera camera) {

        /**
         * 判定
         */

        //如果不是进行中
        if (this.status != StatusEnum.DOING) {
            //过
            return;
        }

        /**
         * 渐变
         */

        //绑定着色器
        this.shader.bind();
        //计算速度倍率,并叠加动画时间
        this.animTime += delta * SPEED;
        //循环动画的时间参数,使动画连续播放,假设动画循环周期为1秒
        this.shader.setUniformf("u_time", this.animTime % 1F);

        //使用shader
        this.pokemonGame.getBatch().setShader(this.shader);

        //渲染渐变
        this.pokemonGame.getBatch().begin();
        this.pokemonGame.getBatch().draw(
                this.img,
                //计算动画位置
                camera.position.x - camera.viewportWidth / 2,
                camera.position.y - camera.viewportHeight / 2,
                Settings.WIDTH,
                Settings.HEIGHT
        );
        this.pokemonGame.getBatch().end();

        //删除shader
        this.pokemonGame.getBatch().setShader(null);

        /**
         * 更新状态
         */

        //如果动画结束了
        if (this.animTime > ANIM_TIME_FINISH) {
            //重置状态
            this.status = StatusEnum.WAITING;
            this.animTime = 0;
        }

    }

    /**
     * 销毁实现
     */
    public void dispose() {
        this.img.dispose();
        this.shader.dispose();
    }

}