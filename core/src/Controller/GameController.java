package Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import Controller.Entities.BlockBody;
import Controller.Entities.PlayerBody;
import Models.Entities.BlockModel;
import Models.Entities.EntityModel;
import Models.Entities.PlayerModel;
import Models.GameModel;

public class GameController implements ContactListener {

    private static GameController gameController = null;

    private PlayerBody player;
    private ArrayList<BlockBody> blocks;
    private World world;

    private static final float LINEAR_SPEED = 1f; //TODO
    private static final float GRAVITY = -5; //TODO

    private GameController(){
        world = new World(new Vector2(0, GRAVITY), true);
        world.setContactListener(this);
        blocks = new ArrayList<BlockBody>();
        player = new PlayerBody(world, GameModel.instance().getPlayer());
        ArrayList<BlockModel> blockModels = GameModel.instance().getBlocks();
        int i = 1; //
        for(BlockModel blockModel : blockModels)
            i++; //
           // blocks.add(new BlockBody(world, blockModel));
    }

    public static GameController instance(){
        if(gameController == null)
            gameController = new GameController();
        return gameController;
    }

    public void update(float delta) {
        world.step(1/60f, 6, 2);

        GameModel.instance().update(delta);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            //verifyBounds(body); TODO
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

       /* if(bodyA.getUserData() instanceof PlayerModel &&
           bodyB.getUserData() instanceof PlayerModel)
            initPlayerCollision(bodyA, bodyB);
        else if(bodyA.getUserData() instanceof PlayerModel &&
                bodyB.getUserData() instanceof BlockBody)
            initBlockCollision(bodyA);
        else if(bodyA.getUserData() instanceof BlockBody &&
                bodyB.getUserData() instanceof PlayerModel)
            initBlockCollision(bodyB);*/
    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData() instanceof PlayerModel ||
                bodyB.getUserData() instanceof PlayerModel)
            player.setLinearVelocity(new Vector2(LINEAR_SPEED, player.getLinearVelocity().y));
       /* else if(bodyA.getUserData() instanceof PlayerModel &&
                bodyB.getUserData() instanceof BlockBody)
            endBlockCollision(bodyA);
        else if(bodyA.getUserData() instanceof BlockBody &&
                bodyB.getUserData() instanceof PlayerModel)
            endBlockCollision(bodyB);*/
    }


    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }



}
