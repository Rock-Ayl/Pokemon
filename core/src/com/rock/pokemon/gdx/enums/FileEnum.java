package com.rock.pokemon.gdx.enums;

import com.rock.pokemon.gdx.common.FilePaths;
import lombok.Getter;

/**
 * 文件 枚举
 *
 * @Author ayl
 * @Date 2022-10-15
 */
@Getter
public enum FileEnum {

    /**
     * 系统配置
     */

    SYSTEM_CONFIG_TEXT_FILE_PATH("file_0010001", FilePaths.SYSTEM_CONFIG_TEXT_FILE_PATH),
    SYSTEM_TEXT_FONT_FNT("file_0010002", FilePaths.SYSTEM_TEXT_FONT_FNT),
    SYSTEM_TEXT_FONT_IMAGE("file_00100003", FilePaths.SYSTEM_TEXT_FONT_IMAGE),

    /**
     * 文件名
     */

    TEXTURES_ATLAS_FILE_NAME("file_0020010", FilePaths.TEXTURES_ATLAS_FILE_NAME),

    /**
     * 图片资源
     */

    TEXTURES_ALTA_PEOPLE("file_00300101", FilePaths.TEXTURES_ALTA_PEOPLE),
    TEXTURES_ALTA_MAP("file_00300102", FilePaths.TEXTURES_ALTA_MAP),
    TEXTURES_ALTA_UI("file_003000103", FilePaths.TEXTURES_ALTA_UI),

    /**
     * 非通用 世界配置
     */

    MAP_CONFIG_PATH_OF_LITTLE_ROOT("file_0040001", FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT),
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST("file_0040002", FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST),
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND("file_0040003", FilePaths.MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND),

    /**
     * 通用 世界配置
     */

    MAP_CONFIG_PATH_OF_WORLD_OBJECT("file_0050001", FilePaths.MAP_CONFIG_PATH_OF_WORLD_OBJECT),
    MAP_CONFIG_PATH_OF_NPC("file_0050002", FilePaths.MAP_CONFIG_PATH_OF_NPC),
    MAP_CONFIG_PATH_OF_BOX("file_0050003", FilePaths.MAP_CONFIG_PATH_OF_BOX),
    MAP_CONFIG_PATH_OF_EVENT("file_0050004", FilePaths.MAP_CONFIG_PATH_OF_EVENT),
    MAP_CONFIG_PATH_OF_SOUND("file_0050005", FilePaths.MAP_CONFIG_PATH_OF_SOUND),

    /**
     * 战斗渐变配置
     */

    TRANSITION_GLSL_VERTEX("file_0060001", FilePaths.TRANSITION_GLSL_VERTEX),
    TRANSITION_GLSL_FRAGMENT("file_0060002", FilePaths.TRANSITION_GLSL_FRAGMENT),
    TRANSITION_ANIMATION_IMAGE_PATH("file_0060003", FilePaths.TRANSITION_ANIMATION_IMAGE_PATH),

    ;

    //唯一编码
    private String code;
    //路径
    private String path;

    FileEnum(String code, String path) {
        this.code = code;
        this.path = path;
    }

}