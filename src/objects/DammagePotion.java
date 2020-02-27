package objects;

public class DammagePotion extends Object{

    private int dammageGiven;

    public DammagePotion(String name, int durability, int value, int dammageGiven) {
        super(name, durability, value);
        this.dammageGiven = dammageGiven;
    }
}
