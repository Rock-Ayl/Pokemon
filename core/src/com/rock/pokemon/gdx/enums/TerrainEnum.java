package com.rock.pokemon.gdx.enums;

/**
 * 地形枚举 枚举
 */
public enum TerrainEnum {

    GRASS_1("grass", "1", "草1"),
    GRASS_2("grass", "2", "草2(隐藏物品)"),
    GRASS_3("grass", "3", "草3标准"),

    ;

    //地形资源目录
    private String dir;

    //地形资源编号
    private String number;

    //中文名
    private String zhName;

    TerrainEnum(String dir, String number, String zhName) {
        this.dir = dir;
        this.number = number;
        this.zhName = zhName;
    }

    /**
     * 以下为 get set 方法
     *
     * @return
     */

    public String getDir() {
        return dir;
    }

    public String getNumber() {
        return number;
    }

}
