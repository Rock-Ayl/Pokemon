package com.rock.pokemon.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rock.pokemon.gdx.enums.LanguageEnum;
import com.rock.pokemon.gdx.model.SoundManager;
import com.rock.pokemon.gdx.screen.MainMenu;
import com.rock.pokemon.gdx.ui.SkinLoading;
import com.rock.pokemon.gdx.ui.TextLoading;

import java.util.Map;

/**
 * 游戏入口
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class Pokemon extends Game {

    /**
     * 基础实体
     */

    //渲染器
    private SpriteBatch batch;

    //皮肤
    private Skin skin;

    //字体
    private BitmapFont font;

    //文本map[编号,文本]
    private Map<Integer, String> textMap;

    //资源管理器
    private AssetManager assetManager;

    //音效管理器
    private SoundManager soundManager;

    /**
     * 初始化
     */
    public void create() {

        /**
         * 基础
         */

        //初始化一个SpriteBatch,游戏中只有一个,直到游戏结束后销毁
        this.batch = new SpriteBatch();

        //初始化资源管理器
        this.assetManager = new AssetManager();
        //加载已打包的各种资源
        this.assetManager.load("assets/packed/image/people/ruby/walk/textures.atlas", TextureAtlas.class);
        this.assetManager.load("assets/packed/image/people/ruby/stand/textures.atlas", TextureAtlas.class);
        this.assetManager.load("assets/packed/image/map/grass/textures.atlas", TextureAtlas.class);
        this.assetManager.load("assets/packed/image/map/object/textures.atlas", TextureAtlas.class);
        this.assetManager.load("assets/packed/image/ui/textures.atlas", TextureAtlas.class);
        //加载资源完成
        this.assetManager.finishLoading();

        //初始化皮肤
        this.skin = SkinLoading.initSkin(this.getAssetManager());

        //使用默认字体
        this.font = new BitmapFont();
        //设置字体缩放
        this.font.getData().setScale(1.5F);

        //根据语言,载入文本,默认使用中文吧
        this.textMap = TextLoading.initText(LanguageEnum.SIMPLIFIED_CHINESE);

        //初始化通用音效
        this.soundManager = new SoundManager();

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
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Map<Integer, String> getTextMap() {
        return textMap;
    }

}
