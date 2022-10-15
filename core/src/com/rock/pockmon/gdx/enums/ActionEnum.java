package com.rock.pockmon.gdx.enums;

/**
 * 人物动作枚举
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public enum ActionEnum {

    stand("stand", "站立"),
    walk("walk", "走路"),

    ;

    //路径
    private String path;
    //中文名
    private String zhName;

    ActionEnum(String path, String zhName) {
        this.path = path;
        this.zhName = zhName;
    }

    public String getPath() {
        return path;
    }

}
