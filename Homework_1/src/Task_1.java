import java.util.Scanner;
public class Task_1
{
    public static void main(String[] args)
    {
        Scanner init = new Scanner(System.in);

        int n = init.nextInt();
        if (n <= 0)
        {
            return;
        }
        int steps = 0;
        while (n != 1)
        {
            if (n % 2 == 0)
            {
                n = n / 2;
            }
            else
            {
                n = 3 * n + 1;
            }
            steps++;
        }

        System.out.println(steps);

        init.close();
    }
}
