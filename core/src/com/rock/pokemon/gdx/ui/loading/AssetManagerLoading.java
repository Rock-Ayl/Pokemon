package com.rock.pokemon.gdx.ui.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.model.mapConfig.NpcMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.WorldMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.WorldObjectMapConfig;
import com.rock.pokemon.gdx.util.FileExtraUtils;
import com.rock.pokemon.gdx.worldloader.NpcMapConfigLoader;
import com.rock.pokemon.gdx.worldloader.WorldMapConfigLoader;
import com.rock.pokemon.gdx.worldloader.WorldObjectMapConfigLoader;

import java.util.List;

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

        //收集 人物 资源列表
        List<String> peopleTextureAtlasList = FileExtraUtils.collectFile(FilePaths.TEXTURES_ALTA_PEOPLE, FilePaths.TEXTURES_ATLAS_FILE_NAME);
        //循环
        for (String peopleTextureAtlasPath : peopleTextureAtlasList) {
            //载入对应人物资源
            assetManager.load(peopleTextureAtlasPath, TextureAtlas.class);
        }

        //收集 地图(地图块、事物等) 资源列表
        List<String> mapTextureAtlasList = FileExtraUtils.collectFile(FilePaths.TEXTURES_ALTA_MAP, FilePaths.TEXTURES_ATLAS_FILE_NAME);
        //循环
        for (String mapTextureAtlasPath : mapTextureAtlasList) {
            //载入对应人物资源
            assetManager.load(mapTextureAtlasPath, TextureAtlas.class);
        }

        //ui相关
        assetManager.load(FilePaths.TEXTURES_ATLAS_UI, TextureAtlas.class);

        /**
         * 载入 各种解析器
         */

        //载入事物配置解析器
        assetManager.setLoader(WorldObjectMapConfig.class, new WorldObjectMapConfigLoader(new InternalFileHandleResolver()));
        //载入世界配置解析器
        assetManager.setLoader(WorldMapConfig.class, new WorldMapConfigLoader(new InternalFileHandleResolver()));
        //载入npc配置解析器
        assetManager.setLoader(NpcMapConfig.class, new NpcMapConfigLoader(new InternalFileHandleResolver()));

        /**
         * 载入 配置
         */

        //载入事物配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_WORLD_OBJECT, WorldObjectMapConfig.class);
        //载入世界配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT, WorldMapConfig.class);
        //载入npc
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_NPC, NpcMapConfig.class);

        //加载资源完成
        assetManager.finishLoading();
        //返回管理器
        return assetManager;
    }

}
