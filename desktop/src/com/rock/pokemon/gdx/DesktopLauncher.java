package com.rock.pokemon.gdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.rock.pokemon.gdx.common.Settings;

/**
 * Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
 * 请注意，在macOS上，您的应用程序需要使用 [-XstartOnFirstThread] JVM参数启动
 */
public class DesktopLauncher {

    public static void main(String[] arg) {
        //初始化游戏对象
        Pokemon pokemon = new Pokemon();
        //初始化配置
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //设置FPS
        config.setForegroundFPS(Settings.FPS_200);
        config.setIdleFPS(Settings.FPS_200);
        //是否使用垂直同步
        config.useVsync(Settings.USE_VERTICAL_SYNC);
        //标题
        config.setTitle(Settings.TITLE);
        //是否可以调整窗口大小
        config.setResizable(true);
        //窗口尺寸,初始化时肯定是整数
        config.setWindowedMode(pokemon.getWindowWidthInt(), pokemon.getWindowHeightInt());
        //设置屏幕尺寸最大最小值,-1为不限制
        config.setWindowSizeLimits(pokemon.getMinWindowWidth(), pokemon.getMinWindowHeight(), -1, -1);
        //初始化
        new Lwjgl3Application(pokemon, config);
    }

}
