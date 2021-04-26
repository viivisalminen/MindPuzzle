package fi.tamk.mindpuzzle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

/**
 * MindPuzzle is the main class of the application
 * that contains metadata of the game and methods the game is operated with.
 *
 * MindPuzzle includes information on what was the last visible screen and character,
 * how many questions in the rooms have been answered and amount of the points.
 * It also handles the text files, screen switching, the reset of the game
 * and the saving of language settings and character status to the Preferences file.
 *
 * @author      Viivi Salminen
 * @version     2021.2704
 * @since       15.0.1
 */
public class MindPuzzle extends Game {
    /**
     * The Viewport's width.
     */
    public static int VIRTUAL_WIDTH = 1080;
    /**
     * The Viewport's height.
     */
    public static int VIRTUAL_HEIGHT = 1920;
    /**
     * A camera with orthographic projection.
     */
    public OrthographicCamera camera;
    /**
     * Draws batched quads using indices.
     */
    public SpriteBatch batch;
    /**
     * Renders bitmap fonts in size 20.
     */
    public BitmapFont font20;
    /**
     * Renders bitmap fonts in size 30.
     */
    public BitmapFont font30;
    /**
     * Renders bitmap fonts in size 40.
     */
    public BitmapFont font40;
    /**
     * Renders bitmap fonts in size 50.
     */
    public BitmapFont font50;
    /**
     * Renders bitmap fonts in size 60.
     */
    public BitmapFont font60;
    /**
     * Loads and stores assets like textures, sounds and music.
     */
    public AssetManager assets;
    /**
     * Text files containing questions in English.
     */
    public FileHandle fileEN;
    /**
     * Text files containing questions in Finnish.
     */
    public FileHandle fileFIN;
    /**
     * 2D array for English questions about relationships and emotions.
     */
    public static String[][] socialQuestions = new String[20][20];
    /**
     * 2D array for English questions about sleep and rest.
     */
    public static String[][] sleepQuestions = new String[20][20];
    /**
     * 2D array for English questions about exercise.
     */
    public static String[][] sportQuestions = new String[20][20];
    /**
     * 2D array for English questions about hobbies.
     */
    public static String[][] hobbyQuestions = new String[20][20];
    /**
     * 2D array for English questions about food and eating habits.
     */
    public static String[][] foodQuestions = new String[20][20];
    /**
     * 2D array for Finnish questions about relationships and emotions.
     */
    public static String[][] socialQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about sleep and rest.
     */
    public static String[][] sleepQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about exercise.
     */
    public static String[][] sportQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about hobbies.
     */
    public static String[][] hobbyQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about food and eating habits.
     */
    public static String[][] foodQuestionsFIN = new String[20][20];
    /**
     * Starting row value for filling in the arrays.
     */
    public int row = 0;
    /**
     * Starting column value for filling in the arrays.
     */
    public int column = 0;
    /**
     * Loading screen of the game.
     */
    public fi.tamk.mindpuzzle.LoadingScreen loadingScreen;
    /**
     * Screen for main menu.
     */
    public fi.tamk.mindpuzzle.MainMenuScreen mainMenuScreen;
    /**
     * Screen for changing settings in the main menu.
     */
    public SettingsScreen settingsScreen;
    /**
     * Screen for introduce the development team of the game.
     */
    public fi.tamk.mindpuzzle.CreditsScreen creditsScreen;
    /**
     * Screen for game instructions.
     */
    public fi.tamk.mindpuzzle.GameInstructionsScreen instructionsScreen;
    /**
     * Screen for room menu.
     */
    public fi.tamk.mindpuzzle.RoomMenuScreen roomMenuScreen;
    /**
     * Screen for sleep-themed room.
     */
    public SleepRoom sleepRoom;
    /**
     * Screen for food-themed room.
     */
    public fi.tamk.mindpuzzle.FoodRoom foodRoom;
    /**
     * Screen for social-themed room.
     */
    public SocialRoom socialRoom;
    /**
     * Screen for hobby-themed room.
     */
    public fi.tamk.mindpuzzle.HobbiesRoom hobbiesRoom;
    /**
     * Screen for sport-themed room.
     */
    public SportsRoom sportsRoom;
    /**
     * Screen for settings in the rooms.
     */
    public fi.tamk.mindpuzzle.SettingsPopUp settingsPopUp;
    /**
     * Screen for question screen.
     */
    public fi.tamk.mindpuzzle.QuestionScreen questionScreen;
    /**
     * Screen for answer screen.
     */
    public fi.tamk.mindpuzzle.AnswerScreen answerScreen;
    /**
     * The game's ending screen.
     */
    public fi.tamk.mindpuzzle.PartyScreen partyScreen;
    /**
     * The most recent screen is stored in the variable, which allows
     * the return to the previous screen from the answer screen.
     */
    public Screen previousScreen = mainMenuScreen;
    /**
     * The character clicked in the room is stored in this variable
     * so that the correct character is seen on the question and answer screen.
     */
    public static String previousCharacter = "";
    /**
     * Points in the game.
     */
    public static int points = 0;
    /**
     * Starting value for return variable of method
     * public static int getAnsweredQuestion(String room) {...}
     */
    public static int returnable = 0;
    /**
     * How many food-related questions have been answered.
     */
    public static int foodQuestionsAnswered = 0;
    /**
     * How many social-related questions have been answered.
     */
    public static int socialQuestionsAnswered = 0;
    /**
     * How many sport-related questions have been answered.
     */
    public static int sportQuestionsAnswered = 0;
    /**
     * How many hobby-related questions have been answered.
     */
    public static int hobbyQuestionsAnswered = 0;
    /**
     * How many sleep-related questions have been answered.
     */
    public static int sleepQuestionsAnswered = 0;
    /**
     * Variable for language settings.
     */
    public static String language = "";

    /**
     * Initializes objects, font and language.
     * Handles the text files and sends them to MainMenuScreen.
     * And finally sets the screen to loading screen.
     */
    @Override
    public void create() {
        assets = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        batch = new SpriteBatch();

        initFonts();
        getLanguage();

        fileEN = Gdx.files.internal("questions/questionsEN.txt");
        initTextFile(socialQuestions, "SOCIAL", fileEN);
        initTextFile(sleepQuestions, "SLEEP", fileEN);
        initTextFile(sportQuestions, "SPORTS", fileEN);
        initTextFile(hobbyQuestions, "HOBBIES", fileEN);
        initTextFile(foodQuestions, "FOOD", fileEN);
        fi.tamk.mindpuzzle.MainMenuScreen.receiveENQuestions(socialQuestions, sleepQuestions, sportQuestions, hobbyQuestions, foodQuestions);

        fileFIN = Gdx.files.internal("questions/questionsFIN.txt");
        initTextFile(socialQuestionsFIN, "SOCIAL", fileFIN);
        initTextFile(sleepQuestionsFIN, "SLEEP", fileFIN);
        initTextFile(sportQuestionsFIN, "SPORTS", fileFIN);
        initTextFile(hobbyQuestionsFIN, "HOBBIES", fileFIN);
        initTextFile(foodQuestionsFIN, "FOOD", fileFIN);
        fi.tamk.mindpuzzle.MainMenuScreen.receiveFINQuestions(socialQuestionsFIN, sleepQuestionsFIN, sportQuestionsFIN, hobbyQuestionsFIN, foodQuestionsFIN);

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

    /**
     * Uses the currently displayed screen's render()-method
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Introduces and initializes fonts
     */
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

    /**
     * Initializes the text files.
     *
     * @param array     the array in which questions are stored
     * @param theme     the theme to be processed within the text file
     * @param file      the text file to be processed
     */
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

    /**
     * Adds line breaks to String lines.
     *
     * @param array     the array in which questions are stored
     * @param line      the line to which line breaks are added
     * @param columnNo  the value of the column in the array to which the processed line will be added
     */
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

    /**
     * Sets the previous visible screen.
     *
     * @param prev  the previous visible screen to set
     */
    public void setPreviousScreen(Screen prev) {
        previousScreen = prev;
    }

    /**
     * Returns the previous visible screen.
     *
     * @return previous visible creen
     */
    public Screen getPreviousScreen() {
        return previousScreen;
    }

    /**
     * Sets the previous clicked character.
     *
     * @param character the character to set
     */
    public static void setPreviousCharacter(String character) {
        previousCharacter = character;
    }

    /**
     * Returns the previous clicked character.
     *
     * @return previous clicked character
     */
    public static String getCharacter() {
        return previousCharacter;
    }

    /**
     * Adds the amount of answered questions in the certain room.
     *
     * @param room the room to whose amount of the questions answered is increased
     */
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

    /**
     * Returns the number of questions have been answered in certain room.
     *
     * @return integer value of answered question between 0-5
     */
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

    /**
     * Sets the language of the game and
     * saves it to the Preferences file.
     *
     * @param locale language to set
     */
    public static void setLanguage(Locale locale) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        language = locale.toString();
        prefs.putString("language", language);
        prefs.flush();
    }

    /**
     * Returns the current language of the game.
     *
     * @return current language
     */
    public static String getLanguage() {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        Locale locale = Locale.getDefault();
        String defaultLanguage = locale.toString();

        language = prefs.getString("language", defaultLanguage);

        return language;
    }

    /**
     * Increases the points by one and saves
     * the total score to the Preferences file.
     */
    public void addPoint() {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        points++;
        prefs.putInteger("points", points);
        prefs.flush();
    }

    /**
     * Returns current score.
     *
     * @return points to return between 0-25
     */
    public static int getPoints() {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        points = prefs.getInteger("points", points);

        return points;
    }

    /**
     * Saves character's status into the Preferences file.
     *
     * @param character     character that has been clicked
     * @param roomCharacter room where the character is
     */
    public void saveCharacter(String character, Boolean roomCharacter) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        prefs.putBoolean(character, roomCharacter);
        prefs.flush();
    }

    /**
     * Gets and returns the saved boolean value for the characters visibility.
     *
     * @param character     the character whose status is being asked
     * @param roomCharacter default value if there is no data stored in Preferences file yet
     * @return if the character should be visible in the rooms or not
     */
    public boolean openCharacters(String character, Boolean roomCharacter) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        Boolean isCharacterVisible  = prefs.getBoolean(character, roomCharacter);

        return isCharacterVisible;
    }

    /**
     * Resets the game.
     */
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

    /**
     * Disposes SpriteBatch, BitmapFonts, AssetManager and classes' objects.
     */
    @Override
    public void dispose() {
        batch.dispose();
        font20.dispose();
        font30.dispose();
        font40.dispose();
        font50.dispose();
        font60.dispose();
        assets.dispose();
        answerScreen.dispose();
        creditsScreen.dispose();
        foodRoom.dispose();
        instructionsScreen.dispose();
        hobbiesRoom.dispose();
        loadingScreen.dispose();
        mainMenuScreen.dispose();
        partyScreen.dispose();
        questionScreen.dispose();
        roomMenuScreen.dispose();
        settingsPopUp.dispose();
        settingsScreen.dispose();
        sleepRoom.dispose();
        socialRoom.dispose();
        sportsRoom.dispose();
    }
}
