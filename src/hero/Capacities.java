package hero;

import hero.spells.Spell;
import java.util.List;

public class Capacities {

    private List<Spell> spells;

    public Capacities(List<Spell> spells) {
        this.spells = spells;
    }

    public void addInCapacities(Spell spell){
        this.spells.add(spell);
    }

    public Spell getSpellByName(String name){
        Spell res = null;
        for (Spell spell : spells) {
            if( spell.getName().equals(name)){
                res = spell;
            }
        }
        return res;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    @Override
    public String toString() {
        return "Capacities: " + spells.toString() +
                "\n";
    }

}
