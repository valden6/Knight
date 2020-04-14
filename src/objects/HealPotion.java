package objects;

public class HealPotion extends Object {

    private int lifeGiven;

    public HealPotion(String name, int durability, int value, int lifeGiven) {
        super(name, durability, value);
        this.lifeGiven = lifeGiven;
    }

    public int getLifeGiven() {
        return lifeGiven;
    }

    @Override
    public String toString() {
        return "\n " + this.getName() + " (life: " + this.lifeGiven + " ; durability: " + this.getDurability() + " ; value: " + this.getValue() + ")";
    }
}
