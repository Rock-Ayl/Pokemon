package com.rock.pokemon.gdx.model.map.config;

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
    private Map<String, SoundConfig> soundMap;

    /**
     * 音效 配置类实体
     */
    @Getter
    @Setter
    public static class SoundConfig {

        //音效备注
        private String remark;

        //音效地址
        private String soundPath;

        //音效间隔时间
        private Long soundTimeInterval;

    }

}