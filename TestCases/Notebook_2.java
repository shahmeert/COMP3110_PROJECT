package TestCases;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Notebook_2 {

    private String name;
    private List<String> entries;
    private boolean locked;

    public Notebook_2(String name) {
        this.name = name;
        this.entries = new ArrayList<>();
        this.locked = false;
    }

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }

    public boolean addEntry(String entry) {
        if (locked) return false;
        if (entry != null && !entry.isEmpty()) {
            entries.add(entry);
            return true;
        }
        return false;
    }

    public List<String> listEntries() {
        return Collections.unmodifiableList(entries);
    }

    public int size() {
        return entries.size();
    }

    public String getName() {
        return name;
    }
}
