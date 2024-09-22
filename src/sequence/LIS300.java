package sequence;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence
 * .
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 **/
//https://leetcode.com/problems/longest-increasing-subsequence/
public class LIS300 {

    //O(n)
    public static int getLIS(int[] nums) {
        int length = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    length = Math.max(length, dp[i]);
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums1 = new int[]{0,1,0,3,2,3};
        int[] nums2 = new int[]{7,7,7,7,7,7,7};
        System.out.print(getLIS(nums1));
        System.out.print(getLIS(nums2));
        int lis = getLIS(nums);
        System.out.print(lis);
    }
}
