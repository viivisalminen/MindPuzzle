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

public class SettingsPopUp implements Screen {

    private final MindPuzzle app;
    //private final float PopUpWidth = MindPuzzle.VIRTUAL_WIDTH * 0.975f;
    //private final float PopUpHeight = MindPuzzle.VIRTUAL_HEIGHT * 0.3f;

    private Stage stage;
    private Table background;
    private Skin skin;

    private TextButton buttonMusic, buttonSounds, buttonFin, buttonEng, buttonX;

    private ShapeRenderer shapeRenderer;

    public SettingsPopUp(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        System.out.println("S-PopUp screen");
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

        buttonMusic = new TextButton("M", skin, "default");
        buttonMusic.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.4f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonMusic.setSize(buttonSize, buttonSize);
        /*buttonMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });*/

        buttonSounds = new TextButton("S", skin, "default");
        buttonSounds.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonSounds.setSize(buttonSize, buttonSize);
        /*buttonSounds.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });*/

        buttonFin = new TextButton("Fi", skin, "default");
        buttonFin.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.6f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonFin.setSize(buttonSize, buttonSize);
        /*buttonFin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });*/

        buttonEng = new TextButton("En", skin, "default");
        buttonEng.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.7f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonEng.setSize(buttonSize, buttonSize);
        /*buttonEng.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });*/

        buttonX = new TextButton("X", skin, "default");
        buttonX.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.8f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonX.setSize(buttonSize, buttonSize);
        buttonX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });

        stage.addActor(buttonMusic);
        stage.addActor(buttonSounds);
        stage.addActor(buttonFin);
        stage.addActor(buttonEng);
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
        app.font.draw(app.batch, "Screen: SettingsPopUp screen", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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
