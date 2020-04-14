package monsters;

import hero.Knight;
import objects.*;
import settings.Settings;

public class DarkWizard extends Wizard {

    private int fireBallDamage;

    public DarkWizard() {
        super("Dark wizard", Settings.DARK_WIZARD_MAX_LIFE, Settings.DARK_WIZARD_GOLD_EARNED, Settings.DARK_WIZARD_XP_EARNED, Settings.DARK_WIZARD_MANA_MAX, Settings.DARK_WIZARD_SPELL_MANA_COST, Settings.DARK_WIZARD_REGENERATION_MANA,
                new WizardStaff("dark wizard staff",25,100,300),
                new Hat("dark wizard hat",5,100,40),
                new Dress("dark wizard dress",5,100,60),
                new Gloves("dark wizard gloves",2,100,130),
                new HealPotion("dark wizard heal potion",100,100,50));
        this.fireBallDamage = Settings.DARK_WIZARD_FIREBALL_DAMAGE;
    }

    @Override
    public void attack(Knight player) {
        if ((this.getLife() <= 30) && this.getHealPotion() != null){
            System.out.println("The " + this.getName() + " use a potion");
            usePotion();
        } else if (this.getMana() <= this.getSpellManaCost()){
            System.out.println("The " + this.getName() + " regenerate his mana");
            regenerateMana();
        } else {
            int randomNumber = (int)(Math.random() * 2);
            if (randomNumber == 1){
                fireBall(player);
            } else {
                attackWithaWeapon(player);
            }
        }
    }

    public void fireBall(Knight player){
        if(this.getMana() >= this.getSpellManaCost()){
            this.setMana(this.getMana() - this.getSpellManaCost());
            player.receiveDamage("The " + this.getName() + " sent you a fire ball and", this.fireBallDamage);
        }
    }

}
