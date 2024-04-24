package com.rock.pokemon.gdx.enums;

import lombok.Getter;

/**
 * 游戏用户行动 枚举
 *
 * @Author ayl
 * @Date 2024-04-25
 */
@Getter
public enum GameUserActionStatusEnum {

    NORMAL("normal", "正常状态,可以控制角色,操作包括:[移动][打开菜单][对话]"),
    EVENTING("eventing", "事件状态,无法控制角色"),

    ;

    //code
    private String code;
    //备注
    private String remark;

    GameUserActionStatusEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

}
