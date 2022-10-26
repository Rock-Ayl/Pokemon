package com.rock.pokemon.gdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.screen.town.LittleRoot;

/**
 * 主菜单界面
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class MainMenu implements Screen {

    //游戏对象
    private final Pokemon game;

    /**
     * 初始化主菜单
     *
     * @param pokemon 游戏对象
     */
    public MainMenu(final Pokemon pokemon) {
        //记录游戏对象
        this.game = pokemon;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //黑幕
        ScreenUtils.clear(Color.BLACK);

        //简单的假主菜单
        this.game.getBatch().begin();
        this.game.getFont().draw(this.game.getBatch(), "New Game \nSetting", Settings.WINDOW_WIDTH / 2, Settings.WINDOW_HEIGHT / 2);
        this.game.getBatch().end();

        //如果点击屏幕 或 按回车
        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            //进入未白镇
            this.game.setScreen(new LittleRoot(this.game));
            //销毁当前资源
            this.dispose();
        }

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
