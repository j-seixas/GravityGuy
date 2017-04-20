package com.lpoo.gravityguy.Actors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor{
    private Body body;
    private Sprite sprite; //TODO Change to final
    private Animation<TextureRegion> animation; //TODO Change to final
    private float stateTime = 0f;

    public Player(World world){
        super();
        if(world == null)
            throw new IllegalArgumentException();
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
    }

    @Override
    public void act(float delta){

    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        sprite.setColor(getColor());
        sprite.draw(batch);
    }

    public void invertGravity(){
        body.setGravityScale(-1f);
    }
}
