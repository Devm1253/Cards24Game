
package zex.com;

import java.util.ArrayList;

public class Calculate {

    private String expression;
    private int position;

    public double evaluate(String input) {
        expression = input.replaceAll("\\s+", "");
        position = 0;

        double result = calcAddSum();

        if (position != expression.length()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return result;
    }

    private double calcAddSum() {
        double result = calcMulDiv();

        while (position < expression.length()) {
            char operator = expression.charAt(position);

            if (operator == '+') {
                position++;
                result += calcMulDiv();
            } else if (operator == '-') {
                position++;
                result -= calcMulDiv();
            } else {
                break;
            }
        }

        return result;
    }

    private double calcMulDiv() {
        double result = calcFac();

        while (position < expression.length()) {
            char operator = expression.charAt(position);

            if (operator == '*') {
                position++;
                result *= calcFac();
            } else if (operator == '/') {
                position++;
                double divisor = calcFac();

                if (divisor == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }

                result /= divisor;
            } else {
                break;
            }
        }

        return result;
    }

    private double calcFac() {
        if (position >= expression.length()) {
            throw new IllegalArgumentException("Incomplete expression");
        }

        char current = expression.charAt(position);

        if (current == '(') {
            position++;
            double result = calcAddSum();

            if (position >= expression.length()
                    || expression.charAt(position) != ')') {
                throw new IllegalArgumentException("Missing closing parenthesis");
            }

            position++;
            return result;
        }

        if (current == '-') {
            position++;
            return -calcFac();
        }

        int start = position;

        while (position < expression.length()
                && Character.isDigit(expression.charAt(position))) {
            position++;
        }

        if (start == position) {
            throw new IllegalArgumentException("Invalid number or operator");
        }

        return Double.parseDouble(expression.substring(start, position));
    }

    private static class NumberExpression {
        double value;
        String expression;

        NumberExpression(double value, String expression) {
            this.value = value;
            this.expression = expression;
        }
    }



}
