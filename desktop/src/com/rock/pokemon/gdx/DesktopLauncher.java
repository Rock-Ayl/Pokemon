package com.rock.pokemon.gdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.rock.pokemon.gdx.common.Settings;

/**
 * Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
 * 注意事项1:在macOS上，您的应用程序需要使用 [-XstartOnFirstThread] JVM参数启动
 * 注意事项2:如果启动后java进程无响应,请重启电脑,暂时不知道原因
 */
public class DesktopLauncher {

    public static void main(String[] arg) {
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
        config.setWindowedMode(Settings.WIDTH, Settings.HEIGHT);
        //设置屏幕尺寸最大最小值,-1为不限制
        config.setWindowSizeLimits(Settings.WIDTH, Settings.HEIGHT, -1, -1);
        //初始化
        new Lwjgl3Application(new PokemonGame(), config);
    }

}
