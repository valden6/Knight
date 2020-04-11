package hero.spells;

import hero.Knight;

public class HealingSpell extends Spell {

    static final int HEALING_MANA_COST = 50;
    static final int HEALING_GIVEN = 50;

    private int healGiven;

    public HealingSpell() {
        super("Heal", HEALING_MANA_COST);
        this.healGiven = HEALING_GIVEN;
    }

    public void use(Knight player){
        player.setLife(player.getLife() + this.healGiven);
        if(player.getLife() > player.getMAXLIFE())
            player.setLife(player.getMAXLIFE());
        System.out.println("Your life is at " + player.getLife());
    }

}
