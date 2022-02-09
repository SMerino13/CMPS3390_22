package a1.smerino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random ran = new Random();
    private static final FoodItems[] foodItems = FoodItems.values();
    private static final Tools[] tools = Tools.values();
    private static final ToolUses[] toolUses = ToolUses.values();


    // Weapons
    private static final WeaponTypes[] weaponTypes = WeaponTypes.values();
    private static final WeaponNames[] weaponNames = WeaponNames.values();

    // Potions
    private static final Potions[] potionNames = Potions.values();
    private static final PotionTypes[] potionTypes = PotionTypes.values();
    private static final PotionPurchases[] potionPurchases = PotionPurchases.values();



    public static void main(String[] args){
        List<Item> items = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for(int i=0; i<itemCnt; i++) {
            int type = ran.nextInt(4);
            switch (type) {
                case 0 -> items.add(genFood());
                case 1 -> items.add(genTool());
                case 2 -> items.add(genWeapon());
                case 3 -> items.add(genPotion());
            }
        }

        for(Item i : items){
            System.out.println(i);
        }
    }

    public static Food genFood(){
        int foodIndex = ran.nextInt(foodItems.length);
        String foodName = foodItems[foodIndex].toString();
        float foodPrice = ran.nextFloat(10);
        int foodQty = ran.nextInt(30);
        int foodUses = ran.nextInt(6);
        float healthGain = ran.nextFloat(20);
        return new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
    }

    public static Tool genTool(){
        int toolIndex = ran.nextInt(tools.length);
        String toolName = tools[toolIndex].toString();
        float toolPrice = ran.nextFloat(200);
        int toolQty = ran.nextInt(15);
        String use = toolUses[toolIndex].toString();
        return new Tool(toolName, toolPrice, toolQty, use);
    }

    public static Weapon genWeapon(){
        int weaponIndex = ran.nextInt(weaponNames.length);
        String weaponName = weaponNames[weaponIndex].toString();
        float weaponPrice = ran.nextFloat(1000);
        int weaponQty = ran.nextInt(15);
        String weaponType = weaponTypes[weaponIndex].toString();
        float hitPoint = ran.nextFloat(50);
        return new Weapon(weaponName, weaponPrice, weaponQty, weaponType, hitPoint);
    }

    public static Potion genPotion(){
        int potionIndex = ran.nextInt(potionNames.length);
        String potionName = potionNames[potionIndex].toString();
        float potionPrice = ran.nextFloat(15);
        int potionQty = ran.nextInt(15);
        String potionType = potionTypes[potionIndex].toString();
        String potionPurchase = potionPurchases[potionIndex].toString();
        float returnSale = potionPrice / 2;
        return new Potion(potionName, potionPrice, potionQty, potionType, potionPurchase, returnSale);
    }
}
