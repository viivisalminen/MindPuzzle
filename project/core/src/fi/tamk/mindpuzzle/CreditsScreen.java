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
 * CreditsScreen shows team roles of the game's authors.
 */
public class CreditsScreen extends ScreenAdapter {
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
     * ImageButtons are used to navigate the game.
     */
    private ImageButton imageMenu;
    /**
     * Texture used in menu button when the button is not touched.
     */
    private Texture imgMenu;
    /**
     * Texture used in menu button when the button is touched.
     */
    private Texture imgMenuPressed;
    /**
     * Image that introduces the development team of the game.
     */
    private Texture credits;
    /**
     * Rectangle variable to increase the size of the credits image.
     */
    private Rectangle creditsLarge;
    /**
     * Rectangle variable to reduce the size of the credits image.
     */
    private Rectangle creditsSmall;

    /**
     * Class constructor. Uses the MindPuzzle reference to set
     * the screen. Creates a stage using StretchViewPort
     * with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public CreditsScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(
                app.VIRTUAL_WIDTH, app.VIRTUAL_HEIGHT, app.camera));
    }

    /**
     * Initializes the textures. Gets the music's value from
     * MainMenuScreen and sets music either on or off
     * depending the returning value.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        if(app.getLanguage().equals("fi_FI")) {
            imgMenu = app.assets.get(
                    "images/Painonapit/Paavalikko.png",
                    Texture.class);
            imgMenuPressed = app.assets.get(
                    "images/Painonapit/PaavalikkoPainettu.png",
                    Texture.class);
            credits = app.assets.get(
                    "images/Credits_and_instructions/Tekijat.png",
                    Texture.class);
        } else {
            imgMenu = app.assets.get(
                    "images/Buttons/Menu.png",
                    Texture.class);
            imgMenuPressed = app.assets.get(
                    "images/Buttons/MenuPressed.png",
                    Texture.class);
            credits = app.assets.get(
                    "images/Credits_and_instructions/Credits.png",
                    Texture.class);
        }

        creditsSmall = new Rectangle(0,0,credits.getWidth() * 0.4f,
                credits.getHeight() * 0.4f);
        creditsLarge = new Rectangle(0,0,credits.getWidth() * 0.65f,
                credits.getHeight() * 0.65f);

        background = new Table();
        background.setBackground(new TextureRegionDrawable(
                new TextureRegion(app.assets.get(
                        "images/background2.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(app.mainMenuScreen.getMusic()) {
            app.mainMenuScreen.music.play();
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
        imageMenu.setPosition(app.VIRTUAL_WIDTH * 0.125f,
                app.VIRTUAL_HEIGHT * 0.8f);
        imageMenu.setSize(app.VIRTUAL_WIDTH * 0.75f,
                app.VIRTUAL_HEIGHT * 0.09f);
        imageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        stage.addActor(imageMenu);
    }

    /**
     * Calls every actor's act()-method that has added to the stage.
     * Draws the stage and credits on the screen.
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
        if (Gdx.graphics.getWidth() < 1000) {
            app.batch.draw(credits,
                    ((stage.getViewport().getScreenWidth() / 2) -
                            (creditsSmall.width / 2)),
                    stage.getViewport().getScreenHeight() * 0.075f,
                    creditsSmall.width, creditsSmall.height);
        } else {
            app.batch.draw(credits,
                    ((stage.getViewport().getScreenWidth() / 2) -
                            (creditsLarge.width / 2)),
                    stage.getViewport().getScreenHeight() * 0.05f,
                    creditsLarge.width, creditsLarge.height);
        }
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
     * Disposes MindPuzzle object and the stage and all its actors.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();
        credits.dispose();
    }
}
