package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen implements Screen {

    private final MindPuzzle app;

    private Stage stage;
    private Skin skin;
    private Skin menuSkin;
    private Texture imgTxt;
    private Texture imgTxtPressed;
    private Image buttonImage;
    private ImageButton imageButton;

    private TextButton buttonMenu, buttonPlay, buttonHowToPlay, buttonSettings, buttonCredits, buttonExit;

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
        stage.clear();

        imgTxt = app.assets.get("images/mushroom.png", Texture.class);
        imgTxtPressed = app.assets.get("images/mushroom.png", Texture.class);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons() {
        // PLAYBUTTON HERE
        imageButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgTxt)),
                new TextureRegionDrawable(new TextureRegion(imgTxtPressed))
        );
        imageButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.7f);
        imageButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.roomMenuScreen);
            }
        });
        stage.addActor(imageButton);

        buttonHowToPlay = new TextButton("How to play", skin, "default");
        buttonHowToPlay.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        buttonHowToPlay.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);

        buttonSettings = new TextButton("Settings", skin, "default");
        buttonSettings.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        buttonSettings.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.settingsScreen);
            }
        });

        buttonCredits = new TextButton("Credits", skin, "default");
        buttonCredits.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.4f);
        buttonCredits.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonCredits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.creditsScreen);
            }
        });

        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonExit.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.6f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //stage.addActor(buttonPlay);
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
        app.font.draw(app.batch, "Screen: MAIN MENU",MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
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
