package com.rock.pokemon.gdx.enums;

import lombok.Getter;

/**
 * 所有事件 {@link com.rock.pokemon.gdx.model.event.EventNodeTemplate} 的枚举
 * 事件包括:剧情、精灵球道具、交互、各种功能
 */
@Getter
public enum EventNodeTypeEnum {

    NONE("none", "未知"),

    /**
     * 音乐、音效
     */

    SOUND_PLAY("sound_play", "音效播放"),

    /**
     * 动画
     */

    NPC_HIDE("npc_hide", "人物隐藏"),
    NPC_SHOW("npc_show", "人物显示"),

    WORLD_OBJECT_HIDE("world_object_hide", "事物隐藏"),
    WORLD_OBJECT_SHOW("world_object_show", "事物显示"),

    WAITING("waiting", "等待"),

    WORLD_OBJECT_ANIMATION_PLAY("world_object_animation_play", "播放事物动画"),

    /**
     * 移动
     */

    NPC_MOVE("npc_move", "人物移动"),

    SCENE_CHANGE("scene_change", "场景切换"),

    TRANSITION_SWITCH_ANIMATION_START_IN("transition_switch_animation_start_in", "场景切换-淡入"),
    TRANSITION_SWITCH_ANIMATION_START_OUT("transition_switch_animation_start_out", "场景切换-淡出"),

    ;

    //编号
    private String code;
    //备注
    private String name;

    EventNodeTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EventNodeTypeEnum parse(String code) {
        //循环所有美剧
        for (EventNodeTypeEnum typeEnum : EventNodeTypeEnum.values()) {
            //如果相同
            if (typeEnum.getCode().equals(code)) {
                //返回
                return typeEnum;
            }
        }
        //默认
        return NONE;
    }

}