package com.rock.pockmon.gdx.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rock.pockmon.gdx.PockMon;
import com.rock.pockmon.gdx.common.FilePaths;
import com.rock.pockmon.gdx.common.Settings;
import com.rock.pockmon.gdx.controller.PersonMoveController;

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
    private OrthographicCamera camera;

    //背景音乐
    private Music rainMusic;

    //移动控制器
    private PersonMoveController moveController;

    /**
     * 初始化未白镇
     *
     * @param pockMon 游戏对象
     */
    public LittleRoot(final PockMon pockMon) {

        //记录游戏对象
        this.game = pockMon;

        //初始化相机
        this.camera = new OrthographicCamera();
        //设置相机尺寸
        this.camera.setToOrtho(false, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);

        //初始化未白镇背景音乐音乐
        this.rainMusic = Gdx.audio.newMusic(Gdx.files.internal(FilePaths.LITTLE_ROOT_BGM));
        //音乐循环播放
        this.rainMusic.setLooping(true);

        //初始化移动控制,指定主角为可移动的角色
        this.moveController = new PersonMoveController(this.game.adventurer);

        //初始化主角开始出现在城镇坐标
        this.game.adventurer.x = 0;
        this.game.adventurer.y = 0;

    }

    @Override
    public void show() {

        //当显示画面时,立即播放音乐
        this.rainMusic.play();

        //当显示画面时,开始监控键盘控制
        Gdx.input.setInputProcessor(moveController);

    }

    @Override
    public void render(float delta) {

        //蓝幕
        ScreenUtils.clear(Color.BLUE);

        //按照惯例,每帧先更新一次相机
        this.camera.update();
        //告诉游戏渲染使用相机
        this.game.batch.setProjectionMatrix(camera.combined);

        //开始渲染
        game.batch.begin();

        //渲染主角
        game.batch.draw(
                //图片
                this.game.adventurer.getCurrentImage(),
                //当前坐标*网格倍率
                this.game.adventurer.x * Settings.SCALED_TILE_SIZE,
                this.game.adventurer.y * Settings.SCALED_TILE_SIZE,
                //大小同样视为网格倍率,人物普遍高度是1格半
                Settings.SCALED_TILE_SIZE * 1.0F,
                Settings.SCALED_TILE_SIZE * 1.5F
        );

        //结束渲染
        game.batch.end();

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

    }

}
