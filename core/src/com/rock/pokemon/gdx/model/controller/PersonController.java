package com.rock.pokemon.gdx.model.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.map.Person;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * 人物输入控制器,根据输入处理移动等逻辑
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class PersonController extends InputAdapter {

    /**
     * 基础
     */

    //游戏对象
    private final PokemonGame pokemonGame;

    //控制器操控的人(一般情况下就是主角)
    private Person person;

    /**
     * 输入-按键状态
     */

    //输入状态,状态可以全部存在,比如按上的同时也可以按下,但是怎么处理就我们说的算了(目前300够用了)
    private boolean[] buttonPressedArray = new boolean[300];

    //输入状态的持续时间(目前300够用了)
    private float[] buttonTimeArr = new float[300];

    /**
     * 输入-方向相关
     */

    //方向按键持续时间不移动,方向键按住的时间小于等于该时间,则换方向不移动,大于该时间,开始移动判定
    private static final float NOT_MOVE_TIME = 0.15F;

    //方向键编码列表初始化,判定也按照这个顺序来,主角移动判定,同一次移动只能一个方向,但输入状态可以有多个,按照绿宝石的手感判定,上优先级最高,下其次,左第三,右的判定最低
    private static final LinkedHashSet<Integer> DIR_INPUT_KEY_LINKED_SET = new LinkedHashSet<>(Arrays.asList(
            Input.Keys.UP,
            Input.Keys.DOWN,
            Input.Keys.LEFT,
            Input.Keys.RIGHT
    ));

    /**
     * 输入-其他控制
     */

    //跑步
    private static final Integer RUN_INPUT_KEY = Input.Keys.X;

    //检查/聊天
    private static final Integer CHECK_INPUT_KEY = Input.Keys.Z;

    /**
     * 初始化,指定要监听的人物
     *
     * @param pokemonGame 游戏对象
     */
    public PersonController(PokemonGame pokemonGame, Person person) {
        //记录游戏、人物
        this.pokemonGame = pokemonGame;
        this.person = person;
    }

    /**
     * 按键按下时调用
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        //覆盖键位状态
        this.buttonPressedArray[keycode] = true;
        //覆盖按下时间,从0开始计算
        this.buttonTimeArr[keycode] = 0;

        /**
         * 触发事件判定
         */

        //如果是检查/聊天
        if (CHECK_INPUT_KEY == keycode) {
            //尝试检查/聊天
            this.pokemonGame.getWorldScreen().getBoxExecutor().checkAndTalk();
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
        //关闭按键状态
        this.buttonPressedArray[keycode] = false;
        //重置持续时间
        this.buttonTimeArr[keycode] = 0;
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
         * 移动判定
         */

        //是否行走,默认false
        boolean walk = false;
        //循环方向键判定
        for (Integer dirInputKey : DIR_INPUT_KEY_LINKED_SET) {

            /**
             * 移动前的判断
             */

            //如果该方向按键没有被按着
            if (this.buttonPressedArray[dirInputKey] == false) {
                //本轮过
                continue;
            }

            //从这里开始计算行走了
            walk = true;

            //对应方向
            DirectionEnum directionEnum = DirectionEnum.parseByKeycode(dirInputKey);

            //无论如何,先尝试让脸换方向
            this.person.changeFacingDir(directionEnum);

            //叠加其持续时间
            this.buttonTimeArr[dirInputKey] += delta;
            //如果按下方向键时间太短
            if (this.buttonTimeArr[dirInputKey] <= NOT_MOVE_TIME) {
                //本轮过
                continue;
            }

            /**
             * 计算移动方式
             */

            //判断是 跑步 or 走路
            WalkEnum walkEnum = this.buttonPressedArray[RUN_INPUT_KEY] ? WalkEnum.RUN : WalkEnum.WALK;

            /**
             * 移动
             */

            //尝试移动判定
            this.person.move(directionEnum, walkEnum);

            //结束方向键判定
            break;
        }
        //如果不再按键走路
        if (walk == false) {
            //尝试停止走路
            this.person.walkStop();
        }

    }

}
