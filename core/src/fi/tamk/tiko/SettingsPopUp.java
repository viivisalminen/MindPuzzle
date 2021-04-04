package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class SettingsPopUp implements Screen {
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

    private TextButton buttonMusic, buttonSounds, buttonFin, buttonEng, buttonX;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public SettingsPopUp(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/roomBackground.png", Texture.class))));
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
        float buttonSize = MindPuzzle.VIRTUAL_WIDTH * 0.075f;

        buttonMusic = new TextButton("M", skin, "default");
        buttonMusic.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.4f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonMusic.setSize(buttonSize, buttonSize);
        buttonMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.musicOff();
            }
        });

        buttonSounds = new TextButton("S", skin, "default");
        buttonSounds.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonSounds.setSize(buttonSize, buttonSize);
        buttonSounds.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.soundEffectOff();
            }
        });

        buttonFin = new TextButton("Fi", skin, "default");
        buttonFin.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.6f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonFin.setSize(buttonSize, buttonSize);
        buttonFin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
            }
        });

        buttonEng = new TextButton("En", skin, "default");
        buttonEng.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.7f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonEng.setSize(buttonSize, buttonSize);
        buttonEng.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
            }
        });

        buttonX = new TextButton("X", skin, "default");
        buttonX.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.8f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonX.setSize(buttonSize, buttonSize);
        buttonX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.getPreviousScreen());
            }
        });

        stage.addActor(buttonMusic);
        stage.addActor(buttonSounds);
        stage.addActor(buttonFin);
        stage.addActor(buttonEng);
        stage.addActor(buttonX);
    }

    // Called when the screen should render itself.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calls every actor's act()-method that has added to the stage.
        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();
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

