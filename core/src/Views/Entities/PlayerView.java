package Views.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import Game.GravityGuy;
import Models.Entities.PlayerModel;
import Views.GameView;


public class PlayerView extends Sprite {

    private TextureRegion fallingUP, fallingDOWN;
    private Animation<TextureRegion> runningGG;
    private float stateTimer;
    private PlayerModel model;


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


    public void update(float delta){
        setPosition(model.getX() - getWidth() / 2 , model.getY() - getHeight() / 2 );
        setRegion(getFrame(delta));
    }

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
