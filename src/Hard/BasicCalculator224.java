package Hard;

import java.util.Stack;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 * <p>
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * <p>
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 **/
public class BasicCalculator224 {
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        int result = 0;
        int sign = 1; // 1 for positive, -1 for negative

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0'); // Build the current number
            } else if (c == '+') {
                result += sign * currentNumber; // Add the current number to the result
                currentNumber = 0; // Reset current number
                sign = 1; // Set sign to positive
            } else if (c == '-') {
                result += sign * currentNumber; // Add the current number to the result
                currentNumber = 0; // Reset current number
                sign = -1; // Set sign to negative
            } else if (c == '(') {
                stack.push(result); // Push current result to stack
                stack.push(sign); // Push current sign to stack
                result = 0; // Reset result for the new expression
                sign = 1; // Reset sign to positive
            } else if (c == ')') {
                result += sign * currentNumber; // Add the last number before closing parenthesis
                result *= stack.pop(); // Multiply with the sign before '('
                result += stack.pop(); // Add the result before '('
                currentNumber = 0; // Reset current number
            }
        }

        // Handle the last number after the loop
        result += sign * currentNumber;

        return result;
    }

    public static void main(String[] args) {
        String expression = "1 + 1"; // You can change this expression for testing
        int result = calculate(expression);
        System.out.println("Result: " + result);
    }
}
