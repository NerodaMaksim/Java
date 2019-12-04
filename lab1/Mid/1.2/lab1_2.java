import java.util.Arrays;

class Perfect
{
    public static int[] findPerfect(int lastNumber)
    {
        int[] perfectNumbers = new int[0];
        int amount = 0;
        for(int i = 1; i <= lastNumber; i++)
        {
            int sum = 0;
            for(int j = 1; j < i; j++)
            {
                if(i % j == 0)
                    sum += j;
            }
            if(sum == i) {
                perfectNumbers = Arrays.copyOf(perfectNumbers, perfectNumbers.length + 1);
                perfectNumbers[amount] = i;
                amount++;
            }
        }
        return perfectNumbers;
    }

    public static void main(String[] args)
    {
        int[] arr = findPerfect(10000);
        for (int a: arr) {
            System.out.print(a + " ");
        }
    }
}