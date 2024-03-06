package com.rock.pokemon.gdx.model.manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.model.mapConfig.BoxMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.NpcMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.WorldMapConfig;
import com.rock.pokemon.gdx.model.mapConfig.WorldObjectMapConfig;
import lombok.Getter;

/**
 * 通用资源管理器
 * 本质上是封装了一层 {@link AssetManager} ,整合了一些通用的获取方法
 *
 * @Author ayl
 * @Date 2024-03-06
 */
@Getter
public class MyAssetManager {

    //资源管理器
    private AssetManager assetManager;

    /**
     * 初始化方法
     *
     * @param assetManager
     */
    public MyAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
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
     * 获取 图片 资源
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
     * @param fileName
     * @return
     */
    public WorldObjectMapConfig getWorldObjectMapConfig(String fileName) {
        return this.assetManager.get(fileName, WorldObjectMapConfig.class);
    }

    /**
     * 获取 Npc 配置类实体
     *
     * @param fileName
     * @return
     */
    public NpcMapConfig getNpcMapConfig(String fileName) {
        return this.assetManager.get(fileName, NpcMapConfig.class);
    }

    /**
     * 获取 Box 配置类实体
     *
     * @param fileName
     * @return
     */
    public BoxMapConfig getBoxMapConfig(String fileName) {
        return this.assetManager.get(fileName, BoxMapConfig.class);
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
     * 销毁
     */
    public void dispose() {
        this.assetManager.dispose();
    }

}
