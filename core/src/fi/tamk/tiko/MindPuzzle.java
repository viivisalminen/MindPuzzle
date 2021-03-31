package fi.tamk.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
	public static int VIRTUAL_WIDTH = 1080;
	public static int VIRTUAL_HEIGHT = 1920;
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

	public FileHandle file;
	public static String[][] textArray = new String[5][5];
	public int row = 0;
	public int column = 0;
	public static String question = "";
	public static String optionA = "";
	public static String optionB = "";
	public static String optionC = "";
	public static String rightAnswer = "";

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
		initTextFile();

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
        parameter30.color = Color.BLACK;
        font30 = generator.generateFont(parameter30);

		FreeTypeFontGenerator.FreeTypeFontParameter parameter40 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter40.size = 40;
		parameter40.color = Color.BLACK;
		font40 = generator.generateFont(parameter40);
    }

    private void initTextFile() {
		file = Gdx.files.internal("questions/questions.txt");

		Scanner scanner = new Scanner(file.readString());
		String line = "";

		while(scanner.hasNext()){
			line = scanner.nextLine();
			column = 0;
			if(line.contains("?")) {
				textArray[row][column] = line;
				System.out.println("Kysymys:  "+question);
			}
			line = scanner.nextLine();
			if(line.contains("a)")) {
				textArray[row][column + 1] = line;
				System.out.println("a):  "+optionA);
			}
			line = scanner.nextLine();
			if (line.contains("b)")) {
				textArray[row][column + 2] = line;
				System.out.println("b):  "+optionB);
			}
			line = scanner.nextLine();
			if (line.contains("c)")) {
				textArray[row][column + 3] = line;
				System.out.println("c):  "+optionC);
			}
			line = scanner.nextLine();
			if (line.contains("a") || line.contains("b") || line.contains("c")) {
				textArray[row][column + 4] = line;
				System.out.println("Oikea vastaus:  "+rightAnswer);
			}

			if(row < 3) {
				row++;
			}
			else if (row == 3) {
				break;
			}
		}

		System.out.println("initTextFile metodissa. Taulukko käsitelty. Tulostetaan...");

		for(int rivi = 0; rivi < textArray.length; rivi++) {
			for(int sarake = 0; sarake < textArray.length; sarake++) {
				System.out.println(textArray[rivi][sarake]);
			}
		}

		MainMenuScreen.receiveQuestions(textArray);
	}

    public static void setVirtualWidth(int width) {
		VIRTUAL_WIDTH = width;
	}

	public static void setVirtualHeight(int height) {
		VIRTUAL_HEIGHT = height;
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
