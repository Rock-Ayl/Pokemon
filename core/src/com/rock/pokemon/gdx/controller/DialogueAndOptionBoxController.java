package com.rock.pokemon.gdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.ui.box.DialogueAndOptionBox;

/**
 * 对话框 + 可选项框 输入控制器
 */
public class DialogueAndOptionBoxController extends InputAdapter {

    //对话框 + 可选项框 实体
    private DialogueAndOptionBox dialogueAndOptionBox;

    /**
     * 初始化
     *
     * @param dialogueAndOptionBox 传入实体
     */
    public DialogueAndOptionBoxController(DialogueAndOptionBox dialogueAndOptionBox) {
        this.dialogueAndOptionBox = dialogueAndOptionBox;
    }

    /**
     * 按键按下时调用
     *
     * @param keycode
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
        //根据按键判断
        switch (keycode) {
            //上
            case Input.Keys.UP:
                //移动
                this.dialogueAndOptionBox.getOptionBox().moveUp();
                //是
                return true;
            //下
            case Input.Keys.DOWN:
                //移动
                this.dialogueAndOptionBox.getOptionBox().moveDown();
                //是
                return true;
            //默认X为取消
            case Input.Keys.X:
                //处理取消
                this.dialogueAndOptionBox.getOptionBox().change(false);
                //是
                return true;
            //回车
            case Input.Keys.ENTER:
                //开/关
                this.dialogueAndOptionBox.getOptionBox().change();
                //是
                return true;
            //默认
            default:
                //过
                return false;
        }
    }

    /**
     * 按键被释放时调用
     *
     * @param keycode 输入
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        //过
        return false;
    }

}
