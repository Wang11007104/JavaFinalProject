package com.wxk.starwar.lwjgl3;

import java.io.File;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {

    /**
     * 程式進入點。設定應用程式視窗參數並啟動遊戲主體。
     *
     * 設定內容包括：
     * - 視窗標題為 "SkyWizard"
     * - 視窗大小為 600x800 像素
     * - 開啟垂直同步（VSync）
     * - 前景最大 FPS 設為 60
     */
    public static void main(String[] arg) {

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("SkyWizard"); // 設定視窗標題
        config.setWindowedMode(600, 800); // 設定視窗大小
        config.useVsync(true); // 開啟垂直同步
        config.setForegroundFPS(60); // 設定 FPS 上限
        new Lwjgl3Application(new SkyWizard(), config); // 啟動 MainSpace

    }
}