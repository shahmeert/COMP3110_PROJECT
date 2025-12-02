import java.util.List;

public class TestCase {
    public String name;
    public List<String> oldLines;
    public List<String> newLines;

    public TestCase(String name, List<String> oldLines, List<String> newLines ){
        this.name = name;
        this.oldLines = oldLines;
        this.newLines = newLines;
    }
}
