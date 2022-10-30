package com.rock.pokemon.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.model.SoundManager;
import com.rock.pokemon.gdx.screen.MainMenu;

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

    //GBA屏幕分辨率为 240 * 160 ,该比例固定,当然,实际使用需要根据缩放倍率计算,另外,绿宝石无论尺寸大小,x轴固定为15个长度地图块,y轴则不按照地图块算

    //屏幕尺寸,默认为GBA原生分辨率[1.5:1],但总体不会固定为这个比率,要看用户设置
    private float windowWidth = 240;
    private float windowHeight = 160;

    //宽高比,GBA屏幕分辨率为 240 * 160 ,比例为1.5
    private final float whProportion = 1.5F;

    //相机比率,相机比例永远是[1.5:1],默认为屏幕尺寸
    private float camreaWidth = windowWidth;
    private float camreaHeight = windowWidth / whProportion;

    /**
     * 基础实体
     */

    //渲染器
    private SpriteBatch batch;

    //字体
    private BitmapFont font;

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

        //使用默认的字体
        this.font = new BitmapFont();

        //初始化资源管理器
        this.assetManager = new AssetManager();
        //加载已打包的图片资源
        this.assetManager.load("assets/packed/image/people/ruby/walk/textures.atlas", TextureAtlas.class);
        this.assetManager.load("assets/packed/image/people/ruby/stand/textures.atlas", TextureAtlas.class);
        this.assetManager.load("assets/packed/image/map/grass/textures.atlas", TextureAtlas.class);
        this.assetManager.load("assets/packed/image/map/object/textures.atlas", TextureAtlas.class);
        //加载资源完成
        this.assetManager.finishLoading();

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

        //该屏幕尺寸的宽高比
        float thisWhProportion = width / height;

        //判断比率和游戏设定的关系
        if (thisWhProportion > this.whProportion) {
            //todo 根据当前宽高比,更新相机尺寸
        } else if (thisWhProportion < this.whProportion) {
            //todo 根据当前宽高比,更新相机尺寸
        } else {
            //既然比率固定
            this.camreaWidth = width;
            this.camreaHeight = height;
        }

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

}
