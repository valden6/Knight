package monsters;

import hero.Knight;
import settings.Settings;

public class Ghoul extends Monster{

    private int damage;
    private boolean secondLife;

    public Ghoul() {
        super("Ghoul", Settings.GHOUL_MAX_LIFE, Settings.GHOUL_GOLD_EARNED, Settings.GHOUL_XP_EARNED);
        this.damage = Settings.GHOUL_DAMAGE;
        this.secondLife = false;
    }

    @Override
    public void attack(Knight player) {
        player.receiveDamage("The " + this.getName() + " bite you and", this.damage);
    }

    @Override
    public void receiveDamage(int damage){
        this.setLife(this.getLife() - damage);

        System.out.println("The " + this.getName() + " takes " + damage + " damage");

        if (this.getLife() <= 0) {
            if (this.secondLife) {
                this.setAlive(false);
                System.out.println("The " + this.getName() + " is dead !");
            }else{
                this.secondLife = true;
                this.setLife(Settings.GHOUL_MAX_LIFE);
                System.out.println("The " + this.getName() + " revive !");
            }
        } else {
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

}
