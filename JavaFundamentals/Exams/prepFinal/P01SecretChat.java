package prepFinal;

import java.util.Scanner;
import java.util.regex.Pattern;

public class P01SecretChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //On the first line, you will receive a string with a message.
        //heVVodar!gniV
        String inputWord = scanner.nextLine();

        String token = scanner.nextLine();

        while (!token.equals("Reveal")) {
            ///heVVodar!gniV
            //InsertSpace:|:5
            String command = token.split(":\\|:")[0];

            if (command.equals("InsertSpace")) {
                int atIndex = Integer.parseInt(token.split(":\\|:")[1]);
                StringBuilder builder = new StringBuilder(inputWord);

                builder.insert(atIndex, " ");
                inputWord = builder.toString();
                System.out.println(inputWord);
            } else if (command.equals("Reverse")) {
                //Reverse:|:!gnil
                //hellodar!gnil -> !gnil -> ling!
                String toReverse = token.split(":\\|:")[1];
                if (inputWord.contains(toReverse)) {
                    StringBuilder reverseWord = new StringBuilder(toReverse);
                    String replacingWord = reverseWord.reverse().toString();
                    inputWord = inputWord.replaceFirst(Pattern.quote(toReverse), "") + replacingWord;

                    System.out.println(inputWord);
                } else {
                    System.out.println("error");
                }

            } else if (command.equals("ChangeAll")) {
                //"ChangeAll:|:{substring}:|:{replacement}"
                String subString = token.split(":\\|:")[1];
                String replacement = token.split(":\\|:")[2];

                inputWord = inputWord.replaceAll(subString,replacement);
                System.out.println(inputWord);
            }


            token = scanner.nextLine();
        }
        System.out.printf("You have a new text message: %s\n", inputWord);
    }
}
