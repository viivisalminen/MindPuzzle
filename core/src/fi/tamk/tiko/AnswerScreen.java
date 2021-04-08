package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class AnswerScreen implements Screen {

    private final MindPuzzle app;

    private Stage stage;
    private Table background;
    private Skin skin;
    private boolean answer;
    private String line = "";
    private TextButton buttonX;
    private ShapeRenderer shapeRenderer;
    private Texture bubble, characterTxt;
    private Rectangle characterRec;

    public AnswerScreen (final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        getCharacter();
        characterRec = new Rectangle(0,0,characterTxt.getWidth() * 0.5f, characterTxt.getHeight() * 0.5f);
        bubble = app.assets.get("images/bubble.png", Texture.class);

        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font30);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        background = new Table();
        background.setBackground(new TextureRegionDrawable(new TextureRegion(app.assets.get("images/popUpBackground.jpg", Texture.class))));
        background.setFillParent(true);
        background.setDebug(true);
        stage.addActor(background);

        initButtons();

        app.addAnsweredQuestion(app.getPreviousScreen());

        if (checkTheAnswer()) {
            app.addPoint();
            MainMenuScreen.right.play();
            line = "Yay your answer was right!";
        } else if (!(checkTheAnswer())) {
            MainMenuScreen.wrong.play();
            line = "Oh no your answer wasn't that good!";
        }
    }

    private void getCharacter() {
        if(app.getCharacter().equals("bird")) {
            characterTxt = app.assets.get("images/Characters/bird.png", Texture.class);
        } else if (app.getCharacter().equals("browncat")) {
            characterTxt = app.assets.get("images/Characters/browncat.png", Texture.class);
        } else if (app.getCharacter().equals("demoncat")) {
            characterTxt = app.assets.get("images/Characters/demoncat.png", Texture.class);
        } else if (app.getCharacter().equals("fox")) {
            characterTxt = app.assets.get("images/Characters/fox.png", Texture.class);
        } else if (app.getCharacter().equals("ghost")) {
            characterTxt = app.assets.get("images/Characters/ghost.png", Texture.class);
        } else if (app.getCharacter().equals("griffinblue")) {
            characterTxt = app.assets.get("images/Characters/griffinblue.png", Texture.class);
        } else if (app.getCharacter().equals("griffinred")) {
            characterTxt = app.assets.get("images/Characters/griffinred.png", Texture.class);
        } else if (app.getCharacter().equals("hamster")) {
            characterTxt = app.assets.get("images/Characters/hamster.png", Texture.class);
        } else if (app.getCharacter().equals("leafdragon")) {
            characterTxt = app.assets.get("images/Characters/leafdragon.png", Texture.class);
        } else if (app.getCharacter().equals("lynx")) {
            characterTxt = app.assets.get("images/Characters/lynx.png", Texture.class);
        } else if (app.getCharacter().equals("mushroomguy")) {
            characterTxt = app.assets.get("images/Characters/mushroomguy.png", Texture.class);
        } else if (app.getCharacter().equals("robotcat")) {
            characterTxt = app.assets.get("images/Characters/robotcat.png", Texture.class);
        } else if (app.getCharacter().equals("skullbear")) {
            characterTxt = app.assets.get("images/Characters/skullbear.png", Texture.class);
        } else if (app.getCharacter().equals("skullwolf")) {
            characterTxt = app.assets.get("images/Characters/skullwolf.png", Texture.class);
        } else if (app.getCharacter().equals("sloth")) {
            characterTxt = app.assets.get("images/Characters/sloth.png", Texture.class);
        } else if (app.getCharacter().equals("snake")) {
            characterTxt = app.assets.get("images/Characters/snake.png", Texture.class);
        } else if (app.getCharacter().equals("swampmonster")) {
            characterTxt = app.assets.get("images/Characters/swampmonster.png", Texture.class);
        } else if (app.getCharacter().equals("wizardcat")) {
            characterTxt = app.assets.get("images/Characters/wizardcat.png", Texture.class);
        } else if (app.getCharacter().equals("wolf")) {
            characterTxt = app.assets.get("images/Characters/wolf.png", Texture.class);
        } else if (app.getCharacter().equals("yeti")) {
            characterTxt = app.assets.get("images/Characters/yeti.png", Texture.class);
        }
    }

    private void initButtons() {
        float buttonSize = MindPuzzle.VIRTUAL_WIDTH * 0.075f;

        buttonX = new TextButton("X", skin, "default");
        buttonX.setPosition(MindPuzzle.VIRTUAL_WIDTH * 0.8f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
        buttonX.setSize(buttonSize, buttonSize);
        buttonX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if((app.getAnsweredQuestion("food") >= 5) && (app.getAnsweredQuestion("social") >= 5) && (app.getAnsweredQuestion("sleep") >= 5)
                        && (app.getAnsweredQuestion("hobbies") >= 5) && (app.getAnsweredQuestion("sports") >= 5)) {
                    app.setScreen(app.partyScreen);
                } else {
                    app.setScreen(app.previousScreen);
                }
            }
        });

        stage.addActor(buttonX);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calls every actor's act()-method that has added to the stage.
        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();

        app.batch.begin();
        app.batch.draw(bubble,MindPuzzle.VIRTUAL_WIDTH * 0.05f,MindPuzzle.VIRTUAL_HEIGHT * 0.65f, bubble.getWidth(), bubble.getHeight());
        app.font40.draw(app.batch, line,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.85f);
        app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.5f,Gdx.graphics.getHeight() * 0.5f, characterRec.width, characterRec.height);
        app.batch.end();
    }

    public boolean checkTheAnswer() {

        if(QuestionScreen.getPlayersAnswer().equals(QuestionScreen.getRightAnswer())) {
            answer = true;
        }
        else {
            answer = false;
        }

        return answer;
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
