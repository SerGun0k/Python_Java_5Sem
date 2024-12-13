public class task_7
{
    public static int[] findMaxInRows(int[][] matrix)
    {
        int[] maxInRows = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++)
        {
            int max = matrix[i][0];

            for (int j = 1; j < matrix[i].length; j++)
            {
                if (matrix[i][j] > max)
                {
                    max = matrix[i][j];
                }
            }
            maxInRows[i] = max;
        }
        return maxInRows;
    }

    public static void main(String[] args)
    {
        int[][] matrix = {
                {1, 5, 3},
                {4, 9, 2},
                {7, 6, 8}
        };
        int[] result = findMaxInRows(matrix);
        System.out.print("Максимальные элементы в каждой строке: ");
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}
