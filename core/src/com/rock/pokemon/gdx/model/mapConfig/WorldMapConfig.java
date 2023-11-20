package com.rock.pokemon.gdx.model.mapConfig;

import java.util.List;

/**
 * 世界配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
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

    public List<WorldMapNode> getTileNodeList() {
        return tileNodeList;
    }

    public void setTileNodeList(List<WorldMapNode> tileNodeList) {
        this.tileNodeList = tileNodeList;
    }

    public List<WorldMapNode> getWorldObjectNodeList() {
        return worldObjectNodeList;
    }

    public void setWorldObjectNodeList(List<WorldMapNode> worldObjectNodeList) {
        this.worldObjectNodeList = worldObjectNodeList;
    }

    public List<WorldMapNode> getNpcNodeList() {
        return npcNodeList;
    }

    public void setNpcNodeList(List<WorldMapNode> npcNodeList) {
        this.npcNodeList = npcNodeList;
    }

}