package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private String rightLine = "";
    private String wrongLine = "";
    private String rightAnswer = "";
    private ImageButton xButton;
    private Texture bubble, characterTxt, exit, exitPressed;
    private Rectangle largerCharacter;

    public AnswerScreen (final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        getCharacter();
        largerCharacter = new Rectangle(0,0,characterTxt.getWidth() * 0.75f, characterTxt.getHeight() * 0.75f);
        bubble = app.assets.get("images/bubble.png", Texture.class);
        exit = app.assets.get("images/RoomSettings/X.png", Texture.class);
        exitPressed = app.assets.get("images/RoomSettings/Xpressed.png", Texture.class);

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

        if(MainMenuScreen.getMusic()) {
            MainMenuScreen.musicOn();
            MainMenuScreen.music.setVolume(0.4f);
        }

        rightAnswer = QuestionScreen.getRightAnswerAsString();

        if(app.getLanguage().equals("fi_FI")) {
            rightLine = "Jei, vastasit oikein! Nyt voin hyvillä"+"\nmielin lähteä valmistautumaan kyläjuhliin!";
            wrongLine = "Voi ei, vastauksesi ei ollut oikein."+"\nOikea vastaus olisi ollut: \n\n"+"\""+rightAnswer+"\"";
        } else {
            rightLine = "Awesome, you answered correctly!"+"\nNow I can happily set off"+"\nto prepare for the village party";
            wrongLine = "Oh no your answer wasn't that good!"+"\nYou should have answered: \n\n"+"\""+rightAnswer+"\"";
        }

        if (checkTheAnswer()) {
            app.addPoint();
            if(MainMenuScreen.getSound()) {
                MainMenuScreen.right.play();
                MainMenuScreen.right.setVolume(1f);
            }
            line = rightLine;
        } else if (!(checkTheAnswer())) {
            if(MainMenuScreen.getSound()) {
                MainMenuScreen.wrong.play();
                MainMenuScreen.wrong.setVolume(1f);
            }
            line = wrongLine;
        }
    }

    private void getCharacter() {
        if(app.getCharacter().equals("bird")) {
            characterTxt = app.assets.get("images/Characters/bird.png", Texture.class);
        } else if (app.getCharacter().equals("browncat")) {
            characterTxt = app.assets.get("images/Characters/browncat.png", Texture.class);
        } else if (app.getCharacter().equals("bunny")) {
            characterTxt = app.assets.get("images/Characters/bunny.png", Texture.class);
        } else if (app.getCharacter().equals("bunnygrey")) {
            characterTxt = app.assets.get("images/Characters/bunnygrey.png", Texture.class);
        } else if (app.getCharacter().equals("cactusbuddy")) {
            characterTxt = app.assets.get("images/Characters/cactusbuddy.png", Texture.class);
        } else if (app.getCharacter().equals("demoncat")) {
            characterTxt = app.assets.get("images/Characters/demoncat.png", Texture.class);
        } else if (app.getCharacter().equals("fishy")) {
            characterTxt = app.assets.get("images/Characters/fishy.png", Texture.class);
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
        } else if (app.getCharacter().equals("wolfbrown")) {
            characterTxt = app.assets.get("images/Characters/wolfbrown.png", Texture.class);
        } else if (app.getCharacter().equals("yeti")) {
            characterTxt = app.assets.get("images/Characters/yeti.png", Texture.class);
        }
    }

    private void initButtons() {
        xButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(exit)),
                new TextureRegionDrawable(new TextureRegion(exitPressed))
        );
        if(Gdx.graphics.getWidth() < 1000) {
            xButton.setPosition((Gdx.graphics.getWidth() / 2 + exit.getWidth() / 3),MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 1000 && Gdx.graphics.getWidth() < 1200) {
            xButton.setPosition((MindPuzzle.VIRTUAL_WIDTH / 2 - exit.getWidth() / 2),MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 1200 && Gdx.graphics.getWidth() < 2000) {
            xButton.setPosition((MindPuzzle.VIRTUAL_WIDTH  / 2 + exit.getWidth() * 3f),MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        } else if (Gdx.graphics.getWidth() >= 2000) {
            xButton.setPosition((MindPuzzle.VIRTUAL_WIDTH / 2 + exit.getWidth() * 4),MindPuzzle.VIRTUAL_HEIGHT * 0.05f);
        }
        xButton.setSize(MindPuzzle.VIRTUAL_WIDTH * 0.2f, MindPuzzle.VIRTUAL_WIDTH * 0.2f);
        xButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(MainMenuScreen.getSound()) {
                    MainMenuScreen.sound.play();
                }
                if((app.getAnsweredQuestion("food") == 5) && (app.getAnsweredQuestion("social") == 5) && (app.getAnsweredQuestion("sleep") == 5)
                        && (app.getAnsweredQuestion("hobbies") == 5) && (app.getAnsweredQuestion("sports") == 5)) {
                    app.setScreen(app.partyScreen);
                } else {
                    app.setScreen(app.previousScreen);
                }
            }
        });
        stage.addActor(xButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Calls every actor's act()-method that has added to the stage.
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        app.batch.begin();
        if(Gdx.graphics.getWidth() < 1000) {
            app.batch.draw(bubble,Gdx.graphics.getWidth() * 0.05f,Gdx.graphics.getHeight() * 0.6f, bubble.getWidth() * 0.65f, bubble.getHeight() * 0.85f);
            app.font30.draw(app.batch, line,Gdx.graphics.getWidth() * 0.125f,Gdx.graphics.getHeight() * 0.875f);
            app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.4f,Gdx.graphics.getHeight() * 0.15f, largerCharacter.width * 0.75f, largerCharacter.height * 0.75f);
        } else if (Gdx.graphics.getWidth() >= 1000  && Gdx.graphics.getWidth() < 1200) {
            app.batch.draw(bubble,Gdx.graphics.getWidth() * 0.05f,Gdx.graphics.getHeight() * 0.6f, bubble.getWidth(), bubble.getHeight() * 1.25f);
            app.font40.draw(app.batch, line,Gdx.graphics.getWidth() * 0.15f,Gdx.graphics.getHeight() * 0.875f);
            app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.4f,Gdx.graphics.getHeight() * 0.2f, largerCharacter.width, largerCharacter.height);
        } else if (Gdx.graphics.getWidth() >= 1200) {
            app.batch.draw(bubble,Gdx.graphics.getWidth() * 0.05f,Gdx.graphics.getHeight() * 0.65f, bubble.getWidth(), bubble.getHeight() * 1.25f);
            app.font40.draw(app.batch, line,Gdx.graphics.getWidth() * 0.15f,Gdx.graphics.getHeight() * 0.875f);
            app.batch.draw(characterTxt, Gdx.graphics.getWidth() * 0.4f,Gdx.graphics.getHeight() * 0.2f, largerCharacter.width, largerCharacter.height);
        }
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
    public void resize(int width, int height) { }

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
