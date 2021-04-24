package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Locale;

/**
 * SettingsPopUp is the settings view that opens from the rooms.
 */
public class SettingsPopUp extends ScreenAdapter {
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
     * X-button to exit the answer view.
     */
    private ImageButton xButton, soundsONButton, soundsOFFButton, musicONButton, musicOFFButton, finONButton, finOFFButton, enONButton, enOFFButton;
    /**
     * Textures used in ImageButtons when button is not touched.
     */
    private Texture exit, soundsON, soundsOFF, musicON, musicOFF, finON, finOFF, enON, enOFF;
    /**
     * Textures used in ImageButtons when button is touched.
     */
    private Texture exitPressed, soundsONPressed, soundsOFFPressed, musicONPressed, musicOFFPressed, finONPressed, finOFFPressed, enONPressed, enOFFPressed;

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public SettingsPopUp(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    /**
     * Sets the InputProcessor that will receive all touch and key input events.
     * Initializes the textures.
     * Gets the music's value from MainMenuScreen and sets music either on or off depending the returning value.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        exit = app.assets.get("images/RoomSettings/X.png", Texture.class);
        exitPressed = app.assets.get("images/RoomSettings/Xpressed.png", Texture.class);
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

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/popUpBackground.jpg", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.musicOn();
        }
    }

    /**
     * Initializes the buttons used in this screen.
     */
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

        xButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(exit)),
                new TextureRegionDrawable(new TextureRegion(exitPressed))
        );
        xButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.75f,MindPuzzle.VIRTUAL_HEIGHT * 0.65f);
        xButton.setSize(buttonSize, buttonSize);
        xButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.previousScreen);
            }
        });
        stage.addActor(xButton);
    }

    /**
     * Calls every actor's act()-method that has added to the stage.
     * Draws the stage and the ending string message on the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
     * Saves the settings to the Preferences file.
     */
    @Override
    public void hide() { MainMenuScreen.saveSettings(MainMenuScreen.getMusic(),MainMenuScreen.getSound()); }

    /**
     * Disposes the stage and all its actors.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
