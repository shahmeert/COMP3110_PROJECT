package TestCases;
public class SensorReading_2 {

    private String sensorId;
    private long timeMillis;
    private double reading;

    public SensorReading_2(String sensorId, long timeMillis, double reading) {
        this.sensorId = sensorId;
        this.timeMillis = timeMillis;
        this.reading = reading;
    }

    public boolean above(double threshold) {
        return reading > threshold;
    }

    public boolean below(double threshold) {
        return reading < threshold;
    }

    public boolean withinRange(double min, double max) {
        return reading >= min && reading <= max;
    }

    public double getReading() {
        return reading;
    }
}
