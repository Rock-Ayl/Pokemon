package com.rock.pokemon.gdx.model.event.node;

import com.rock.pokemon.gdx.enums.EventNodeTypeEnum;
import com.rock.pokemon.gdx.model.event.EventNodeTemplate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 人物移动 事件节点
 *
 * @Author ayl
 * @Date 2024-04-20
 */
@Getter
@Setter
public class NpcMoveEventNode implements EventNodeTemplate {

    //对应npcId
    private Integer npcId;

    /**
     * 对应移动枚举 {@link com.rock.pokemon.gdx.enums.DirectionEnum} 的 ordinal
     */
    private List<Integer> moveDirectionEnumOrdinalList;

    @Override
    public EventNodeTypeEnum getType() {
        //对应枚举类型
        return EventNodeTypeEnum.NPC_MOVE;
    }

}