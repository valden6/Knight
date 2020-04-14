package objects;

public abstract class Weapon extends Object {

    private int damage;

    public Weapon(String name, int damage, int durability, int value) {
        super(name,durability,value);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "\n " + this.getName() + " (damage: " + this.damage + " ; durability: " + this.getDurability() + " ; value: " + this.getValue() + ")";
    }

}
