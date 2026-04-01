package com.rock.pokemon.gdx.model.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.rock.pokemon.gdx.PokemonGame;
import com.rock.pokemon.gdx.enums.DirectionEnum;
import com.rock.pokemon.gdx.enums.FileEnum;
import com.rock.pokemon.gdx.model.map.config.EventMapConfig;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig;
import com.rock.pokemon.gdx.model.map.config.NpcMapConfig.NpcMapNode;
import com.rock.pokemon.gdx.model.map.config.WorldMapConfig;
import com.rock.pokemon.gdx.model.map.config.WorldObjectMapConfig;
import com.rock.pokemon.gdx.model.screen.WorldScreen;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 世界对象,代表一个世界
 *
 * @Author ayl
 * @Date 2022-10-21
 */
@Getter
@Setter
public class World {

    //对应上层世界屏幕
    private WorldScreen worldScreen;

    //地图网格本身
    private TileMap tileMap;

    //该世界的人物集合,有序的map,key是人物id
    private LinkedHashMap<String, Person> personMap = new LinkedHashMap<>();

    //该世界的事物的列表(树木、草、牌子等等)
    private List<WorldObject> worldObjectList = new ArrayList<>();

    //地图联通触发索引（当前位置 + 输入方向 => 事件id）
    private Map<String, String> mapConnectEventIdMap = new HashMap<>();

    /**
     * 初始化世界
     *
     * @param pokemonGame    游戏对象
     * @param worldScreen    世界对应屏幕
     * @param worldMapConfig 世界配置
     */
    public World(PokemonGame pokemonGame, WorldScreen worldScreen, WorldMapConfig worldMapConfig) {

        /**
         * 记录
         */

        //记录世界对应屏幕
        this.worldScreen = worldScreen;

        /**
         * 读取各种配置
         */

        //读取事物配置
        WorldObjectMapConfig worldObjectMapConfig = pokemonGame.getGameContext().getMyAssetManager().getWorldObjectMapConfig();

        //读取npc配置
        NpcMapConfig npcMapConfig = pokemonGame.getGameContext().getMyAssetManager().getNpcMapConfig();

        //读取事件配置
        Map<String, EventMapConfig.Event> eventMap = pokemonGame.getGameContext().getMyAssetManager().getEventMapConfig().getEventMap();

        /**
         * 初始化 地图网格、地图块本身
         */

        //初始化地图网格
        this.tileMap = new TileMap(worldMapConfig.getWidth(), worldMapConfig.getHeight());
        //循环1
        for (int x = 0; x < worldMapConfig.getWidth(); x++) {
            //循环2
            for (int y = 0; y < worldMapConfig.getHeight(); y++) {
                //填充对应坐标
                this.tileMap.getMap()[x][y] = new Tile(x, y);
            }
        }

        //初始化地图联通事件索引
        this.initMapConnectEventMap(worldMapConfig, eventMap);

        /**
         * 载入地图资源
         */

        //循环地图节点
        for (WorldMapConfig.TileNode tileNode : worldMapConfig.getTileNodeList()) {

            /**
             * 获取当前地图块图片
             */

            //获取图片路径
            FileEnum fileEnum = FileEnum.parseByCode(tileNode.getFileCode());
            //判空
            if (fileEnum == FileEnum.NONE) {
                //本轮过
                continue;
            }
            //获取图片对象
            TextureRegion image = pokemonGame
                    .getGameContext().getMyAssetManager()
                    .getTextureAtlas(fileEnum.getPath())
                    .findRegion(tileNode.getRegionName());
            //如果没有图片
            if (image == null) {
                //本轮过
                continue;
            }
            //获取地图块宽高
            Float width = Optional.ofNullable(tileNode)
                    .map(WorldMapConfig.TileNode::getWidth)
                    .orElse(null);
            Float height = Optional.ofNullable(tileNode)
                    .map(WorldMapConfig.TileNode::getHeight)
                    .orElse(null);

            /**
             * 填充所有地图块
             */

            //是否填充所有的地图,默认false
            boolean fillAllMap = Optional.ofNullable(tileNode)
                    .map(WorldMapConfig.TileNode::getFillAllMap)
                    .orElse(false);
            //如果要填充所有
            if (fillAllMap == true) {
                //循环1
                for (int x = 0; x < worldMapConfig.getWidth(); x++) {
                    //循环2
                    for (int y = 0; y < worldMapConfig.getHeight(); y++) {
                        //设置图片,宽高
                        this.tileMap.getMap()[x][y].setImage(image, width, height);
                    }
                }
            }

            /**
             * 填充指定地图块
             */

            //获取坐标列表
            List<WorldMapConfig.Location> locationList = tileNode.getLocationList();
            //如果存在指定
            if (CollectionUtils.isNotEmpty(locationList)) {
                //循环
                for (WorldMapConfig.Location location : locationList) {
                    //设置图片,宽高
                    this.tileMap.getMap()[location.getX()][location.getY()].setImage(image, width, height);
                }
            }

        }

        /**
         * 载入事物资源
         */

        //循环世界事务节点
        for (WorldMapConfig.WorldObjectNode worldObjectNode : worldMapConfig.getWorldObjectNodeList()) {

            /**
             * 获取事务对应事件
             */

            //门事件,默认为0
            EventMapConfig.Event doorEvent = null;
            //获取门事件id
            String doorEventId = worldObjectNode.getDoorEventId();
            //如果存在对应门事件
            if (doorEventId != null && eventMap.containsKey(doorEventId)) {
                //获取对应门事件
                doorEvent = eventMap.get(doorEventId);
            }

            /**
             * 初始化对应的事物
             */

            //获取事物名称
            String worldObjectName = worldObjectNode.getWorldObjectName();
            //读取对应事物配置
            WorldObjectMapConfig.WorldObjectMapNode worldObjectNodeConfig = worldObjectMapConfig.getWorldObjectMap().get(worldObjectName);
            //循环坐标列表
            for (WorldMapConfig.Location location : worldObjectNode.getLocationList()) {
                //初始化事物
                WorldObject worldObject = new WorldObject(
                        pokemonGame.getGameContext().getMyAssetManager(),
                        worldObjectNodeConfig,
                        worldObjectNode.getWorldObjectId(),
                        location.getX(),
                        location.getY(),
                        doorEvent
                );
                //加入到世界
                this.addWorldObject(worldObject);
            }
        }

        /**
         * 载入NPC资源
         */

        //循环世界事务节点
        for (WorldMapConfig.NpcNode npcNode : worldMapConfig.getNpcNodeList()) {
            //获取npcId
            String npcId = npcNode.getNpcId();
            //获取npc名称
            String npcName = npcNode.getNpcName();
            //读取对应npc配置
            NpcMapNode npcMapNode = npcMapConfig.getNpcMap().get(npcName);
            //获取坐标
            WorldMapConfig.Location location = npcNode.getLocation();
            //初始化话一个npc,并加入到该世界
            new Person(npcId, npcMapNode, this, location.getX(), location.getY(), pokemonGame);
        }

    }

    /**
     * 初始化地图联通事件索引（当前位置 + 输入方向 => 事件id）
     */
    private void initMapConnectEventMap(WorldMapConfig worldMapConfig, Map<String, EventMapConfig.Event> eventMap) {
        //重置
        this.mapConnectEventIdMap = new HashMap<>();
        //读取配置节点
        List<WorldMapConfig.MapConnectEventNode> nodeList = Optional.ofNullable(worldMapConfig)
                .map(WorldMapConfig::getMapConnectEventNodeList)
                .orElse(null);
        //没有配置直接结束
        if (CollectionUtils.isEmpty(nodeList)) {
            //过
            return;
        }
        //循环节点
        for (WorldMapConfig.MapConnectEventNode node : nodeList) {
            //判空
            if (node == null) {
                //本轮过
                continue;
            }
            //读取触发方向
            DirectionEnum triggerDirectionEnum = DirectionEnum.parseByName(node.getTriggerDirection());
            //读取事件id
            String eventId = node.getEventId();
            //无效配置直接跳过
            if (triggerDirectionEnum == null || StringUtils.isBlank(eventId)) {
                //本轮过
                continue;
            }
            //如果事件配置存在,且该事件不存在
            if (eventMap != null && eventMap.containsKey(eventId) == false) {
                //本轮过
                continue;
            }
            //读取坐标列表
            List<WorldMapConfig.Location> locationList = node.getLocationList();
            //没有触发点直接跳过
            if (CollectionUtils.isEmpty(locationList)) {
                //本轮过
                continue;
            }
            //循环坐标
            for (WorldMapConfig.Location location : locationList) {
                //坐标异常保护
                if (location == null || location.getX() == null || location.getY() == null) {
                    //本轮过
                    continue;
                }
                //覆盖登记
                this.mapConnectEventIdMap.put(
                        buildMapConnectEventKey(location.getX(), location.getY(), triggerDirectionEnum),
                        eventId
                );
            }
        }
    }

    /**
     * 根据当前位置和输入方向，查找地图联通事件id
     *
     * @param x             地图坐标x
     * @param y             地图坐标y
     * @param directionEnum 输入方向
     * @return 事件id
     */
    public String findMapConnectEventId(int x, int y, DirectionEnum directionEnum) {
        //判空
        if (directionEnum == null) {
            //过
            return null;
        }
        //返回
        return this.mapConnectEventIdMap.get(buildMapConnectEventKey(x, y, directionEnum));
    }

    /**
     * 构建地图联通事件索引键
     *
     * @param x             坐标x
     * @param y             坐标y
     * @param directionEnum 方向
     * @return 索引key
     */
    private static String buildMapConnectEventKey(int x, int y, DirectionEnum directionEnum) {
        return x + "_" + y + "_" + directionEnum.getName();
    }

    /**
     * 加入人物
     *
     * @param person 人物实体
     */
    public void addPerson(Person person) {
        //加入到地图块
        this.getTileMap().getTile(person.getX(), person.getY()).setPerson(person);
        //加入到人物集合
        this.personMap.put(person.getNpcId(), person);
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
        for (Person person : this.personMap.values()) {
            //更新人物
            person.update(delta);
        }
        //循环
        for (WorldObject worldObject : this.worldObjectList) {
            //更新事物
            worldObject.update(delta);
        }
    }

    /**
     * 根据事物id,查找事物
     *
     * @param worldObjectId 事物id
     * @return 事物实体
     */
    public WorldObject findWorldObjectById(String worldObjectId) {
        //判空
        if (StringUtils.isNotBlank(worldObjectId)) {
            //循环
            for (WorldObject worldObject : this.worldObjectList) {
                //如果是
                if (worldObjectId.equals(worldObject.getWorldObjectId())) {
                    //返回
                    return worldObject;
                }
            }
        }
        //默认
        return null;
    }

}
