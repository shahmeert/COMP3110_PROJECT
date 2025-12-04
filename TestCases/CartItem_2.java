
public class CartItem_2 {

    private String id;
    private String displayName;
    private int qty;
    private double unitPrice;

    public CartItem_2(String id, String displayName, int qty, double unitPrice) {
        this.id = id;
        this.displayName = displayName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public void addUnits(int units) {
        if (units > 0) {
            qty += units;
        }
    }

    public void removeUnits(int units) {
        if (units > 0 && qty - units >= 0) {
            qty -= units;
        }
    }

    public double subtotal() {
        return qty * unitPrice;
    }

    public String getDisplayName() {
        return displayName;
    }
}
