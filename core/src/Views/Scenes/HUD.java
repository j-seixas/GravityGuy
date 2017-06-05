package Views.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Controller.GameController;
import Game.GravityGuy;

/**
 * Holds the information necessary to display
 * to the screen about the game
 */
public class HUD implements Disposable{
    private GravityGuy game;
    private Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;

    Table table;
    Label timeLable, levelLable;
    private TextureRegion returnBtUp, returnBtDown;
    private ImageButton returnBt;

    private boolean lost;

    /**
     * HUD constructor
     */
    public HUD(){
        Gdx.input.setInputProcessor(stage);
        game = GravityGuy.instance();
        worldTimer = 0;
        timeCount = 0;
        lost = false;
        viewport = new StretchViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, GravityGuy.instance().getSpriteBatch());

        table = new Table();
        table.top();
        table.setFillParent(true);


        Texture buttons = game.getAssetManager().get("images/buttons.png");

        returnBtDown = new TextureRegion(buttons, 1600, 0, 400, 400);
        returnBtUp = new TextureRegion(buttons, 1600, 400, 400, 400);
        returnBt = new ImageButton(new TextureRegionDrawable(returnBtUp), new TextureRegionDrawable(returnBtDown));
        returnBt.setSize(25, 25);
        returnBt.setPosition(0, 183);
        returnBt.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.restartGameScreen();
                game.setMenuScreen();
            }
        });

        timeLable = new Label(String.format("%04d", worldTimer), GravityGuy.instance().getSkin());
        levelLable = new Label(String.format("SCORE:"),GravityGuy.instance().getSkin());

        timeLable.setFontScale(0.7f);
        levelLable.setFontScale(0.7f);
        table.add(levelLable).width(100).padTop(2).padLeft(100);
        table.add(timeLable).width(100).padTop(2);

        stage.addActor(returnBt);
        stage.addActor(table);
    }

    /**
     * Draws the HUD elements to screen
     */
    public void draw(){
        stage.draw();
    }

    /**
     * Updates the HUD
     * @param delta The time elapsed since the last frame
     */
    public void update(float delta){
        Gdx.input.setInputProcessor(stage);
        if(GameController.instance().isPlayerIsMoving()) {
            timeCount += delta;
            if (timeCount >= 1 && !lost) {
                worldTimer += 1;
                timeLable.setText(String.format("%04d", worldTimer));
                timeCount = 0;
            }
        }
        stage.act(delta);
    }

    /**
     * Gets the world timer
     * @return Returns the world timer
     */
    public int getIntTime() { return worldTimer;}

    /**
     * Gets the camera
     * @return Returns the camera
     */
    public Camera getCamera(){
        return stage.getCamera();
    }

    /**
     * Sets the information about the user loosing the game
     * @param lost Indicates if the user lost the game
     */
    public void setLost(boolean lost) {
        this.lost = lost;
    }

    /**
     * Disposes the HUD
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
