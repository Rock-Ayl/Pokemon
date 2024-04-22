package com.rock.pokemon.gdx.model.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.model.loader.*;
import com.rock.pokemon.gdx.model.map.config.*;
import com.rock.pokemon.gdx.util.FileExtraUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 通用资源管理器
 * 本质上是封装了一层 {@link AssetManager} ,整合了一些通用的获取方法
 *
 * @Author ayl
 * @Date 2024-03-06
 */
@Getter
public class MyAssetManager {

    //原生资源管理器
    private AssetManager assetManager;

    /**
     * 初始化方法
     */
    public MyAssetManager() {
        //初始化原生资源管理器
        this.assetManager = initAssetManager();
    }

    /**
     * 初始化一个 原生资源管理器 ,并载入资源
     *
     * @return
     */
    private static AssetManager initAssetManager() {

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
        //载入NPC配置解析器
        assetManager.setLoader(NpcMapConfig.class, new NpcMapConfigLoader(new InternalFileHandleResolver()));
        //载入盒子配置解析器
        assetManager.setLoader(BoxMapConfig.class, new BoxMapConfigLoader(new InternalFileHandleResolver()));
        //载入事件配置解析器
        assetManager.setLoader(EventMapConfig.class, new EventMapConfigLoader(new InternalFileHandleResolver()));
        //载入音效配置解析器
        assetManager.setLoader(SoundMapConfig.class, new SoundMapConfigLoader(new InternalFileHandleResolver()));

        /**
         * 载入 配置
         */

        //载入事物配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_WORLD_OBJECT, WorldObjectMapConfig.class);
        //载入npc
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_NPC, NpcMapConfig.class);
        //载入盒子
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_BOX, BoxMapConfig.class);
        //载入事件
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_EVENT, EventMapConfig.class);
        //载入音效
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_SOUND, SoundMapConfig.class);

        //载入未白镇地图配置
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT, WorldMapConfig.class);
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST, WorldMapConfig.class);
        assetManager.load(FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND, WorldMapConfig.class);

        /**
         * 结束加载
         */

        //加载资源完成
        assetManager.finishLoading();
        //返回管理器
        return assetManager;
    }

    /**
     * 获取 图片 资源
     *
     * @param fileName 文件名称
     * @return
     */
    public TextureAtlas getTextureAtlas(String fileName) {
        return this.assetManager.get(fileName, TextureAtlas.class);
    }

    /**
     * 获取 图片 资源,指定是否必要
     *
     * @param fileName 文件名称
     * @param required 是否必要
     * @return
     */
    public TextureAtlas getTextureAtlas(String fileName, boolean required) {
        return this.assetManager.get(fileName, TextureAtlas.class, required);
    }

    /**
     * 获取 事物 配置类实体
     *
     * @return
     */
    public WorldObjectMapConfig getWorldObjectMapConfig() {
        return this.assetManager.get(FilePaths.MAP_CONFIG_PATH_OF_WORLD_OBJECT, WorldObjectMapConfig.class);
    }

    /**
     * 获取 Npc 配置类实体
     *
     * @return
     */
    public NpcMapConfig getNpcMapConfig() {
        return this.assetManager.get(FilePaths.MAP_CONFIG_PATH_OF_NPC, NpcMapConfig.class);
    }

    /**
     * 获取 Box 配置类实体
     *
     * @return
     */
    public BoxMapConfig getBoxMapConfig() {
        return this.assetManager.get(FilePaths.MAP_CONFIG_PATH_OF_BOX, BoxMapConfig.class);
    }

    /**
     * 获取 世界 配置类实体
     *
     * @param fileName
     * @return
     */
    public WorldMapConfig getWorldMapConfig(String fileName) {
        return this.assetManager.get(fileName, WorldMapConfig.class);
    }

    /**
     * 获取 事件 配置类实体
     *
     * @return
     */
    public EventMapConfig getEventMapConfig() {
        return this.assetManager.get(FilePaths.MAP_CONFIG_PATH_OF_EVENT, EventMapConfig.class);
    }

    /**
     * 获取 音效 配置类实体
     *
     * @return
     */
    public SoundMapConfig getSoundMapConfig() {
        return this.assetManager.get(FilePaths.MAP_CONFIG_PATH_OF_SOUND, SoundMapConfig.class);
    }

    /**
     * 销毁
     */
    public void dispose() {
        this.assetManager.dispose();
    }

}
