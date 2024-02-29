package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.GridPoint2;
import com.rock.pokemon.gdx.Pokemon;
import com.rock.pokemon.gdx.common.FilePaths;
import com.rock.pokemon.gdx.model.mapConfig.*;
import com.rock.pokemon.gdx.model.people.Person;
import com.rock.pokemon.gdx.screen.town.LittleRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 世界对象,代表一个世界(比如未白镇)
 *
 * @Author ayl
 * @Date 2022-10-21
 */
@Getter
@Setter
public class World {

    //todo 世界上一层的城镇,目前先写死这个吧
    private LittleRoot town;

    //地图网格本身
    private TileMap tileMap;

    //该世界的人物列表
    private List<Person> personList = new ArrayList<>();

    //该世界的事物的列表(树木、草、牌子等等)
    private List<WorldObject> worldObjectList = new ArrayList<>();

    /**
     * 初始化世界
     *
     * @param game          游戏对象
     * @param town          城镇对象
     * @param mapConfigPath 世界配置路径
     */
    public World(Pokemon game, LittleRoot town, String mapConfigPath) {

        /**
         * 记录
         */

        //记录城镇
        this.town = town;

        /**
         * 读取各种配置
         */

        //读取世界配置
        WorldMapConfig worldMapConfig = game.getAssetManager().get(mapConfigPath, WorldMapConfig.class);

        //读取事物配置
        WorldObjectMapConfig worldObjectMapConfig = game.getAssetManager().get(FilePaths.MAP_CONFIG_PATH_OF_WORLD_OBJECT, WorldObjectMapConfig.class);

        //读取npc配置
        NpcMapConfig npcMapConfig = game.getAssetManager().get(FilePaths.MAP_CONFIG_PATH_OF_NPC, NpcMapConfig.class);

        /**
         * 初始化地图网格
         */

        //初始化地图网格
        this.tileMap = new TileMap(worldMapConfig.getWidth(), worldMapConfig.getHeight());

        /**
         * 载入地图资源
         */

        //循环地图节点
        for (WorldMapNode tileMapNode : worldMapConfig.getTileNodeList()) {
            //获取坐标列表
            List<WorldMapNode.Location> locationList = tileMapNode.getLocationList();
            //如果没有则填充所有、有则按照指定的填充
            if (CollectionUtils.isEmpty(locationList)) {
                //循环1
                for (int x = 0; x < worldMapConfig.getWidth(); x++) {
                    //循环2
                    for (int y = 0; y < worldMapConfig.getHeight(); y++) {
                        //填充对应坐标
                        this.tileMap.getMap()[x][y] = new Tile(
                                //坐标
                                x,
                                y,
                                //对应图片资源
                                game.getAssetManager().get(tileMapNode.getFilePath(), TextureAtlas.class).findRegion(tileMapNode.getRegionName())
                        );
                    }
                }
            } else {
                //循环
                for (WorldMapNode.Location location : locationList) {
                    //填充对应坐标
                    this.tileMap.getMap()[location.getX()][location.getY()] = new Tile(
                            //坐标
                            location.getX(),
                            location.getY(),
                            //获取对应图片资源
                            game.getAssetManager().get(tileMapNode.getFilePath(), TextureAtlas.class).findRegion(tileMapNode.getRegionName())
                    );
                }
            }
        }

        /**
         * 载入事物资源
         */

        //循环世界事务节点
        for (WorldMapNode worldObjectNode : worldMapConfig.getWorldObjectNodeList()) {
            //获取事物名称
            String worldObjectName = worldObjectNode.getWorldObjectName();
            //读取对应事物配置
            WorldObjectMapNode worldObjectNodeConfig = worldObjectMapConfig.getWorldObjectMap().get(worldObjectName);
            //循环坐标列表
            for (WorldMapNode.Location location : worldObjectNode.getLocationList()) {
                //初始化事物
                WorldObject worldObject = new WorldObject(game.getAssetManager(), worldObjectNodeConfig, location.getX(), location.getY());
                //加入到世界
                this.addWorldObject(worldObject);
            }
        }

        /**
         * 载入NPC资源
         */

        //循环世界事务节点
        for (WorldMapNode npcNode : worldMapConfig.getNpcNodeList()) {
            //获取npc名称
            String npcName = npcNode.getNpcName();
            //读取对应npc配置
            NpcMapConfig.NpcMapNode npcMapNode = npcMapConfig.getNpcMap().get(npcName);
            //循环坐标列表
            for (WorldMapNode.Location location : npcNode.getLocationList()) {
                //初始化话一个npc,并加入到该世界
                new Person(npcMapNode, this, location.getX(), location.getY(), game);
            }
        }

    }

    /**
     * 加入人物
     *
     * @param person 人物实体
     */
    public void addPerson(Person person) {
        //加入到地图块
        this.getTileMap().getTile(person.getX(), person.getY()).setPerson(person);
        //加入到人物列表
        this.personList.add(person);
    }

    /**
     * 加入事物
     *
     * @param worldObject 事物实体
     */
    public void addWorldObject(WorldObject worldObject) {
        //循环
        for (GridPoint2 gridPoint2 : worldObject.getGridPointList()) {
            //对应网格地图块上加入事物(体积碰撞)
            this.getTileMap().getTile(worldObject.getX() + gridPoint2.x, worldObject.getY() + gridPoint2.y).setWorldObject(worldObject);
        }
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
        for (Person person : this.personList) {
            //更新人物
            person.update(delta);
        }
        //循环
        for (WorldObject worldObject : this.worldObjectList) {
            //更新事物
            worldObject.update(delta);
        }
    }

}
