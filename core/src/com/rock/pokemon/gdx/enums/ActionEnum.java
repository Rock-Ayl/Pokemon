package com.rock.pokemon.gdx.enums;

/**
 * 人物动作枚举
 *
 * @Author ayl
 * @Date 2022-10-15
 */
public enum ActionEnum {

    STAND("stand", "站立"),
    WALK("walk", "走路"),
    RUN("run", "跑步"),
    CYCLING("cycling", "骑自行车"),
    SURFING("surfing", "冲浪"),

    ;

    //路径
    private String path;
    //中文名
    private String zhName;

    ActionEnum(String path, String zhName) {
        this.path = path;
        this.zhName = zhName;
    }

    /**
     * get set 方法
     *
     * @return
     */

    public String getPath() {
        return path;
    }

}
