package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.enums.TerrainEnum;
import com.rock.pokemon.gdx.model.people.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 世界对象,代表一个世界(比如未白镇)
 *
 * @Author ayl
 * @Date 2022-10-21
 */
public class World {

    //地图网格本身
    private TileMap tileMap;

    //该世界的人物列表
    private List<Person> personList = new ArrayList<>();

    //该世界的事物的列表(树木、草、牌子等等)
    private List<WorldObject> worldObjectList = new ArrayList<>();

    /**
     * 初始化世界
     *
     * @param assetManager 资源管理器
     * @param width        世界宽
     * @param height       世界高
     */
    public World(AssetManager assetManager, int width, int height) {

        //初始化地图网格
        this.tileMap = new TileMap(width, height);

        /**
         * 这里目前先简单生成一个全是草的地图map
         */

        //循环1
        for (int x = 0; x < width; x++) {
            //循环2
            for (int y = 0; y < height; y++) {
                //初始化地图块为草3
                tileMap.getTileMap()[x][y] = new Tile(x, y, TerrainEnum.GRASS_3, assetManager);
            }
        }


        /**
         * 简单加点事物
         */

        //载入事物资源
        TextureAtlas textureAtlas = assetManager.get("assets/packed/image/map/object/textures.atlas", TextureAtlas.class);

        //初始化牌子
        WorldObject sign = new WorldObject(5, 5, textureAtlas.findRegion("sign"), 1F, 1F, false);
        //初始化草地土块补丁
        WorldObject grassDirtPatch = new WorldObject(9, 5, textureAtlas.findRegion("grass_dirt_patch"), 1F, 1F, true);

        //事物加入到世界
        this.addWorldObject(sign);
        this.addWorldObject(grassDirtPatch);

    }

    /**
     * 加入人物
     *
     * @param person 人物实体
     */
    public void addPerson(Person person) {
        //组装
        this.personList.add(person);
    }

    /**
     * 加入事物
     *
     * @param worldObject 事物实体
     */
    public void addWorldObject(WorldObject worldObject) {
        //地图块上加入事物
        this.getTileMap().getTile(worldObject.getX(), worldObject.getY()).setWorldObject(worldObject);
        //组装
        this.worldObjectList.add(worldObject);
    }

    /**
     * 每帧更新
     *
     * @param delta 每帧时间
     */
    public void update(float delta) {
        //循环
        for (Person person : personList) {
            //更新人物
            person.update(delta);
        }
        //循环
        for (WorldObject worldObject : worldObjectList) {
            //更新事物
            worldObject.update(delta);
        }
    }

    /**
     * 以下是 get set 方法
     *
     * @return
     */

    public TileMap getTileMap() {
        return tileMap;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<WorldObject> getWorldObjectList() {
        return worldObjectList;
    }

}
