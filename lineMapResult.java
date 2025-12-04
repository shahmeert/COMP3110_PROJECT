import java.io.*;
import java.util.*;

public class lineMapResult {
    private final Map <Integer, Integer> lineMap;
    
    //Constructor
    public lineMapResult(Map<Integer, Integer> lineMap, List <String> oldLines, List <String> newLines){
       
        this.lineMap = lineMap;

    }

    public void printToFile(String filename){

        try {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("Old File --> New File");
                writer.newLine();

                //Iterate through map
                for(Map.Entry<Integer, Integer> e : lineMap.entrySet()){
                    
                    int oldIndex = e.getKey();
                    int newIndex = e.getValue();
                    
                    //Print result
                    writer.write((oldIndex + 1) + " --> " + (newIndex + 1));
                    writer.newLine();
                    
                }
            }

            //Display message after finishing a pair
            System.out.println("Finished writing match results to " + filename + ".");

        } catch (IOException e) {

            System.out.println("Writing to " + filename +  " failed.");

        }

    }

}