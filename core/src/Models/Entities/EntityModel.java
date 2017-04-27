package Models.Entities;


public class EntityModel {

    private int x, y;

    public EntityModel(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
}
