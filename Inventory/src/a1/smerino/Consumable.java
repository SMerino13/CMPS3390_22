package a1.smerino;

public class Consumable extends Item{
    private int usesLeft;

    // Constructor
    public Consumable() {
        // Call the base classes constructor
        // Will call items constructor
        super();
        this.usesLeft = 0;
    }

    public Consumable(String name, double price, int qty, int usesLeft) {
        super(name, price, qty);
        this.usesLeft = Math.max(usesLeft, 0);
    }

    public int getUsesLeft() {
        return usesLeft;
    }

    public void setUsesLeft(int usesLeft) {
        this.usesLeft = Math.max(usesLeft, 0);
    }

    @Override
    public String toString(){
        // First the parent (Item) string from its toString
        return String.format("%s %5d |", super.toString(), this.usesLeft);
    }
}
