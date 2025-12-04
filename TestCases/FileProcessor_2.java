
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor_2 {

    // updated method name and logic ordering
    public int getLineCount(String filename) {
        int total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.readLine() != null) total++;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public boolean containsText(String filename, String text) {
        // slight change: trimmed comparison
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().contains(text)) return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
