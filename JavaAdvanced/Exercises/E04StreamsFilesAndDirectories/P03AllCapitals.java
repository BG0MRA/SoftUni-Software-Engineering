package E04StreamsFilesAndDirectories;

import java.io.*;

public class P03AllCapitals {
    public static void main(String[] args) {

        String path = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/input.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
             PrintWriter writer = new PrintWriter(new FileWriter("/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/all-capitals-out.txt"))) {
            bufferedReader.lines().forEach(line -> writer.println(line.toUpperCase()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
