import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {

    @org.junit.jupiter.api.Test
    void changeWithUsingReflection() {
        Reflection reflectionStr = new Reflection();
        String oldStr = "Old string";
        String newStr = "New string";
        reflectionStr.initialize(oldStr);
        String actual = reflectionStr.changeWithUsingReflection(newStr);
        Assert.assertTrue(actual.equals(newStr) && !actual.equals(oldStr));
    }
}