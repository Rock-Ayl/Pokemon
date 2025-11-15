package com.rock.pokemon.gdx.model.manager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.model.ui.loading.SkinLoading;
import com.rock.pokemon.gdx.model.ui.loading.TextLoading;
import lombok.Getter;

import java.util.Map;

/**
 * 游戏上下文：通用资源 统一放这里
 *
 * @Author ayl
 * @Date 2025-11-15
 */
@Getter
public class GameContext {

    /**
     * 游戏基础实体,无论到哪里都用得上的东西
     */

    //渲染器
    private SpriteBatch batch;

    //资源管理器
    private MyAssetManager myAssetManager;

    //音效管理器
    private MySoundManager mySoundManager;

    //存档管理器
    private SaveManager saveManager;

    //皮肤
    private Skin skin;

    //字体
    private BitmapFont font;

    //文本map[编号,文本]
    private Map<Integer, String> textMap;

    /**
     * 初始化 游戏上下文：通用资源
     */
    public GameContext() {

        /**
         * 基础
         */

        //初始化一个SpriteBatch,游戏中只有一个,直到游戏结束后销毁
        this.batch = new SpriteBatch();

        //初始化资源管理器,并载入资源
        this.myAssetManager = new MyAssetManager();

        //初始化通用音效
        this.mySoundManager = new MySoundManager(this.myAssetManager.getSoundMapConfig());

        //todo 初始化存档,目前写死,之后要读取存档
        this.saveManager = new SaveManager();

        //初始化皮肤,并载入资源
        this.skin = SkinLoading.initSkin(this.getMyAssetManager());

        //使用默认字体
        this.font = new BitmapFont();
        //设置字体缩放
        this.font.getData().setScale(Settings.SCALE);

        //根据语言,载入文本
        this.textMap = TextLoading.initText();

    }

    /**
     * 销毁资源
     */
    public void dispose() {
        this.batch.dispose();
        this.font.dispose();
        this.myAssetManager.dispose();
    }

}