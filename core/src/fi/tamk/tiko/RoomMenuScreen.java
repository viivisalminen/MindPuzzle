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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class RoomMenuScreen implements Screen {

    private final MindPuzzle app;

    private Stage stage;
    private Table background;
    private Skin skin;

    private Texture imgSleep, imgFood, imgHobbies, imgSports, imgSocial;
    private Texture imgSleepPressed, imgFoodPressed, imgHobbiesPressed, imgSportsPressed, imgSocialPressed;
    private ImageButton imageSleep, imageFood, imageHobbies, imageSports, imageSocial;
    private TextButton buttonMenu;

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

        imgFood = app.assets.get("images/food.png", Texture.class);
        imgFoodPressed = app.assets.get("images/food.png", Texture.class);
        imgSleep = app.assets.get("images/sleep.png", Texture.class);
        imgSleepPressed = app.assets.get("images/sleep.png", Texture.class);
        imgSocial = app.assets.get("images/social.png", Texture.class);
        imgSocialPressed = app.assets.get("images/social.png", Texture.class);
        imgHobbies = app.assets.get("images/hobbies.png", Texture.class);
        imgHobbiesPressed = app.assets.get("images/hobbies.png", Texture.class);
        imgSports = app.assets.get("images/sports.png", Texture.class);
        imgSportsPressed = app.assets.get("images/sports.png", Texture.class);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/background.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();
    }

    private void initButtons() {
        buttonMenu = new TextButton("Main menu", skin, "default");
        buttonMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.8f);
        buttonMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        buttonMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        imageFood = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgFood)),
                new TextureRegionDrawable(new TextureRegion(imgFoodPressed))
        );
        imageFood.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        imageFood.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageFood.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.foodRoom);
            }
        });

        imageSocial = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSocial)),
                new TextureRegionDrawable(new TextureRegion(imgSocialPressed))
        );
        imageSocial.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        imageSocial.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageSocial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.socialRoom);
            }
        });

        imageHobbies = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgHobbies)),
                new TextureRegionDrawable(new TextureRegion(imgHobbiesPressed))
        );
        imageHobbies.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        imageHobbies.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageHobbies.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.hobbiesRoom);
            }
        });

        imageSports = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSports)),
                new TextureRegionDrawable(new TextureRegion(imgSportsPressed))
        );
        imageSports.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        imageSports.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageSports.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.sportsRoom);
            }
        });

        imageSleep = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSleep)),
                new TextureRegionDrawable(new TextureRegion(imgSleepPressed))
        );
        imageSleep.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        imageSleep.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageSleep.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.sleepRoom);
            }
        });

        stage.addActor(imageSleep);
        stage.addActor(imageFood);
        stage.addActor(imageSocial);
        stage.addActor(imageHobbies);
        stage.addActor(imageSports);
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

