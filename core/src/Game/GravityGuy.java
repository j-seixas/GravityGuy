package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

import Views.GameView;
import Views.MenuView;

public class GravityGuy extends Game {
    public enum State{ FALLINGDOWN, FALLINGUP, RUNNING, STOPPED};
    private static GravityGuy game = null;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    public static final float PPM = 100;
    public final int MAX_PLAYERS = 4;
    private int number_players = 1;
    private AssetManager assetManager;
    private GameView gameView;
    private MenuView menuView;
    private SpriteBatch spriteBatch;

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
        //instance().resetScreens();
        gameView = new GameView();
        instance().setGameScreen();
    }

    private void loadAssets() {
        assetManager.load("hero.png", Texture.class);
        //assetManager.load("map1.tmx", TiledMap.class);
        assetManager.finishLoading();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public int getNumberPlayers() {
        return number_players;
    }

    public void setNumberPlayers(int number_players) {
        this.number_players = number_players;
    }

    private void setMenuScreen() {
        setScreen(menuView);
    }

    private void setGameScreen() {
        setScreen(gameView);
    }

    public void resetScreens() {
        menuView = new MenuView();
        gameView = new GameView();
    }

    public SpriteBatch getSpriteBatch(){
        return spriteBatch;
    }
}
