package Views.Entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import Game.GravityGuy;

public class PlayerView extends EntityView {

    public PlayerView(){
        Texture texture = GravityGuy.getAssetManager().get("hero.png");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
