
public class CoordinateGrid_2 {

    private int columns;
    private int rows;

    public CoordinateGrid_2(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < columns && y < rows;
    }

    public int indexOf(int x, int y) {
        if (!inside(x, y)) {
            return -1;
        }
        return y * columns + x;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
