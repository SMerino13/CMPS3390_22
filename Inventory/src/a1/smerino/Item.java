package a1.smerino;

public class Item {
    private  double price;
    private int qty;
    private String name;

    public Item() {
        this.price = 0;
        this.qty = 0;
        this.name = "Item";
    }

    public Item(String name, double price, int qty) {
        this.price = price;
        this.qty = qty;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.max(price, 0);
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = Math.max(qty, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("| %-20s | $%-10.2f| %6d |", this.name, this.price, this.qty);
    }
}
