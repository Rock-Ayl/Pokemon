package com.rock.pokemon.gdx.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

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
        //测试文本
        textLabel = new Label("This is a test 这是一段测试文本.", skin);
        //将文本组装至文本框中,左对齐,空5F
        this.add(textLabel)
                .expand()
                .align(Align.left)
                .pad(5f);
    }

    @Override
    public void act(float delta) {
        //todo
    }

}
