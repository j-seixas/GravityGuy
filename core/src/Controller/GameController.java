package Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import Controller.Entities.PlayerBody;
import Models.Entities.BlockModel;
import Models.Entities.PlayerModel;
import Models.GameModel;

public class GameController implements ContactListener {

    private static GameController gameController = null;

    private PlayerBody player;
    private World world;

    private boolean playerIsMoving;

    private static float LINEAR_SPEED = 1f;
    private static final float GRAVITY = -5;

    private GameController(){
        world = new World(new Vector2(0, GRAVITY), true);
        world.setContactListener(this);
        player = new PlayerBody(world, GameModel.instance().getPlayer());
        player.setLinearVelocity(new Vector2(LINEAR_SPEED, 0));
        playerIsMoving = true;
    }

    public static GameController instance(){
        if(gameController == null)
            gameController = new GameController();
        return gameController;
    }

    public static GameController reset(){
        gameController = new GameController();
        return gameController;
    }

    public void update(float delta) {
        world.step(1/60f, 6, 2);

        updatePlayer();
    }


    public void updatePlayer(){
        if(((PlayerModel) player.getUserData()).isGravity())
            player.getBody().setGravityScale(1);
        else
            player.getBody().setGravityScale(-1);


        if(player.getBody().getLinearVelocity().y > 0){
            ((PlayerModel) player.getUserData()).setCurrPlayerAction(PlayerModel.PlayerAction.FALLINGUP);
            playerIsMoving = true;
        } else if(player.getBody().getLinearVelocity().y < 0) {
            ((PlayerModel) player.getUserData()).setCurrPlayerAction(PlayerModel.PlayerAction.FALLINGDOWN);
            playerIsMoving = true;
        }else if(player.getBody().getLinearVelocity().y == 0 && player.getBody().getLinearVelocity().x != 0) {
            ((PlayerModel) player.getUserData()).setCurrPlayerAction(PlayerModel.PlayerAction.RUNNING);
            playerIsMoving = true;
        }else if(((PlayerModel) player.getUserData()).isFinishLine()) {
            ((PlayerModel) player.getUserData()).setCurrPlayerAction(PlayerModel.PlayerAction.STOPPED);
            playerIsMoving = false;
        } else if(player.getBody().getLinearVelocity().y == 0 && player.getBody().getLinearVelocity().x == 0)
            playerIsMoving = false;

        ((PlayerModel) player.getUserData()).setPosition(player.getBody().getPosition().x, player.getBody().getPosition().y);
    }

    public World getWorld(){ return world; }


    public PlayerBody getPlayer() {
        return player;
    }


    public float getVelocity() {return LINEAR_SPEED;}

    public boolean isPlayerIsMoving() {
        return playerIsMoving;
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData() instanceof PlayerModel && bodyB.getUserData() instanceof BlockModel
                && ((BlockModel) bodyB.getUserData()).isFinal())
            ((PlayerModel) bodyA.getUserData()).setFinishLine(true);
        else if(bodyB.getUserData() instanceof PlayerModel && bodyA.getUserData() instanceof BlockModel
                && ((BlockModel) bodyA.getUserData()).isFinal())
            ((PlayerModel) bodyB.getUserData()).setFinishLine(true);

    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData() instanceof PlayerModel ||
                bodyB.getUserData() instanceof PlayerModel)
            player.setLinearVelocity(new Vector2(LINEAR_SPEED, player.getLinearVelocity().y));

    }


    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }



}
