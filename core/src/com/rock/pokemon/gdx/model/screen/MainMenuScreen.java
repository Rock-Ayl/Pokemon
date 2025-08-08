package com.rock.pokemon.gdx.model.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.common.FilePaths;

/**
 * 屏幕 主菜单界面
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class MainMenuScreen implements Screen {

    //游戏对象
    private PokemonGame pokemonGame;

    /**
     * 初始化/进入
     *
     * @param pokemonGame 游戏对象
     */
    public MainMenuScreen(PokemonGame pokemonGame) {
        //记录游戏对象
        this.pokemonGame = pokemonGame;
    }

    /**
     * 渲染
     *
     * @param delta
     */
    @Override
    public void render(float delta) {

        /**
         * 渲染页面
         */

        //黑幕
        ScreenUtils.clear(Color.BLACK);

        //超级简单的主菜单
        this.pokemonGame.getBatch().begin();
        this.pokemonGame.getFont().draw(this.pokemonGame.getBatch(), "New Game \nSetting", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        this.pokemonGame.getBatch().end();

        /**
         * 控制逻辑
         */

        //如果点击屏幕 或 按回车
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            //进入 未白镇 指定位置
            this.pokemonGame.setScreen(new WorldScreen(this.pokemonGame, FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT, 19, 18));
            //销毁当前资源
            this.dispose();
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
