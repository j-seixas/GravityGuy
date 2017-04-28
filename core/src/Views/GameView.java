package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

import Controller.GameController;
import Game.GravityGuy;
import Models.Entities.BlockModel;
import Models.Entities.PlayerModel;
import Models.GameModel;
import Views.Entities.BackgroundView;
import Views.Entities.BlockView;
import Views.Entities.PlayerView;


public class GameView extends ScreenAdapter {

    private GravityGuy game;
    private PlayerView playerView;
    private BlockView blockView;
    private BackgroundView backgroundView;

    public GameView() {
        super();
        this.game = GravityGuy.instance();
        playerView = new PlayerView();
        blockView = new BlockView();
        backgroundView = new BackgroundView();
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        GameController.instance().update(delta);

        game.getSpriteBatch().begin();
        drawBackground();
        drawEntities();
        game.getSpriteBatch().end();
    }

    private void handleInput(float delta){
        //TODO
    }

    private void drawEntities(){
        ArrayList<BlockModel> blocks = GameModel.instance().getBlocks();
        for(BlockModel blockModel : blocks){
            blockView.update(blockModel);
            blockView.draw(game.getSpriteBatch());
        }

        PlayerModel playerModel = GameModel.instance().getPlayer();
        playerView.update(playerModel);
        playerView.draw(game.getSpriteBatch());
    }

    private void drawBackground(){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        playerView.draw(game.getSpriteBatch());
    }
}
