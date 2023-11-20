package com.rock.pokemon.gdx.ui.box;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项框 实体
 */
public class OptionBox extends Table {

    /**
     * 基础
     */

    //游戏对象
    private final Pokemon game;

    /**
     * 可选项内容
     */

    //可选项指针索引
    private int selectorIndex = 0;
    //可选项列表
    private List<Label> optionList = new ArrayList<>();
    //箭头列表
    private List<Image> arrowList = new ArrayList<>();
    //存储着箭头和文本的小盒子
    private Table uiContainer;

    /**
     * 初始化 可选项框
     *
     * @param game 游戏对象
     */
    public OptionBox(Pokemon game) {

        //初始化父级
        super(game.getSkin());

        //记录游戏对象
        this.game = game;

        //设置整体背景贴图
        this.setBackground(Settings.UI_IMAGE_OPTION_BOX);
        this.uiContainer = new Table();
        this.add(this.uiContainer)
                .pad(15f);

    }

    /**
     * 加入一行可选项
     *
     * @param option 可选项文字
     */
    public void addOption(String option) {

        /**
         * 对应箭头
         */

        //初始化箭头图片
        Image selectorLabel = new Image(this.getSkin(), Settings.UI_IMAGE_OPTION_ARROW);
        //指定箭头比例
        selectorLabel.setScaling(Scaling.none);
        selectorLabel.setScale(Settings.SCALE / 1.5F);
        //组装至列表
        this.arrowList.add(selectorLabel);
        //重新计算箭头是否可见
        restArrowVisible();
        //将箭头组装至窗口
        this.uiContainer.add(selectorLabel)
                //拉伸
                .expand()
                //左对齐
                .align(Align.left)
                //周围组件间距
                .space(Settings.TILE_SIZE / 2F);

        /**
         * 对应文字
         */

        //初始化文字,载入字体
        Label optionLabel = new Label(option, this.getSkin(), Settings.SYSTEM_FONT_LABEL);
        //组装至列表
        this.optionList.add(optionLabel);
        //将文字组装至窗口
        this.uiContainer.add(optionLabel)
                //拉伸
                .expand()
                //右对齐
                .align(Align.right)
                //周围组件间距
                .space(Settings.TILE_SIZE / 2F);

        /**
         * 换行
         */

        //换行
        this.uiContainer.row();

    }

    /**
     * 箭头向上移动
     */
    public void moveUp() {
        //如果未开启
        if (this.isVisible() == false) {
            //过
            return;
        }
        //向上移动一行,不可越界
        this.selectorIndex = this.selectorIndex - 1;
        //如果越界
        if (this.selectorIndex < 0) {
            //重置为另一边
            this.selectorIndex = this.arrowList.size() - 1;
        }
        //重置当前箭头可见
        restArrowVisible();
        //移动音效
        this.game.getSoundManager().playMenuClose();
    }

    /**
     * 箭头向下移动
     */
    public void moveDown() {
        //如果未开启
        if (this.isVisible() == false) {
            //过
            return;
        }
        //向下移动一行
        this.selectorIndex = this.selectorIndex + 1;
        //如果越界
        if (this.selectorIndex >= this.arrowList.size()) {
            //重置为另一边
            this.selectorIndex = 0;
        }
        //重置当前箭头可见
        restArrowVisible();
        //移动音效
        this.game.getSoundManager().playMenuClose();
    }

    /**
     * 重置当前箭头可见
     */
    private void restArrowVisible() {
        //循环所有箭头列表
        for (int i = 0; i < this.arrowList.size(); i++) {
            //决定开启 or 关闭
            this.arrowList.get(i).setVisible(i == this.selectorIndex);
        }
    }

    /**
     * 改变可见
     */
    public void change() {
        //对可见取反
        if (this.isVisible()) {
            //设置为不可见
            change(false);
        } else {
            //可见
            change(true);
        }
    }

    /**
     * 改变可见
     *
     * @param open 是否可见
     */
    public void change(boolean open) {
        //如果打开
        if (open) {
            //如果已经开启了
            if (this.isVisible()) {
                //过
                return;
            }
            //设置为可见
            this.setVisible(true);
            //菜单打开音效
            this.game.getSoundManager().playMenuOpen();
        } else {
            //如果已经关闭了
            if (this.isVisible() == false) {
                //过
                return;
            }
            //设置为不可见
            this.setVisible(false);
            //菜单关闭音效
            this.game.getSoundManager().playMenuClose();
        }
    }

    /**
     * 清理所有可选项
     */
    public void clearChoices() {
        this.uiContainer.clearChildren();
        this.optionList.clear();
        this.arrowList.clear();
        this.selectorIndex = 0;
    }

}
