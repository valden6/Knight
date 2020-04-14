package monsters;

import hero.Knight;
import objects.*;
import settings.Settings;

import java.util.Set;

public class DevilWizard extends Wizard {

    private int lifeDrain;

    public DevilWizard() {
        super("Devil wizard", Settings.DEVIL_WIZARD_MAX_LIFE,Settings.DEVIL_WIZARD_GOLD_EARNED, Settings.DEVIL_WIZARD_XP_EARNED,Settings.DEVIL_WIZARD_MANA_MAX,Settings.DEVIL_WIZARD_SPELL_MANA_COST,Settings.DEVIL_WIZARD_REGENERATION_MANA,
                new WizardStaff("devil wizard staff",25,100,300),
                new Hat("devil wizard hat",5,100,40),
                new Dress("devil wizard dress",5,100,60),
                new Gloves("devil wizard gloves",2,100,130),
                new HealPotion("devil wizard heal potion",100,100,50));
        this.lifeDrain = Settings.DEVIL_WIZARD_LIFE_DRAIN;
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
                lifeDrain(player);
            } else {
                attackWithaWeapon(player);
            }
        }
    }

    private void lifeDrain(Knight player){
        if(this.getMana() >= this.getSpellManaCost()){
            this.setMana(this.getMana() - this.getSpellManaCost());
            player.receiveDamage("The " + this.getName() + " drain your life, take you " + this.lifeDrain + " hp", this.lifeDrain);
            this.setLife(this.getLife() + this.lifeDrain);
            if (this.getLife() > Settings.DEVIL_WIZARD_MAX_LIFE)
               this.setLife(Settings.DEVIL_WIZARD_MAX_LIFE);
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

    @Override
    public void receiveDamage(int damage) {
        damage = calculateArmor(damage);
        reduceDurabilityEquipment(damage);

        if (damage > 0 ){
            this.setLife(this.getLife() - damage);
            System.out.println("The " + this.getName() + " takes " + damage + " damage");
        }else {
            System.out.println("You make 0 damage due to the armor of the wizard");
        }

        if (this.getLife() <= 0) {
            this.setAlive(false);
            System.out.println("The " + this.getName() + " is dead !");
        } else {
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

}
