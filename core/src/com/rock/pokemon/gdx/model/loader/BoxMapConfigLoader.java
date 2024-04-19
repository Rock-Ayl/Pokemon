package com.rock.pokemon.gdx.model.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.map.config.BoxMapConfig;
import com.rock.pokemon.gdx.util.FastJsonExtraUtils;

/**
 * 加载box配置对象
 */
public class BoxMapConfigLoader extends AsynchronousAssetLoader<BoxMapConfig, AssetLoaderParameters<BoxMapConfig>> {

    //加载npc配置
    private BoxMapConfig boxMapConfig;

    public BoxMapConfigLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager assetManager, String filename, FileHandle file, AssetLoaderParameters<BoxMapConfig> parameter) {
        //读取配置文件、解析为对应配置实体、初始化
        this.boxMapConfig = FastJsonExtraUtils.deepClone(file.readString(), BoxMapConfig.class);
    }

    @Override
    public BoxMapConfig loadSync(AssetManager arg0, String arg1, FileHandle arg2, AssetLoaderParameters<BoxMapConfig> arg3) {
        //返回
        return boxMapConfig;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String filename, FileHandle file, AssetLoaderParameters<BoxMapConfig> parameter) {
        return null;
    }

}
