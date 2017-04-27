package Models.Entities;


public class PlayerModel extends EntityModel{

    private boolean flipped;

    public PlayerModel(int x, int y){
        super(x,y);
        flipped = false;
    }

}
