package com.rock.pockmon.gdx.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rock.pockmon.gdx.common.FilePaths;

/**
 * 地图块 枚举
 */
public enum TileEnum {

    /**
     * 草
     */

    GRASS_1(new Texture(Gdx.files.internal(FilePaths.MAP_IMAGE_GRASS_1)), "草1"),
    GRASS_2(new Texture(Gdx.files.internal(FilePaths.MAP_IMAGE_GRASS_2)), "草2"),

    ;

    //地图块图片
    private Texture image;
    //中文名
    private String zhName;

    TileEnum(Texture image, String zhName) {
        ;
        this.image = image;
        this.zhName = zhName;
    }

    public Texture getImage() {
        return image;
    }

    public String getZhName() {
        return zhName;
    }

}
