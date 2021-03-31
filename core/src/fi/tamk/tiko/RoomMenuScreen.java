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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

// RoomMenuScreen includes menu for rooms sleep, food, social, hobbies and sports.
public class RoomMenuScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;
    // A skin stores resources for UI widgets to use (texture regions, ninepatches, fonts, colors, etc)
    private Skin skin;
    // Renders points, lines, shape outlines and filled shapes.
    private ShapeRenderer shapeRenderer;

    private Texture imgMenu, imgSleep, imgFood, imgHobbies, imgSports, imgSocial;
    private Texture imgMenuPressed ,imgSleepPressed, imgFoodPressed, imgHobbiesPressed, imgSportsPressed, imgSocialPressed;
    private ImageButton imageMenu, imageSleep, imageFood, imageHobbies, imageSports, imageSocial;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public RoomMenuScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        imgMenu = app.assets.get("images/Buttons/Menu.png", Texture.class);
        imgMenuPressed = app.assets.get("images/Buttons/MenuPressed.png", Texture.class);
        imgFood = app.assets.get("images/RoomIcons/Food.png", Texture.class);
        imgFoodPressed = app.assets.get("images/RoomIcons/FoodPressed.png", Texture.class);
        imgSleep = app.assets.get("images/RoomIcons/Sleep.png", Texture.class);
        imgSleepPressed = app.assets.get("images/RoomIcons/SleepPressed.png", Texture.class);
        imgSocial = app.assets.get("images/RoomIcons/Social.png", Texture.class);
        imgSocialPressed = app.assets.get("images/RoomIcons/SocialPressed.png", Texture.class);
        imgHobbies = app.assets.get("images/RoomIcons/Hobbies.png", Texture.class);
        imgHobbiesPressed = app.assets.get("images/RoomIcons/HobbiesPressed.png", Texture.class);
        imgSports = app.assets.get("images/RoomIcons/Sports.png", Texture.class);
        imgSportsPressed = app.assets.get("images/RoomIcons/SportsPressed.png", Texture.class);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/background2.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.music.play();
        }
    }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        imageMenu = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgMenu)),
                new TextureRegionDrawable(new TextureRegion(imgMenuPressed))
        );
        imageMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.8f);
        imageMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        imageFood = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgFood)),
                new TextureRegionDrawable(new TextureRegion(imgFoodPressed))
        );
        imageFood.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        imageFood.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageFood.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.foodRoom);
            }
        });

        imageSocial = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSocial)),
                new TextureRegionDrawable(new TextureRegion(imgSocialPressed))
        );
        imageSocial.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        imageSocial.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageSocial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.socialRoom);
            }
        });

        imageHobbies = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgHobbies)),
                new TextureRegionDrawable(new TextureRegion(imgHobbiesPressed))
        );
        imageHobbies.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        imageHobbies.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageHobbies.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.hobbiesRoom);
            }
        });

        imageSports = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSports)),
                new TextureRegionDrawable(new TextureRegion(imgSportsPressed))
        );
        imageSports.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.5f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        imageSports.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageSports.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.sportsRoom);
            }
        });

        imageSleep = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSleep)),
                new TextureRegionDrawable(new TextureRegion(imgSleepPressed))
        );
        imageSleep.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.1f);
        imageSleep.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.25f, MindPuzzle.VIRTUAL_WIDTH * 0.25f);
        imageSleep.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.sleepRoom);
            }
        });

        stage.addActor(imageMenu);
        stage.addActor(imageSleep);
        stage.addActor(imageFood);
        stage.addActor(imageSocial);
        stage.addActor(imageHobbies);
        stage.addActor(imageSports);
    }

    // Calls every actor's act()-method that has added to the stage.
    private void update(float delta) {
        stage.act(delta);
    }

    // Called when the screen should render itself.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
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

    // Called when the Application is destroyed. Disposes the stage and all its actors.
    @Override
    public void dispose() {
        stage.dispose();
    }
}

