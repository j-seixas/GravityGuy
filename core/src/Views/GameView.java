package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import Controller.GameController;
import Game.GravityGuy;
import Models.Entities.BlockModel;
import Models.Entities.PlayerModel;
import Models.GameModel;
import Sprites.GravGuy;
import Tools.PhysicsWorld;
import Views.Entities.BackgroundView;
import Views.Entities.BlockView;
import Views.Entities.PlayerView;
import Views.Scenes.Hud;


public class GameView extends ScreenAdapter {

    private GravityGuy game;
    private TextureAtlas atlas;
    private Hud hud;
    private PlayerView playerView;
    private BlockView blockView;
    private BackgroundView backgroundView;
    private FitViewport viewport;
    private OrthographicCamera camera;
    private GravGuy player;

    //Tiles
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d
    private World world;
    private Box2DDebugRenderer b2dr;


    public GameView() {
        super();
        this.game = GravityGuy.instance();
        playerView = new PlayerView();
        blockView = new BlockView();
        backgroundView = new BackgroundView();
        camera = new OrthographicCamera();
        hud = new Hud(game.getSpriteBatch());

        atlas = new TextureAtlas("GravityGuySprites.atlas");

        // viewport.apply();
        viewport = new FitViewport(GravityGuy.WIDTH / GravityGuy.PPM, GravityGuy.HEIGHT / GravityGuy.PPM, camera);
        // camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2, 0);
        maploader = new TmxMapLoader();
        map = maploader.load("map1.tmx");
        //map = game.getAssetManager().get("map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GravityGuy.PPM );

        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -5), true);
        b2dr = new Box2DDebugRenderer();
        player = new GravGuy(world, this);

        player.body.setLinearVelocity(new Vector2(1f, 0));

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if( contact.getFixtureA().getBody().getUserData() == "Final" || contact.getFixtureB().getBody().getUserData() == "Final"){
                    //hud.setTestLable("WIN");
                }
            }

            @Override
            public void endContact(Contact contact) {
                if( contact.getFixtureA().getBody().getUserData() == "Player" || contact.getFixtureB().getBody().getUserData() == "Player"){
                    player.body.setLinearVelocity(new Vector2(1f, 0));
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        new PhysicsWorld(world, map);
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width , height);
        camera.position.set(GravityGuy.WIDTH/ 2 / GravityGuy.PPM,GravityGuy.HEIGHT / 2 / GravityGuy.PPM, 0);
    }

    @Override
    public void render(float delta) {
        update(delta);
        //GameController.instance().update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, camera.combined);

        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        player.draw(game.getSpriteBatch());
        game.getSpriteBatch().end();

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
        if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            //System.out.println("Touched");
            if(player.body.getGravityScale() == -1)
                player.body.setGravityScale(1);
            else
                player.body.setGravityScale(-1);
        }

        //if(player.body.getFixtureList().)

    }

    public void update(float delta){
        handleInput(delta);

        world.step(1/60f, 6, 2); //60fps, Nao sei o que é o 6 e o 2

        camera.position.x = player.body.getPosition().x;

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

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {
        super.show();
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
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
