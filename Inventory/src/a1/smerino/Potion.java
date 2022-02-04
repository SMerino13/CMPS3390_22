package a1.smerino;

public class Potion extends Tool{
    private String potionType;
    private double resalePrice;

    public Potion() {
        super();
        this.potionType = "";
        this.resalePrice =0;
    }

    public Potion(String name, double price, int qty, String use, String potionType, double resalePrice) {
        super(name, price, qty, use);
        this.potionType = potionType;
        this.resalePrice = resalePrice;
    }

    public String getPotionType() {
        return potionType;
    }

    public void setPotionType(String potionType) {
        this.potionType = potionType;
    }

    public double getResalePrice() {
        return resalePrice;
    }

    public void setResalePrice(double resalePrice) {
        this.resalePrice = resalePrice;
    }

    @Override
    public String toString(){
        return String.format("%s %-15s %s %10.2f|", super.toString(), this.potionType, "|", this.resalePrice);
    }
}
