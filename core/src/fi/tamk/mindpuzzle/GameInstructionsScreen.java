package fi.tamk.mindpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * GameInstructionsScreen shows the game's instructions.
 */
public class GameInstructionsScreen extends ScreenAdapter {
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
     * ImageButtons are used to navigate to main menu.
     */
    private ImageButton imageMenu;
    /**
     * Texture used in ImageButton when button is not touched.
     */
    private Texture imgMenu;
    /**
     * Textures used in ImageButton when button is touched.
     */
    private Texture imgMenuPressed;
    /**
     * Image that tells the game instructions.
     */
    private Texture instructions;
    /**
     * Rectangle variable to increase the size of the instructions image.
     */
    private Rectangle instructionsLarge;
    /**
     * Rectangle variable to reduce the size of the instructions image.
     */
    private Rectangle instructionsSmall;

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public GameInstructionsScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));

    }

    /**
     * Sets the InputProcessor that will receive all touch and key input events.
     * Initializes the textures. Sets the previous screen.
     * Gets the music's value from MainMenuScreen and sets music either on or off depending the returning value.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        if(app.getLanguage().equals("fi_FI")) {
            imgMenu = app.assets.get("images/Painonapit/Paavalikko.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Painonapit/PaavalikkoPainettu.png", Texture.class);
            instructions = app.assets.get("images/Credits_and_instructions/Peliohjeet.png", Texture.class);
        } else {
            imgMenu = app.assets.get("images/Buttons/Menu.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Buttons/MenuPressed.png", Texture.class);
            instructions = app.assets.get("images/Credits_and_instructions/HowTo.png", Texture.class);
        }

        instructionsSmall = new Rectangle(0,0,instructions.getWidth() * 0.55f, instructions.getHeight() * 0.55f);
        instructionsLarge = new Rectangle(0,0,instructions.getWidth() * 0.75f, instructions.getHeight() * 0.75f);

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/background2.png", Texture.class))));
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
        imageMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.8f);
        imageMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        stage.addActor(imageMenu);
    }

    /**
     * Calls every actor's act()-method that has added to the stage.
     * Draws the stage and game instructions on the screen.
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
            app.batch.draw(instructions, ((stage.getViewport().getScreenWidth() / 2) - (instructionsSmall.width / 2)),stage.getViewport().getScreenHeight() * 0.15f, instructionsSmall.width, instructionsSmall.height);
        } else {
            app.batch.draw(instructions, ((stage.getViewport().getScreenWidth() / 2) - (instructionsLarge.width / 2)),stage.getViewport().getScreenHeight() * 0.2f, instructionsLarge.width, instructionsLarge.height);
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
     * Disposes the stage and all its actors.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();
        instructions.dispose();

        //imgMenu.dispose();
        //imgMenuPressed.dispose();
    }
}
