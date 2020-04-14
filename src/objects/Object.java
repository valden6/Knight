package objects;

public abstract class Object {

    private String name;
    private int durability;
    private int value;

    public Object(String name, int durability, int value) {
        this.name = name;
        this.durability = durability;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public String toString() {
        return "\n " + name + " (durability: " + durability + " ; value: " + value + ")";
    }
}
