package a1.smerino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        List<Item> items = new ArrayList<>();
        FoodItems[] foodItems = FoodItems.values();
        Tools[] tools = Tools.values();
        ToolUses[] toolUses = ToolUses.values();

        // Weapons
        WeaponTypes[] weaponTypes = WeaponTypes.values();
        WeaponNames[] weaponNames = WeaponNames.values();

        // Potions
        Potions[] potionNames = Potions.values();
        PotionTypes[] potionTypes = PotionTypes.values();
        PotionPurchases[] potionPurchases = PotionPurchases.values();

        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for(int i=0; i<itemCnt; i++) {
            int type = ran.nextInt(4);
            switch (type) {
                case 0 -> {
                    int foodIndex = ran.nextInt(foodItems.length);
                    String foodName = foodItems[foodIndex].toString();
                    float foodPrice = ran.nextFloat(10);
                    int foodQty = ran.nextInt(30);
                    int foodUses = ran.nextInt(6);
                    float healthGain = ran.nextFloat(20);
                    Food tmpFood = new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
                    items.add(tmpFood);
                }
                case 1 -> {
                    int toolIndex = ran.nextInt(tools.length);
                    String toolName = tools[toolIndex].toString();
                    float toolPrice = ran.nextFloat(200);
                    int toolQty = ran.nextInt(15);
                    String use = toolUses[toolIndex].toString();
                    Tool tmpTool = new Tool(toolName, toolPrice, toolQty, use);
                    items.add(tmpTool);
                }
                case 2 -> {
                    int weaponIndex = ran.nextInt(weaponNames.length);
                    String weaponName = weaponNames[weaponIndex].toString();
                    float weaponPrice = ran.nextFloat(1000);
                    int weaponQty = ran.nextInt(15);
                    String weaponType = weaponTypes[weaponIndex].toString();
                    float hitPoint = ran.nextFloat(50);
                    Weapon tmpWeapon = new Weapon(weaponName, weaponPrice, weaponQty, weaponType, hitPoint);
                    items.add(tmpWeapon);
                }
                case 3 -> {
                    int potionIndex = ran.nextInt(potionNames.length);
                    String potionName = potionNames[potionIndex].toString();
                    float potionPrice = ran.nextFloat(15);
                    int potionQty = ran.nextInt(15);
                    String potionType = potionTypes[potionIndex].toString();
                    String potionPurchase = potionPurchases[potionIndex].toString();
                    float returnSale = potionPrice / 2;
                    Potion tmpPotion = new Potion(potionName, potionPrice, potionQty, potionType, potionPurchase, returnSale);
                    items.add(tmpPotion);
                }
            }
        }

        for(Item i : items){
            System.out.println(i);
        }
    }
}
