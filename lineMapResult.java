import java.io.*;
import java.util.*;

public class lineMapResult {
    private Map <Integer, Integer> lineMap = new LinkedHashMap<>();
    private double accuracy = -1;
    //Constructor
    public lineMapResult(Map<Integer, Integer> lineMap){
        this.lineMap = lineMap;
    }

    public void addMapping(int oldLine, int newLine) {
        lineMap.put(oldLine, newLine);
    }

    public void setAccuracy(double acc) {
        this.accuracy = acc;
    }

    public double computeAccuracy(Map<Integer, Integer> expected){
        int correct = 0;

        // union of all origin line indices seen in either map
        Set<Integer> allLines = new HashSet<>();
        allLines.addAll(expected.keySet());
        allLines.addAll(lineMap.keySet());

        if (allLines.isEmpty()) {
            return 0.0;
        }

        for (int origin : allLines) {
            int expectedVal = expected.getOrDefault(origin, -1); // -1 = “no mapping / deleted”
            int actualVal   = lineMap.getOrDefault(origin, -1);

            if (expectedVal == actualVal) {
                correct++;
            }
        }

        return (100.0 * correct) / allLines.size();
        
    }



    public void printToFile(String filename){

        try {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(String.format("Accuracy: %.2f%%", accuracy));
                writer.newLine();
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