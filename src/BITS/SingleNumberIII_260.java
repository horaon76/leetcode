package BITS;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 * <p>
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 * <p>
 * Input: nums = [-1,0]
 * Output: [-1,0]
 * Example 3:
 * <p>
 * Input: nums = [0,1]
 * Output: [1,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each integer in nums will appear twice, only two integers will appear once.
 **/

public class SingleNumberIII_260 {
    public static int[] singleNumber(int[] nums) {
        // Step 1: XOR all numbers to get x XOR y
        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }

        // Step 2: Find the rightmost set bit (1)
        int rightmostSetBit = xorResult & -xorResult;

        int[] result = new int[2];
        // Step 3: Partition numbers into two groups and XOR them
        for (int num : nums) {
            if ((num & rightmostSetBit) == 0) {
                result[0] ^= num; // Group with the set bit
            } else {
                result[1] ^= num; // Group without the set bit
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] result = singleNumber(nums);

        System.out.println("The two unique numbers are: " + result[0] + " and " + result[1]); // Output: 3 and 5
    }
}
/**
 * Explanation:
 * XOR All Elements: This step reduces the problem to a single value, xorResult, which represents the XOR of the two unique numbers.
 *
 * Find the Rightmost Set Bit: The expression xorResult & -xorResult isolates the rightmost set bit. This bit is guaranteed to be different between the two unique numbers.
 *
 * Partition and XOR: By checking if each number has the rightmost set bit, we can partition the numbers into two groups. XORing the numbers in each group gives the two unique numbers.
 *
 * Time Complexity:
 * Time Complexity: O(N), where N is the number of elements in the input array. Each number is processed a constant number of times.
 * Space Complexity: O(1) since we are using a constant amount of space for the output array.
 * **/

/**
 * DRY Run
 *
 * Sure! Let's dry run the solution for **LeetCode 260: Single Number III** using the example input:
 *
 * ### Input
 * ```java
 * nums = [1, 2, 1, 3, 2, 5]
 * ```
 *
 * ### Step-by-Step Dry Run
 *
 * #### Step 1: XOR All Elements
 *
 * We will iterate through the array and XOR all the elements to get `xorResult`.
 *
 * - Initialize `xorResult = 0`.
 *
 * | Index | Element | Operation              | xorResult  |
 * |-------|---------|------------------------|------------|
 * | 0     | 1       | 0 ^ 1                  | 1          |
 * | 1     | 2       | 1 ^ 2                  | 3          |
 * | 2     | 1       | 3 ^ 1                  | 2          |
 * | 3     | 3       | 2 ^ 3                  | 1          |
 * | 4     | 2       | 1 ^ 2                  | 3          |
 * | 5     | 5       | 3 ^ 5                  | 6          |
 *
 * After this step, `xorResult` is `6` (binary `0110`), which is `x XOR y` where `x` and `y` are the two unique numbers.
 *
 * #### Step 2: Find the Rightmost Set Bit
 *
 * Next, we need to find a bit that is set in `xorResult`. We can isolate the rightmost set bit using the expression `xorResult & -xorResult`.
 *
 * - Calculate `rightmostSetBit`:
 *
 * ```java
 * rightmostSetBit = 6 & -6;  // -6 is 10 (in binary: 1110)
 *                        = 6 & 2  // (in binary: 0110 & 0010)
 *                        = 2
 * ```
 *
 * So, `rightmostSetBit = 2` (binary `0010`).
 *
 * #### Step 3: Partition the Numbers
 *
 * Now we will partition the numbers into two groups based on the rightmost set bit and XOR each group to find the unique numbers.
 *
 * - Initialize `result = [0, 0]`.
 *
 * We'll iterate through each number and partition them based on whether they have the rightmost set bit.
 *
 * | Index | Element | Check (element & rightmostSetBit) | Operation                       | result       |
 * |-------|---------|------------------------------------|---------------------------------|--------------|
 * | 0     | 1       | 1 & 2 = 0                          | result[0] ^= 1                 | [1, 0]       |
 * | 1     | 2       | 2 & 2 = 2                          | result[1] ^= 2                 | [1, 2]       |
 * | 2     | 1       | 1 & 2 = 0                          | result[0] ^= 1                 | [0, 2]       |
 * | 3     | 3       | 3 & 2 = 2                          | result[1] ^= 3                 | [0, 1]       |
 * | 4     | 2       | 2 & 2 = 2                          | result[1] ^= 2                 | [0, 3]       |
 * | 5     | 5       | 5 & 2 = 0                          | result[0] ^= 5                 | [5, 3]       |
 *
 * ### Final Result
 *
 * After processing all the elements, we find:
 *
 * - `result[0] = 5`
 * - `result[1] = 3`
 *
 * Thus, the unique numbers are `5` and `3`.
 *
 * ### Output
 *
 * The final output would be:
 *
 * ```java
 * The two unique numbers are: 5 and 3
 * ```
 *
 * ### Summary of the Dry Run
 * 1. We XORed all numbers to get `6`.
 * 2. We found the rightmost set bit as `2`.
 * 3. We partitioned the numbers into two groups based on this bit and XORed them to get the unique numbers `5` and `3`.
 *
 * This confirms the correctness of the algorithm and shows how it effectively isolates the two unique numbers.
 * **/