package com.rock.pokemon.gdx.model.map.config;

import com.alibaba.fastjson.JSONObject;
import com.rock.pokemon.gdx.model.event.EventNodeTemplate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Event 配置类实体
 *
 * @Author ayl
 * @Date 2024-04-20
 */
@Getter
@Setter
public class EventMapConfig {

    //配置名称
    private String name;

    //事件map
    private Map<String, Event> eventMap;

    /**
     * 事件 配置类实体
     */
    @Getter
    @Setter
    public static class Event {

        //备注
        private String remark;

        //事件节点Json数据列表
        private List<JSONObject> eventNodeJsonList;

        //事件节点实体列表
        private List<? extends EventNodeTemplate> eventNodeList;

    }

}