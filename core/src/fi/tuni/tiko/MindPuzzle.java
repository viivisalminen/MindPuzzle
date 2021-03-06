package fi.tuni.tiko;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MindPuzzle extends Game {

	public static final String TITLE = "MindPuzzle";
	public static final float VERSION =  0.1f;
	public static final int VIRTUAL_WIDTH = 480;
	public static final int VIRTUAL_HEIGHT = 420;

	public OrthographicCamera camera;
	public SpriteBatch batch;

	public BitmapFont font;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 720);
		batch = new SpriteBatch();
		font = new BitmapFont();

		// Setting the game state
		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		this.getScreen().dispose();
	}
}
