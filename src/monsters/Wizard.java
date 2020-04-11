package monsters;

import hero.Knight;
import objects.*;
import objects.Object;

public class Wizard extends Monster {

    static final int MAX_LIFE = 100;
    static final int GOLD_EARNED = 150;
    static final int XP_EARNED = 500;
    static final int MANA_MAX = 100;
    static final int SPELL_MANA_COST = 50;
    static final int REGENERATION_MANA = 50;

    private int mana;
    private int spellManaCost;
    private int regenerationMana;
    private WizardStaff wizardStaff;
    private Hat hat;
    private Dress dress;
    private Gloves gloves;
    private HealPotion healPotion;

    public Wizard() {
        super("Wizard", MAX_LIFE,GOLD_EARNED, XP_EARNED);
        this.mana = MANA_MAX;
        this.spellManaCost = SPELL_MANA_COST;
        this.regenerationMana = REGENERATION_MANA;
        this.hat = new Hat("wizard hat",5,100,40);
        this.dress = new Dress("wizard dress",15,100,60);
        this.gloves = new Gloves("wizard gloves",5,100,130);
        this.wizardStaff = new WizardStaff("wizard staff",25,100,300);
        this.healPotion = new HealPotion("wizard heal potion",100,100,50);
    }

    public Wizard(String name, int life, int goldEarned, int xpEarned, int mana, int spellManaCost, int regenerationMana, WizardStaff wizardStaff, Hat hat, Dress dress, Gloves gloves, HealPotion healPotion) {
        super(name, life, goldEarned, xpEarned);
        this.mana = mana;
        this.spellManaCost = spellManaCost;
        this.regenerationMana = regenerationMana;
        this.wizardStaff = wizardStaff;
        this.hat = hat;
        this.dress = dress;
        this.gloves = gloves;
        this.healPotion = healPotion;
    }

    @Override
    public void attack(Knight player) {
        if ((this.getLife() <= 30) && this.healPotion != null){
            System.out.println("The " + this.getName() + " use a potion");
            usePotion();
        }else if (this.mana <= this.spellManaCost){
            System.out.println("The " + this.getName() + " regenerate his mana");
            regenerateMana();
        }else {
            attackWithaWeapon(player);
        }
    }

    protected void usePotion() {
        this.setLife(this.getLife() + this.healPotion.getLifeGiven());
        if(this.getLife() > MAX_LIFE)
            this.setLife(MAX_LIFE);
        this.healPotion = null;
        System.out.println("The " + this.getName() + " have now " + this.getLife() + " hp");
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

    public void attackWithaWeapon(Knight player){
        player.receiveDammage("The " + this.getName() + " attack with his wizard staff and", this.wizardStaff.getDammage());
    }

    protected void regenerateMana() {
        this.mana += this.regenerationMana;
        if (this.mana > MANA_MAX)
            this.mana = MANA_MAX;
    }

    public Object dropObject(){
        return randomObject();
    }

    public Object randomObject(){
        Object object;
        int randomNumber = (int)(Math.random() * 5);

        switch (randomNumber){
            case 0:
                object = this.getDress();
                break;
            case 1:
                object = this.getGloves();
                break;
            case 2:
                object = this.getHat();
                break;
            case 3:
                object = this.getHealPotion();
                break;
            case 4:
                object = this.getWizardStaff();
                break;
            default:
                System.out.println("Default case");
                object = null;
                break;
        }
        return object;
    }

    public WizardStaff getWizardStaff() {
        return wizardStaff;
    }

    public Hat getHat() {
        return hat;
    }

    public Dress getDress() {
        return dress;
    }

    public Gloves getGloves() {
        return gloves;
    }

    public HealPotion getHealPotion() {
        return healPotion;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getSpellManaCost() {
        return spellManaCost;
    }

    public int calculateArmor(int dammage){
        int armor = this.hat.getProtection() + this.gloves.getProtection() + this.dress.getProtection();
        dammage -= armor;
        return dammage;
    }

    public void reduceDurabilityEquipment(int reduction){
        this.hat.setDurability(this.hat.getDurability() - reduction);
        this.gloves.setDurability(this.gloves.getDurability() - reduction);
        this.dress.setDurability(this.dress.getDurability() - reduction);
    }

}
