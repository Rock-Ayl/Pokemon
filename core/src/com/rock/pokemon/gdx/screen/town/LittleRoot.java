package com.rock.pokemon.gdx.screen.town;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.common.Settings;
import com.rock.pokemon.gdx.controller.OptionBoxController;
import com.rock.pokemon.gdx.controller.PersonController;
import com.rock.pokemon.gdx.enums.PersonEnum;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.people.Person;
import com.rock.pokemon.gdx.screen.renderer.WorldRenderer;
import com.rock.pokemon.gdx.ui.box.DialogueBox;
import com.rock.pokemon.gdx.ui.box.OptionBox;

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

    //屏幕,我们这里使用[FitViewport],让相机等比缩放
    private FitViewport viewport;

    //舞台
    private Stage uiStage;

    //主表格
    private Table rootTable;

    //对话框
    private DialogueBox dialogueBox;

    //可选项框
    private OptionBox optionBox;

    /**
     * 控制器
     */

    //所有控制器
    private InputMultiplexer inputMultiplexer;

    //人物 控制器
    private PersonController personController;

    //可选项框 控制器
    private OptionBoxController optionBoxController;

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
     * 初始化/进入未白镇
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
        this.world = new World(this.game.getAssetManager());

        //初始化世界渲染器
        this.worldRenderer = new WorldRenderer(this.world);

        //一个普通NPC
        this.world.addPerson(new Person(PersonEnum.RUBE, this.world, 10, 5, this.game.getAssetManager(), this.game.getSoundManager()));

        /**
         * 主角相关
         */

        //初始化主角
        this.adventurer = new Person(PersonEnum.RUBE, this.world, 7, 3, this.game.getAssetManager(), this.game.getSoundManager());

        /**
         * UI
         */

        //初始化[FitViewport]屏幕,保证游戏横纵比,并使用相机(Fit效果拖动时会有问题)
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //初始化舞台
        this.uiStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        //初始化主表格
        rootTable = new Table();
        //该设置仅用于主表格
        rootTable.setFillParent(true);
        //开启debug,默认不开启
        rootTable.setDebug(false);
        //是否显示本UI
        rootTable.setVisible(true);

        //舞台加入主表格
        uiStage.addActor(rootTable);

        /**
         * 创建一个可选项框
         */

        this.optionBox = new OptionBox(this.game.getSkin());
        this.optionBox.addOption("图鉴");
        this.optionBox.addOption("精灵");
        this.optionBox.addOption("背包");
        //this.optionBox.addOption("领航员");
        //this.optionBox.addOption("主角");
        this.optionBox.addOption("保存");
        this.optionBox.addOption("设置");
        this.optionBox.addOption("退出");
        this.optionBox.setVisible(true);
        this.rootTable.add(this.optionBox)
                .expand()
                .align(Align.right)
                //和边界的间隙
                .padRight(Settings.SCALE)
                //换行
                .row();

        /**
         * 创建一个对话框
         */

        //初始化一个对话框
        this.dialogueBox = new DialogueBox(this.game.getSkin());
        //随便根据编号弄一个文本,组装到对话框播放动画
        this.dialogueBox.animateText(this.game.getTextMap().get(4));
        //是否绘制这个对框框,默认是
        this.dialogueBox.setVisible(true);
        //将对话框放在主表格的下方
        this.rootTable.add(this.dialogueBox)
                .expand()
                //横坐标成长到最大(拉伸)
                .growX()
                //高大概是两格
                .height(Settings.SCALE_TILE_SIZE * 2.5F)
                //向下对齐
                .align(Align.bottom)
                //和边界的间隙,左右小点,下面大点
                .padLeft(Settings.SCALE)
                .padRight(Settings.SCALE)
                .padBottom(Settings.SCALE * 3);

        /**
         * 控制器
         */

        //初始化所有控制器
        this.inputMultiplexer = new InputMultiplexer();
        //初始化可选项框控制器
        this.optionBoxController = new OptionBoxController(this.optionBox);
        //初始化输入监听,控制主角的行动
        this.personController = new PersonController(this.adventurer);

        //组装至所有控制器
        this.inputMultiplexer.addProcessor(personController);
        this.inputMultiplexer.addProcessor(optionBoxController);

    }

    @Override
    public void show() {
        //当显示画面时,立即播放音乐
        this.music.play();
        //当显示画面时,开始监控键盘控制
        Gdx.input.setInputProcessor(inputMultiplexer);
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
        this.viewport.getCamera().position.x = (this.adventurer.getWorldX() + 0.5F) * Settings.SCALE_TILE_SIZE;
        this.viewport.getCamera().position.y = (this.adventurer.getWorldY() + 0.5F) * Settings.SCALE_TILE_SIZE;
        //更新当前屏幕的宽高,如果不这么做,当拖拽窗口时,将会拉伸屏幕,破坏屏幕比例,顺道更新相机了
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //渲染时使用相机
        this.game.getBatch().setProjectionMatrix(this.viewport.getCamera().combined);

        /**
         * 对话框操作
         */

        //如果按回车 and 对话完毕
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && this.dialogueBox.isFinished()) {
            //对话框不可见
            this.dialogueBox.setVisible(false);
        }

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
