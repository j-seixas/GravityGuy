package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Controller.GameController;
import Game.GravityGuy;
import Models.Entities.PlayerModel;
import Models.GameModel;
import Tools.PhysicsWorld;
import Views.Entities.PlayerView;
import Views.Scenes.HUD;


public class GameView extends ScreenAdapter {
    public enum GameState {PLAYING, WON, LOST}

    private GameState state;
    private GravityGuy game;
    private TextureAtlas atlas;
    private HUD HUD;

    private GameController gameController;
    private GameModel gameModel;
    private PlayerView playerView;

    private StretchViewport viewport;
    private OrthographicCamera camera;

    //Tiles
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d
    private Box2DDebugRenderer b2dr;


    public GameView() {
        super();
        game = GravityGuy.instance();

        camera = game.getCamera();


        state = GameState.PLAYING;



        gameController = GameController.instance();
        gameModel = GameModel.instance();


        viewport = new StretchViewport(GravityGuy.WIDTH / GravityGuy.PPM, GravityGuy.HEIGHT / GravityGuy.PPM, camera);







        b2dr = new Box2DDebugRenderer();


    }

    @Override
    public void resize(int width, int height){
        viewport.update(width , height);
        camera.position.set(GravityGuy.WIDTH/ 2 / GravityGuy.PPM,GravityGuy.HEIGHT / 2 / GravityGuy.PPM, 0);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //b2dr.render(gameController.getWorld(), camera.combined);

        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        game.getSpriteBatch().begin();
        playerView.draw(game.getSpriteBatch());
        game.getSpriteBatch().end();

        game.getSpriteBatch().setProjectionMatrix(HUD.getCamera().combined);
        HUD.draw();
    }

    public void handleInput(float delta){
        if((Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                && gameModel.getPlayer().getCurrPlayerAction() == PlayerModel.PlayerAction.RUNNING){

            if(gameModel.getPlayer().isGravity())
                gameModel.getPlayer().setGravity(false);
            else
                gameModel.getPlayer().setGravity(true);
        }
    }

    public void update(float delta){
        handleInput(delta);

        gameController.update(delta);
        gameModel.update(delta);
        playerView.update(delta);


        if(gameModel.getPlayer().getY() > (208 / GravityGuy.PPM) || gameModel.getPlayer().getY() < 0)
            state = GameState.LOST;

        if(gameModel.getPlayer().getCurrPlayerAction() != PlayerModel.PlayerAction.STOPPED
                && state == GameState.PLAYING)
            //camera.position.x = gameController.getVelocity() * HUD.getTime() ;
           // camera.position.x += 1.5 / GravityGuy.PPM;
            camera.position.x = gameModel.getPlayer().getX();
        camera.update();
        HUD.update(delta);
        renderer.setView(camera);
    }


    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {
        atlas = game.getAssetManager().get("GravityGuySprites.atlas");
        playerView = new PlayerView(gameModel.getPlayer(), this);

        HUD = new HUD();

        map = game.getAssetManager().get("map2.tmx");
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GravityGuy.PPM );
        new PhysicsWorld(gameController.getWorld(), map);
        //super.show();
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
        map.dispose();
        renderer.dispose();
        b2dr.dispose();
        HUD.dispose();
    }
}
