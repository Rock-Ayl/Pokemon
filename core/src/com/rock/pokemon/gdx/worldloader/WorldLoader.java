package com.rock.pokemon.gdx.worldloader;

import com.alibaba.fastjson.JSON;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.mapConfig.WorldMapConfig;

/**
 * 加载世界对象
 */
public class WorldLoader extends AsynchronousAssetLoader<World, WorldLoader.WorldParameter> {

    //加载的世界本身
    private World world;

    public WorldLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager assetManager, String filename, FileHandle file, WorldParameter parameter) {
        //读取配置文件、解析为对应配置实体、初始化世界
        this.world = new World(assetManager, JSON.parseObject(file.readString(), WorldMapConfig.class));
    }

    @Override
    public World loadSync(AssetManager arg0, String arg1, FileHandle arg2, WorldParameter arg3) {
        return world;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String filename, FileHandle file, WorldParameter parameter) {
        return null;
    }

    public static class WorldParameter extends AssetLoaderParameters<World> {

    }

}
