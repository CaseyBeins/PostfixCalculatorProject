package com.example.postfixcalculatorprogram;

import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PostfixCalculator {

    public int evaluatePostfix(String postfixExpression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfixExpression.split("\\s+"); //******

        for (String token : tokens) { //*******
            if (token.matches("-?\\d+")) { //*******
                stack.push(Integer.parseInt(token)); //******
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();

                switch (token) { //******
                    case "+": //******
                        stack.push(operand1 + operand2);
                        break;
                    case "-": //********
                        stack.push(operand1 - operand2);
                        break;
                    case "*": //******
                        stack.push(operand1 * operand2);
                        break;
                    case "/": //********
                        stack.push(operand1 / operand2);
                        break;
                    case "%": //******
                        stack.push(operand1 % operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token); //*******
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


        String expression1 = "4 2 * 3 +";
        System.out.println("Result 1: " + calculator.evaluatePostfix(expression1));


        String expression2 = "5 3 + 7 *";
        System.out.println("Result 2: " + calculator.evaluatePostfix(expression2));


        String expression3 = "4 2 * +";
        try {
            System.out.println("Result 3: " + calculator.evaluatePostfix(expression3));
        } catch (Exception e) {
            System.out.println("Error: Invalid postfix expression");
        }


        calculator.evaluateFromFile("expressions.txt");
    }
}
