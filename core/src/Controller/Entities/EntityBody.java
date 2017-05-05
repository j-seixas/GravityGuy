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
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle rect;
    protected Body body;

    EntityBody(World world, TiledMap map, Rectangle rect) {
        this.world = world;
        this.map = map;
        this.rect = rect;
        BodyDef bdef = new BodyDef();


        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2)/ GravityGuy.PPM, (rect.getY() + rect.getHeight() / 2)/ GravityGuy.PPM);
        body = world.createBody(bdef);


        /*BodyDef bodyDef = createBodyDef();
        bodyDef.position.set(model.getX(), model.getY());
        body = world.createBody(bodyDef);
        body.setUserData(model);
        createFixture();*/
    }

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

    public Object getUserData() {
        return body.getUserData();
    }
}
