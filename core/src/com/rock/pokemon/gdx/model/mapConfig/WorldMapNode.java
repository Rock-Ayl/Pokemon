package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 世界节点配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
@Getter
@Setter
public class WorldMapNode {

    //节点备注
    private String remark;

    //资源路径
    private String filePath;

    //资源区域名
    private String regionName;

    //事物名称
    private String worldObjectName;

    //npc名称
    private String npcName;

    //坐标列表
    private List<Location> locationList;

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