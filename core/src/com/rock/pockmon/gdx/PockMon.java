package com.rock.pockmon.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rock.pockmon.gdx.enums.PersonEnum;
import com.rock.pockmon.gdx.model.people.Person;
import com.rock.pockmon.gdx.screen.MainMenu;

/**
 * 游戏入口
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public class PockMon extends Game {

    //绘制图形
    public SpriteBatch batch;
    //字体
    public BitmapFont font;
    //主角
    public Person adventurer;

    /**
     * 初始化
     */
    public void create() {
        //初始化一个SpriteBatch,游戏中只有一个,直到游戏结束后销毁
        this.batch = new SpriteBatch();
        //使用默认的字体
        this.font = new BitmapFont();
        //todo 初始化主角,先使用男主角吧
        this.adventurer = new Person(PersonEnum.RUBE);
        //进入主目录
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
        batch.dispose();
        font.dispose();
    }

}
