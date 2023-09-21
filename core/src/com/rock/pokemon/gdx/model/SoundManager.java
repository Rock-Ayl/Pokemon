package com.rock.pokemon.gdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.rock.pokemon.gdx.common.FilePaths;

/**
 * 通用音效管理器
 *
 * @Author ayl
 * @Date 2022-10-19
 */
public class SoundManager {

    /**
     * 撞墙音效
     */

    //音效-撞墙
    private Sound NO_WALK;

    //音效-撞墙-上次发出音效的时间(时间戳)
    private long NO_WALK_LAST_SOUND_TIME;

    //音效-撞墙-音效之间的间隔 毫秒
    private static final long NO_WALK_SOUND_TIME_INTERVAL = 500L;

    /**
     * 初始化
     */

    //初始化音效管理器
    public SoundManager() {
        //初始化撞墙
        this.NO_WALK = Gdx.audio.newSound(Gdx.files.internal(FilePaths.SOUND_NO_WALK));
    }

    /**
     * 人物发出撞墙的音效
     */
    public void playNoWalk() {
        //当前时间戳
        long thisTime = System.currentTimeMillis();
        //如果距离上次发出音效时间没有过间隔期
        if (thisTime - NO_WALK_SOUND_TIME_INTERVAL < NO_WALK_LAST_SOUND_TIME) {
            //不发出音效
            return;
        }
        //发出撞墙音效
        this.NO_WALK.play();
        //记录发出音效的时间
        NO_WALK_LAST_SOUND_TIME = thisTime;
    }

}
