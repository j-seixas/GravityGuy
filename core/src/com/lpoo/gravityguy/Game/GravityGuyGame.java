package com.lpoo.gravityguy.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class GravityGuyGame extends Game {

    public static final int MAX_PLAYERS = 4;
    private AssetManager assetManager;
    private com.lpoo.gravityguy.Screens.MainMenuScreen mainMenu;

	@Override
	public void create () {
        assetManager = new AssetManager();
        //TODO Load assets
        assetManager.finishLoading();
        mainMenu = new com.lpoo.gravityguy.Screens.MainMenuScreen(this);
        setScreen(mainMenu);
    }
}
