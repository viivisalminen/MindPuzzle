package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class SettingsScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;

    private ImageButton menuButton, soundsONButton, soundsOFFButton, musicONButton, musicOFFButton, finONButton, finOFFButton, enONButton, enOFFButton;
    private Texture menu, soundsON, soundsOFF, musicON, musicOFF, finON, finOFF, enON, enOFF;
    private Texture menuPressed, soundsONPressed, soundsOFFPressed, musicONPressed, musicOFFPressed, finONPressed, finOFFPressed, enONPressed, enOFFPressed;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public SettingsScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
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

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.music.play();
        }
    }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        menuButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(menu)),
                new TextureRegionDrawable(new TextureRegion(menuPressed))
        );
        menuButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.8f);
        menuButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        soundsONButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(soundsON)),
                new TextureRegionDrawable(new TextureRegion(soundsONPressed))
        );
        soundsONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        soundsONButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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
        soundsOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        soundsOFFButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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
        musicONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        musicONButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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
        musicOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        musicOFFButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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
        finONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        finONButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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
        finOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        finOFFButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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
        enONButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        enONButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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
        enOFFButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        enOFFButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
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

        stage.addActor(menuButton);
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
