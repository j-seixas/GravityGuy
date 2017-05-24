package Models;

import java.util.ArrayList;

import Models.Entities.BlockModel;
import Models.Entities.PlayerModel;

public class GameModel {

    private static GameModel gameModel = null;

    private ArrayList<BlockModel> blocks;
    private PlayerModel player;

    private GameModel(){
        player = new PlayerModel(32, 32); //TODO
        blocks = new ArrayList<BlockModel>();
        blocks.add(new BlockModel(0,0)); //TODO
    }

    public static GameModel instance(){
        if(gameModel == null)
            gameModel = new GameModel();
        return gameModel;
    }

    public ArrayList<BlockModel> getBlocks() {
        return blocks;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void update(float delta){

    }
}
