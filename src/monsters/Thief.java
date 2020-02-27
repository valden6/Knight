package monsters;

import hero.Knight;
import objects.Sword;

public class Thief extends Monster {

    private Sword sword;

    public Thief() {
        super("Thief", 20,10,20);
        this.sword = new Sword("Epée rouillée",5,100,30);
    }

    @Override
    public void attack(Knight player) {
        player.setLife(player.getLife() - this.sword.getDammage());
        System.out.println("The " + this.getName() + " attack and give you " + this.sword.getDammage() + " dammage");
        System.out.println("You have " + player.getLife() + " hp");
    }

    @Override
    public String toString() {
        return "Thief{" +
                "sword=" + sword +
                '}';
    }
}
