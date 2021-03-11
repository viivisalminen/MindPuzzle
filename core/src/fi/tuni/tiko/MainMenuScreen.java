package fi.tuni.tiko;

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

public class MainMenuScreen implements Screen {

    private final MindPuzzle app;
    public static final int VIRTUAL_WIDTH = 400;
    public static final int VIRTUAL_HEIGHT = 650;

    private Stage stage;
    private Skin skin;

    private TextButton buttonPlay, buttonHowToPlay, buttonSettings, buttonCredits, buttonExit;

    private ShapeRenderer shapeRenderer;

    public MainMenuScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        System.out.println("MAIN MENU");
        Gdx.input.setInputProcessor(stage);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons() {
        buttonPlay = new TextButton("Play", skin, "default");
        buttonPlay.setPosition(VIRTUAL_WIDTH/6,500);
        buttonPlay.setSize(280, 60);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });

        buttonHowToPlay = new TextButton("How to play", skin, "default");
        buttonHowToPlay.setPosition(VIRTUAL_WIDTH/6,400);
        buttonHowToPlay.setSize(280, 60);

        buttonSettings = new TextButton("Settings", skin, "default");
        buttonSettings.setPosition(VIRTUAL_WIDTH/6,300);
        buttonSettings.setSize(280, 60);

        buttonCredits = new TextButton("Credits", skin, "default");
        buttonCredits.setPosition(VIRTUAL_WIDTH/6,200);
        buttonCredits.setSize(280, 60);

        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(VIRTUAL_WIDTH/6,100);
        buttonExit.setSize(280, 60);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(buttonPlay);
        stage.addActor(buttonHowToPlay);
        stage.addActor(buttonSettings);
        stage.addActor(buttonCredits);
        stage.addActor(buttonExit);
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
        app.font.draw(app.batch, "Screen: MAIN MENU", 20,20);
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
