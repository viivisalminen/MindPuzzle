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

public class HobbiesRoom implements Screen {

    private final MindPuzzle app;

    private Stage stage;
    private Skin skin;

    private TextButton buttonDoor, buttonCharacter, buttonSettingsPopUp;

    private ShapeRenderer shapeRenderer;

    public HobbiesRoom(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        System.out.println("HOBBIES ROOM");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons() {
        buttonDoor = new TextButton("Room Menu", skin, "default");
        buttonDoor.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.8f);
        buttonDoor.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonDoor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });

        buttonCharacter = new TextButton("Character", skin, "default");
        buttonCharacter.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.45f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonCharacter.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.1f, MindPuzzle.VIRTUAL_WIDTH * 0.1f);

        buttonSettingsPopUp = new TextButton("Settings", skin, "default");
        buttonSettingsPopUp.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.45f,MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        buttonSettingsPopUp.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.1f, MindPuzzle.VIRTUAL_WIDTH * 0.1f);


        stage.addActor(buttonDoor);
        stage.addActor(buttonCharacter);
        stage.addActor(buttonSettingsPopUp);
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
        app.font.draw(app.batch, "Screen: HOBBIES ROOM", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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

