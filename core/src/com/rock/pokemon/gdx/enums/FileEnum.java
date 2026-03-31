package com.rock.pokemon.gdx.enums;

import lombok.Getter;

/**
 * 文件,所有文件都存在这里 枚举
 *
 * @Author ayl
 * @Date 2022-10-15
 */
@Getter
public enum FileEnum {

    /**
     * 系统配置
     */

    //文本-文件地址
    SYSTEM_CONFIG_TEXT_FILE_PATH("file_0010001", "assets/text/%s/Text.txt"),
    //文本-文字皮肤
    SYSTEM_TEXT_FONT_FNT("file_0010002", "assets/font/black/黑体.fnt"),
    SYSTEM_TEXT_FONT_IMAGE("file_00100003", "assets/font/black/黑体.png"),

    /**
     * 文件名
     */

    //资源文件名
    TEXTURES_ATLAS_FILE_NAME("file_0020010", "textures.atlas"),

    /**
     * 图片资源
     */

    //人物资源-通用路径
    TEXTURES_ALTA_PEOPLE("file_00300101", "assets/packed/image/people/"),
    //地图资源-通用路径
    TEXTURES_ALTA_MAP("file_00300102", "assets/packed/image/map/"),
    //ui资源-通用路径
    TEXTURES_ALTA_UI("file_003000103", "assets/packed/image/ui/"),

    /**
     * 非通用 世界配置
     */

    //配置文件路径-未白镇地图
    MAP_CONFIG_PATH_OF_LITTLE_ROOT("file_0040001", "assets/config/map/little_root/Main.json"),
    //配置文件路径-未白镇-路比家
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST("file_0040002", "assets/config/map/little_root/Ruby-First.json"),
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND("file_0040003", "assets/config/map/little_root/Ruby-SECOND.json"),

    /**
     * 通用 世界配置
     */

    //配置文件路径-事物
    MAP_CONFIG_PATH_OF_WORLD_OBJECT("file_0050001", "assets/config/map/WorldObject.json"),
    //配置文件路径-npc
    MAP_CONFIG_PATH_OF_NPC("file_0050002", "assets/config/map/Npc.json"),
    //配置文件路径-盒子
    MAP_CONFIG_PATH_OF_BOX("file_0050003", "assets/config/map/Box.json"),
    //配置文件路径-事件
    MAP_CONFIG_PATH_OF_EVENT("file_0050004", "assets/config/map/Event.json"),
    //配置文件路径-音效
    MAP_CONFIG_PATH_OF_SOUND("file_0050005", "assets/config/map/Sound.json"),

    /**
     * 战斗渐变配置
     */

    //顶点着色器
    TRANSITION_GLSL_VERTEX("file_0060001", "assets/config/transition/glsl/vertex.glsl"),
    //片元着色器
    TRANSITION_GLSL_FRAGMENT("file_0060002", "assets/config/transition/glsl/fragment.glsl"),
    //渐变动画图片路径
    TRANSITION_ANIMATION_IMAGE_PATH("file_0060003", "assets/config/transition/transition_%s.png"),

    ;

    //唯一编码
    private final String code;
    //路径
    private final String path;

    FileEnum(String code, String path) {
        this.code = code;
        this.path = path;
    }

}