package objects;

public class ManaPotion extends Object {

    private int manaGiven;

    public ManaPotion(String name, int durability, int value, int manaGiven) {
        super(name, durability, value);
        this.manaGiven = manaGiven;
    }

    public int getManaGiven() {
        return manaGiven;
    }

    @Override
    public String toString() {
        return "\n " + this.getName() + " (mana: " + this.manaGiven + " ; durability: " + this.getDurability() + " ; value: " + this.getValue() + ")";
    }
}
