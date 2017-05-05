package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import Game.GravityGuy;
import Views.GameView;


public class GravGuy extends Sprite {
    public World world;
    public Body body;
    private TextureRegion jump;

    public GravGuy(World world, GameView screen){
        super(screen.getAtlas().findRegion("0Jump"));
        this.world = world;
        createGravGuy();
        jump = new TextureRegion(getTexture(), 0, 0, 567, 556);
        setBounds(0, 0, 32 / GravityGuy.PPM, 31 / GravityGuy.PPM);
        setRegion(jump);
    }

    public void createGravGuy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GravityGuy.PPM, 32 / GravityGuy.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GravityGuy.PPM);

        body.setUserData("Player");
        fdef.shape = shape;
        fdef.restitution = 0;
        body.createFixture(fdef);

    }
}
