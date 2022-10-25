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

    //音效-撞墙
    private Sound soundNoWalk;

    //初始化音效管理器
    public SoundManager() {

        /**
         * 初始化通用音效
         */

        this.soundNoWalk = Gdx.audio.newSound(Gdx.files.internal(FilePaths.SOUND_NO_WALK));

    }

    /**
     * 以下是 get set 方法
     */

    public Sound getSoundNoWalk() {
        return soundNoWalk;
    }

}
