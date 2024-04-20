package com.rock.pokemon.gdx.model.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig;
import com.rock.pokemon.gdx.util.FastJsonExtraUtils;

/**
 * 加载 Npc 配置对象
 */
public class NpcMapConfigLoader extends AsynchronousAssetLoader<NpcMapConfig, AssetLoaderParameters<NpcMapConfig>> {

    //加载配置
    private NpcMapConfig npcMapConfig;

    public NpcMapConfigLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager assetManager, String filename, FileHandle file, AssetLoaderParameters<NpcMapConfig> parameter) {
        //读取配置文件、解析为对应配置实体、初始化
        this.npcMapConfig = FastJsonExtraUtils.deepClone(file.readString(), NpcMapConfig.class);
    }

    @Override
    public NpcMapConfig loadSync(AssetManager arg0, String arg1, FileHandle arg2, AssetLoaderParameters<NpcMapConfig> arg3) {
        //返回
        return npcMapConfig;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String filename, FileHandle file, AssetLoaderParameters<NpcMapConfig> parameter) {
        return null;
    }

}
