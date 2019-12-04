class Pyramid
{
    public static void build(int depth)
    {
        for(int i = 1; i <= depth; i++)
        {
            for(int j = depth - i; j > 0; j--)
                System.out.print(" ");
            for(int j = 1; j <= i; j++)
                System.out.print(j);
            for(int j = i - 1; j > 0; j--)
                System.out.print(j);
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        for(int i = 1; i <= 9; i++)
            build(i);
    }
}