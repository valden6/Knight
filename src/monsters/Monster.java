package monsters;

import hero.Knight;

public class Monster {

    private String name;
    private int life;
    private boolean alive;
    private int goldEarned;
    private int xpEarned;

    public Monster(String name, int life, int goldEarned, int xpEarned) {
        this.name = name;
        this.life = life;
        this.alive = true;
        this.goldEarned = goldEarned;
        this.xpEarned = xpEarned;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public int getXpEarned() {
        return xpEarned;
    }

    public void attack(Knight player){
        System.out.println("The " + this.name + " doesn't attack ");
    }

    public void receiveDammage(int dammage){
        this.life -= dammage;

        if (this.life <= 0) {
            this.setAlive(false);
            System.out.println("The " + this.name + " is dead !");

        } else {
            System.out.println("The " + this.name + " have " + this.life + " hp");
        }
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", life=" + life +
                ", alive=" + alive +
                '}';
    }
}
