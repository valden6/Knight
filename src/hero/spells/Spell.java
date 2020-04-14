package hero.spells;

public abstract class Spell {

    private String name;
    private int manaCost;

    public Spell(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }

    public void use(){
        System.out.println("The spell is use...");
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    @Override
    public String toString() {
        return name;
    }
}
