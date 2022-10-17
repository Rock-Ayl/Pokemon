package com.rock.pockmon.gdx.enums;

/**
 * 方向枚举
 */
public enum DirectionEnum {

    NORTH(0, 1, "north.png"),
    EAST(1, 0, "east.png"),
    SOUTH(0, -1, "south.png"),
    WEST(-1, 0, "west.png"),

    ;

    //该方向每次移动坐标
    private int dx, dy;

    //文件名
    private String fileName;

    DirectionEnum(int dx, int dy, String fileName) {
        this.dx = dx;
        this.dy = dy;
        this.fileName = fileName;
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

    public String getFileName() {
        return fileName;
    }

}
