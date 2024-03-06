package com.rock.pokemon.gdx.common;

/**
 * 文件地址类,所有文件路径都存放在这里
 * 文件包括: 图片、音效、音乐、文本
 *
 * @Author ayl
 * @Date 2022-10-12
 */
public class FilePaths {

    /**
     * 系统配置
     */

    //文本-文件地址
    public static final String SYSTEM_CONFIG_TEXT_FILE_PATH = "assets/text/%s/Text.txt";

    //文本-文字皮肤
    public static final String SYSTEM_TEXT_FONT_FNT = "assets/font/black/黑体.fnt";
    public static final String SYSTEM_TEXT_FONT_IMAGE = "assets/font/black/黑体.png";

    /**
     * 文件名
     */

    //资源文件名
    public static final String TEXTURES_ATLAS_FILE_NAME = "textures.atlas";

    /**
     * 图片资源
     */

    //人物资源-通用路径
    public static final String TEXTURES_ALTA_PEOPLE = "assets/packed/image/people/";

    //地图资源-通用路径
    public static final String TEXTURES_ALTA_MAP = "assets/packed/image/map/";

    //ui资源-通用路径
    public static final String TEXTURES_ALTA_UI = "assets/packed/image/ui/";

    /**
     * 世界配置
     */

    //配置文件路径-未白镇地图
    public static final String MAP_CONFIG_PATH_OF_LITTLE_ROOT = "assets/config/map/little_root/Main.json";
    //配置文件路径-未白镇-路比家
    public static final String MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_FIRST = "assets/config/map/little_root/Ruby-First.json";
    public static final String MAP_CONFIG_PATH_OF_LITTLE_ROOT_HOUSE_RUBY_SECOND = "assets/config/map/little_root/Ruby-SECOND.json";

    //配置文件路径-事物
    public static final String MAP_CONFIG_PATH_OF_WORLD_OBJECT = "assets/config/map/WorldObject.json";
    //配置文件路径-npc
    public static final String MAP_CONFIG_PATH_OF_NPC = "assets/config/map/Npc.json";
    //配置文件路径-box
    public static final String MAP_CONFIG_PATH_OF_BOX = "assets/config/map/Box.json";

    /**
     * 音效
     */

    //撞墙音效
    public static final String SOUND_NO_WALK = "audio/sound/emerald_0007_no_walk.wav";

    //菜单开启音效
    public static final String SOUND_MENU_OPEN = "audio/sound/emerald_0006_menu_open.wav";
    //菜单关闭音效
    public static final String SOUND_MENU_CLOSE = "audio/sound/emerald_0005_menu_close.wav";

}
