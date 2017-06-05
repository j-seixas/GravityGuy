package Models;


import Models.Entities.PlayerModel;

/**
 * Holds the the information all the
 * entities' body
 */
public class GameModel {

    private static GameModel gameModel = null;
    private PlayerModel player;

    private GameModel(){
        player = new PlayerModel(32, 32);
    }

    /**
     * Gets the GameModel instance
     * @return Returns the GameModel instance
     */
    public static GameModel instance(){
        if(gameModel == null)
            gameModel = new GameModel();
        return gameModel;
    }

    /**
     * Returns the GameModel
     * @return
     */
    public static GameModel reset(){
        gameModel = new GameModel();
        return gameModel;
    }

    /**
     * Gets the player model
     * @return Retuns the player model
     */
    public PlayerModel getPlayer() {
        return player;
    }
}
