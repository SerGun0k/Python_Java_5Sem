public class task_3
{
    public static int maxSubArraySum(int[] nums)
    {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++)
        {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args)
    {
        int[] nums = {-2, 1, -3, 4, -1, -6, 2, 1, -5, 1};
        int result = maxSubArraySum(nums);
        System.out.println("Максимальная сумма подмассива: " + result);
    }
}
