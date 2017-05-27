package Models.Entities;



public class PlayerModel extends EntityModel {
    public enum PlayerAction { FALLINGDOWN, FALLINGUP, RUNNING, STOPPED}

    private boolean gravity, finishLine;
    private PlayerAction prevAction, currAction;


    public PlayerModel(float x, float y){
        super(x,y);

        gravity = true;
        finishLine = false;
        prevAction = PlayerAction.RUNNING;
        currAction = PlayerAction.RUNNING;
    }

    public PlayerAction getCurrPlayerAction(){ return this.currAction; }

    public void setCurrPlayerAction(PlayerAction playerAction){
        this.currAction = playerAction;
    }

    public PlayerAction getPrevPlayerAction(){ return this.prevAction; }

    public void setPrevPlayerAction(PlayerAction playerAction){ this.prevAction = playerAction; }

    public boolean isGravity(){ return gravity; }

    public void setGravity(boolean gravity){
        this.gravity = gravity;
    }

    public boolean isFinishLine() {
        return finishLine;
    }

    public void setFinishLine(boolean finishLine) {
        this.finishLine = finishLine;
    }

}
