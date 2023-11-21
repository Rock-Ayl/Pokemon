package com.rock.pokemon.gdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.ui.box.DialogueBox;

/**
 * 可选项框 输入控制器
 */
public class DialogueBoxController extends InputAdapter {

    //对话框实体
    private DialogueBox dialogueBox;

    /**
     * 初始化 对话框
     *
     * @param dialogueBox 传入可选项
     */
    public DialogueBoxController(DialogueBox dialogueBox) {
        this.dialogueBox = dialogueBox;
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
            //回车
            case Input.Keys.ENTER:
                //如果是对话完毕
                if (this.dialogueBox.isFinished()) {
                    //对话框不可见
                    this.dialogueBox.setVisible(false);
                }
                //是
                return true;
            //默认
            default:
                //过
                return false;
        }
    }

}
