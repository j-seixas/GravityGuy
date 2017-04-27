package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Views.GameView;
import Views.MenuView;

public class GravityGuy extends Game {

    private static GravityGuy game = null;

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
        instance().resetScreens();
        instance().setGameScreen();
    }

    private void loadAssets() {
        assetManager.load("hero.png", Texture.class);
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
