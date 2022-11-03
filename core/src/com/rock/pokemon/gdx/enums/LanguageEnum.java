package com.rock.pokemon.gdx.enums;

/**
 * 语言枚举
 */
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

    /**
     * 以下为 get set 方法
     *
     * @return
     */

    public String getPath() {
        return path;
    }

}
