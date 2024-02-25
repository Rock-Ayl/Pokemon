package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 事物配置类实体
 *
 * @Author ayl
 * @Date 2023-09-15
 */
@Getter
@Setter
public class WorldObjectMapConfig {

    //配置名称
    private String name;

    //事物Map
    private Map<String, WorldObjectMapNode> worldObjectMap;

}