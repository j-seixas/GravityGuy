package Controller.Entities;


import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Models.Entities.PlayerModel;

public class PlayerBody extends EntityBody {

    public PlayerBody(World world, PlayerModel model) {
        super(world, model);
    }

    @Override
    protected BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    @Override
    protected void createFixture() {

    }
}
