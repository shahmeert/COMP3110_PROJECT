
import java.util.ArrayList;
import java.util.List;

public class InventoryManager_1 {

    private List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }

    public boolean removeItem(String item) {
        return items.remove(item);
    }

    public int countItems() {
        return items.size();
    }

    public void printItems() {
        for (String s : items) {
            System.out.println(s);
        }
    }
}
