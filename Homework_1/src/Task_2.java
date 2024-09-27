import java.util.Scanner;
public class Task_2
{
    public static void main(String[] args)
    {
        Scanner init = new Scanner(System.in);
        int n = init.nextInt();
        if (n <= 0)
        {
            System.out.println("ERROR");
        }
        int Sum = 0;
        for (int i = 0; i < n; i++)
        {
            int a = init.nextInt();
            if (i % 2 == 0)
            {
                Sum = Sum + a;
            }
            else
            {
                Sum = Sum - a;
            }
        }
        System.out.println(Sum);
    }
}
