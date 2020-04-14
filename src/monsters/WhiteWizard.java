package monsters;

import hero.Knight;
import objects.*;
import settings.Settings;

public class WhiteWizard extends Wizard {

    private int healing;

    public WhiteWizard() {
        super("White wizard", Settings.WHITE_WIZARD_MAX_LIFE,Settings.WHITE_WIZARD_GOLD_EARNED, Settings.WHITE_WIZARD_XP_EARNED,Settings.WHITE_WIZARD_MANA_MAX,Settings.WHITE_WIZARD_SPELL_MANA_COST,Settings.WHITE_WIZARD_REGENERATION_MANA,
                new WizardStaff("white wizard staff",25,100,300),
                new Hat("white wizard hat",5,100,40),
                new Dress("white wizard dress",5,100,60),
                new Gloves("white wizard gloves",2,100,130),
                new HealPotion("white wizard heal potion",100,100,50));
        this.healing = Settings.WHITE_WIZARD_HEALING;
    }

    @Override
    public void attack(Knight player) {
        if (this.getLife() <= 30){
            if (this.getHealPotion() != null) {
                System.out.println("The " + this.getName() + " use a potion");
                usePotion();
            } else if (this.getMana() >= this.getSpellManaCost()) {
                System.out.println("The " + this.getName() + " heal him");
                heal();
            } else {
                attackWithaWeapon(player);
            }
        } else if (this.getMana() <= this.getSpellManaCost()){
            System.out.println("The " + this.getName() + " regenerate his mana");
            regenerateMana();
        } else {
            attackWithaWeapon(player);
        }
    }

    private void heal(){
        if (this.getMana() >= this.getSpellManaCost()) {
            this.setMana(this.getMana() - this.getSpellManaCost());
            this.setLife(this.getLife() + this.healing);
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

}
