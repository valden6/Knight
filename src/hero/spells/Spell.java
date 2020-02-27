package hero.spells;

public abstract class Spell {

    private String name;
    private int manaCost;

    public Spell(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", manaCost=" + manaCost +
                '}';
    }

}
