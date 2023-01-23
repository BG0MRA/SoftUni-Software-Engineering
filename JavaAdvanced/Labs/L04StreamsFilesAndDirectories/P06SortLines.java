package L04StreamsFilesAndDirectories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class P06SortLines {
    public static void main(String[] args) throws IOException {

        // Specific Path location
        String basePath = "\\04. Java-Advanced-Files-and-Streams-Lab-Resources";
        String inputPath = basePath + "\\input.txt";
        String outputPath = basePath + "\\06_output.txt";

        Path path;
        path = Paths.get(inputPath);
        List<String> sortedLines = Files.readAllLines(path)
                .stream()
                .sorted()
                .collect(Collectors.toList());

        Path output = Paths.get(outputPath);
        Files.write(output, sortedLines);
    }
}
