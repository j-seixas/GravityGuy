package com.lpoo.gravityguy.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.lpoo.gravityguy.Game.GravityGuyGame;
import com.lpoo.gravityguy.Stages.MainMenuStage;

public class MainMenuScreen extends ScreenAdapter{

    private GravityGuyGame game;
    private MainMenuStage stage;

    public MainMenuScreen(GravityGuyGame game){
        super();
        this.game = game;
        this.stage = new MainMenuStage();
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor( 1f, 1f, 1f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        stage.act();
        stage.draw();
    }
}
