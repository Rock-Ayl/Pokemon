package com.rock.pockmon.gdx.enums;

/**
 * 站立枚举,一共有四个,东南西北
 */
public enum StandEnum {

    EAST("east.png"),
    SOUTH("south.png"),
    west("west.png"),
    NORTH("north.png"),

    ;

    //文件名
    private String fileName;

    StandEnum(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
