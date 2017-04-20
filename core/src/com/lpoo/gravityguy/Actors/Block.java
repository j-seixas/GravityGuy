package com.lpoo.gravityguy.Actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Block {

    private BodyDef def;
    private Body body;

    public Block(World world){
      if(world == null)
        throw new IllegalArgumentException();
      def = new BodyDef();
      def.type = BodyDef.BodyType.StaticBody;
      body = world.createBody(def);
    }

}
