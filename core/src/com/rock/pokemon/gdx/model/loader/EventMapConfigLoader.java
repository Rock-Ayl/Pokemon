package com.rock.pokemon.gdx.model.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rock.pokemon.gdx.model.event.EventNodeParser;
import com.rock.pokemon.gdx.model.map.config.EventMapConfig;
import com.rock.pokemon.gdx.util.FastJsonExtraUtils;

import java.util.stream.Collectors;

/**
 * 加载 Event 配置对象
 */
public class EventMapConfigLoader extends AsynchronousAssetLoader<EventMapConfig, AssetLoaderParameters<EventMapConfig>> {

    //加载配置
    private EventMapConfig eventMapConfig;

    public EventMapConfigLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager assetManager, String filename, FileHandle file, AssetLoaderParameters<EventMapConfig> parameter) {
        //读取配置文件、解析为对应配置实体、初始化
        this.eventMapConfig = FastJsonExtraUtils.deepClone(file.readString(), EventMapConfig.class);
        //循环事件实体
        for (EventMapConfig.Event event : this.eventMapConfig.getEventMap().values()) {
            //解析不同类型的事件节点
            event.setEventNodeList(event
                    //获取json数据
                    .getEventNodeJsonList()
                    .stream()
                    //解析为对应实体
                    .map(EventNodeParser::parse)
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public EventMapConfig loadSync(AssetManager arg0, String arg1, FileHandle arg2, AssetLoaderParameters<EventMapConfig> arg3) {
        //返回
        return eventMapConfig;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String filename, FileHandle file, AssetLoaderParameters<EventMapConfig> parameter) {
        return null;
    }

}
