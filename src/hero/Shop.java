package hero;

import objects.*;
import objects.Object;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    private ArrayList<Object> objectsSell;
    private ArrayList<Object> objectsBuy;

    public Shop(){
        this.objectsSell = new ArrayList<>();
        this.objectsBuy = new ArrayList<>();
    }

    private void sellObject(Knight player, String nameObjectToSell){
        Object objectToSell = player.getInventory().getObjectByName(nameObjectToSell);
        if (objectToSell != null) {
            this.objectsSell.add(objectToSell);
            player.getInventory().removeInInventory(objectToSell);
            System.out.println(objectToSell.getName() + " is sold");
            player.receiveGold(objectToSell.getValue());
        }else {
            System.out.println("Nothing was sold ! ");
        }
    }

    private void buyObject(Knight player, String nameObjectToBuy){
        Object objectToBuy = this.getObjectToBuyByName(nameObjectToBuy);
        if (player.getGold() >= objectToBuy.getValue()) {
            this.objectsBuy.remove(objectToBuy);
            player.getInventory().addInInventory(objectToBuy);
            System.out.println(objectToBuy.getName() + " is bought");
            player.removeGold(objectToBuy.getValue());
            System.out.println("\n");
        } else {
            System.out.println("You don't have enough gold ! ");
        }
    }

    public void showShop(Knight player) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[SHOP]");
        System.out.println("Action : ");
        System.out.println("--> B - Buy");
        System.out.println("--> S - Sell");
        System.out.println("--> R - Repair your equipment");
        System.out.println("--> Z - Show what you have sold");
        System.out.println("--> Q - Quit the shop");
        switch (sc.nextLine()){
            case "b":
                System.out.println("[BUY]");
                buyShop(player);
                showShop(player);
                break;
            case "s":
                System.out.println("[SELL]");
                System.out.println(player.getInventory().toString());
                System.out.println("Which object ?");
                sellObject(player,sc.nextLine());
                showShop(player);
                break;
            case "z":
                if(!this.objectsSell.isEmpty())
                    System.out.println("You have sold: " + this.objectsSell.toString());
                else
                    System.out.println("You didn't have sell something");
                showShop(player);
                break;
            case "r":
                System.out.println("[REPAIR]");
                repairEquipment(player);
                break;
            case "q":
                System.out.println("You quit the shop");
                break;
            default:
                System.out.println("Default continue...");
                break;
        }

    }

    private void repairEquipment(Knight player) {
        Scanner sc = new Scanner(System.in);
        int repairValue = 0;

        for (Object object : player.getInventory().getObjects()) {
            if(object.getDurability() < 100)
                repairValue += (object.getValue() / 2);
        }

        System.out.println("All the reparation will cost " + repairValue + " gold");
        System.out.println("--> Y - Yes");
        System.out.println("--> N - No");
        if(sc.nextLine().equals("y")){
            if(player.getGold() >= repairValue) {
                player.removeGold(repairValue);
                for (Object object : player.getInventory().getObjects()) {
                    if (object.getDurability() < 100)
                        object.setDurability(100);
                }
                System.out.println("All your equipment is repair");
            }else {
                System.out.println("You don't have enough gold !");
            }
        }else {
            showShop(player);
        }

    }

    private void buyShop(Knight player) {
        System.out.println(player.getGold() + " gold");
        showItemToBuy();
        Scanner sc = new Scanner(System.in);
        System.out.println("Action : ");
        System.out.println("--> A - Buy the first item");
        System.out.println("--> Z - Buy the second item");
        System.out.println("--> E - Buy the third item");
        System.out.println("--> N - Nothing");
        switch (sc.nextLine()){
            case "a":
                buyObject(player, this.objectsBuy.get(0).getName());
                buyShop(player);
                break;
            case "z":
                buyObject(player, this.objectsBuy.get(1).getName());
                buyShop(player);
                break;
            case "e":
                buyObject(player, this.objectsBuy.get(2).getName());
                buyShop(player);
                break;
            case "n":
                break;
            default:
                System.out.println("Default case");
                break;
        }
    }

    private void showItemToBuy() {
        if (!this.objectsBuy.isEmpty())
            this.objectsBuy.clear();
        randomItem();
        System.out.println("Shop : " + this.objectsBuy.toString());
    }

    private void randomItem(){
        int randomNumber1 = (int)(Math.random() * 5);
        Armor firstItem = null;

        switch (randomNumber1){
            case 0:
                firstItem = new Breastplate("Shop's Breastplate", 30,100,400);
                break;
            case 1:
                firstItem = new Gauntlet("Shop's Gauntlet", 20,100,350);
                break;
            case 2:
                firstItem = new Helmet("Shop's Helmet", 25,100,200);
                break;
            case 3:
                firstItem = new Leggings("Shop's Leggings", 50,100,600);
                break;
            case 4:
                firstItem = new Shield("Shop's Shield", 10,100,300);
                break;
            default:
                System.out.println("Default Case");
                break;
        }

        int randomNumber2 = (int)(Math.random() * 2);
        Weapon secondItem = null;

        switch (randomNumber2){
            case 0:
                secondItem = new Sword("Shop's Sword", 40,100,450);
                break;
            case 1:
                secondItem = new Sword("Shop's Sword 2", 50,100,800);
                break;
            default:
                System.out.println("Default Case");
                break;
        }

        int randomNumber3 = (int)(Math.random() * 2);
        Object thirdItem = null;

        switch (randomNumber3){
            case 0:
                thirdItem = new DamagePotion("Shop's damage potion", 100,50,20);
                break;
            case 1:
                thirdItem = new HealPotion("Shop's heal potion", 100,50,50);
                break;
            default:
                System.out.println("Default Case");
                break;
        }

        this.objectsBuy.add(firstItem);
        this.objectsBuy.add(secondItem);
        this.objectsBuy.add(thirdItem);
    }

    public Object getObjectToBuyByName(String name){
        Object res = null;
        for (Object object : objectsBuy)
        {
            if( object.getName().equals(name)){
                res = object;
            }
        }
        return res;
    }

}
