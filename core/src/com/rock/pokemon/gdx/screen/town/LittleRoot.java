package com.rock.pokemon.gdx.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.controller.InputController;
import com.rock.pokemon.gdx.enums.PersonEnum;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.people.Person;
import com.rock.pokemon.gdx.screen.renderer.WorldRenderer;
import com.rock.pokemon.gdx.ui.DialogueBox;

/**
 * 未白镇(开局城镇)
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class LittleRoot implements Screen {

    //游戏对象
    private final Pokemon game;

    //背景音乐
    private Music music;

    /**
     * UI
     */

    //相机
    private OrthographicCamera camera;

    //屏幕,我们这里使用[ExtendViewport],让相机等比缩放,同时拖动屏幕也不会有问题
    private ExtendViewport viewport;

    //舞台
    private Stage stage;

    //主表格
    private Table table;

    /**
     * 控制器
     */

    //输入控制器
    private InputController inputController;

    /**
     * 世界
     */

    //未白镇的世界实体
    private World world;

    //未白镇的世界渲染器
    private WorldRenderer worldRenderer;

    //主角实体
    private Person adventurer;

    /**
     * 初始化未白镇
     *
     * @param pokemon 游戏对象
     */
    public LittleRoot(final Pokemon pokemon) {

        /**
         * 基本
         */

        //记录游戏对象
        this.game = pokemon;

        /**
         * 音乐
         */

        //初始化未白镇背景音乐音乐
        this.music = Gdx.audio.newMusic(Gdx.files.internal(FilePaths.LITTLE_ROOT_BGM));
        //音乐循环播放
        this.music.setLooping(true);

        /**
         * 世界
         */

        //初始化世界
        this.world = new World(this.game.getAssetManager(), 15, 10);

        //初始化世界渲染器
        this.worldRenderer = new WorldRenderer(this.game.getAssetManager(), this.world);

        /**
         * 主角相关
         */

        //初始化主角
        this.adventurer = new Person(PersonEnum.RUBE, this.world, 7, 3, this.game.getAssetManager(), this.game.getSoundManager());

        //初始化输入监听,控制主角的行动
        this.inputController = new InputController(this.adventurer);

        /**
         * UI
         */

        //初始化相机
        this.camera = new OrthographicCamera();

        //初始化[ExtendViewport]屏幕,保证游戏横纵比,并使用相机(Fit效果拖动时会有问题)
        this.viewport = new ExtendViewport(this.game.getWindowWidth(), this.game.getWindowHeight(), this.camera);

        //初始化舞台
        this.stage = new Stage(this.viewport);

        //初始化主表格
        table = new Table();
        //该设置仅用于主表格
        table.setFillParent(true);
        //开启debug table.setDebug(true);

        //舞台加入主表格
        stage.addActor(table);

        //初始化一个对话框
        DialogueBox dialogueBox = new DialogueBox(this.game.getSkin());
        //随便根据编号弄一个文本
        dialogueBox.setText(this.game.getTextMap().get(2));
        //将对话框放在主表格的下方
        this.table.add(dialogueBox)
                .expand()
                .align(Align.bottom);

    }

    @Override
    public void show() {

        //当显示画面时,立即播放音乐
        this.music.play();

        //当显示画面时,开始监控键盘控制
        Gdx.input.setInputProcessor(inputController);

    }

    /**
     * 每帧处理
     *
     * @param delta 帧时间
     */
    @Override
    public void render(float delta) {

        /**
         * 相机
         */

        //每帧根据主角坐标,更新相机坐标
        this.camera.position.x = (this.adventurer.getWorldX() + 0.5F) * this.game.getScaledTileSize();
        this.camera.position.y = (this.adventurer.getWorldY() + 0.5F) * this.game.getScaledTileSize();

        //更新相机
        this.camera.update();

        //渲染时使用相机
        this.game.getBatch().setProjectionMatrix(this.camera.combined);

        /**
         * 渲染世界及更新
         */

        //黑幕
        ScreenUtils.clear(Color.BLACK);

        //每帧更新输入控制器
        this.inputController.update(delta);

        //每帧更新世界
        this.world.update(delta);

        //开始渲染 地图、人物
        this.game.getBatch().begin();

        //渲染整个世界
        this.worldRenderer.render(this.game);

        //结束渲染
        this.game.getBatch().end();

        /**
         * 渲染UI
         */

        this.stage.act(delta);
        this.stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        //修改当前游戏屏幕尺寸
        this.game.updateWindows(width, height);

        //更新当前屏幕宽高和更新相机
        this.stage.getViewport().update(width, height);

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
        this.stage.dispose();
    }

}
