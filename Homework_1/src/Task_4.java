import java.util.Scanner;

public class Task_4
{
    public static void main(String[] args)
    {
        Scanner init = new Scanner(System.in);

        int N = init.nextInt();
        int[] Roads = new int[N];

        for (int i = 0; i < N; i++)
        {
            int amount_bridge = init.nextInt();
            int mini = Integer.MAX_VALUE;

            for (int j = 0; j < amount_bridge; j++)
            {
                int height = init.nextInt();
                if (height < mini)
                {
                    mini = height;
                }
            }

            Roads[i] = mini;
        }
        int maxHeight = Roads[0];
        int roadNumber = 1;

        for (int i = 1; i < Roads.length; i++)
        {
            if (Roads[i] > maxHeight)
            {
                maxHeight = Roads[i];
                roadNumber = i + 1;
            }
        }

        System.out.println(roadNumber + " " + maxHeight);
        init.close();
    }
}
