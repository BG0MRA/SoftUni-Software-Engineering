package E04StreamsFilesAndDirectories;

import java.io.File;
import java.util.ArrayDeque;

public class P08GetFolderSize {
    public static void main(String[] args) {

        File folder = new File("/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/Exercises Resources");
        File[] arr = folder.listFiles();

        int folderSize = 0;
        ArrayDeque<File> files = new ArrayDeque<>();
        files.offer(folder);

        while (!files.isEmpty()) {
            File currentFile = files.poll();
            File[] nestedFiles = currentFile.listFiles();
            for (File nestedFile : nestedFiles) {
                if (nestedFile.isDirectory()) {
                    files.offer(nestedFile);
                } else {
                    folderSize += nestedFile.length();
                }
            }
        }

        System.out.println("Folder size: " + folderSize);


    }
}
