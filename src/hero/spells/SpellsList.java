package hero.spells;

import java.util.ArrayList;
import java.util.List;

public class SpellsList {

    private List<Spell> spells;

    public SpellsList() {
        this.spells = new ArrayList<>();

        // List of all spells
        spells.add(new HealingSpell());
        spells.add(new DamageSpell());
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

    public void removeInSpellsList(Spell spell){
        this.spells.remove(spell);
    }

    @Override
    public String toString() {
        return spells.toString();
    }
}
