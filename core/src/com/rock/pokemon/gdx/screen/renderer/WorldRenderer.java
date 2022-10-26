package com.rock.pokemon.gdx.screen.renderer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rock.pokemon.gdx.model.Camera;
import com.rock.pokemon.gdx.model.map.World;

/**
 * 世界渲染器,用它统一渲染世界的每个地图块、人物、事物
 *
 * @Author ayl
 * @Date 2022-10-23
 */
public class WorldRenderer {

    //资源管理器
    private AssetManager assetManager;

    //一个完整的世界实体
    private World world;

    /**
     * 初始化方法,要渲染,得有世界和图片资源吧.
     *
     * @param assetManager 资源管理器
     * @param world        世界实体
     */
    public WorldRenderer(AssetManager assetManager, World world) {
        this.assetManager = assetManager;
        this.world = world;
    }

    /**
     * 使用渲染器和相机,计算世界坐标,然后计算人物、地图块、事物的图层,最后渲染
     *
     * @param batch  渲染器
     * @param camera 相机
     */
    public void render(SpriteBatch batch, Camera camera) {
        //todo
    }

}
