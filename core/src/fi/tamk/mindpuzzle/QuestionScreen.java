package fi.tamk.mindpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * QuestionScreen shows the view for the character's multiple choice question.
 */
public class QuestionScreen extends ScreenAdapter {
    /**
     * Class MindPuzzle object that allows to set screen from inside this class.
     */
    private final MindPuzzle app;
    /**
     * A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
     */
    private Stage stage;
    /**
     * Positions the background image to the Screen.
     */
    private Table background;
    /**
     * ImageButton used as option a's button.
     */
    private ImageButton buttonA;
    /**
     * ImageButton used as option a's button.
     */
    private ImageButton buttonB;
    /**
     * ImageButton used as option a's button.
     */
    private ImageButton buttonC;
    /**
     * Texture used in buttonA, buttonB and buttonC.
     */
    private Texture answerBackground;
    /**
     * Texture used as speech bubble.
     */
    private Texture bubble;
    /**
     * Texture used as game character.
     */
    private Texture characterTxt;
    /**
     * Rectangle object to increase the size of the character image.
     */
    private Rectangle characterLarge;
    /**
     * Rectangle object to reduce the size of the character image.
     */
    private Rectangle characterSmall;
    /**
     * Rectangle object to increase the size of the speech bubble image.
     */
    private Rectangle bubbleLarge;
    /**
     * Rectangle object to reduce the size of the speech bubble image.
     */
    private Rectangle bubbleSmall;
    /**
     * Starting row value for getting questions from the arrays.
     */
    public int row = (int)(Math.random() * 20);
    /**
     * String object that gets the question from array.
     */
    public static String question = "";
    /**
     * String object that gets the option a) from array.
     */
    public static String optionA = "";
    /**
     * String object that gets the option b) from array.
     */
    public static String optionB = "";
    /**
     * String object that gets the option c) from array.
     */
    public static String optionC = "";
    /**
     * String object that gets the right answer as a letter a, b or c from array.
     */
    public static String rightAnswer = "";
    /**
     * String object that gets the right answer as String from array.
     */
    public static String rightAnswerAsString = "";
    /**
     * String object that gets the player's answer as a letter a, b or c.
     */
    public static String playersAnswer = "";

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public QuestionScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    /**
     * Sets the InputProcessor that will receive all touch and key input events.
     * Initializes the textures and question.
     * Gets the music's value from MainMenuScreen and sets music either on or off depending the returning value.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //Allows input to Scene2D components
        stage.clear();

        getCharacter();
        characterSmall = new Rectangle(0,0,characterTxt.getWidth() * 0.3f, characterTxt.getHeight() * 0.3f);
        characterLarge = new Rectangle(0,0,characterTxt.getWidth() * 0.45f, characterTxt.getHeight() * 0.45f);

        bubble = app.assets.get("images/bubble.png", Texture.class);
        bubbleSmall = new Rectangle(0,0,bubble.getWidth() * 0.675f, bubble.getHeight() * 0.77f);
        bubbleLarge = new Rectangle(0,0,bubble.getWidth(), bubble.getHeight() * 1.25f);

        if(Gdx.graphics.getWidth() < 1000) {
            answerBackground = app.assets.get("images/answerMedium.png", Texture.class);
        } else if (Gdx.graphics.getWidth() >= 1000 && Gdx.graphics.getWidth() < 1200) {
            answerBackground = app.assets.get("images/answerMedium.png", Texture.class);
        } else if (Gdx.graphics.getWidth() >= 1200) {
            answerBackground = app.assets.get("images/answerLarge.png", Texture.class);
        }

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/popUpBackground.jpg", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initQuestions(app.getPreviousScreen());
        initButtons();

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.musicOn();
            MainMenuScreen.music.setVolume(0.4f);
        }
    }

    /**
     * Initializes the character texture depending on which character was clicked.
     */
    private void getCharacter() {
        if(app.getCharacter().equals("bird")) {
            characterTxt = app.assets.get("images/Characters/bird.png", Texture.class);
        } else if (app.getCharacter().equals("browncat")) {
            characterTxt = app.assets.get("images/Characters/browncat.png", Texture.class);
        } else if (app.getCharacter().equals("bunny")) {
            characterTxt = app.assets.get("images/Characters/bunny.png", Texture.class);
        } else if (app.getCharacter().equals("bunnygrey")) {
            characterTxt = app.assets.get("images/Characters/bunnygrey.png", Texture.class);
        } else if (app.getCharacter().equals("cactusbuddy")) {
            characterTxt = app.assets.get("images/Characters/cactusbuddy.png", Texture.class);
        } else if (app.getCharacter().equals("demoncat")) {
            characterTxt = app.assets.get("images/Characters/demoncat.png", Texture.class);
        } else if (app.getCharacter().equals("fishy")) {
            characterTxt = app.assets.get("images/Characters/fishy.png", Texture.class);
        } else if (app.getCharacter().equals("fox")) {
            characterTxt = app.assets.get("images/Characters/fox.png", Texture.class);
        } else if (app.getCharacter().equals("ghost")) {
            characterTxt = app.assets.get("images/Characters/ghost.png", Texture.class);
        } else if (app.getCharacter().equals("griffinblue")) {
            characterTxt = app.assets.get("images/Characters/griffinblue.png", Texture.class);
        } else if (app.getCharacter().equals("griffinred")) {
            characterTxt = app.assets.get("images/Characters/griffinred.png", Texture.class);
        } else if (app.getCharacter().equals("hamster")) {
            characterTxt = app.assets.get("images/Characters/hamster.png", Texture.class);
        } else if (app.getCharacter().equals("leafdragon")) {
            characterTxt = app.assets.get("images/Characters/leafdragon.png", Texture.class);
        } else if (app.getCharacter().equals("lynx")) {
            characterTxt = app.assets.get("images/Characters/lynx.png", Texture.class);
        } else if (app.getCharacter().equals("mushroomguy")) {
            characterTxt = app.assets.get("images/Characters/mushroomguy.png", Texture.class);
        } else if (app.getCharacter().equals("robotcat")) {
            characterTxt = app.assets.get("images/Characters/robotcat.png", Texture.class);
        } else if (app.getCharacter().equals("skullbear")) {
            characterTxt = app.assets.get("images/Characters/skullbear.png", Texture.class);
        } else if (app.getCharacter().equals("skullwolf")) {
            characterTxt = app.assets.get("images/Characters/skullwolf.png", Texture.class);
        } else if (app.getCharacter().equals("sloth")) {
            characterTxt = app.assets.get("images/Characters/sloth.png", Texture.class);
        } else if (app.getCharacter().equals("snake")) {
            characterTxt = app.assets.get("images/Characters/snake.png", Texture.class);
        } else if (app.getCharacter().equals("swampmonster")) {
            characterTxt = app.assets.get("images/Characters/swampmonster.png", Texture.class);
        } else if (app.getCharacter().equals("wizardcat")) {
            characterTxt = app.assets.get("images/Characters/wizardcat.png", Texture.class);
        } else if (app.getCharacter().equals("wolf")) {
            characterTxt = app.assets.get("images/Characters/wolf.png", Texture.class);
        } else if (app.getCharacter().equals("wolfbrown")) {
            characterTxt = app.assets.get("images/Characters/wolfbrown.png", Texture.class);
        } else if (app.getCharacter().equals("yeti")) {
            characterTxt = app.assets.get("images/Characters/yeti.png", Texture.class);
        }
    }

    /**
     * Initializes the buttons used in this screen.
     */
    private void initButtons() {
        float xPos = ((MindPuzzle.VIRTUAL_WIDTH / 2) - (answerBackground.getWidth() / 2));

        buttonA = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(answerBackground))
        );
        buttonA.setPosition(xPos ,MindPuzzle.VIRTUAL_HEIGHT * 0.335f);
        buttonA.setSize(answerBackground.getWidth(), answerBackground.getHeight());
        buttonA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                setPlayersAnswer("a");
                app.setScreen(app.answerScreen);
            }
        });

        buttonB = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(answerBackground)));
        buttonB.setPosition(xPos,MindPuzzle.VIRTUAL_HEIGHT * 0.175f);
        buttonB.setSize(answerBackground.getWidth(), answerBackground.getHeight());
        buttonB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                setPlayersAnswer("b");
                app.setScreen(app.answerScreen);
            }
        });

        buttonC = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(answerBackground)));
        buttonC.setPosition(xPos,MindPuzzle.VIRTUAL_HEIGHT * 0.015f);
        buttonC.setSize(answerBackground.getWidth(), answerBackground.getHeight());
        buttonC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                setPlayersAnswer("c");
                app.setScreen(app.answerScreen);
            }
        });

        stage.addActor(buttonA);
        stage.addActor(buttonB);
        stage.addActor(buttonC);
    }

    /**
     * Initializes the question depending on which was the previous screen and
     * what is current language of the game.
     *
     * @param prev  previous Screen
     */
    private void initQuestions(Screen prev) {
        if(row == 19) {
            row = 0;
        }

        String[][] array = new String[20][20];

        if(app.getLanguage().equals("fi_FI")) {
            if (prev.equals(app.socialRoom)) {
                array = MainMenuScreen.questionsAboutSocialFIN;
            } else if (prev.equals(app.sleepRoom)) {
                array = MainMenuScreen.questionsAboutSleepFIN;
            } else if (prev.equals(app.sportsRoom)) {
                array = MainMenuScreen.questionsAboutSportsFIN;
            } else if (prev.equals(app.hobbiesRoom)) {
                array = MainMenuScreen.questionsAboutHobbiesFIN;
            } else if (prev.equals(app.foodRoom)) {
                array = MainMenuScreen.questionsAboutFoodFIN;
            }
        } else {
            if (prev.equals(app.socialRoom)) {
                array = MainMenuScreen.questionsAboutSocial;
            } else if (prev.equals(app.sleepRoom)) {
                array = MainMenuScreen.questionsAboutSleep;
            } else if (prev.equals(app.sportsRoom)) {
                array = MainMenuScreen.questionsAboutSports;
            } else if (prev.equals(app.hobbiesRoom)) {
                array = MainMenuScreen.questionsAboutHobbies;
            } else if (prev.equals(app.foodRoom)) {
                array = MainMenuScreen.questionsAboutFood;
            }
        }

        question = array[row][0];
        optionA = array[row][1];
        optionB = array[row][2];
        optionC = array[row][3];
        rightAnswer = array[row][4];
        row++;

        setRightAnswerAsString();
    }

    /**
     * Returns the right answer as a letter a, b or c.
     *
     * @return right answer as a single letter
     */
    public static String getRightAnswer() {
        return rightAnswer;
    }

    /**
     * Changes the right answer from a single letter to an entire string.
     */
    private void setRightAnswerAsString() {
        switch (rightAnswer) {
            case "a":
                rightAnswerAsString = optionA;
                break;
            case "b":
                rightAnswerAsString = optionB;
                break;
            case "c":
                rightAnswerAsString = optionC;
                break;
        }
    }

    /**
     * Returns the right answer as an entire string.
     *
     * @return right answer as string
     */
    public static String getRightAnswerAsString() { return rightAnswerAsString; }

    /**
     * Sets the player's answer as a letter a, b or c.
     *
     * @param letter player answer
     */
    public static void setPlayersAnswer(String letter) {
        playersAnswer = letter;
    }

    /**
     * Return the players answer as a letter a, b or c.
     *
     * @return player answer as a single letter
     */
    public static String getPlayersAnswer() {
        return playersAnswer;
    }

    /**
     * Calls every actor's act()-method that has added to the stage.
     * Draws the stage, speech bubble, character and the question on the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        app.batch.begin();
        if(Gdx.graphics.getWidth() < 1000) {
            app.batch.draw(bubble,stage.getViewport().getScreenWidth() * 0.05f,stage.getViewport().getScreenHeight() * 0.65f, bubbleSmall.width, bubbleSmall.height);
            app.batch.draw(characterTxt, stage.getViewport().getScreenWidth() * 0.5f,stage.getViewport().getScreenHeight() * 0.475f, characterSmall.width, characterSmall.height);
            app.font30.draw(app.batch, question,stage.getViewport().getScreenWidth() * 0.115f,stage.getViewport().getScreenHeight() * 0.91f);
            app.font30.draw(app.batch, optionA,stage.getViewport().getScreenWidth() * 0.1f,stage.getViewport().getScreenHeight() * 0.46f);
            app.font30.draw(app.batch, optionB,stage.getViewport().getScreenWidth() * 0.1f,stage.getViewport().getScreenHeight() * 0.3f);
            app.font30.draw(app.batch, optionC,stage.getViewport().getScreenWidth() * 0.1f,stage.getViewport().getScreenHeight() * 0.14f);
        } else {
            app.batch.draw(bubble,stage.getViewport().getScreenWidth() * 0.05f,stage.getViewport().getScreenHeight() * 0.65f, bubbleLarge.width, bubbleLarge.height);
            app.batch.draw(characterTxt, stage.getViewport().getScreenWidth() * 0.5f,stage.getViewport().getScreenHeight() * 0.475f, characterLarge.width, characterLarge.height);
            app.font40.draw(app.batch, question,stage.getViewport().getScreenWidth() * 0.115f,stage.getViewport().getScreenHeight() * 0.91f);
            app.font40.draw(app.batch, optionA,stage.getViewport().getScreenWidth() * 0.1f,stage.getViewport().getScreenHeight() * 0.46f);
            app.font40.draw(app.batch, optionB,stage.getViewport().getScreenWidth() * 0.1f,stage.getViewport().getScreenHeight() * 0.3f);
            app.font40.draw(app.batch, optionC,stage.getViewport().getScreenWidth() * 0.1f,stage.getViewport().getScreenHeight() * 0.14f);
        }
        app.batch.end();
    }

    /**
     * Resizes the viewport's dimensions based on the screen dimensions of
     * the device using the application.
     *
     * @param width     The viewport's width of the device
     * @param height    The viewport's height of the device
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Disposes the stage and all its actors.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();
        bubble.dispose();
        characterTxt.dispose();
        //answerBackground.dispose();
    }
}

