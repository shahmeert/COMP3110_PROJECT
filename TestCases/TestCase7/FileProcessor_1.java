import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor_1 {

    public int countLines(String filename) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean containsText(String filename, String text) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(text)) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
