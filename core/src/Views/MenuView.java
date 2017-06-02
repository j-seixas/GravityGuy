package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Game.GravityGuy;

public class MenuView extends ScreenAdapter {

    private GravityGuy game;

    private Stage stage;

    private Image background;
    private TextureRegion btUpPlay, btUpSettings, btUpLeaderBoard, btUpAchiev, btUpExit;
    private TextureRegion btDownPlay, btDownSettings, btDownLeaderBoard, btDownAchiev, btDownExit;

    private ImageButton btPlay, btSettings, btLeaderBoard, btAchiev, btExit;

    public MenuView() {
        super();
        this.game = GravityGuy.instance();
        this.stage = new Stage(new StretchViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, game.getCamera()));

    }

    @Override
    public void resize(int width, int height) {
        //super.resize(width, height);
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void show() {
        //super.show();

        Gdx.input.setInputProcessor(stage);
        stage.clear();

        Texture backTex = game.getAssetManager().get("background.png");
        background = new Image(backTex);

        stage.addActor(background);

        background.setBounds(0,0,GravityGuy.WIDTH, GravityGuy.HEIGHT);

        initButtons();
        game.getCamera().position.x = stage.getWidth() / 2;
        game.getCamera().position.y = stage.getHeight() / 2;
    }

    private void initButtons(){
        initTexButtons();

        btPlay = new ImageButton(new TextureRegionDrawable(btUpPlay), new TextureRegionDrawable(btDownPlay));
        btPlay.setSize(40, 40);
        btPlay.setPosition(30, 40);
        btPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setGameScreen();
            }
        });
        btSettings = new ImageButton(new TextureRegionDrawable(btUpSettings), new TextureRegionDrawable(btDownSettings));
        btSettings.setSize(40, 40);
        btSettings.setPosition(80, 40);
        btSettings.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setSettingsScreen();
            }
        });
        btLeaderBoard = new ImageButton(new TextureRegionDrawable(btUpLeaderBoard), new TextureRegionDrawable(btDownLeaderBoard));
        btLeaderBoard.setSize(40, 40);
        btLeaderBoard.setPosition(130, 40);
        btLeaderBoard.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getPlayServices().showScore();
            }
        });
        btAchiev = new ImageButton(new TextureRegionDrawable(btUpAchiev), new TextureRegionDrawable(btDownAchiev));
        btAchiev.setSize(40, 40);
        btAchiev.setPosition(180, 40);
        btAchiev.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getPlayServices().showAchievement();
            }
        });
        btExit = new ImageButton(new TextureRegionDrawable(btUpExit), new TextureRegionDrawable(btDownExit));
        btExit.setSize(40,40);
        btExit.setPosition(230, 40);
        btExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.getPlayServices().signOut();
                Gdx.app.exit();
            }
        });

        stage.addActor(btPlay);
        stage.addActor(btSettings);
        stage.addActor(btLeaderBoard);
        stage.addActor(btAchiev);
        stage.addActor(btExit);
    }

    private void initTexButtons(){
        Texture buttons = game.getAssetManager().get("buttons.png");
        btUpPlay = new TextureRegion(buttons, 0, 0, 400, 400);
        btDownPlay = new TextureRegion(buttons, 0, 400, 400, 400);
        btUpSettings = new TextureRegion(buttons, 400, 0, 400, 400);
        btDownSettings = new TextureRegion(buttons, 400, 400, 400, 400);
        btUpLeaderBoard = new TextureRegion(buttons, 2090, 0, 400, 400);
        btDownLeaderBoard = new TextureRegion(buttons, 2090, 400, 400, 400);
        btUpAchiev = new TextureRegion(buttons, 800, 0, 400, 400);
        btDownAchiev = new TextureRegion(buttons, 800, 400, 400, 400);
        btUpExit = new TextureRegion(buttons, 1200, 0, 400, 400);
        btDownExit = new TextureRegion(buttons, 1200, 400, 400, 400);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    void update(float delta){
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }
}
