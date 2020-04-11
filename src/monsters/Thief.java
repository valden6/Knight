package monsters;

import hero.Knight;
import objects.Sword;

public class Thief extends Monster {

    static final int MAX_LIFE = 100;
    static final int GOLD_EARNED = 10;
    static final int XP_EARNED = 20;
    static final int GOLD_STOLEN = 100;

    private int goldStolen;

    private Sword sword;

    public Thief() {
        super("Thief", MAX_LIFE,GOLD_EARNED,XP_EARNED);
        this.sword = new Sword("thief's sword",10,100,40);
        this.goldStolen = GOLD_STOLEN;
    }

    @Override
    public void attack(Knight player) {
        int randomNumber = (int)(Math.random() * 2);

        switch (randomNumber){
            case 0:
                attackWithaWeapon(player);
                break;
            case 1:
                stealGold(player);
                break;
            default:
                System.out.println("Default Case");
                break;
        }
    }

    private void stealGold(Knight player) {
        System.out.println("The " + this.getName() + " steal you");
        player.removeGold(this.goldStolen);
    }

    private void attackWithaWeapon(Knight player) {
        player.receiveDammage("The " + this.getName() + " attack with his sword and", this.sword.getDammage());
    }

    @Override
    public String toString() {
        return "Thief{" +
                "sword=" + sword +
                '}';
    }
}
