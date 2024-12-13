import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class task_1
{
    public static String longestUniqueSubstring(String s)
    {
        Set<Character> charSet = new HashSet<>();
        int left = 0;
        String longest = "";

        for (int right = 0; right < s.length(); right++)
        {
            while (charSet.contains(s.charAt(right)))
            {
                charSet.remove(s.charAt(left));
                left++;
            }

            charSet.add(s.charAt(right));

            if (right - left + 1 > longest.length())
            {
                longest = s.substring(left, right + 1);
            }
        }

        return longest;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        String input = scanner.nextLine();
        String result = longestUniqueSubstring(input);
        System.out.println("Самая длинная подстрока без повторений: " + result);
        scanner.close();
    }
}
