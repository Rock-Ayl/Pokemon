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
    public static final String VERSION = "0.0.6";

    //todo 当前语言(暂时固定为中文)
    public static final LanguageEnum LANG = LanguageEnum.SIMPLIFIED_CHINESE;

    //是否使用垂直同步
    public static final boolean USE_VERTICAL_SYNC = true;

    //游戏FPS(帧率),推荐200
    public static final int FPS_60 = 60;
    public static final int FPS_144 = 144;
    public static final int FPS_200 = 200;

    /**
     * 屏幕、地图、贴图
     */

    //缩放倍率,游戏的任何等比缩放,都与其有关,目前固定为2,整数
    public static float SCALE = 2F;
    //地图/贴图/人物单个网格大小
    public final static float TILE_SIZE = 16F;
    //整体的比例
    public final static float SCALE_TILE_SIZE = SCALE * TILE_SIZE;
    //屏幕宽高,最小是这个
    public final static int WIDTH = (int) (240 * SCALE);
    public final static int HEIGHT = (int) (160 * SCALE);

}
