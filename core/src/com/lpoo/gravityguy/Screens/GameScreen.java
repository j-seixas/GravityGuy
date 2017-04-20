package com.lpoo.gravityguy.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.lpoo.gravityguy.Stages.GameStage;

public class GameScreen extends ScreenAdapter{

    private GameStage stage;

    public GameScreen(int number_players){
        super();
        if(number_players <= 0 || number_players > 4)
            throw new IllegalArgumentException();
        stage = new GameStage();
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor( 1f, 1f, 1f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        stage.act();
        stage.draw();
    }
}
