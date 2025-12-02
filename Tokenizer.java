import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tokenizer {

    // Java types
    private static final Set<String> TYPES = Set.of( 
    "int", "double", "float", "long", "short", 
    "byte", "boolean", "char", "void", "String");

    // Java keywords
    private static final Set<String> KEYWORDS = Set.of(
    "if", "else", "for", "while", "do",
    "switch", "case", "default",
    "break", "continue", "return",
    "class", "public", "private", "protected",
    "static", "final", "new", "this",
    "null", "true", "false");

    /*
    * Input: Recieves a List of Strings
    * Output: List of tokenized String
    * Loops through ALL lines of the java file
    */
    public static List<String> tokenize(List<String> lines) {
        List<String> tokens = new ArrayList<>();
        for (String line : lines) {
            tokenizeLine(line, tokens); // works on single line
        }
        tokens.add("EOF");
        return tokens;
    }

    /*
    * Tokenize a single line and store in token list
    */
    private static void tokenizeLine(String line, List<String> tokens) {
        int i = 0;
        int n = line.length();
        StringBuilder sb = new StringBuilder();

        while (i < n) { // loop length of line
            char c = line.charAt(i);

            // Skip whitespace (lines are "normalized" but just in case)**************
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // Identifiers / keywords / types (start with letter or _)
            if (Character.isLetter(c) || c == '_') {
                sb.setLength(0);
                while (i < n && (Character.isLetterOrDigit(line.charAt(i)) || line.charAt(i) == '_')) {
                    sb.append(line.charAt(i));
                    i++;
                }
                addWordToken(sb.toString(), tokens); // sends the read word to check what token it is
                continue;
            }

            // Numbers
            // same as letters but we read numbers
            if (Character.isDigit(c)) {
                sb.setLength(0);
                while (i < n && Character.isDigit(line.charAt(i))) {
                    sb.append(line.charAt(i));
                    i++;
                }
                tokens.add("NUMBER");
                continue;
            }

            // String literals "..."
            if (c == '"') {
                i++; // skip opening quote
                sb.setLength(0);
                while (i < n && line.charAt(i) != '"') {
                    sb.append(line.charAt(i));
                    i++;
                }
                // skip closing quote
                if (i < n && line.charAt(i) == '"') {
                    i++;
                }
                tokens.add("STRING");
                continue;
            }

            // Multi-character operators: ==, !=, >=, <=, &&, ||
            if (i + 1 < n) {
                char next = line.charAt(i + 1);
                String two = "" + c + next;
                switch (two) {
                    case "==":
                        tokens.add("EQEQ");
                        i += 2;
                        continue;
                    case "!=":
                        tokens.add("NEQ");
                        i += 2;
                        continue;
                    case ">=":
                        tokens.add("GE");
                        i += 2;
                        continue;
                    case "<=":
                        tokens.add("LE");
                        i += 2;
                        continue;
                    case "&&":
                        tokens.add("AND");
                        i += 2;
                        continue;
                    case "||":
                        tokens.add("OR");
                        i += 2;
                        continue;
                }
            }

            // Single-character operators / punctuation
            switch (c) {
                case '=': tokens.add("ASSIGN"); break;
                case '+': tokens.add("PLUS"); break;
                case '-': tokens.add("MINUS"); break;
                case '*': tokens.add("STAR"); break;
                case '/': tokens.add("SLASH"); break;
                case '%': tokens.add("PERCENT"); break;
                case '<': tokens.add("LT"); break;
                case '>': tokens.add("GT"); break;
                case '!': tokens.add("NOT"); break;
                case ';': tokens.add("SEMICOLON"); break;
                case ',': tokens.add("COMMA"); break;
                case '.': tokens.add("DOT"); break;
                case '(': tokens.add("LPAREN"); break;
                case ')': tokens.add("RPAREN"); break;
                case '{': tokens.add("LBRACE"); break;
                case '}': tokens.add("RBRACE"); break;
            }
            i++;
        }
    }

    /**
    * Decide if a word is a TYPE, KEYWORD, or ID.
    */
    private static void addWordToken(String word, List<String> tokens) {
        if (TYPES.contains(word)) {
            tokens.add("TYPE");
        } else if (KEYWORDS.contains(word)) {
            // map keyword to its own token name, e.g. "if" → "IF"
            tokens.add(word.toUpperCase());
        } else if ("true".equals(word) || "false".equals(word)) {
            tokens.add("BOOLEAN_LITERAL");
        } else if ("null".equals(word)) {
            tokens.add("NULL_LITERAL");
        } else {
            tokens.add("ID");
        }
    }
}
