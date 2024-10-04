package DP;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 **/
public class HouseRobber198 {
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return robHouse(nums, nums.length - 1, memo);
    }

    private int robHouse(int[] nums, int i, int[] memo) {
        // Base case: no houses left to rob
        if (i < 0) return 0;

        // Check if already calculated
        if (memo[i] >= 0) return memo[i];

        // Store the result in memo
        memo[i] = Math.max(nums[i] + robHouse(nums, i - 2, memo), robHouse(nums, i - 1, memo));
        return memo[i];
    }
    public static int robDP1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // Fill the dp array
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(robDP1(nums));
    }
}
/**
 * Recursive (Brute Force)
 * 𝑂
 * (
 * 2
 * 𝑛
 * )
 * O(2
 * n
 *  )
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n)	Exponential time complexity because it explores every possibility.
 * Recursion with Memoization
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n)
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n)	Optimized with memoization, reducing redundant calculations.
 * Dynamic Programming (DP)
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n)
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n)	Bottom-up approach using a dp array to store results.
 * Space-Optimized DP
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n)
 * 𝑂
 * (
 * 1
 * )
 * O(1)	Uses two variables instead of a dp array, optimal in both time and space.
 * **/