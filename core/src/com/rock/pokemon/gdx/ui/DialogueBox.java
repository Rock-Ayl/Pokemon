package com.rock.pokemon.gdx.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rock.pokemon.gdx.common.Settings;

/**
 * 对话框实体
 */
public class DialogueBox extends Table {

    //文本
    private Label textLabel;

    /**
     * 根据皮肤初始化对话框
     *
     * @param skin
     */
    public DialogueBox(Skin skin) {
        //初始化父级
        super(skin);
        //使用从皮肤中使用背景
        this.setBackground("dialogueBox");
        //初始化label
        this.textLabel = new Label("", skin);
        //将文本组装至文本框中
        this.add(this.textLabel)
                //均匀分布该label
                .expand()
                //左对齐
                .align(Align.left)
                //和周围间距
                .pad(Settings.SCALE * 3);
    }

    /**
     * 设置文本
     *
     * @param text 文本内容
     */
    public void setText(String text) {
        //设置文本
        this.textLabel.setText(text);
    }

    @Override
    public void act(float delta) {
        //todo
    }

}
