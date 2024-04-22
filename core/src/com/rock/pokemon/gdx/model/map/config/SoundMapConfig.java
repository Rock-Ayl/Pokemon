package com.rock.pokemon.gdx.model.map.config;

import com.badlogic.gdx.audio.Sound;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 音效 配置类实体
 *
 * @Author ayl
 * @Date 2024-04-22
 */
@Getter
@Setter
public class SoundMapConfig {

    //配置名称
    private String name;

    //音效map
    private Map<String, MySound> soundMap;

    /**
     * 音效 配置类实体
     */
    @Getter
    @Setter
    public static class MySound {

        /**
         * 配置文件参数,这些参数均来自配置文件
         */

        //音效备注
        private String remark;

        //音效地址
        private String soundPath;

        //音效间隔时间
        private Long soundTimeInterval;

        /**
         * 非配置文件参数,这些参数来着后续初始化或计算
         */

        //音效对象,需要根据path读取
        private Sound sound;

        //该音效上次播放时间(毫秒时间戳)
        private Long lastPlayTime;

        //音效id,初始化时候会根据map的覆盖
        private String soundId;

    }

}