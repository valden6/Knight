package main;

import hero.Knight;
import hero.Shop;
import monsters.*;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        int iteration = -1;
        int monsterKilled = 0;

        System.out.println("\nWelcome in the game, you are knight who want to destroy all bad vilains !\n");
        Knight player = new Knight();
        Shop shop = new Shop();
        Monster monster = randomMonster();

        fightKnight(player,monster,shop,iteration, monsterKilled);

        // TODO monnstre plus difficile en fonction de l'exp ( multiplicateur)
        // TODO reduire la durabilite des armures quand il y a un coup
        // TODO pouvoir pour le hero --> EN COURS
        // TODO nouveau mage herite
        // TODO plus d'objet tel que : potion resistance ; arme differente ;
        // TODO IA des monstres : plus trop de vie, reprend de la vie etc...
        // TODO Vraie utilité des objets magique tel que de la resistance ou du gain de mana
        // TODO Ajouter les anneaux dans les equipements

    }

    public static Monster randomMonster(){
        Monster monster;
        int randomNumber = (int)(Math.random() * 4);

        switch (randomNumber){
            case 0:
                monster = new Thief();
                break;
            case 1:
                monster = new BadKnight();
                break;
            case 2:
                monster = new King();
                break;
            case 3:
                monster = new Wizard();
                break;
            default:
                System.out.println("Default Case");
                monster = null;
                break;
        }
        return monster;
    }

    public static void fightKnight(Knight player, Monster monster, Shop shop, int iteration, int monsterKilled){
        if (player.getLife() <= 0) {
            gameOver(player, monsterKilled);
        } else {
            Scanner sc = new Scanner(System.in);
            iteration++;
            if (iteration == 0) {
                System.out.println("A " + monster.getName() + " appear !");
                System.out.println("A fight begin !");
            }
            System.out.println("--> A - Attack");
            System.out.println("--> I - Check your inventory");
            System.out.println("--> N - Do nothing");
            System.out.println("Choose your action : ");
            switch (sc.nextLine()) {
                case "a":
                    player.attack(monster);
                    break;
                case "i":
                    player.showInventory();
                    break;
                case "n":
                    System.out.println("You choose to do nothing...");
                    break;
                default:
                    System.out.println("Default break...");
                    break;
            }
            if (!monster.isAlive()) {
                iteration = -1;
                monsterKilled += 1;
                player.receiveGold(monster.getGoldEarned());
                player.receiveXp(monster.getXpEarned());
                if (monster.getName().equals("King") || monster.getName().equals("Wizard")) {
                    if (monster.getName().equals("King"))
                        player.receiveItem(new King().dropObject());
                    else
                        player.receiveItem(new Wizard().dropObject());
                }
                actionKnight(player, shop, iteration, monsterKilled);
            } else {
                monster.attack(player);
                fightKnight(player, monster, shop, iteration, monsterKilled);
            }
        }
    }

    public static void actionKnight(Knight player, Shop shop, int iteration, int monsterKilled){
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do ?");
        System.out.println("--> C - Continue to the next battle");
        System.out.println("--> I - Check your inventory");
        System.out.println("--> S - Go to the shop");
        System.out.println("Choose your action : ");
        switch (sc.nextLine()){
            case "c":
                System.out.println("You choose to continue...");
                fightKnight(player,randomMonster(),shop,iteration,monsterKilled);
                break;
            case "i":
                System.out.println("You choose to show your inventory...");
                player.showInventory();
                actionKnight(player,shop,iteration, monsterKilled);
                break;
            case "s":
                System.out.println("You choose to go to the shop...");
                shop.showShop(player);
                actionKnight(player,shop,iteration, monsterKilled);
                break;
            default:
                System.out.println("Default continue...");
                fightKnight(player,randomMonster(),shop,iteration, monsterKilled);
                break;
        }
    }

    public static void gameOver(Knight player, int monsterKilled){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n! GAME OVER !");
        System.out.println("You have killed " + monsterKilled + " monsters !");
        System.out.println("You were level " + player.getLevel() + " !");
        System.out.println("You had " + player.getGold() + " gold !\n");
        System.out.println("--> R - Replay");
        System.out.println("--> Q - Quit the game");
        System.out.println("Choose your action : ");

        switch (sc.nextLine()){
            case "r":
                System.out.println("You choose to replay a game...\n");
                main(null);
                break;
            case "q":
                System.out.println("Bye! See you soon...\n");
                break;
            default:
                System.out.println("Default continue...");
                break;
        }
    }

}
