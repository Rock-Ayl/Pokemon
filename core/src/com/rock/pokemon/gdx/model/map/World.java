package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rock.pokemon.gdx.model.mapConfig.MapConfig;
import com.rock.pokemon.gdx.model.mapConfig.MapNode;
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
     * @param mapConfig    世界配置类
     */
    public World(AssetManager assetManager, MapConfig mapConfig) {

        //初始化地图网格
        this.tileMap = new TileMap(mapConfig.getWidth(), mapConfig.getHeight());

        /**
         * 载入地图
         */

        //循环地图节点
        for (MapNode tileMapNode : mapConfig.getTileNodeList()) {
            //获取操作
            String operate = tileMapNode.getOperate();
            //根据操作处理
            switch (operate) {
                //填充全部
                case "fill":
                    //循环1
                    for (int x = 0; x < mapConfig.getWidth(); x++) {
                        //循环2
                        for (int y = 0; y < mapConfig.getHeight(); y++) {
                            //填充对应坐标
                            this.tileMap.getTileMap()[x][y] = new Tile(x, y, assetManager.get(tileMapNode.getFilePath(), TextureAtlas.class).findRegion(tileMapNode.getRegionName()));
                        }
                    }
                    break;
                //默认
                default:
                    //填充对应坐标
                    this.tileMap.getTileMap()[tileMapNode.getX()][tileMapNode.getY()] = new Tile(tileMapNode.getX(), tileMapNode.getY(), assetManager.get(tileMapNode.getFilePath(), TextureAtlas.class).findRegion(tileMapNode.getRegionName()));
                    break;
            }
        }

        /**
         * 载入事物
         */

        //循环事务节点
        for (MapNode worldObjectMapNode : mapConfig.getWorldObjectNodeList()) {
            //初始化事物
            WorldObject worldObject = new WorldObject(
                    //坐标
                    worldObjectMapNode.getX(),
                    worldObjectMapNode.getY(),
                    //读取图片资源
                    assetManager.get(worldObjectMapNode.getFilePath(), TextureAtlas.class)
                            .findRegion(worldObjectMapNode.getRegionName()),
                    //宽高
                    worldObjectMapNode.getWidth(),
                    worldObjectMapNode.getHeight(),
                    //是否可以走
                    worldObjectMapNode.getWalkable());
            //加入到世界
            this.addWorldObject(worldObject);
        }

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
