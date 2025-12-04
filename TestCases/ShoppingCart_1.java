
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart_1 {

    private List<String> items = new ArrayList<>();
    private List<Double> prices = new ArrayList<>();

    public void addItem(String name, double price) {
        items.add(name);
        prices.add(price);
    }

    public int getItemCount() {
        return items.size();
    }

    public double getTotal() {
        double total = 0.0;
        for (double p : prices) {
            total += p;
        }
        return total;
    }

    public void clear() {
        items.clear();
        prices.clear();
    }
}
