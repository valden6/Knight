package objects;

public abstract class Armor extends Object {

    private int protection;

    public Armor(String name, int protection, int durability, int value) {
        super(name,durability,value);
        this.protection = protection;
    }

    public int getProtection() {
        return protection;
    }

    @Override
    public String toString() {
        return "\n " + this.getName() + " (protection: " + this.protection + " ; durability: " + this.getDurability() + " ; value: " + this.getValue() + ")";
    }
}
