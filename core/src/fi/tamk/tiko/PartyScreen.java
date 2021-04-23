package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class PartyScreen implements Screen {
    // Class MindPuzzle object that allows to set screen from inside this class.
    private final MindPuzzle app;
    // A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
    private Stage stage;
    // Positions the background picture to the Screen.
    private Table background;
    private String line = "";

    private Texture imgMenu, imgExit;
    private Texture imgMenuPressed ,imgExitPressed;
    private ImageButton imageMenu,imageExit;

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public PartyScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        if(app.getLanguage().equals("fi_FI")) {
            imgMenu = app.assets.get("images/Painonapit/Paavalikko.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Painonapit/PaavalikkoPainettu.png", Texture.class);
            imgExit = app.assets.get("images/Painonapit/Lopeta.png", Texture.class);
            imgExitPressed = app.assets.get("images/Painonapit/LopetaPainettu.png", Texture.class);

        } else {
            imgMenu = app.assets.get("images/Buttons/Menu.png", Texture.class);
            imgMenuPressed = app.assets.get("images/Buttons/MenuPressed.png", Texture.class);
            imgExit = app.assets.get("images/Buttons/Exit.png", Texture.class);
            imgExitPressed = app.assets.get("images/Buttons/ExitPressed.png", Texture.class);
        }

        background = new Table();

        if (app.getPoints() >= 18) {
            background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/happyending.png", Texture.class))));
            line = "Hurray! The villagers are happy again.\nLet's have a great party!";
        } else if (app.getPoints() > 8 && app.getPoints() < 18) {
            background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/neutralEnding.png", Texture.class))));
            line = "Well done! You managed to help the villagers a little. \nSome of them are happy enough to party!";
        } else if (app.getPoints() < 9) {
            background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/badEnding.png", Texture.class))));
            line = "Oh no! The villagers are still \ntoo sad to have a party!";
        }

        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        /*if(MainMenuScreen.getMusic()) {
            MainMenuScreen.music.play();
        }*/
    }
    // Initializes the buttons used in this screen.
    private void initButtons() {
        imageMenu = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgMenu)),
                new TextureRegionDrawable(new TextureRegion(imgMenuPressed))
        );
        imageMenu.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f,MindPuzzle.VIRTUAL_HEIGHT * 0.125f);
        imageMenu.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.resetGame();
                app.setScreen(app.mainMenuScreen);
            }
        });

        imageExit = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(imgExit)),
                new TextureRegionDrawable(new TextureRegion(imgExitPressed))
        );
        imageExit.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.125f, MindPuzzle.VIRTUAL_HEIGHT * 0.025f);
        imageExit.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.75f, MindPuzzle.VIRTUAL_HEIGHT * 0.09f);
        imageExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.resetGame();
                Gdx.app.exit();
            }
        });

        stage.addActor(imageMenu);
        stage.addActor(imageExit);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calls every actor's act()-method that has added to the stage.
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        app.batch.begin();
        app.font40.draw(app.batch, line,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        app.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
