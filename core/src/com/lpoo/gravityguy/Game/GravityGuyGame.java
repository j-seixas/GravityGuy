package com.lpoo.gravityguy.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.lpoo.gravityguy.Screens.GameScreen;
import com.lpoo.gravityguy.Screens.MainMenuScreen;

public class GravityGuyGame extends Game {

    public static final int MAX_PLAYERS = 4;
    private int number_players = 1;
    private AssetManager assetManager;
    private MainMenuScreen mainMenu;
    private GameScreen gameScreen;

	@Override
	public void create () {
        assetManager = new AssetManager();
        loadAssets();
        resetScreens();
        setGameScreen();
    }

    public void loadAssets(){
        assetManager.load("hero.png", Texture.class);
        assetManager.finishLoading();
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

    public int getNumberPlayers(){
        return number_players;
    }

    public void setNumberPlayers(int number_players){
        this.number_players = number_players;
    }

    public void setMenuScreen(){
        setScreen(mainMenu);
    }

    public void setGameScreen(){
        setScreen(gameScreen);
    }

    public void resetScreens(){
        mainMenu = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
    }
}
