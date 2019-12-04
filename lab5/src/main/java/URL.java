package main.java;

import org.apache.log4j.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.util.*;

class ResourceManager {
    private ResourceBundle resourceBundle;
    private final String resourceName = "property\\text";
    ResourceManager() throws IOException {
        FileInputStream fis = new FileInputStream(resourceName + ".property");
        resourceBundle = new PropertyResourceBundle(fis);
    }
    public void changeResource(Locale locale) throws IOException {
        FileInputStream fis = new FileInputStream(resourceName + "_" + locale + ".property");
        resourceBundle = new PropertyResourceBundle(fis);
    }
    public String getString(String key) {
        return resourceBundle.getString(key);
    }
    public Enumeration getSetKey() {
        return resourceBundle.getKeys();
    }

    public void printMenu() {
        System.out.println("1 - english");
        System.out.println("2 - русский");
        System.out.print(">");
    }

    public static char inputCommand() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int com = sc.nextInt();
                if (com < 1 || com > 3)
                    throw new IOException();
                return Character.forDigit(com, 10);
            } catch (IOException exp) {
                System.out.println("Error enter command!!! Repeat\n> ");
            }
        }
    }
    public static Locale getLocale(char command) {
        switch (command) {
            case '1':
                return new Locale("en", "GB");
                case '2':
                    return new Locale("ru", "RU");
                    default:
                        return Locale.getDefault();
        }
    }
}

public class URL {
    private String url = "https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0";
    private Document html;
    static Logger logger = LogManager.getLogger(URL.class);
    static ResourceManager manager;

    static {
        try {
            manager = new ResourceManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    URL(String _url) {
        url = _url;
        try {
            html = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.out.println("Failed to connect to " + url);
            System.exit(1);
        }
    }

    URL() {
        try {
            html = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.out.println("Failed to connect to " + url);
            System.exit(1);
        }
    }

    public Map<String, Integer> countTags() {
        Elements elements = html.getAllElements();
        List<String> tags = new ArrayList<>();
        for (Element e : elements) {
            tags.add(e.tag().toString());
        }
        Map<String, Integer> frequencyOfTags = new HashMap<>();
        tags.remove("#root");
        for (String str : tags) {
            if (frequencyOfTags.containsKey(str))
                frequencyOfTags.compute(str, (k, v) -> v + 1);
            else
                frequencyOfTags.put(str, 1);
        }
        return frequencyOfTags;
    }

    public static void main(String[] args) throws IOException {
//        try {
//            FileAppender appender = new FileAppender(new SimpleLayout(), "log.txt");
//            logger.addAppender(appender);
//            logger.setLevel(Level.INFO);
//        } catch (IOException e) {
//            logger.error("error i/o", e);
//        }
//        manager.printMenu();
//        char command = manager.inputCommand();
//        Locale locale = manager.getLocale(command);
//        manager.changeResource(locale);
        URL u1 = new URL();
        Map<String, Integer> frequencyOfTags = u1.countTags();
        System.out.println(manager.getString("label1"));//"Sorted by lexicographical oder:"
        frequencyOfTags.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
//        logger.info(manager.getString("label1") + frequencyOfTags.toString());//"Sorted by lexicographical oder:"
        System.out.println();
        System.out.println(manager.getString("label2"));//"Sorted by frequency: "
        frequencyOfTags.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
//        logger.info(manager.getString("label2") + frequencyOfTags.toString());//"Sorted by frequency: "
    }
}
