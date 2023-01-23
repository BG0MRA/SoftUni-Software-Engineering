package E04StreamsFilesAndDirectories;

import java.io.*;

public class P05LineNumbers {
    public static void main(String[] args) {

        String path = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/inputLineNumbers.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path));
             PrintWriter pw = new PrintWriter(new FileWriter("/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/line-numbers-out.txt"))) {
            String line = br.readLine();
            int counter = 0;
            while (line != null) {
                counter++;
                String numberedLine = counter + ". " + line;
                pw.println(numberedLine);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
