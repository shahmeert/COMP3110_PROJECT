public class MatrixOperations_1 {

    public int[][] add(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result[r][c] = a[r][c] + b[r][c];
            }
        }
        return result;
    }

    public int[][] scalarMultiply(int[][] matrix, int scalar) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result[r][c] = matrix[r][c] * scalar;
            }
        }
        return result;
    }

    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int v : row) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
