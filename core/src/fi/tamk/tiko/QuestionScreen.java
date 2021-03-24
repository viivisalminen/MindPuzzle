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

    private TextButton buttonA, buttonB, buttonC, buttonX;
    private Texture characterTxt;
    private Rectangle characterRec;

    /*String question = "123456789123456789\nWhat should I do?";
    String optionA = "A ) 123456789123456789\n123456789123456789";
    String optionB = "B ) 123456789123456789\n123456789123456789";
    String optionC = "C ) 123456789123456789\n123456789123456789";*/

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
        System.out.println("Question screen");
        Gdx.input.setInputProcessor(stage); //Allows input to Scene2D components
        stage.clear();

        characterTxt = app.assets.get("images/skullwolf.png", Texture.class);
        characterRec = new Rectangle(0,0,characterTxt.getWidth() * 0.75f, characterTxt.getHeight() * 0.75f);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/popUpBackground.jpg", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.music.play();
        }
    }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        float buttonHeight = MindPuzzle.VIRTUAL_HEIGHT * 0.1f;
        float buttonWidth = MindPuzzle.VIRTUAL_WIDTH * 0.75f;

        buttonA = new TextButton(MainMenuScreen.getOptionA(), skin, "default");
        buttonA.getLabel().setFontScale(2, 2);
        buttonA.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.275f);
        buttonA.setSize(buttonWidth, buttonHeight);
        buttonA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.setPlayersAnswer("a");
                app.setScreen(app.answerScreen);
            }
        });

        buttonB = new TextButton(MainMenuScreen.getOptionB(), skin, "default");
        buttonB.getLabel().setFontScale(2, 2);
        buttonB.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.175f);
        buttonB.setSize(buttonWidth, buttonHeight);
        buttonB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.setPlayersAnswer("b");
                app.setScreen(app.answerScreen);
            }
        });

        buttonC = new TextButton(MainMenuScreen.getOptionC(), skin, "default");
        buttonC.getLabel().setFontScale(2, 2);
        buttonC.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.075f);
        buttonC.setSize(buttonWidth, buttonHeight);
        buttonC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.setPlayersAnswer("c");
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

        stage.addActor(buttonA);
        stage.addActor(buttonB);
        stage.addActor(buttonC);
        stage.addActor(buttonX);
    }

    // Calls every actor's act()-method that has added to the stage.
    private void update(float delta) {
        stage.act(delta);
    }

    // Called when the screen should render itself.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        app.batch.begin();
        app.font40.draw(app.batch, MainMenuScreen.getQuestion(), Gdx.graphics.getWidth() * 0.1f,Gdx.graphics.getHeight() * 0.5f);

        app.batch.draw(characterTxt, MindPuzzle.VIRTUAL_WIDTH * 0.3f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f, characterRec.width, characterRec.height);
        app.font30.draw(app.batch, "Screen: Question screen", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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