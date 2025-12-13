import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class normalizeFile {

    public static List<String> readNormalFile(Path file) throws IOException {
        List<String> lines = new ArrayList<>();

        //Read lines from file
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        //Remove inside of comments
        List<String> noComments = removeComments(lines);

        List<String> result = new ArrayList<>();
        for (String line : noComments) {
            String normalized = normalize(line);
            
            //Keep blank lines so indexing stays the same
            result.add(normalized);
        }

        return result;
    }

    private static List<String> removeComments(List<String> lines) {
        List<String> result = new ArrayList<>();

        //Inside multiline comment
        boolean inBlock = false;

        for (String line : lines) {
            StringBuilder sb = new StringBuilder();
            boolean inString = false;
            char stringChar = 0;

            int i = 0;
            int n = line.length();

            while (i < n) {
                char c = line.charAt(i);

                if (inBlock) {
                    //Inside a block comment find end symbol
                    if (i + 1 < n && c == '*' && line.charAt(i + 1) == '/') {
                        inBlock = false;
                        i += 2;
                    } else {
                        i++;
                    }
                } else {
                    //Not in block comment

                    //Handle start/end of string literals
                    if (!inString && (c == '"' || c == '\'')) {

                        inString = true;
                        stringChar = c;
                        sb.append(c);
                        i++;
                        continue;

                    }
                    if (inString) {

                        sb.append(c);
                        // End of string if same quote and not escaped
                        if (c == stringChar && (i == 0 || line.charAt(i - 1) != '\\')) {
                            inString = false;
                        }
                        i++;
                        continue;
                    }

                    //Not in string, not in block comment: look for // or /*

                    //Block comment
                    if (i + 1 < n && c == '/' && line.charAt(i + 1) == '*') {
                        inBlock = true;
                        i += 2;
                        continue;
                    }

                    //Single-line comment
                    if (i + 1 < n && c == '/' && line.charAt(i + 1) == '/') {
                        break;
                    }

                    //Normal character
                    sb.append(c);
                    i++;
                }
            }

            //Add processed line (may be empty string)
            result.add(sb.toString());
        }

        return result;
    }

    private static String normalize(String line) {

        //Turn whitespace into a single space
        line = line.replaceAll("\\s+", " ");

        //Trim spaces
        line = line.trim();
        return line;

    }
}