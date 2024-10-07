package BITS;
/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * Each element in the array appears twice except for one element which appears only once.
 * **/

/**
 *
 * a ^ a = 0 (any number XORed with itself is 0).
 * a ^ 0 = a (any number XORed with 0 remains unchanged).
 * XOR is commutative and associative, which means the order of applying XORs doesn't matter.
 * **/
public class singleNumber136 {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // XOR each number with result
        }
        return result; // The remaining result is the single number
    }
}
/**
 * Time Complexity:
 * O(n) where n is the number of elements in the array, as we are just iterating through the array once.
 * Space Complexity:
 * O(1) as we are using only a constant amount of extra space.
 * **/