package com.rock.pokemon.gdx.ui.box;

import com.rock.pokemon.gdx.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * 对话框 + 可选项框 实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
public class DialogueAndOptionBox {

    /**
     * 基础
     */

    //游戏对象
    private final Pokemon game;

    //对话框
    private DialogueBox dialogueBox;

    //可选项框
    private OptionBox optionBox;

    /**
     * 控制盒子当前展示 对应对话框/可选项 参数
     */

    //索引
    private int nodeIndex;

    //todo
    private List nodeList;

    /**
     * 初始化
     *
     * @param game 游戏对象
     */
    public DialogueAndOptionBox(Pokemon game) {

        //记录游戏对象
        this.game = game;

        //初始化基本参数
        this.nodeIndex = 0;
        this.nodeList = new ArrayList();

        //初始化对话框、可选项框
        this.dialogueBox = new DialogueBox(this.game);
        this.optionBox = new OptionBox(this.game);

        //默认不显示
        this.dialogueBox.setVisible(false);
        this.optionBox.setVisible(false);

    }

}
