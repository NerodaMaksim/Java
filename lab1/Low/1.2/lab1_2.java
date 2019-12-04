import java.util.Arrays;

class DifferentCharacters
{
    public static String StringFilter(String inputString)
    {
        int i = 0;
        int[] min = {0,1000}; // 0 - first char of word, 1 - difference of word
        String str = "";
        while(i < inputString.length())
        {
            while (i < inputString.length() && !Character.isWhitespace(inputString.charAt(i))) {//inputString.charAt(i) != ' '
                str += inputString.charAt(i);
                i++;

            }
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            str = new String(chars);
            str.toUpperCase();
            int difference = 0;
            for (int j = 1; j <+ str.length(); j++) {
                if (str.charAt(j-1) != str.charAt(j))
                    difference++;
            }
            if (min[1] > difference) {
                min[0] = i - str.length();
                min[1] = difference;
            }
            str = "";
            i++;
        }
        i = min[0];
        while(!Character.isWhitespace(inputString.charAt(i))) {
            str += inputString.charAt(i);
            i++;
        }
        return str;
    }

    public static void main(String[] args)
    {
        String str = "asdf bbb aaaa vbfr bfbh";
        str = StringFilter(str);
        System.out.println(str);
    }

}