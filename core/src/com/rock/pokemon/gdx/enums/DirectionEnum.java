package com.rock.pokemon.gdx.enums;

import com.badlogic.gdx.Input;

/**
 * 方向枚举
 */
public enum DirectionEnum {

    NORTH(0, 1, Input.Keys.UP, "north", "北"),
    EAST(1, 0, Input.Keys.RIGHT, "east", "东"),
    SOUTH(0, -1, Input.Keys.DOWN, "south", "南"),
    WEST(-1, 0, Input.Keys.LEFT, "west", "西"),

    ;

    //该方向每次移动坐标
    private int dx, dy;

    //该方向对应的输入按键编码(暂时用枚举)
    private int keycode;

    //名字
    private String name;
    //中文名
    private String zhName;

    DirectionEnum(int dx, int dy, int keycode, String name, String zhName) {
        this.dx = dx;
        this.dy = dy;
        this.keycode = keycode;
        this.name = name;
        this.zhName = zhName;
    }

    /**
     * 根据方向输入,解析方向枚举
     *
     * @param keycode 案件编码
     * @return
     */
    public static DirectionEnum parseByKeycode(int keycode) {
        //循环
        for (DirectionEnum value : DirectionEnum.values()) {
            //如果对应上
            if (value.keycode == keycode) {
                //返回
                return value;
            }
        }
        //默认,南
        return SOUTH;
    }

    /**
     * 以下是 get set 方法
     *
     * @return
     */

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public String getName() {
        return name;
    }

}
