package Controller.Entities;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;
import Models.Entities.EntityModel;

public abstract class EntityBody {
    //protected World world;

    protected Body body;

    EntityBody(World world, EntityModel model) {

        BodyDef bodyDef = createBodyDef();
        bodyDef.position.set(model.getX(), model.getY());
        body = world.createBody(bodyDef);
        body.setUserData(model);

    }

    protected abstract BodyDef createBodyDef();
    protected abstract void createFixture();


    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setTransform(float x, float y, float angle) {
        body.setTransform(x, y, angle);
    }

    public void setLinearVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public Vector2 getLinearVelocity() { return body.getLinearVelocity(); }

    public Object getUserData() {
        return body.getUserData();
    }
}
