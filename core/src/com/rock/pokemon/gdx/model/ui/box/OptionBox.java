package com.rock.pokemon.gdx.model.ui.box;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.model.map.config.BoxMapConfig.BoxMapNodeBoxOption;

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
    private final PokemonGame pokemonGame;

    /**
     * 可选项内容
     */

    //存储着箭头和文本的小盒子
    private Table uiContainer;

    //可选项指针索引
    private int selectorIndex = 0;

    //可选项文字列表
    private List<Label> optionList = new ArrayList<>();
    //箭头列表
    private List<Image> arrowList = new ArrayList<>();
    //可选项节点配置列表
    private List<BoxMapNodeBoxOption> boxMapNodeBoxOptionList = new ArrayList<>();

    /**
     * 初始化 可选项框
     *
     * @param pokemonGame 游戏对象
     */
    public OptionBox(PokemonGame pokemonGame) {

        //初始化父类
        super(pokemonGame.getSkin());

        //记录游戏对象
        this.pokemonGame = pokemonGame;

        //设置整体背景贴图
        this.setBackground(Settings.UI_IMAGE_OPTION_BOX);
        this.uiContainer = new Table();
        this.add(this.uiContainer)
                .pad(15f);

    }

    /**
     * 设置所有选项
     *
     * @param boxMapNodeBoxOptionList 可选项节点列表
     */
    public void setOption(List<BoxMapNodeBoxOption> boxMapNodeBoxOptionList) {
        //清理当前box
        clearBox();
        //循环
        for (BoxMapNodeBoxOption boxMapNodeBoxOption : boxMapNodeBoxOptionList) {
            //加入一行可选项
            addOption(boxMapNodeBoxOption);
        }
    }

    /**
     * 加入一行可选项
     *
     * @param boxMapNodeBoxOption 可选项节点
     */
    private void addOption(BoxMapNodeBoxOption boxMapNodeBoxOption) {

        //加入可选项
        this.boxMapNodeBoxOptionList.add(boxMapNodeBoxOption);

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

        //初始化对应文字,载入字体
        Label optionLabel = new Label(this.pokemonGame.getTextMap().get(boxMapNodeBoxOption.getValueTextNumber()), this.getSkin(), Settings.SYSTEM_FONT_LABEL);
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
        this.pokemonGame.getMySoundManager().play(Settings.SOUND_ID_MENU_CLOSE_AND_MOVE);
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
        this.pokemonGame.getMySoundManager().play(Settings.SOUND_ID_MENU_CLOSE_AND_MOVE);
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
            this.pokemonGame.getMySoundManager().play(Settings.SOUND_ID_MENU_OPEN);
        } else {
            //如果已经关闭了
            if (this.isVisible() == false) {
                //过
                return;
            }
            //设置为不可见
            this.setVisible(false);
            //菜单关闭音效
            this.pokemonGame.getMySoundManager().play(Settings.SOUND_ID_MENU_CLOSE_AND_MOVE);
        }
    }

    /**
     * 获取当前选中的可选项
     *
     * @return
     */
    public BoxMapNodeBoxOption getSelectorBoxMapNodeBoxOption() {
        return this.boxMapNodeBoxOptionList.get(this.selectorIndex);
    }

    /**
     * 清空盒子
     */
    private void clearBox() {
        this.uiContainer.clear();
        this.optionList.clear();
        this.arrowList.clear();
        this.boxMapNodeBoxOptionList.clear();
        this.selectorIndex = 0;
    }

}
