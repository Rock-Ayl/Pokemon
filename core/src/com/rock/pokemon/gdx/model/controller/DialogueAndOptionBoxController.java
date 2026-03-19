package com.rock.pokemon.gdx.model.controller;

import com.badlogic.gdx.InputAdapter;
import com.rock.pokemon.gdx.common.Settings;
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

        //记录当前是否可见(这是判断是否消耗该事件的关键,必须在处理逻辑之前获取状态)
        boolean visible = this.dialogueAndOptionBox.isVisible();

        //根据按键判断
        switch (keycode) {
            //上
            case Settings.INPUT_KEY_UP:
                //移动
                this.dialogueAndOptionBox.moveUp();
                break;
            //下
            case Settings.INPUT_KEY_DOWN:
                //移动
                this.dialogueAndOptionBox.moveDown();
                break;
            //取消
            case Settings.INPUT_KEY_CANCEL:
                break;
            //确认
            case Settings.INPUT_KEY_CONFIRM:
                //处理确认
                this.dialogueAndOptionBox.nextNode();
                break;
            //默认
            default:
                break;
        }
        
        //如果原本对话框可见,则后续按键不会被触发(消耗掉本次事件)
        return visible;
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
