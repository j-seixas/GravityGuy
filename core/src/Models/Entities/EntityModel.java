package Models.Entities;


public abstract class EntityModel {
    private float x;
    private float y;

    public EntityModel(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
}
