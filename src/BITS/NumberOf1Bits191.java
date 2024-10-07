package BITS;

/**
 * Write a function that takes the binary representation of a positive integer and returns the number of
 * set bits
 * it has (also known as the Hamming weight).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 11
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * The input binary string 1011 has a total of three set bits.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 128
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The input binary string 10000000 has a total of one set bit.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 2147483645
 * <p>
 * Output: 30
 * <p>
 * Explanation:
 * <p>
 * The input binary string 1111111111111111111111111111101 has a total of thirty set bits.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 231 - 1
 * <p>
 * <p>
 * Follow up: If this function is called many times, how would you optimize it?
 **/
public class NumberOf1Bits191 {

    // Function to count number of 1 bits
    public static int hammingWeight(int n) {
        int count = 0;

        // Loop until all bits are checked
        while (n != 0) {
            count += (n & 1); // Check if the least significant bit is 1
            n >>>= 1;         // Unsigned right shift by 1 (>>> to treat n as unsigned)
        }

        return count;
    }

    public static int hammingWeight2(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n - 1); // Removes the lowest set bit
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 11;  // Binary: 00000000000000000000000000001011
        System.out.println("Number of 1 bits: " + hammingWeight(n));  // Output: 3
    }
}
/**
 * while (n != 0): We continue checking bits until all bits are shifted out (i.e., n becomes 0).
 * n & 1: This operation checks the least significant bit. If the bit is 1, we increment the counter.
 * n >>>= 1: We use the unsigned right shift (>>>) to shift the bits of n to the right. This operation ensures that negative numbers are treated as unsigned.
 * <p>
 * For n = 11 (binary 1011):
 * <p>
 * 1011 & 1 = 1 (increment count to 1)
 * Right shift: 1011 >>> 1 -> 101 (binary)
 * 101 & 1 = 1 (increment count to 2)
 * Right shift: 101 >>> 1 -> 10 (binary)
 * 10 & 1 = 0 (no change in count)
 * Right shift: 10 >>> 1 -> 1 (binary)
 * 1 & 1 = 1 (increment count to 3)
 * Right shift: 1 >>> 1 -> 0 (binary, loop terminates)
 **/

/**
 * n & (n - 1): This operation turns off the rightmost 1 bit in n. For example, if n = 1011 (binary for 11), n - 1 = 1010 and n & (n - 1) = 1010, effectively removing the rightmost 1.
 * Counter increment: Each time we remove a 1, we increment the counter. When n becomes 0, we stop.
 *
 * Time Complexity: O(k), where k is the number of 1 bits in the integer. In the worst case, this will be at most O(32) for a 32-bit integer, but it can be more efficient than the previous approach if there are fewer 1s.
 * Space Complexity: O(1) (constant space).
 * **/