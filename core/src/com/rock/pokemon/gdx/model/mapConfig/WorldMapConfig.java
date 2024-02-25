package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 世界配置类实体
 *
 * @Author ayl
 * @Date 2023-09-13
 */
@Getter
@Setter
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

}