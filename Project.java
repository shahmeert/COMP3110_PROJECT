/* Comp 3110 Project
 
Group Members: Shahmeer, Kyle, Marko
Due Date: Dec 10th
*/

import java.util.*;
import java.io.IOException;
import java.util.List;

public class Project {

    public static void main(String[] args) {
        //get filepath
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Testcase path: ");

        String datasetPath = scan.nextLine();
        scan.close();

        try {
            List<TestCase> tests = DSLoader.Loader(datasetPath);
            if (tests.isEmpty()) {
                System.out.println("No test cases found in: " + datasetPath);
                return;
            }

            mapMatch matcher = new mapMatch();

            for (TestCase tc : tests) {
                System.out.println("Processing test case: " + tc.name);

                lineMapResult result = matcher.map(tc.oldLines, tc.newLines);

                // Create a separate result file for each pair
                String outFileName = tc.name + ".txt";
                result.printToFile(outFileName);
            }

            System.out.println("All test cases processed.");

        } catch (IOException e) {
            System.out.println("Failed to load dataset from: " + datasetPath);

        }
    }
}