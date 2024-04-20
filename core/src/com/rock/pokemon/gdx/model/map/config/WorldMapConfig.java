package com.rock.pokemon.gdx.model.map.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 世界 配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
@Getter
@Setter
public class WorldMapConfig {

    //世界名称
    private String name;

    //世界备注
    private String remark;

    //世界宽
    private Integer width;

    //世界高
    private Integer height;

    //bgm地址
    private String bgmPath;

    //地图块节点列表
    private List<TileNode> tileNodeList;

    //事物节点列表
    private List<WorldObjectNode> worldObjectNodeList;

    //npc节点列表
    private List<NpcNode> npcNodeList;

    /**
     * 地图块节点实体
     */
    @Getter
    @Setter
    public static class TileNode {

        //节点备注
        private String remark;

        //资源路径
        private String filePath;

        //资源区域名
        private String regionName;

        //是否填充所有的地图,默认false
        private Boolean fillAllMap;

        //宽度
        private Float width;

        //高度
        private Float height;

        //坐标列表
        private List<Location> locationList;

    }

    /**
     * 事物节点实体
     */
    @Getter
    @Setter
    public static class WorldObjectNode {

        //节点备注
        private String remark;

        //事物名称
        private String worldObjectName;

        //坐标列表
        private List<Location> locationList;

    }

    /**
     * npc节点实体
     */
    @Getter
    @Setter
    public static class NpcNode {

        //npcId
        private Integer npcId;

        //节点备注
        private String remark;

        //npc名称
        private String npcName;

        //坐标列表
        private List<Location> locationList;

    }

    /**
     * 坐标对象
     */
    @Getter
    @Setter
    public static class Location {

        //资源坐标
        private Integer x;
        private Integer y;

    }

}