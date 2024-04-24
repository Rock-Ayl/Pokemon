package com.rock.pokemon.gdx.model.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.map.config.WorldMapConfig;
import com.rock.pokemon.gdx.util.FastJsonExtraUtils;

/**
 * 加载 世界 配置对象
 */
public class WorldMapConfigLoader extends AsynchronousAssetLoader<WorldMapConfig, AssetLoaderParameters<WorldMapConfig>> {

    //加载配置
    private WorldMapConfig worldMapConfig;

    public WorldMapConfigLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager assetManager, String filename, FileHandle file, AssetLoaderParameters<WorldMapConfig> parameter) {
        //读取配置文件、解析为对应配置实体、初始化
        this.worldMapConfig = FastJsonExtraUtils.deepClone(file.readString(), WorldMapConfig.class);
    }

    @Override
    public WorldMapConfig loadSync(AssetManager arg0, String arg1, FileHandle arg2, AssetLoaderParameters<WorldMapConfig> arg3) {
        //返回
        return this.worldMapConfig;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String filename, FileHandle file, AssetLoaderParameters<WorldMapConfig> parameter) {
        return null;
    }

}
