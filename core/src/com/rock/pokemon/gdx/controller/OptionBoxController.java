package com.rock.pokemon.gdx.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.ui.box.OptionBox;

/**
 * 对话框输入控制器
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
            case Keys.UP:
            case Keys.DOWN:
            case Keys.X:
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
        //如果不可见
        if (optionBox.isVisible() == false) {
            //过
            return false;
        }
        //根据按键判断
        switch (keycode) {
            //上
            case Keys.UP:
                //移动
                this.optionBox.moveUp();
                //是
                return true;
            //下
            case Keys.DOWN:
                //移动
                this.optionBox.moveDown();
                //是
                return true;
            //默认X为取消
            case Keys.X:
                //不可见
                this.optionBox.setVisible(false);
                //是
                return true;
            //默认
            default:
                //过
                return false;
        }
    }

}
