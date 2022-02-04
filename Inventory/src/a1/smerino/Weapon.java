package a1.smerino;

public class Weapon extends Item{
    private String weaponType;
    private float hitPoints;

    public Weapon() {
        super();
        this.weaponType = "";
        this.hitPoints = 0;
    }

    public Weapon(String name, double price, int qty, String weaponType, float hitPoints) {
        super(name, price, qty);
        this.weaponType = weaponType;
        this.hitPoints = Math.max(hitPoints, 0);
    }

    public String getWeaponType() {
        return weaponType;
    }

    public float getHitPoints() {
        return hitPoints;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public void setHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public String toString(){
        return String.format("%s %s %-2s %8.2f |", super.toString(), this.weaponType, "| Damage/Hit:", this.hitPoints);
    }
}
