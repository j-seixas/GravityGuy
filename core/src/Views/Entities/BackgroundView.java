package Views.Entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import Game.GravityGuy;

public class BackgroundView extends EntityView{

    public BackgroundView(){
        Texture texture = GravityGuy.instance().getAssetManager().get("background.png", Texture.class);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
