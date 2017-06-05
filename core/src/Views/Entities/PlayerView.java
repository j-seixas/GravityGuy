package Views.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

import Game.GravityGuy;
import Models.Entities.PlayerModel;
import Views.GameView;

/**
 * Hold the information necessary to
 * display a player to the screen
 */
public class PlayerView extends Sprite {

    private TextureRegion fallingUP, fallingDOWN;
    private Animation<TextureRegion> runningGG;
    private float stateTimer;
    private PlayerModel model;


    /**
     * PlayerView constructor
     *
     * @param model The model to which it's linked
     * @param screen The screen to which it should be displayed
     */
    public PlayerView(PlayerModel model, GameView screen){
        super(screen.getAtlas().findRegion("Jump"));
        stateTimer = 0;
        this.model = model;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 8; i++)
            frames.add(new TextureRegion(getTexture(), i * 400, 400, 400, 400));
        runningGG = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        fallingDOWN = new TextureRegion(getTexture(), 0, 0, 400, 400);
        setBounds(0, 0, 24 / GravityGuy.PPM,  24 / GravityGuy.PPM);
        setRegion(fallingDOWN);

        fallingUP = new TextureRegion(getTexture(), 0, 0, 400, 400);
        setBounds(0, 0, 24 / GravityGuy.PPM,  24 / GravityGuy.PPM);
        fallingUP.flip(false,true);

    }

    /**
     * Updates the image's position based on its model
     * @param delta The time elapsed since the last frame
     */
    public void update(float delta){
        setPosition(model.getX() - getWidth() / 2 , model.getY() - getHeight() / 2 );
        setRegion(getFrame(delta));
    }

    /**
     * Gets the correct image to ensure animation
     * @param delta The time elapsed since the last frame
     * @return Returns the current animation frame
     */
    public TextureRegion getFrame(float delta){
        PlayerModel.PlayerAction prevAction = model.getPrevPlayerAction();
        PlayerModel.PlayerAction currAction = model.getCurrPlayerAction();

        TextureRegion region;
        switch (currAction){
            case RUNNING:
                region = runningGG.getKeyFrame(stateTimer, true);
                break;
            case FALLINGDOWN:
                region = fallingDOWN;
                break;
            default:
                region = runningGG.getKeyFrame(1);
                break;
        }

        if(!model.isGravity() && !region.isFlipY())
            region.flip(false, true);
        else if(model.isGravity() && region.isFlipY())
            region.flip(false, true);
        stateTimer = currAction == prevAction ? stateTimer + delta : 0;
        model.setPrevPlayerAction(currAction);
        return region;
    }

}
