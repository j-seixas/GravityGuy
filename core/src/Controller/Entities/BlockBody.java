package Controller.Entities;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;
import Models.Entities.BlockModel;

/**
 * This class implements the necessary
 * methods to define the physical body
 * of a block.
 */
public class BlockBody extends EntityBody{


    private Rectangle rect;
    private BlockModel model;

    /**
     * BlockBody constructor
     *
     * @param world The physics world
     * @param rect  The rectangle received from the map loader
     * @param model The model to which it's linked
     */
    public BlockBody(World world, Rectangle rect, BlockModel model) {
        super(world, model);
        this.model = model;


        this.rect = rect;
        createFixture();
    }

    /**
     * Defines the BodyDef of a block
     *
     * @return Returns the generated BodyDef
     */
    @Override
    protected BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    /**
     * Creates the FixtureDef of a block and links it to the body
     */
    @Override
    protected void createFixture() {
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        shape.setAsBox(rect.getWidth() / 2 / GravityGuy.PPM, rect.getHeight() / 2 / GravityGuy.PPM);
        fixtureDef.shape = shape;
        fixtureDef.friction = model.isFinal() ? 1 : 0;
        fixtureDef.restitution = 0;
        body.createFixture(fixtureDef);
    }

    /**
     * Checks if the block is part of the blocks at the end of the game
     * @return Returns whether or not the block is part of the blocks at the end of the game
     */
    public boolean isFinal() {
        return model.isFinal();
    }

}
