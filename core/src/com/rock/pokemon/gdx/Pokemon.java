package com.rock.pokemon.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.model.manager.SoundManager;
import com.rock.pokemon.gdx.screen.menu.MainMenu;
import com.rock.pokemon.gdx.ui.loading.AssetManagerLoading;
import com.rock.pokemon.gdx.ui.loading.SkinLoading;
import com.rock.pokemon.gdx.ui.loading.TextLoading;

import java.util.Map;

/**
 * 游戏入口
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class Pokemon extends Game {

    /**
     * 游戏基础实体,无论到哪里都用得上的东西
     */

    //渲染器
    private SpriteBatch batch;

    //资源管理器
    private AssetManager assetManager;

    //音效管理器
    private SoundManager soundManager;

    //皮肤
    private Skin skin;

    //字体
    private BitmapFont font;

    //文本map[编号,文本]
    private Map<Integer, String> textMap;

    /**
     * 初始化
     */
    public void create() {

        /**
         * 基础
         */

        //初始化一个SpriteBatch,游戏中只有一个,直到游戏结束后销毁
        this.batch = new SpriteBatch();

        //初始化资源管理器,并载入资源
        this.assetManager = AssetManagerLoading.initAssetManager();

        //初始化通用音效
        this.soundManager = new SoundManager();

        //初始化皮肤,并载入资源
        this.skin = SkinLoading.initSkin(this.getAssetManager());

        //使用默认字体
        this.font = new BitmapFont();
        //设置字体缩放
        this.font.getData().setScale(Settings.SCALE);

        //根据语言,载入文本
        this.textMap = TextLoading.initText();

        /**
         * 场景
         */

        //进入主菜单
        this.setScreen(new MainMenu(this));

    }

    /**
     * 帧渲染
     */
    public void render() {
        //官网要求
        super.render();
    }

    /**
     * 销毁资源
     */
    public void dispose() {
        this.batch.dispose();
        this.font.dispose();
        this.assetManager.dispose();
    }

    /**
     * 以下是普通 get set 方法
     */

    public SpriteBatch getBatch() {
        return this.batch;
    }

    /**
     * 获取游戏默认font
     *
     * @return
     */
    public BitmapFont getFont() {
        return this.font;
    }

    /**
     * 获取资源管理器
     *
     * @return
     */
    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    /**
     * 获取音效管理器
     *
     * @return
     */
    public SoundManager getSoundManager() {
        return this.soundManager;
    }

    /**
     * 获取皮肤
     *
     * @return
     */
    public Skin getSkin() {
        return this.skin;
    }

    /**
     * 获取文本map[编号,文本]
     *
     * @return
     */
    public Map<Integer, String> getTextMap() {
        return this.textMap;
    }

}
