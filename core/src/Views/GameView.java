package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

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
    private FitViewport viewport;
    private OrthographicCamera camera;

    public GameView() {
        super();
        this.game = GravityGuy.instance();
        playerView = new PlayerView();
        blockView = new BlockView();
        backgroundView = new BackgroundView();
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2, 0);
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2, 0);
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        GameController.instance().update(delta);

        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        drawBackground();
        drawEntities();
        game.getSpriteBatch().end();
    }

    private void handleInput(float delta){
        //TODO
    }

    private void drawEntities(){
        /*ArrayList<BlockModel> blocks = GameModel.instance().getBlocks();
        for(BlockModel blockModel : blocks){
            blockView.update(blockModel, viewport);
            blockView.draw(game.getSpriteBatch());
        }*/

        PlayerModel playerModel = GameModel.instance().getPlayer();
        playerView.update(playerModel, viewport);
        playerView.draw(game.getSpriteBatch());
    }

    private void drawBackground(){
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //playerView.draw(game.getSpriteBatch());
    }
}
