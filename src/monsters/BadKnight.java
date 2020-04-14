package monsters;

import hero.Knight;
import objects.*;
import settings.Settings;

public class BadKnight extends Monster{

    private Sword sword;
    private Helmet helmet;
    private Breastplate breastplate;
    private Gauntlet gauntlet;
    private Leggings leggings;

    public BadKnight() {
        super("Bad Knight", Settings.BAD_KNIGHT_MAX_LIFE,Settings.BAD_KNIGHT_GOLD_EARNED, Settings.BAD_KNIGHT_XP_EARNED);
        this.sword = new Sword("Sharpened sword",5,100,40);
        this.helmet = new Helmet("knight's helmet",5,100,60);
        this.gauntlet = new Gauntlet("knight's gauntlet",2,100,130);
        this.breastplate = new Breastplate("knight's breastplate",10,100,300);
        this.leggings = new Leggings("knight's leggings",5,100,200);
    }

    public BadKnight(String name, int life, int goldEarned, int xpEarned, Sword sword, Helmet helmet, Gauntlet gauntlet, Breastplate breastplate, Leggings leggings) {
        super(name, life,goldEarned, xpEarned);
        this.sword = sword;
        this.helmet = helmet;
        this.gauntlet = gauntlet;
        this.breastplate = breastplate;
        this.leggings = leggings;
    }

    @Override
    public void attack(Knight player) {
        player.receiveDamage("The " + this.getName() + " attack with his sword and", this.sword.getDamage());
    }

    @Override
    public void receiveDamage(int damage) {
        damage = calculateArmor(damage);
        reduceDurabilityEquipment(damage);

        if (damage > 0 ){
            this.setLife(this.getLife() - damage);
            System.out.println("The " + this.getName() + " takes " + damage + " damage");
        }else {
            System.out.println("You make 0 damage due to the armor of the Bad Knight");
        }

        if (this.getLife() <= 0) {
            this.setAlive(false);
            System.out.println("The " + this.getName() + " is dead !");
        } else {
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

    public Sword getSword() {
        return sword;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public Breastplate getBreastplate() {
        return breastplate;
    }

    public Gauntlet getGauntlet() {
        return gauntlet;
    }

    public Leggings getLeggings() {
        return leggings;
    }

    public int calculateArmor(int damage){
        int armor = this.helmet.getProtection() + this.gauntlet.getProtection() + this.breastplate.getProtection() + this.leggings.getProtection();
        damage -= armor;
        return damage;
    }

    public void reduceDurabilityEquipment(int reduction){
        this.helmet.setDurability(this.helmet.getDurability() - reduction);
        this.gauntlet.setDurability(this.gauntlet.getDurability() - reduction);
        this.breastplate.setDurability(this.breastplate.getDurability() - reduction);
        this.leggings.setDurability(this.leggings.getDurability() - reduction);
    }
}

