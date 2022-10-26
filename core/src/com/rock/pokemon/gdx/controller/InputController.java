package com.rock.pokemon.gdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.model.map.TileMap;
import com.rock.pokemon.gdx.model.people.Person;

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

    //控制器操控的人(一般情况下就是主角)
    private Person person;

    //当前地图网格
    private TileMap tileMap;

    /**
     * 输入状态,状态可以全部存在,比如按上的同时也可以按下,但是怎么处理就我们说的算了
     */

    //代表当前方向的输入状态
    private boolean up, down, left, right;

    //每种方向的按键持续时间
    private float upTime, downTime, leftTime, rightTime;

    //方向按键持续时间不移动,方向键按住的时间小于等于该时间,则换方向不移动,大于该时间,开始移动判定
    private float notMoveTime = 0.15F;

    /**
     * 初始化,指定要监听的人物
     *
     * @param person  人物
     * @param tileMap 地图网格map
     */
    public InputController(Person person, TileMap tileMap) {
        //记录游戏对象及地图网格
        this.person = person;
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
                //方向状态开启
                up = true;
                //重置其持续时间
                this.upTime = 0F;
                break;
            case Input.Keys.DOWN:
                //方向状态开启
                down = true;
                //重置其持续时间
                this.downTime = 0F;
                break;
            case Input.Keys.LEFT:
                //方向状态开启
                left = true;
                //重置其持续时间
                this.leftTime = 0F;
                break;
            case Input.Keys.RIGHT:
                //方向状态开启
                right = true;
                //重置其持续时间
                this.rightTime = 0F;
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
                //如果按键持续时间特别短
                if (upTime <= notMoveTime) {
                    //单纯的脸换个方向
                    this.person.changeDir(DirectionEnum.NORTH);
                }
                //关闭按键状态
                up = false;
                //重置持续时间
                upTime = 0;
                break;
            case Input.Keys.DOWN:
                //如果按键持续时间特别短
                if (downTime <= notMoveTime) {
                    //单纯的脸换个方向
                    this.person.changeDir(DirectionEnum.SOUTH);
                }
                //关闭按键状态
                down = false;
                //重置持续时间
                downTime = 0;
                break;
            case Input.Keys.LEFT:
                //如果按键持续时间特别短
                if (leftTime <= notMoveTime) {
                    //单纯的脸换个方向
                    this.person.changeDir(DirectionEnum.WEST);
                }
                //关闭按键状态
                left = false;
                //重置持续时间
                leftTime = 0;
                break;
            case Input.Keys.RIGHT:
                //如果按键持续时间特别短
                if (rightTime <= notMoveTime) {
                    //单纯的脸换个方向
                    this.person.changeDir(DirectionEnum.EAST);
                }
                //关闭按键状态
                right = false;
                //重置持续时间
                rightTime = 0;
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
            //叠加其持续时间
            upTime += delta;
            //如果持续按
            if (upTime > notMoveTime) {
                //尝试移动判定
                this.person.move(tileMap, DirectionEnum.NORTH);
            }
        } else if (down) {
            //叠加其持续时间
            downTime += delta;
            //如果持续按
            if (downTime > notMoveTime) {
                //尝试移动判定
                this.person.move(tileMap, DirectionEnum.SOUTH);
            }
        } else if (left) {
            //叠加其持续时间
            leftTime += delta;
            //如果持续按
            if (leftTime > notMoveTime) {
                //尝试移动判定
                this.person.move(tileMap, DirectionEnum.WEST);
            }
        } else if (right) {
            //叠加其持续时间
            rightTime += delta;
            //如果持续按
            if (rightTime > notMoveTime) {
                //尝试移动判定
                this.person.move(tileMap, DirectionEnum.EAST);
            }
        }

    }

}
