package Models.Entities;


public class PlayerModel extends EntityModel{

    public enum PlayerAction { RUNNING, FALLING }

    private boolean flipped;
    private PlayerAction playerAction;

    public PlayerModel(int x, int y){
        super(x,y);
        flipped = false;
        playerAction = PlayerAction.RUNNING;
    }

    public PlayerAction getPlayerAction(){
        return playerAction;
    }

    public void setPlayerAction(PlayerAction playerAction){
        this.playerAction = playerAction;
    }

    public boolean isFlipped(){
        return flipped;
    }

    public void setFlipped(boolean flipped){
        this.flipped = flipped;
    }

}
