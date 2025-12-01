import java.util.*;

public class mapMatch {

    public lineMapResult map(List<String> oldLines, List<String> newLines){
        Map<Integer, Integer> mapping = new HashMap<>();

        return new lineMapResult(mapping, oldLines, newLines);
    }

}
