package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Box 配置类实体
 *
 * @Author ayl
 * @Date 2023-11-22
 */
@Getter
@Setter
public class BoxMapConfig {

    //配置名称
    private String name;

    //BoxMap
    private Map<String, BoxMapNode> boxMap;

}