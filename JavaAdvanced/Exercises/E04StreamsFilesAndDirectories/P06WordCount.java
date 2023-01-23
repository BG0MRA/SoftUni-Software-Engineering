package E04StreamsFilesAndDirectories;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class P06WordCount {
    public static void main(String[] args) {

        String path1 = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/words.txt";
        String path2 = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/text.txt";
        String path3 = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/word-count-out.txt";
        //1.прочитаме думите, които ще търсим
        //2.прочитаме дума по дума втория файл
        //3.записваме резултата в няколко реда в трети файл

        try (Scanner scan1 = new Scanner(new FileReader(path1));
             Scanner scan2 = new Scanner(new FileReader(path2));
             PrintWriter pw = new PrintWriter(new FileWriter(path3))) {

            LinkedHashMap<String, Integer> wordMap = new LinkedHashMap<>();
            Arrays.stream(scan1.nextLine().split("\\s+")).forEach(word ->
                    wordMap.put(word, 0));

            String textInput = scan2.next();
            while (scan2.hasNext()) {
                if (wordMap.containsKey(textInput)) {
                    int occurrence = wordMap.get(textInput) + 1;
                    wordMap.put(textInput, occurrence);
                }
                textInput = scan2.next();
            }

            wordMap.entrySet().forEach(entry -> pw.printf("%s - %d\n",entry.getKey(),entry.getValue()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
