package objects;

public class DamagePotion extends Object{

    private int damageGiven;

    public DamagePotion(String name, int durability, int value, int damageGiven) {
        super(name, durability, value);
        this.damageGiven = damageGiven;
    }

    @Override
    public String toString() {
        return "\n " + this.getName() + " (damage: " + this.damageGiven + " ; durability: " + this.getDurability() + " ; value: " + this.getValue() + ")";
    }
}
