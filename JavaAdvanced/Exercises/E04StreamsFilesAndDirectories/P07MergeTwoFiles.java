package E04StreamsFilesAndDirectories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class P07MergeTwoFiles {
    public static void main(String[] args) {
        //В1 четем със Scanner и пишем с PrintWriter (with append!)
        //PrintWriter pw = new PrintWriter(new FileWriter(path,true))

        Path input1 = Paths.get("/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/inputOne.txt");
        Path input2 = Paths.get("/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/inputTwo.txt");
        Path output = Paths.get("/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/merge-two-files-out.txt");

        try {
            List<String> firstFile = Files.readAllLines(input1);
            List<String> secondFile = Files.readAllLines(input2);

            Files.write(output, firstFile, StandardOpenOption.CREATE);
            Files.write(output, secondFile, StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
