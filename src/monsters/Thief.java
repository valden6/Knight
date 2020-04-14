package monsters;

import hero.Knight;
import objects.Sword;
import settings.Settings;

public class Thief extends Monster {

    private int goldStolen;

    private Sword sword;

    public Thief() {
        super("Thief", Settings.THIEF_MAX_LIFE,Settings.THIEF_GOLD_EARNED,Settings.THIEF_XP_EARNED);
        this.sword = new Sword("thief's sword",10,100,40);
        this.goldStolen = Settings.THIEF_GOLD_STOLEN;
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
        player.receiveDamage("The " + this.getName() + " attack with his sword and", this.sword.getDamage());
    }

    @Override
    public String toString() {
        return "Thief{" +
                "sword=" + sword +
                '}';
    }
}
