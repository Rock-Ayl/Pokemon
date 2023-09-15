package com.rock.pokemon.gdx.model.mapConfig;

import java.util.Map;

/**
 * 事物配置类实体
 *
 * @Author ayl
 * @Date 2023-09-15
 */
public class WorldObjectMapConfig {

    //配置名称
    private String name;

    //事物Map
    private Map<String, WorldObjectMapNode> worldObjectMap;

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

    public Map<String, WorldObjectMapNode> getWorldObjectMap() {
        return worldObjectMap;
    }

    public void setWorldObjectMap(Map<String, WorldObjectMapNode> worldObjectMap) {
        this.worldObjectMap = worldObjectMap;
    }

}