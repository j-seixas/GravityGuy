package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import Tools.PlayServices;
import Views.GameOverScreen;
import Views.GameView;
import Views.LoadingScreen;
import Views.MenuView;
import Views.SettingsScreen;

public class GravityGuy extends Game {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 208;
    public static final float PPM = 100;
    public static final int MAX_PLAYERS = 4;


    private int number_players = 1;

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

    public GravityGuy(PlayServices playServices)
    {
        this.playServices = playServices;
        game = this;
    }


    public static GravityGuy instance() {
        //if (game != null)
           // game = new GravityGuy();
        return game;
    }


    @Override
    public void create() {
        assetManager = new AssetManager();
        camera = new OrthographicCamera();

        spriteBatch = new SpriteBatch();
       // instance().loadAssets();
        menuView = new MenuView();
        gameView = new GameView();
        settingsScreen = new SettingsScreen();
        //instance().setGameScreen();
        loadingScreen = new LoadingScreen();
        instance().setScreen(loadingScreen);

    }

    //private void loadAssets() { assetManager.finishLoading(); }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public int getNumberPlayers() {
        return number_players;
    }

    public void setNumberPlayers(int number_players) {
        this.number_players = number_players;
    }

    public void setMenuScreen() {
        setScreen(menuView);
    }

    public void restartGameScreen(){gameView = new GameView();}

    public void setGameScreen() {
        restartGameScreen();
        setScreen(gameView);
    }

    public void setSettingsScreen() {setScreen(settingsScreen);}

    public void setGameOverScreen(int score, int gravityChanges) { setScreen(new GameOverScreen(score, gravityChanges));}

    public SpriteBatch getSpriteBatch(){
        return spriteBatch;
    }

    public OrthographicCamera getCamera() {return camera; }

    public void initFonts(){
        FreeTypeFontGenerator generator = assetManager.get("Prime Regular.otf");
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

       // params.borderStraight = true;
        params.genMipMaps = true;
        params.minFilter = Texture.TextureFilter.MipMapLinearLinear;
        params.magFilter = Texture.TextureFilter.Linear;

        params.size = 24;
        params.color = Color.WHITE;
        params.borderWidth = 1;
        params.borderColor = Color.BLACK;
        //params.shadowOffsetX = 1;
        //params.renderCount = 500;
        font = generator.generateFont(params);

        //font = new BitmapFont(Gdx.files.internal("consolas.fnt"), Gdx.files.internal("consolas.png"), false);
        //font.setColor(Color.WHITE);
        font.setUseIntegerPositions(false);

        /*Texture texture = new Texture(Gdx.files.internal("font.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("font.fnt"), new TextureRegion(texture), false);*/
    }

    public BitmapFont getFont() {
        return font;
    }

    public void initSkin(){
        skin = new Skin();
        skin.addRegions(assetManager.get("uiskin.atlas", TextureAtlas.class));
        skin.add("default-font", font);
        skin.load(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(0.33f);

    }

    public Skin getSkin() {
        return skin;
    }

    public PlayServices getPlayServices(){return playServices;}

    @Override
    public void dispose() {
        font.dispose();
        menuView.dispose();
        if(gameView != null) gameView.dispose();
        loadingScreen.dispose();
        assetManager.dispose();
        spriteBatch.dispose();
    }
}
