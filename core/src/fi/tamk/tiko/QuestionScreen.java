package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class QuestionScreen implements Screen {

    private final MindPuzzle app;

    private Stage stage;
    private Table background;
    private Skin skin;

    private TextButton buttonA, buttonB, buttonC, buttonX;

    private ShapeRenderer shapeRenderer;

    public QuestionScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        System.out.println("Question screen");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/roomBackground.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();
    }

    private void initButtons() {
        float buttonSize = MindPuzzle.VIRTUAL_WIDTH * 0.075f;

        buttonA = new TextButton("A", skin, "default");
        buttonA.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        buttonA.setSize(buttonSize, buttonSize);
        /*buttonA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });*/

        buttonB = new TextButton("B", skin, "default");
        buttonB.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.15f);
        buttonB.setSize(buttonSize, buttonSize);
        /*buttonB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });*/

        buttonC = new TextButton("C", skin, "default");
        buttonC.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.1f);
        buttonC.setSize(buttonSize, buttonSize);
        /*buttonC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });*/

        buttonX = new TextButton("X", skin, "default");
        buttonX.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.8f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonX.setSize(buttonSize, buttonSize);
        buttonX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });

        stage.addActor(buttonA);
        stage.addActor(buttonB);
        stage.addActor(buttonC);
        stage.addActor(buttonX);

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
        app.font.draw(app.batch, "Screen: Question screen", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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