package com.rock.pokemon.gdx.enums;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
     * 获取枚举本身图片
     *
     * @param assetManager 资源管理器
     * @return
     */
    public TextureRegion getImage(AssetManager assetManager) {
        //根据枚举的目录+编号,获取资源对象
        return assetManager
                .get("assets/packed/image/map/" + this.dir + "/textures.atlas", TextureAtlas.class)
                .findRegion(this.number);
    }

}
