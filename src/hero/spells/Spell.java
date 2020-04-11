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

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", manaCost=" + manaCost +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }
}
