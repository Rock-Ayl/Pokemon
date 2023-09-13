package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * 世界配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
public class MapConfig {

    //世界名称
    private String name;

    //世界宽
    private Integer width;

    //世界高
    private Integer height;

    //世界内容节点列表
    private List<MapNode> mapNodeList;

    /**
     * 以下为 get set
     *
     * @return
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<MapNode> getMapNodeList() {
        return mapNodeList;
    }

    public void setMapNodeList(List<MapNode> mapNodeList) {
        this.mapNodeList = mapNodeList;
    }

}