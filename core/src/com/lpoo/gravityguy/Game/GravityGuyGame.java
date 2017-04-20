package com.lpoo.gravityguy.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.lpoo.gravityguy.Screens.MainMenuScreen;

public class GravityGuyGame extends Game {

    public static final int MAX_PLAYERS = 4;
    private int number_players;
    private AssetManager assetManager;
    private MainMenuScreen mainMenu;

	@Override
	public void create () {
        assetManager = new AssetManager();
        loadAssets();
        assetManager.finishLoading();
        mainMenu = new MainMenuScreen(this);
        setScreen(mainMenu);
    }

    public void loadAssets(){
        assetManager.load("..\\rsc\\hero.png", Texture.class);
    }

    public int getNumberPlayers(){
        return number_players;
    }
}
