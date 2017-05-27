package Models;

import java.util.ArrayList;

import Game.GravityGuy;
import Models.Entities.BlockModel;
import Models.Entities.CameraModel;
import Models.Entities.PlayerModel;

public class GameModel {

    private static GameModel gameModel = null;
    private PlayerModel player;
    private CameraModel camera;

    private GameModel(){
        player = new PlayerModel(32, 32);
        camera = new CameraModel(player.getX(), GravityGuy.HEIGHT / 2 / GravityGuy.PPM);
    }

    public static GameModel instance(){
        if(gameModel == null)
            gameModel = new GameModel();
        return gameModel;
    }


    public PlayerModel getPlayer() {
        return player;
    }

    public void update(float delta){

    }

    public void updateCamera(float x){
        camera.setPosition(x, camera.getY());
    }

}
