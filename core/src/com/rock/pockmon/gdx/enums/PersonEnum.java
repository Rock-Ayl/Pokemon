package com.rock.pockmon.gdx.enums;

import com.rock.pockmon.gdx.common.FilePaths;

/**
 * 人物枚举 枚举
 */
public enum PersonEnum {

    RUBE(1, FilePaths.RUBY_IMAGE_DIR, "路比"),
    SAPPHIRE(2, "", "沙菲亚"),

    ;

    //人物编号
    private Integer number;
    //任务图片目录
    private String imageDir;
    //中文名
    private String zhName;

    PersonEnum(Integer number, String imageDir, String zhName) {
        this.number = number;
        this.imageDir = imageDir;
        this.zhName = zhName;
    }

    public Integer getNumber() {
        return number;
    }

    public String getImageDir() {
        return imageDir;
    }

    public String getZhName() {
        return zhName;
    }

}
