package com.rock.pokemon.gdx.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.controller.DialogueAndOptionBoxController;
import com.rock.pokemon.gdx.controller.PersonController;
import com.rock.pokemon.gdx.model.map.Person;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.mapConfig.NpcMapConfig;
import com.rock.pokemon.gdx.screen.renderer.WorldRenderer;
import com.rock.pokemon.gdx.ui.box.DialogueAndOptionBox;
import lombok.Getter;

/**
 * 通用世界屏幕
 *
 * @Author ayl
 * @Date 2022-10-15
 */
@Getter
public class LittleRoot implements Screen {

    //游戏对象
    private final Pokemon game;

    //背景音乐
    private Music music;

    /**
     * UI
     */

    //屏幕,我们这里使用[FitViewport],让相机等比缩放
    private FitViewport viewport;

    //舞台
    private Stage uiStage;

    //谈话table
    private Table talkTable;

    //对话框+可选项盒子
    private DialogueAndOptionBox dialogueAndOptionBox;

    /**
     * 控制器
     */

    //所有控制器
    private InputMultiplexer inputMultiplexer;

    //人物 控制器
    private PersonController personController;

    //对话框 + 可选项框 输入控制器
    private DialogueAndOptionBoxController dialogueAndOptionBoxController;

    /**
     * 世界
     */

    //世界实体
    private World world;

    //世界渲染器
    private WorldRenderer worldRenderer;

    //主角实体
    private Person adventurer;

    /**
     * 初始化世界、主角进入世界指定位置
     *
     * @param pokemon            游戏对象
     * @param worldMapConfigPath 世界配置路径
     * @param adventurerX        主角要进入本世界的初始坐标 x
     * @param adventurerY        主角要进入本世界的初始坐标 y
     */
    public LittleRoot(final Pokemon pokemon, String worldMapConfigPath, int adventurerX, int adventurerY) {

        /**
         * 基本
         */

        //记录游戏对象
        this.game = pokemon;

        /**
         * 音乐
         */

        //固定背景音乐
        this.music = Gdx.audio.newMusic(Gdx.files.internal(FilePaths.LITTLE_ROOT_BGM));
        //音乐循环播放
        this.music.setLooping(true);

        /**
         * 生成世界
         */

        //初始化世界
        this.world = new World(this.game, this, worldMapConfigPath);
        //初始化世界渲染器
        this.worldRenderer = new WorldRenderer(this.world);

        /**
         * 生成主角,并加入世界
         */

        //获取npc配置文件
        NpcMapConfig adventurerNpcMapConfig = this.game.getAssetManager().get(FilePaths.MAP_CONFIG_PATH_OF_NPC, NpcMapConfig.class);
        //初始化主角
        this.adventurer = new Person(adventurerNpcMapConfig.getNpcMap().get(this.game.getSaveManager().getAdventurerNpcMapConfigName()), this.world, adventurerX, adventurerY, this.game);

        /**
         * UI
         */

        //初始化[FitViewport]屏幕,保证游戏横纵比,并使用相机(Fit效果拖动时会有问题)
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //初始化舞台
        this.uiStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        //初始化谈话table
        this.talkTable = new Table();
        //该设置仅用于谈话table
        this.talkTable.setFillParent(true);
        //开启debug,默认不开启
        this.talkTable.setDebug(false);
        //是否显示本UI
        this.talkTable.setVisible(true);

        //谈话table加入舞台
        this.uiStage.addActor(this.talkTable);

        //初始化对应ui盒子
        this.dialogueAndOptionBox = new DialogueAndOptionBox(this.game, this.talkTable);

        /**
         * 控制器
         */

        //初始化所有控制器
        this.inputMultiplexer = new InputMultiplexer();

        //初始化输入监听,控制主角的行动
        this.personController = new PersonController(this.adventurer);
        //初始化对话框 + 可选项框 输入控制器,控制UI
        this.dialogueAndOptionBoxController = new DialogueAndOptionBoxController(this.dialogueAndOptionBox);

        //组装至所有控制器
        this.inputMultiplexer.addProcessor(this.personController);
        this.inputMultiplexer.addProcessor(this.dialogueAndOptionBoxController);

    }

    @Override
    public void show() {
        //当显示画面时,立即播放音乐
        this.music.play();
        //当显示画面时,开始监控键盘控制
        Gdx.input.setInputProcessor(this.inputMultiplexer);
    }

    /**
     * 每帧处理
     *
     * @param delta 帧时间
     */
    @Override
    public void render(float delta) {

        //如果按回车
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            //优先计算加速
            delta = delta * Settings.SYSTEM_SPEED_MULTIPLIER;
        }

        /**
         * 相机
         */

        //每帧根据主角坐标,更新相机坐标
        this.viewport.getCamera().position.x = (this.adventurer.getWorldX() + 0.5F) * Settings.SCALE_TILE_SIZE;
        this.viewport.getCamera().position.y = (this.adventurer.getWorldY() + 0.5F) * Settings.SCALE_TILE_SIZE;
        //更新当前屏幕的宽高,如果不这么做,当拖拽窗口时,将会拉伸屏幕,破坏屏幕比例,顺道更新相机了
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //渲染时使用相机
        this.game.getBatch().setProjectionMatrix(this.viewport.getCamera().combined);

        /**
         * 渲染世界及更新
         */

        //黑幕
        ScreenUtils.clear(Color.BLACK);

        //每帧更新输入控制器
        this.personController.update(delta);
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

        //更新舞台帧
        this.uiStage.act(delta);
        //渲染整个舞台
        this.uiStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        //更新舞台
        this.uiStage.getViewport().update(width, height, true);
        //更新屏幕
        this.viewport.update(width, height);
    }

    @Override
    public void dispose() {
        this.music.dispose();
        this.uiStage.dispose();
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

}
