public class Vehicle_1 {

    private String make;
    private String model;
    private int year;
    private int mileage;
    private boolean running;

    public Vehicle_1(String make, String model, int year, int mileage) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.running = false;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void drive(int distance) {
        if (running && distance > 0) {
            mileage += distance;
        }
    }

    public String info() {
        return make + " " + model + " (" + year + ") - " + mileage + " km";
    }
}
