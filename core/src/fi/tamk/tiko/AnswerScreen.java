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
    private Texture characterTxt;
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

        characterTxt = app.assets.get("images/skullwolf.png", Texture.class);
        characterRec = new Rectangle(0,0,Gdx.graphics.getWidth() * 0.62f, Gdx.graphics.getHeight() * 0.42f);

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
        app.addAnsweredQuestion();

        if (checkTheAnswer()) {
            app.addPoint();
            line = "Yay your answer was right!";
        } else if (!(checkTheAnswer())) {
            line = "Oh no your answer wasn't that good!";
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
                if(app.getAnsweredQuestion() == 7) {
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
        app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.3f,Gdx.graphics.getHeight() * 0.5f, characterRec.width, characterRec.height);
        app.font40.draw(app.batch, line,MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.3f);
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
