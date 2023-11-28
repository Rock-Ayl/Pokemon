package com.rock.pokemon.gdx.ui.box;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.model.mapConfig.BoxMapNode;
import com.rock.pokemon.gdx.model.mapConfig.BoxMapNodeBox;
import com.rock.pokemon.gdx.model.mapConfig.BoxMapNodeBoxOption;
import com.rock.pokemon.gdx.util.JsonExtraUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //对应桌面
    private Table table;

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
     * @param game 游戏对象
     */
    public DialogueAndOptionBox(Pokemon game, Table table) {
        //记录游戏对象
        this.game = game;
        //记录桌面
        this.table = table;
    }

    /**
     * 重置配置
     *
     * @param boxMapNode 盒子配置节点
     */
    public void reset(BoxMapNode boxMapNode) {

        //深克隆(因为该配置后续会存在改变的情况),并记录盒子配置节点
        this.boxMapNode = JsonExtraUtils.deepClone(boxMapNode, BoxMapNode.class);

        //初始化基本参数
        this.nodeIndex = 0;

        //初始化对话框、可选项框
        this.dialogueBox = new DialogueBox(this.game, this);
        this.optionBox = new OptionBox(this.game);

        //默认不显示
        setVisible(false);

        /**
         * 加入到桌面
         */

        //清除原有
        this.table.clear();
        //将菜单放入桌面右边
        this.table.add(this.optionBox)
                .expand()
                .align(Align.right)
                //和边界的间隙
                .padRight(Settings.SCALE)
                //换行
                .row();
        //将对话框放在谈话table的下方
        this.table.add(this.dialogueBox)
                .expand()
                //横坐标成长到最大(拉伸)
                .growX()
                //高大概是两格
                .height(Settings.SCALE_TILE_SIZE * 2.5F)
                //向下对齐
                .align(Align.bottom)
                //和边界的间隙,左右小点,下面大点
                .padLeft(Settings.SCALE)
                .padRight(Settings.SCALE)
                .padBottom(Settings.SCALE * 3);

    }

    /**
     * 处理确认逻辑
     */
    public void nextNode() {

        /**
         * 判断不处理逻辑
         */

        //如果没有加载
        if (this.boxMapNode == null) {
            //过
            return;
        }
        //如果对话未完成
        if (this.dialogueBox.isFinished() == false) {
            //结束
            return;
        }

        /**
         * 这里判断 可选项 确认操作
         */

        //如果选项可见
        if (this.optionBox.isVisible()) {

            /**
             * 加入选择该选项后的内容
             */

            //获取当前选中的可选项
            BoxMapNodeBoxOption boxMapNodeBoxOption = this.optionBox.getSelectorBoxMapNodeBoxOption();
            //获取这些可选项中的盒子列表
            List<BoxMapNodeBox> boxList = Optional.ofNullable(boxMapNodeBoxOption)
                    .map(BoxMapNodeBoxOption::getBoxList)
                    .orElse(new ArrayList<>());
            //插入到该可选项盒子列表后面
            this.boxMapNode.getBoxList().addAll(this.nodeIndex, boxList);

            //播放音效
            this.game.getSoundManager().playMenuClose();

            /**
             * 继续执行
             */

            //统一设置为不可见
            setVisible(false);

            //继续执行(展开下一个[对话/可选项/隐藏]等等)
            nextNode();

            //结束
            return;
        }

        /**
         * 统一处理下一个节点
         */

        //如果已完成
        if (isFinished()) {
            //统一设置为不可见
            setVisible(false);
            //结束
            return;
        }
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
                //播放音效
                this.game.getSoundManager().playMenuClose();
                break;
        }

    }

    /**
     * 当 对话框 动画完成时,回调
     */
    public void dialogueBoxFinish() {
        //如果没有加载
        if (this.boxMapNode == null) {
            //过
            return;
        }
        //如果已完成
        if (isFinished()) {
            //过
            return;
        }
        //获取当前节点配置
        BoxMapNodeBox boxMapNodeBox = this.boxMapNode.getBoxList().get(this.nodeIndex);
        //获取盒子配置类型
        String type = boxMapNodeBox.getType();
        //根据类型处理
        switch (type) {
            //可选框
            case "OptionBox":
                //自动开启对话配对的可选项
                nextNode();
                break;
        }
    }

    /**
     * 判断是否已完成(当没有节点时视为完成)
     *
     * @return
     */
    public boolean isFinished() {
        //如果没有加载
        if (this.boxMapNode == null) {
            //过
            return true;
        }
        //判断
        return this.nodeIndex >= this.boxMapNode.getBoxList().size();
    }

    /**
     * 统一设置是否可见
     *
     * @param visible 是否可见
     */
    public void setVisible(boolean visible) {
        //判空
        if (this.optionBox != null) {
            //设置
            this.optionBox.setVisible(visible);
        }
        //判空
        if (this.dialogueBox != null) {
            //设置
            this.dialogueBox.setVisible(visible);
        }
    }

    /**
     * 箭头向上移动
     */
    public void moveUp() {
        //判空
        if (this.optionBox != null) {
            //执行
            this.optionBox.moveUp();
        }
    }

    /**
     * 箭头向下移动
     */
    public void moveDown() {
        //判空
        if (this.optionBox != null) {
            //执行
            this.optionBox.moveDown();
        }
    }

}
