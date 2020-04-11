package hero.spells;

import monsters.Monster;

public class DammageSpell extends Spell {

    static final int DAMMAGE_MANA_COST = 50;
    static final int DAMMAGE_GIVEN = 50;

    private int dammageGiven;

    public DammageSpell() {
        super("Dammage", DAMMAGE_MANA_COST);
        this.dammageGiven = DAMMAGE_GIVEN;
    }

    public void use(Monster monster){
        monster.receiveDammage(this.dammageGiven);
    }

}
