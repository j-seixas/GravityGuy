package Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import Controller.Entities.BlockBody;
import Controller.Entities.PlayerBody;
import Models.GameModel;

public class GameController implements ContactListener {

    private static GameController gameController = null;

    private PlayerBody player;
    private ArrayList<BlockBody> blocks;
    private World world;

    private static final float LINEAR_SPEED = 10f; //TODO
    private static final float GRAVITY = 10f; //TODO

    private GameController(){
        world = new World(new Vector2(0, GRAVITY), true);
        world.setContactListener(this);
        player = new PlayerBody(world, GameModel.instance().getPlayer());
    }

    public static GameController instance(){
        if(gameController == null)
            gameController = new GameController();
        return gameController;
    }

    public void update(float delta) {

    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
