package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// SplashScreen is the starting screen of the game.
public class SplashScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;
    // Image used in SplashScreen.
    private Image splashImg;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public SplashScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new FitViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    // Called when this screen becomes the current screen for a Game.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/background2.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        // After the splash action is complete, screen will change to the main menu screen.
        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                app.setScreen(app.mainMenuScreen);
            }
        };

        //Texture splashTxt = new Texture(Gdx.files.internal("mushroom.png"));
        //Same row as above
        Texture splashTxt = app.assets.get("images/logo.png", Texture.class);

        splashImg = new Image(splashTxt);
        splashImg.setSize(splashTxt.getWidth()/1.5f,splashTxt.getHeight()/1.5f );
        splashImg.setOrigin(splashImg.getWidth()/2f,splashImg.getHeight()/2f );
        // Keeping the image centered in the screen
        splashImg.setPosition(MindPuzzle.VIRTUAL_WIDTH / 2 - 32, MindPuzzle.VIRTUAL_HEIGHT + 32);

        // Sets the image invisible and reveals the image within 2 seconds
        // sequences chains the actions and executes them one by one

        //splashImg.addAction(sequence(alpha(0f), fadeIn(2f)));

        // Swings the image to the center of the screen with parallel effect revealing the image in the same tim
        splashImg.addAction(sequence(alpha(0), scaleTo(0.1f,0.1f),
                parallel(fadeIn(1.5f, Interpolation.pow2),
                        scaleTo(2f,2f,2.5f, Interpolation.pow5),
                        moveTo(MindPuzzle.VIRTUAL_WIDTH / 3.25f, MindPuzzle.VIRTUAL_HEIGHT / 2 - 32, 2f, Interpolation.swing)),
                delay(1f), fadeOut(1.25f), run(transitionRunnable)));

        stage.addActor(splashImg);
    }

    // Called when the screen should render itself.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }

    // Calls every actor's act()-method that has added to the stage.
    public void update(float delta) { stage.act(delta); }

    // Keeping the camera view centered in the screen
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    // Called when the Application is paused, usually when it's not active or visible on-screen.
    // An Application is also paused before it is destroyed.
    @Override
    public void pause() {
        System.out.println("Pause!");
    }

    // Called when the Application is resumed from a paused state, usually when it regains focus.
    @Override
    public void resume() {
        System.out.println("Resume!");
    }

    // Called when this screen is no longer the current screen for a Game.
    @Override
    public void hide() {
        System.out.println("Hide!");
    }

    // Called when the Application is destroyed. Disposes the stage and all its actors.
    @Override
    public void dispose() {
        stage.dispose();
    }
}
