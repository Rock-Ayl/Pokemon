package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 事物节点配置类实体
 *
 * @Author ayl
 * @Date 2023-09-15
 */
@Getter
@Setter
public class WorldObjectMapNode {

    //节点备注
    private String remark;

    //动画帧持续时间(如果有,则视为动画,否则视为静态图片)
    private Float frameDuration;

    //资源路径
    private String filePath;

    //资源区域名
    private String regionName;

    //事物宽高
    private Float width;
    private Float height;

    //事物是否可以行走
    private Boolean walkable;

    //指定 可以 行走的坐标列表
    private List<Location> tileList;

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