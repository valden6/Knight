package monsters;

import hero.Knight;
import objects.*;

public class DarkWizard extends Wizard {

    static final int MAX_LIFE = 150;
    static final int GOLD_EARNED = 300;
    static final int XP_EARNED = 500;
    static final int MANA_MAX = 150;
    static final int SPELL_MANA_COST = 50;
    static final int REGENERATION_MANA = 50;
    static final int FIREBALL_DAMMAGE = 60;

    private int fireBallDammage;

    public DarkWizard() {
        super("Dark wizard", MAX_LIFE,GOLD_EARNED, XP_EARNED,MANA_MAX,SPELL_MANA_COST,REGENERATION_MANA,
                new WizardStaff("dark wizard staff",25,100,300),
                new Hat("dark wizard hat",5,100,40),
                new Dress("dark wizard dress",5,100,60),
                new Gloves("dark wizard gloves",2,100,130),
                new HealPotion("dark wizard heal potion",100,100,50));
        this.fireBallDammage = FIREBALL_DAMMAGE;
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
            player.receiveDammage("The " + this.getName() + " sent you a fire ball and", this.fireBallDammage);
        }
    }

}
