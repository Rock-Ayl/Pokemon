package com.rock.pokemon.gdx.model.controller;

/**
 * @Author ayl
 * @Date 2025-11-15
 */

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.model.map.Person;

/**
 * 人物输入控制器：负责监听键盘,转发给命令处理器
 */
public class PlayerController extends InputAdapter {

    //游戏对象
    private final PokemonGame pokemonGame;

    //玩家命令处理器
    private final PlayerCommandHandler commandHandler;

    /**
     * 初始化
     *
     * @param pokemonGame 游戏实体
     * @param person      角色
     */
    public PlayerController(PokemonGame pokemonGame, Person person) {
        this.pokemonGame = pokemonGame;
        this.commandHandler = new PlayerCommandHandler(person);
    }

    @Override
    public boolean keyDown(int keycode) {
        //先记录输入
        this.commandHandler.onKeyDown(keycode);
        //检查/聊天
        if (keycode == Input.Keys.Z) {
            //检查/对话
            this.pokemonGame.getWorldScreen().getBoxExecutor().checkAndTalk();
        }
        //返回
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        //实现
        this.commandHandler.onKeyUp(keycode);
        //返回
        return true;
    }

    /**
     * 每帧更新人物移动状态
     */
    public void update(float delta) {
        this.commandHandler.update(delta);
    }

}