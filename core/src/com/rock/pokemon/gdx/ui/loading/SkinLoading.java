package com.rock.pokemon.gdx.ui.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rock.pokemon.gdx.common.FilePaths;
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
        TextureAtlas uiAtlas = assetManager.get(FilePaths.TEXTURES_ALTA_UI + FilePaths.TEXTURES_ATLAS_FILE_NAME);

        /**
         * ui 对话框
         */

        //获取对话框
        NinePatch buttonSquareBlue = new NinePatch(uiAtlas.findRegion("dialogue_box"), 10, 10, 5, 5);
        //设置该图片的宽高比
        buttonSquareBlue.scale(Settings.SCALE, Settings.SCALE);
        //组装至皮肤
        skin.add(Settings.UI_IMAGE_DIALOGUE_BOX, buttonSquareBlue);

        /**
         * ui 可选项框
         */

        //获取可选项框
        NinePatch option = new NinePatch(uiAtlas.findRegion("option_box"), 5, 5, 5, 5);
        //设置该图片的宽高比
        option.scale(Settings.SCALE, Settings.SCALE);
        //组装至皮肤
        skin.add(Settings.UI_IMAGE_OPTION_BOX, option);

        //获取可选项箭头
        TextureAtlas.AtlasRegion arrow = uiAtlas.findRegion("arrow");
        //组装至皮肤
        skin.add(Settings.UI_IMAGE_OPTION_ARROW, arrow, TextureRegion.class);

        /**
         * 通用字体样式
         */

        //初始化通用字体样式
        Label.LabelStyle normalFontLabel = new Label.LabelStyle();
        //载入字体
        normalFontLabel.font = new BitmapFont(
                Gdx.files.internal(FilePaths.SYSTEM_TEXT_FONT_FNT),
                Gdx.files.internal(FilePaths.SYSTEM_TEXT_FONT_IMAGE),
                false
        );
        //组装至皮肤
        skin.add(Settings.SYSTEM_FONT_LABEL, normalFontLabel);

        //返回皮肤
        return skin;
    }

}
