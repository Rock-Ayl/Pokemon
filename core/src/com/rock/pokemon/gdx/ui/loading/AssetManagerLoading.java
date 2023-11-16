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

        //人物-路比
        assetManager.load(FilePaths.TEXTURES_ALTA_PEOPLE_RUBY_STAND, TextureAtlas.class);
        assetManager.load(FilePaths.TEXTURES_ALTA_PEOPLE_RUBY_WALK, TextureAtlas.class);
        assetManager.load(FilePaths.TEXTURES_ALTA_PEOPLE_RUBY_RUN, TextureAtlas.class);

        //人物-小田卷
        assetManager.load(FilePaths.TEXTURES_ALTA_PEOPLE_PROF_BIRCH_STAND, TextureAtlas.class);
        assetManager.load(FilePaths.TEXTURES_ALTA_PEOPLE_PROF_BIRCH_WALK, TextureAtlas.class);

        //地图相关
        assetManager.load(FilePaths.TEXTURES_ALTA_MAP_OBJECT, TextureAtlas.class);
        assetManager.load(FilePaths.TEXTURES_ALTA_MAP_HOUSE, TextureAtlas.class);

        //ui相关
        assetManager.load(FilePaths.TEXTURES_ATLAS_UI, TextureAtlas.class);

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
