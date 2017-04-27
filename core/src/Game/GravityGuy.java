package Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import Views.GameView;
import Views.MenuView;

public class GravityGuy extends Game {

    private static GravityGuy game = null;

    public static final int MAX_PLAYERS = 4;
    private static int number_players = 1;
    private static AssetManager assetManager;
    private static GameView gameView;
    private static MenuView menuView;

    private GravityGuy() {
        assetManager = new AssetManager();
    }

    public static GravityGuy instance() {
        if (game == null)
            game = new GravityGuy();
        return game;
    }

    @Override
    public void create() {
        instance().loadAssets();
        instance().resetScreens();
        instance().setGameScreen();
    }

    public void loadAssets() {
        assetManager.load("hero.png", Texture.class);
        assetManager.finishLoading();
    }

    public static AssetManager getAssetManager() {
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
}
