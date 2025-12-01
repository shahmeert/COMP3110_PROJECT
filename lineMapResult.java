import java.util.*;
public class lineMapResult {
    private  Map <Integer, Integer> lineMap;
    private List <String> oldLines;
    private List <String> newLines;
    
    //create intial map
    public lineMapResult(Map<Integer, Integer> lineMap, List <String> oldLines, List <String> newLines){
        this.lineMap = lineMap;
        this.oldLines = oldLines;
        this.newLines = newLines;
    }

    //Print Matching Lines
    public void printMatches(){

        for(Map.Entry<Integer, Integer> e : lineMap.entrySet()){

            int oldIndex = e.getKey();
            int newIndex = e.getValue();

            System.out.println((oldIndex + 1) + " --> " + (newIndex + 1));

        }

    }

}
