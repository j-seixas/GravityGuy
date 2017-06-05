package Controller.Entities;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;
import Models.Entities.BlockModel;


public class BlockBody extends EntityBody{


    private Rectangle rect;
    private BlockModel model;


    public BlockBody(World world, Rectangle rect, BlockModel model) {
        super(world, model);
        this.model = model;


        this.rect = rect;
        createFixture();
    }

    @Override
    protected BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

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

    public boolean isFinal() {
        return model.isFinal();
    }

}
