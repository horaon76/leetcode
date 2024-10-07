package BITS;
/**
 *
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = 2, b = 3
 * Output: 5
 *
 *
 * Constraints:
 *
 * -1000 <= a, b <= 1000
 *
 * Approach
 * Bitwise AND (&): This operation will help identify the carry bits. For each pair of bits from the two integers, if both bits are 1, a carry will be generated.
 *
 * Bitwise XOR (^): This operation will compute the sum of the bits without considering the carry. It will sum the bits where at least one of the bits is 1.
 *
 * Left Shift (<<): This operation will shift the carry to the left by one position, which is necessary because the carry affects the next higher bit.
 *
 * Algorithm
 * Initialize a and b as the two input integers.
 * Use a loop until b becomes 0:
 * Calculate the carry: carry = a & b
 * Calculate the sum without carry: a = a ^ b
 * Shift the carry to the left: b = carry << 1
 * Once b is 0, a contains the result.
 * **/
public class SumOfInteger371 {

    public static int getSum(int a, int b) {
        while (b != 0) {
            // Calculate carry
            int carry = a & b;
            // Calculate sum without carry
            a = a ^ b;
            // Shift carry to left
            b = carry << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("Sum of 1 and 2: " + getSum(1, 2)); // Output: 3
        System.out.println("Sum of -2 and 3: " + getSum(-2, 3)); // Output: 1
    }
}

/**
 *
 * ### Initial Values
 *
 * - \( a = 1 \)  (in binary: `0000 0001`)
 * - \( b = 2 \)  (in binary: `0000 0010`)
 *
 * ### Iteration 1
 *
 * 1. **Calculate Carry:**
 *    - \( \text{carry} = a \& b = 0000 0001 \& 0000 0010 = 0000 0000 \)
 *
 * 2. **Calculate Sum without Carry:**
 *    - \( a = a \oplus b = 0000 0001 \oplus 0000 0010 = 0000 0011 \) (which is `3`)
 *
 * 3. **Shift Carry to Left:**
 *    - \( b = \text{carry} << 1 = 0000 0000 << 1 = 0000 0000 \)
 *
 * ### Values after Iteration 1
 *
 * - \( a = 3 \)  (in binary: `0000 0011`)
 * - \( b = 0 \)  (in binary: `0000 0000`)
 *
 * ### Exit Condition
 *
 * Since \( b = 0 \), we exit the loop.
 *
 * ### Final Result
 *
 * The final value of \( a \) is \( 3 \). Thus, the sum of \( 1 \) and \( 2 \) is \( 3 \).
 *
 * ### Summary of Dry Run Steps
 *
 * | Step | Operation                     | a (Binary)      | b (Binary)      | Carry (Binary) | Result (Binary) |
 * |------|-------------------------------|------------------|------------------|-----------------|------------------|
 * | 1    | Initial Values                | `0000 0001`      | `0000 0010`      | -               | -                |
 * | 2    | Calculate Carry                | `0000 0001`      | `0000 0010`      | `0000 0000`     | -                |
 * | 3    | Calculate Sum without Carry    | `0000 0011`      | `0000 0010`      | -               | `0000 0011`      |
 * | 4    | Shift Carry to Left            | `0000 0011`      | `0000 0000`      | -               | -                |
 * | 5    | Exit Loop                     | `0000 0011`      | `0000 0000`      | -               | -                |
 *
 * The final output of the function `getSum(1, 2)` is `3`.
 *
 * ### Explanation of Each Step
 *
 * - **Step 1**: Initialize the input values for \( a \) and \( b \).
 * - **Step 2**: Calculate the carry bits where both \( a \) and \( b \) have `1`s.
 * - **Step 3**: Calculate the intermediate sum of \( a \) and \( b \) without considering the carry.
 * - **Step 4**: Move the carry to the next higher bit.
 * - **Step 5**: Since \( b \) is now `0`, the loop exits, and the sum is stored in \( a \).
 *
 * This process efficiently computes the sum of the two integers using only bitwise operations.
 * **/