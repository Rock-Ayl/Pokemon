package com.rock.pockmon.gdx.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rock.pockmon.gdx.PockMon;
import com.rock.pockmon.gdx.common.FilePaths;
import com.rock.pockmon.gdx.common.Settings;
import com.rock.pockmon.gdx.controller.InputController;
import com.rock.pockmon.gdx.model.Camera;
import com.rock.pockmon.gdx.model.map.Tile;
import com.rock.pockmon.gdx.model.map.TileMap;

/**
 * 未白镇(开局城镇)
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class LittleRoot implements Screen {

    //游戏对象
    private final PockMon game;

    //背景音乐
    private Music music;

    //一个自定义的相机
    private Camera camera;

    //输入控制器
    private InputController inputController;

    //地图网格
    private TileMap tileMap;

    /**
     * 初始化未白镇
     *
     * @param pockMon 游戏对象
     */
    public LittleRoot(final PockMon pockMon) {

        //记录游戏对象
        this.game = pockMon;

        //初始化自定义相机
        this.camera = new Camera();

        //初始化未白镇背景音乐音乐
        this.music = Gdx.audio.newMusic(Gdx.files.internal(FilePaths.LITTLE_ROOT_BGM));
        //音乐循环播放
        this.music.setLooping(true);

        //初始化地图网格
        this.tileMap = new TileMap(15, 15);

        //初始化输入监听,控制主角的行动
        this.inputController = new InputController(this.game, this.tileMap);

    }

    @Override
    public void show() {

        //当显示画面时,立即播放音乐
        this.music.play();

        //当显示画面时,开始监控键盘控制
        Gdx.input.setInputProcessor(inputController);

    }

    @Override
    public void render(float delta) {

        //根据主角坐标更新相机坐标
        this.camera.update(
                this.game.getAdventurer().getWorldX() + 0.5F,
                this.game.getAdventurer().getWorldY() + 0.5F)
        ;

        //黑幕
        ScreenUtils.clear(Color.BLACK);

        //计算出世界的真实起始点,让世界始终以主角(相机)为中心
        float worldStartX = Gdx.graphics.getWidth() / 2 - this.camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight() / 2 - this.camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        //每帧更新输入控制器
        this.inputController.update(delta);
        //每帧更新主角
        this.game.getAdventurer().update(this.tileMap, delta);

        //开始渲染 地图、人物
        this.game.getBatch().begin();

        //循环1
        for (int x = 0; x < tileMap.getWidth(); x++) {
            //循环2
            for (int y = 0; y < tileMap.getHeight(); y++) {
                //当前地图块对象
                Tile tile = tileMap.getTileMap()[x][y];
                //渲染
                this.game.getBatch().draw(
                        //图片
                        tile.getSprite(this.game.getAssetManager()),
                        //真实坐标 + 当前坐标 * 网格倍率
                        worldStartX + tile.getX() * Settings.SCALED_TILE_SIZE,
                        worldStartY + tile.getY() * Settings.SCALED_TILE_SIZE,
                        //使用地图块的宽高 * 网格倍率
                        tile.getWidth() * Settings.SCALED_TILE_SIZE,
                        tile.getHeight() * Settings.SCALED_TILE_SIZE
                );
            }
        }

        //根据世界起点,渲染主角
        this.game.getBatch().draw(
                //图片
                this.game.getAdventurer().getSprite(),
                //真实坐标 + 当前坐标 * 网格倍率
                worldStartX + this.game.getAdventurer().getWorldX() * Settings.SCALED_TILE_SIZE,
                worldStartY + this.game.getAdventurer().getWorldY() * Settings.SCALED_TILE_SIZE,
                //使用地图块的宽高 * 网格倍率
                this.game.getAdventurer().getWidth() * Settings.SCALED_TILE_SIZE,
                this.game.getAdventurer().getHeight() * Settings.SCALED_TILE_SIZE
        );

        //结束渲染
        this.game.getBatch().end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.music.dispose();
    }

}
