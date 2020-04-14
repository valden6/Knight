package hero.spells;

import hero.Knight;
import settings.Settings;

public class HealingSpell extends Spell {

    private int healGiven;

    public HealingSpell() {
        super("Heal", Settings.SPELL_HEALING_MANA_COST);
        this.healGiven = Settings.SPELL_HEALING_GIVEN;
    }

    public void use(Knight player){
        player.setLife(player.getLife() + this.healGiven);
        if(player.getLife() > Settings.KNIGHT_MAX_LIFE)
            player.setLife(Settings.KNIGHT_MAX_LIFE);
        System.out.println("Your life is at " + player.getLife());
    }

}
