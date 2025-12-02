import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*

- Recieves a file
- Normalizes the file
- returns the file as List 

*/

public class normalizeFile {
    
        public static List<String> readNormalFile(Path file) throws IOException{
            List<String> lines = new ArrayList<>();

            try(BufferedReader br = Files.newBufferedReader(file)){
                String line;

                while((line = br.readLine()) != null){
                    String normalize = normalize(line);
                    if(normalize.isEmpty() != true){
                        lines.add(normalize);
                    }
                }
            }
            return lines;
        }

        private static String normalize(String line){
            line = line.trim();
            line = line.replaceAll("\\s+", " ");
            return line;
        }

}
