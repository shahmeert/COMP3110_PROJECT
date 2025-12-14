import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
 - Receives a file
 - Removes comments
 - Normalizes whitespace
 - Returns the file as a List<String> with the SAME number of lines
*/

public class normalizeFile {

    public static List<String> readNormalFile(Path file) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        // Remove comment *content* but keep one output line per input line
        List<String> noComments = removeComments(lines);

        // Normalize spaces but DO NOT drop empty lines
        List<String> result = new ArrayList<>();
        for (String line : noComments) {
            String normalized = normalize(line);
            result.add(normalized); // keep even if blank
        }

        return result;
    }

    private static List<String> removeComments(List<String> lines) {
        List<String> result = new ArrayList<>();
        boolean inBlock = false;   // inside /* ... */ across lines

        for (String line : lines) {
            StringBuilder sb = new StringBuilder();
            boolean inString = false;
            char stringChar = 0;

            int i = 0;
            int n = line.length();

            while (i < n) {
                char c = line.charAt(i);

                if (inBlock) {
                    // Look only for end of block comment */
                    if (i + 1 < n && c == '*' && line.charAt(i + 1) == '/') {
                        inBlock = false;
                        i += 2; // skip */
                    } else {
                        i++;    // skip comment chars
                    }
                } else { // not in block comment
                    if (!inString) {
                        // Start of line/block comment?
                        if (i + 1 < n && c == '/' && line.charAt(i + 1) == '*') {
                            inBlock = true;
                            i += 2;  // skip /*
                            continue;
                        }
                        // Single-line comment //
                        if (i + 1 < n && c == '/' && line.charAt(i + 1) == '/') {
                            // rest of line is a comment
                            break;
                        }
                        // Start of string literal?
                        if (c == '"' || c == '\'') {
                            inString = true;
                            stringChar = c;
                            sb.append(c);
                            i++;
                        } else {
                            sb.append(c);
                            i++;
                        }
                    } else {
                        // Inside a string literal: copy everything until end of string
                        sb.append(c);
                        if (c == stringChar && (i == 0 || line.charAt(i - 1) != '\\')) {
                            inString = false;
                        }
                        i++;
                    }
                }
            }

            // Always add a line (may be empty) to preserve indexing
            result.add(sb.toString());
        }

        return result;
    }

    private static String normalize(String line) {
        line = line.trim();
        line = line.replaceAll("\s+$", "");
        return line;
    }
}