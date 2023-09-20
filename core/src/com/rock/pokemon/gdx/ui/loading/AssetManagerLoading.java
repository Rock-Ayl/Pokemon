package com.rock.pokemon.gdx.ui.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.model.mapConfig.WorldMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.WorldObjectMapConfig;
import com.rock.pokemon.gdx.worldloader.WorldMapConfigLoader;
import com.rock.pokemon.gdx.worldloader.WorldObjectMapConfigLoader;

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

        /**
         * 载入 已打包的各种资源
         */

        //人物
        assetManager.load("assets/packed/image/people/ruby/walk/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/people/ruby/stand/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/people/ruby/run/textures.atlas", TextureAtlas.class);

        //地图相关
        assetManager.load("assets/packed/image/map/grass/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/map/flower/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/map/object/textures.atlas", TextureAtlas.class);
        assetManager.load("assets/packed/image/map/house/textures.atlas", TextureAtlas.class);

        //ui相关
        assetManager.load("assets/packed/image/ui/textures.atlas", TextureAtlas.class);

        /**
         * 载入 各种解析器
         */

        //载入事物配置解析器
        assetManager.setLoader(WorldObjectMapConfig.class, new WorldObjectMapConfigLoader(new InternalFileHandleResolver()));
        //载入世界配置解析器
        assetManager.setLoader(WorldMapConfig.class, new WorldMapConfigLoader(new InternalFileHandleResolver()));

        /**
         * 载入 配置
         */

        //载入事物配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_WORLD_OBJECT, WorldObjectMapConfig.class);
        //载入世界配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT, WorldMapConfig.class);

        //加载资源完成
        assetManager.finishLoading();
        //返回管理器
        return assetManager;
    }

}
