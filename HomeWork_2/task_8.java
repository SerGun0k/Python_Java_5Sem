public class task_8
{
    public static int[][] rotateMatrixCounterClockwise(int[][] matrix)
    {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] rotatedMatrix = new int[m][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                rotatedMatrix[m - 1 - j][i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }
    public static void printMatrix(int[][] matrix)
    {
        for (int[] row : matrix)
        {
            for (int element : row)
            {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args)
    {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        int[][] rotated = rotateMatrixCounterClockwise(matrix);
        System.out.println("Матрица после поворота на 90 градусов против часовой стрелки:");
        printMatrix(rotated);
    }
}
