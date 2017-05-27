package Models.Entities;


public class BlockModel extends EntityModel {
    private boolean isFinal;

    public BlockModel(float x, float y, boolean isFinal){
        super(x ,y);
        this.isFinal = isFinal;
    }

    public boolean isFinal(){ return isFinal;}

}
