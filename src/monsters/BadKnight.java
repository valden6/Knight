package monsters;

import hero.Knight;
import objects.*;

public class BadKnight extends Monster{

    private Sword sword;
    private Helmet helmet;
    private Breastplate breastplate;
    private Gauntlet gauntlet;
    private Leggings leggings;

    public BadKnight() {
        super("Bad Knight", 60,30, 40);
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
        player.setLife(player.getLife() - this.sword.getDammage());
        System.out.println("The " + this.getName() + " attack and give you " + this.sword.getDammage() + " dammage");
        System.out.println("You have " + player.getLife() + " hp");
    }

    @Override
    public void receiveDammage(int dammage) {
        dammage = calculateArmor(dammage);
        reduceDurabilityEquipment(dammage);

        if (dammage > 0 ){
            this.setLife(this.getLife() - dammage);
            System.out.println("The " + this.getName() + " takes " + dammage + " dammage");
        }else {
            System.out.println("You make 0 dammage due to the armor of the Bad Knight");
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

    public int calculateArmor(int dammage){
        int armor = this.helmet.getProtection() + this.gauntlet.getProtection() + this.breastplate.getProtection() + this.leggings.getProtection();
        dammage -= armor;
        return dammage;
    }

    public void reduceDurabilityEquipment(int reduction){
        this.helmet.setDurability(this.helmet.getDurability() - reduction);
        this.gauntlet.setDurability(this.gauntlet.getDurability() - reduction);
        this.breastplate.setDurability(this.breastplate.getDurability() - reduction);
        this.leggings.setDurability(this.leggings.getDurability() - reduction);
    }
}

