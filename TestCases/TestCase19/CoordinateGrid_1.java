public class CoordinateGrid_1 {

    private int width;
    private int height;

    public CoordinateGrid_1(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int toIndex(int x, int y) {
        if (!inBounds(x, y)) {
            return -1;
        }
        return y * width + x;
    }
}
