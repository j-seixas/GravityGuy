package Controller.Entities;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import Models.Entities.EntityModel;

/**
 * This class structures and identifies
 * what's necessary to define the
 * physical body of any entity.
 */
public abstract class EntityBody {

    protected Body body;

    /**
     * EntityBody constructor
     * Defines the structure of any entity,
     * by calling the abstract methods
     *
     * @param world The physics world
     * @param model The model to which it's linked
     */
    EntityBody(World world, EntityModel model) {
        BodyDef bodyDef = createBodyDef();
        bodyDef.position.set(model.getX(), model.getY());
        body = world.createBody(bodyDef);
        body.setUserData(model);
    }

    /**
     * Defines the BodyDef of an entity
     *
     * @return Returns the generated BodyDef
     */
    protected abstract BodyDef createBodyDef();

    /**
     * Creates the FixtureDef of an entity and links it to the body
     */
    protected abstract void createFixture();

    /**
     * Gets the x coordinate of the entity's body
     * @return Returns the x coordinate of the entity's body
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Gets the y coordinate of the entity's body
     * @return Returns the y coordinate of the entity's body
     */
    public float getY() {
        return body.getPosition().y;
    }

    /**
     * Sets the entity body's linear velocity
     * @param velocity The new linear velocity
     */
    public void setLinearVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    /**
     * Gets the entity body's linear velocity
     * @return Returns the entity body's linear velocity
     */
    public Vector2 getLinearVelocity() { return body.getLinearVelocity(); }

    /**
     * Gets the entity body's data
     * @return Returns the entity body's data
     */
    public Object getUserData() {
        return body.getUserData();
    }

    /**
     * Gets the entity's body
     * @return Returns the entity's body
     */
    public Body getBody(){ return body;}
}
