
public class Product_1 {

    private String id;
    private String name;
    private double price;
    private int stock;

    public Product_1(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public boolean purchase(int qty) {
        if (qty > 0 && qty <= stock) {
            stock -= qty;
            return true;
        }
        return false;
    }

    public void restock(int qty) {
        if (qty > 0) {
            stock += qty;
        }
    }

    public String label() {
        return name + " ($" + price + ") - stock: " + stock;
    }
}
