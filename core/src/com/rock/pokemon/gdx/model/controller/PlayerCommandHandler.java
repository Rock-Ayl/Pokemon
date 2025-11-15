package com.rock.pokemon.gdx.model.controller;


import com.badlogic.gdx.Input;
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

    //方向相关
    private static final LinkedHashSet<Integer> DIR_INPUT_KEY_LINKED_SET =
            new LinkedHashSet<>(Arrays.asList(
                    Input.Keys.UP,
                    Input.Keys.DOWN,
                    Input.Keys.LEFT,
                    Input.Keys.RIGHT
            ));

    // 跑步键
    private static final int RUN_INPUT_KEY = Input.Keys.X;

    // 按住不移动的阈值
    private static final float NOT_MOVE_TIME = 0.15F;

    // ---- 状态记录 ----

    //方向键是否按下
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    //各方向按下时间
    private float upTime;
    private float downTime;
    private float leftTime;
    private float rightTime;

    // 跑步键状态
    private boolean runPressed;

    /**
     * 初始化
     *
     * @param person
     */
    public PlayerCommandHandler(Person person) {
        this.person = person;
    }

    /**
     * InputAdapter.keyDown 转发到这里
     */
    public void onKeyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                upPressed = true;
                upTime = 0f;
                break;
            case Input.Keys.DOWN:
                downPressed = true;
                downTime = 0f;
                break;
            case Input.Keys.LEFT:
                leftPressed = true;
                leftTime = 0f;
                break;
            case Input.Keys.RIGHT:
                rightPressed = true;
                rightTime = 0f;
                break;
            case RUN_INPUT_KEY:
                runPressed = true;
                break;
            default:
                // 其他键不用管
        }
    }

    /**
     * InputAdapter.keyUp 转发到这里
     */
    public void onKeyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                upPressed = false;
                upTime = 0f;
                break;
            case Input.Keys.DOWN:
                downPressed = false;
                downTime = 0f;
                break;
            case Input.Keys.LEFT:
                leftPressed = false;
                leftTime = 0f;
                break;
            case Input.Keys.RIGHT:
                rightPressed = false;
                rightTime = 0f;
                break;
            case RUN_INPUT_KEY:
                runPressed = false;
                break;
            default:
                // ignore
        }
    }

    /**
     * 每帧更新移动逻辑
     */
    public void update(float delta) {
        boolean anyDirPressed = false;

        // 按“绿宝石手感”优先级：上 > 下 > 左 > 右
        for (Integer key : DIR_INPUT_KEY_LINKED_SET) {

            if (isPressed(key) == false) {
                continue;
            }

            anyDirPressed = true;

            DirectionEnum directionEnum = DirectionEnum.parseByKeycode(key);

            // 无论如何先转向
            this.person.changeFacingDir(directionEnum);

            // 叠加时间
            addPressTime(key, delta);

            // 按下时间还没到阈值：只转向不移动
            if (getPressTime(key) <= NOT_MOVE_TIME) {
                continue;
            }

            // 计算走路 / 跑步
            WalkEnum walkEnum = this.runPressed ? WalkEnum.RUN : WalkEnum.WALK;

            // 真正尝试移动
            this.person.move(directionEnum, walkEnum);

            // 本帧只允许一个方向，结束判定
            break;
        }

        //没有任何方向键按着，则尝试停止走路
        if (anyDirPressed == false) {
            this.person.walkStop();
        }
    }

    /**
     * 小工具方法：根据 key 读写状态
     *
     * @param keycode
     * @return
     */
    private boolean isPressed(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                return this.upPressed;
            case Input.Keys.DOWN:
                return this.downPressed;
            case Input.Keys.LEFT:
                return this.leftPressed;
            case Input.Keys.RIGHT:
                return this.rightPressed;
            default:
                return false;
        }
    }

    /**
     * 叠加 按下时间
     *
     * @param keycode 键位
     * @return
     */
    private void addPressTime(int keycode, float delta) {
        switch (keycode) {
            case Input.Keys.UP:
                this.upTime += delta;
                break;
            case Input.Keys.DOWN:
                this.downTime += delta;
                break;
            case Input.Keys.LEFT:
                this.leftTime += delta;
                break;
            case Input.Keys.RIGHT:
                this.rightTime += delta;
                break;
            default:
                // ignore
        }
    }

    /**
     * 获取 按下时间
     *
     * @param keycode 键位
     * @return
     */
    private float getPressTime(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                return this.upTime;
            case Input.Keys.DOWN:
                return this.downTime;
            case Input.Keys.LEFT:
                return this.leftTime;
            case Input.Keys.RIGHT:
                return this.rightTime;
            default:
                return 0f;
        }
    }

}