package monsters;

import hero.Knight;
import objects.*;
import objects.Object;
import settings.Settings;

public class Wizard extends Monster {

    private int mana;
    private int spellManaCost;
    private int regenerationMana;
    private WizardStaff wizardStaff;
    private Hat hat;
    private Dress dress;
    private Gloves gloves;
    private HealPotion healPotion;

    public Wizard() {
        super("Wizard", Settings.WIZARD_MAX_LIFE,Settings.WIZARD_GOLD_EARNED, Settings.WIZARD_XP_EARNED);
        this.mana = Settings.WIZARD_MANA_MAX;
        this.spellManaCost = Settings.WIZARD_SPELL_MANA_COST;
        this.regenerationMana = Settings.WIZARD_REGENERATION_MANA;
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
        if(this.getLife() > Settings.WIZARD_MAX_LIFE)
            this.setLife(Settings.WIZARD_MAX_LIFE);
        this.healPotion = null;
        System.out.println("The " + this.getName() + " use a potion and have now " + this.getLife() + " hp");
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

    public void attackWithaWeapon(Knight player){
        player.receiveDamage("The " + this.getName() + " attack with his wizard staff and", this.wizardStaff.getDamage());
    }

    protected void regenerateMana() {
        this.mana += this.regenerationMana;
        if (this.mana > Settings.WIZARD_MANA_MAX)
            this.mana = Settings.WIZARD_MANA_MAX;
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
