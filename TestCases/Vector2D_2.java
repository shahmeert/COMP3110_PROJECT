package TestCases;
public class Vector2D_2 {

    private double xComp;
    private double yComp;

    public Vector2D_2(double xComp, double yComp) {
        this.xComp = xComp;
        this.yComp = yComp;
    }

    public double magnitude() {
        return Math.sqrt(xComp * xComp + yComp * yComp);
    }

    public void scale(double factor) {
        xComp *= factor;
        yComp *= factor;
    }

    public Vector2D_2 add(Vector2D_2 other) {
        return new Vector2D_2(xComp + other.xComp, yComp + other.yComp);
    }

    public String toString() {
        return "[" + xComp + ", " + yComp + "]";
    }
}
