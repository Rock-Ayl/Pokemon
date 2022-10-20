package com.rock.pockmon.gdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pockmon.gdx.PockMon;
import com.rock.pockmon.gdx.enums.DirectionEnum;
import com.rock.pockmon.gdx.model.map.TileMap;

/**
 * 输入控制器,根据输入处理移动等逻辑
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class InputController extends InputAdapter {

    /**
     * 基础
     */

    //游戏对象
    private final PockMon game;
    //当前地图网格
    private TileMap tileMap;

    /**
     * 输入状态,状态可以全部存在,比如按上的同时也可以按下,但是怎么处理就我们说的算了
     */

    //代表当前方向的输入状态
    private boolean up, down, left, right;

    /**
     * 初始化,指定要监听的的游戏
     *
     * @param game    游戏对象
     * @param tileMap 地图网格map
     */
    public InputController(PockMon game, TileMap tileMap) {
        //记录游戏对象及地图网格
        this.game = game;
        this.tileMap = tileMap;
    }

    /**
     * 按键按下时调用
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        //根据输入决定方向状态
        switch (keycode) {
            case Input.Keys.UP:
                up = true;
                break;
            case Input.Keys.DOWN:
                down = true;
                break;
            case Input.Keys.LEFT:
                left = true;
                break;
            case Input.Keys.RIGHT:
                right = true;
                break;
        }
        //默认返回
        return false;
    }

    /**
     * 按键被释放时调用
     *
     * @param keycode 输入
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        //根据释放按键释放方向状态
        switch (keycode) {
            case Input.Keys.UP:
                up = false;
                break;
            case Input.Keys.DOWN:
                down = false;
                break;
            case Input.Keys.LEFT:
                left = false;
                break;
            case Input.Keys.RIGHT:
                right = false;
                break;
        }
        //默认返回
        return false;
    }

    /**
     * 每一帧都进行更新
     *
     * @param delta 帧时间,可能会用到,也可能不会,看具体逻辑,传入准没错
     */
    public void update(float delta) {

        /**
         * 判定主角的移动
         * 主角移动判定,同一次移动只能一个方向,但输入状态可以有多个,按照绿宝石的手感判定,上优先级最高,下其次,左第三,右的判定最低
         */

        if (up) {
            //尝试移动判定
            this.game.getAdventurer().move(tileMap, DirectionEnum.NORTH);
        } else if (down) {
            //尝试移动判定
            this.game.getAdventurer().move(tileMap, DirectionEnum.SOUTH);
        } else if (left) {
            //尝试移动判定
            this.game.getAdventurer().move(tileMap, DirectionEnum.WEST);
        } else if (right) {
            //尝试移动判定
            this.game.getAdventurer().move(tileMap, DirectionEnum.EAST);
        }

    }

}
