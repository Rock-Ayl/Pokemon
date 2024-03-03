package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 世界配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
@Getter
@Setter
public class WorldMapConfig {

    //世界名称
    private String name;

    //世界宽
    private Integer width;

    //世界高
    private Integer height;

    //地图块节点列表
    private List<WorldMapNode> tileNodeList;

    //事物节点列表
    private List<WorldMapNode> worldObjectNodeList;

    //npc节点列表
    private List<WorldMapNode> npcNodeList;

    /**
     * 世界节点配置类实体
     */
    @Getter
    @Setter
    public static class WorldMapNode {

        //节点备注
        private String remark;

        //资源路径
        private String filePath;

        //资源区域名
        private String regionName;

        //宽度
        private Float width;

        //高度
        private Float height;

        //事物名称
        private String worldObjectName;

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