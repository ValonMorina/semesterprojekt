package worldofzuul;

public class Inventory {

    private boolean hammer = false;
    private boolean wood = false;
    private boolean pickaxe = false;
    private boolean map = false;
    private boolean paper = false;
    private boolean stone = false;
    private boolean nail = false;
    private boolean iron = false;
    private boolean colorPens = false;
    private boolean shovel = false;
    private boolean concrete = false;
    private boolean pipes = false;

    public Inventory (boolean hammer,boolean wood,boolean pickaxe, boolean map,boolean paper,boolean stone,boolean nail,boolean iron, boolean colorPens,boolean shovel,boolean concrete,boolean pipes)
    {
        this.hammer = hammer;
        this.wood = wood;
        this.pickaxe = pickaxe;
        this.map = map;
        this.paper = paper;
        this.stone = stone;
        this.nail = nail;
        this.iron = iron;
        this.colorPens = colorPens;
        this.shovel = shovel;
        this.concrete = concrete;
        this.pipes = pipes;
    }
}
