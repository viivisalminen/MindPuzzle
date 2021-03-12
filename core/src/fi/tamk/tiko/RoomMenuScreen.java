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

public class RoomMenuScreen implements Screen {

    private final MindPuzzle app;

    private Stage stage;
    private Skin skin;

    private TextButton buttonSleep, buttonFood, buttonSocial, buttonHobbies, buttonSport, buttonMenu;

    private ShapeRenderer shapeRenderer;

    public RoomMenuScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        System.out.println("ROOM MENU");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons() {
        buttonSleep = new TextButton("Sleep", skin, "default");
        buttonSleep.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.8f);
        buttonSleep.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonSleep.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.sleepRoom);
            }
        });

        buttonFood = new TextButton("Food", skin, "default");
        buttonFood.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        buttonFood.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonFood.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.foodRoom);
            }
        });

        buttonSocial = new TextButton("Social", skin, "default");
        buttonSocial.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        buttonSocial.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonSocial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.socialRoom);
            }
        });

        buttonHobbies = new TextButton("Hobbies", skin, "default");
        buttonHobbies.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonHobbies.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonHobbies.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.hobbiesRoom);
            }
        });

        buttonSport = new TextButton("Sports", skin, "default");
        buttonSport.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonSport.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonSport.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.sportsRoom);
            }
        });

        buttonMenu = new TextButton("Main Menu", skin, "default");
        buttonMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        buttonMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        stage.addActor(buttonSleep);
        stage.addActor(buttonFood);
        stage.addActor(buttonSocial);
        stage.addActor(buttonHobbies);
        stage.addActor(buttonSport);
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
        app.font.draw(app.batch, "Screen: ROOM MENU", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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

