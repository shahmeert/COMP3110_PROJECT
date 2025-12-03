public class Shape_2 {

    private String category;
    private double w;
    private double h;

    public Shape_2(String category, double w, double h) {
        this.category = category;
        this.w = w;
        this.h = h;
    }

    public double area() {
        return w * h;
    }

    public boolean isSquare() {
        return w == h;
    }

    public void doubleSize() {
        w *= 2;
        h *= 2;
    }

    public String getInfo() {
        return category + " [" + w + " x " + h + "]";
    }
}
