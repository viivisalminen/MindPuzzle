package fi.tamk.mindpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * SleepRoom shows the game's bedroom-themed room
 */
public class SleepRoom extends ScreenAdapter {
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
     * Points as String object.
     */
    private String points = Integer.toString(fi.tamk.mindpuzzle.MindPuzzle.getPoints());
    /**
     * Depending the current language of the game, line is "points" either in English or in Finnish.
     */
    private String line = "";
    /**
     * ImageButton for settings popup.
     */
    private ImageButton settingsButton;
    /**
     * Texture used in settings button when the button is not touched.
     */
    private Texture settingsTxt;
    /**
     * Texture used in settings button when the button is touched.
     */
    private Texture settingsTxtPressed;
    /**
     * Rectangle object to resize the settingsTxt.
     */
    private Rectangle settingRec;
    /**
     * ImageButton for getting back to room menu.
     */
    private ImageButton doorButton;
    /**
     * Texture used in door button.
     */
    private Texture doorTxt;
    /**
     * ImageButton for character 1 in the room.
     */
    private ImageButton character1Button;
    /**
     * ImageButton for character 2 in the room.
     */
    private ImageButton character2Button;
    /**
     * ImageButton for character 3 in the room.
     */
    private ImageButton character3Button;
    /**
     * ImageButton for character 4 in the room.
     */
    private ImageButton character4Button;
    /**
     * ImageButton for character 5 in the room.
     */
    private ImageButton character5Button;
    /**
     * Texture of character 1.
     */
    private Texture character1Txt;
    /**
     * Texture of character 2.
     */
    private Texture character2Txt;
    /**
     * Texture of character 3.
     */
    private Texture character3Txt;
    /**
     * Texture of character 4.
     */
    private Texture character4Txt;
    /**
     * Texture of character 5.
     */
    private Texture character5Txt;
    /**
     * Boolean value for the character 1 in the room.
     * If true, character is visible.
     * If false, character is invisible.
     */
    private boolean char1NotClicked = true;
    /**
     * Boolean value for the character 2 in the room.
     * If true, character is visible.
     * If false, character is invisible.
     */
    private boolean char2NotClicked = true;
    /**
     * Boolean value for the character 3 in the room.
     * If true, character is visible.
     * If false, character is invisible.
     */
    private boolean char3NotClicked = true;
    /**
     * Boolean value for the character 4 in the room.
     * If true, character is visible.
     * If false, character is invisible.
     */
    private boolean char4NotClicked = true;
    /**
     * Boolean value for the character 5 in the room.
     * If true, character is visible.
     * If false, character is invisible.
     */
    private boolean char5NotClicked = true;

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     * Checks the saved status of the characters.
     *
     * @param app   MindPuzzle class's object
     */
    public SleepRoom(final fi.tamk.mindpuzzle.MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        openCharacterInfo();
    }

    /**
     * Sets the InputProcessor that will receive all touch and key input events.
     * Initializes the character textures. Sets the previous screen and plays
     * the music if it it on.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        if(app.getLanguage().equals("fi_FI")) {
            line = "PISTEET: ";
            doorTxt = app.assets.get("images/doorFIN.png", Texture.class);
        } else {
            line = "POINTS: ";
            doorTxt = app.assets.get("images/door.png", Texture.class);
        }

        character1Txt = app.assets.get("images/Characters/griffinredFlipped.png", Texture.class);
        character2Txt = app.assets.get("images/Characters/robotcatFlipped.png", Texture.class);
        character3Txt = app.assets.get("images/Characters/skullbear.png", Texture.class);
        character4Txt = app.assets.get("images/Characters/skullwolf.png", Texture.class);
        character5Txt = app.assets.get("images/Characters/sloth.png", Texture.class);

        settingsTxt = app.assets.get("images/RoomSettings/Settings.png", Texture.class);
        settingsTxtPressed = app.assets.get("images/RoomSettings/SettingsPressed.png", Texture.class);
        settingRec = new Rectangle(0,0, settingsTxt.getWidth() * 0.74f, settingsTxt.getHeight() * 0.74f);

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/sleepRoom.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();
        app.setPreviousScreen(app.sleepRoom);

        if(fi.tamk.mindpuzzle.MainMenuScreen.getMusic()) {
            fi.tamk.mindpuzzle.MainMenuScreen.musicOn();
        }
    }

    /**
     * Initializes the buttons used in this screen.
     */
    private void initButtons() {
        doorButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(doorTxt))
        );
        doorButton.setPosition(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH * 0.25f, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.675f);
        doorButton.setSize(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH * 0.5f, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH * 0.5f);
        doorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(fi.tamk.mindpuzzle.MainMenuScreen.getSound()) {
                    fi.tamk.mindpuzzle.MainMenuScreen.sound.play();
                }
                app.setScreen(app.roomMenuScreen);
            }
        });

        character1Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(character1Txt)));
        character1Button.setPosition(0, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.41f);
        character1Button.setSize(character1Txt.getWidth() * 0.4f, character1Txt.getHeight() * 0.4f);
        character1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(fi.tamk.mindpuzzle.MainMenuScreen.getSound()) {
                    fi.tamk.mindpuzzle.MainMenuScreen.sound.play();
                }
                char1NotClicked = false;
                app.saveCharacter("sleep1",char1NotClicked);
                app.setPreviousCharacter("griffinred");
                app.setScreen(app.questionScreen);
            }
        });

        character2Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(character2Txt)));
        character2Button.setPosition(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH * 0.025f, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.125f);
        character2Button.setSize(character2Txt.getWidth() * 0.4f, character2Txt.getHeight() * 0.4f);
        character2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(fi.tamk.mindpuzzle.MainMenuScreen.getSound()) {
                    fi.tamk.mindpuzzle.MainMenuScreen.sound.play();
                }
                char2NotClicked = false;
                app.saveCharacter("sleep2",char2NotClicked);
                app.setPreviousCharacter("robotcat");
                app.setScreen(app.questionScreen);
            }
        });

        character3Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(character3Txt)));
        character3Button.setPosition(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH * 0.6f, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.075f);
        character3Button.setSize(character3Txt.getWidth() * 0.5f, character3Txt.getHeight() * 0.5f);
        character3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(fi.tamk.mindpuzzle.MainMenuScreen.getSound()) {
                    fi.tamk.mindpuzzle.MainMenuScreen.sound.play();
                }
                char3NotClicked = false;
                app.saveCharacter("sleep3",char3NotClicked);
                app.setPreviousCharacter("skullbear");
                app.setScreen(app.questionScreen);
            }
        });

        character4Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(character4Txt)));
        character4Button.setPosition(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH * 0.575f, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.55f);
        character4Button.setSize(character4Txt.getWidth() * 0.4f, character4Txt.getHeight() * 0.4f);
        character4Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(fi.tamk.mindpuzzle.MainMenuScreen.getSound()) {
                    fi.tamk.mindpuzzle.MainMenuScreen.sound.play();
                }
                char4NotClicked = false;
                app.saveCharacter("sleep4",char4NotClicked);
                app.setPreviousCharacter("skullwolf");
                app.setScreen(app.questionScreen);
            }
        });

        character5Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(character5Txt)));
        character5Button.setPosition(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH * 0.5f, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.375f);
        character5Button.setSize(character5Txt.getWidth() * 0.4f, character5Txt.getHeight() * 0.4f);
        character5Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(fi.tamk.mindpuzzle.MainMenuScreen.getSound()) {
                    fi.tamk.mindpuzzle.MainMenuScreen.sound.play();
                }
                char5NotClicked = false;
                app.saveCharacter("sleep5",char5NotClicked);
                app.setPreviousCharacter("sloth");
                app.setScreen(app.questionScreen);
            }
        });

        settingsButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(settingsTxt)),
                new TextureRegionDrawable(new TextureRegion(settingsTxtPressed))
        );
        if(Gdx.graphics.getWidth() < 1000) {
            settingsButton.setPosition((Gdx.graphics.getWidth() / 2 + settingsTxt.getWidth() / 3), fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 1000 && Gdx.graphics.getWidth() < 1200) {
            settingsButton.setPosition((fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH / 2 - settingsTxt.getWidth() / 2), fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 1200 && Gdx.graphics.getWidth() < 2000) {
            settingsButton.setPosition(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH  / 2, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 2000) {
            settingsButton.setPosition(fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_WIDTH / 2, fi.tamk.mindpuzzle.MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        }
        settingsButton.setSize(settingRec.width, settingRec.height);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(fi.tamk.mindpuzzle.MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.settingsPopUp);
            }
        });

        stage.addActor(settingsButton);
        stage.addActor(doorButton);

        //stage.addActor(character1Button);
        if (char1NotClicked) {
            stage.addActor(character1Button);
        }
        if (char2NotClicked) {
            stage.addActor(character2Button);
        }
        if (char3NotClicked) {
            stage.addActor(character3Button);
        }
        if (char4NotClicked) {
            stage.addActor(character4Button);
        }
        if (char5NotClicked) {
            stage.addActor(character5Button);
        }
    }

    /**
     * Asks from the MindPuzzle if character should be visible or not.
     */
    public void openCharacterInfo() {
        char1NotClicked = app.openCharacters("sleep1",char1NotClicked);
        char2NotClicked = app.openCharacters("sleep2",char2NotClicked);
        char3NotClicked = app.openCharacters("sleep3",char3NotClicked);
        char4NotClicked = app.openCharacters("sleep4",char4NotClicked);
        char5NotClicked = app.openCharacters("sleep5",char5NotClicked);
    }

    /**
     * Resets the boolean values of the characters.
     */
    public void resetCharacterInfo() {
        char1NotClicked = true;
        char2NotClicked = true;
        char3NotClicked = true;
        char4NotClicked = true;
        char5NotClicked = true;
    }

    /**
     * Calls every actor's act()-method that has added to the stage.
     * Draws the score on the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        app.batch.begin();
        points = Integer.toString(MindPuzzle.getPoints());
        app.font60.draw(app.batch, line+points,stage.getViewport().getScreenWidth() * 0.05f,stage.getViewport().getScreenHeight() * 0.975f);
        app.batch.end();
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
     * Disposes the stage and all its actors.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();
        //settingsTxt.dispose();
        //settingsTxtPressed.dispose();
        //doorTxt.dispose();
        //character1Txt.dispose();
        //character2Txt.dispose();
        //character3Txt.dispose();
        //character4Txt.dispose();
        //character5Txt.dispose();
    }
}
