package com.lpoo.gravityguy.Stages;

import java.util.ArrayList;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameMap extends Stage {

    private ArrayList<com.lpoo.gravityguy.Actors.Block> blocks;
    private World world;

    public GameMap(World world){
        if(world == null)
            throw new IllegalArgumentException();
        blocks = new ArrayList<com.lpoo.gravityguy.Actors.Block>();
        com.lpoo.gravityguy.Actors.Block block = new com.lpoo.gravityguy.Actors.Block(world);
        blocks.add(block);
    }

}
