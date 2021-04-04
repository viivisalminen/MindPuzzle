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

import java.util.Locale;
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
	public BitmapFont font60;
	// Provides access to an application's raw asset files.
	public AssetManager assets;

	public FileHandle file;
	public static String[][] socialQuestions = new String[5][5];
	public static String[][] sleepQuestions = new String[5][5];
	public static String[][] sportQuestions = new String[5][5];
	public static String[][] hobbyQuestions = new String[5][5];
	public static String[][] foodQuestions = new String[5][5];
	public int row = 0;
	public int column = 0;

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
	public PartyScreen partyScreen;

	// The most recent screen is stored in the variable, which allows
	// the return to the previous screen from the question screen.
	public Screen previousScreen = mainMenuScreen;

	public static int points = 0;
	public static int questionsAnswered = 0;

	public static String language = "";

	// Called when the Application is first created.
	// Initializes objects and sets the screen to loading screen.
	@Override
	public void create() {
		assets = new AssetManager();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		batch = new SpriteBatch();

		initFonts();
		initTextFile(socialQuestions, "SOCIAL");
		initTextFile(sleepQuestions, "SLEEP");
		initTextFile(sportQuestions, "SPORTS");
		initTextFile(hobbyQuestions, "HOBBIES");
		initTextFile(foodQuestions, "FOOD");
		MainMenuScreen.receiveQuestions(socialQuestions, sleepQuestions, sportQuestions, hobbyQuestions, foodQuestions);

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
		partyScreen = new PartyScreen(this);

		this.setScreen(loadingScreen);
		setLanguage(Locale.getDefault());
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

		FreeTypeFontGenerator.FreeTypeFontParameter parameter60 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter60.size = 60;
		parameter60.color = Color.BLACK;
		font60 = generator.generateFont(parameter60);
    }

    private void initTextFile(String[][] array, String theme) {
		file = Gdx.files.internal("questions/questions.txt");

		Scanner scanner = new Scanner(file.readString());
		String line = "";

		while(scanner.hasNext()) {
			line = scanner.nextLine();
			if (line.contains(theme)) {
				column = 0;
				line = scanner.nextLine();
				if (line.contains("?")) {
					array[row][column] = line;
				}

				line = scanner.nextLine();
				if (line.contains("a)")) {
					array[row][column + 1] = line;
				}

				line = scanner.nextLine();
				if (line.contains("b)")) {
					array[row][column + 2] = line;
				}

				line = scanner.nextLine();
				if (line.contains("c)")) {
					array[row][column + 3] = line;
				}

				line = scanner.nextLine();
				if (line.contains("a") || line.contains("b") || line.contains("c")) {
					array[row][column + 4] = line;
				}

				if (row < 3) {
					row++;
				} else if (row == 3) {
					break;
				}
			}
		}

		row = 0;
		scanner.close();
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

	public static void addPoint() {
		points++;
	}

	public static int getPoints() {
		return points;
	}

	public static void addAnsweredQuestion() {
		questionsAnswered++;
	}

	public static int getAnsweredQuestion() {
		return questionsAnswered;
	}

	public static void setLanguage(Locale locale) {
		language = locale.toString();
		System.out.println("Kieli: "+language);
	}

	public static String getLanguage() {
		return language;
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
