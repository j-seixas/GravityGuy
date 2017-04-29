package Views.Entities;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Models.Entities.EntityModel;

public abstract class EntityView {
    protected Sprite sprite;

    public void draw(SpriteBatch spriteBatch){
        sprite.draw(spriteBatch);
    }

    public void update(EntityModel model, FitViewport viewport) {
        sprite.setCenter(model.getX(), model.getY());
    }
}
