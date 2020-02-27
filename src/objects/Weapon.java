package objects;

public abstract class Weapon extends Object {

    private int dammage;

    public Weapon(String name, int dammage, int durability, int value) {
        super(name,durability,value);
        this.dammage = dammage;
    }

    public int getDammage() {
        return dammage;
    }
}
