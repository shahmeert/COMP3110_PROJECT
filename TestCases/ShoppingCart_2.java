package TestCases;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart_2 {

    private List<String> productNames = new ArrayList<>();
    private List<Double> productPrices = new ArrayList<>();

    public void addItem(String name, double price) {
        productNames.add(name);
        productPrices.add(price);
    }

    public int size() {
        return productNames.size();
    }

    public double getTotal() {
        double total = 0.0;
        for (double price : productPrices) {
            total += price;
        }
        return total;
    }

    public boolean isEmpty() {
        return productNames.isEmpty();
    }
}
