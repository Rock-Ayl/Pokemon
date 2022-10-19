package com.rock.pockmon.gdx.enums;

/**
 * 人物枚举 枚举
 */
public enum PersonEnum {

    RUBE(1, "路比"),
    SAPPHIRE(2, "沙菲亚"),

    ;

    //人物编号
    private Integer number;
    //中文名
    private String zhName;

    PersonEnum(Integer number, String zhName) {
        this.number = number;
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

    public String getZhName() {
        return zhName;
    }

}
