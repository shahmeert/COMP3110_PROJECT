
public class Product_2 {

    private String code;
    private String displayName;
    private double unitCost;
    private int quantity;

    public Product_2(String code, String displayName, double unitCost, int quantity) {
        this.code = code;
        this.displayName = displayName;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public boolean sell(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount;
            return true;
        }
        return false;
    }

    public void addStock(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }

    public String details() {
        return displayName + ": $" + unitCost + ", qty=" + quantity;
    }
}
