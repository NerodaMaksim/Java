import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab7 {

    static List<String> EqualVowAndCons(String inputLine) {
        List<String> arr = Arrays.asList(inputLine.split(" "));
        List<String> outputArray = new ArrayList<>();
        arr.forEach(x->{
            if(conditionPassed(x))
                outputArray.add(x);
        });
        return outputArray;
    }

    private static boolean conditionPassed(String str){
        int vowels = 0,
                consonant = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.toLowerCase().charAt(i) < 'a' && str.toLowerCase().charAt(i) > 'z')
                continue;
            else if (str.toLowerCase().charAt(i) == 'a' || str.toLowerCase().charAt(i) == 'e' || str.toLowerCase().charAt(i) == 'i' || str.toLowerCase().charAt(i) == 'o' || str.toLowerCase().charAt(i) == 'u')
                vowels++;
            else
                consonant++;
        }
        return vowels == consonant;
    }
}
