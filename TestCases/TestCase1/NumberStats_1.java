import java.util.Arrays;

public class NumberStats_1 {

    private int[] values;

    public NumberStats_1(int[] values) {
        this.values = values;
    }

    public int min() {
        int min = Integer.MAX_VALUE;
        for (int v : values) {
            if (v < min) {
                min = v;
            }
        }
        return min;
    }

    public int max() {
        int max = Integer.MIN_VALUE;
        for (int v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

    public double average() {
        if (values.length == 0) return 0.0;
        int sum = 0;
        for (int v : values) {
            sum += v;
        }
        return sum / (double) values.length;
    }

    @Override
    public String toString() {
        return "NumberStats(values=" + Arrays.toString(values) + ")";
    }
}
