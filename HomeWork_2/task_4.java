public class task_4
{
    public static int[][] rotateMatrix(int[][] matrix)
    {
        int n = matrix.length;
        int[][] rotatedMatrix = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rotatedMatrix[j][n - 1 - i] = matrix[i][j];
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
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println("Исходная матрица:");
        printMatrix(matrix);
        int[][] rotated = rotateMatrix(matrix);
        System.out.println("Матрица после поворота на 90 градусов по часовой стрелке:");
        printMatrix(rotated);
    }
}
