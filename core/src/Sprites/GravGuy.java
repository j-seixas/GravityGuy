package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;

/**
 * Created by jnuno on 04-May-17.
 */

public class GravGuy extends Sprite {
    public World world;
    public Body body;

    public GravGuy(World world){
        this.world = world;
        createGravGuy();
    }

    public void createGravGuy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GravityGuy.PPM, 32 / GravityGuy.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GravityGuy.PPM);

        body.setUserData("Player");
        fdef.shape = shape;
        fdef.restitution = 0;
        body.createFixture(fdef);

    }
}
