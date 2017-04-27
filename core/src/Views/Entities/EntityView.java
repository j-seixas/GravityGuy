package Views.Entities;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Models.Entities.EntityModel;

public abstract class EntityView {
    protected Sprite sprite;

    public void draw(SpriteBatch spriteBatch){
        sprite.draw(spriteBatch);
    }

    public void update(EntityModel model) {
        sprite.setCenter(model.getX(), model.getY());
    }
}
