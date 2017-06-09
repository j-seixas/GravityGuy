package Controller.Entities;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;
import Models.Entities.PlayerModel;

/**
 * This class implements the necessary
 * methods to define the physical body
 * of a player.
 */
public class PlayerBody extends EntityBody {
    private PlayerModel model;

    /**
     * PlayerBody constructor
     *
     * @param world The physics world
     * @param model The model to which it's linked
     */
    public PlayerBody(World world, PlayerModel model) {
        super(world, model);
        this.model = model;
        createFixture();
        body.setSleepingAllowed(false);
    }

    /**
     * Defines the BodyDef of a player
     *
     * @return Returns the generated BodyDef
     */
    @Override
    protected BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    /**
     * Creates the FixtureDef of a player and links it to the body
     */
    @Override
    protected void createFixture() {
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(9 / GravityGuy.PPM);

        fixtureDef.shape = shape;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0;
        body.createFixture(fixtureDef);
    }

}
