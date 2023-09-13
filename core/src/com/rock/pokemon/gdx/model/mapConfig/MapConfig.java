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

    //地图块节点列表
    private List<MapNode> tileNodeList;

    //事物节点列表
    private List<MapNode> worldObjectNodeList;

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

    public List<MapNode> getTileNodeList() {
        return tileNodeList;
    }

    public void setTileNodeList(List<MapNode> tileNodeList) {
        this.tileNodeList = tileNodeList;
    }

    public List<MapNode> getWorldObjectNodeList() {
        return worldObjectNodeList;
    }

    public void setWorldObjectNodeList(List<MapNode> worldObjectNodeList) {
        this.worldObjectNodeList = worldObjectNodeList;
    }

}