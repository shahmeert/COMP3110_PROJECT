package TestCases;
public class Shape_1 {

    private String type;
    private double width;
    private double height;

    public Shape_1(String type, double width, double height) {
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public boolean isSquare() {
        return width == height;
    }

    public String describe() {
        return "Shape: " + type + ", width=" + width + ", height=" + height;
    }

    public void resize(double factor) {
        this.width *= factor;
        this.height *= factor;
    }
}
