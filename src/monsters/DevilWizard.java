package monsters;

import hero.Knight;
import objects.*;

public class DevilWizard extends Wizard {

    static final int MAX_LIFE = 100;
    static final int GOLD_EARNED = 500;
    static final int XP_EARNED = 700;
    static final int MANA_MAX = 100;
    static final int SPELL_MANA_COST = 50;
    static final int REGENERATION_MANA = 50;
    static final int LIFE_DRAIN = 20;

    private int lifeDrain;

    public DevilWizard() {
        super("Devil wizard", MAX_LIFE,GOLD_EARNED, XP_EARNED,MANA_MAX,SPELL_MANA_COST,REGENERATION_MANA,
                new WizardStaff("devil wizard staff",25,100,300),
                new Hat("devil wizard hat",5,100,40),
                new Dress("devil wizard dress",5,100,60),
                new Gloves("devil wizard gloves",2,100,130),
                new HealPotion("devil wizard heal potion",100,100,50));
        this.lifeDrain = LIFE_DRAIN;
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
            player.receiveDammage("The " + this.getName() + " drain your life, take you " + this.lifeDrain + " hp", this.lifeDrain);
            this.setLife(this.getLife() + this.lifeDrain);
            if (this.getLife() < MAX_LIFE)
               this.setLife(MAX_LIFE);
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

    @Override
    public void receiveDammage(int dammage) {
        dammage = calculateArmor(dammage);
        reduceDurabilityEquipment(dammage);

        if (dammage > 0 ){
            this.setLife(this.getLife() - dammage);
            System.out.println("The " + this.getName() + " takes " + dammage + " dammage");
        }else {
            System.out.println("You make 0 dammage due to the armor of the wizard");
        }

        if (this.getLife() <= 0) {
            this.setAlive(false);
            System.out.println("The " + this.getName() + " is dead !");
        } else {
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

}
