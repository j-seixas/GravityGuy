package com.lpoo.gravityguy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lpoo.gravityguy.Game.GravityGuyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Gravity Guy";
        config.resizable = false;
        //config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
        //config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        //config.fullscreen = true;
        new LwjglApplication(GravityGuyGame.instance(), config);
	}
}
