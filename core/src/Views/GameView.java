package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import Controller.GameController;
import Game.GravityGuy;
import Models.Entities.BlockModel;
import Models.Entities.PlayerModel;
import Models.GameModel;
import Views.Entities.BackgroundView;
import Views.Entities.BlockView;
import Views.Entities.PlayerView;
import Views.Scenes.Hud;


public class GameView extends ScreenAdapter {

    private GravityGuy game;
    private Hud hud;
    private PlayerView playerView;
    private BlockView blockView;
    private BackgroundView backgroundView;
    private FitViewport viewport;
    private OrthographicCamera camera;

    //Tiles
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d
    //private World world;
    //private Box2DDebugRenderer b2dr;


    public GameView() {
        super();
        this.game = GravityGuy.instance();
        playerView = new PlayerView();
        blockView = new BlockView();
        backgroundView = new BackgroundView();
        camera = new OrthographicCamera();
        viewport = new FitViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, camera);
        hud = new Hud(game.getSpriteBatch());
        // viewport.apply();
       // camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2, 0);
        maploader = new TmxMapLoader();
        map = maploader.load("map1.tmx");
        //map = game.getAssetManager().get("map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        //world = new World(new Vector2(0,0), true);
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(GravityGuy.WIDTH/2,GravityGuy.HEIGHT/2, 0);
    }

    @Override
    public void render(float delta) {
        update(delta);
        //GameController.instance().update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.getSpriteBatch().setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        /*
        camera.update();
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        drawBackground();
        drawEntities();
        game.getSpriteBatch().end(); */
    }

    public void handleInput(float delta){
        if(Gdx.input.isTouched())
            camera.position.x += 100 * delta;
    }

    public void update(float delta){
        handleInput(delta);

        camera.update();
        renderer.setView(camera);
    }

    private void drawEntities(){
        /*ArrayList<BlockModel> blocks = GameModel.instance().getBlocks();
        for(BlockModel blockModel : blocks){
            blockView.update(blockModel, viewport);
            blockView.draw(game.getSpriteBatch());
        }*/

        PlayerModel playerModel = GameModel.instance().getPlayer();
        playerView.update(playerModel, viewport);
        playerView.draw(game.getSpriteBatch());
    }

    private void drawBackground(){
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //playerView.draw(game.getSpriteBatch());
    }
}
