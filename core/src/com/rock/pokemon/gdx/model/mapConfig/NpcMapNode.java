package com.rock.pokemon.gdx.model.mapConfig;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Npc 节点配置类实体
 *
 * @Author ayl
 * @Date 2023-11-20
 */
@Getter
@Setter
public class NpcMapNode {

    //节点备注
    private String remark;

    //资源区域名
    private String regionName;

    //站立
    private String standFilePath;

    //走
    private String walkFilePath;

    //跑步
    private String runFilePath;

    //事件列表
    private List<NpcMapNodeEvent> eventList;

}