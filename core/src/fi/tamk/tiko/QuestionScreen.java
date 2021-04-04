package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    // Renders points, lines, shape outlines and filled shapes.
    private ShapeRenderer shapeRenderer;

    private TextButton questionButton, buttonA, buttonB, buttonC, buttonX;
    private Texture characterTxt;
    private Rectangle characterRec;

    public int row = 0;
    public static String question = "";
    public static String optionA = "";
    public static String optionB = "";
    public static String optionC = "";
    public static String rightAnswer = "";
    public static String playersAnswer = "";


    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public QuestionScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); //Allows input to Scene2D components
        stage.clear();

        getCharacter();
        characterRec = new Rectangle(0,0,Gdx.graphics.getWidth() * 0.62f, Gdx.graphics.getHeight() * 0.42f);

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
            MainMenuScreen.music.play();
        }
    }

    private void getCharacter() {
        if(app.getCharacter().equals("bird")) {
            characterTxt = app.assets.get("images/Characters/bird.png", Texture.class);
        } else if (app.getCharacter().equals("browncat")) {
            characterTxt = app.assets.get("images/Characters/browncat.png", Texture.class);
        } else if (app.getCharacter().equals("fox")) {
            characterTxt = app.assets.get("images/Characters/fox.png", Texture.class);
        } else if (app.getCharacter().equals("hamster")) {
            characterTxt = app.assets.get("images/Characters/hamster.png", Texture.class);
        } else if (app.getCharacter().equals("lynx")) {
            characterTxt = app.assets.get("images/Characters/lynx.png", Texture.class);
        } else if (app.getCharacter().equals("mushroomguy")) {
            characterTxt = app.assets.get("images/Characters/mushroomguy.png", Texture.class);
        } else if (app.getCharacter().equals("robotcat")) {
            characterTxt = app.assets.get("images/Characters/robotcat.png", Texture.class);
        } else if (app.getCharacter().equals("skullwolf")) {
            characterTxt = app.assets.get("images/Characters/skullwolf.png", Texture.class);
        } else if (app.getCharacter().equals("swampmonster")) {
            characterTxt = app.assets.get("images/Characters/swampmonster.png", Texture.class);
        } else if (app.getCharacter().equals("yeti")) {
            characterTxt = app.assets.get("images/Characters/yeti.png", Texture.class);
        }
    }

    private void initQuestions(Screen prev) {
        if(row == 3) {
            row = 0;
        }

        if (prev.equals(app.socialRoom)) {
            question = MainMenuScreen.questionsAboutSocial[row][0];
            optionA = MainMenuScreen.questionsAboutSocial[row][1];
            optionB = MainMenuScreen.questionsAboutSocial[row][2];
            optionC = MainMenuScreen.questionsAboutSocial[row][3];
            rightAnswer = MainMenuScreen.questionsAboutSocial[row][4];
            row++;
        } else if (prev.equals(app.sleepRoom)) {
            question = MainMenuScreen.questionsAboutSleep[row][0];
            optionA = MainMenuScreen.questionsAboutSleep[row][1];
            optionB = MainMenuScreen.questionsAboutSleep[row][2];
            optionC = MainMenuScreen.questionsAboutSleep[row][3];
            rightAnswer = MainMenuScreen.questionsAboutSleep[row][4];
            row++;
        } else if (prev.equals(app.sportsRoom)) {
            question = MainMenuScreen.questionsAboutSports[row][0];
            optionA = MainMenuScreen.questionsAboutSports[row][1];
            optionB = MainMenuScreen.questionsAboutSports[row][2];
            optionC = MainMenuScreen.questionsAboutSports[row][3];
            rightAnswer = MainMenuScreen.questionsAboutSports[row][4];
            row++;
        } else if (prev.equals(app.hobbiesRoom)) {
            question = MainMenuScreen.questionsAboutHobbies[row][0];
            optionA = MainMenuScreen.questionsAboutHobbies[row][1];
            optionB = MainMenuScreen.questionsAboutHobbies[row][2];
            optionC = MainMenuScreen.questionsAboutHobbies[row][3];
            rightAnswer = MainMenuScreen.questionsAboutHobbies[row][4];
            row++;
        } else if (prev.equals(app.foodRoom)) {
            question = MainMenuScreen.questionsAboutFood[row][0];
            optionA = MainMenuScreen.questionsAboutFood[row][1];
            optionB = MainMenuScreen.questionsAboutFood[row][2];
            optionC = MainMenuScreen.questionsAboutFood[row][3];
            rightAnswer = MainMenuScreen.questionsAboutFood[row][4];
            row++;
        }
    }

    public static String getRightAnswer() {
        return rightAnswer;
    }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        float buttonHeight = MindPuzzle.VIRTUAL_HEIGHT * 0.1f;
        float buttonWidth = MindPuzzle.VIRTUAL_WIDTH * 0.75f;

        questionButton = new TextButton(question, skin, "default");
        questionButton.getLabel().setFontScale(1.5f, 1.5f);
        questionButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.375f);
        questionButton.setSize(buttonWidth, buttonHeight*1.5f);

        buttonA = new TextButton(optionA, skin, "default");
        buttonA.getLabel().setFontScale(1.5f, 1.5f);
        buttonA.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.275f);
        buttonA.setSize(buttonWidth, buttonHeight);
        buttonA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPlayersAnswer("a");
                app.setScreen(app.answerScreen);
            }
        });

        buttonB = new TextButton(optionB, skin, "default");
        buttonB.getLabel().setFontScale(1.5f, 1.5f);
        buttonB.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.175f);
        buttonB.setSize(buttonWidth, buttonHeight);
        buttonB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPlayersAnswer("b");
                app.setScreen(app.answerScreen);
            }
        });

        buttonC = new TextButton(optionC, skin, "default");
        buttonC.getLabel().setFontScale(1.5f, 1.5f);
        buttonC.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.075f);
        buttonC.setSize(buttonWidth, buttonHeight);
        buttonC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPlayersAnswer("c");
                app.setScreen(app.answerScreen);
            }
        });

        buttonX = new TextButton("X", skin, "default");
        buttonX.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.85f,MindPuzzle.VIRTUAL_HEIGHT * 0.35f);
        buttonX.setSize(MindPuzzle.VIRTUAL_HEIGHT * 0.05f, MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        buttonX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.previousScreen);
            }
        });

        stage.addActor(questionButton);
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
        app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.3f,Gdx.graphics.getHeight() * 0.5f, characterRec.width, characterRec.height);
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