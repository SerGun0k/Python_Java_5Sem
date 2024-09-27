import java.util.Scanner;
public class Task_5
{
    public static void main(String[] args)
    {
        Scanner init = new Scanner(System.in);
        int number = init.nextInt();

        if (isThreeDigitNumber(number))
        {
            if (isDoubleEven(number))
            {
                System.out.println(number + " является дважды четным числом.");
            }
            else
            {
                System.out.println(number + " не является дважды четным числом.");
            }
        } else {
            System.out.println("ERROR");
        }

        init.close();
    }

    private static boolean isThreeDigitNumber(int number)
    {
        return number >= 100 && number <= 999;
    }

    private static boolean isDoubleEven(int number)
    {
        int sum = 0;
        int product = 1;

        while (number > 0)
        {
            int digit = number % 10;
            sum += digit;
            product *= digit;
            number /= 10;
        }
        return (sum % 2 == 0) && (product % 2 == 0);
    }
}
