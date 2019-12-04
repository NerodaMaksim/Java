import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


class Lab7Test {

    @org.junit.jupiter.api.Test
    void equalVowAndCons() {
        String inputLine = "uhbasd fghdbммп uhojag opiujk";
        List<String> expected = new ArrayList<>();
        LatinEqI actualI = inputLine1 -> {
            return Lab7.EqualVowAndCons(inputLine);
        };
        expected.add("uhojag");
        expected.add("opiujk");
        Assert.assertEquals(expected, actualI.findCorrectWords(inputLine));
    }
}