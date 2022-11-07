package com.rock.pokemon.gdx.ui.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * 资源管理器载入
 */
public class AssetManagerLoading {

    /**
     * 初始化一个资源管理器,并载入资源
     *
     * @return
     */
    public static AssetManager initAssetManager() {
        //初始化资源管理器
        AssetManager assetManager = new AssetManager();
        //加载已打包的各种资源
        assetManager.load("assets/packed/image/people/ruby/walk/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/people/ruby/stand/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/map/grass/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/map/object/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/ui/textures.atlas", TextureAtlas.class);
        //加载资源完成
        assetManager.finishLoading();
        //返回皮肤
        return assetManager;
    }

}
