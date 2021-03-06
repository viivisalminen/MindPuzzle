package fi.tuni.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen implements Screen {

    private final MindPuzzle app;
    private Stage stage;

    private Image splashImg;

    // Constructor of the one game page (World, textures etc...)
    public SplashScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        Gdx.input.setInputProcessor(stage);

        Texture splashTxt = new Texture(Gdx.files.internal("mushroom.png"));
        splashImg = new Image(splashTxt);
        splashImg.setSize(splashTxt.getWidth()/5f,splashTxt.getHeight()/5f );
        splashImg.setOrigin(splashImg.getWidth()/2f,splashImg.getHeight()/2f );

        // Keeping the image centered in the screen
        splashImg.setPosition(stage.getWidth() / 2 - 32, stage.getHeight() / 2 - 32);

        stage.addActor(splashImg);
    }

    // show() is called once every time game sets the screen to one object(= game page)
    // Initializing to set the game
    // Resets everything to defaults
    @Override
    public void show() {
        System.out.println("Show!");
        splashImg.setPosition(stage.getWidth() / 2 - 32, stage.getHeight() + 32);

        // Sets the image invisible and reveals the image within 2 seconds
        // sequences chains the actions and executes them one by one
        //splashImg.addAction(sequence(alpha(0f), fadeIn(2f)));

        // Swings the image to the center of the screen with parallel effect revealing the image in the same tim
        splashImg.addAction(sequence(alpha(0), scaleTo(0.1f,0.1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(2f,2f,2.5f, Interpolation.pow5),
                        moveTo(stage.getWidth() / 2 - 32, stage.getHeight() / 2 - 32, 2f, Interpolation.swing)),
                        delay(1.5f), fadeOut(1.25f)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        app.batch.begin();
        app.font.draw(app.batch, "Splashscreen!", 120,120);
        app.batch.end();
    }

    public void update(float delta) {
        // Calls every actor's act()-method that has added to the stage
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        // Keeping the camera view centered in the screen
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        System.out.println("Pause!");
    }

    @Override
    public void resume() {
        System.out.println("Resume!");
    }

    // hide () is called every time the screen is changed to something else
    @Override
    public void hide() {
        System.out.println("Hide!");
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
