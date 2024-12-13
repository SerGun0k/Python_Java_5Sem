import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class task_5
{
    public static List<int[]> findAllPairsWithSum(int[] nums, int target)
    {
        HashSet<Integer> seen = new HashSet<>();
        List<int[]> result = new ArrayList<>();

        for (int num : nums)
        {
            int complement = target - num;
            if (seen.contains(complement))
            {
                result.add(new int[] {complement, num});
            }

            seen.add(num);
        }

        return result;
    }

    public static void printPairs(List<int[]> pairs)
    {
        if (pairs.isEmpty())
        {
            System.out.println("Пары не найдены");
        }
        else
        {
            for (int[] pair : pairs)
            {
                System.out.println("Найдена пара: " + pair[0] + ", " + pair[1]);
            }
        }
    }

    public static void main(String[] args)
    {
        int[] nums = {10, 15, 3, 7, 4, 6, 3, 2};
        int target = 17;

        List<int[]> pairs = findAllPairsWithSum(nums, target);

        printPairs(pairs);
    }
}
