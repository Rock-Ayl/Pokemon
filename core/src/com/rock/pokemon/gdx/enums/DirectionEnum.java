package com.rock.pokemon.gdx.enums;

/**
 * 方向枚举
 */
public enum DirectionEnum {

    NORTH(0, 1, "north", "北"),
    EAST(1, 0, "east", "东"),
    SOUTH(0, -1, "south", "南"),
    WEST(-1, 0, "west", "西"),

    ;

    //该方向每次移动坐标
    private int dx, dy;

    //名字
    private String name;
    //中文名
    private String zhName;

    DirectionEnum(int dx, int dy, String name, String zhName) {
        this.dx = dx;
        this.dy = dy;
        this.name = name;
        this.zhName = zhName;
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
