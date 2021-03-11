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
        buttonSleep.setPosition(MindPuzzle.VIRTUAL_WIDTH/6,550);
        buttonSleep.setSize(280, 60);
        buttonSleep.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.sleepRoom);
            }
        });

        buttonFood = new TextButton("Food", skin, "default");
        buttonFood.setPosition(MindPuzzle.VIRTUAL_WIDTH/6,450);
        buttonFood.setSize(280, 60);
        buttonFood.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.foodRoom);
            }
        });

        buttonSocial = new TextButton("Social", skin, "default");
        buttonSocial.setPosition(MindPuzzle.VIRTUAL_WIDTH/6,350);
        buttonSocial.setSize(280, 60);
        buttonSocial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.socialRoom);
            }
        });

        buttonHobbies = new TextButton("Hobbies", skin, "default");
        buttonHobbies.setPosition(MindPuzzle.VIRTUAL_WIDTH/6,250);
        buttonHobbies.setSize(280, 60);
        buttonHobbies.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.hobbiesRoom);
            }
        });

        buttonSport = new TextButton("Sports", skin, "default");
        buttonSport.setPosition(MindPuzzle.VIRTUAL_WIDTH/6,150);
        buttonSport.setSize(280, 60);
        buttonSport.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.sportsRoom);
            }
        });

        buttonMenu = new TextButton("Menu", skin, "default");
        buttonMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH/6,50);
        buttonMenu.setSize(280, 60);
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
        app.font.draw(app.batch, "Screen: ROOM MENU", 20,20);
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

