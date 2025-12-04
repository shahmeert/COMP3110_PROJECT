
public class CartItem_1 {

    private String productId;
    private String name;
    private int quantity;
    private double price;

    public CartItem_1(String productId, String name, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void increaseQuantity(int amount) {
        if (amount > 0) {
            quantity += amount;
        }
    }

    public void decreaseQuantity(int amount) {
        if (amount > 0 && quantity - amount >= 0) {
            quantity -= amount;
        }
    }

    public double getTotal() {
        return quantity * price;
    }

    public String getName() {
        return name;
    }
}
