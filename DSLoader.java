import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class DSLoader {
    public static List<TestCase> Loader(String path) throws IOException{
        List<TestCase> testList = new ArrayList<>();

        File folder = new File(path);
        
        //Folder path entered incorrectly
        if (!folder.exists() || !folder.isDirectory()){
            System.out.println("Dataset not found");
            return testList;
        }

        //Create array of files
        File[] files = folder.listFiles();
        Arrays.sort(files, Comparator.comparing(File::getName));

        for (int i = 0; i < files.length - 1; i++){
            File oldFile = files[i];
            File newFile = files[i + 1];

            //Remove excess from title to determine base
            String oldBase = oldFile.getName().replaceAll("\\d+\\.java$", "");
            String newBase = newFile.getName().replaceAll("\\d+\\.java$", "");

            
            if (!oldBase.equals(newBase)){
                continue;
            }

            //Send Files to normalizeFile.java for normalization
            Path oldPath = oldFile.toPath();
            Path newPath = newFile.toPath();

            List<String> oldLines = normalizeFile.readNormalFile(oldPath);
            List<String> newLines = normalizeFile.readNormalFile(newPath);

            //Name and create the test case 
            String testName = oldBase + "1_-→_" + oldBase + "2_result";

            //Get Xml File
            String xmlBase = oldBase.replaceAll("_$", "").replaceAll("\\d+$", "");
            File xmlFile = new File(folder, xmlBase + ".xml");
            
            Map<Integer, Integer> expectedMapping = new HashMap<>();

            //Send xml file to loader
            if(xmlFile.exists() && xmlFile.isFile()){

                expectedMapping = XMLAccuracyLoader.loadXML(xmlFile);

            }

            //Create test case
            TestCase tc = new TestCase(testName, oldLines, newLines, expectedMapping);

            //Add test case to list of test cases
            testList.add(tc);
            
        }

        return testList;
    
    }

}