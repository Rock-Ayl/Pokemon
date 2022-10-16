package com.rock.pockmon.gdx.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rock.pockmon.gdx.PockMon;
import com.rock.pockmon.gdx.common.FilePaths;
import com.rock.pockmon.gdx.common.Settings;
import com.rock.pockmon.gdx.controller.PersonMoveController;
import com.rock.pockmon.gdx.model.OurCamera;
import com.rock.pockmon.gdx.model.map.TileMap;
import com.rock.pockmon.gdx.util.GdxUtils;

/**
 * 未白镇(开局城镇)
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class LittleRoot implements Screen {

    //游戏对象
    private final PockMon game;

    //相机
    private OurCamera ourCamera;

    //背景音乐
    private Music music;

    //移动控制器
    private PersonMoveController moveController;

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

        //初始化相机
        this.ourCamera = new OurCamera();

        //初始化未白镇背景音乐音乐
        this.music = Gdx.audio.newMusic(Gdx.files.internal(FilePaths.LITTLE_ROOT_BGM));
        //音乐循环播放
        this.music.setLooping(true);

        //初始化地图网格
        this.tileMap = new TileMap(10, 10);

        //初始化移动控制,指定主角为可移动的角色
        this.moveController = new PersonMoveController(this.game, this.tileMap);

    }

    @Override
    public void show() {

        //当显示画面时,立即播放音乐
        this.music.play();

        //当显示画面时,开始监控键盘控制
        Gdx.input.setInputProcessor(moveController);

    }

    @Override
    public void render(float delta) {

        //黑幕
        ScreenUtils.clear(Color.BLACK);

        //先根据主角的坐标,计算出相机的位置(需要加0.5个身为,让相机完全到最中心)
        this.ourCamera.update(this.game.adventurer.x + 0.5F, this.game.adventurer.y + 0.5F);
        //根据相机的坐标,再计算出世界的起始点
        float worldStartX = Gdx.graphics.getWidth() / 2 - this.ourCamera.getX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight() / 2 - this.ourCamera.getY() * Settings.SCALED_TILE_SIZE;

        //开始渲染
        this.game.batch.begin();

        //根据世界起点,渲染地图网格
        GdxUtils.drawTileMap(this.game.batch, this.tileMap, worldStartX, worldStartY);

        //根据世界起点,渲染主角
        GdxUtils.drawPerson(this.game.batch, this.game.adventurer, worldStartX, worldStartY);

        //结束渲染
        this.game.batch.end();

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
