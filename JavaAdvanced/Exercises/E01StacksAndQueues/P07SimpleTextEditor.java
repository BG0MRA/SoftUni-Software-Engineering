package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P07SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder text = new StringBuilder();
        ArrayDeque<String> commands = new ArrayDeque<>();
        ArrayDeque<String> removedElements = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String rawCommand = scanner.nextLine();
            String[] commandArguments = rawCommand.split(" ");
            String commandType = commandArguments[0];

            switch (commandType) {
                case "1": //"1 {string}" - appends [string] to the end of the text.
                    commands.push(rawCommand);
                    executeAdd(commandArguments[1], text);
                    break;
                case "2"://"2 {count}" - erases the last [count] elements from the text.
                    commands.push(rawCommand);
                    int countToDelete = Integer.parseInt(commandArguments[1]);
                    StringBuilder elementsToBeDeleted = new StringBuilder();
                    for (int j = text.length() - countToDelete; j < text.length(); j++) {
                        elementsToBeDeleted.append(text.charAt(j));
                    }
                    removedElements.push(elementsToBeDeleted.toString());
                    executeDelete(countToDelete, text);
                    break;
                case "3"://"3 {index}" - returns the element at position [index] from the text.
                    int index = Integer.parseInt(commandArguments[1]);
                    System.out.printf("%s%n", text.charAt(index - 1));
                    break;
                case "4"://	"4" - undoes the last not-undone command of type 1 or 2 and
                    // returns the text to the state before that operation.
                    String lastCommand = commands.pop();
                    String[] lastCommandArguments = lastCommand.split(" ");
                    String lastCommandType = lastCommandArguments[0];
                    switch (lastCommandType) {
                        case "1":
                            //Предишната команда е била добавяне
                            //Сега трябва да премахнем
                            int elementsToDelete = lastCommandArguments[1].length();
                            executeDelete(elementsToDelete, text);
                            break;
                        case "2":
                            //Предишната команда е била изтриване
                            //Сега трябва да добавим
                            String elementsToAddBack = removedElements.pop();
                            executeAdd(elementsToAddBack, text);
                            break;
                    }
                    break;
            }

//            System.out.println(text.toString());
        }
    }

    private static void executeDelete(int countToDelete, StringBuilder text) {
        for (int i = 1; i <= countToDelete; i++) {
            text.deleteCharAt(text.length() - 1);
        }
    }

    private static void executeAdd(String commandArgument, StringBuilder text) {
        text.append(commandArgument);
    }

}
