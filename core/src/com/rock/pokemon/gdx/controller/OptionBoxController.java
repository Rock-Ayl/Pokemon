package com.rock.pokemon.gdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.ui.box.OptionBox;

/**
 * 对话框 输入控制器
 */
public class OptionBoxController extends InputAdapter {

    //对应的对话框
    private OptionBox optionBox;

    /**
     * 初始化 对话框
     *
     * @param box 传入可选项
     */
    public OptionBoxController(OptionBox box) {
        this.optionBox = box;
    }

    @Override
    public boolean keyDown(int keycode) {
        //如果可选框不可见
        if (this.optionBox.isVisible() == false) {
            //过
            return false;
        }
        //根据按键判断
        switch (keycode) {
            //上下X
            case Input.Keys.UP:
            case Input.Keys.DOWN:
            case Input.Keys.X:
                //是
                return true;
            //默认
            default:
                //过
                return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        //根据按键判断
        switch (keycode) {
            //上
            case Input.Keys.UP:
                //移动
                this.optionBox.moveUp();
                //是
                return true;
            //下
            case Input.Keys.DOWN:
                //移动
                this.optionBox.moveDown();
                //是
                return true;
            //默认X为取消
            case Input.Keys.X:
                //处理取消
                this.optionBox.change(false);
                //是
                return true;
            //回车
            case Input.Keys.ENTER:
                //开/关
                this.optionBox.change();
                //是
                return true;
            //默认
            default:
                //过
                return false;
        }
    }

}
