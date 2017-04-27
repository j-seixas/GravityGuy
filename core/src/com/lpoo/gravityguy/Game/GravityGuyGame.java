package com.lpoo.gravityguy.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.lpoo.gravityguy.Screens.GameScreen;
import com.lpoo.gravityguy.Screens.MainMenuScreen;

public class GravityGuyGame extends Game {

    public enum GameState { MAIN_MENU, PLAYING, GAME_OVER }

    private static GravityGuyGame game = null;

    public static final int MAX_PLAYERS = 4;
    private static GameState gameState;
    private static int number_players = 1;
    private static AssetManager assetManager;
    private static MainMenuScreen mainMenu;
    private static GameScreen gameScreen;

    private GravityGuyGame() {
        assetManager = new AssetManager();
        gameState = GameState.PLAYING; //TODO Modify when we have a main menu
    }

    public static GravityGuyGame instance() {
        if (game == null)
            game = new GravityGuyGame();
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
        setScreen(mainMenu);
    }

    private void setGameScreen() {
        setScreen(gameScreen);
    }

    public void resetScreens() {
        mainMenu = new MainMenuScreen();
        gameScreen = new GameScreen();
    }

    @Override
    public void render(){
        if(gameState == GameState.MAIN_MENU)
            setMenuScreen();
        else if(gameState == GameState.PLAYING)
            setGameScreen();
        super.render();
        System.out.println("" + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());
    }
}
