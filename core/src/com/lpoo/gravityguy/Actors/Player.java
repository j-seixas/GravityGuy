package com.lpoo.gravityguy.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.lpoo.gravityguy.Game.GravityGuyGame;

public class Player extends Actor{
    private GravityGuyGame game;
    private World world;
    private Body body;
    private Sprite sprite; //TODO Change to final
    private Animation<TextureRegion> animation; //TODO Change to final
    private float stateTime = 0f;

    public Player(World w){
        super();
        game = GravityGuyGame.instance();
        world = w;
        initBody();
        initSprites();
        setVelocity(new Vector2(100, 0));
    }

    private void initSprites() {
        Texture texture = game.getAssetManager().get("hero.png");
        sprite = new Sprite(texture);
    }

    private void initBody() {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(def);
    }

    public void setVelocity(Vector2 velocity){
        body.setLinearVelocity(velocity);
    }

    @Override
    public void act(float delta){
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        System.out.println("Body: " + body.getPosition().x + ", " + body.getPosition().y);
        System.out.println("Sprite: " + sprite.getX() + ", " + sprite.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        sprite.setColor(getColor());
        sprite.draw(batch);
    }

    public void invertGravity(){
        body.setGravityScale(-1f);
    }
}
