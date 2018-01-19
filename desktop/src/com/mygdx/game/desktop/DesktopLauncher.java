package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import main.MainClass;


/**
 * @author Beshoy
 * @author Kiran  
 * @author Maloof
 */

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.useGL30 = false;
        config.width = 1280;
        config.height = 720;
        config.title = "MISTAKE";

        new LwjglApplication(new MainClass(), config);
    }
}
