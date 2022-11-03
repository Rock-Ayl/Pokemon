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
     * 基础属性
     */

    //缩放倍率,游戏的任何等比缩放,都与其有关
    private float scale = 3F;
    //地图/贴图/人物单个网格大小
    private final float tileSize = 16F;

    //屏幕尺寸,GBA屏幕分辨率为 240 * 160 ,该比例固定,默认为GBA原生分辨率[1.5:1],但总体不会固定为这个比率,要看用户设置
    private float windowWidth = 240;
    private float windowHeight = 160;

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
     * 根据当前屏幕尺寸更新窗口配置(本质上是根据[屏幕尺寸宽]更新[缩放倍率])
     *
     * @param width
     * @param height
     */
    public void updateWindows(int width, int height) {

        //屏幕尺寸变更为该尺寸
        this.windowWidth = width;
        this.windowHeight = height;

    }

    /**
     * 缩放倍率
     *
     * @return
     */
    public float getScale() {
        return scale;
    }

    /**
     * 设置缩放比率
     *
     * @param scale 传入设置
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * 获取1坐标数值的地图块或人物的真实的大小,该数值随着缩放倍率变动
     *
     * @return
     */
    public float getScaledTileSize() {
        return tileSize * scale;
    }

    /**
     * 获取最小的屏幕尺寸宽,强制为int
     *
     * @return
     */
    public int getMinWindowWidth() {
        return (int) windowWidth * 2;
    }

    /**
     * 获取最小的屏幕尺寸高,强制为int
     *
     * @return
     */
    public int getMinWindowHeight() {
        return (int) windowHeight * 2;
    }

    /**
     * 屏幕尺寸宽,强制为int
     *
     * @return
     */
    public int getWindowWidthInt() {
        return (int) (windowWidth * scale);
    }

    /**
     * 屏幕尺寸高,强制为int
     *
     * @return
     */
    public int getWindowHeightInt() {
        return (int) (windowHeight * scale);
    }

    /**
     * 屏幕尺寸宽
     *
     * @return
     */
    public float getWindowWidth() {
        return windowWidth * scale;
    }

    /**
     * 屏幕尺寸高
     *
     * @return
     */
    public float getWindowHeight() {
        return windowHeight * scale;
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

}
