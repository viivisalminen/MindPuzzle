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

public class SocialRoom implements Screen {
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

    private TextButton buttonSettingsPopUp;
    private ImageButton doorButton, pixel1Button, pixel2Button, pixel3Button, pixel4Button, pixel5Button;
    private Texture doorTxt, pixel1Txt, pixel2Txt, pixel3Txt, pixel4Txt, pixel5Txt;
    private Texture doorTxtPressed;
    private boolean char1NotClicked = true;
    private boolean char2NotClicked = true;
    private boolean char3NotClicked = true;
    private boolean char4NotClicked = true;
    private boolean char5NotClicked = true;

    private String points = Integer.toString(MindPuzzle.getPoints());
    private String line = "";

    // Class constructor. Uses the MindPuzzle reference to set the screen.
    public SocialRoom(final MindPuzzle app) {
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

        if(app.getLanguage().equals("fi_FI")) {
            line = "PISTEET: ";
            doorTxt = app.assets.get("images/doorFIN.png", Texture.class);
            doorTxtPressed = app.assets.get("images/doorFIN.png", Texture.class);
        } else {
            line = "POINTS: ";
            doorTxt = app.assets.get("images/door.png", Texture.class);
            doorTxtPressed = app.assets.get("images/door.png", Texture.class);
        }

        pixel1Txt = app.assets.get("images/Characters/wizardcat.png", Texture.class);
        pixel2Txt = app.assets.get("images/Characters/swampmonster.png", Texture.class);
        pixel3Txt = app.assets.get("images/Characters/snake.png", Texture.class);
        pixel4Txt = app.assets.get("images/Characters/wolf.png", Texture.class);
        pixel5Txt = app.assets.get("images/Characters/yeti.png", Texture.class);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/socialRoom.png", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.music.play();
        }

        app.setPreviousScreen(app.socialRoom);
    }

    // Initializes the buttons used in this screen.
    private void initButtons() {
        doorButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(doorTxt)),
                new TextureRegionDrawable(new TextureRegion(doorTxtPressed))
        );
        doorButton.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.25f,MindPuzzle.VIRTUAL_HEIGHT * 0.675f);
        doorButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.5f, MindPuzzle.VIRTUAL_WIDTH * 0.5f);
        doorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.roomMenuScreen);
            }
        });

        pixel1Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(pixel1Txt)));
        pixel1Button.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.075f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f);
        pixel1Button.setSize(pixel1Txt.getWidth() * 0.35f, pixel1Txt.getHeight() * 0.35f);
        pixel1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                char1NotClicked = false;
                app.setPreviousCharacter("wizardcat");
                app.setScreen(app.questionScreen);
            }
        });

        pixel2Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(pixel2Txt)));
        pixel2Button.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.25f);
        pixel2Button.setSize(pixel2Txt.getWidth() * 0.25f, pixel2Txt.getHeight() * 0.25f);
        pixel2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                char2NotClicked = false;
                app.setPreviousCharacter("swampmonster");
                app.setScreen(app.questionScreen);
            }
        });

        pixel3Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(pixel3Txt)));
        pixel3Button.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.7f,MindPuzzle.VIRTUAL_HEIGHT * 0.6f);
        pixel3Button.setSize(pixel3Txt.getWidth() * 0.25f, pixel3Txt.getHeight() * 0.25f);
        pixel3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                char3NotClicked = false;
                app.setPreviousCharacter("snake");
                app.setScreen(app.questionScreen);
            }
        });

        pixel4Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(pixel4Txt)));
        pixel4Button.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.35f,MindPuzzle.VIRTUAL_HEIGHT * 0.15f);
        pixel4Button.setSize(pixel4Txt.getWidth() * 0.5f, pixel4Txt.getHeight() * 0.5f);
        pixel4Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                char4NotClicked = false;
                app.setPreviousCharacter("wolf");
                app.setScreen(app.questionScreen);
            }
        });

        pixel5Button = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(pixel5Txt)));
        pixel5Button.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.56f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        pixel5Button.setSize(pixel5Txt.getWidth() * 0.6f, pixel5Txt.getHeight() * 0.6f);
        pixel5Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                char5NotClicked = false;
                app.setPreviousCharacter("yeti");
                app.setScreen(app.questionScreen);
            }
        });

        buttonSettingsPopUp = new TextButton("Settings", skin, "default");
        buttonSettingsPopUp.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.45f,MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        buttonSettingsPopUp.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.1f, MindPuzzle.VIRTUAL_WIDTH * 0.1f);
        buttonSettingsPopUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                app.setScreen(app.settingsPopUp);
            }
        });

        stage.addActor(doorButton);
        if (char1NotClicked) {
            stage.addActor(pixel1Button);
        }
        if (char2NotClicked) {
            stage.addActor(pixel2Button);
        }
        if (char3NotClicked) {
            stage.addActor(pixel3Button);
        }
        if (char4NotClicked) {
            stage.addActor(pixel4Button);
        }
        if (char5NotClicked) {
            stage.addActor(pixel5Button);
        }

        stage.addActor(buttonSettingsPopUp);
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
        points = Integer.toString(MindPuzzle.getPoints());
        if(Gdx.graphics.getWidth() < 1000) {
            app.font40.draw(app.batch, line+points,Gdx.graphics.getWidth() * 0.075f,Gdx.graphics.getHeight() * 0.97f);
        } else if (Gdx.graphics.getWidth() >= 1000  && Gdx.graphics.getWidth() < 1200) {
            app.font60.draw(app.batch, line+points,MindPuzzle.VIRTUAL_WIDTH * 0.1f,MindPuzzle.VIRTUAL_HEIGHT * 0.9f);
        } else if (Gdx.graphics.getWidth() >= 1200) {
            app.font60.draw(app.batch, line+points,Gdx.graphics.getWidth() * 0.1f,Gdx.graphics.getHeight() * 1.5f);
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