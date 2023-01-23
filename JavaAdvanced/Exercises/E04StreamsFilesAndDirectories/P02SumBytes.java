package E04StreamsFilesAndDirectories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P02SumBytes {
    public static void main(String[] args) throws IOException {

        String path = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/input.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String readLine = bufferedReader.readLine();
            long result = 0;
            while (readLine != null) {
                for (char symbol : readLine.toCharArray()) {
                    result += symbol;
                }
                readLine = bufferedReader.readLine();
            }

            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
