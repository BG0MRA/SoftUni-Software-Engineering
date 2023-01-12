package E01StacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P08InfixToPostfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] expression = scanner.nextLine().split(" ");
        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> queue = new ArrayDeque<>();

        for (int i = 0; i < expression.length; i++) {
            String currentElement = expression[i];
            //'(' -> отива директно в stack
            // ')' -> pop-ва всичко от stack-a до достигане на '(' и всичко pop-нато без скобата се добавя в queue
            // '*' или '/' -> докато елеметът на върха stack-a е '*' или '/', той се pop-ва и добавя в queue,
            // след това настоящия елемент се добавя в stack-a
            // '+' '-' ->  докато елеметът на върха stack-a е '*', '/', '+' или '-' той се pop-ва и добавя в queue,
            //след това настоящия елемент се добавя в stack-a
            //цифра -> добавя се в опашката
            //накрая всичко останало в Stack-a се pop-ва и добавя в опашката
            String topStackElement = "";
            switch (currentElement) {
                case "(":
                    stack.push(currentElement);
                    break;
                case ")":
                    topStackElement = stack.pop();
                    while (!topStackElement.equals("(")) {
                        queue.offer(topStackElement);
                        topStackElement = stack.pop();
                    }
                    break;
                case "*":
                case "/":
                    while (!stack.isEmpty()) {
                        topStackElement = stack.peek();
                        if (topStackElement.equals("*") ||
                                topStackElement.equals("/")) {
                            queue.offer(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(currentElement);
                    break;
                case "+":
                case "-":
                    while (!stack.isEmpty()) {
                        topStackElement = stack.peek();
                        if (topStackElement.equals("*") ||
                                topStackElement.equals("/") ||
                                topStackElement.equals("+") ||
                                topStackElement.equals("-")) {
                            queue.offer(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(currentElement);
                    break;
                default:
                    //currentElement is number
                    queue.offer(currentElement);
                    break;
            }
        }

        while (!stack.isEmpty()) {
            queue.offer(stack.pop());
        }

        for (String element : queue) {
            System.out.printf("%s ", element);
        }
    }
}
