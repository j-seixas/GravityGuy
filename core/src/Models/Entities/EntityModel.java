package Models.Entities;


import Game.GravityGuy;

/**
 * Holds the information of any entity's model
 */
public abstract class EntityModel {
    private float x;
    private float y;

    /**
     * EntityModel constructor
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public EntityModel(float x, float y){
        this.x = x / GravityGuy.PPM;
        this.y = y / GravityGuy.PPM;
    }

    /**
     * Gets the y coordinate of the entity's model
     * @return Returns the y coordinate of the entity's model
     */
    public float getY() {
        return y;
    }

    /**
     * Gets the x coordinate of the entity's model
     * @return Returns the x coordinate of the entity's model
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the new position of the entity's model
     * @param x The new x coordinate
     * @param y The new y coordinate
     */
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
}
