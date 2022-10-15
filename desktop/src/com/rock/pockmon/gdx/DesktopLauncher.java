package com.rock.pockmon.gdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.rock.pockmon.gdx.common.Settings;

/**
 * Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
 * 请注意，在macOS上，您的应用程序需要使用 [-XstartOnFirstThread] JVM参数启动
 */
public class DesktopLauncher {

    public static void main(String[] arg) {
        //初始化配置
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //设置帧率
        config.setForegroundFPS(Settings.FPS_144);
        //窗口尺寸
        config.setWindowedMode(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        //是否使用垂直同步
        config.useVsync(Settings.USE_VERTICAL_SYNC);
        //标题
        config.setTitle(Settings.TITLE);
        //初始化
        new Lwjgl3Application(new PockMon(), config);
    }

}
