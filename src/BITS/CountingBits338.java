package BITS;
/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 *
 * Constraints:
 *
 * 0 <= n <= 105
 *
 *
 * Follow up:
 *
 * It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
 * Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 * **/

/**
 * Input: n = 5
 * Output: [0, 1, 1, 2, 1, 2]
 * Explanation:
 * 0 in binary is 0 (0 bits set)
 * 1 in binary is 1 (1 bit set)
 * 2 in binary is 10 (1 bit set)
 * 3 in binary is 11 (2 bits set)
 * 4 in binary is 100 (1 bit set)
 * 5 in binary is 101 (2 bits set)
 * **/
public class CountingBits338 {
    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] result = countBits(n);

        System.out.print("The count of bits for numbers from 0 to " + n + " is: ");
        for (int count : result) {
            System.out.print(count + " ");
        }
        // Output: The count of bits for numbers from 0 to 5 is: 0 1 1 2 1 2
    }
}

/**
 * Filling the Array:
 *
 * We iterate from 0 to n, computing the number of 1 bits for each number using the formula:
 * ans[i] = ans[i >> 1] + (i & 1)
 * Here, ans[i >> 1] counts the bits for i/2, and (i & 1) checks if i is odd (adding 1 if it is).
 * **/
/**
 * Time Complexity
 *
 * Time Complexity: O(n), where n is the input number. We iterate through all numbers from 0 to n.
 * Space Complexity: O(n) for the output array.
 * **/