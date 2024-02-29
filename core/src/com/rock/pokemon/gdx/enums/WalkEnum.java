package com.rock.pokemon.gdx.enums;

import lombok.Getter;

/**
 * 人物走路枚举
 *
 * @Author ayl
 * @Date 2022-10-15
 */
@Getter
public enum WalkEnum {

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

    WalkEnum(String path, String zhName) {
        this.path = path;
        this.zhName = zhName;
    }

}
