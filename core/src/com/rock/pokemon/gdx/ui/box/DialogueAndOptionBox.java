package com.rock.pokemon.gdx.ui.box;

import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.model.mapConfig.BoxMapNode;
import com.rock.pokemon.gdx.model.mapConfig.BoxMapNodeBox;

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

    //Box节点 配置类实体
    private BoxMapNode boxMapNode;

    /**
     * 初始化
     *
     * @param game       游戏对象
     * @param boxMapNode 盒子配置节点
     */
    public DialogueAndOptionBox(Pokemon game, BoxMapNode boxMapNode) {
        //记录游戏对象
        this.game = game;
        //重置配置
        reset(boxMapNode);
    }

    /**
     * 重置配置
     *
     * @param boxMapNode 盒子配置节点
     */
    public void reset(BoxMapNode boxMapNode) {

        //记录盒子配置节点
        this.boxMapNode = boxMapNode;

        //初始化基本参数
        this.nodeIndex = 0;

        //初始化对话框、可选项框
        this.dialogueBox = new DialogueBox(this.game);
        this.optionBox = new OptionBox(this.game);

        //默认不显示
        this.dialogueBox.setVisible(false);
        this.optionBox.setVisible(false);

    }

    /**
     * 下一个节点
     */
    public void nextNode() {

        /**
         * 判断不处理逻辑
         */

        //如果对话未完成
        if (this.dialogueBox.isFinished() == false) {
            //结束
            return;
        }
        //如果索引越界了
        if (this.nodeIndex >= this.boxMapNode.getBoxList().size()) {
            //统一设置为不可见
            setVisible(false);
            //结束
            return;
        }

        /**
         * 统一处理
         */

        //获取下一个节点配置
        BoxMapNodeBox boxMapNodeBox = this.boxMapNode.getBoxList().get(nodeIndex++);
        //获取盒子配置类型
        String type = boxMapNodeBox.getType();
        //根据类型处理
        switch (type) {
            //可选框
            case "OptionBox":
                //新的对话框
                this.optionBox.setOption(boxMapNodeBox.getOptionList());
                //盒子可见
                this.optionBox.setVisible(true);
                break;
            //对话框
            case "DialogueBox":
                //新的文本
                this.dialogueBox.animateText(this.game.getTextMap().get(boxMapNodeBox.getTextNumber()));
                //盒子可见
                this.dialogueBox.setVisible(true);
                break;
        }

    }

    /**
     * 统一设置是否可见
     *
     * @param visible 是否可见
     */
    public void setVisible(boolean visible) {
        //统一设置
        this.dialogueBox.setVisible(visible);
        this.optionBox.setVisible(visible);
    }

    /**
     * 以下是 get set 方法
     */

    public DialogueBox getDialogueBox() {
        return this.dialogueBox;
    }

    public OptionBox getOptionBox() {
        return this.optionBox;
    }

}
