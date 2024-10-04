package Greedy;

import java.util.Arrays;

/**
 * you are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 * <p>
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 * <p>
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * It's guaranteed that you can reach nums[n - 1].
 **/

public class Jump_Game_II_45 {
    //Time - O(n)
    //Space - O(1)
    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }
    //O(n^2)
    //Space O(n)
    public int jump1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Starting point

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 0, 1, 4};
        System.out.println(jump(nums));
    }
}
