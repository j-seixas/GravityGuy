package Controller.Entities;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import Models.Entities.BlockModel;

public class BlockBody extends EntityBody{

    BlockBody(World world, BlockModel model) {
        super(world, model);
    }

    @Override
    protected BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    @Override
    protected void createFixture() {

    }
}
