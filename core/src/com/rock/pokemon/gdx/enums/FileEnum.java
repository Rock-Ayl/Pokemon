package com.rock.pokemon.gdx.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    SYSTEM_TEXT_FONT_IMAGE("file_0010003", "assets/font/black/黑体.png"),

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
    TEXTURES_ALTA_UI("file_00300103", "assets/packed/image/ui/"),

    /**
     * 通用图集路径
     */

    //地图物件图集
    TEXTURES_ATLAS_MAP_OBJECT("file_00300201", "assets/packed/image/map/object/textures.atlas"),
    //地图房屋图集
    TEXTURES_ATLAS_MAP_HOUSE("file_00300202", "assets/packed/image/map/house/textures.atlas"),
    //人物图集-路比-站立
    TEXTURES_ATLAS_NPC_RUBY_STAND("file_00300203", "assets/packed/image/people/ruby/stand/textures.atlas"),
    //人物图集-路比-走路
    TEXTURES_ATLAS_NPC_RUBY_WALK("file_00300204", "assets/packed/image/people/ruby/walk/textures.atlas"),
    //人物图集-路比-跑步
    TEXTURES_ATLAS_NPC_RUBY_RUN("file_00300205", "assets/packed/image/people/ruby/run/textures.atlas"),
    //人物图集-小田卷博士-站立
    TEXTURES_ATLAS_NPC_PROF_BIRCH_STAND("file_00300206", "assets/packed/image/people/prof_birch/stand/textures.atlas"),
    //人物图集-小田卷博士-走路
    TEXTURES_ATLAS_NPC_PROF_BIRCH_WALK("file_00300207", "assets/packed/image/people/prof_birch/walk/textures.atlas"),

    /**
     * 非通用 世界配置
     */

    //配置文件路径-未白镇地图
    MAP_CONFIG_PATH_OF_LITTLE_ROOT("file_0040001", "assets/config/map/little_root/Main.json"),
    //配置文件路径-未白镇-路比家
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST("file_0040002", "assets/config/map/little_root/Ruby-First.json"),
    MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND("file_0040003", "assets/config/map/little_root/Ruby-Second.json"),

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
     * 音乐音效资源
     */

    //背景音乐-未白镇
    AUDIO_MUSIC_LITTLE_ROOT("file_0051001", "audio/music/LittleRoot.mp3"),
    //音效-菜单关闭
    AUDIO_SOUND_EMERALD_0005_MENU_CLOSE("file_0051002", "audio/sound/emerald_0005_menu_close.wav"),
    //音效-菜单开启
    AUDIO_SOUND_EMERALD_0006_MENU_OPEN("file_0051003", "audio/sound/emerald_0006_menu_open.wav"),
    //音效-撞墙
    AUDIO_SOUND_EMERALD_0007_NO_WALK("file_0051004", "audio/sound/emerald_0007_no_walk.wav"),
    //音效-开门
    AUDIO_SOUND_EMERALD_0008_OPEN_DOOR("file_0051005", "audio/sound/emerald_0008_open_door.wav"),

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

    //code索引
    private static final Map<String, FileEnum> CODE_MAP;

    static {
        Map<String, FileEnum> codeMap = new HashMap<>();
        for (FileEnum fileEnum : FileEnum.values()) {
            codeMap.put(fileEnum.code, fileEnum);
        }
        CODE_MAP = Collections.unmodifiableMap(codeMap);
    }

    FileEnum(String code, String path) {
        this.code = code;
        this.path = path;
    }

    /**
     * 按 code 获取枚举
     *
     * @param code 唯一编码
     * @return 枚举对象,不存在则返回null
     */
    public static FileEnum parseByCode(String code) {
        if (code == null) {
            return null;
        }
        return CODE_MAP.get(code);
    }

    /**
     * 统一解析路径
     * 兼容:
     * 1) 直接传路径
     * 2) 传 fileEnum 的 code
     *
     * @param codeOrPath code或者原始路径
     * @return 真实路径
     */
    public static String parsePath(String codeOrPath) {
        if (codeOrPath == null) {
            return null;
        }
        FileEnum fileEnum = parseByCode(codeOrPath);
        return fileEnum == null ? codeOrPath : fileEnum.getPath();
    }

}
