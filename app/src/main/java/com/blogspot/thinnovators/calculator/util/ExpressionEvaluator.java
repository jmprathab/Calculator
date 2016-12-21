package com.blogspot.thinnovators.calculator.util;

import java.util.Stack;

/**
 * A class which is used to evaluate arithmetic expressions
 */

public class ExpressionEvaluator {
    /* Create stacks for operators and operands */
    private static Stack<Integer> operatorStack = new Stack<>();
    private static Stack<Double> operandStack = new Stack<>();

    /* Create temporary stacks for operators and operands */
    private static Stack<Integer> operatorTemporaryStack = new Stack<>();
    private static Stack<Double> operandTemporaryStack = new Stack<>();

    /**
     * @param input the input arithmetic expression which will be evaluated
     * @return a double value of the expression which is evaluated
     * @throws ArithmeticException if given arithmetic expression is not well formed Eg: "34/*56"
     */

    public static double evaluateExpression(String input) throws ArithmeticException {
        operatorStack.clear();
        operandStack.clear();
        operatorTemporaryStack.clear();
        operandTemporaryStack.clear();

        try {


            input = "0" + input;
            input = input.replaceAll("-", "+-");

        /* Store operands and operators in respective stacks */
            String temp = "";
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (ch == '-')
                    temp = "-" + temp;
                else if (ch != '+' && ch != '*' && ch != '/')
                    temp = temp + ch;
                else {
                    operandStack.push(Double.parseDouble(temp));
                    operatorStack.push((int) ch);
                    temp = "";
                }
            }
            operandStack.push(Double.parseDouble(temp));

        /* Create char array of operators as per precedence */
        /* -ve sign is already taken care of while storing */
            char operators[] = {'/', '*', '+'};
        /* Evaluation of expression */
            for (int i = 0; i < 3; i++) {
                boolean it = false;
                while (!operatorStack.isEmpty()) {
                    int operator = operatorStack.pop();
                    double v1 = operandStack.pop();
                    double v2 = operandStack.pop();
                    if (operator == operators[i]) {
                    /* if operator matches evaluate and store in temporary stack */
                        if (i == 0) {
                            operandTemporaryStack.push(v2 / v1);
                            it = true;
                            break;
                        } else if (i == 1) {
                            operandTemporaryStack.push(v2 * v1);
                            it = true;
                            break;
                        } else if (i == 2) {
                            operandTemporaryStack.push(v2 + v1);
                            it = true;
                            break;
                        }
                    } else {
                        operandTemporaryStack.push(v1);
                        operandStack.push(v2);
                        operatorTemporaryStack.push(operator);
                    }
                }
            /* Push back all elements from temporary stacks to main stacks */
                while (!operandTemporaryStack.isEmpty())
                    operandStack.push(operandTemporaryStack.pop());
                while (!operatorTemporaryStack.isEmpty())
                    operatorStack.push(operatorTemporaryStack.pop());
            /* Iterate again for same operator */
                if (it) {
                    i--;
                }
            }
        } catch (ArithmeticException e) {
            throw e;
        }
        return operandStack.pop();
    }

}
