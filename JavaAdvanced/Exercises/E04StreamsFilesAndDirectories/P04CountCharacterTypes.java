package E04StreamsFilesAndDirectories;

import java.io.*;
import java.util.Set;

public class P04CountCharacterTypes {
    public static void main(String[] args) {

        String path = "/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/input.txt";

        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        //•	a, e, i, o, u are vowels, only lower case.

        Set<Character> punctuationMarks = Set.of('!', ',', '.', '?');
        //•	Punctuation marks are (! , . ?).

        //•	All others are consonants.
        //•	Do not count whitespace.

        int countVowels = 0;
        int countPunctuationMarks = 0;
        int countConsonants = 0;

        try (FileReader fr = new FileReader(path);
             PrintWriter pw = new PrintWriter(new FileWriter("/Users/bazzalth/Desktop/SoftUni/Java Advanced/Exercises/E04Streams, Files and Directories/04. Java-Advanced-Files-and-Streams-Exercises-Resources/count-chars-out.txt"))) {

            int oneSymbol = fr.read();

            while (oneSymbol != -1) {
                char symbolAsChar = (char) oneSymbol;
                if (vowels.contains(symbolAsChar)) {
                    countVowels++;

                } else if (punctuationMarks.contains(symbolAsChar)) {
                    countPunctuationMarks++;

                } else if (!Character.isWhitespace(symbolAsChar)) {
                    countConsonants++;
                }

                oneSymbol = fr.read();
            }

            pw.println("Vowels: "+countVowels);
            pw.println("Other symbols: "+countConsonants);
            pw.println("Punctuation: "+countPunctuationMarks);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        //Vowels: 41
        //Other symbols: 72
        //Punctuation: 6


    }
}
