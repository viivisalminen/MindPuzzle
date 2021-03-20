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

public class FoodRoom implements Screen {
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

    private TextButton buttonDoor, buttonCharacter, buttonSettingsPopUp;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public FoodRoom(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        System.out.println("FOOD ROOM");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/roomBackground.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();
        app.setPreviousScreen(app.foodRoom);
    }

    // Initializes the buttons used in this screen.
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
        buttonCharacter.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.questionScreen);
            }
        });

        buttonSettingsPopUp = new TextButton("Settings", skin, "default");
        buttonSettingsPopUp.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.45f,MindPuzzle.VIRTUAL_HEIGHT * 0.2f);
        buttonSettingsPopUp.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.1f, MindPuzzle.VIRTUAL_WIDTH * 0.1f);
        buttonSettingsPopUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.settingsPopUp);
            }
        });


        stage.addActor(buttonDoor);
        stage.addActor(buttonCharacter);
        stage.addActor(buttonSettingsPopUp);
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

        app.batch.begin();
        app.font30.draw(app.batch, "Screen: FOOD ROOM", MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        app.batch.end();
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