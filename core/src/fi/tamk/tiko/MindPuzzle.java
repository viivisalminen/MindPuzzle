package fi.tamk.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.Scanner;

/*The class MindPuzzle contains methods the application is operated with.

Class MindPuzzle initializes the measurement of the screens, fonts used in the application
*/
public class MindPuzzle extends Game {
	public static final String TITLE = "MindPuzzle";
	// Application version number.
	public static final float VERSION =  0.1f;
	// Screen dimensions.
	public static final int VIRTUAL_WIDTH = 1080;
	public static final int VIRTUAL_HEIGHT = 1920;
	// A camera with orthographic projection.
	// Used in most of the screens
	public OrthographicCamera camera;
	// Draws batched quads using indices.
	public SpriteBatch batch;
	// Renders bitmap fonts.
	public BitmapFont font30;
	public BitmapFont font40;
	// Provides access to an application's raw asset files.
	public AssetManager assets;

	// Classes' objects that are used to switch screens.
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
	public SettingsPopUp settingsPopUp;
	public QuestionScreen questionScreen;
	public AnswerScreen answerScreen;

	// The most recent screen is stored in the variable, which allows
	// the return to the previous screen from the question screen.
	public Screen previousScreen = mainMenuScreen;


	// Called when the Application is first created.
	// Initializes objects and sets the screen to loading screen.
	@Override
	public void create() {
		assets = new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH/2, VIRTUAL_HEIGHT/2);
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
		settingsPopUp = new SettingsPopUp(this);
		questionScreen = new QuestionScreen(this);
		answerScreen = new AnswerScreen(this);

		this.setScreen(loadingScreen);
	}


	// Introduces and initializes fonts
    private void initFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-MediumItalic.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter30 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter30.size = 30;
        //parameter30.borderColor = Color.BLACK;
        //parameter30.borderWidth = 2;
        parameter30.color = Color.BLACK;
        font30 = generator.generateFont(parameter30);

		FreeTypeFontGenerator.FreeTypeFontParameter parameter40 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter40.size = 40;
		//parameter40.borderColor = Color.BLACK;
		//parameter40.borderWidth = 2;
		parameter40.color = Color.BLACK;
		font40 = generator.generateFont(parameter40);
    }

	// Sets the previous visible screen.
	public void setPreviousScreen(Screen prev) {
		previousScreen = prev;
	}
	// Returns the previous visible screen.
	public Screen getPreviousScreen() {
		return previousScreen;
	}

	// Uses the currently displayed screens render()-method
	@Override
	public void render() {
	    super.render();
	}
	// Called when the Application is destroyed. Disposes all objects.
	@Override
	public void dispose() {
		batch.dispose();
		font30.dispose();
		font40.dispose();
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
		questionScreen.dispose();
		answerScreen.dispose();
	}
}
