package fi.tamk.mindpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * LoadingScreen loads all the assets to the game.
 * The visual view to the user is the download bar that
 * shows how many assets have been loaded.
 */
public class LoadingScreen extends ScreenAdapter {
    /**
     * Class MindPuzzle object that allows to set screen from inside this class.
     */
    private final MindPuzzle app;
    /**
     * Renders the loading bar.
     */
    private ShapeRenderer shapeRenderer;
    /**
     * A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.
     */
    private Stage stage;
    /**
     * Progress variable tells how many assets AssetManager has been downloaded so far.
     */
    private float progress;

    /**
     * Class constructor.
     *
     * Uses the MindPuzzle reference to set the screen.
     * Creates a stage using StretchViewPort with MindPuzzle class' viewport dimensions and camera.
     *
     * @param app   MindPuzzle class's object
     */
    public LoadingScreen(final MindPuzzle app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(MindPuzzle.VIRTUAL_WIDTH, MindPuzzle.VIRTUAL_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setProjectionMatrix(app.camera.combined);
    }

    /**
     * Sets the progress' starting value and calls method to load the assets.
     */
    @Override
    public void show() {
        this.progress = 0f;
        queueAssets();
    }

    /**
     * Renders the loading bar to the screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f, MindPuzzle.VIRTUAL_WIDTH * 0.7f, MindPuzzle.VIRTUAL_HEIGHT * 0.02f);

        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.rect(MindPuzzle.VIRTUAL_WIDTH * 0.15f,MindPuzzle.VIRTUAL_HEIGHT * 0.5f, progress * (MindPuzzle.VIRTUAL_WIDTH * 0.7f), MindPuzzle.VIRTUAL_HEIGHT * 0.02f);
        shapeRenderer.end();
    }

    /**
     * Tells the AssetManager to keep checking if all the assets have been loaded.
     *  If statement keeps returning false until all the assets are finished loading
     *  After that, application changes to the MainMenuScreen.
     */
    public void update() {
        progress = MathUtils.lerp(progress, app.assets.getProgress(), 0.1f);

        if(app.assets.update() && progress >= app.assets.getProgress() - 0.001f) {
            app.setScreen(app.mainMenuScreen);
        }
    }

    /**
     * Loads all the assets used in the game one by one.
     */
    public void queueAssets() {
        app.assets.load("images/Buttons/Credits.png", Texture.class);
        app.assets.load("images/Buttons/CreditsPressed.png", Texture.class);
        app.assets.load("images/Buttons/EnglishOFF_Settings.png", Texture.class);
        app.assets.load("images/Buttons/EnglishOFF_SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/EnglishON_Settings.png", Texture.class);
        app.assets.load("images/Buttons/EnglishON_SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/Exit.png", Texture.class);
        app.assets.load("images/Buttons/ExitPressed.png", Texture.class);
        app.assets.load("images/Buttons/FinnishOFF_Settings.png", Texture.class);
        app.assets.load("images/Buttons/FinnishOFF_SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/FinnishON_Settings.png", Texture.class);
        app.assets.load("images/Buttons/FinnishON_SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/HowToPlay.png", Texture.class);
        app.assets.load("images/Buttons/HowToPlayPressed.png", Texture.class);
        app.assets.load("images/Buttons/Menu.png", Texture.class);
        app.assets.load("images/Buttons/MenuPressed.png", Texture.class);
        app.assets.load("images/Buttons/MusicOFF_Settings.png", Texture.class);
        app.assets.load("images/Buttons/MusicOFF_SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/MusicON_Settings.png", Texture.class);
        app.assets.load("images/Buttons/MusicON_SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/Play.png", Texture.class);
        app.assets.load("images/Buttons/PlayPressed.png", Texture.class);
        app.assets.load("images/Buttons/Settings.png", Texture.class);
        app.assets.load("images/Buttons/SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/SoundsOFF_Settings.png", Texture.class);
        app.assets.load("images/Buttons/SoundsOFF_SettingsPressed.png", Texture.class);
        app.assets.load("images/Buttons/SoundsON_Settings.png", Texture.class);
        app.assets.load("images/Buttons/SoundsON_SettingsPressed.png", Texture.class);

        app.assets.load("images/Characters/bird.png", Texture.class);
        app.assets.load("images/Characters/browncat.png", Texture.class);
        app.assets.load("images/Characters/browncatFlipped.png", Texture.class);
        app.assets.load("images/Characters/bunny.png", Texture.class);
        app.assets.load("images/Characters/bunnygrey.png", Texture.class);
        app.assets.load("images/Characters/bunnygreyFlipped.png", Texture.class);
        app.assets.load("images/Characters/cactusbuddy.png", Texture.class);
        app.assets.load("images/Characters/demoncat.png", Texture.class);
        app.assets.load("images/Characters/fishy.png", Texture.class);
        app.assets.load("images/Characters/fox.png", Texture.class);
        app.assets.load("images/Characters/ghost.png", Texture.class);
        app.assets.load("images/Characters/griffinblue.png", Texture.class);
        app.assets.load("images/Characters/griffinred.png", Texture.class);
        app.assets.load("images/Characters/griffinredFlipped.png", Texture.class);
        app.assets.load("images/Characters/hamster.png", Texture.class);
        app.assets.load("images/Characters/leafdragon.png", Texture.class);
        app.assets.load("images/Characters/leafdragonFlipped.png", Texture.class);
        app.assets.load("images/Characters/lynx.png", Texture.class);
        app.assets.load("images/Characters/mushroomguy.png", Texture.class);
        app.assets.load("images/Characters/robotcat.png", Texture.class);
        app.assets.load("images/Characters/robotcatFlipped.png", Texture.class);
        app.assets.load("images/Characters/skullbear.png", Texture.class);
        app.assets.load("images/Characters/skullwolf.png", Texture.class);
        app.assets.load("images/Characters/sloth.png", Texture.class);
        app.assets.load("images/Characters/snake.png", Texture.class);
        app.assets.load("images/Characters/swampmonster.png", Texture.class);
        app.assets.load("images/Characters/wizardcat.png", Texture.class);
        app.assets.load("images/Characters/wizardcatFlipped.png", Texture.class);
        app.assets.load("images/Characters/wolf.png", Texture.class);
        app.assets.load("images/Characters/wolfbrown.png", Texture.class);
        app.assets.load("images/Characters/wolfbrownFlipped.png", Texture.class);
        app.assets.load("images/Characters/yeti.png", Texture.class);

        app.assets.load("images/Credits_and_instructions/Credits.png", Texture.class);
        app.assets.load("images/Credits_and_instructions/HowTo.png", Texture.class);
        app.assets.load("images/Credits_and_instructions/Peliohjeet.png", Texture.class);
        app.assets.load("images/Credits_and_instructions/Tekijat.png", Texture.class);

        app.assets.load("images/Huonekuvakkeet/Harrastukset.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/HarrastuksetPainettu.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/Ihmissuhteet.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/IhmissuhteetPainettu.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/Liikunta.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/LiikuntaPainettu.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/Ravinto.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/RavintoPainettu.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/Uni.png", Texture.class);
        app.assets.load("images/Huonekuvakkeet/UniPainettu.png", Texture.class);

        app.assets.load("images/Painonapit/Asetukset.png", Texture.class);
        app.assets.load("images/Painonapit/AsetuksetPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/EnglantiOFF.png", Texture.class);
        app.assets.load("images/Painonapit/EnglantiOFFPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/EnglantiON.png", Texture.class);
        app.assets.load("images/Painonapit/EnglantiONPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/Lopeta.png", Texture.class);
        app.assets.load("images/Painonapit/LopetaPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/Musiikki_OFF2.png", Texture.class);
        app.assets.load("images/Painonapit/MusiikkiOFF.png", Texture.class);
        app.assets.load("images/Painonapit/MusiikkiON.png", Texture.class);
        app.assets.load("images/Painonapit/MusiikkiONPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/Ohjeet.png", Texture.class);
        app.assets.load("images/Painonapit/OhjeetPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/Paavalikko.png", Texture.class);
        app.assets.load("images/Painonapit/PaavalikkoPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/Pelaa.png", Texture.class);
        app.assets.load("images/Painonapit/PelaaPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/PeliaanetOFF.png", Texture.class);
        app.assets.load("images/Painonapit/PeliaanetOFFPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/PeliaanetON.png", Texture.class);
        app.assets.load("images/Painonapit/PeliaanetONPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/SuomiOFF.png", Texture.class);
        app.assets.load("images/Painonapit/SuomiOFFPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/SuomiON.png", Texture.class);
        app.assets.load("images/Painonapit/SuomiONPainettu.png", Texture.class);
        app.assets.load("images/Painonapit/Tekijat.png", Texture.class);
        app.assets.load("images/Painonapit/TekijatPainettu.png", Texture.class);

        app.assets.load("images/RoomIcons/Food.png", Texture.class);
        app.assets.load("images/RoomIcons/FoodPressed.png", Texture.class);
        app.assets.load("images/RoomIcons/Hobbies.png", Texture.class);
        app.assets.load("images/RoomIcons/HobbiesPressed.png", Texture.class);
        app.assets.load("images/RoomIcons/Sleep.png", Texture.class);
        app.assets.load("images/RoomIcons/SleepPressed.png", Texture.class);
        app.assets.load("images/RoomIcons/Social.png", Texture.class);
        app.assets.load("images/RoomIcons/SocialPressed.png", Texture.class);
        app.assets.load("images/RoomIcons/Sports.png", Texture.class);
        app.assets.load("images/RoomIcons/SportsPressed.png", Texture.class);

        app.assets.load("images/RoomSettings/EnOFF_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/EnOFF_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/EnON_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/EnON_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/FiOFF_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/FiOFF_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/FiON_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/FiON_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/MusicOFF_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/MusicOFF_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/MusicON_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/MusicON_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/Settings.png", Texture.class);
        app.assets.load("images/RoomSettings/SettingsPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/SoundsOFF_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/SoundsOFF_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/SoundsON_Room.png", Texture.class);
        app.assets.load("images/RoomSettings/SoundsON_RoomPressed.png", Texture.class);
        app.assets.load("images/RoomSettings/X.png", Texture.class);
        app.assets.load("images/RoomSettings/Xpressed.png", Texture.class);

        app.assets.load("images/answerLarge.png", Texture.class);
        app.assets.load("images/answerMedium.png", Texture.class);
        app.assets.load("images/background2.png", Texture.class);
        app.assets.load("images/badEnding.png", Texture.class);
        app.assets.load("images/bubble.png", Texture.class);
        app.assets.load("images/door.png", Texture.class);
        app.assets.load("images/doorFIN.png", Texture.class);
        app.assets.load("images/foodRoom.png", Texture.class);
        app.assets.load("images/happyending.png", Texture.class);
        app.assets.load("images/hobbiesRoom.png", Texture.class);
        app.assets.load("images/logo.png", Texture.class);
        app.assets.load("images/neutralEnding.png", Texture.class);
        app.assets.load("images/popUpBackground.jpg", Texture.class);
        app.assets.load("images/sleepRoom.png", Texture.class);
        app.assets.load("images/socialRoom.png", Texture.class);
        app.assets.load("images/sportRoom.png", Texture.class);

        app.assets.load("sounds/background.mp3", Music.class);
        app.assets.load("sounds/button.mp3", Sound.class);
        app.assets.load("sounds/right.mp3", Music.class);
        app.assets.load("sounds/wrong.mp3", Music.class);

        app.assets.load("ui/uiskin.atlas", TextureAtlas.class);
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
     * Disposes the ShapeRenderer.
     */
    @Override
    public void dispose() {
        app.dispose();
        stage.dispose();
        shapeRenderer.dispose();
    }
}
