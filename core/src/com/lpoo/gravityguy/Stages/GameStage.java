package com.lpoo.gravityguy.Stages;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lpoo.gravityguy.Actors.Block;
import com.lpoo.gravityguy.Actors.Player;

public class GameStage extends Stage {

    private World world;
    private ArrayList<Block> blocks;
    private ArrayList<Player> players;

    public GameStage(){
        players = new ArrayList<Player>();
        blocks = new ArrayList<Block>();
        world = new World(new Vector2(0f, -1f), true);
        //TODO Change the constructors of the actors
        players.add(new Player(world));
        blocks.add(new Block(world));
    }

    @Override
    public void act(float delta){

    }

    @Override
    public void draw(){

    }

}
