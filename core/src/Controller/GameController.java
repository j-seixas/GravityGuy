package Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class GameController implements ContactListener {

    private static GameController gameController = null;
    private World world;


    private GameController(){
        world = new World(new Vector2(0, 10), true);

       /* shipBody = new ShipBody(world, GameModel.getInstance().getShip());

        List<AsteroidModel> asteroids = GameModel.getInstance().getAsteroids();
        for (AsteroidModel asteroid : asteroids)
            if (asteroid.getSize() == AsteroidModel.AsteroidSize.BIG)
                new BigAsteroidBody(world, asteroid);
            else if (asteroid.getSize() == AsteroidModel.AsteroidSize.MEDIUM)
                new MediumAsteroidBody(world, asteroid);*/

        world.setContactListener(this);
    }

    public GameController instance(){
        if(gameController == null)
            gameController = new GameController();
        return gameController;
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
