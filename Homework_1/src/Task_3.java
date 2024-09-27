import java.util.Scanner;
public class Task_3
{
    public static void main(String[] args)
    {
        Scanner init = new Scanner(System.in);
        int x_0 = 0;
        int y_0 = 0;
        int x = init.nextInt();
        int y = init.nextInt();
        init.nextLine();

        int totalSteps = 0;
        while (true)
        {
            String direction = init.nextLine();
            if (direction.equals("стоп"))
            {
                break;
            }
            int steps = init.nextInt();
            init.nextLine();

            switch (direction)
            {
                case "север":
                    y_0 += steps;
                    break;
                case "юг":
                    y_0 -= steps;
                    break;
                case "восток":
                    x_0 += steps;
                    break;
                case "запад":
                    x_0 -= steps;
                    break;
            }
            totalSteps++;
        }

        int deltaX = Math.abs(x - x_0);
        int deltaY = Math.abs(y - y_0);

        int minInstructions = 0;

        if (deltaX > 0)
        {
            minInstructions++;
        }
        if (deltaY > 0)
        {
            minInstructions++;
        }

        System.out.println(minInstructions);

        init.close();
    }

}