package com.rock.pokemon.gdx.model.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.map.config.SoundMapConfig;
import com.rock.pokemon.gdx.util.FastJsonExtraUtils;

/**
 * 加载 Sound 配置对象
 */
public class SoundMapConfigLoader extends AsynchronousAssetLoader<SoundMapConfig, AssetLoaderParameters<SoundMapConfig>> {

    //加载配置
    private SoundMapConfig soundMapConfig;

    public SoundMapConfigLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager assetManager, String filename, FileHandle file, AssetLoaderParameters<SoundMapConfig> parameter) {
        //读取配置文件、解析为对应配置实体、初始化
        this.soundMapConfig = FastJsonExtraUtils.deepClone(file.readString(), SoundMapConfig.class);
    }

    @Override
    public SoundMapConfig loadSync(AssetManager arg0, String arg1, FileHandle arg2, AssetLoaderParameters<SoundMapConfig> arg3) {
        //返回
        return this.soundMapConfig;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String filename, FileHandle file, AssetLoaderParameters<SoundMapConfig> parameter) {
        return null;
    }

}
