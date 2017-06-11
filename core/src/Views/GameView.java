package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Controller.GameController;
import Game.GravityGuy;
import Models.Entities.PlayerModel;
import Models.GameModel;
import Controller.PhysicsWorld;
import Views.Entities.PlayerView;
import Views.Scenes.HUD;

/**
 * Defines what the game screen should look like
 */
public class GameView extends ScreenAdapter {
    /**
     * Holds the state of the game
     */
    public enum GameState {PLAYING, LOST}

    private GameState state;
    private GravityGuy game;
    private TextureAtlas atlas;
    private HUD hud;

    private GameController gameController;
    private GameModel gameModel;
    private PlayerView playerView;

    private StretchViewport viewport;
    private OrthographicCamera camera;

    //Tiles
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Music music;

    private static boolean gyroAvailable = false;
    private boolean checkGyro = false;
    private int numberGravityChanges = 0;

    /**
     * GameView constructor
     */
    public GameView() {
        super();
        game = GravityGuy.instance();

        camera = game.getCamera();

        gameModel = GameModel.reset();
        gameController = GameController.reset();

        viewport = new StretchViewport(GravityGuy.WIDTH / GravityGuy.PPM, GravityGuy.HEIGHT / GravityGuy.PPM, camera);
        gyroAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Gyroscope);
    }

    /**
     * Resizes the screen
     * @param width The new screen width
     * @param height The new screen height
     */
    @Override
    public void resize(int width, int height){
        viewport.update(width , height);
        camera.position.set(GravityGuy.WIDTH/ 2 / GravityGuy.PPM,GravityGuy.HEIGHT / 2 / GravityGuy.PPM, 0);
    }

    /**
     * Renders the image
     * @param delta The time elapsed since the last frame
     */
    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.getSpriteBatch().setProjectionMatrix(camera.combined);

        game.getSpriteBatch().begin();
        playerView.draw(game.getSpriteBatch());
        game.getSpriteBatch().end();

        game.getSpriteBatch().setProjectionMatrix(hud.getCamera().combined);
        hud.draw();
    }

    private void changeGravity(){
        if(gameModel.getPlayer().getCurrPlayerAction() == PlayerModel.PlayerAction.RUNNING ){
            if(gameModel.getPlayer().isGravity())
                gameModel.getPlayer().setGravity(false);
            else
                gameModel.getPlayer().setGravity(true);
            game.getPlayServices().incrementAchievement(2,1);
            numberGravityChanges++;
        }
    }

    /**
     * Processes the user input
     * @param delta The time elapsed since the last frame
     */
    public void handleInput(float delta){
        if(Gdx.input.justTouched()) {
            changeGravity();
        }

        if(gyroAvailable){
            float gyroRotY = Gdx.input.getGyroscopeY();
            if(gyroRotY >= 3 && !checkGyro)
                checkGyro = true;
            else if (gyroRotY <= -3 && checkGyro){
                changeGravity();
                checkGyro = false;
            }
        }


    }

    /**
     * Updates the game elements
     * @param delta The time elapsed since the last frame
     */
    public void update(float delta){
        handleInput(delta);

        gameController.update(delta);
        playerView.update(delta);


        if(gameModel.getPlayer().getY() > (208 / GravityGuy.PPM) || gameModel.getPlayer().getY() < 0
                || camera.position.x - 150 / GravityGuy.PPM > gameModel.getPlayer().getX()
                || gameModel.getPlayer().isFinishLine()) {
            state = GameState.LOST;
            hud.setLost(true);
            game.setGameOverScreen(hud.getIntTime(), numberGravityChanges);
        }
        if(gameModel.getPlayer().getCurrPlayerAction() != PlayerModel.PlayerAction.STOPPED
                && state == GameState.PLAYING)
            camera.position.x = gameController.getLinearSpeed() * hud.getTime();
        camera.update();
        hud.update(delta);
        renderer.setView(camera);
    }

    /**
     * Gets the texture atlas
     * @return Returns the texture atlas
     */
    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * Shows the screen
     */
    @Override
    public void show() {
        atlas = game.getAssetManager().get("images/GravityGuySprites.atlas");
        playerView = new PlayerView(gameModel.getPlayer(), this);

        hud = new HUD();

        map = game.getAssetManager().get("maps/map3.tmx");
        state = GameState.PLAYING;
        camera.position.set(viewport.getWorldWidth() / 2 + 32 / GravityGuy.PPM, viewport.getWorldHeight() / 2, 0);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GravityGuy.PPM );
        new PhysicsWorld(gameController.getWorld(), map);

        music = game.getAssetManager().get("sounds/music.wav");
        music.setLooping(true);
        if(game.getMusic())
            music.play();
    }

    /**
     * Hides the screen
     */
    @Override
    public void hide() {
        super.hide();
        music.stop();
    }

    /**
     * Pauses the screen
     */
    @Override
    public void pause() {
        super.pause();
        music.pause();
    }

    /**
     * Resumes the screen
     */
    @Override
    public void resume() {
        super.resume();
        if(game.getMusic())
            music.play();
    }

    /**
     * Disposes the screen
     */
    @Override
    public void dispose() {
        if(map != null) map.dispose();
        if(renderer != null) renderer.dispose();
        if(hud != null) hud.dispose();
        if(atlas != null) atlas.dispose();
        if(music != null) music.dispose();
        if(gameController.getWorld()!= null) gameController.getWorld().dispose();
    }
}
