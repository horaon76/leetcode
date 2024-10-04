package Greedy;

public class JumpGameI_55 {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false; // If current index is beyond maxReach, we can't proceed
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true; // If we can reach or exceed the last index
            }
        }

        return false;
    }

    public boolean canJumpDP(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true; // Starting position

        for (int i = 0; i < n; i++) {
            if (dp[i]) {
                for (int j = i + 1; j <= i + nums[i] && j < n; j++) {
                    dp[j] = true; // Mark reachable positions
                }
            }
        }

        return dp[n - 1];
    }
}
