package com.rock.pockmon.gdx.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rock.pockmon.gdx.PockMon;
import com.rock.pockmon.gdx.common.FilePaths;
import com.rock.pockmon.gdx.common.Settings;

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

        //初始化主角开始出现在城镇坐标
        this.game.adventurer.x = Settings.WINDOW_WIDTH / 2;
        this.game.adventurer.y = Settings.WINDOW_HEIGHT / 2;

    }

    @Override
    public void show() {

        //当前显示画面时立即播放音乐
        this.rainMusic.play();

    }

    @Override
    public void render(float delta) {

        //屏幕渲染成蓝色
        ScreenUtils.clear(Color.BLUE);

        //按照惯例,每帧先更新一次相机
        this.camera.update();
        //告诉游戏渲染使用相机
        this.game.batch.setProjectionMatrix(camera.combined);

        //开始渲染
        game.batch.begin();

        //渲染主角
        game.batch.draw(this.game.adventurer.getCurrentImage(), this.game.adventurer.x, this.game.adventurer.y, this.game.adventurer.width, this.game.adventurer.height);

        //结束渲染
        game.batch.end();

        //主角移动判定,同一次移动只能一个方向,按照绿宝石的手感判定, 上优先级最高,下其次,左第三,右的判定最低
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //计算移动坐标
            this.game.adventurer.y += this.game.adventurer.moveSpeed * Gdx.graphics.getDeltaTime();
            //限制人物不能移出屏幕边界
            this.game.adventurer.y = Math.min(this.game.adventurer.y, Settings.WINDOW_HEIGHT - this.game.adventurer.height);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //计算移动坐标
            this.game.adventurer.y -= this.game.adventurer.moveSpeed * Gdx.graphics.getDeltaTime();
            //限制人物不能移出屏幕边界
            this.game.adventurer.y = Math.max(this.game.adventurer.y, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //计算移动坐标
            this.game.adventurer.x -= this.game.adventurer.moveSpeed * Gdx.graphics.getDeltaTime();
            //限制人物不能移出屏幕边界
            this.game.adventurer.x = Math.max(this.game.adventurer.x, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //计算移动坐标
            this.game.adventurer.x += this.game.adventurer.moveSpeed * Gdx.graphics.getDeltaTime();
            //限制人物不能移出屏幕边界
            this.game.adventurer.x = Math.min(this.game.adventurer.x, Settings.WINDOW_WIDTH - this.game.adventurer.width);
        }


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
