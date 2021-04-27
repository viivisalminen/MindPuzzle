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
 * that contains metadata of the game and methods
 * the game is operated with.
 *
 * MindPuzzle includes information on what was the last visible screen
 * and character, how many questions in the rooms have been answered
 * and amount of the points. It also handles the text files, screen
 * switching, the reset of the game and the saving of language
 * settings and character status to the Preferences file.
 *
 * @author      Viivi Salminen
 * @version     2021.2704
 * @since       15.0.1
 */
public class MindPuzzle extends Game {
    /**
     * The Viewport's width.
     */
    public int VIRTUAL_WIDTH = 1080;
    /**
     * The Viewport's height.
     */
    public int VIRTUAL_HEIGHT = 1920;
    /**
     * A camera with orthographic projection.
     */
    public OrthographicCamera camera;
    /**
     * SpriteBatch handles drawing.
     */
    public SpriteBatch batch;
    /**
     * Bitmap font in size 20.
     */
    public BitmapFont font20;
    /**
     * Bitmap font in size 30.
     */
    public BitmapFont font30;
    /**
     * Bitmap font in size 40 and black color.
     */
    public BitmapFont font40;
    /**
     * Bitmap font in size 40 and white color.
     */
    public BitmapFont font40white;
    /**
     * Bitmap font in size 50 and black color.
     */
    public BitmapFont font50;
    /**
     * Bitmap font in size 50 and white color.
     */
    public BitmapFont font50white;
    /**
     * Bitmap font in size 60.
     */
    public BitmapFont font60;
    /**
     * Loads and stores assets like textures, sounds and music.
     */
    public AssetManager assets;
    /**
     * Text file containing questions in English.
     */
    public FileHandle fileEN;
    /**
     * Text file containing questions in Finnish.
     */
    public FileHandle fileFIN;
    /**
     * 2D array for English questions about relationships and emotions.
     */
    public String[][] socialQuestions = new String[20][20];
    /**
     * 2D array for English questions about sleep and rest.
     */
    public String[][] sleepQuestions = new String[20][20];
    /**
     * 2D array for English questions about exercise.
     */
    public String[][] sportQuestions = new String[20][20];
    /**
     * 2D array for English questions about hobbies.
     */
    public String[][] hobbyQuestions = new String[20][20];
    /**
     * 2D array for English questions about food and eating habits.
     */
    public String[][] foodQuestions = new String[20][20];
    /**
     * 2D array for Finnish questions about relationships and emotions.
     */
    public String[][] socialQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about sleep and rest.
     */
    public String[][] sleepQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about exercise.
     */
    public String[][] sportQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about hobbies.
     */
    public String[][] hobbyQuestionsFIN = new String[20][20];
    /**
     * 2D array for Finnish questions about food and eating habits.
     */
    public String[][] foodQuestionsFIN = new String[20][20];
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
    public LoadingScreen loadingScreen;
    /**
     * Screen for main menu.
     */
    public MainMenuScreen mainMenuScreen;
    /**
     * Screen for changing settings in the main menu.
     */
    public SettingsScreen settingsScreen;
    /**
     * Screen for introducing the development team of the game.
     */
    public CreditsScreen creditsScreen;
    /**
     * Screen for game instructions.
     */
    public GameInstructionsScreen instructionsScreen;
    /**
     * Screen for room menu.
     */
    public RoomMenuScreen roomMenuScreen;
    /**
     * Screen for sleep-themed room.
     */
    public SleepRoom sleepRoom;
    /**
     * Screen for food-themed room.
     */
    public FoodRoom foodRoom;
    /**
     * Screen for social-themed room.
     */
    public SocialRoom socialRoom;
    /**
     * Screen for hobby-themed room.
     */
    public HobbiesRoom hobbiesRoom;
    /**
     * Screen for sport-themed room.
     */
    public SportsRoom sportsRoom;
    /**
     * Screen for settings in the rooms.
     */
    public SettingsPopUp settingsPopUp;
    /**
     * Screen for question screen.
     */
    public QuestionScreen questionScreen;
    /**
     * Screen for answer screen.
     */
    public AnswerScreen answerScreen;
    /**
     * The game's ending screen.
     */
    public PartyScreen partyScreen;
    /**
     * The most recent screen is stored in the variable, which allows
     * the return to the previous screen from the answer screen.
     */
    public Screen previousScreen;
    /**
     * The character clicked in the room is stored in this variable so that
     * the correct character is seen on the question and answer screens.
     */
    public String previousCharacter = "";
    /**
     * Points in the game.
     */
    public int points = 0;
    /**
     * Starting value for return variable of method
     * public static int getAnsweredQuestion(String room) {...}
     */
    public int returnable = 0;
    /**
     * How many food-related questions have been answered.
     */
    public int foodQuestionsAnswered = 0;
    /**
     * How many social-related questions have been answered.
     */
    public int socialQuestionsAnswered = 0;
    /**
     * How many sport-related questions have been answered.
     */
    public int sportQuestionsAnswered = 0;
    /**
     * How many hobby-related questions have been answered.
     */
    public int hobbyQuestionsAnswered = 0;
    /**
     * How many sleep-related questions have been answered.
     */
    public int sleepQuestionsAnswered = 0;
    /**
     * Object for language settings.
     */
    public String language = "";

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

        initFonts();
        getLanguage();

        fileEN = Gdx.files.internal("questions/questionsEN.txt");
        initTextFile(socialQuestions, "SOCIAL", fileEN);
        initTextFile(sleepQuestions, "SLEEP", fileEN);
        initTextFile(sportQuestions, "SPORTS", fileEN);
        initTextFile(hobbyQuestions, "HOBBIES", fileEN);
        initTextFile(foodQuestions, "FOOD", fileEN);
        mainMenuScreen.receiveENQuestions(socialQuestions,
                sleepQuestions, sportQuestions,
                hobbyQuestions, foodQuestions);

        fileFIN = Gdx.files.internal("questions/questionsFIN.txt");
        initTextFile(socialQuestionsFIN, "SOCIAL", fileFIN);
        initTextFile(sleepQuestionsFIN, "SLEEP", fileFIN);
        initTextFile(sportQuestionsFIN, "SPORTS", fileFIN);
        initTextFile(hobbyQuestionsFIN, "HOBBIES", fileFIN);
        initTextFile(foodQuestionsFIN, "FOOD", fileFIN);
        mainMenuScreen.receiveFINQuestions(socialQuestionsFIN,
                sleepQuestionsFIN, sportQuestionsFIN,
                hobbyQuestionsFIN, foodQuestionsFIN);

        previousScreen = mainMenuScreen;
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
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-Bold.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter20 =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter20.size = 20;
        parameter20.color = Color.BLACK;
        font20 = generator.generateFont(parameter20);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter30 =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter30.size = 30;
        parameter30.color = Color.BLACK;
        font30 = generator.generateFont(parameter30);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter40 =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter40.size = 40;
        parameter40.color = Color.BLACK;
        font40 = generator.generateFont(parameter40);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter40white =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter40white.size = 40;
        parameter40white.color = Color.WHITE;
        font40white = generator.generateFont(parameter40white);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter50 =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter50.size = 50;
        parameter50.color = Color.BLACK;
        font50 = generator.generateFont(parameter50);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter50white =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter50white.size = 50;
        parameter50white.color = Color.WHITE;
        font50white = generator.generateFont(parameter50white);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter60 =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
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
    private void initTextFile(String[][] array,
                              String theme, FileHandle file) {
        Scanner scanner = new Scanner(file.readString());
        String line = "";

        while(scanner.hasNext()) {
            line = scanner.nextLine();
            if (line.contains(theme)) {
                column = 0;
                line = scanner.nextLine();
                if (line.contains("?")) {
                    lineUpText(array, line, 0);
                }

                line = scanner.nextLine();
                if (line.contains("a)")) {
                    lineUpText(array, line, 1);
                }

                line = scanner.nextLine();
                if (line.contains("b)")) {
                    lineUpText(array, line, 2);
                }

                line = scanner.nextLine();
                if (line.contains("c)")) {
                    lineUpText(array, line, 3);
                }

                line = scanner.nextLine();
                if (line.contains("a") ||
                        line.contains("b") || line.contains("c")) {
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
     * @param columnNo  the value of the column in the array to which
     *                  the processed line will be added
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
    public void setPreviousCharacter(String character) {
        previousCharacter = character;
    }

    /**
     * Returns the previous clicked character.
     *
     * @return previous clicked character
     */
    public String getCharacter() {
        return previousCharacter;
    }

    /**
     * Adds the amount of answered questions in the certain room.
     *
     * @param room the room to whose amount of the questions
     *             answered is increased
     */
    public void addAnsweredQuestion(Screen room) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        if(room.equals(foodRoom)) {
            foodQuestionsAnswered++;
            prefs.putInteger("foodquestions", foodQuestionsAnswered);
        } else if (room.equals(socialRoom)) {
            socialQuestionsAnswered++;
            prefs.putInteger("socialquestions", socialQuestionsAnswered);
        } else if (room.equals(sleepRoom)) {
            sleepQuestionsAnswered++;
            prefs.putInteger("sleepquestions", sleepQuestionsAnswered);
        } else if (room.equals(hobbiesRoom)) {
            hobbyQuestionsAnswered++;
            prefs.putInteger("hobbyquestions", hobbyQuestionsAnswered);
        } else if (room.equals(sportsRoom)) {
            sportQuestionsAnswered++;
            prefs.putInteger("sportquestions", sportQuestionsAnswered);
        }

        prefs.flush();
    }

    /**
     * Returns the number of questions have been answered in certain room.
     *
     * @return integer value of answered question between 0-5
     */
    public int getAnsweredQuestion(String room) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        if(room.equals("food")) {
            returnable =
                    prefs.getInteger("foodquestions", foodQuestionsAnswered);
        } else if (room.equals("social")) {
            returnable =
                    prefs.getInteger("socialquestions", socialQuestionsAnswered);
        } else if (room.equals("sleep")) {
            returnable =
                    prefs.getInteger("sleepquestions", sleepQuestionsAnswered);
        } else if (room.equals("hobbies")) {
            returnable =
                    prefs.getInteger("hobbyquestions", hobbyQuestionsAnswered);
        } else if (room.equals("sports")) {
            returnable =
                    prefs.getInteger("sportquestions", sportQuestionsAnswered);
        }

        return returnable;
    }

    /**
     * Sets the language of the game and
     * saves it to the Preferences file.
     *
     * @param locale language to set
     */
    public void setLanguage(Locale locale) {
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
    public String getLanguage() {
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
    public int getPoints() {
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
     * @param roomCharacter default value if there is no data stored
     *                      in Preferences file yet
     * @return boolean if the character should be visible in the rooms or not
     */
    public boolean openCharacters(String character, Boolean roomCharacter) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        Boolean isCharacterVisible  = prefs.getBoolean(character, roomCharacter);

        return isCharacterVisible;
    }

    /**
     * Reset the game. However, retains the language and audio
     * settings made during the first round of play.
     */
    public void resetGame() {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
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
        prefs.putInteger("points", points);
        prefs.putInteger("foodquestions", foodQuestionsAnswered);
        prefs.putInteger("socialquestions", socialQuestionsAnswered);
        prefs.putInteger("sportquestions", sportQuestionsAnswered);
        prefs.putInteger("hobbyquestions", hobbyQuestionsAnswered);
        prefs.putInteger("sleepquestions", sleepQuestionsAnswered);

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
        font40white.dispose();
        font50.dispose();
        font50white.dispose();
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
