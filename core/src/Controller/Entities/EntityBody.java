package Controller.Entities;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Models.Entities.EntityModel;

public abstract class EntityBody {

    private Body body;

    EntityBody(World world, EntityModel model) {
        BodyDef bodyDef = createBodyDef();
        bodyDef.position.set(model.getX(), model.getY());
        body = world.createBody(bodyDef);
        body.setUserData(model);
        createFixture();
    }

    protected abstract BodyDef createBodyDef();
    protected abstract void createFixture();

    protected void createFixture(FixtureDef fixtureDef){
        body.createFixture(fixtureDef);
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
