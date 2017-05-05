package Controller.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;


public class FinalBlockBody extends EntityBody{

    public FinalBlockBody(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);

        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        body.setUserData("Final");

        shape.setAsBox(rect.getWidth() / 2 / GravityGuy.PPM, rect.getHeight() / 2 / GravityGuy.PPM);
        fdef.shape = shape;
        fdef.friction = 1;
        fdef.restitution = 0;
        body.createFixture(fdef);
    }


}
