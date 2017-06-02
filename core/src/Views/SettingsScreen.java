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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Game.GravityGuy;


public class SettingsScreen implements Screen {

    private GravityGuy game;

    private Stage stage;
    private TextureRegion checkBoxOff, checkBoxOn, returnBtUp, returnBtDown;
    private CheckBox soundCheck;
    private ImageButton returnBt;
    private Label soundLabel;

    public SettingsScreen(){
        game = GravityGuy.instance();
        stage = new Stage(new StretchViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, game.getCamera()));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Texture buttons = game.getAssetManager().get("buttons.png");

        checkBoxOff = new TextureRegion(buttons, 2070, 0, 20, 20);
        checkBoxOn = new TextureRegion(buttons, 2070, 20, 20, 20);


        soundCheck = new CheckBox("",
                new CheckBox.CheckBoxStyle(new TextureRegionDrawable(checkBoxOff),
                        new TextureRegionDrawable(checkBoxOn), game.getFont(), Color.WHITE));
        soundCheck.setBounds(20, 50, 20, 20);
        soundCheck.setChecked(true);

        soundLabel = new Label("Music", new Label.LabelStyle(game.getFont(), Color.WHITE));
        soundLabel.setFontScale(1f);
        soundLabel.setPosition(60, 55);

        returnBtUp = new TextureRegion(buttons, 1600, 0, 400, 400);
        returnBtDown = new TextureRegion(buttons, 1600, 400, 400, 400);
        returnBt = new ImageButton(new TextureRegionDrawable(returnBtUp), new TextureRegionDrawable(returnBtDown));
        returnBt.setSize(20, 20);
        returnBt.setPosition(280, 188);
        returnBt.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setMenuScreen();
            }
        });

        stage.addActor(soundCheck);
        stage.addActor(returnBt);
        stage.addActor(soundLabel);

    }

    void update(float delta){
        if(!soundCheck.isChecked())
            game.getPlayServices().unlockAchievement(0);
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        update(delta);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
