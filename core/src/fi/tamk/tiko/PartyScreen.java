package fi.tamk.tiko;

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

/**
 * PartyScreen is the ending screen or the game. The view is determined by the player's score.
 * The player can return to the main menu and start a new game or exit the game.
 */
public class PartyScreen extends ScreenAdapter {
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
     * String object that gets the string depending on how many points the player got
     */
    private String line = "";
    /**
     * X-button to exit the answer view.
     */
    private ImageButton imageMenu,imageExit;
    /**
     * Textures used in ImageButtons when button is not touched.
     */
    private Texture imgMenu, imgExit;
    /**
     * Textures used in ImageButtons when button is touched.
     */
    private Texture imgMenuPressed ,imgExitPressed;

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public PartyScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    /**
     * Sets the InputProcessor that will receive all touch and key input events.
     * Initializes the textures and the ending message to the screen.
     * Gets the music's value from MainMenuScreen and sets music either on or off depending the returning value.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        if(app.getLanguage().equals("fi_FI")) {
            imgMenu = app.assets.get("images/Painonapit/Paavalikko.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Painonapit/PaavalikkoPainettu.png", Texture.class);
            imgExit = app.assets.get("images/Painonapit/Lopeta.png", Texture.class);
            imgExitPressed = app.assets.get("images/Painonapit/LopetaPainettu.png", Texture.class);

        } else {
            imgMenu = app.assets.get("images/Buttons/Menu.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Buttons/MenuPressed.png", Texture.class);
            imgExit = app.assets.get("images/Buttons/Exit.png", Texture.class);
            imgExitPressed = app.assets.get("images/Buttons/ExitPressed.png", Texture.class);
        }

        background = new Table();
        if (app.getPoints() >= 18) {
            background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/happyending.png", Texture.class))));
            if(app.getLanguage().equals("fi_FI")) {
                line = "Hurraa! Kyläläiset ovat jälleen iloisia! \nNyt juhlitaan!";
            } else {
                line = "Hurray! The villagers are happy again.\nLet's have a great party!";
            }
        } else if (app.getPoints() > 8 && app.getPoints() < 18) {
            background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/neutralEnding.png", Texture.class))));
            if(app.getLanguage().equals("fi_FI")) {
                line = "Hienosti tehty! Onnistuit auttamaan joitain kyläläisiä.\nOsa heistä tulivat juhlimaan.";
            } else {
                line = "Well done! You managed to help the villagers a little. \nSome of them are happy enough to party!";
            }
        } else if (app.getPoints() < 9) {
            background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/badEnding.png", Texture.class))));
            if(app.getLanguage().equals("fi_FI")) {
                line = "Voi ei! Et onnistunut auttamaan kyläläisiä ja he ovat liian surullisia juhlimaan.";
            } else {
                line = "Oh no! You failed to help the villagers \nand they are still too sad to have a party";
            }
        }
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.music.play();
        }
    }

    /**
     * Initializes the buttons used in this screen.
     */
    private void initButtons() {
        imageMenu = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgMenu)),
                new TextureRegionDrawable(new TextureRegion(imgMenuPressed))
        );
        imageMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.125f);
        imageMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.resetGame();
                app.setScreen(app.mainMenuScreen);
            }
        });

        imageExit = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgExit)),
                new TextureRegionDrawable(new TextureRegion(imgExitPressed))
        );
        imageExit.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.025f);
        imageExit.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.resetGame();
                Gdx.app.exit();
            }
        });

        stage.addActor(imageMenu);
        stage.addActor(imageExit);
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

        app.batch.begin();
        app.font40.draw(app.batch, line,stage.getViewport().getScreenWidth() * 0.15f,stage.getViewport().getScreenHeight() * 0.91f);
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
        stage.dispose();
    }
}
