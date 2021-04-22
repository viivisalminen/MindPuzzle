package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class QuestionScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;
    // A skin stores resources for UI widgets to use (texture regions, ninepatches, fonts, colors, etc)
    private Skin skin;

    private ImageButton buttonA, buttonB, buttonC;
    private TextButton buttonX;
    private Texture answerBackground, answerBackgroundPressed, bubble, characterTxt;
    private Rectangle characterRec;

    public int row = (int)(Math.random() * 15);
    public static String question = "";
    public static String optionA = "";
    public static String optionB = "";
    public static String optionC = "";
    public static String rightAnswer = "";
    public static String playersAnswer = "";
    public static String rightAnswerAsString = "";

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public QuestionScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //Allows input to Scene2D components
        stage.clear();

        getCharacter();
        characterRec = new Rectangle(0,0,characterTxt.getWidth() * 0.5f, characterTxt.getHeight() * 0.5f);
        bubble = app.assets.get("images/bubble.png", Texture.class);

        if(Gdx.graphics.getWidth() < 1000) {
            answerBackground = app.assets.get("images/answerMedium.png", Texture.class);
            answerBackgroundPressed = app.assets.get("images/answerMedium.png", Texture.class);
        } else if (Gdx.graphics.getWidth() >= 1000 && Gdx.graphics.getWidth() < 1200) {
            answerBackground = app.assets.get("images/answerMedium.png", Texture.class);
            answerBackgroundPressed = app.assets.get("images/answerMedium.png", Texture.class);
        } else if (Gdx.graphics.getWidth() >= 1200) {
            answerBackground = app.assets.get("images/answerLarge.png", Texture.class);
            answerBackgroundPressed = app.assets.get("images/answerLarge.png", Texture.class);
        }

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

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

    private void initQuestions(Screen prev) {
        if(row == 14) {
            row = 0;
        }

        String[][] array = new String[15][15];

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

    public static String getRightAnswer() {
        return rightAnswer;
    }

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

    public static String getRightAnswerAsString() { return rightAnswerAsString; }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        float xPos = ((MindPuzzle.VIRTUAL_WIDTH / 2) - (answerBackground.getWidth() / 2));

        buttonA = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(answerBackground)),
                new TextureRegionDrawable(new TextureRegion(answerBackgroundPressed))
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

        buttonX = new TextButton("X", skin, "default");
        buttonX.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.85f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        buttonX.setSize(MindPuzzle.VIRTUAL_HEIGHT * 0.075f, MindPuzzle.VIRTUAL_HEIGHT * 0.075f);
        buttonX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.previousScreen);
            }
        });

        stage.addActor(buttonA);
        stage.addActor(buttonB);
        stage.addActor(buttonC);
        stage.addActor(buttonX);
    }

    public static void setPlayersAnswer(String a) {
        playersAnswer = a;
    }

    public static String getPlayersAnswer() {
        return playersAnswer;
    }

    // Called when the screen should render itself.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calls every actor's act()-method that has added to the stage.
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        app.batch.begin();

        if(Gdx.graphics.getWidth() < 1000) {
            app.batch.draw(bubble,Gdx.graphics.getWidth() * 0.05f,Gdx.graphics.getHeight() * 0.715f, bubble.getWidth() * 0.65f, bubble.getHeight() * 0.65f);
            app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.45f,Gdx.graphics.getHeight() * 0.475f, characterRec.width * 0.6f, characterRec.height * 0.6f);
            app.font30.draw(app.batch, question,Gdx.graphics.getWidth() * 0.125f,Gdx.graphics.getHeight() * 0.915f);
            app.font30.draw(app.batch, optionA,Gdx.graphics.getWidth() * 0.15f,Gdx.graphics.getHeight() * 0.46f);
            app.font30.draw(app.batch, optionB,Gdx.graphics.getWidth() * 0.15f,Gdx.graphics.getHeight() * 0.3f);
            app.font30.draw(app.batch, optionC,Gdx.graphics.getWidth() * 0.15f,Gdx.graphics.getHeight() * 0.14f);
        } else if (Gdx.graphics.getWidth() >= 1000 && Gdx.graphics.getWidth() < 1200) {
            app.batch.draw(bubble,Gdx.graphics.getWidth() * 0.05f,Gdx.graphics.getHeight() * 0.715f, bubble.getWidth(), bubble.getHeight());
            app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.475f, characterRec.width, characterRec.height);
            app.font40.draw(app.batch, question,Gdx.graphics.getWidth() * 0.15f,Gdx.graphics.getHeight() * 0.925f);
            app.font40.draw(app.batch, optionA,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
            app.font40.draw(app.batch, optionB,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.265f);
            app.font40.draw(app.batch, optionC,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.125f);
        } else if (Gdx.graphics.getWidth() >= 1200) {
            app.batch.draw(bubble,Gdx.graphics.getWidth() * 0.05f,Gdx.graphics.getHeight() * 0.765f, bubble.getWidth(), bubble.getHeight());
            app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.475f, characterRec.width, characterRec.height);
            app.font40.draw(app.batch, question,Gdx.graphics.getWidth() * 0.15f,Gdx.graphics.getHeight() * 0.8f);
            app.font40.draw(app.batch, optionA,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
            app.font40.draw(app.batch, optionB,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.265f);
            app.font40.draw(app.batch, optionC,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.125f);
        }
        app.batch.end();
    }

    // Called when the Application is resized. This can happen at any point during
    // a non-paused state but will never happen before a call to create().
    @Override
    public void resize(int width, int height) { }

    // Called when the Application is paused, usually when it's not active or visible on-screen.
    // An Application is also paused before it is destroyed.
    @Override
    public void pause() { }

    // Called when the Application is resumed from a paused state, usually when it regains focus.
    @Override
    public void resume() { }

    // Called when this screen is no longer the current screen for a Game.
    @Override
    public void hide() { }

    // Called when the Application is destroyed. Disposes the stage and all its actors.
    @Override
    public void dispose() {
        stage.dispose();
    }
}