package hero.spells;

import monsters.Monster;
import settings.Settings;

public class DamageSpell extends Spell {

    private int damageGiven;

    public DamageSpell() {
        super("Damage", Settings.SPELL_DAMAGE_MANA_COST);
        this.damageGiven = Settings.SPELL_DAMAGE_GIVEN;
    }

    public void use(Monster monster){
        monster.receiveDamage(this.damageGiven);
    }

}
