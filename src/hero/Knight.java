package hero;

import hero.spells.DamageSpell;
import hero.spells.HealingSpell;
import hero.spells.Spell;
import hero.spells.SpellsList;
import monsters.Monster;
import objects.*;
import objects.Object;
import settings.Settings;

import java.util.ArrayList;
import java.util.Scanner;

public class Knight {

    private int life;
    private int level;
    private int xp;
    private int levelUp;
    private int gold;
    private int mana;
    private Capacities capacities;
    private SpellsList spellsList;
    private Inventory inventory;
    private Weapon leftHand;
    private Armor rightHand;
    private Armor head;
    private Armor arms;
    private Armor legs;
    private Armor breasts;

    public Knight() {
        this.life = Settings.KNIGHT_MAX_LIFE;
        this.level = 1;
        this.xp = 0;
        this.gold = 5;
        this.levelUp = 10;
        this.mana = Settings.KNIGHT_MAX_MANA;

        Sword sword = new Sword("Epée normale",50,100,50);
        HealPotion healPotion = new HealPotion("heal",100,30, 100);
        ManaPotion manaPotion = new ManaPotion("mana",100,30, 100);
        Shield shield = new Shield("shield",10,100,500);

        this.leftHand = sword;
        this.rightHand = shield;
        this.head = null;
        this.arms = null;
        this.legs = null;
        this.breasts = null;

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(sword);
        objects.add(healPotion);
        objects.add(manaPotion);
        this.inventory = new Inventory(objects);

        ArrayList<Spell> spells = new ArrayList<>();
        this.capacities = new Capacities(spells);

        this.spellsList = new SpellsList();
    }

    public void attack(Monster target){
        if (this.leftHand != null) {
            if (this.leftHand.getDurability() > 0) {
                target.receiveDamage(this.leftHand.getDamage());
                this.leftHand.setDurability(this.leftHand.getDurability() - 5);
            } else {
                System.out.println("The " + this.leftHand.getName() + " is broken");
            }
        } else {
            System.out.println("You don't have weapon in your hand !");
            target.receiveDamage(Settings.KNIGHT_DAMAGE_NO_WEAPON);
        }
    }

    public void useSpell(Monster monster){
        Scanner sc = new Scanner(System.in);
        System.out.println("Which spell do you want to use ?");
        System.out.println(this.capacities.getSpells().toString());
        System.out.println("Choose your spell : ");
        Spell spellChoose = this.capacities.getSpellByName(sc.nextLine());
        
        if (this.mana >= spellChoose.getManaCost()){
            this.mana -= spellChoose.getManaCost();
            System.out.println("Your mana is at " + this.mana);
            if(spellChoose.getName().equals("Heal")){
                ((HealingSpell) spellChoose).use(this);
            }else if(spellChoose.getName().equals("Damage")){
                ((DamageSpell) spellChoose).use(monster);
            }
        } else {
            System.out.println("You don't have enough mana !");
        }
    }

    public void changeWeapon(String futureWeapon){
        Weapon weapon = (Weapon) this.inventory.getObjectByName(futureWeapon);
        this.setLeftHand(weapon);
        System.out.println("Your new weapon is " + this.leftHand.getName());
    }

    public void regenerate(String potionName){
        if(potionName.equals("heal")) {
            HealPotion potion = (HealPotion) this.inventory.getObjectByName(potionName);
            this.life += potion.getLifeGiven();
            this.inventory.removeInInventory(potion);
            if(this.life > Settings.KNIGHT_MAX_LIFE)
                this.life = Settings.KNIGHT_MAX_LIFE;
            System.out.println("Your life is at " + this.life);
        }else if (potionName.equals("mana")) {
            ManaPotion potion = (ManaPotion) this.inventory.getObjectByName(potionName);
            this.mana += potion.getManaGiven();
            this.inventory.removeInInventory(potion);
            if(this.mana > Settings.KNIGHT_MAX_MANA)
                this.mana = Settings.KNIGHT_MAX_MANA;
            System.out.println("Your mana is at " + this.mana);
        }else{
            System.out.println("Default case : ! BUG !");
        }
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
                    System.out.println("The armor didn't change");
                break;
        }
        System.out.println(armor.getName() + " is added to your armor" );
    }

    public void receiveGold(int number) {
        this.gold += number;
        System.out.println("You receive " + number + " gold and have now " + this.gold + " gold");
    }

    public void removeGold(int number) {
        if(this.gold > 0) {
            this.gold -= number;
            if (this.gold <= 0) {
                this.gold = 0;
            }
        }
        System.out.println("You loose " + number + " gold and have now " + this.gold + " gold");
    }

    public void receiveItem(Object object) {
        this.inventory.addInInventory(object);
        System.out.println("You receive a new object: " + object.toString() + "\n");
    }

    public void receiveDamage(String monsterAttack, int damage){
        damage = calculateArmor(damage);
        reduceDurabilityEquipment(damage);
        if (damage > 0 ){
            this.life -= damage;
            System.out.println(monsterAttack + " give you " + damage + " damage");
        }else {
            System.out.println(monsterAttack + " you take 0 damage due to your armor");
        }
        System.out.println("You have " + this.life + " hp");
    }

    public void receiveXp(int number) {
        this.xp += number;
        System.out.println("You receive " + number + " xp and have now " + this.xp + " xp");
        if(this.xp >= this.levelUp){
            this.level += 1;
            this.xp = 0;
            System.out.println("You are level " + level + " !");
            checkLevel();
            setUpLevelUp();
        }
    }

    private int calculateArmor(int damage){
        int armor = 0;

        if (this.rightHand != null)
            armor += this.rightHand.getProtection();
        if (this.head != null)
            armor += this.head.getProtection();
        if (this.breasts != null)
            armor += this.breasts.getProtection();
        if (this.arms != null)
            armor += this.arms.getProtection();
        if (this.legs != null)
            armor += this.legs.getProtection();

        damage -= armor;
        return damage;
    }

    private void reduceDurabilityEquipment(int reduction){
        if (this.rightHand != null)
            this.rightHand.setDurability(this.rightHand.getDurability() - reduction);
        if (this.head != null)
            this.head.setDurability(this.head.getDurability() - reduction);
        if (this.breasts != null)
            this.breasts.setDurability(this.breasts.getDurability() - reduction);
        if (this.arms != null)
            this.arms.setDurability(this.arms.getDurability() - reduction);
        if (this.legs != null)
            this.legs.setDurability(this.legs.getDurability() - reduction);
    }

    private void checkLevel() {
        if (this.level >= Settings.KNIGHT_LEVEL_MINI_CAPACITIES) {
            Spell spellChosen = chooseSpellToAdd();
            this.spellsList.removeInSpellsList(spellChosen);
            this.capacities.addInCapacities(spellChosen);
        }
    }

    private Spell chooseSpellToAdd() {
        Spell spell = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Which spell do you want ?");
        System.out.println(this.spellsList.toString());
        spell = this.spellsList.getSpellByName(sc.nextLine());
        return spell;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getGold() {
        return gold;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
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
        System.out.println("[INVENTORY]");
        System.out.println(this.inventory.toString());
        Scanner sc = new Scanner(System.in);
        System.out.println("Action : ");
        System.out.println("--> W - Change weapon");
        System.out.println("--> A - Change/add armor");
        System.out.println("--> P - Take a potion");
        System.out.println("--> S - Show your equipment wear");
        System.out.println("--> Q - Quit the inventory");
        switch (sc.nextLine()){
            case "w":
                sc = new Scanner(System.in);
                System.out.println("Which weapon ?");
                changeWeapon(sc.nextLine());
                showInventory();
                break;
            case "a":
                sc = new Scanner(System.in);
                System.out.println("Which armor ?");
                changeArmor(sc.nextLine());
                showInventory();
                break;
            case "p":
                sc = new Scanner(System.in);
                System.out.println("Which potion ?");
                regenerate(sc.nextLine());
                showInventory();
                break;
            case "q":
                break;
            case "s":
                System.out.println("[EQUIPMENT]");
                System.out.println("Left Hand : " + this.leftHand.toString());
                if(this.rightHand != null)
                    System.out.println("Right Hand : " + this.rightHand.toString());
                else
                    System.out.println("Right Hand :");
                if(this.head != null)
                    System.out.println("Head : " + this.head.toString());
                else
                    System.out.println("Head :");
                if(this.arms != null)
                    System.out.println("Arms : " + this.arms.toString());
                else
                    System.out.println("Arms :");
                if(this.breasts != null)
                    System.out.println("Breast : " + this.breasts.toString());
                else
                    System.out.println("Breast :");
                if(this.legs != null)
                    System.out.println("Legs : " + this.legs.toString());
                else
                    System.out.println("Legs :");

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

    @Override
    public String toString() {
        return "Knight { " +
                life + " hp" +
                " ; " + mana + " mana " +
                "; level " + level +
                " ; " + gold + " gold " +
                '}';
    }
}
