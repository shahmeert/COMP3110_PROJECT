public class Vector2D_1 {

    private double x;
    private double y;

    public Vector2D_1(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }

    public Vector2D_1 add(Vector2D_1 other) {
        return new Vector2D_1(this.x + other.x, this.y + other.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
