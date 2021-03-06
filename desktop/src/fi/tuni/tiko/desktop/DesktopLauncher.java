package fi.tuni.tiko.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.tuni.tiko.MindPuzzle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = MindPuzzle.TITLE + " v" + MindPuzzle.VERSION;
		// Window size for desktop launcher
		config.width = MindPuzzle.VIRTUAL_WIDTH;
		config.height = MindPuzzle.VIRTUAL_HEIGHT;
		// location where the window opens on the screen
		config.x = 500;
		config.y = 25;

		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		//config.resizable = false;

		new LwjglApplication(new MindPuzzle(), config);
	}
}
