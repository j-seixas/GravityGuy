package com.lpoo.gravityguy.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.gravityguy.Game.GravityGuyGame;
import com.lpoo.gravityguy.Actors.Player;

public class MainMenuScreen extends ScreenAdapter{

    private GravityGuyGame game;
    private World world;
    private Player player;

    public MainMenuScreen(GravityGuyGame game){
        super();
        this.game = game;
        world = new World(new Vector2(0f, -1f), true);
        player = new Player(world);
    }
}
