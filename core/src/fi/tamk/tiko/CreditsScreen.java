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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

// CreditsScreen contains information about the authors of the game,
// the customer and possible source information of the material.
public class CreditsScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;
    // Renders points, lines, shape outlines and filled shapes.
    private ShapeRenderer shapeRenderer;
    private Texture imgMenu, imgMenuPressed, credits;
    private ImageButton imageMenu;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public CreditsScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(app.camera.combined);
    }

    // Called when this screen becomes the current screen for a Game.
    // Resets everything on this screen to defaults.
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        if(app.getLanguage().equals("fi_FI")) {
            imgMenu = app.assets.get("images/Painonapit/Paavalikko.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Painonapit/PaavalikkoPainettu.png", Texture.class);
            credits = app.assets.get("images/Credits_and_instructions/Tekijat.png", Texture.class);
        } else {
            imgMenu = app.assets.get("images/Buttons/Menu.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Buttons/MenuPressed.png", Texture.class);
            credits = app.assets.get("images/Credits_and_instructions/Credits.png", Texture.class);
        }

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
        imageMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.8f);
        imageMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.mainMenuScreen);
            }
        });

        stage.addActor(imageMenu);
    }

    // Called when the screen should render itself.
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calls every actor's act()-method that has added to the stage.
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        app.batch.begin();

        if(Gdx.graphics.getWidth() < 1000) {
            app.batch.draw(credits, MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f, credits.getWidth() * 0.4f, credits.getHeight() * 0.4f);
        } else if (Gdx.graphics.getWidth() >= 1000  && Gdx.graphics.getWidth() < 1200) {
            app.batch.draw(credits, MindPuzzle.VIRTUAL_WIDTH * 0.3f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f, credits.getWidth() * 0.65f, credits.getHeight() * 0.65f);
        } else if (Gdx.graphics.getWidth() >= 1200) {
            app.batch.draw(credits, MindPuzzle.VIRTUAL_WIDTH * 0.2f,MindPuzzle.VIRTUAL_HEIGHT * 0.1f, credits.getWidth() * 0.7f, credits.getHeight() * 0.75f);
        }
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
