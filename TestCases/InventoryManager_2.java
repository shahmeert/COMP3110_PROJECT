package TestCases;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager_2 {

    private List<String> inventory = new ArrayList<>();

    public void insertItem(String item) { // renamed
        inventory.add(item);
    }

    public boolean deleteItem(String item) { // renamed
        return inventory.remove(item);
    }

    public int totalItems() { // renamed
        return inventory.size();
    }

    public void display() { // renamed print method
        for (String s : inventory) {
            System.out.println("- " + s);
        }
    }
}
