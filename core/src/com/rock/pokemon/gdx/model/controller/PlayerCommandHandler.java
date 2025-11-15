package com.rock.pokemon.gdx.model.controller;


import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.WalkEnum;
import com.rock.pokemon.gdx.model.map.Person;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * 玩家命令处理器
 *
 * @Author ayl
 * @Date 2025-11-15
 */
public class PlayerCommandHandler {

    //人物
    private final Person person;

    /**
     * 输入 - 按键状态
     * 这里只记录真正关心的 key，不再用 300 大数组
     */

    //方向相关集合
    private static final LinkedHashSet<Integer> DIR_INPUT_KEY_LINKED_SET = new LinkedHashSet<>(Arrays.asList(
            Settings.INPUT_KEY_UP,
            Settings.INPUT_KEY_DOWN,
            Settings.INPUT_KEY_LEFT,
            Settings.INPUT_KEY_RIGHT
    ));

    // 按住不移动的阈值
    private static final float NOT_MOVE_TIME = 0.15F;

    // ---- 状态记录 ----

    /**
     * 输入-按键状态
     */

    //输入状态,状态可以全部存在,比如按上的同时也可以按下,但是怎么处理就我们说的算了(目前300够用了)
    private boolean[] buttonPressedArray = new boolean[300];

    //输入状态的持续时间(目前300够用了)
    private float[] buttonTimeArr = new float[300];

    /**
     * 初始化
     *
     * @param person 角色
     */
    public PlayerCommandHandler(Person person) {
        this.person = person;
    }

    /**
     * 每帧更新移动逻辑
     */
    public void update(float delta) {
        //是否按下方向键
        boolean anyDirPressed = false;
        //按“绿宝石手感”优先级：上 > 下 > 左 > 右
        for (Integer key : DIR_INPUT_KEY_LINKED_SET) {
            //如果未按下
            if (isPressed(key) == false) {
                //本轮过
                continue;
            }
            //记录其按下了
            anyDirPressed = true;
            //获取方向
            DirectionEnum directionEnum = DirectionEnum.parseByKeycode(key);
            //无论如何,人物先转向
            this.person.changeFacingDir(directionEnum);
            //叠加按下时间
            addPressTime(key, delta);
            //按下时间还没到阈值：只转向不移动
            if (getPressTime(key) <= NOT_MOVE_TIME) {
                //本轮过
                continue;
            }
            //计算走路 / 跑步
            WalkEnum walkEnum = this.buttonPressedArray[Settings.INPUT_KEY_CANCEL] ? WalkEnum.RUN : WalkEnum.WALK;
            //人物真正尝试移动
            this.person.move(directionEnum, walkEnum);
            //本帧只允许一个方向，结束判定
            break;
        }
        //没有任何方向键按着，则尝试停止走路
        if (anyDirPressed == false) {
            this.person.walkStop();
        }
    }

    /**
     * 按下
     */
    public void onKeyDown(int keycode) {
        //输入
        this.buttonPressedArray[keycode] = true;
    }

    /**
     * 取消按下
     */
    public void onKeyUp(int keycode) {
        //输出
        this.buttonPressedArray[keycode] = false;
    }

    /**
     * 获取 按下状态
     *
     * @param keycode
     * @return
     */
    private boolean isPressed(int keycode) {
        //返回
        return this.buttonPressedArray[keycode];
    }

    /**
     * 叠加 按下时间
     *
     * @param keycode 键位
     * @return
     */
    private void addPressTime(int keycode, float delta) {
        //叠加
        this.buttonTimeArr[keycode] += delta;
    }

    /**
     * 获取 按下时间
     *
     * @param keycode 键位
     * @return
     */
    private float getPressTime(int keycode) {
        //返回
        return this.buttonTimeArr[keycode];
    }

}