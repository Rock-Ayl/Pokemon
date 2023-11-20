package com.rock.pokemon.gdx.model.mapConfig;

import java.util.Map;

/**
 * Npc 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-20
 */
public class NpcMapConfig {

    //配置名称
    private String name;

    //npcMap
    private Map<String, NpcMapNode> npcMap;

    /**
     * 以下为 get set
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, NpcMapNode> getNpcMap() {
        return npcMap;
    }

    public void setNpcMap(Map<String, NpcMapNode> npcMap) {
        this.npcMap = npcMap;
    }

}