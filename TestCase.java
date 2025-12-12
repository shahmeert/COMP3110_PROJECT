import java.util.*;

public class TestCase {
    public String name;
    public List<String> oldLines;
    public List<String> newLines;
    public Map<Integer, Integer> expectedMapping;
    public boolean hasXml;

    public TestCase(String name, List<String> oldLines, List<String> newLines, Map<Integer, Integer> expectedMapping, boolean hasXml){
        this.name = name;
        this.oldLines = oldLines;
        this.newLines = newLines;
        this.expectedMapping = expectedMapping;
        this.hasXml = hasXml;
    }
}
