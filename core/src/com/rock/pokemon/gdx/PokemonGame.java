package com.rock.pokemon.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.rock.pokemon.gdx.model.manager.GameContext;
import com.rock.pokemon.gdx.model.screen.MainMenuScreen;
import com.rock.pokemon.gdx.model.screen.WorldScreen;
import lombok.Getter;

/**
 * 游戏入口
 *
 * @Author ayl
 * @Date 2022-10-15
 */
@Getter
public class PokemonGame extends Game {

    /**
     * 游戏上下文：通用资源
     */

    //游戏上下文
    private GameContext gameContext;

    /**
     * 初始化
     */
    public void create() {

        /**
         * 初始化 游戏上下文：通用资源
         */

        //初始化实体
        this.gameContext = new GameContext();
        //注册所有资源
        this.gameContext.init(this);

        /**
         * 场景
         */

        //进入主菜单
        this.setScreen(new MainMenuScreen(this));

    }

    /**
     * 获取当前 通用世界对象
     *
     * @return
     */
    public WorldScreen getWorldScreen() {
        //获取当前游戏的屏幕
        Screen screen = this.getScreen();
        //如果不是目标对象
        if (screen instanceof WorldScreen == false) {
            //过
            return null;
        }
        //强转并返回
        return (WorldScreen) screen;
    }

    /**
     * 帧渲染
     */
    public void render() {
        //官网要求
        super.render();
    }

    /**
     * 销毁资源
     */
    public void dispose() {
        this.gameContext.dispose();
    }

}
