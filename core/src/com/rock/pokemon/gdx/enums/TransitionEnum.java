package com.rock.pokemon.gdx.enums;

import lombok.Getter;

/**
 * 渐变枚举
 */
@Getter
public enum TransitionEnum {

    ZERO(0, "-"),
    ONE(1, "-"),
    TWO(2, "-"),
    THREE(3, "-"),
    FOUR(4, "-"),
    FIVE(5, "-"),
    SIX(6, "-"),
    SEVEN(7, "-"),
    EIGHT(8, "-"),
    NINE(9, "-"),
    TEN(10, "-"),
    ONE_ONE(11, "-"),
    ONE_TWO(12, "-"),

    ;

    //编号
    private int number;
    //备注
    private String remark;

    TransitionEnum(int number, String remark) {
        this.number = number;
        this.remark = remark;
    }

}
