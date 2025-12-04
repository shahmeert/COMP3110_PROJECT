
public class StringUtilsExample_2 {

    public String reverse(String text) { // parameter renamed from input -> text
        StringBuilder builder = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            builder.append(text.charAt(i));
        }
        return builder.toString();
    }

    // updated to be case-insensitive and ignore surrounding spaces
    public boolean isPalindrome(String input) {
        if (input == null) return false;
        String cleaned = input.trim().toLowerCase();
        String reversed = reverse(cleaned);
        return cleaned.equals(reversed);
    }

    // same behavior, minor formatting/comment changes
    public String joinWithComma(String[] items) {
        if (items == null || items.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            builder.append(items[i]);
            if (i < items.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        StringUtilsExample_2 utils = new StringUtilsExample_2();
        System.out.println(utils.isPalindrome("Level"));
    }
}
