package com.lpoo.gravityguy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GravityGuyGame extends Game {

    private MainMenuScreen mainMenu;
    private GameScreen gameScreen;

	@Override
	public void create () {
        mainMenu = new MainMenuScreen();
        gameScreen = new GameScreen();
	}

	@Override
	public void render () {
        //Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void dispose () {
        mainMenu.dispose();
        gameScreen.dispose();
	}
}
