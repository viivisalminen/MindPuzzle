package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

// MainMenuScreen is the main menu of the game including play, how to play, settings, credits and exit.
public class MainMenuScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;

    private ImageButton imagePlay, imageHowToPlay, imageCredits, imageSettings, imageExit;
    private Rectangle logoSmall, logoMedium, logoLarge;
    private Texture logo, imgPlay, imgHowToPlay, imgCredits, imgSettings, imgExit;
    private Texture imgPlayPressed, imgHowToPlayPressed, imgCreditsPressed, imgSettingsPressed, imgExitPressed;

    public static Music music, right, wrong;
    public static boolean musicPlaying = true;
    public static Sound sound;
    public static boolean soundOn = true;

    public static String[][] questionsAboutSocial = new String[20][20];
    public static String[][] questionsAboutSleep = new String[20][20];
    public static String[][] questionsAboutSports = new String[20][20];
    public static String[][] questionsAboutHobbies = new String[20][20];
    public static String[][] questionsAboutFood = new String[20][20];

    public static String[][] questionsAboutSocialFIN = new String[20][20];
    public static String[][] questionsAboutSleepFIN = new String[20][20];
    public static String[][] questionsAboutSportsFIN = new String[20][20];
    public static String[][] questionsAboutHobbiesFIN = new String[20][20];
    public static String[][] questionsAboutFoodFIN = new String[20][20];

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public MainMenuScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        logo = app.assets.get("images/logo.png", Texture.class);
        logoSmall = new Rectangle(0,0,logo.getWidth() * 0.5f, logo.getHeight() * 0.5f);
        logoMedium = new Rectangle(0,0,logo.getWidth() * 0.75f, logo.getHeight() * 0.75f);
        logoLarge = new Rectangle(0,0,logo.getWidth(), logo.getHeight());

        if(app.getLanguage().equals("fi_FI")) {
            imgCredits = app.assets.get("images/Painonapit/Tekijat.png", Texture.class);
            imgCreditsPressed = app.assets.get("images/Painonapit/TekijatPainettu.png", Texture.class);
            imgExit = app.assets.get("images/Painonapit/Lopeta.png", Texture.class);
            imgExitPressed = app.assets.get("images/Painonapit/LopetaPainettu.png", Texture.class);
            imgHowToPlay = app.assets.get("images/Painonapit/Ohjeet.png", Texture.class);
            imgHowToPlayPressed = app.assets.get("images/Painonapit/OhjeetPainettu.png", Texture.class);
            imgPlay = app.assets.get("images/Painonapit/Pelaa.png", Texture.class);
            imgPlayPressed = app.assets.get("images/Painonapit/PelaaPainettu.png", Texture.class);
            imgSettings = app.assets.get("images/Painonapit/Asetukset.png", Texture.class);
            imgSettingsPressed = app.assets.get("images/Painonapit/AsetuksetPainettu.png", Texture.class);
        } else {
            imgCredits = app.assets.get("images/Buttons/Credits.png", Texture.class);
            imgCreditsPressed = app.assets.get("images/Buttons/CreditsPressed.png", Texture.class);
            imgExit = app.assets.get("images/Buttons/Exit.png", Texture.class);
            imgExitPressed = app.assets.get("images/Buttons/ExitPressed.png", Texture.class);
            imgHowToPlay = app.assets.get("images/Buttons/HowToPlay.png", Texture.class);
            imgHowToPlayPressed = app.assets.get("images/Buttons/HowToPlayPressed.png", Texture.class);
            imgPlay = app.assets.get("images/Buttons/Play.png", Texture.class);
            imgPlayPressed = app.assets.get("images/Buttons/PlayPressed.png", Texture.class);
            imgSettings = app.assets.get("images/Buttons/Settings.png", Texture.class);
            imgSettingsPressed = app.assets.get("images/Buttons/SettingsPressed.png", Texture.class);
        }

        sound = app.assets.get("sounds/button.mp3", Sound.class);
        right = app.assets.get("sounds/right.mp3", Music.class);
        wrong = app.assets.get("sounds/wrong.mp3", Music.class);
        music = app.assets.get("sounds/background.mp3", Music.class);

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/background2.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();
        app.setPreviousScreen(app.mainMenuScreen);

        openSettings(musicPlaying, soundOn);
        app.getPoints();

        if(getMusic()) {
            music.play();
            music.setLooping(true);
            music.setVolume(1f);
        }
    }

    // MainMenuScreen.receiveQuestions(socialQuestions, sleepQuestions, sportQuestions, hobbyQuestions, foodQuestions);
    public static void receiveENQuestions(String[][] social, String[][] sleep, String[][] sport, String[][] hobby, String[][] food) {
        questionsAboutSocial = social.clone();
        questionsAboutSleep = sleep.clone();
        questionsAboutSports = sport.clone();
        questionsAboutHobbies = hobby.clone();
        questionsAboutFood = food.clone();
    }

    public static void receiveFINQuestions(String[][] social, String[][] sleep, String[][] sport, String[][] hobby, String[][] food) {
        questionsAboutSocialFIN = social.clone();
        questionsAboutSleepFIN = sleep.clone();
        questionsAboutSportsFIN = sport.clone();
        questionsAboutHobbiesFIN = hobby.clone();
        questionsAboutFoodFIN = food.clone();
    }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        imagePlay = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgPlay)),
                new TextureRegionDrawable(new TextureRegion(imgPlayPressed))
        );
        imagePlay.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        imagePlay.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imagePlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                app.setScreen(app.roomMenuScreen);
            }
        });

        imageHowToPlay = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgHowToPlay)),
                new TextureRegionDrawable(new TextureRegion(imgHowToPlayPressed))
        );
        imageHowToPlay.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        imageHowToPlay.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageHowToPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                app.setScreen(app.instructionsScreen);
            }
        });

        imageSettings = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSettings)),
                new TextureRegionDrawable(new TextureRegion(imgSettingsPressed))
        );
        imageSettings.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        imageSettings.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                app.setScreen(app.settingsScreen);
            }
        });

        imageCredits = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgCredits)),
                new TextureRegionDrawable(new TextureRegion(imgCreditsPressed))
        );
        imageCredits.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        imageCredits.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageCredits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                app.setScreen(app.creditsScreen);
            }
        });

        imageExit = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgExit)),
                new TextureRegionDrawable(new TextureRegion(imgExitPressed))
        );
        imageExit.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.1f);
        imageExit.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                Gdx.app.exit();
            }
        });

        stage.addActor(imagePlay);
        stage.addActor(imageHowToPlay);
        stage.addActor(imageCredits);
        stage.addActor(imageExit);
        stage.addActor(imageSettings);
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
            app.batch.draw(logo, Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getHeight() * 0.75f, logoSmall.width, logoSmall.height);
        } else if (Gdx.graphics.getWidth() >= 1000 && Gdx.graphics.getWidth() < 1200) {
            app.batch.draw(logo, MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.7f, logoMedium.width, logoMedium.height);
        } else if (Gdx.graphics.getWidth() >= 1200) {
            app.batch.draw(logo, ((Gdx.graphics.getWidth() / 2) - (logoLarge.width / 2)), MindPuzzle.VIRTUAL_HEIGHT * 0.7f, logoLarge.width, logoLarge.height);
        }


        app.batch.end();
    }

    public static void musicOn(){
        music.play();
        music.setLooping(true);
        music.setVolume(1f);
        musicPlaying = true;
    }

    public static void musicOff() {
        music.stop();
        musicPlaying = false;
    }

    public static Boolean getMusic() {
        return musicPlaying;
    }

    public static void soundEffectOn() {
        sound.play(1f);;
        soundOn = true;
    }

    public static void soundEffectOff() {
        sound.stop();
        soundOn = false;
    }

    public static Boolean getSound() {
        return soundOn;
    }

    public static void saveSettings(Boolean music, Boolean sound) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        prefs.putBoolean("music", musicPlaying);
        prefs.putBoolean("sound", soundOn);
        prefs.flush();
    }

    public static void openSettings(Boolean music, Boolean sound) {
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        Boolean musicBoolean  = prefs.getBoolean("music", true);
        if(musicBoolean) {
            musicOn();
        } else {
            musicOff();
        }

        Boolean soundBoolean  = prefs.getBoolean("sound", true);
        if(soundBoolean) {
            soundEffectOn();
        } else {
            soundEffectOff();
        }
    }

    public void resetSettings() {
        musicPlaying = true;
        soundOn = true;
        Preferences prefs = Gdx.app.getPreferences("MindPuzzlePreferences");
        prefs.clear();
        prefs.flush();
    }

    // Called when the Application is resized. This can happen at any point during
    // a non-paused state but will never happen before a call to create().
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

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

    // Called when the Application is destroyed. Disposes the stage and all its actors, sound and music objects.
    @Override
    public void dispose() {
        stage.dispose();
        sound.dispose();
        music.dispose();
        right.dispose();
        wrong.dispose();
    }
}

