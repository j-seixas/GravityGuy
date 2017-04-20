package com.lpoo.gravityguy.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.lpoo.gravityguy.Game.GravityGuyGame;
import com.lpoo.gravityguy.Stages.GameStage;

public class GameScreen extends ScreenAdapter{

    private GravityGuyGame game;
    private GameStage stage;

    public GameScreen(GravityGuyGame game){
        super();
        if(game.getNumberPlayers() <= 0 || game.getNumberPlayers() > 4)
            throw new IllegalArgumentException();
        this.game = game;
        this.stage = new GameStage(game);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        stage.act();
        stage.draw();
    }
}
