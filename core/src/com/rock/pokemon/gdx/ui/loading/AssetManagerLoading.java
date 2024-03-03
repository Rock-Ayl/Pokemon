package com.rock.pokemon.gdx.ui.loading;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.model.mapConfig.BoxMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.NpcMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.WorldMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.WorldObjectMapConfig;
import com.rock.pokemon.gdx.util.FileExtraUtils;
import com.rock.pokemon.gdx.worldloader.BoxMapConfigLoader;
import com.rock.pokemon.gdx.worldloader.NpcMapConfigLoader;
import com.rock.pokemon.gdx.worldloader.WorldMapConfigLoader;
import com.rock.pokemon.gdx.worldloader.WorldObjectMapConfigLoader;

import java.util.Arrays;
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
         * 载入已打包的各种图片资源
         */

        //所有资源路径,包含 地图块、事物、人物、UI
        List<String> textureAtlasPathList = Arrays.asList(
                FilePaths.TEXTURES_ALTA_MAP,
                FilePaths.TEXTURES_ALTA_PEOPLE,
                FilePaths.TEXTURES_ALTA_UI
        );
        //循环
        for (String dirPath : textureAtlasPathList) {
            //收集里面的资源列表
            List<String> textureAtlasList = FileExtraUtils.collectFile(dirPath, FilePaths.TEXTURES_ATLAS_FILE_NAME);
            //循环
            for (String filePath : textureAtlasList) {
                //载入对应资源
                assetManager.load(filePath, TextureAtlas.class);
            }
        }

        /**
         * 载入 各种解析器
         */

        //载入事物配置解析器
        assetManager.setLoader(WorldObjectMapConfig.class, new WorldObjectMapConfigLoader(new InternalFileHandleResolver()));
        //载入世界配置解析器
        assetManager.setLoader(WorldMapConfig.class, new WorldMapConfigLoader(new InternalFileHandleResolver()));
        //载入npc配置解析器
        assetManager.setLoader(NpcMapConfig.class, new NpcMapConfigLoader(new InternalFileHandleResolver()));
        //载入box配置解析器
        assetManager.setLoader(BoxMapConfig.class, new BoxMapConfigLoader(new InternalFileHandleResolver()));

        /**
         * 载入 配置
         */

        //载入事物配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_WORLD_OBJECT, WorldObjectMapConfig.class);
        //载入npc
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_NPC, NpcMapConfig.class);
        //载入box
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_BOX, BoxMapConfig.class);

        //载入未白镇地图配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT, WorldMapConfig.class);
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY, WorldMapConfig.class);

        /**
         * 结束加载
         */

        //加载资源完成
        assetManager.finishLoading();
        //返回管理器
        return assetManager;
    }

}
