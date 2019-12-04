package main.java;

import java.io.*;
import java.util.Scanner;

public class WorkWithFile implements Serializable {
    private Scanner inputFile;
    private FileWriter outputFile;
    private String inputFilePath;
    private String outputFilePath;
    private char offset = 'u';

    WorkWithFile(String _inputFilePath, String _outputFilePath) throws Exception {
        outputFile = new FileWriter(_outputFilePath);
        FileWriter fw = new FileWriter(_inputFilePath);
        fw.close();
        FileReader fr = new FileReader(_inputFilePath);
        //inputFile = new Scanner(fr);
        inputFilePath = _inputFilePath;
        outputFilePath = _outputFilePath;
    }

    public String longestLineInFile() throws IOException, ClassNotFoundException {
        int max = 0;
        String longestLine = "";
        String line = read();
        String[] lines = line.split("\n");
        for (String str : lines) {
            if (str.length() > max) {
                max = str.length();
                longestLine = str;
            }
        }
        return longestLine;
    }

    public String read() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(inputFilePath));
        Encryptor filter = new Encryptor(new FileReader(inputFilePath));
        return filter.decrypt(in.readObject().toString(), offset);
    }

    public void createInputFile(String str) throws IOException {
        FileWriter fw = new FileWriter(inputFilePath);
        Decryptor filter = new Decryptor(fw);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(inputFilePath));
        out.writeObject(filter.encrypt(str, offset));
        out.close();
    }

    public void write(String str) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFilePath));
        Decryptor filter = new Decryptor(outputFile);
        outputFile.close();
        out.writeObject(filter.encrypt(str, offset));
        out.close();
    }

    public void close() throws IOException {
        outputFile.close();
        inputFile.close();
    }

    public static void main(String[] args) throws Exception {
        String _outputFilePath = "", defaultPath = "output.txt";
        Scanner in = new Scanner(System.in);
        System.out.print("Enter output file path(Default: output.txt)->");
        _outputFilePath = in.nextLine();
        if (_outputFilePath.equalsIgnoreCase(""))
            _outputFilePath = defaultPath;
        try {
            WorkWithFile t1 = new WorkWithFile("input.txt", _outputFilePath);
            t1.createInputFile("In all chaotic beauty lies a wounded work of art.\n" +
                    "Beautiful but torn, wreaking havoc on my heart.\n" +
                    "Camouflaged by insecurities, blinded by it all.\n" +
                    "I love the way you sit there and barely notice me at all.");
            t1.write(t1.longestLineInFile());
            System.out.println(t1.longestLineInFile());
            try {
                t1.close();
            } catch (Exception e) {
                System.out.println("Files already closed ");
            }
        } catch (Exception e) {
            System.out.println("Wrong file path");
        }
    }
}
