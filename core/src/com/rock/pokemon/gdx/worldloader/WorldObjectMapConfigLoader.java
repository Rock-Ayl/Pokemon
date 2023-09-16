package com.rock.pokemon.gdx.worldloader;

import com.alibaba.fastjson.JSON;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.mapConfig.WorldObjectMapConfig;

/**
 * 加载事物配置对象
 */
public class WorldObjectMapConfigLoader extends AsynchronousAssetLoader<WorldObjectMapConfig, AssetLoaderParameters<WorldObjectMapConfig>> {

    //加载世界配置
    private WorldObjectMapConfig worldObjectMapConfig;

    public WorldObjectMapConfigLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager assetManager, String filename, FileHandle file, AssetLoaderParameters<WorldObjectMapConfig> parameter) {
        //读取配置文件、解析为对应配置实体、初始化世界
        this.worldObjectMapConfig = JSON.parseObject(file.readString(), WorldObjectMapConfig.class);
    }

    @Override
    public WorldObjectMapConfig loadSync(AssetManager arg0, String arg1, FileHandle arg2, AssetLoaderParameters<WorldObjectMapConfig> arg3) {
        //返回
        return worldObjectMapConfig;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String filename, FileHandle file, AssetLoaderParameters<WorldObjectMapConfig> parameter) {
        return null;
    }

}
