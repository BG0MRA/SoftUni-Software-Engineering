package L05Polymorphism.Demos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MethodOverriding {
    public static void main(String[] args) {

    }

    public static class Parser {
        private String fileName;

        public Parser(String fileName) {
            this.fileName = fileName;
        }

        public int parse() throws FileNotFoundException {
            Scanner scanner = new Scanner(new FileInputStream(fileName));
            return Integer.parseInt(scanner.nextLine());
        }
    }

    public static class ParserV2 extends Parser {

        public ParserV2(String fileName) {
            super(fileName);
        }

        @Override
        public int parse () {
            return 13;
        }
    }
}
