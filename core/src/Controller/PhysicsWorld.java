package Controller;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Controller.Entities.BlockBody;
import Models.Entities.BlockModel;

/**
 * Translates the tile map to the
 * respective object
 */
public class PhysicsWorld {

    /**
     * PhysicsWorld constructor
     * @param world The physics world
     * @param map The tile map
     */
    public PhysicsWorld(World world, TiledMap map) {

        //Blocks
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new BlockBody(world, rect, new BlockModel(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2, false));
        }

        //FinalLine
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new BlockBody(world, rect, new BlockModel(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2, true));
        }
    }
}
