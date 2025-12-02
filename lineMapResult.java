import java.io.*;
import java.util.*;

public class lineMapResult {
    private final Map <Integer, Integer> lineMap;
    private List <String> oldLines;
    private List <String> newLines;
    
    //Constructor
    public lineMapResult(Map<Integer, Integer> lineMap, List <String> oldLines, List <String> newLines){
        this.lineMap = lineMap;
        this.oldLines = oldLines;
        this.newLines = newLines;
    }

    //Print Matching Lines
    /*public void printMatches(){

        for(Map.Entry<Integer, Integer> e : lineMap.entrySet()){

            int oldIndex = e.getKey();
            int newIndex = e.getValue();

            System.out.println((oldIndex + 1) + " --> " + (newIndex + 1));

        }

    }*/

    public void printToFile(String filename){

        try {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("Old File --> New File");
                writer.newLine();

                for(Map.Entry<Integer, Integer> e : lineMap.entrySet()){
                    
                    int oldIndex = e.getKey();
                    int newIndex = e.getValue();
                    
                    writer.write((oldIndex + 1) + " --> " + (newIndex + 1));
                    writer.newLine();
                    
                }
            }
            System.out.println("Finished writing match results to " + filename + ".");

        } catch (IOException e) {

            System.out.println("Writing to " + filename +  " failed.");

        }

    }

}