package com.rock.pokemon.gdx.ui.box;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.rock.pokemon.gdx.common.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * 可选项框 实体
 */
public class OptionBox extends Table {

    //可选项指针索引
    private int selectorIndex = 0;
    //可选项列表
    private List<Label> optionList = new ArrayList<>();
    //箭头列表
    private List<Image> arrowList = new ArrayList<>();

    private Table uiContainer;

    /**
     * 初始化 可选项框
     *
     * @param skin
     */
    public OptionBox(Skin skin) {
        //初始化父级
        super(skin);
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

        //初始化文字,载入字体
        Label optionLabel = new Label(option, this.getSkin(), Settings.SYSTEM_FONT_LABEL);
        //组装至列表
        this.optionList.add(optionLabel);

        //初始化箭头图片
        Image selectorLabel = new Image(this.getSkin(), Settings.UI_IMAGE_OPTION_ARROW);
        //指定箭头比例
        selectorLabel.setScaling(Scaling.none);
        selectorLabel.setScale(Settings.SCALE / 1.5F);
        //组装至列表
        this.arrowList.add(selectorLabel);

        //重新计算箭头是否可见
        restArrowVisible();

        //将箭头、文字组装至窗口
        uiContainer.add(selectorLabel)
                //拉伸
                .expand()
                //左对齐
                .align(Align.left)
                //周围组件间距
                .space(Settings.TILE_SIZE / 2F);
        uiContainer.add(optionLabel)
                //拉伸
                .expand()
                //右对齐
                .align(Align.right)
                //周围组件间距
                .space(Settings.TILE_SIZE / 2F);
        //换行
        uiContainer.row();

    }

    /**
     * 箭头向上移动
     */
    public void moveUp() {
        //向上移动一行,不可越界
        this.selectorIndex = this.selectorIndex - 1;
        //如果越界
        if (this.selectorIndex < 0) {
            //重置为另一边
            this.selectorIndex = this.arrowList.size() - 1;
        }
        //重置当前箭头可见
        restArrowVisible();
    }

    /**
     * 箭头向下移动
     */
    public void moveDown() {
        //向下移动一行
        this.selectorIndex = this.selectorIndex + 1;
        //如果越界
        if (this.selectorIndex >= this.arrowList.size()) {
            //重置为另一边
            this.selectorIndex = 0;
        }
        //重置当前箭头可见
        restArrowVisible();
    }

    /**
     * 重置当前箭头可见
     */
    private void restArrowVisible() {
        //循环所有箭头列表
        for (int i = 0; i < this.arrowList.size(); i++) {
            //如果是选定的箭头
            if (i == this.selectorIndex) {
                //开启
                this.arrowList.get(i).setVisible(true);
            } else {
                //否则关闭
                this.arrowList.get(i).setVisible(false);
            }
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
