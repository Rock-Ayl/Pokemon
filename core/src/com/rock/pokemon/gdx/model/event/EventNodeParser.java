package com.rock.pokemon.gdx.model.event;

import com.alibaba.fastjson.JSONObject;
import com.badlogic.gdx.Gdx;
import com.rock.pokemon.gdx.enums.EventNodeTypeEnum;
import com.rock.pokemon.gdx.model.event.node.*;
import com.rock.pokemon.gdx.util.FastJsonExtraUtils;

import java.util.Optional;

/**
 * 事件节点解析器,用来解析 {@link EventNodeTemplate}
 *
 * @Author ayl
 * @Date 2024-04-20
 */
public class EventNodeParser {

    /**
     * 根据配置文件的json,解析为对应的 {@link EventNodeTemplate}
     *
     * @param data 来自配置文件的 EventNode 数据
     * @return
     */
    public static EventNodeTemplate parse(JSONObject data) {
        //要解析为的实体
        Class<? extends EventNodeTemplate> clazz = null;
        //获取类型解析为枚举
        EventNodeTypeEnum eventNodeTypeEnum = Optional.ofNullable(data)
                //获取类型
                .map(p -> p.getString("type"))
                //解析枚举
                .map(EventNodeTypeEnum::parse)
                //默认
                .orElse(EventNodeTypeEnum.NONE);
        //根据枚举
        switch (eventNodeTypeEnum) {
            case WAITING:
                //指定对应实体
                clazz = WaitingEventNode.class;
                break;
            case NPC_HIDE:
                //指定对应实体
                clazz = NpcHideEventNode.class;
                break;
            case NPC_MOVE:
                //指定对应实体
                clazz = NpcMoveEventNode.class;
                break;
            case NPC_SHOW:
                //指定对应实体
                clazz = NpcShowEventNode.class;
                break;
            case SOUND_PLAY:
                //指定对应实体
                clazz = SoundPlayEventNode.class;
                break;
            case SCENE_CHANGE:
                //指定对应实体
                clazz = SceneChangeEventNode.class;
                break;
            case WORLD_OBJECT_HIDE:
                //指定对应实体
                clazz = WorldObjectHideEventNode.class;
                break;
            case WORLD_OBJECT_SHOW:
                //指定对应实体
                clazz = WorldObjectShowEventNode.class;
                break;
            case WORLD_OBJECT_ANIMATION_PLAY:
                //指定对应实体
                clazz = WorldObjectAnimationPlayEventNode.class;
                break;
            case TRANSITION_SWITCH_ANIMATION_START_IN:
                //指定对应实体
                clazz = TransitionSwitchAnimationStartInEventNode.class;
                break;
            case TRANSITION_SWITCH_ANIMATION_START_OUT:
                //指定对应实体
                clazz = TransitionSwitchAnimationStartOutEventNode.class;
                break;
            //默认
            case NONE:
            default:
                break;
        }
        //判空
        if (clazz == null) {
            //日志
            Gdx.app.error("EventNodeParserParseError", "未识别节点类型");
            //过
            return null;
        }
        //解析实体实现
        return FastJsonExtraUtils.deepClone(data, clazz);
    }

}
