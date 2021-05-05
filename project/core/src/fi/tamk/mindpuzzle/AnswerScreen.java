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
 * AnswerScreen shows the game's answer view after the QuestionScreen.
 */
public class AnswerScreen extends ScreenAdapter {
    /**
     * Class MindPuzzle object that allows to set
     * screen from inside this class.
     */
    private final MindPuzzle app;
    /**
     * A 2D scene graph containing hierarchies of actors.
     * Stage handles the viewport and distributes input events.
     */
    private Stage stage;
    /**
     * Positions the background image to the Screen.
     */
    private Table background;
    /**
     * Boolean variable gets the value depending on whether
     * the player's answer is right or wrong.
     */
    private boolean answer;
    /**
     * String object that gets the string depending on what the player answered
     * and what is the current language of the game.
     */
    private String line = "";
    /**
     * String object that gets happy message either
     * in English or in Finnish depending on
     * what is the current language of the game.
     */
    private String rightLine = "";
    /**
     * String object that gets sad message either
     * in English or in Finnish depending on
     * what is the current language of the game.
     */
    private String wrongLine = "";
    /**
     * String object that gets the right answer as
     * an entire string from the QuestionScreen.
     */
    private String rightAnswer = "";
    /**
     * X-button to exit the answer view.
     */
    private ImageButton xButton;
    /**
     * Texture used in ImageButton when button is not touched.
     */
    private Texture exit;
    /**
     * Texture used in ImageButton when button is touched.
     */
    private Texture exitPressed;
    /**
     * Texture for speech bubble image.
     */
    private Texture bubble;
    /**
     * Rectangle variable to reduce the size of the speech bubble image.
     */
    private Rectangle bubbleSmall;
    /**
     * Texture for a game character image.
     */
    private Texture characterTxt;
    /**
     * Rectangle variable to increase the size of the character image.
     */
    private Rectangle characterLarge;
    /**
     * Rectangle variable to reduce the size of the character image.
     */
    private Rectangle characterSmall;
    /**
     * Rectangle variable to increase the size of the speech bubble image.
     */
    private Rectangle bubbleLarge;

    /**
     * Class constructor. Uses the MindPuzzle reference to set
     * the screen. Creates a stage using StretchViewPort
     * with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public AnswerScreen (final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(app.VIRTUAL_WIDTH,
                app.VIRTUAL_HEIGHT, app.camera));
    }

    /**
     * Sets the InputProcessor that will receive all
     * touch and key input events.
     * Initializes the textures and question.
     * Gets the music's value from MainMenuScreen and
     * sets music either on or off depending the returning value.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();
        getCharacter();
        
        characterSmall = new Rectangle(0,0,characterTxt.getWidth() * 0.5f,
                characterTxt.getHeight() * 0.5f);
        characterLarge = new Rectangle(0,0,characterTxt.getWidth() * 0.725f,
                characterTxt.getHeight() * 0.725f);

        bubble = app.assets.get("images/bubble.png", Texture.class);
        bubbleSmall = new Rectangle(0,0,bubble.getWidth() * 0.675f,
                bubble.getHeight() * 0.95f);
        bubbleLarge = new Rectangle(0,0,bubble.getWidth(),
                bubble.getHeight() * 1.5f);

        exit = app.assets.get("images/RoomSettings/X.png", Texture.class);
        exitPressed = app.assets.get(
                "images/RoomSettings/Xpressed.png", Texture.class);

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(
                app.assets.get("images/popUpBackground.jpg", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();
        app.addAnsweredQuestion(app.getPreviousScreen());

        if(app.mainMenuScreen.getMusic()) {
            app.mainMenuScreen.musicOn();
            app.mainMenuScreen.music.setVolume(0.4f);
        }

        rightAnswer = app.questionScreen.getRightAnswerAsString();

        if(app.getLanguage().equals("fi_FI")) {
            rightLine = "Jei, vastasit oikein! Nyt voin hyvillä"
                    +"\nmielin lähteä valmistautumaan kyläjuhliin!";
            wrongLine = "Voi ei, vastauksesi ei ollut oikein."
                    +"\nOikea vastaus olisi ollut: \n\n"
                    +"\""+rightAnswer+"\"";
        } else {
            rightLine = "Awesome, you answered correctly!"
                    +"\nNow I can happily set off"
                    +"\nto prepare for the village party";
            wrongLine = "Oh no your answer wasn't that good!"
                    +"\nYou should have answered: \n\n"
                    +"\""+rightAnswer+"\"";
        }

        if (checkTheAnswer()) {
            app.addPoint();
            if(app.mainMenuScreen.getSound()) {
                app.mainMenuScreen.right.play();
                app.mainMenuScreen.right.setVolume(1f);
            }
            line = rightLine;
        } else if (!(checkTheAnswer())) {
            if(app.mainMenuScreen.getSound()) {
                app.mainMenuScreen.wrong.play();
                app.mainMenuScreen.wrong.setVolume(1f);
            }
            line = wrongLine;
        }
    }

    /**
     * Initializes the character texture and sets the character boolean
     * to false depending on which character was clicked.
     */
    private void getCharacter() {
        if(app.getCharacter().equals("bird")) {
            characterTxt = app.assets.get(
                    "images/Characters/bird.png", Texture.class);
            app.foodRoom.char1NotClicked = false;
            app.saveCharacter("food1", app.foodRoom.char1NotClicked);
        } else if (app.getCharacter().equals("browncat")) {
            characterTxt = app.assets.get(
                    "images/Characters/browncat.png", Texture.class);
            app.foodRoom.char2NotClicked = false;
            app.saveCharacter("food2", app.foodRoom.char2NotClicked);
        } else if (app.getCharacter().equals("demoncat")) {
            characterTxt = app.assets.get(
                    "images/Characters/demoncat.png", Texture.class);
            app.foodRoom.char3NotClicked = false;
            app.saveCharacter("food3", app.foodRoom.char3NotClicked);
        } else if (app.getCharacter().equals("ghost")) {
            characterTxt = app.assets.get(
                    "images/Characters/ghost.png", Texture.class);
            app.foodRoom.char4NotClicked = false;
            app.saveCharacter("food4", app.foodRoom.char4NotClicked);
        } else if (app.getCharacter().equals("fox")) {
            characterTxt = app.assets.get(
                    "images/Characters/fox.png", Texture.class);
            app.foodRoom.char5NotClicked = false;
            app.saveCharacter("food5", app.foodRoom.char5NotClicked);
        } else if (app.getCharacter().equals("hamster")) {
            characterTxt = app.assets.get(
                    "images/Characters/hamster.png", Texture.class);
            app.hobbiesRoom.char1NotClicked = false;
            app.saveCharacter("hobbies1", app.hobbiesRoom.char1NotClicked);
        } else if (app.getCharacter().equals("leafdragon")) {
            characterTxt = app.assets.get(
                    "images/Characters/leafdragon.png", Texture.class);
            app.hobbiesRoom.char2NotClicked = false;
            app.saveCharacter("hobbies2", app.hobbiesRoom.char2NotClicked);
        } else if (app.getCharacter().equals("lynx")) {
            characterTxt = app.assets.get(
                    "images/Characters/lynx.png", Texture.class);
            app.hobbiesRoom.char3NotClicked = false;
            app.saveCharacter("hobbies3", app.hobbiesRoom.char3NotClicked);
        } else if (app.getCharacter().equals("bunnygrey")) {
            characterTxt = app.assets.get(
                    "images/Characters/bunnygrey.png", Texture.class);
            app.hobbiesRoom.char4NotClicked = false;
            app.saveCharacter("hobbies4", app.hobbiesRoom.char4NotClicked);
        } else if (app.getCharacter().equals("mushroomguy")) {
            characterTxt = app.assets.get(
                    "images/Characters/mushroomguy.png", Texture.class);
            app.hobbiesRoom.char5NotClicked = false;
            app.saveCharacter("hobbies5", app.hobbiesRoom.char5NotClicked);
        } else if (app.getCharacter().equals("griffinred")) {
            characterTxt = app.assets.get(
                    "images/Characters/griffinred.png", Texture.class);
            app.sleepRoom.char1NotClicked = false;
            app.saveCharacter("sleep1", app.sleepRoom.char1NotClicked);
        } else if (app.getCharacter().equals("robotcat")) {
            characterTxt = app.assets.get(
                    "images/Characters/robotcat.png", Texture.class);
            app.sleepRoom.char2NotClicked = false;
            app.saveCharacter("sleep2", app.sleepRoom.char2NotClicked);
        } else if (app.getCharacter().equals("skullbear")) {
            characterTxt = app.assets.get(
                    "images/Characters/skullbear.png", Texture.class);
            app.sleepRoom.char3NotClicked = false;
            app.saveCharacter("sleep3", app.sleepRoom.char3NotClicked);
        } else if (app.getCharacter().equals("skullwolf")) {
            characterTxt = app.assets.get(
                    "images/Characters/skullwolf.png", Texture.class);
            app.sleepRoom.char4NotClicked = false;
            app.saveCharacter("sleep4", app.sleepRoom.char4NotClicked);
        } else if (app.getCharacter().equals("sloth")) {
            characterTxt = app.assets.get(
                    "images/Characters/sloth.png", Texture.class);
            app.sleepRoom.char5NotClicked = false;
            app.saveCharacter("sleep5", app.sleepRoom.char5NotClicked);
        } else if (app.getCharacter().equals("wizardcat")) {
            characterTxt = app.assets.get(
                    "images/Characters/wizardcat.png", Texture.class);
            app.socialRoom.char1NotClicked = false;
            app.saveCharacter("social1", app.socialRoom.char1NotClicked);
        } else if (app.getCharacter().equals("swampmonster")) {
            characterTxt = app.assets.get(
                    "images/Characters/swampmonster.png", Texture.class);
            app.socialRoom.char2NotClicked = false;
            app.saveCharacter("social2", app.socialRoom.char2NotClicked);
        } else if (app.getCharacter().equals("snake")) {
            characterTxt = app.assets.get(
                    "images/Characters/snake.png", Texture.class);
            app.socialRoom.char3NotClicked = false;
            app.saveCharacter("social3", app.socialRoom.char3NotClicked);
        } else if (app.getCharacter().equals("wolf")) {
            characterTxt = app.assets.get(
                    "images/Characters/wolf.png", Texture.class);
            app.socialRoom.char4NotClicked = false;
            app.saveCharacter("social4", app.socialRoom.char4NotClicked);
        } else if (app.getCharacter().equals("yeti")) {
            characterTxt = app.assets.get(
                    "images/Characters/yeti.png", Texture.class);
            app.socialRoom.char5NotClicked = false;
            app.saveCharacter("social5", app.socialRoom.char5NotClicked);
        } else if (app.getCharacter().equals("fishy")) {
            characterTxt = app.assets.get(
                    "images/Characters/fishy.png", Texture.class);
            app.sportsRoom.char1NotClicked = false;
            app.saveCharacter("sports1", app.sportsRoom.char1NotClicked);
        } else if (app.getCharacter().equals("wolfbrown")) {
            characterTxt = app.assets.get(
                    "images/Characters/wolfbrown.png", Texture.class);
            app.sportsRoom.char2NotClicked = false;
            app.saveCharacter("sports2", app.sportsRoom.char2NotClicked);
        } else if (app.getCharacter().equals("bunny")) {
            characterTxt = app.assets.get(
                    "images/Characters/bunny.png", Texture.class);
            app.sportsRoom.char3NotClicked = false;
            app.saveCharacter("sports3", app.sportsRoom.char3NotClicked);
        } else if (app.getCharacter().equals("cactusbuddy")) {
            characterTxt = app.assets.get(
                    "images/Characters/cactusbuddy.png", Texture.class);
            app.sportsRoom.char4NotClicked = false;
            app.saveCharacter("sports4", app.sportsRoom.char4NotClicked);
        } else if (app.getCharacter().equals("griffinblue")) {
            characterTxt = app.assets.get(
                    "images/Characters/griffinblue.png", Texture.class);
            app.sportsRoom.char5NotClicked = false;
            app.saveCharacter("sports5", app.sportsRoom.char5NotClicked);
        }
    }

    /**
     * Initializes the buttons used in this screen.
     */
    private void initButtons() {
        xButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(exit)),
                new TextureRegionDrawable(new TextureRegion(exitPressed))
        );
        if(Gdx.graphics.getWidth() < 1000) {
            xButton.setPosition((
                    Gdx.graphics.getWidth() / 2 + exit.getWidth() / 3),
                    app.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 1000
                && Gdx.graphics.getWidth() < 1200) {
            xButton.setPosition((
                    app.VIRTUAL_WIDTH / 2 - exit.getWidth() / 2),
                    app.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 1200
                && Gdx.graphics.getWidth() < 2000) {
            xButton.setPosition((
                    app.VIRTUAL_WIDTH  / 2 + exit.getWidth() * 3f),
                    app.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 2000) {
            xButton.setPosition((
                    app.VIRTUAL_WIDTH / 2 + exit.getWidth() * 4),
                    app.VIRTUAL_HEIGHT * 0.05f);
        }
        xButton.setSize(app.VIRTUAL_WIDTH * 0.2f, app.VIRTUAL_WIDTH * 0.2f);
        xButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }

                if((app.getAnsweredQuestion("food") >= 5)
                        && (app.getAnsweredQuestion("social") >= 5)
                        && (app.getAnsweredQuestion("sleep") >= 5)
                        && (app.getAnsweredQuestion("hobbies") >= 5)
                        && (app.getAnsweredQuestion("sports") >= 5)) {
                    app.setScreen(app.partyScreen);
                } else if (!app.foodRoom.char1NotClicked
                        && !app.foodRoom.char2NotClicked
                        && !app.foodRoom.char3NotClicked
                        && !app.foodRoom.char4NotClicked
                        && !app.foodRoom.char5NotClicked
                        && !app.hobbiesRoom.char1NotClicked
                        && !app.hobbiesRoom.char2NotClicked
                        && !app.hobbiesRoom.char3NotClicked
                        && !app.hobbiesRoom.char4NotClicked
                        && !app.hobbiesRoom.char5NotClicked
                        && !app.socialRoom.char1NotClicked
                        && !app.socialRoom.char2NotClicked
                        && !app.socialRoom.char3NotClicked
                        && !app.socialRoom.char4NotClicked
                        && !app.socialRoom.char5NotClicked
                        && !app.sportsRoom.char1NotClicked
                        && !app.sportsRoom.char2NotClicked
                        && !app.sportsRoom.char3NotClicked
                        && !app.sportsRoom.char4NotClicked
                        && !app.sportsRoom.char5NotClicked
                        && !app.sleepRoom.char1NotClicked
                        && !app.sleepRoom.char2NotClicked
                        && !app.sleepRoom.char3NotClicked
                        && !app.sleepRoom.char4NotClicked
                        && !app.sleepRoom.char5NotClicked) {
                    app.setScreen(app.partyScreen);
                } else {
                    app.setScreen(app.previousScreen);
                }
            }
        });
        stage.addActor(xButton);
    }

    /**
     * Calls every actor's act()-method that has added to the stage.
     * Draws the stage, speech bubble, character and the answer message on the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        app.batch.begin();
        if(stage.getViewport().getScreenWidth() < 1000) {
            app.batch.draw(bubble,
                    stage.getViewport().getScreenWidth() * 0.05f,
                    stage.getViewport().getScreenHeight() * 0.55f,
                    bubbleSmall.width, bubbleSmall.height);
            app.font30.draw(app.batch, line,
                    stage.getViewport().getScreenWidth() * 0.115f,
                    stage.getViewport().getScreenHeight() * 0.875f);
            app.batch.draw(characterTxt,
                    stage.getViewport().getScreenWidth() * 0.4f,
                    stage.getViewport().getScreenHeight() * 0.15f,
                    characterSmall.width, characterSmall.height);
        } else if (stage.getViewport().getScreenWidth() > 1000
                && stage.getViewport().getScreenWidth() < 1200) {
            app.batch.draw(bubble,
                    stage.getViewport().getScreenWidth() * 0.05f,
                    stage.getViewport().getScreenHeight() * 0.55f,
                    bubbleLarge.width, bubbleLarge.height);
            app.font40.draw(app.batch, line,
                    stage.getViewport().getScreenWidth() * 0.115f,
                    stage.getViewport().getScreenHeight() * 0.875f);
            app.batch.draw(characterTxt,
                    stage.getViewport().getScreenWidth() * 0.4f,
                    stage.getViewport().getScreenHeight() * 0.175f,
                    characterLarge.width, characterLarge.height);
        } else if (stage.getViewport().getScreenWidth() > 1200) {
            app.batch.draw(bubble,
                    stage.getViewport().getScreenWidth() * 0.05f,
                    stage.getViewport().getScreenHeight() * 0.6f,
                    bubbleLarge.width, bubbleLarge.height);
            app.font40.draw(app.batch, line,
                    stage.getViewport().getScreenWidth() * 0.1175f,
                    stage.getViewport().getScreenHeight() * 0.85f);
            app.batch.draw(characterTxt,
                    stage.getViewport().getScreenWidth() * 0.4f,
                    stage.getViewport().getScreenHeight() * 0.175f,
                    characterLarge.width, characterLarge.height);
        }
        app.batch.end();
    }

    /**
     * Checks if the player's answer was right or wrong.
     *
     * @return
     */
    public boolean checkTheAnswer() {
        if(app.questionScreen.getPlayersAnswer().equals(
                app.questionScreen.getRightAnswer())) {
            answer = true;
        }
        else {
            answer = false;
        }
        return answer;
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
     * Disposes MindPuzzle object and the stage and all its actors.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();
        bubble.dispose();
        characterTxt.dispose();
    }
}

