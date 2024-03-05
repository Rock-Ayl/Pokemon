package com.rock.pokemon.gdx.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.screen.town.LittleRoot;

/**
 * 主菜单界面
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class MainMenu implements Screen {

    //游戏对象
    private Pokemon game;

    /**
     * 初始化/进入
     *
     * @param pokemon 游戏对象
     */
    public MainMenu(Pokemon pokemon) {
        //记录游戏对象
        this.game = pokemon;
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
        this.game.getBatch().begin();
        this.game.getFont().draw(this.game.getBatch(), "New Game \nSetting", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        this.game.getBatch().end();

        /**
         * 控制逻辑
         */

        //如果点击屏幕 或 按回车
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            //进入 未白镇 指定位置
            this.game.setScreen(new LittleRoot(this.game, FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT, 19, 18));
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
