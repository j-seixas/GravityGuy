package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import Views.GameView;
import Views.MenuView;

public class GravityGuy extends Game {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 208;
    public static final float PPM = 100;
    public static final int MAX_PLAYERS = 4;

    private int number_players = 1;
    private AssetManager assetManager;
    private GameView gameView;
    private MenuView menuView;
    private SpriteBatch spriteBatch;
    private static GravityGuy game = null;


    public static GravityGuy instance() {
        if (game == null)
            game = new GravityGuy();
        return game;
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        spriteBatch = new SpriteBatch();
        instance().loadAssets();
        menuView = new MenuView();
        gameView = new GameView();
        instance().setGameScreen();
    }

    private void loadAssets() { assetManager.finishLoading(); }

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

    public void setGameScreen() {
        setScreen(gameView);
    }

    public SpriteBatch getSpriteBatch(){
        return spriteBatch;
    }
}
