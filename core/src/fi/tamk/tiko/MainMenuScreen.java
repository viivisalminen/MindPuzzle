package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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

import java.util.Scanner;

// MainMenuScreen is the main menu of the game including play, how to play, settings, credits and exit.
public class MainMenuScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;

    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;
    // A skin stores resources for UI widgets to use (texture regions, ninepatches, fonts, colors, etc).
    private Skin skin;
    // Renders points, lines, shape outlines and filled shapes.
    private ShapeRenderer shapeRenderer;

    private Texture imgTxt;
    private Texture imgTxtPressed;

    private ImageButton imageButton;
    private TextButton buttonMenu, buttonPlay, buttonHowToPlay, buttonSettings, buttonCredits, buttonExit;

    public FileHandle file;
    public String text = "";
    public String [] textArray;
    public static String question = "";
    public static String optionA = "";
    public static String optionB = "";
    public static String optionC = "";
    public static String rightAnswer = "";
    public static String playersAnswer = "";
    public static Music music;
    public static boolean musicPlaying = true;
    public static Sound sound;
    public static boolean soundOn = true;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public MainMenuScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        System.out.println("MAIN MENU");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        imgTxt = app.assets.get("images/mushroom.png", Texture.class);
        imgTxtPressed = app.assets.get("images/mushroom.png", Texture.class);
        sound = app.assets.get("sounds/hitsound.wav", Sound.class);
        music = app.assets.get("sounds/mixkit-jk.mp3", Music.class);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/background.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();
        initTextFile();
        if(getMusic()) {
            music.play();
        }

        app.setPreviousScreen(app.mainMenuScreen);
    }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        // PLAYBUTTON HERE
        imageButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgTxt)),
                new TextureRegionDrawable(new TextureRegion(imgTxtPressed))
        );
        imageButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.15f, MindPuzzle.VIRTUAL_HEIGHT * 0.7f);
        imageButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                app.setScreen(app.roomMenuScreen);
            }
        });
        stage.addActor(imageButton);

        buttonHowToPlay = new TextButton("How to play", skin, "default");
        buttonHowToPlay.getLabel().setFontScale(3, 3);
        buttonHowToPlay.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.15f, MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        buttonHowToPlay.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonHowToPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
            }
        });

        buttonSettings = new TextButton("Settings", skin, "default");
        buttonSettings.getLabel().setFontScale(3, 3);
        buttonSettings.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.15f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        buttonSettings.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                app.setScreen(app.settingsScreen);
            }
        });

        buttonCredits = new TextButton("Credits", skin, "default");
        buttonCredits.getLabel().setFontScale(3, 3);
        buttonCredits.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.15f, MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonCredits.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonCredits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                app.setScreen(app.creditsScreen);
            }
        });

        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.getLabel().setFontScale(3, 3);
        buttonExit.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.15f, MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonExit.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getSound()) {
                    sound.play();
                }
                Gdx.app.exit();
            }
        });

        //stage.addActor(buttonPlay);
        stage.addActor(buttonHowToPlay);
        stage.addActor(buttonSettings);
        stage.addActor(buttonCredits);
        stage.addActor(buttonExit);

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
        app.font30.draw(app.batch, "Screen: MAIN MENU",MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        app.batch.end();
    }

    public static void musicOn(){
        music.play();
        music.setLooping(true);
        music.setVolume(0.05f);
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
        sound.play();
        soundOn = true;
    }

    public static void soundEffectOff() {
        sound.stop();
        soundOn = false;
    }

    public static Boolean getSound() {
        return soundOn;
    }


    public static String getQuestion() { return question; }

    public static String getOptionA() { return optionA; }

    public static String getOptionB() { return optionB; }

    public static String getOptionC() { return optionC; }

    // Sets the previous right answer.
    public static void setRightAnswer(String prev) {
        rightAnswer = prev;
    }
    // Returns the previous right answer.
    public static String getRightAnswer() {
        return rightAnswer;
    }

    // Sets the player's previous answer.
    public static void setPlayersAnswer(String prev) {
        playersAnswer = prev;
    }
    // Returns the player's previous answer.
    public static String getPlayersAnswer() {
        return playersAnswer;
    }

    private void initTextFile() {
        file = Gdx.files.internal("questions/questions.txt");
        //text = file.readString();
        //System.out.println(text);

        Scanner scanner = new Scanner(file.readString());
        String line = "";

        /*while(scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }*/
        int random = (int)(Math.random()*3+1);
        String randomNumber = Integer.toString(random);
        System.out.println(randomNumber);

        while(scanner.hasNext()){
            line = scanner.nextLine();

            if(line.contains(randomNumber) && line.contains("?")) {
                question = line;
                System.out.println("Kysymys:  "+question);
            }
            else if(line.contains("a)")) {
                optionA = line;
                System.out.println("a):  "+optionA);
            }
            else if(line.contains("b)")) {
                optionB = line;
                System.out.println("b):  "+optionB);
            }
            else if(line.contains("c)")) {
                optionC = line;
                System.out.println("c):  "+optionC);
            }
            else if(line.contains("a") || line.contains("b") || line.contains("c")) {
                setRightAnswer(line);
                rightAnswer = line;
                System.out.println("Oikea vastaus:  "+rightAnswer);
                break;
            }
        }
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

    // Called when the Application is destroyed. Disposes the stage and all its actors, sound and music objects.
    @Override
    public void dispose() {
        stage.dispose();
        sound.dispose();
        music.dispose();
    }
}
