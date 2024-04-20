package com.rock.pokemon.gdx.enums;

import lombok.Getter;

@Getter
public enum EventNodeTypeEnum {

    none("none", "位置"),

    /**
     * 音乐、音效
     */

    MUSIC_SOUND_PLAY("music_sound_play", "播放音效"),

    /**
     * 动画
     */

    NPC_HIDE("npc_hide", "人物隐藏"),
    NPC_SHOW("npc_show", "人物显示"),

    WAITING("waiting", "等待"),
    PLAY_WORLD_OBJECT_ANIMATION("play_world_object_animation", "播放事物动画"),

    /**
     * 移动
     */

    MOVE_ADVENTURER("move_adventurer", "主角移动"),

    SCENE_CHANGE("scene_change", "场景切换"),

    ANIMATION_TRANSITION_SWITCH_START_IN("animation_transition_switch_start_in", "场景切换-淡入"),
    ANIMATION_TRANSITION_SWITCH_START_OUT("animation_transition_switch_start_out", "场景切换-淡出"),

    ;

    //编号
    private String code;
    //备注
    private String name;

    EventNodeTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}