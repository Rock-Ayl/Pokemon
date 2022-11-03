package com.rock.pokemon.gdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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

        NinePatch buttonSquareBlue = new NinePatch(uiAtlas.findRegion("dialogue_box"), 10, 10, 5, 5);
        skin.add("dialogueBox", buttonSquareBlue);

        /**
         * 载入字体
         */

        //载入字体
        BitmapFont font = new BitmapFont(
                Gdx.files.internal("assets/font/song/STSong.fnt"),
                Gdx.files.internal("assets/font/song/STSong.png"),
                false
        );
        //字体组装至皮肤o
        skin.add("font", font);
        //初始化默认字体
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        //设置
        labelStyle.font = font;
        //组装至皮肤默认
        skin.add("default", labelStyle);

        //返回皮肤
        return skin;
    }

}