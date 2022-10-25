package com.rock.pokemon.gdx.enums;

/**
 * 人物枚举 枚举
 */
public enum PersonEnum {

    RUBE(1, "ruby", "路比"),
    SAPPHIRE(2, "sapphire", "沙菲亚"),

    ;

    //人物编号
    private Integer number;
    //人物名(用来获取动画资源)
    private String name;
    //中文名
    private String zhName;

    PersonEnum(Integer number, String name, String zhName) {
        this.number = number;
        this.name = name;
        this.zhName = zhName;
    }

    /**
     * Get Set 方法
     *
     * @return
     */

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

}
