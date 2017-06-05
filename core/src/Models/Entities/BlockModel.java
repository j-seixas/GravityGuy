package Models.Entities;


/**
 * Holds the information of a block model
 */
public class BlockModel extends EntityModel {
    private boolean isFinal;

    /**
     * BlockModel constructor
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param isFinal The boolean that indicates if the block is a final block
     */
    public BlockModel(float x, float y, boolean isFinal){
        super(x ,y);
        this.isFinal = isFinal;
    }

    /**
     * Checks if the block is part of the blocks at the end of the game
     * @return Returns whether or not the block is part of the blocks at the end of the game
     */
    public boolean isFinal(){ return isFinal;}

}
