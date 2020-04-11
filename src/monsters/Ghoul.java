package monsters;

import hero.Knight;

public class Ghoul extends Monster{

    static final int MAX_LIFE = 50;
    static final int GOLD_EARNED = 100;
    static final int XP_EARNED = 150;
    static final int DAMMAGE = 10;

    private int dammage;
    private boolean secondLife;

    public Ghoul() {
        super("Ghoul", MAX_LIFE, GOLD_EARNED, XP_EARNED);
        this.dammage = DAMMAGE;
        this.secondLife = false;
    }

    @Override
    public void attack(Knight player) {
        player.receiveDammage("The " + this.getName() + " bite you and", this.dammage);
    }

    @Override
    public void receiveDammage(int dammage){
        this.setLife(this.getLife() - dammage);

        System.out.println("The " + this.getName() + " takes " + dammage + " dammage");

        if (this.getLife() <= 0) {
            if (this.secondLife) {
                this.setAlive(false);
                System.out.println("The " + this.getName() + " is dead !");
            }else{
                this.secondLife = true;
                this.setLife(MAX_LIFE);
                System.out.println("The " + this.getName() + " revive !");
            }
        } else {
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }

}
