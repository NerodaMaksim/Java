import java.lang.reflect.Field;
import java.util.Scanner;

public class Reflection {
    private String strForReflection;

    String initialize(String str) {
        strForReflection = str;
        return strForReflection;
    }

    String initialize() {
        System.out.print("Enter string->");
        Scanner in = new Scanner(System.in);
        strForReflection = in.nextLine();
        return strForReflection;
    }

    String changeWithUsingReflection(String str) {
        String string = initialize(str);
        try {
            Field field = strForReflection.getClass().getDeclaredField("value");
            field.setAccessible(true);
            field.set(strForReflection, string.getBytes());
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return strForReflection;
    }

    String changeWithUsingReflection() {
        String string = initialize();
        try {
            Field field = strForReflection.getClass().getDeclaredField("value");
            field.setAccessible(true);
            field.set(strForReflection, string.getBytes());
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return strForReflection;
    }

    public static void main(String[] args) {
        Reflection reflectionStr = new Reflection();
        System.out.println("Initialized string:");
        System.out.println(reflectionStr.initialize());
        System.out.println("String to change:");
        System.out.println(reflectionStr.changeWithUsingReflection());
    }
}
