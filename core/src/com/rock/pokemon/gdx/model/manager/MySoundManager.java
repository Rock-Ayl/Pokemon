package com.rock.pokemon.gdx.model.manager;

import com.badlogic.gdx.Gdx;
import com.rock.pokemon.gdx.model.map.config.SoundMapConfig;
import com.rock.pokemon.gdx.util.FastJsonExtraUtils;

import java.util.Map;

/**
 * 通用音效管理器
 *
 * @Author ayl
 * @Date 2022-10-19
 */
public class MySoundManager {

    //音效配置对象
    private SoundMapConfig soundMapConfig;

    /**
     * 初始化音效管理器
     *
     * @param soundMapConfig 音效配置
     */
    public MySoundManager(SoundMapConfig soundMapConfig) {
        //克隆一下
        this.soundMapConfig = FastJsonExtraUtils.deepClone(soundMapConfig, SoundMapConfig.class);
        //循环配置
        for (Map.Entry<String, SoundMapConfig.MySound> entry : this.soundMapConfig.getSoundMap().entrySet()) {
            //获取soundId
            String soundId = entry.getKey();
            //获取配置
            SoundMapConfig.MySound mySound = entry.getValue();
            //初始化音效
            mySound.setSound(Gdx.audio.newSound(Gdx.files.internal(mySound.getSoundPath())));
            //覆盖id
            mySound.setSoundId(soundId);
        }
    }

    /**
     * 根据 音效id 播放音效
     *
     * @param soundId 音效id
     */
    public void play(String soundId) {
        //获取对应音效对象
        SoundMapConfig.MySound mySound = soundMapConfig.getSoundMap().get(soundId);

        /**
         * 播放音效目前有两种情况
         */

        //如果有间隔时间
        if (mySound.getSoundTimeInterval() > 0L) {

            /**
             * 情况1 有间隔 比如撞墙
             */

            //当前时间戳
            long thisTime = System.currentTimeMillis();
            //如果距离上次发出音效时间没有过间隔期
            if (mySound.getLastPlayTime() != null && thisTime - mySound.getSoundTimeInterval() < mySound.getLastPlayTime()) {
                //不发出音效
                return;
            }
            //播放
            mySound.getSound().play();
            //记录发出音效的时间
            mySound.setLastPlayTime(thisTime);
        } else {

            /**
             * 情况2 无间隔 比如菜单开启、关闭、移动
             */

            //直接播放
            mySound.getSound().play();
        }
    }

}
