package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Game.GravityGuy;

/**
 * Defines what the game over screen should look like
 */
public class GameOverScreen implements Screen{
    private GravityGuy game;
    private Stage stage;

    private int score, gravityChanges;
    private Table table;
    private Label gameOverLabel, clickLabel, scoreLabel;

    /**
     * GameOverScreen constructor
     *
     * @param score The score obtained before loosing
     * @param gravityChanges The number of times the gravity has changed
     */
    public GameOverScreen(int score, int gravityChanges){
        game = GravityGuy.instance();
        this.score = score;
        this.gravityChanges = gravityChanges;
        stage = new Stage(new StretchViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, game.getCamera()));
    }

    /**
     * Shows the game over screen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        clickLabel = new Label(String.format("CLICK TO PLAY AGAIN!"),GravityGuy.instance().getSkin());
        scoreLabel = new Label(String.format("SCORE:  %d", score), GravityGuy.instance().getSkin());
        gameOverLabel = new Label(String.format("GAME OVER"),GravityGuy.instance().getSkin());
        gameOverLabel.setFontScale(1.1f);
        scoreLabel.setFontScale(1f);
        clickLabel.setFontScale(0.9f);


        table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(gameOverLabel).expandX().padTop(20);
        table.row();
        table.add(scoreLabel).expandX().padTop(30);
        table.row();
        table.add(clickLabel).expandX().padTop(30);

        stage.addActor(table);


        table.addAction(Actions.sequence(Actions.alpha(0),
                Actions.fadeIn(2) ));

        updateAchiev();
    }

    /**
     * Updates the achievements
     */
    private void updateAchiev(){
        game.getPlayServices().incrementAchievement(0,1);
        game.getPlayServices().incrementAchievement(1,1);
        if(gravityChanges >= 50) game.getPlayServices().unlockAchievement(1);
        if(score >= 100) game.getPlayServices().unlockAchievement(3);
        if(score == 666) game.getPlayServices().unlockAchievement(2);
        game.getPlayServices().submitScore(score);
    }

    /**
     * Renders the image
     * @param delta The time elapsed since the last frame
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.draw();
    }

    /**
     * Updates the screen
     * @param delta The time elapsed since the last frame
     */
    public void update(float delta){
        if(Gdx.input.justTouched()){
            game.setGameScreen();
        }
        stage.act(delta);
    }

    /**
     * Resizes the screen
     * @param width The new screen width
     * @param height The new screen height
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    /**
     * Unused
     */
    @Override
    public void pause() {

    }

    /**
     * Unused
     */
    @Override
    public void resume() {

    }

    /**
     * Unused
     */
    @Override
    public void hide() {

    }

    /**
     * Disposes the screen
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
