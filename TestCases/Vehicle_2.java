package TestCases;
public class Vehicle_2 {

    private String brand;
    private String type;
    private int yearBuilt;
    private int odometer;
    private boolean engineOn;

    public Vehicle_2(String brand, String type, int yearBuilt, int odometer) {
        this.brand = brand;
        this.type = type;
        this.yearBuilt = yearBuilt;
        this.odometer = odometer;
        this.engineOn = false;
    }

    public void ignite() {
        engineOn = true;
    }

    public void shutDown() {
        engineOn = false;
    }

    public void travel(int kms) {
        if (engineOn && kms > 0) {
            odometer += kms;
        }
    }

    public String summary() {
        return brand + " " + type + " (year " + yearBuilt + "), " + odometer + " km";
    }
}
