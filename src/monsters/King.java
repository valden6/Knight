package monsters;

import hero.Knight;
import objects.*;
import objects.Object;

public class King extends BadKnight {

    private Shield shield;

    public King() {
        super("King", 1,1000, 500,
                new Sword("golden sword",50,100,500),
                new Helmet("king's helmet",5,100,550),
                new Gauntlet("king's gauntlet",2,100,600),
                new Breastplate("king's breastplate",10,100,700),
                new Leggings("king's leggings",5,100,700));

        this.shield = new Shield("king's shield",5,100,450);
    }

    @Override
    public void attack(Knight player) {
        player.setLife(player.getLife() - this.getSword().getDammage());
        System.out.println("The " + this.getName() + " attack and give you " + this.getSword().getDammage() + " dammage");
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
            System.out.println("You make 0 dammage due to the armor of the king");
        }

        if (this.getLife() <= 0) {
            this.setAlive(false);
            System.out.println("The " + this.getName() + " is dead !");

        } else {
            System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
        }
    }


    @Override
    public int calculateArmor(int dammage){
        int armor = this.getHelmet().getProtection() + this.getGauntlet().getProtection() + this.getBreastplate().getProtection() + this.getLeggings().getProtection() + this.shield.getProtection();
        dammage -= armor;
        return dammage;
    }

    @Override
    public void reduceDurabilityEquipment(int reduction){
        this.getHelmet().setDurability(this.getHelmet().getDurability() - reduction);
        this.getGauntlet().setDurability(this.getGauntlet().getDurability() - reduction);
        this.getBreastplate().setDurability(this.getBreastplate().getDurability() - reduction);
        this.getLeggings().setDurability(this.getLeggings().getDurability() - reduction);
        this.shield.setDurability(this.shield.getDurability() - reduction);
    }

    public Object dropObject(){
        return randomObject();
    }

    public Object randomObject(){
        Object object;
        int randomNumber = (int)(Math.random() * 6);

        switch (randomNumber){
            case 0:
                object = this.getSword();
                break;
            case 1:
                object = this.getBreastplate();
                break;
            case 2:
                object = this.getGauntlet();
                break;
            case 3:
                object = this.getLeggings();
                break;
            case 4:
                object = this.getHelmet();
                break;
            case 5:
                object = this.shield;
                break;
            default:
                System.out.println("Default case");
                object = null;
                break;
        }
        return object;
    }
}
