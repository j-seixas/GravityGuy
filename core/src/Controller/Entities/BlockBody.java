package Controller.Entities;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;
import Models.Entities.BlockModel;

public class BlockBody extends EntityBody{

    public BlockBody(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);

        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();

        shape.setAsBox(rect.getWidth() / 2 / GravityGuy.PPM, rect.getHeight() / 2 / GravityGuy.PPM);
        fdef.shape = shape;
        fdef.friction = 0;
        fdef.restitution = 0;
        body.createFixture(fdef);
    }


}
