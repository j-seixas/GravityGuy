package com.lpoo.gravityguy.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.lpoo.gravityguy.Game.GravityGuyGame;
import com.lpoo.gravityguy.Stages.GameStage;

public class GameScreen extends ScreenAdapter {

    private GravityGuyGame game;
    private GameStage stage;

    public GameScreen() {
        super();
        this.game = GravityGuyGame.instance();
        this.stage = new GameStage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
}
