package com.rock.pokemon.gdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rock.pokemon.gdx.common.Settings;

/**
 * 皮肤载入
 */
public class SkinLoading {

    /**
     * 初始化一个皮肤,并载入资源
     *
     * @param assetManager 资源管理器
     * @return
     */
    public static Skin initSkin(AssetManager assetManager) {

        //初始化皮肤
        Skin skin = new Skin();

        /**
         * 载入ui资源
         */

        //获取ui资源
        TextureAtlas uiAtlas = assetManager.get("assets/packed/image/ui/textures.atlas");

        //获取对话框
        NinePatch buttonSquareBlue = new NinePatch(uiAtlas.findRegion("dialogue_box"), 10, 10, 5, 5);
        //设置该图片的宽高比
        buttonSquareBlue.scale(Settings.SCALE, Settings.SCALE);
        //组装至皮肤
        skin.add("dialogueBox", buttonSquareBlue);

        /**
         * 载入样式
         */

        //初始化[对话框][Label]样式
        Label.LabelStyle dialogueBoxLabelStyle = new Label.LabelStyle();
        //载入对话框字体,并设置字体
        dialogueBoxLabelStyle.font = new BitmapFont(
                Gdx.files.internal("assets/font/black/黑体.fnt"),
                Gdx.files.internal("assets/font/black/黑体.png"),
                false
        );
        //组装[对话框][Label]样式至皮肤中
        skin.add(Settings.STYLE_DIALOGUE_BOX_LABEL, dialogueBoxLabelStyle);

        //返回皮肤
        return skin;
    }

}
