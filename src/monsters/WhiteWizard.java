package monsters;

import hero.Knight;
import objects.*;

public class WhiteWizard extends Wizard {

    static final int MAX_LIFE = 150;
    static final int GOLD_EARNED = 300;
    static final int XP_EARNED = 500;
    static final int MANA_MAX = 150;
    static final int SPELL_MANA_COST = 50;
    static final int REGENERATION_MANA = 50;
    static final int HEALING = 30;

    private int healing;

    public WhiteWizard() {
        super("White wizard", MAX_LIFE,GOLD_EARNED, XP_EARNED,MANA_MAX,SPELL_MANA_COST,REGENERATION_MANA,
                new WizardStaff("white wizard staff",25,100,300),
                new Hat("white wizard hat",5,100,40),
                new Dress("white wizard dress",5,100,60),
                new Gloves("white wizard gloves",2,100,130),
                new HealPotion("white wizard heal potion",100,100,50));
        this.healing = HEALING;
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
