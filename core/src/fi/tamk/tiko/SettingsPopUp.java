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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Locale;

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

    private ImageButton soundsONButton, soundsOFFButton, musicONButton, musicOFFButton, finONButton, finOFFButton, enONButton, enOFFButton;
    private Texture soundsON, soundsOFF, musicON, musicOFF, finON, finOFF, enON, enOFF;
    private Texture soundsONPressed, soundsOFFPressed, musicONPressed, musicOFFPressed, finONPressed, finOFFPressed, enONPressed, enOFFPressed;
    private TextButton buttonX;

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

        soundsON = app.assets.get("images/RoomSettings/SoundsON_Room.png", Texture.class);
        soundsONPressed = app.assets.get("images/RoomSettings/SoundsON_RoomPressed.png", Texture.class);
        soundsOFF = app.assets.get("images/RoomSettings/SoundsOFF_Room.png", Texture.class);
        soundsOFFPressed = app.assets.get("images/RoomSettings/SoundsOFF_RoomPressed.png", Texture.class);
        musicON = app.assets.get("images/RoomSettings/MusicON_Room.png", Texture.class);
        musicONPressed = app.assets.get("images/RoomSettings/MusicON_RoomPressed.png", Texture.class);
        musicOFF = app.assets.get("images/RoomSettings/MusicOFF_Room.png", Texture.class);
        musicOFFPressed = app.assets.get("images/RoomSettings/MusicOFF_RoomPressed.png", Texture.class);
        finON = app.assets.get("images/RoomSettings/FiON_Room.png", Texture.class);
        finONPressed = app.assets.get("images/RoomSettings/FiON_RoomPressed.png", Texture.class);
        finOFF = app.assets.get("images/RoomSettings/FiOFF_Room.png", Texture.class);
        finOFFPressed = app.assets.get("images/RoomSettings/FiOFF_RoomPressed.png", Texture.class);
        enON = app.assets.get("images/RoomSettings/EnON_Room.png", Texture.class);
        enONPressed = app.assets.get("images/RoomSettings/EnON_RoomPressed.png", Texture.class);
        enOFF = app.assets.get("images/RoomSettings/EnOFF_Room.png", Texture.class);
        enOFFPressed = app.assets.get("images/RoomSettings/EnOFF_RoomPressed.png", Texture.class);

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
        int buttonSize = 180;

        soundsONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(soundsON)),
                new TextureRegionDrawable(new TextureRegion(soundsONPressed))
        );
        soundsONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        soundsONButton.setSize(buttonSize, buttonSize);
        soundsONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                MainMenuScreen.soundEffectOff();
                stage.addActor(soundsOFFButton);
                show();
            }
        });

        soundsOFFButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(soundsOFF)),
                new TextureRegionDrawable(new TextureRegion(soundsOFFPressed))
        );
        soundsOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        soundsOFFButton.setSize(buttonSize, buttonSize);
        soundsOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.soundEffectOn();
                MainMenuScreen.sound.play();
                stage.addActor(soundsONButton);
                show();
            }
        });

        musicONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(musicON)),
                new TextureRegionDrawable(new TextureRegion(musicONPressed))
        );
        musicONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.3f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        musicONButton.setSize(buttonSize, buttonSize);
        musicONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.musicOff();
                stage.addActor(musicOFFButton);
                show();
            }
        });

        musicOFFButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(musicOFF)),
                new TextureRegionDrawable(new TextureRegion(musicOFFPressed))
        );
        musicOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.3f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        musicOFFButton.setSize(buttonSize, buttonSize);
        musicOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenuScreen.musicOn();
                stage.addActor(musicONButton);
                show();
            }
        });

        finONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(finON)),
                new TextureRegionDrawable(new TextureRegion(finONPressed))
        );
        finONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        finONButton.setSize(buttonSize, buttonSize);
        finONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setLanguage(new Locale("en", "US"));
                stage.addActor(finOFFButton);
                show();

            }
        });

        finOFFButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(finOFF)),
                new TextureRegionDrawable(new TextureRegion(finOFFPressed))
        );
        finOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        finOFFButton.setSize(buttonSize, buttonSize);
        finOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setLanguage(new Locale("fi", "FI"));
                stage.addActor(finONButton);
                show();

            }
        });

        enONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(enON)),
                new TextureRegionDrawable(new TextureRegion(enONPressed))
        );
        enONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.3f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        enONButton.setSize(buttonSize, buttonSize);
        enONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setLanguage(new Locale("fi", "FI"));
                stage.addActor(enOFFButton);
                show();
            }
        });

        enOFFButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(enOFF)),
                new TextureRegionDrawable(new TextureRegion(enOFFPressed))
        );
        enOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.3f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        enOFFButton.setSize(buttonSize, buttonSize);
        enOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setLanguage(new Locale("en", "US"));
                stage.addActor(enONButton);
                show();
            }
        });

        if(MainMenuScreen.getSound()) {
            stage.addActor(soundsONButton);
        }
        else if (!MainMenuScreen.getSound()){
            stage.addActor(soundsOFFButton);
        }

        if(MainMenuScreen.getMusic()) {
            stage.addActor(musicONButton);
        }
        else if (!MainMenuScreen.getMusic()){
            stage.addActor(musicOFFButton);
        }

        if(app.getLanguage().equals("fi_FI")) {
            stage.addActor(finONButton);
            stage.addActor(enOFFButton);
        } else if (!(app.getLanguage().equals("fi_FI"))) {
            stage.addActor(finOFFButton);
            stage.addActor(enONButton);
        }

        buttonX = new TextButton("X", skin, "default");
        buttonX.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.85f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        buttonX.setSize(MindPuzzle.VIRTUAL_HEIGHT * 0.075f, MindPuzzle.VIRTUAL_HEIGHT * 0.075f);
        buttonX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.previousScreen);
            }
        });
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

