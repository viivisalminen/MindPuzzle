package fi.tuni.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class LoadingScreen implements Screen {

    private final MindPuzzle app;

    private ShapeRenderer shapeRenderer;
    private Stage stage;
    // How much AssetManager has loaded at the time
    private float progress;

    public LoadingScreen(final MindPuzzle app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    public void queueAssets() {
        app.assets.load("images/mushroom.png", Texture.class);
        app.assets.load("ui/uiskin.atlas", TextureAtlas.class);
    }

    @Override
    public void show() {
        System.out.println("LOADING!");
        this.progress = 0f;
        queueAssets();
    }

    public void update(float delta) {
        progress = MathUtils.lerp(progress, app.assets.getProgress(), 0.1f);

        // Keeps returning false until all the assets are finished loading
        // After that application changes the SplashScreen
        if(app.assets.update() && progress >= app.assets.getProgress() - 0.001f) {
            app.setScreen(app.splashScreen);
            //app.setScreen(app.mainMenuScreen);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        // Loading bar rectangles
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32,app.camera.viewportHeight / 2 - 8, app.camera.viewportWidth - 64, 16);

        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.rect(32,app.camera.viewportHeight / 2 - 8, progress * (app.camera.viewportWidth - 64), 16);
        shapeRenderer.end();

        app.batch.begin();
        app.font.draw(app.batch, "Screen: LOADING", 20,20);
        app.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
