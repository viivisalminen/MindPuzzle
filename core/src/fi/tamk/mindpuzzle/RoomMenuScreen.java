package fi.tamk.mindpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * RoomMenuScreen is the second menu of the game including
 * menu button to return to the main menu and rooms sleep,
 * food, social, hobbies and sports.
 */
public class RoomMenuScreen extends ScreenAdapter {
    /**
     * Class MindPuzzle object that allows to set screen from inside this class.
     */
    private final MindPuzzle app;
    /**
     * A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
     */
    private Stage stage;
    /**
     * Positions the background image to the Screen.
     */
    private Table background;
    /**
     * ImageButtons are used to navigate the game.
     */
    private ImageButton imageMenu;
    /**
     * ImageButtons used to go to the sleep room.
     */
    private ImageButton imageSleep;
    /**
     * ImageButtons used to go to the food room.
     */
    private ImageButton imageFood;
    /**
     * ImageButtons used to go to the hobby room.
     */
    private ImageButton imageHobbies;
    /**
     * ImageButtons used to go to the sport room.
     */
    private ImageButton imageSports;
    /**
     * ImageButtons used to go to the social room.
     */
    private ImageButton imageSocial;
    /**
     * Texture used in menu-button when button is not touched.
     */
    private Texture imgMenu;
    /**
     * Textures used in sleep-button when button is not touched.
     */
    private Texture imgSleep;
    /**
     * Textures used in food-button when button is not touched.
     */
    private Texture imgFood;
    /**
     * Textures used in hobby-button when button is not touched.
     */
    private Texture imgHobbies;
    /**
     * Textures used in sport-button when button is not touched.
     */
    private Texture imgSports;
    /**
     * Textures used in social-button when button is not touched.
     */
    private Texture imgSocial;
    /**
     * Textures used in menu-button when button is touched.
     */
    private Texture imgMenuPressed;
    /**
     * Textures used in sleep-button when button is touched.
     */
    private Texture imgSleepPressed;
    /**
     * Textures used in food-button when button is touched.
     */
    private Texture imgFoodPressed;
    /**
     * Textures used in hobby-button when button is touched.
     */
    private Texture imgHobbiesPressed;
    /**
     * Textures used in sports-button when button is touched.
     */
    private Texture imgSportsPressed;
    /**
     * Textures used in social-button when button is touched.
     */
    private Texture imgSocialPressed;

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public RoomMenuScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(app.VIRTUAL_WIDTH, app.VIRTUAL_HEIGHT, app.camera));
    }

    /**
     * Sets the InputProcessor that will receive all touch and key input events.
     * Initializes the textures.
     * Gets the music's value from MainMenuScreen and sets music either on or off depending the returning value.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        if(app.getLanguage().equals("fi_FI")) {
            imgMenu = app.assets.get("images/Painonapit/Paavalikko.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Painonapit/PaavalikkoPainettu.png", Texture.class);
            imgFood = app.assets.get("images/Huonekuvakkeet/Ravinto.png", Texture.class);
            imgFoodPressed = app.assets.get("images/Huonekuvakkeet/RavintoPainettu.png", Texture.class);
            imgSleep = app.assets.get("images/Huonekuvakkeet/Uni.png", Texture.class);
            imgSleepPressed = app.assets.get("images/Huonekuvakkeet/UniPainettu.png", Texture.class);
            imgSocial = app.assets.get("images/Huonekuvakkeet/Ihmissuhteet.png", Texture.class);
            imgSocialPressed = app.assets.get("images/Huonekuvakkeet/IhmissuhteetPainettu.png", Texture.class);
            imgHobbies = app.assets.get("images/Huonekuvakkeet/Harrastukset.png", Texture.class);
            imgHobbiesPressed = app.assets.get("images/Huonekuvakkeet/HarrastuksetPainettu.png", Texture.class);
            imgSports = app.assets.get("images/Huonekuvakkeet/Liikunta.png", Texture.class);
            imgSportsPressed = app.assets.get("images/Huonekuvakkeet/LiikuntaPainettu.png", Texture.class);
        } else {
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
        }

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/background2.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(app.mainMenuScreen.getMusic()) {
            app.mainMenuScreen.musicOn();
        }
    }

    /**
     * Initializes the buttons used in this screen.
     */
    private void initButtons() {
        imageMenu = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgMenu)),
                new TextureRegionDrawable(new TextureRegion(imgMenuPressed))
        );
        imageMenu.setPosition(app.VIRTUAL_WIDTH * 0.125f,app.VIRTUAL_HEIGHT * 0.8f);
        imageMenu.setSize(app.VIRTUAL_WIDTH * 0.75f, app.VIRTUAL_HEIGHT * 0.09f);
        imageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        imageFood = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgFood)),
                new TextureRegionDrawable(new TextureRegion(imgFoodPressed))
        );
        imageFood.setPosition(app.VIRTUAL_WIDTH * 0.2f,app.VIRTUAL_HEIGHT * 0.5f);
        imageFood.setSize(app.VIRTUAL_WIDTH * 0.25f, app.VIRTUAL_WIDTH * 0.25f);
        imageFood.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setScreen(app.foodRoom);
            }
        });

        imageSocial = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSocial)),
                new TextureRegionDrawable(new TextureRegion(imgSocialPressed))
        );
        imageSocial.setPosition(app.VIRTUAL_WIDTH * 0.5f,app.VIRTUAL_HEIGHT * 0.5f);
        imageSocial.setSize(app.VIRTUAL_WIDTH * 0.25f, app.VIRTUAL_WIDTH * 0.25f);
        imageSocial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setScreen(app.socialRoom);
            }
        });

        imageHobbies = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgHobbies)),
                new TextureRegionDrawable(new TextureRegion(imgHobbiesPressed))
        );
        imageHobbies.setPosition(app.VIRTUAL_WIDTH * 0.2f,app.VIRTUAL_HEIGHT * 0.3f);
        imageHobbies.setSize(app.VIRTUAL_WIDTH * 0.25f, app.VIRTUAL_WIDTH * 0.25f);
        imageHobbies.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setScreen(app.hobbiesRoom);
            }
        });

        imageSports = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSports)),
                new TextureRegionDrawable(new TextureRegion(imgSportsPressed))
        );
        imageSports.setPosition(app.VIRTUAL_WIDTH * 0.5f,app.VIRTUAL_HEIGHT * 0.3f);
        imageSports.setSize(app.VIRTUAL_WIDTH * 0.25f, app.VIRTUAL_WIDTH * 0.25f);
        imageSports.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
                }
                app.setScreen(app.sportsRoom);
            }
        });

        imageSleep = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgSleep)),
                new TextureRegionDrawable(new TextureRegion(imgSleepPressed))
        );
        imageSleep.setPosition(app.VIRTUAL_WIDTH * 0.35f,app.VIRTUAL_HEIGHT * 0.1f);
        imageSleep.setSize(app.VIRTUAL_WIDTH * 0.25f, app.VIRTUAL_WIDTH * 0.25f);
        imageSleep.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(app.mainMenuScreen.getSound()) {
                    app.mainMenuScreen.sound.play();
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

    /**
     * Calls every actor's act()-method that has added to the stage.
     * Draws the stage on the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * Resizes the viewport's dimensions based on the screen dimensions of
     * the device using the application.
     *
     * @param width     The viewport's width of the device
     * @param height    The viewport's height of the device
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Disposes the stage and all its actors.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();
        //imgMenu.dispose();
        //imgSleep.dispose();
        //imgFood.dispose();
        //imgHobbies.dispose();
        //imgSports.dispose();
        //imgSocial.dispose();
        //imgMenuPressed.dispose();
        //imgSleepPressed.dispose();
        //imgFoodPressed.dispose();
        //imgHobbiesPressed.dispose();
        //imgSportsPressed.dispose();
        //imgSocialPressed.dispose();
    }
}
