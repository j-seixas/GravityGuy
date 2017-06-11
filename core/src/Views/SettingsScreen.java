package Views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Game.GravityGuy;

/**
 * Defines what the settings screen should look like
 */
public class SettingsScreen implements Screen {

    private GravityGuy game;

    private Stage stage;
    private TextureRegion checkBoxOff, checkBoxOn, returnBtUp, returnBtDown;
    private CheckBox soundCheck;
    private ImageButton returnBt;
    private Label soundLabel;
    private Image background;

    /**
     * SettingsScreen constructor
     */
    public SettingsScreen(){
        game = GravityGuy.instance();
        stage = new Stage(new StretchViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, game.getCamera()));
    }

    /**
     * Shows the screen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        initTextures();
        initButtonsCheckBox();
        initLabels();


        background.setBounds(0,0,GravityGuy.WIDTH, GravityGuy.HEIGHT);

        stage.addActor(background);
        stage.addActor(soundCheck);
        stage.addActor(returnBt);
        stage.addActor(soundLabel);

    }

    /**
     * Initializes textures
     */
    void initTextures(){
        Texture backTex = game.getAssetManager().get("images/settings.png");
        background = new Image(backTex);
        Texture buttons = game.getAssetManager().get("images/buttons.png");

        checkBoxOff = new TextureRegion(buttons, 2070, 0, 20, 20);
        checkBoxOn = new TextureRegion(buttons, 2070, 20, 20, 20);
        returnBtUp = new TextureRegion(buttons, 1600, 0, 400, 400);
        returnBtDown = new TextureRegion(buttons, 1600, 400, 400, 400);
    }

    /**
     * Initializes the buttons
     */
    void initButtonsCheckBox(){
        soundCheck = new CheckBox("",
                new CheckBox.CheckBoxStyle(new TextureRegionDrawable(checkBoxOff),
                        new TextureRegionDrawable(checkBoxOn), game.getFont(), Color.WHITE));
        soundCheck.setBounds(20, 50, 20, 20);
        if(game.getMusic())
            soundCheck.setChecked(true);

        returnBt = new ImageButton(new TextureRegionDrawable(returnBtUp), new TextureRegionDrawable(returnBtDown));
        returnBt.setSize(20, 20);
        returnBt.setPosition(280, 188);
        returnBt.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setMenuScreen();
            }
        });
    }

    /**
     * Initializes the labels
     */
    void initLabels(){
        soundLabel = new Label("Music", new Label.LabelStyle(game.getFont(), Color.WHITE));
        soundLabel.setFontScale(1f);
        soundLabel.setPosition(60, 55);
    }

    /**
     * Updates the screen
     * @param delta The time elapsed since the last frame
     */
    void update(float delta){
        if(!soundCheck.isChecked()) {
            game.setMusic(false);
            game.getPlayServices().unlockAchievement(0);
        }else
            game.setMusic(true);
        stage.act(delta);
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
    public void pause() { }

    /**
     * Unused
     */
    @Override
    public void resume() { }

    /**
     * Unused
     */
    @Override
    public void hide() { }

    /**
     * Disposes the screen
     */
    @Override
    public void dispose() {
        if(stage != null) stage.dispose();
    }
}
