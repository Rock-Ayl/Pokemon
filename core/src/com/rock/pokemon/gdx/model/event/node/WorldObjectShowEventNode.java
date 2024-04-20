package com.rock.pokemon.gdx.model.event.node;

import com.rock.pokemon.gdx.enums.EventNodeTypeEnum;
import com.rock.pokemon.gdx.model.event.EventNodeTemplate;
import lombok.Getter;
import lombok.Setter;

/**
 * 事物显示 事件节点
 *
 * @Author ayl
 * @Date 2024-04-20
 */
@Getter
@Setter
public class WorldObjectShowEventNode implements EventNodeTemplate {

    //对应事物Id
    private String worldObjectId;

    @Override
    public EventNodeTypeEnum getType() {
        //对应枚举类型
        return EventNodeTypeEnum.WORLD_OBJECT_SHOW;
    }

}