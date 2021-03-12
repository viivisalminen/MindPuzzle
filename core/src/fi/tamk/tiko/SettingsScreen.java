package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class SettingsScreen implements Screen {

    private final MindPuzzle app;
    public static final int VIRTUAL_WIDTH = 400;
    public static final int VIRTUAL_HEIGHT = 650;

    private Stage stage;
    private Skin skin;

    private TextButton buttonSound, buttonMusic, buttonLanguage, buttonMenu;

    private ShapeRenderer shapeRenderer;

    public SettingsScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        System.out.println("SETTINGS");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons() {
        buttonSound = new TextButton("Sound", skin, "default");
        buttonSound.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        buttonSound.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);

        buttonMusic = new TextButton("Music", skin, "default");
        buttonMusic.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        buttonMusic.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);

        buttonLanguage = new TextButton("Language", skin, "default");
        buttonLanguage.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonLanguage.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);

        buttonMenu = new TextButton("Main Menu", skin, "default");
        buttonMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.1f);
        buttonMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.7f, MindPuzzle.VIRTUAL_WIDTH * 0.09f);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        stage.addActor(buttonSound);
        stage.addActor(buttonMusic);
        stage.addActor(buttonLanguage);
        stage.addActor(buttonMenu);
    }

    private void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        app.batch.begin();
        app.font.draw(app.batch, "Screen: SETTINGS", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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
        stage.dispose();
    }
}
