

// File: NumberStats_2.java
import java.util.Arrays;

public class NumberStats_2 {

    // renamed from 'values' to 'numbers'
    private int[] numbers;

    public NumberStats_2(int[] numbers) {
        this.numbers = numbers;
    }

    // same logic, just slightly reordered declarations and comments added
    public int max() {
        int max = Integer.MIN_VALUE;
        for (int n : numbers) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    public int min() {
        int min = Integer.MAX_VALUE;
        for (int n : numbers) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }

    public double average() {
        if (numbers.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        // slight formatting change on this line
        return sum / (double) numbers.length;
    }

    @Override
    public String toString() {
        return "NumberStats(numbers=" + Arrays.toString(numbers) + ")";
    }
}
