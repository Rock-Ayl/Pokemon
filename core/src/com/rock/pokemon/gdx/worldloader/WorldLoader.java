package com.rock.pokemon.gdx.worldloader;

import com.alibaba.fastjson.JSON;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.map.World;
import com.rock.pokemon.gdx.model.mapConfig.MapConfig;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.Charset;

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
        //配置内容
        String fileStr = "";
        try {
            //读取配置文件
            fileStr = FileUtils.readFileToString(file.file(), Charset.defaultCharset());
        } catch (IOException e) {
            //日志
            Gdx.app.error(this.getClass().getName(), "读取配置文件", e);
        }
        //解析为对应实体
        MapConfig mapConfig = JSON.parseObject(fileStr, MapConfig.class);
        //初始化世界
        this.world = new World(assetManager, mapConfig);
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
