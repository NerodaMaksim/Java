import java.util.Scanner;

class StringAverage
{
    public static String[] averageFunc(String[] inputString)
    {
        float averageLength = 0;
        int generalLength = 0,
                ammount = 0;
        for(String str : inputString)
        {
            generalLength  += str.length();
            ammount++;
        }
        averageLength = (float)generalLength/ammount;
        ammount = 0;
        for(String str : inputString)
        {
            if(averageLength > (float) str.length())
                ammount++;
        }
        String[] outputString;
        outputString = new String[ammount];
        byte i = 0;
        for(String str : inputString)
        {
            if(averageLength > (float)str.length()){
                outputString[i] = str;
                i++;
            }
        }
        return outputString;
    }

    private static String[] inputStringFunc(int numberOfLines)
    {
        String[] str = {"laptop","watch","chair","table","bed","phone","knife","headphones","pillow","door"};
        for(String str1 : str)
        {
            System.out.println(str1);
        }

//        String[] str = new String[numberOfLines];
//        Scanner in = new Scanner(System.in);
//        for(int i = 0; i < numberOfLines; i++)
//            str[i] = in.nextLine();

        return str;
    }

    private static void outputStringFinc(String[] strToOut)
    {
        System.out.println();
        for(String str : strToOut)
        {
            System.out.println(str);
        }
    }

    public static void main(String[] args)
    {
        String[] str = inputStringFunc(10);
        str = averageFunc(str);
        outputStringFinc(str);
    }
}