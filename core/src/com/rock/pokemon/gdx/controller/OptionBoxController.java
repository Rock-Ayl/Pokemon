package com.rock.pokemon.gdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.ui.box.OptionBox;

/**
 * 可选项框 输入控制器
 */
public class OptionBoxController extends InputAdapter {

    //可选项框实体
    private OptionBox optionBox;

    /**
     * 初始化 对话框
     *
     * @param box 传入可选项
     */
    public OptionBoxController(OptionBox box) {
        this.optionBox = box;
    }

    /**
     * 按键按下时调用
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
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
