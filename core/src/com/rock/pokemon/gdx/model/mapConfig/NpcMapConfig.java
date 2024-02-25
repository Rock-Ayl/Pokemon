package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Npc 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-20
 */
@Getter
@Setter
public class NpcMapConfig {

    //配置名称
    private String name;

    //npcMap
    private Map<String, NpcMapNode> npcMap;

}