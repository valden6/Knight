package monsters;

import hero.Knight;
import objects.*;

public class Nazgul extends King {

    static final int MAX_LIFE = 100;
    static final int GOLD_EARNED = 1500;
    static final int XP_EARNED = 1000;

    private boolean invisibility;

    public Nazgul() {
        super("Nazgul", MAX_LIFE,GOLD_EARNED, XP_EARNED,
                new Sword("dark sword",50,100,500),
                new Helmet("dark hood",5,100,550),
                new Gauntlet("dark gauntlet",5,100,600),
                new Breastplate("dark dress",10,100,700),
                new Leggings("dark leggings",5,100,700),
                new Shield("dark shield",5,100,450));
        this.invisibility = false;
    }

    @Override
    public void attack(Knight player) {
        int randomNumber = (int)(Math.random() * 2);

        switch (randomNumber){
            case 0:
                attackWithaWeapon(player);
                break;
            case 1:
                invisibility(player);
                break;
            default:
                System.out.println("Default Case");
                break;
        }
    }

    private void invisibility(Knight player) {
        if (!this.invisibility) {
            this.invisibility = true;
            System.out.println("The " + this.getName() + " is now invisible");
        }
    }

    private void attackWithaWeapon(Knight player) {
        player.receiveDammage("The " + this.getName() + " attack with his sword and", this.getSword().getDammage());
    }

    @Override
    public void receiveDammage(int dammage) {
        if (!this.invisibility) {
            dammage = calculateArmor(dammage);
            reduceDurabilityEquipment(dammage);
            if (dammage > 0) {
                this.setLife(this.getLife() - dammage);
                System.out.println("The " + this.getName() + " takes " + dammage + " dammage");
            } else {
                System.out.println("You make 0 dammage due to the armor of the king");
            }

            if (this.getLife() <= 0) {
                this.setAlive(false);
                System.out.println("The " + this.getName() + " is dead !");

            } else {
                System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
            }
        } else {
            this.invisibility = false;
            System.out.println("You make 0 dammage due to invisibility");
        }
    }

}
