package Sprites;

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
import Views.GameView;


public class GravGuy extends Sprite {
    public enum State{ FALLINGDOWN, FALLINGUP, RUNNING, STOPPED};
    public State currState, prevState;
    public World world;
    public Body body;
    private TextureRegion fallingUP, fallingDOWN;
    private Animation<TextureRegion> runningGG;
    private float stateTimer;
    private boolean gravity;

    public GravGuy(World world, GameView screen){
        super(screen.getAtlas().findRegion("Jump"));
        this.world = world;
        currState = State.RUNNING;
        prevState = State.RUNNING;
        stateTimer = 0;
        gravity = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 8; i++)
            frames.add(new TextureRegion(getTexture(), i * 400, 400, 400, 400));
        runningGG = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        createGravGuy();
        fallingDOWN = new TextureRegion(getTexture(), 0, 0, 400, 400);
        setBounds(0, 0, 24 / GravityGuy.PPM,  24 / GravityGuy.PPM);
        setRegion(fallingDOWN);

        fallingUP = new TextureRegion(getTexture(), 0, 0, 400, 400);
        setBounds(0, 0, 24 / GravityGuy.PPM,  24 / GravityGuy.PPM);
        fallingUP.flip(false,true);

    }

    public void createGravGuy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GravityGuy.PPM, 32 / GravityGuy.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(9 / GravityGuy.PPM);

        body.setUserData("Player");
        fdef.shape = shape;
        fdef.restitution = 0;
        body.createFixture(fdef);

    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta){

        currState = getState();

        TextureRegion region;
        switch (currState){
            case RUNNING:
                region = runningGG.getKeyFrame(stateTimer, true);
                break;
            case FALLINGDOWN:
                region = fallingDOWN;
                break;
            default:
                region = runningGG.getKeyFrame(1);
        }

        if(body.getGravityScale() < 0 && !region.isFlipY())
            region.flip(false, true);
        else if(body.getGravityScale() > 0 && region.isFlipY())
            region.flip(false, true);
        stateTimer = currState == prevState ? stateTimer + delta : 0;
        prevState = currState;
        return region;
    }

    public State getState(){
        if(body.getLinearVelocity().y > 0){
            return State.FALLINGUP;
        } else if(body.getLinearVelocity().y < 0)
            return  State.FALLINGDOWN;
        else if(body.getLinearVelocity().y == 0 && body.getLinearVelocity().x != 0)
            return  State.RUNNING;
        else
            return State.STOPPED;
    }
}
