package test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import org.junit.Test;

import Controller.Entities.PlayerBody;
import Controller.GameController;
import Models.Entities.PlayerModel;
import Models.GameModel;

import static org.junit.Assert.*;


public class GameControllerTest extends GameTest{

    @Test
    public void testGetsSets(){
        GameController gameController = GameController.instance();

        World world = gameController.getWorld();
        Vector2 vecgrav = new Vector2(0,1f);
        world.setGravity(vecgrav);

        assertEquals(world.getGravity(), vecgrav);
        gameController = GameController.reset();
        world = gameController.getWorld();

        assertNotEquals(world.getGravity(), vecgrav);

        assertTrue(gameController.isPlayerIsMoving());

    }

    @Test
    public void testUpdatePlayer(){
        GameController gameController = GameController.instance();
        PlayerBody player = gameController.getPlayer();

        player.getBody().setLinearVelocity(new Vector2(0,0));
        gameController.updatePlayer();

        assertFalse(gameController.isPlayerIsMoving());

        player.getBody().setLinearVelocity(new Vector2(0,0));
        GameModel.instance().getPlayer().setFinishLine(true);
        gameController.updatePlayer();
        assertFalse(gameController.isPlayerIsMoving());
        assertEquals(GameModel.instance().getPlayer().getCurrPlayerAction(), PlayerModel.PlayerAction.STOPPED);

        player.getBody().setLinearVelocity(new Vector2(0,5));
        gameController.updatePlayer();
        assertEquals(GameModel.instance().getPlayer().getCurrPlayerAction(), PlayerModel.PlayerAction.FALLINGUP);

        player.getBody().setLinearVelocity(new Vector2(0,-5));
        gameController.updatePlayer();
        assertEquals(GameModel.instance().getPlayer().getCurrPlayerAction(), PlayerModel.PlayerAction.FALLINGDOWN);

        player.getBody().setLinearVelocity(new Vector2(2,0));
        gameController.updatePlayer();
        assertEquals(GameModel.instance().getPlayer().getCurrPlayerAction(), PlayerModel.PlayerAction.RUNNING);
    }

}