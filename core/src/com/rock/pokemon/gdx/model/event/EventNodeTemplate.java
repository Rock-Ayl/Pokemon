package com.rock.pokemon.gdx.model.event;

import com.rock.pokemon.gdx.enums.EventNodeTypeEnum;

/**
 * 事件节点接口实现
 *
 * @Author ayl
 * @Date 2024-04-20
 */
public interface EventNodeTemplate {

    /**
     * 对应类型
     *
     * @return
     */
    EventNodeTypeEnum getType();

}