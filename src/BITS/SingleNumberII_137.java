package BITS;

/**
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 * <p>
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 **/

public class SingleNumberII_137 {

    public static int singleNumber(int[] nums) {
        int result = 0;

        // Iterate through every bit position (0 to 31 for a 32-bit integer)
        for (int i = 0; i < 32; i++) {
            int bitSum = 0;

            // Count how many numbers have the ith bit set
            for (int num : nums) {
                if ((num >> i & 1) == 1) {
                    bitSum++;
                }
            }

            // If bitSum is not a multiple of 3, set the ith bit in the result
            if (bitSum % 3 != 0) {
                result |= (1 << i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 3, 2};
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};

        System.out.println("Single number in array 1: " + singleNumber(nums1)); // Output: 3
        System.out.println("Single number in array 2: " + singleNumber(nums2)); // Output: 99
    }
}
/**
 * Explanation:
 * We loop through all 32 bit positions (from 0 to 31, as integers are 32-bit).
 * For each bit position, we count how many numbers in the array have 1 at that position using the >> right-shift operator and & 1 to extract the bit.
 * If the count of 1s in any bit position is not divisible by 3, that bit is part of the single number, so we set the corresponding bit in the result using the bitwise OR result |= (1 << i).
 * Time Complexity:
 * Time Complexity: O(32n) = O(n), where n is the number of elements in the input array.
 * Space Complexity: O(1), as we only use a fixed number of extra variables.
 * **/