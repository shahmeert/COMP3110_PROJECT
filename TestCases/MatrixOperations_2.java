
public class MatrixOperations_2 {

    // same behavior as _1 but with loop variable renames and order tweaks
    public int[][] add(int[][] left, int[][] right) {
        int rows = left.length;
        int cols = left[0].length;
        int[][] result = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                result[row][col] = left[row][col] + right[row][col];
            }
        }
        return result;
    }

    public int[][] scalarMultiply(int[][] matrix, int factor) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int[][] result = new int[rowCount][colCount];

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                result[r][c] = matrix[r][c] * factor;
            }
        }
        return result;
    }

    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
