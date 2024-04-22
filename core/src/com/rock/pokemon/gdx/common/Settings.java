package com.rock.pokemon.gdx.common;

import com.rock.pokemon.gdx.enums.LanguageEnum;

/**
 * 配置类,所有通用配置都存在在这里
 *
 * @Author ayl
 * @Date 2022-10-12
 */
public class Settings {

    /**
     * 系统固定配置
     */

    //标题
    public static final String TITLE = "Pokemon By Rock";
    //游戏版本
    public static final String VERSION = "0.0.16";

    //当前语言(暂时固定为中文)
    public static final LanguageEnum LANG = LanguageEnum.SIMPLIFIED_CHINESE;

    //是否使用垂直同步
    public static final boolean USE_VERTICAL_SYNC = true;

    //游戏FPS(帧率),推荐200
    public static final int FPS_60 = 60;
    public static final int FPS_144 = 144;
    public static final int FPS_200 = 200;

    //速度倍率(加速),1为默认速度
    public static final float SYSTEM_SPEED_MULTIPLIER = 3F;

    /**
     * 屏幕、地图、贴图
     */

    //缩放倍率,游戏的任何等比缩放,都与其有关,目前固定为3,整数
    public final static float SCALE = 3F;
    //地图/贴图/人物单个网格大小
    public final static float TILE_SIZE = 16F;
    //整体的比例
    public final static float SCALE_TILE_SIZE = SCALE * TILE_SIZE;
    //屏幕宽高,最小是这个
    public final static int WIDTH = (int) (240 * SCALE);
    public final static int HEIGHT = (int) (160 * SCALE);

    /**
     * 皮肤-key-UI
     */

    //对话框-皮肤-key
    public final static String UI_IMAGE_DIALOGUE_BOX = "dialogue_box";

    //可选项框-皮肤-key
    public final static String UI_IMAGE_OPTION_BOX = "option_box";
    //可选项框-箭头-key
    public final static String UI_IMAGE_OPTION_ARROW = "arrow";

    /**
     * 皮肤-key-子体
     */

    //通用字体样式-key
    public final static String SYSTEM_FONT_LABEL = "systemFontLabel";

    /**
     * 音效-id
     */

    //撞墙
    public final static String SOUND_ID_NO_WALK = "sound_emerald_0007_no_walk";

    //菜单开启
    public final static String SOUND_ID_MENU_OPEN = "sound_emerald_0006_menu_open";
    //菜单关闭
    public final static String SOUND_ID_MENU_CLOSE = "sound_emerald_0005_menu_close";

}
