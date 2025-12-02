import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class DSLoader {
    public static List<TestCase> Loader(String path) throws IOException{
        List<TestCase> testList = new ArrayList<>();

        File folder = new File(path);
        

        if (!folder.exists() || !folder.isDirectory()){
            System.out.println("Dataset not found");
            return testList;
        }

        File[] files = folder.listFiles();

        for (int i = 0; i < files.length - 1; i++){
            File oldFile = files[i];
            File newFile = files[i + 1];

            String oldBase = oldFile.getName().replaceAll("\\d+\\.txt$", "");
            String newBase = newFile.getName().replaceAll("\\d+\\.txt$", "");

            if (!oldBase.equals(newBase)){
                continue;
            }

            List<String> oldLines = Files.readAllLines(oldFile.toPath());
            List<String> newLines = Files.readAllLines(newFile.toPath()); 

            String testName = oldBase + (i + 1) + "→" + oldBase + (i + 2);

            testList.add(new TestCase(testName, oldLines, newLines));
        }
        return testList;
    }
}
