package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

// LoadingScreen loads all the assets to the game and
// shows how many assets have been loaded using the download bar.
public class LoadingScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // Renders points, lines, shape outlines and filled shapes.
    private ShapeRenderer shapeRenderer;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Progress tells how much AssetManager has loaded at the time
    private float progress;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public LoadingScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new FitViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(app.camera.combined);
    }

    // Loads all the assets used in the game one by one.
    public void queueAssets() {
        app.assets.load("images/logo.png", Texture.class);
        app.assets.load("images/background.png", Texture.class);
        app.assets.load("images/roomBackground.png", Texture.class);
        app.assets.load("images/food.png", Texture.class);
        app.assets.load("images/hobbies.png", Texture.class);
        app.assets.load("images/sleep.png", Texture.class);
        app.assets.load("images/social.png", Texture.class);
        app.assets.load("images/sports.png", Texture.class);
        app.assets.load("images/mushroom.png", Texture.class);
        app.assets.load("ui/uiskin.atlas", TextureAtlas.class);
        app.assets.load("sounds/hitsound.wav", Sound.class);
        app.assets.load("sounds/mixkit-jk.mp3", Music.class);
        app.assets.load("images/popUpBackground.jpg", Texture.class);
        app.assets.load("images/socialRoom.png", Texture.class);
        app.assets.load("images/door.png", Texture.class);
        app.assets.load("images/skullwolf.png", Texture.class);
    }

    // Called when this screen becomes the current screen for a Game.
    @Override
    public void show() {
        System.out.println("LOADING!");
        this.progress = 0f;
        queueAssets();
    }

    // Tells the AssetManager to keep checking if all the assets have been loaded.
    // After every asset have been loaded, change the vies to SplashScreen.
    public void update() {
        // MathUtils.lerp(float fromValue, float toValue, float progress)
        // Linearly interpolates between fromValue to toValue on progress position.
        progress = MathUtils.lerp(progress, app.assets.getProgress(), 0.1f);
        // Keeps returning false until all the assets are finished loading
        // After that, application changes to the SplashScreen
        if(app.assets.update() && progress >= app.assets.getProgress() - 0.001f) {
            app.setScreen(app.mainMenuScreen);
            //app.setScreen(app.questionScreen);
        }
    }

    // Called when the screen should render itself.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        // Loading bar rectangles
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f, MindPuzzle.VIRTUAL_WIDTH * 0.7f, MindPuzzle.VIRTUAL_HEIGHT * 0.02f);

        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.rect(MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f, progress * (MindPuzzle.VIRTUAL_WIDTH * 0.7f), MindPuzzle.VIRTUAL_HEIGHT * 0.02f);
        shapeRenderer.end();

        app.batch.begin();
        app.font30.draw(app.batch, "Screen: LOADING", Gdx.graphics.getWidth() * 0.05f,Gdx.graphics.getHeight() * 0.05f);
        app.batch.end();
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

    // Called when the Application is destroyed. Disposes the shapeRenderer.
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
