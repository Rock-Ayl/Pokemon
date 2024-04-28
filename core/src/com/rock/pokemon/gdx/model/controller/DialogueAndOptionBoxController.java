package com.rock.pokemon.gdx.model.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.model.ui.box.DialogueAndOptionBox;

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
                this.dialogueAndOptionBox.moveUp();
                break;
            //下
            case Input.Keys.DOWN:
                //移动
                this.dialogueAndOptionBox.moveDown();
                break;
            //取消
            case Input.Keys.X:
                break;
            //确认
            case Input.Keys.Z:
            case Input.Keys.ENTER:
                //处理确认
                this.dialogueAndOptionBox.nextNode();
                break;
            //默认
            default:
                break;
        }
        //如果动画没有结束,则后续按键不会被触发
        return this.dialogueAndOptionBox.isFinished() == false;
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
