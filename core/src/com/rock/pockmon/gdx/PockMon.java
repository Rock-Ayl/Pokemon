package com.rock.pockmon.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pockmon.gdx.enums.DirectionEnum;
import com.rock.pockmon.gdx.enums.PersonEnum;
import com.rock.pockmon.gdx.model.animation.PersonAnimationSet;
import com.rock.pockmon.gdx.model.people.Person;
import com.rock.pockmon.gdx.screen.MainMenu;

/**
 * 游戏入口
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class PockMon extends Game {

    /**
     * 基础
     */

    //绘制图形
    private SpriteBatch batch;

    //字体
    private BitmapFont font;

    //资源管理器
    private AssetManager assetManager;

    /**
     * 主角
     */

    //主角
    private Person adventurer;

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
        //加载资源完成
        this.assetManager.finishLoading();

        /**
         * 资源
         */

        //获取资源
        TextureAtlas walkTextureAtlas = this.assetManager.get("assets/packed/image/people/ruby/walk/textures.atlas", TextureAtlas.class);
        TextureAtlas standTextureAtlas = this.assetManager.get("assets/packed/image/people/ruby/stand/textures.atlas", TextureAtlas.class);
        //初始化动画集合
        PersonAnimationSet animations = new PersonAnimationSet(
                //载入动画 秒/帧(N图-1=帧),名字,模式
                new Animation(0.3F / 2F, walkTextureAtlas.findRegions(DirectionEnum.NORTH.getName()), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3F / 2F, walkTextureAtlas.findRegions(DirectionEnum.SOUTH.getName()), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3F / 2F, walkTextureAtlas.findRegions(DirectionEnum.EAST.getName()), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3F / 2F, walkTextureAtlas.findRegions(DirectionEnum.WEST.getName()), Animation.PlayMode.LOOP_PINGPONG),
                //载入单帧图片
                standTextureAtlas.findRegion(DirectionEnum.NORTH.getName()),
                standTextureAtlas.findRegion(DirectionEnum.SOUTH.getName()),
                standTextureAtlas.findRegion(DirectionEnum.EAST.getName()),
                standTextureAtlas.findRegion(DirectionEnum.WEST.getName())
        );

        /**
         * 主角
         */

        //初始化主角,先使用男主角吧
        this.adventurer = new Person(PersonEnum.RUBE, animations);

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
     * 以下是 get set 方法
     */

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Person getAdventurer() {
        return adventurer;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

}
