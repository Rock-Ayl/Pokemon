package com.rock.pockmon.gdx.enums;

/**
 * 语言枚举
 */
public enum LanguageEnum {

    SIMPLIFIED_CHINESE("Simplified Chinese", "简体中文(默认)"),
    ENGLISH("English", "英语"),

    ;

    //编码
    private String code;
    //中文
    private String zh;

    LanguageEnum(String code, String zh) {
        this.code = code;
        this.zh = zh;
    }

    public String getCode() {
        return code;
    }

    public String getZh() {
        return zh;
    }

}
