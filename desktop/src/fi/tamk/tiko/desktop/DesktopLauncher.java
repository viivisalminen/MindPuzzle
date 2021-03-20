package fi.tamk.tiko.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.tamk.tiko.MindPuzzle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = MindPuzzle.TITLE + " v" + MindPuzzle.VERSION;
		// Window size for desktop launcher
		config.width = 400;
		config.height = 650;
		// location where the window opens on the screen
		config.x = 300;
		config.y = 25;

		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		//config.resizable = false;

		new LwjglApplication(new MindPuzzle(), config);
	}
}
