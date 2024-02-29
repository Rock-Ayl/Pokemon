package com.rock.pokemon.gdx.enums;

import lombok.Getter;

/**
 * 语言枚举
 */
@Getter
public enum LanguageEnum {

    SIMPLIFIED_CHINESE("Simplified Chinese", "zh", "简体中文(默认)"),
    ENGLISH("English", "en", "英语"),

    ;

    //编码
    private String code;
    //路径
    private String path;
    //中文
    private String zh;

    LanguageEnum(String code, String path, String zh) {
        this.code = code;
        this.path = path;
        this.zh = zh;
    }

}
