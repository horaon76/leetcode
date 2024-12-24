package DP;

import java.util.HashMap;
import java.util.Map;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 **/

public class ClimbingStairs70 {
    private Map<Integer, Integer> memo = new HashMap<>();
    //Time Complexity: 𝑂 ( 2 𝑛 ) O(2 n ) Space Complexity: 𝑂 ( 𝑛 ) O(n) (call stack)
    public static int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }


    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = climbStairs(n - 1) + climbStairs(n - 2);
        memo.put(n, result);
        return result;
    }

    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
    public int climbStairs3(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;
        int prev1 = 2;

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
    public static void main(String args[]) {
        int n = 3;
        System.out.println(climbStairs1(n));
    }
}
//Approach	Time Complexity	Space Complexity
//Recursive (Brute Force)	O(2^n)	 O(n)
//Memoization (Top-Down DP)	O(n)	O(n)
//Tabulation (Bottom-Up DP)	O(n)	O(n)
//Space-Optimized DP	O(n)	O(1)