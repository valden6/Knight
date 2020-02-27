package monsters;

import hero.Knight;
import objects.*;
import objects.Object;

public class Wizard extends Monster {

    static final int HEALINGMANACOST = 50;
    static final int HEALING = 30;
    static final int REGENERATEMANA = 50;
    static final int MANAMAX = 100;
    static final int LIFEMAX = 100;

    private WizardStaff wizardStaff;
    private Hat hat;
    private Dress dress;
    private Gloves gloves;
    private HealPotion healPotion;
    private int mana;

    public Wizard() {
        super("Wizard", LIFEMAX,150, 500);
        this.hat = new Hat("wizard hat",5,100,40);
        this.dress = new Dress("wizard dress",5,100,60);
        this.gloves = new Gloves("wizard gloves",2,100,130);
        this.wizardStaff = new WizardStaff("wizard staff",25,100,300);
        this.healPotion = new HealPotion("wizard heal potion",100,100,50);
        this.mana = MANAMAX;
    }

    @Override
    public void attack(Knight player) {
        int randomNumber = (int)(Math.random() * 3);

        switch (randomNumber){
            case 0:
                attackWithaWeapon(player);
                break;
            case 1:
                System.out.println("The " + this.getName() + " choose to heal him");
                heal();
                break;
            case 2:
                System.out.println("The " + this.getName() + " regenerate his mana");
                regenerateMana();
                break;
            default:
                System.out.println("Default Case");
                break;
        }
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

    public void attackWithaWeapon(Knight player){
        player.setLife(player.getLife() - this.wizardStaff.getDammage());
        System.out.println("The " + this.getName() + " attack and give you " + this.wizardStaff.getDammage() + " dammage");
        System.out.println("You have " + player.getLife() + " hp");
    }

    public void heal(){
        if(this.mana >= HEALINGMANACOST){
            this.mana -= HEALINGMANACOST;
            this.setLife(this.getLife() + HEALING);
        }
        System.out.println("The " + this.getName() + " have " + this.getLife() + " hp");
    }

    private void regenerateMana() {
        this.mana += REGENERATEMANA;
        if (this.mana > MANAMAX)
            this.mana = MANAMAX;
    }

    public Object dropObject(){
        return randomObject();
    }

    public Object randomObject(){
        Object object;
        int randomNumber = (int)(Math.random() * 5);

        switch (randomNumber){
            case 0:
                object = this.getDress();
                break;
            case 1:
                object = this.getGloves();
                break;
            case 2:
                object = this.getHat();
                break;
            case 3:
                object = this.getHealPotion();
                break;
            case 4:
                object = this.getWizardStaff();
                break;
            default:
                System.out.println("Default case");
                object = null;
                break;
        }
        return object;
    }

    public WizardStaff getWizardStaff() {
        return wizardStaff;
    }

    public Hat getHat() {
        return hat;
    }

    public Dress getDress() {
        return dress;
    }

    public Gloves getGloves() {
        return gloves;
    }

    public HealPotion getHealPotion() {
        return healPotion;
    }

    public int calculateArmor(int dammage){
        int armor = this.hat.getProtection() + this.gloves.getProtection() + this.dress.getProtection();
        dammage -= armor;
        return dammage;
    }

    public void reduceDurabilityEquipment(int reduction){
        this.hat.setDurability(this.hat.getDurability() - reduction);
        this.gloves.setDurability(this.gloves.getDurability() - reduction);
        this.dress.setDurability(this.dress.getDurability() - reduction);
    }

}
