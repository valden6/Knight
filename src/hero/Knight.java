package hero;

import monsters.Monster;
import objects.*;
import objects.Object;

import java.util.ArrayList;
import java.util.Scanner;

public class Knight {

    static final int MAXLIFE = 100;
    static final int DAMMAGENOWEAPON = 5;

    private int life;
    private int level;
    private int xp;
    private int levelUp;
    private int gold;
    private int mana;
    private Inventory inventory;
    private Weapon leftHand;
    private Armor rightHand;
    private Armor head;
    private Armor arms;
    private Armor legs;
    private Armor breasts;

    public Knight() {
        this.life = MAXLIFE;
        this.level = 1;
        this.xp = 0;
        this.gold = 5;
        this.levelUp = 10;
        this.mana = 100;

        Sword sword = new Sword("Epée normale",50,100,50);
        HealPotion healPotion = new HealPotion("popo",100,30, 50);

        this.leftHand = sword;
        this.rightHand = null;
        this.head = null;
        this.arms = null;
        this.legs = null;
        this.breasts = null;

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(sword);
        objects.add(healPotion);
        this.inventory = new Inventory(objects);
    }

    public void attack(Monster target){
        System.out.println("You choose to attack");
        if (this.leftHand != null) {
            if (this.leftHand.getDurability() > 0) {
                target.receiveDammage(this.leftHand.getDammage());
                this.leftHand.setDurability(this.leftHand.getDurability() - 5);
            } else {
                System.out.println("The " + this.leftHand.getName() + " is broken");
            }
        } else {
            System.out.println("You don't have weapon in your hand...");
            target.receiveDammage(DAMMAGENOWEAPON);
        }
    }

    public void changeWeapon(String futureWeapon){
        Weapon weapon = (Weapon) this.inventory.getObjectByName(futureWeapon);
        this.setLeftHand(weapon);
        System.out.println("Your new weapon is " + this.leftHand.getName());
    }

    public void heal(String potionName){
        HealPotion potion = (HealPotion) this.inventory.getObjectByName(potionName);
        this.life += potion.getLifeGiven();
        this.inventory.removeInInventory(potion);
        if(this.life > MAXLIFE)
            this.life = MAXLIFE;
        System.out.println("Your life is at " + this.life);
    }

    public void changeArmor(String futureArmor){
        Armor armor = (Armor) this.inventory.getObjectByName(futureArmor);
        switch (armor.getClass().getName()){
            case "objects.Shield":
                this.rightHand = armor;
                break;
            case "objects.Leggings":
                this.legs = armor;
                break;
            case "objects.Helmet":
                this.head = armor;
                break;
            case "objects.Gauntlet":
                this.arms = armor;
                break;
            case "objects.Breastplate":
                this.breasts = armor;
                break;
            default:
                    System.out.println("L'armure n'as pas changé");
                break;
        }
        System.out.println(armor.getName() + " is added to your armor" );
    }

    public void receiveGold(int number) {
        this.gold += number;
        System.out.println("You receive " + number + " gold and have now " + this.gold + " gold");
    }

    public void removeGold(int number) {
        this.gold -= number;
        System.out.println("You loose " + number + " gold and have now " + this.gold + " gold");
    }

    public void receiveItem(Object object) {
        this.inventory.addInInventory(object);
        System.out.println("You receive a new object in your inventory: " + object.toString());
    }

    public void receiveXp(int number) {
        this.xp += number;
        System.out.println("You receive " + number + " xp and have now " + this.xp + " xp");
        if(this.xp >= this.levelUp){
            this.level += 1;
            this.xp = 0;
            setUpLevelUp();
            System.out.println("You are level " + level + " !");
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getGold() {
        return gold;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLevel() {
        return level;
    }

    private void setUpLevelUp(){
        switch (this.level){
            case 1:
                this.levelUp = 10;
                break;
            case 2:
                this.levelUp = 50;
                break;
            case 3:
                this.levelUp = 100;
                break;
            case 4:
                this.levelUp = 500;
                break;
            case 5:
                this.levelUp = 1000;
                break;
            default:
                System.out.println("Default case");
                break;
        }
    }

    public void showInventory() {
        System.out.println(this.inventory.toString());
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do ?");
        System.out.println("--> W - Change weapon");
        System.out.println("--> A - Change/add armor");
        System.out.println("--> H - Take heal potion");
        System.out.println("--> S - Show your equipment wear");
        System.out.println("--> Q - Quit the inventory");
        System.out.println("Choose your action : ");
        switch (sc.nextLine()){
            case "w":
                System.out.println("You choose to change your weapon...");
                sc = new Scanner(System.in);
                System.out.println("Which weapon ?");
                changeWeapon(sc.nextLine());
                showInventory();
                break;
            case "a":
                System.out.println("You choose to change/add armor...");
                sc = new Scanner(System.in);
                System.out.println("Which armor ?");
                changeArmor(sc.nextLine());
                showInventory();
                break;
            case "h":
                System.out.println("You choose to take a heal potion...");
                sc = new Scanner(System.in);
                System.out.println("Which potion ?");
                heal(sc.nextLine());
                showInventory();
                break;
            case "q":
                System.out.println("You choose to quit the inventory...");
                break;
            case "s":
                System.out.println("You choose to show your equipment wear...");
                System.out.println("Left Hand : " + this.leftHand.toString());
                if(this.rightHand != null)
                    System.out.println("Right Hand : " + this.rightHand.toString());
                else
                    System.out.println("Right Hand : ...");
                if(this.head != null)
                    System.out.println("Head : " + this.head.toString());
                else
                    System.out.println("Head : ...");
                if(this.arms != null)
                    System.out.println("Arms : " + this.arms.toString());
                else
                    System.out.println("Arms : ...");
                if(this.breasts != null)
                    System.out.println("Breast : " + this.breasts.toString());
                else
                    System.out.println("Breast : ...");
                if(this.legs != null)
                    System.out.println("Legs : " + this.legs.toString());
                else
                    System.out.println("Legs : ...");

                System.out.println();
                showInventory();
                break;
            default:
                System.out.println("Default continue...");
                break;
        }

    }

    public void setLeftHand(Weapon leftHand) {
        this.leftHand = leftHand;
    }
}
