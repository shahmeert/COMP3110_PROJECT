public class SensorReading_1 {

    private String id;
    private long timestamp;
    private double value;

    public SensorReading_1(String id, long timestamp, double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

    public boolean isAbove(double threshold) {
        return value > threshold;
    }

    public boolean isBelow(double threshold) {
        return value < threshold;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }
}
