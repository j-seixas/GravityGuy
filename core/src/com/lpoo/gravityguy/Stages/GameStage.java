package com.lpoo.gravityguy.Stages;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lpoo.gravityguy.Actors.Block;
import com.lpoo.gravityguy.Actors.Player;
import com.lpoo.gravityguy.Game.GravityGuyGame;

public class GameStage extends Stage {

    private GravityGuyGame game;
    private World world;
    private ArrayList<Block> blocks;
    private ArrayList<Player> players;

    public GameStage(){
        game = GravityGuyGame.instance();
        players = new ArrayList<Player>();
        blocks = new ArrayList<Block>();
        world = new World(new Vector2(0f, 1f), true);
        for(int i = 0; i < game.getNumberPlayers(); i++){
            //TODO Missing set bounds
            Player player = new Player(world);
            players.add(player);
            addActor(player);
        }
        //TODO Missing set bounds and generate randomly
       /* for(int i = 0; i < 10; i++){
            Block block = new Block(world);
            blocks.add(block);
            addActor(block);
        }*/
    }

    @Override
    public void act(float delta){
        super.act(delta);
        world.step(delta, 6, 2);
    }

    @Override
    public void draw(){
        super.draw();
    }

}
