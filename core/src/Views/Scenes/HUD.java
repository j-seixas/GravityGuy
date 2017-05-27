package Views.Scenes;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Game.GravityGuy;

public class HUD implements Disposable{
    private Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;

    Table table;
    Label timeLable, levelLable, testLable;

    public HUD(){
        worldTimer = 0;
        timeCount = 0;
        viewport = new FitViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, GravityGuy.instance().getSpriteBatch());

        table = new Table();
        table.top();
        table.setFillParent(true);

        timeLable = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        levelLable = new Label(String.format("Level 1"), new Label.LabelStyle(new BitmapFont(), Color.WHITE) );
        testLable = new Label(String.format("TEST"), new Label.LabelStyle(new BitmapFont(), Color.WHITE) );

        table.add(levelLable).expandX().padTop(10);
        table.add(testLable).expandX().padTop(10);
        table.add(timeLable).expandX().padTop(10);
        //table.row();
        stage.addActor(table);
    }

    public void draw(){
        stage.draw();
    }

    public void update(float delta){
        timeCount += delta;
        if(timeCount >= 1){
            worldTimer += 1;
            timeLable.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public Camera getCamera(){
        return stage.getCamera();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
