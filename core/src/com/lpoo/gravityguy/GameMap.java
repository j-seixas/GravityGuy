package com.lpoo.gravityguy;

import java.util.ArrayList;
import com.badlogic.gdx.physics.box2d.World;

public class GameMap {

    private ArrayList<Block> blocks;
    private World world;

    public GameMap(World world){
        if(world == null)
            throw new IllegalArgumentException();
        blocks = new ArrayList<Block>();
        Block block = new Block(world);
        blocks.add(block);
    }

}
