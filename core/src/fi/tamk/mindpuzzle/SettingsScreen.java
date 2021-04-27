package fi.tamk.mindpuzzle;

import com.badlogic.gdx.Gdx;
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
 * SettingsScreen is the settings view that opens from the main menu.
 */
public class SettingsScreen extends ScreenAdapter {
    /**
     * Class MindPuzzle object that allows to set screen from inside this class.
     */
    private final fi.tamk.mindpuzzle.MindPuzzle app;
    /**
     * A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
     */
    private Stage stage;
    /**
     * Positions the background image to the Screen.
     */
    private Table background;
    /**
     * ImageButton for getting back to room menu.
     */
    private ImageButton menuButton;
    /**
     * ImageButton for setting sounds on.
     */
    private ImageButton soundsONButton;
    /**
     * ImageButton for setting sounds off.
     */
    private ImageButton soundsOFFButton;
    /**
     * ImageButton for setting music on.
     */
    private ImageButton musicONButton;
    /**
     * ImageButton for setting music off.
     */
    private ImageButton musicOFFButton;
    /**
     * ImageButton for setting the game language to Finnish.
     */
    private ImageButton finONButton;
    /**
     * ImageButton for setting the game language to English.
     */
    private ImageButton finOFFButton;
    /**
     * ImageButton for setting the game language to English.
     */
    private ImageButton enONButton;
    /**
     * ImageButton for setting the game language to Finnish.
     */
    private ImageButton enOFFButton;
    /**
     * Texture used in menu-button when button is not touched.
     */
    private Texture menu;
    /**
     * Texture used in sounds on-button when button is not touched.
     */
    private Texture soundsON;
    /**
     * Texture used in sounds off-button when button is not touched.
     */
    private Texture soundsOFF;
    /**
     * Texture used in music on-button when button is not touched.
     */
    private Texture musicON;
    /**
     * Texture used in music off-button when button is not touched.
     */
    private Texture musicOFF;
    /**
     * Texture used in finnish on-button when button is not touched.
     */
    private Texture finON;
    /**
     * Texture used in finnish off-button when button is not touched.
     */
    private Texture finOFF;
    /**
     * Texture used in english on-button when button is not touched.
     */
    private Texture enON;
    /**
     * Texture used in english off-button when button is not touched.
     */
    private Texture enOFF;
    /**
     * Texture used in menu-button when button is touched.
     */
    private Texture menuPressed;
    /**
     * Texture used in sounds on-button when button is touched.
     */
    private Texture soundsONPressed;
    /**
     * Texture used in sounds off-button when button is touched.
     */
    private Texture soundsOFFPressed;
    /**
     * Texture used in music on-button when button is touched.
     */
    private Texture musicONPressed;
    /**
     * Texture used in music off-button when button is touched.
     */
    private Texture musicOFFPressed;
    /**
     * Texture used in finnish on-button when button is touched.
     */
    private Texture finONPressed;
    /**
     * Texture used in finnish off-button when button is touched.
     */
    private Texture finOFFPressed;
    /**
     * Texture used in english on-button when button is touched.
     */
    private Texture enONPressed;
    /**
     * Texture used in english off-button when button is touched.
     */
    private Texture enOFFPressed;

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public SettingsScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(app.VIRTUAL_WIDTH, app.VIRTUAL_HEIGHT, app.camera));
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

        if(app.getLanguage().equals("fi_FI")) {
            menu = app.assets.get("images/Painonapit/Paavalikko.png", Texture.class);
            menuPressed = app.assets.get("images/Painonapit/PaavalikkoPainettu.png", Texture.class);
            soundsON = app.assets.get("images/Painonapit/PeliaanetON.png", Texture.class);
            soundsONPressed = app.assets.get("images/Painonapit/PeliaanetONPainettu.png", Texture.class);
            soundsOFF = app.assets.get("images/Painonapit/PeliaanetOFF.png", Texture.class);
            soundsOFFPressed = app.assets.get("images/Painonapit/PeliaanetOFFPainettu.png", Texture.class);
            musicON = app.assets.get("images/Painonapit/MusiikkiON.png", Texture.class);
            musicONPressed = app.assets.get("images/Painonapit/MusiikkiONPainettu.png", Texture.class);
            musicOFF = app.assets.get("images/Painonapit/MusiikkiOFF.png", Texture.class);
            musicOFFPressed = app.assets.get("images/Painonapit/Musiikki_OFF2.png", Texture.class);
            finON = app.assets.get("images/Painonapit/SuomiON.png", Texture.class);
            finONPressed = app.assets.get("images/Painonapit/SuomiONPainettu.png", Texture.class);
            finOFF = app.assets.get("images/Painonapit/SuomiOFF.png", Texture.class);
            finOFFPressed = app.assets.get("images/Painonapit/SuomiOFFPainettu.png", Texture.class);
            enON = app.assets.get("images/Painonapit/EnglantiON.png", Texture.class);
            enONPressed = app.assets.get("images/Painonapit/EnglantiONPainettu.png", Texture.class);
            enOFF = app.assets.get("images/Painonapit/EnglantiOFF.png", Texture.class);
            enOFFPressed = app.assets.get("images/Painonapit/EnglantiOFFPainettu.png", Texture.class);
        } else {
            menu = app.assets.get("images/Buttons/Menu.png", Texture.class);
            menuPressed = app.assets.get("images/Buttons/MenuPressed.png", Texture.class);
            soundsON = app.assets.get("images/Buttons/SoundsON_Settings.png", Texture.class);
            soundsONPressed = app.assets.get("images/Buttons/SoundsON_SettingsPressed.png", Texture.class);
            soundsOFF = app.assets.get("images/Buttons/SoundsOFF_Settings.png", Texture.class);
            soundsOFFPressed = app.assets.get("images/Buttons/SoundsOFF_SettingsPressed.png", Texture.class);
            musicON = app.assets.get("images/Buttons/MusicON_Settings.png", Texture.class);
            musicONPressed = app.assets.get("images/Buttons/MusicON_SettingsPressed.png", Texture.class);
            musicOFF = app.assets.get("images/Buttons/MusicOFF_Settings.png", Texture.class);
            musicOFFPressed = app.assets.get("images/Buttons/MusicOFF_SettingsPressed.png", Texture.class);
            finON = app.assets.get("images/Buttons/FinnishON_Settings.png", Texture.class);
            finONPressed = app.assets.get("images/Buttons/FinnishON_SettingsPressed.png", Texture.class);
            finOFF = app.assets.get("images/Buttons/FinnishOFF_Settings.png", Texture.class);
            finOFFPressed = app.assets.get("images/Buttons/FinnishOFF_SettingsPressed.png", Texture.class);
            enON = app.assets.get("images/Buttons/EnglishON_Settings.png", Texture.class);
            enONPressed = app.assets.get("images/Buttons/EnglishON_SettingsPressed.png", Texture.class);
            enOFF = app.assets.get("images/Buttons/EnglishOFF_Settings.png", Texture.class);
            enOFFPressed = app.assets.get("images/Buttons/EnglishOFF_SettingsPressed.png", Texture.class);
        }

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("images/background2.png"))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(app.mainMenuScreen.getMusic()) {
            app.mainMenuScreen.musicOn();
        }
    }

    /**
     * Initializes the buttons used in this screen.
     */
    private void initButtons() {
        menuButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(menu)),
                new TextureRegionDrawable(new TextureRegion(menuPressed))
        );
        menuButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.8f);
        menuButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        soundsONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(soundsON)),
                new TextureRegionDrawable(new TextureRegion(soundsONPressed))
        );
        soundsONButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.5f);
        soundsONButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        soundsONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.mainMenuScreen.soundEffectOff();
                stage.addActor(soundsOFFButton);
                show();
            }
        });

        soundsOFFButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(soundsOFF)),
                new TextureRegionDrawable(new TextureRegion(soundsOFFPressed))
        );
        soundsOFFButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.5f);
        soundsOFFButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        soundsOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.mainMenuScreen.soundEffectOn();
                stage.addActor(soundsONButton);
                show();
            }
        });

        musicONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(musicON)),
                new TextureRegionDrawable(new TextureRegion(musicONPressed))
        );
        musicONButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.4f);
        musicONButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        musicONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.mainMenuScreen.musicOff();
                stage.addActor(musicOFFButton);
                show();
            }
        });

        musicOFFButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(musicOFF)),
                new TextureRegionDrawable(new TextureRegion(musicOFFPressed))
        );
        musicOFFButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.4f);
        musicOFFButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        musicOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.mainMenuScreen.musicOn();
                stage.addActor(musicONButton);
                show();
            }
        });

        finONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(finON)),
                new TextureRegionDrawable(new TextureRegion(finONPressed))
        );
        finONButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.3f);
        finONButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        finONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
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
        finOFFButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.3f);
        finOFFButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        finOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
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
        enONButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.2f);
        enONButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        enONButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
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
        enOFFButton.setPosition(app.VIRTUAL_WIDTH * 0.125f, app.VIRTUAL_HEIGHT * 0.2f);
        enOFFButton.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        enOFFButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setLanguage(new Locale("en", "US"));
                stage.addActor(enONButton);
                show();
            }
        });

        if(app.mainMenuScreen.getSound()) {
            stage.addActor(soundsONButton);
        }
        else if (!app.mainMenuScreen.getSound()){
            stage.addActor(soundsOFFButton);
        }

        if(app.mainMenuScreen.getMusic()) {
            stage.addActor(musicONButton);
        }
        else if (!app.mainMenuScreen.getMusic()){
            stage.addActor(musicOFFButton);
        }

        if(app.getLanguage().equals("fi_FI")) {
            stage.addActor(finONButton);
            stage.addActor(enOFFButton);
        } else if (!(app.getLanguage().equals("fi_FI"))) {
            stage.addActor(finOFFButton);
            stage.addActor(enONButton);
        }

        stage.addActor(menuButton);
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
    public void hide() { app.mainMenuScreen.saveSettings(app.mainMenuScreen.getMusic(), app.mainMenuScreen.getSound()); }

    /**
     * Disposes the stage and all its actors.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();

        //menu.dispose();
        //soundsON.dispose();
        //soundsOFF.dispose();
        //musicON.dispose();
        //musicOFF.dispose();
        //finON.dispose();
        //finOFF.dispose();
        //enON.dispose();
        //enOFF.dispose();
        //menuPressed.dispose();
        //soundsONPressed.dispose();
        //soundsOFFPressed.dispose();
        //musicONPressed.dispose();
        //musicOFFPressed.dispose();
        //finONPressed.dispose();
        //finOFFPressed.dispose();
        //enONPressed.dispose();
        //enOFFPressed.dispose();
    }
}
