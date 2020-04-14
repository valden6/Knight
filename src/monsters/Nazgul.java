package monsters;

import hero.Knight;
import objects.*;
import settings.Settings;

public class Nazgul extends King {

    private boolean invisibility;

    public Nazgul() {
        super("Nazgul", Settings.NAZGUL_MAX_LIFE,Settings.NAZGUL_GOLD_EARNED, Settings.NAZGUL_XP_EARNED,
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
        player.receiveDamage("The " + this.getName() + " attack with his sword and", this.getSword().getDamage());
    }

    @Override
    public void receiveDamage(int damage) {
        if (!this.invisibility) {
            damage = calculateArmor(damage);
            reduceDurabilityEquipment(damage);
            if (damage > 0) {
                this.setLife(this.getLife() - damage);
                System.out.println("The " + this.getName() + " takes " + damage + " damage");
            } else {
                System.out.println("You make 0 damage due to the armor of the king");
            }

            if (this.getLife() <= 0) {
                this.setAlive(false);
                System.out.println("The " + this.getName() + " is dead !");

            } else {
                System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
            }
        } else {
            this.invisibility = false;
            System.out.println("You make 0 damage due to invisibility");
        }
    }

}
