package test;

import org.junit.Test;

import Models.Entities.PlayerModel;
import Models.GameModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class GameModelTest extends GameTest {

    @Test
    public void testPositionPlayer(){
        GameModel gameModel = GameModel.instance();

        assertEquals(gameModel.getPlayer().getX(), gameModel.getPlayer().getY(), 32);
        gameModel.getPlayer().setPosition(64, 64);
        assertEquals(gameModel.getPlayer().getX(), gameModel.getPlayer().getY(), 64);

        gameModel = GameModel.reset();
        assertEquals(gameModel.getPlayer().getX(), gameModel.getPlayer().getY(), 32);

    }

    @Test
    public void testPlayerActions(){
        GameModel gameModel = GameModel.instance();

        assertEquals(gameModel.getPlayer().getCurrPlayerAction(),
                gameModel.getPlayer().getPrevPlayerAction());
        assertEquals(PlayerModel.PlayerAction.RUNNING, gameModel.getPlayer().getCurrPlayerAction());
        gameModel.getPlayer().setPrevPlayerAction(PlayerModel.PlayerAction.FALLINGDOWN);
        gameModel.getPlayer().setCurrPlayerAction(PlayerModel.PlayerAction.FALLINGUP);

        assertNotEquals(gameModel.getPlayer().getCurrPlayerAction(),
                gameModel.getPlayer().getPrevPlayerAction());
        assertEquals(PlayerModel.PlayerAction.FALLINGUP, gameModel.getPlayer().getCurrPlayerAction());
        assertEquals(PlayerModel.PlayerAction.FALLINGDOWN, gameModel.getPlayer().getPrevPlayerAction());

        assertNotEquals(gameModel.getPlayer().getCurrPlayerAction(), PlayerModel.PlayerAction.STOPPED);
    }

    @Test
    public void testSetsAndGets(){
        GameModel gameModel = GameModel.instance();

        gameModel.getPlayer().setGravity(true);
        assertTrue(gameModel.getPlayer().isGravity());
        gameModel.getPlayer().setGravity(false);
        assertFalse(gameModel.getPlayer().isGravity());

        gameModel.getPlayer().setFinishLine(true);
        assertTrue(gameModel.getPlayer().isFinishLine());
        gameModel.getPlayer().setFinishLine(false);
        assertFalse(gameModel.getPlayer().isFinishLine());
    }
}