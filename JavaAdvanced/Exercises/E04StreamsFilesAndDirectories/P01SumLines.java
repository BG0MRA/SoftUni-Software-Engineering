package E04StreamsFilesAndDirectories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P01SumLines {
    public static void main(String[] args) {

        String path = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/input.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String readLine = bufferedReader.readLine();

            while (readLine != null) {
                long lineResult = 0;
                for (char symbol : readLine.toCharArray()) {
                    lineResult += symbol;
                }
                System.out.println(lineResult);

                readLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
