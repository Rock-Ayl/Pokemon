package com.rock.pockmon.gdx.common;

import com.rock.pockmon.gdx.enums.LanguageEnum;

/**
 * 配置类,所有通用配置都存在在这里
 *
 * @Author ayl
 * @Date 2022-10-12
 */
public class Settings {

    //标题
    public static final String TITLE = "PockMon By Rock";
    //游戏版本
    public static final String VERSION = "0.0.1";

    //游戏帧率
    public static int FPS_60 = 60;
    public static int FPS_144 = 144;

    //todo 当前语言(暂时固定为中文)
    public static LanguageEnum LANG = LanguageEnum.SIMPLIFIED_CHINESE;

    //todo 分辨率 暂时固定为 800 * 480
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 480;

    //是否使用垂直同步
    public static boolean USE_VERTICAL_SYNC = true;

}
