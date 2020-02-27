package hero.spells;

public class HealingSpell extends Spell {

    static final int HEALINGMANACOST = 50;
    static final int HEALINGIVEN = 50;

    private int dammageGiven;

    public HealingSpell() {
        super("Heal", HEALINGMANACOST);
        this.dammageGiven = HEALINGIVEN;
    }



}
