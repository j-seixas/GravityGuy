package Views.Scenes;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Game.GravityGuy;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;

    Label timeLable, levelLable;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        viewport = new FitViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLable = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        levelLable = new Label(String.format("Level 1"), new Label.LabelStyle(new BitmapFont(), Color.WHITE) );

        table.add(levelLable).expandX().padTop(10);
        table.add(timeLable).expandX().padTop(10);
        //table.row();
        stage.addActor(table);
    }
}
