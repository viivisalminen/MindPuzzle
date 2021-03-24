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

public class SettingsScreen implements Screen {
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

    private TextButton buttonSound, buttonMusic, buttonLanguage, buttonMenu;
    private TextButton soundPressed, musicPressed;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public SettingsScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        System.out.println("SETTINGS");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("images/background.png"))));
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
        buttonSound = new TextButton("Sound on", skin, "default");
        soundPressed = new TextButton("Sound off", skin, "default");
        buttonSound.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        buttonSound.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        soundPressed.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        soundPressed.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);

        if(MainMenuScreen.getSound()) {
            stage.addActor(buttonSound);
        }
        else if (!MainMenuScreen.getSound()){
            stage.addActor(soundPressed);
        }

        buttonSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                MainMenuScreen.soundEffectOff();
                stage.addActor(soundPressed);
            }
        });
        soundPressed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.soundEffectOn();
                MainMenuScreen.sound.play();
                stage.addActor(buttonSound);
            }
        });

        buttonMusic = new TextButton("Music on", skin, "default");
        musicPressed = new TextButton("Music off", skin, "default");
        buttonMusic.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        buttonMusic.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        musicPressed.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        musicPressed.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);

        if(MainMenuScreen.getMusic()) {
            stage.addActor(buttonMusic);
        }
        else if (!MainMenuScreen.getMusic()){
            stage.addActor(musicPressed);
        }

        buttonMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.musicOff();
                stage.addActor(musicPressed);
            }
        });
        musicPressed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.musicOn();
                stage.addActor(buttonMusic);
            }
        });

        buttonLanguage = new TextButton("Language", skin, "default");
        buttonLanguage.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonLanguage.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonLanguage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
            }
        });

        buttonMenu = new TextButton("Main Menu", skin, "default");
        buttonMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.1f);
        buttonMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.7f, MindPuzzle.VIRTUAL_WIDTH * 0.09f);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        stage.addActor(buttonLanguage);
        stage.addActor(buttonMenu);
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
        app.font30.draw(app.batch, "Screen: SETTINGS", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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
