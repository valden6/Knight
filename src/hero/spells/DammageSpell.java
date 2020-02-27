package hero.spells;

public class DammageSpell extends Spell {

    static final int DAMMAGEMANACOST = 50;
    static final int DAMMAGEGIVEN = 50;

    private int healGiven;

    public DammageSpell() {
        super("Dammage", DAMMAGEMANACOST);
        this.healGiven = DAMMAGEGIVEN;
    }

}
