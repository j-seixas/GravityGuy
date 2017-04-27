package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import Controller.GameController;
import Game.GravityGuy;


public class GameView extends ScreenAdapter {

    private GravityGuy game;

    public GameView() {
        super();
        this.game = GravityGuy.instance();
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

    }

    private void drawEntities(){

    }

    private void drawBackground(){
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }
}
