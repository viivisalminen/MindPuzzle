package fi.tamk.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    // A camera with orthographic projection. Used in most of the screens
    public OrthographicCamera camera;
    // Draws batched quads using indices.
    public SpriteBatch batch;
    // Renders bitmap fonts.
    public BitmapFont font20;
    public BitmapFont font30;
    public BitmapFont font40;
    public BitmapFont font50;
    public BitmapFont font60;
    // Provides access to an application's raw asset files.
    public AssetManager assets;

    public FileHandle fileEN, fileFIN;
    public static String[][] socialQuestions = new String[20][20];
    public static String[][] sleepQuestions = new String[20][20];
    public static String[][] sportQuestions = new String[20][20];
    public static String[][] hobbyQuestions = new String[20][20];
    public static String[][] foodQuestions = new String[20][20];
    public static String[][] socialQuestionsFIN = new String[20][20];
    public static String[][] sleepQuestionsFIN = new String[20][20];
    public static String[][] sportQuestionsFIN = new String[20][20];
    public static String[][] hobbyQuestionsFIN = new String[20][20];
    public static String[][] foodQuestionsFIN = new String[20][20];
    public int row = 0;
    public int column = 0;
    public static int returnable = 0;

    // Classes' objects that are used to switch screens.
    public LoadingScreen loadingScreen;
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
    public GameInstructionsScreen instructionsScreen;

    // The most recent screen is stored in the variable, which allows
    // the return to the previous screen from the question screen.
    public Screen previousScreen = mainMenuScreen;
    public static String previousCharacter = "";

    public static int points = 0;
    public static int foodQuestionsAnswered = 0;
    public static int socialQuestionsAnswered = 0;
    public static int sportQuestionsAnswered = 0;
    public static int hobbyQuestionsAnswered = 0;
    public static int sleepQuestionsAnswered = 0;

    public static String language = "";

    // Called when the Application is first created.
    // Initializes objects and sets the screen to loading screen.
    @Override
    public void create() {
        assets = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        batch = new SpriteBatch();

        assets.load("images/background2.png", Texture.class);

        initFonts();
        getLanguage();

        fileEN = Gdx.files.internal("questions/questionsEN.txt");
        initTextFile(socialQuestions, "SOCIAL", fileEN);
        initTextFile(sleepQuestions, "SLEEP", fileEN);
        initTextFile(sportQuestions, "SPORTS", fileEN);
        initTextFile(hobbyQuestions, "HOBBIES", fileEN);
        initTextFile(foodQuestions, "FOOD", fileEN);
        MainMenuScreen.receiveENQuestions(socialQuestions, sleepQuestions, sportQuestions, hobbyQuestions, foodQuestions);

        fileFIN = Gdx.files.internal("questions/questionsFIN.txt");
        initTextFile(socialQuestionsFIN, "SOCIAL", fileFIN);
        initTextFile(sleepQuestionsFIN, "SLEEP", fileFIN);
        initTextFile(sportQuestionsFIN, "SPORTS", fileFIN);
        initTextFile(hobbyQuestionsFIN, "HOBBIES", fileFIN);
        initTextFile(foodQuestionsFIN, "FOOD", fileFIN);
        MainMenuScreen.receiveFINQuestions(socialQuestionsFIN, sleepQuestionsFIN, sportQuestionsFIN, hobbyQuestionsFIN, foodQuestionsFIN);

        loadingScreen = new LoadingScreen(this);
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
        instructionsScreen = new GameInstructionsScreen(this);

        this.setScreen(loadingScreen);
    }

    // Uses the currently displayed screens render()-method
    @Override
    public void render() {
        super.render();
    }

    // Introduces and initializes fonts
    private void initFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-Bold.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter20 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter20.size = 20;
        parameter20.color = Color.BLACK;
        font20 = generator.generateFont(parameter20);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter30 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter30.size = 30;
        parameter30.color = Color.BLACK;
        font30 = generator.generateFont(parameter30);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter40 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter40.size = 40;
        parameter40.color = Color.BLACK;
        font40 = generator.generateFont(parameter40);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter50 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter50.size = 50;
        parameter50.color = Color.BLACK;
        font50 = generator.generateFont(parameter50);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter60 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter60.size = 60;
        parameter60.color = Color.BLACK;
        font60 = generator.generateFont(parameter60);
    }

    private void initTextFile(String[][] array, String theme, FileHandle file) {
        Scanner scanner = new Scanner(file.readString());
        String line = "";

        while(scanner.hasNext()) {
            line = scanner.nextLine();
            if (line.contains(theme)) {
                column = 0;
                line = scanner.nextLine();
                if (line.contains("?")) {	lineUpText(array, line, 0);	}

                line = scanner.nextLine();
                if (line.contains("a)")) {	lineUpText(array, line, 1);	}

                line = scanner.nextLine();
                if (line.contains("b)")) {	lineUpText(array, line, 2);	}

                line = scanner.nextLine();
                if (line.contains("c)")) {	lineUpText(array, line, 3);	}

                line = scanner.nextLine();
                if (line.contains("a") || line.contains("b") || line.contains("c")) {
                    array[row][column + 4] = line;
                }

                if (row < 19) {
                    row++;
                }
                else if (row == 19) {
                    break;
                }
            }
        }

        row = 0;
        scanner.close();
    }

    private void lineUpText(String[][] array, String line, int columnNo) {
        String longLine = "";
        if(line.length() >= 40 && line.length() < 80) {
            longLine = new StringBuilder().append(line.substring(0, 40))
                    .append("\n").append(line.substring(40)).toString();
            array[row][columnNo] = longLine;
        } else if(line.length() >= 80 && line.length() < 120) {
            longLine = new StringBuilder().append(line.substring(0,40))
                    .append("\n").append(line.substring(40,80))
                    .append("\n").append(line.substring(80)).toString();
            array[row][columnNo] = longLine;
        } else if(line.length() >= 120 && line.length() < 150) {
            longLine = new StringBuilder().append(line.substring(0,40))
                    .append("\n").append(line.substring(40,80))
                    .append("\n").append(line.substring(80,120))
                    .append("\n").append(line.substring(120)).toString();
            array[row][columnNo] = longLine;
        } else if(line.length() >= 150) {
            longLine = new StringBuilder().append(line.substring(0,40))
                    .append("\n").append(line.substring(40,80))
                    .append("\n").append(line.substring(80,120))
                    .append("\n").append(line.substring(120,150))
                    .append("\n").append(line.substring(150)).toString();
            array[row][columnNo] = longLine;
        } else {
            array[row][columnNo] = line;
        }
    }

    // Sets the previous visible screen.
    public void setPreviousScreen(Screen prev) {
        previousScreen = prev;
    }
    // Returns the previous visible screen.
    public Screen getPreviousScreen() {
        return previousScreen;
    }

    public static void setPreviousCharacter(String character) {
        previousCharacter = character;
    }

    public static String getCharacter() {
        return previousCharacter;
    }

    public void addAnsweredQuestion(Screen room) {
        if(room.equals(foodRoom)) {
            foodQuestionsAnswered++;
        } else if (room.equals(socialRoom)) {
            socialQuestionsAnswered++;
        } else if (room.equals(sleepRoom)) {
            sleepQuestionsAnswered++;
        } else if (room.equals(hobbiesRoom)) {
            hobbyQuestionsAnswered++;
        } else if (room.equals(sportsRoom)) {
            sportQuestionsAnswered++;
        }
    }

    public static int getAnsweredQuestion(String room) {
        if(room.equals("food")) {
            returnable = foodQuestionsAnswered;
        } else if (room.equals("social")) {
            returnable = socialQuestionsAnswered;
        } else if (room.equals("sleep")) {
            returnable = sleepQuestionsAnswered;
        } else if (room.equals("hobbies")) {
            returnable = hobbyQuestionsAnswered;
        } else if (room.equals("sports")) {
            returnable = sportQuestionsAnswered;
        }

        return returnable;
    }

    public static void setLanguage(Locale locale) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        language = locale.toString();
        prefs.putString("language", language);
        prefs.flush();
    }

    public static String getLanguage() {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        Locale locale = Locale.getDefault();
        String defaultLanguage = locale.toString();

        language = prefs.getString("language", defaultLanguage);

        return language;
    }

    public void addPoint() {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        points++;
        prefs.putInteger("points", points);
        prefs.flush();
    }

    public static int getPoints() {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        points = prefs.getInteger("points", points);

        return points;
    }

    public void saveCharacter(String character, Boolean roomCharacter) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        prefs.putBoolean(character, roomCharacter);
        prefs.flush();
    }

    public boolean openCharacters(String character, Boolean roomCharacter) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        Boolean isCharacterVisible  = prefs.getBoolean(character, roomCharacter);

        return isCharacterVisible;
    }

    public void resetGame() {
        mainMenuScreen.resetSettings();
        foodRoom.resetCharacterInfo();
        hobbiesRoom.resetCharacterInfo();
        sleepRoom.resetCharacterInfo();
        socialRoom.resetCharacterInfo();
        sportsRoom.resetCharacterInfo();
        points = 0;
        foodQuestionsAnswered = 0;
        socialQuestionsAnswered = 0;
        sportQuestionsAnswered = 0;
        hobbyQuestionsAnswered = 0;
        sleepQuestionsAnswered = 0;
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        prefs.clear();
        prefs.flush();
    }

    // Called when the Application is destroyed. Disposes all objects.
    @Override
    public void dispose() {
        batch.dispose();
        font20.dispose();
        font30.dispose();
        font40.dispose();
        font50.dispose();
        font60.dispose();
        assets.dispose();
        loadingScreen.dispose();
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
