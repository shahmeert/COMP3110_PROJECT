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
                lines.add(line);
            }
        }
        List<String> noComments = removeComments(lines); 
        List<String> result = new ArrayList<>();
        for (String line : noComments) {
            String normalized = normalize(line);
            if (!normalized.isEmpty()) {
                result.add(normalized);
            }
        }
        return result;
    }

    private static List<String> removeComments(List<String> lines){
        List<String> result = new ArrayList<>();
        boolean inBlock = false;

        for(String line : lines){
            StringBuilder sb = new StringBuilder();
            boolean inString = false;
            char stringChar = 0;

            int i = 0;
            int n = line.length();

            while(i < n){
                char c = line.charAt(i);

                if(!inBlock){ //handles string literals

                    if(c == '"' || c == '\''){
                        if(!inString){
                            inString = true;
                            stringChar = c;
                        } else if(c == stringChar){
                            if(i > 0 && line.charAt(i - 1) != '\\'){
                                inString = false;
                            }
                        }
                    }
                    sb.append(c);
                    i++;
                }
                else if(!inString){ //looks for comments outside of string
                    if(i + 1 < n && c == '/' && line.charAt(i + 1) == '*' ){ //block comments
                        inBlock = true;
                        i += 2;
                    }
                    else if( i + 1 < n && c =='/' && line.charAt(i + 1) == '/'){ //single line comment
                        break;
                    }
                    else{ //inside string normal char
                        sb.append(c);
                        i++;
                    }

                }
                else{
                    if(i + 1 < n && c == '*' && line.charAt(i +1) == '/'){
                        inBlock = false;
                        i += 2;
                    } else{
                        i++;
                    }
                }
        }
            result.add(sb.toString());
        }
        return result;
    }

    private static String normalize(String line){
        line = line.trim();
        line = line.replaceAll("\\s+", " ");
        return line;
    }

}
