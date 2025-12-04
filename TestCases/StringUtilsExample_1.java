package TestCases;
public class StringUtilsExample_1 {

    public String reverse(String input) {
        StringBuilder builder = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            builder.append(input.charAt(i));
        }
        return builder.toString();
    }

    public boolean isPalindrome(String input) {
        String reversed = reverse(input);
        return input.equals(reversed);
    }

    public String joinWithComma(String[] values) {
        if (values == null || values.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            builder.append(values[i]);
            if (i < values.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        StringUtilsExample_1 utils = new StringUtilsExample_1();
        System.out.println(utils.isPalindrome("level"));
    }
}
