package Models.Entities;


/**
 * Holds the information of a player model
 */
public class PlayerModel extends EntityModel {
    /**
     * Holds the state of the player model
     */
    public enum PlayerAction { FALLINGDOWN, FALLINGUP, RUNNING, STOPPED}

    private boolean gravity, finishLine;
    private PlayerAction prevAction, currAction;

    /**
     * PlayerModel constructor
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public PlayerModel(float x, float y){
        super(x,y);

        gravity = true;
        finishLine = false;
        prevAction = PlayerAction.RUNNING;
        currAction = PlayerAction.RUNNING;
    }

    /**
     * Gets the current player state
     * @return Returns the current player state
     */
    public PlayerAction getCurrPlayerAction(){ return this.currAction; }

    /**
     * Sets the current player state
     * @param playerAction The new player state
     */
    public void setCurrPlayerAction(PlayerAction playerAction){
        this.currAction = playerAction;
    }

    /**
     * Gets the previous player state
     * @return Returns the previous player state
     */
    public PlayerAction getPrevPlayerAction(){ return this.prevAction; }

    /**
     * Sets the previous player state
     * @param playerAction The new player state
     */
    public void setPrevPlayerAction(PlayerAction playerAction){ this.prevAction = playerAction; }

    /**
     * Checks if the gravity is normal or inverted
     * @return Returns whether the gravity is normal or inverted
     */
    public boolean isGravity(){ return gravity; }

    /**
     * Sets the gravity's direction
     * @param gravity Gravity direction
     */
    public void setGravity(boolean gravity){
        this.gravity = gravity;
    }

    /**
     * Checks if the player reached the end of the level
     * @return Returns whether or not the player reached the end of the level
     */
    public boolean isFinishLine() {
        return finishLine;
    }

    /**
     * Sets the information about the player reaching the end of the level
     * @param finishLine Indicates whether or not the player reached the end of the level
     */
    public void setFinishLine(boolean finishLine) {
        this.finishLine = finishLine;
    }
}
