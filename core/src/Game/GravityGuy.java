package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import Tools.PlayServices;
import Views.GameOverScreen;
import Views.GameView;
import Views.LoadingScreen;
import Views.MenuView;
import Views.SettingsScreen;

/**
 * The game class
 * Initializes the necessary resources
 * and needed objects
 */
public class GravityGuy extends Game {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 208;
    public static final float PPM = 100;

    private static PlayServices playServices;
    private AssetManager assetManager;
    private GameView gameView;
    private MenuView menuView;
    private LoadingScreen loadingScreen;
    private SettingsScreen settingsScreen;

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    private static GravityGuy game = null;
    private BitmapFont font;
    private Skin skin;

    private boolean music;


    /**
     * GravityGuy constructor
     *
     * @param playServices Google Play services
     */
    public GravityGuy(PlayServices playServices)
    {
        this.playServices = playServices;
        game = this;
    }

    /**
     * Gets the game instance
     * @return Returns the game instance
     */
    public static GravityGuy instance() {
        return game;
    }


    /**
     * Initializes the game
     */
    @Override
    public void create() {
        assetManager = new AssetManager();
        camera = new OrthographicCamera();

        spriteBatch = new SpriteBatch();

        music = true;

        menuView = new MenuView();
        gameView = new GameView();
        settingsScreen = new SettingsScreen();
        loadingScreen = new LoadingScreen();
        instance().setScreen(loadingScreen);

    }

    /**
     * Gets the asset manager of the game
     * @return Returns the asset manager of the game
     */
    public AssetManager getAssetManager() {
        return assetManager;
    }

    /**
     * Sets the menu screen as the displayed screen
     */
    public void setMenuScreen() {
        setScreen(menuView);
    }

    /**
     * Restarts the game screen
     */
    public void restartGameScreen(){gameView = new GameView();}

    /**
     * Sets the game screen as the displayed screen
     */
    public void setGameScreen() {
        restartGameScreen();
        setScreen(gameView);
    }

    /**
     * Sets the settings screen as the displayed screen
     */
    public void setSettingsScreen() {setScreen(settingsScreen);}

    /**
     * Sets the game over screen as the displayed screen
     */
    public void setGameOverScreen(int score, int gravityChanges) { setScreen(new GameOverScreen(score, gravityChanges));}

    /**
     * Gets the game' sprite batch
     * @return Returns the game' sprite batch
     */
    public SpriteBatch getSpriteBatch(){
        return spriteBatch;
    }

    /**
     * Gets the game's camera
     * @return Returns the game's camera
     */
    public OrthographicCamera getCamera() {return camera; }

    /**
     * Initializes the used fonts
     */
    public void initFonts(){
        FreeTypeFontGenerator generator = assetManager.get("fontskins/Prime Regular.otf");
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.genMipMaps = true;
        params.minFilter = Texture.TextureFilter.MipMapLinearLinear;
        params.magFilter = Texture.TextureFilter.Linear;

        params.size = 24;
        params.color = Color.WHITE;
        params.borderWidth = 1;
        params.borderColor = Color.BLACK;

        font = generator.generateFont(params);
        font.setUseIntegerPositions(false);

    }

    /**
     * Gets the game's font
     * @return Return the game's font
     */
    public BitmapFont getFont() {
        return font;
    }

    /**
     * Initializes the skins
     */
    public void initSkin(){
        skin = new Skin();
        skin.addRegions(assetManager.get("fontskins/uiskin.atlas", TextureAtlas.class));
        skin.add("default-font", font);
        skin.load(Gdx.files.internal("fontskins/uiskin.json"));
        skin.getFont("default-font").getData().setScale(0.33f);

    }

    /**
     * Gets the game' skins
     *
     * @return Returns the game' skins
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     * Gets Google Play's services
     * @return Returns Google Play's services
     */
    public PlayServices getPlayServices(){return playServices;}

    /**
     * Checks if the music is enabled
     * @return Returns whether or not the music is enabled
     */
    public boolean getMusic(){return music;}

    /**
     * Enables or disables the music
     * @param music The boolean that determines if the music will be enabled/disabled
     */
    public void setMusic(boolean music){
        this.music = music;
    }

    /**
     * Disposes the game and its resources
     */
    @Override
    public void dispose() {

        if(skin != null)
            skin.dispose();
        if(menuView != null) menuView.dispose();
        if(gameView != null) gameView.dispose();
        if(loadingScreen != null) loadingScreen.dispose();
        if(settingsScreen != null) settingsScreen.dispose();
        if( assetManager != null) assetManager.dispose();
        if(spriteBatch != null) spriteBatch.dispose();
    }
}
