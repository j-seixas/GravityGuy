package com.lpoo.gravityguy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

//import java.util.ArrayList;

public class GravityGuyGame extends Game {

    public static final int MAX_PLAYERS = 4;

    private World world;
    //private ArrayList<Player> players;
    Player player;

    private MainMenuScreen mainMenu;
    private GameScreen gameScreen;

	@Override
	public void create () {
        mainMenu = new MainMenuScreen();
        gameScreen = new GameScreen();
        world = new World(new Vector2(0f, -1f), true);
        //players = new ArrayList<Player>();
        //TODO We should only init players after we know how many will play
        //players.add(new Player(world));
        player = new Player(world);
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
