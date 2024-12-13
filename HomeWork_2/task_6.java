public class task_6
{
    public static int sumOfElements(int[][] matrix)
    {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
    public static void main(String[] args)
    {
        int[][] matrix = {
                {-1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };

        int result = sumOfElements(matrix);
        System.out.println("Сумма всех элементов в массиве: " + result);
    }
}
