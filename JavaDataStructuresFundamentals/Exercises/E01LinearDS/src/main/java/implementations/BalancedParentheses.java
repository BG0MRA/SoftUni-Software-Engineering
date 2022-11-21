package implementations;

import interfaces.Solvable;

import java.util.ArrayDeque;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    //{[()]} - This is a balanced parenthesis.
    //{[(])} - This is not a balanced parenthesis.
    @Override
    public Boolean solve() {
        //stack to write the open brackets
        ArrayDeque<Character> bracketsStack = new ArrayDeque<>();
        boolean isBalanced = false;

        //reading the brackets from the input String
        for (int i = 0; i < parentheses.length(); i++) {
            char symbol = parentheses.charAt(i);

            if ((symbol == '{') || (symbol == '[') || (symbol == '(')) {
            //if Symbol is OpenBracket put it in Stack
                bracketsStack.push(symbol);
            } else if ((symbol == '}') || (symbol == ']') || (symbol == ')')) {
                //if Symbol is ClosingBracket compare it
                if (bracketsStack.isEmpty()) {
                //if Stack is Empty - return false
                    return false;
                } else {
                    //Else return the bracket from the Stack and compare it.
                    char currentSymbol = bracketsStack.pop();
                    if (validateSymbol(symbol, currentSymbol)) {
                        isBalanced = true;
                    } else {
                        //Symbol is not valid return false
                        return false;
                    }

                }
            }
        }

        return isBalanced;
    }

    private boolean validateSymbol(char symbol, char currentSymbol) {
        if (symbol == '}') {
            return currentSymbol == '{';
        } else if (symbol == ']') {
            return currentSymbol == '[';
        } else if (symbol == ')') {
            return currentSymbol == '(';
        }
        return false;
    }

}
