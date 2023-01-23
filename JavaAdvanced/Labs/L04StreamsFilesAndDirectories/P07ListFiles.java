package L04StreamsFilesAndDirectories;

import java.io.File;

public class P07ListFiles {
    public static void main(String[] args) {

        // Specific Path location
        String folderPath = "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\Files-and-Streams";

        File file = new File(folderPath);

        File[] nestedFiles = file.listFiles();

        for (File nestedFile : nestedFiles) {
            if (nestedFile.isFile()) {
                System.out.printf("%s: [%d]%n", nestedFile.getName(), nestedFile.length());
            }
        }
    }
}
