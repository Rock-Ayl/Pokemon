package com.rock.pokemon.gdx.model.mapConfig;

import java.util.Map;

/**
 * Box 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
public class BoxMapConfig {

    //配置名称
    private String name;

    //BoxMap
    private Map<String, BoxMapNode> boxMap;

    /**
     * 以下为 get set
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, BoxMapNode> getBoxMap() {
        return boxMap;
    }

    public void setBoxMap(Map<String, BoxMapNode> boxMap) {
        this.boxMap = boxMap;
    }

}