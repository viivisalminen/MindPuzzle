package fi.tuni.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class MindPuzzle extends Game {

	public static final String TITLE = "MindPuzzle";
	public static final float VERSION =  0.1f;
	public static final int VIRTUAL_WIDTH = 400;
	public static final int VIRTUAL_HEIGHT = 650;

	public OrthographicCamera camera;
	public SpriteBatch batch;

	public BitmapFont font;
	public BitmapFont font20;

	public AssetManager assets;

	public LoadingScreen loadingScreen;
	public SplashScreen splashScreen;
	public MainMenuScreen mainMenuScreen;
	public RoomMenuScreen roomMenuScreen;
	public SettingsScreen settingsScreen;
	public CreditsScreen creditsScreen;
	public SleepRoom sleepRoom;
	public FoodRoom foodRoom;
	public SocialRoom socialRoom;
	public HobbiesRoom hobbiesRoom;
	public SportsRoom sportsRoom;

	@Override
	public void create() {
		assets = new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		batch = new SpriteBatch();

		initFonts();

		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		roomMenuScreen = new RoomMenuScreen(this);
		settingsScreen = new SettingsScreen(this);
		creditsScreen = new CreditsScreen(this);
		sleepRoom = new SleepRoom(this);
		foodRoom = new FoodRoom(this);
		socialRoom = new SocialRoom(this);
		hobbiesRoom = new HobbiesRoom(this);
		sportsRoom = new SportsRoom(this);

		// Setting the game state
		this.setScreen(loadingScreen);
	}

    private void initFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-MediumItalic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 24;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);

		FreeTypeFontGenerator.FreeTypeFontParameter parameter20 = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter20.size = 20;
		parameter20.color = Color.BLACK;
		font20 = generator.generateFont(parameter20);
    }

	@Override
	public void render() {
	    super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		assets.dispose();
		loadingScreen.dispose();
		splashScreen.dispose();
		mainMenuScreen.dispose();
		roomMenuScreen.dispose();
		settingsScreen.dispose();
		creditsScreen.dispose();
		sleepRoom.dispose();
		foodRoom.dispose();
		socialRoom.dispose();
		hobbiesRoom.dispose();
		sportsRoom.dispose();
	}
}
