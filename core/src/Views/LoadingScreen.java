package Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import Game.GravityGuy;


public class LoadingScreen implements Screen {

    private GravityGuy game;
    private Stage stage;

    private Image loadingImg, titleImg;

    public LoadingScreen(){
        game = GravityGuy.instance();
        stage = new Stage(new StretchViewport(GravityGuy.WIDTH, GravityGuy.HEIGHT, game.getCamera()));

    }

    private void queueAssets(){
        game.getAssetManager().load("GravityGuySprites.atlas", TextureAtlas.class);
        game.getAssetManager().load("GravityGuySprites.png", Texture.class);
        game.getAssetManager().load("buttons.png", Texture.class);
        game.getAssetManager().load("background.png", Texture.class);
        game.getAssetManager().load("uiskin.atlas", TextureAtlas.class);
       // game.getAssetManager().load("uiskin.png", Texture.class);
        //game.getAssetManager().load("uiskin.json", Skin.class, new SkinLoader.SkinParameter("uiskin.atlas", ));
        game.getAssetManager().setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
        game.getAssetManager().load("Prime Regular.otf", FreeTypeFontGenerator.class);
        game.getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(
                new InternalFileHandleResolver()));
        game.getAssetManager().load("map1.tmx", TiledMap.class);
        game.getAssetManager().load("map2.tmx", TiledMap.class);

    }

    @Override
    public void show() {
        game.getAssetManager().load("LoadingScreen.png", Texture.class);
        game.getAssetManager().load("title.png", Texture.class);
        game.getAssetManager().finishLoading();

        Gdx.input.setInputProcessor(stage);


        Texture loadingTex = game.getAssetManager().get("LoadingScreen.png");
        loadingImg = new Image(loadingTex);
        Texture titleTex = game.getAssetManager().get("title.png");
        titleImg = new Image(titleTex);

        stage.addActor(loadingImg);
        stage.addActor(titleImg);

        loadingImg.setBounds(0,0,GravityGuy.WIDTH, GravityGuy.HEIGHT);
        titleImg.setBounds(0,0,GravityGuy.WIDTH, GravityGuy.HEIGHT);

        titleImg.addAction(Actions.sequence(Actions.alpha(0),
                                        Actions.fadeIn(1) ));


        queueAssets();
    }

    public void changeScreen(){

        titleImg.addAction(Actions.sequence(Actions.delay(1.5f),
                Actions.fadeOut(1.5f), Actions.alpha(0)));
        loadingImg.addAction(Actions.sequence(Actions.delay(2.5f), Actions.fadeOut(1.5f),
                Actions.delay(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                game.initFonts();
                game.initSkin();
                game.setMenuScreen();
            }
        })));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        update(delta);

        stage.draw();
    }

    public void update(float delta){
        stage.act(delta);

        if(game.getAssetManager().update())
            changeScreen();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
