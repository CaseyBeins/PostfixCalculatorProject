package com.example.postfixcalculatorprogram;

import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PostfixCalculator {

    public int evaluatePostfix(String postfixExpression) {
        Stack<Integer> stack = new Stack<>();

        for (char ch : postfixExpression.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.push(ch - '0');
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();

                switch (ch) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        stack.push(operand1 / operand2);
                        break;
                    case '%':
                        stack.push(operand1 % operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + ch);
                }
            }
        }
        return stack.pop();
    }

    public void evaluateFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int result = evaluatePostfix(line);
                    System.out.println("Result: " + result);
                } catch (Exception e) {
                    System.out.println("Error: Invalid postfix expression");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PostfixCalculator calculator = new PostfixCalculator();

        // Example 1: Valid Expression
        String expression1 = "42*3+";
        System.out.println("Result 1: " + calculator.evaluatePostfix(expression1));

        // Example 2: Valid Expression
        String expression2 = "53+7*";
        System.out.println("Result 2: " + calculator.evaluatePostfix(expression2));

        // Example 3: Invalid Expression
        String expression3 = "42*+"; // Missing operand
        try {
            System.out.println("Result 3: " + calculator.evaluatePostfix(expression3));
        } catch (Exception e) {
            System.out.println("Error: Invalid postfix expression");
        }

        // Read from file
        calculator.evaluateFromFile("expressions.txt");
    }
}
