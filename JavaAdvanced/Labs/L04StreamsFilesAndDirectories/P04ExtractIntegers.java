package L04StreamsFilesAndDirectories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class P04ExtractIntegers {
    public static void main(String[] args) throws FileNotFoundException {

        // Specific Path location
        String basePath = "\\04. Java-Advanced-Files-and-Streams-Lab-Resources";
        String inputPath = basePath + "\\input.txt";
        String outputPath = basePath + "\\04_output.txt";

        Scanner reader = new Scanner(new FileInputStream(inputPath));
        PrintStream writer = new PrintStream(new FileOutputStream(outputPath));
//        PrintStream writer = System.out;

        while (reader.hasNext()) {
            if (reader.hasNextInt()) {
                writer.println(reader.nextInt());
            }

            reader.next();
        }
    }
}
