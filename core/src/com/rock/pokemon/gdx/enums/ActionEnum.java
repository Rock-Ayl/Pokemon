package com.rock.pokemon.gdx.enums;

import lombok.Getter;

/**
 * 人物动作枚举
 *
 * @Author ayl
 * @Date 2022-10-15
 */
@Getter
public enum ActionEnum {

    STAND("stand", "站立"),
    WALK("walk", "走路"),

    ;

    //路径
    private String path;
    //中文名
    private String zhName;

    ActionEnum(String path, String zhName) {
        this.path = path;
        this.zhName = zhName;
    }

}
