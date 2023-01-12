package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P05BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String brackets = scanner.nextLine();

        ArrayDeque<Character> openingBrackets = new ArrayDeque<>();
        boolean isBalances = true;
        for (int i = 0; i < brackets.length(); i++) {
            char currentBracket = brackets.charAt(i);
            if (currentBracket == '{' || currentBracket == '[' || currentBracket == '(') {
                openingBrackets.push(currentBracket);
            } else {
                if (openingBrackets.isEmpty()) {
                    isBalances = false;

                } else {
                    char lastOpeningBracket = openingBrackets.pop();
                    if (currentBracket == '}' && lastOpeningBracket != '{') {
                        //not balanced
                        isBalances = false;
                    } else if (currentBracket == ']' && lastOpeningBracket != '[') {
                        //not balanced
                        isBalances = false;
                    } else if (currentBracket == ')' && lastOpeningBracket != '(') {
                        //not balanced
                        isBalances = false;
                    }
                }
                if (!isBalances) {
                    break;
                }
            }
        }

        if (isBalances && openingBrackets.isEmpty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}
