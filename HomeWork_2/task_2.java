import java.util.Arrays;

public class task_2
{
    public static int[] mergeSortedArrays(int[] array1, int[] array2)
    {
        int n1 = array1.length;
        int n2 = array2.length;

        int[] mergedArray = new int[n1 + n2];
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2)
        {
            if (array1[i] < array2[j])
            {
                mergedArray[k++] = array1[i++];
            } else
            {
                mergedArray[k++] = array2[j++];
            }
        }

        while (i < n1)
        {
            mergedArray[k++] = array1[i++];
        }

        while (j < n2)
        {
            mergedArray[k++] = array2[j++];
        }

        return mergedArray;
    }

    public static void main(String[] args)
    {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {2, 4, 6, 6, 8};

        int[] mergedArray = mergeSortedArrays(array1, array2);

        System.out.println("Объединённый отсортированный массив: " + Arrays.toString(mergedArray));
    }
}
