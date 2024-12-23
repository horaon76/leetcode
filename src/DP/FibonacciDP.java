package DP;

import java.util.Arrays;

//public class FibonacciDP {
//
//    // Top-Down Approach (Memoization)
//    public static int fibonacciTopDown(int n, int[] memo) {
//        if (n <= 1) return n;
//        if (memo[n] != -1) return memo[n]; // Return cached result if available
//
//        memo[n] = fibonacciTopDown(n - 1, memo) + fibonacciTopDown(n - 2, memo); // Recursive call
//        return memo[n];
//    }
//
//    public static void main(String[] args) {
//        int n = 5; // Example: Find the 10th Fibonacci number
//        int[] memo = new int[n + 1];
//        Arrays.fill(memo, -1); // Initialize cache with -1
//
//        System.out.println("Fibonacci (" + n + ") using Memoization: " + fibonacciTopDown(n, memo));
//    }
//}

public class FibonacciDP {

    // Bottom-Up Approach (Tabulation)
    public static int fibonacciBottomUp(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5; // Example: Find the 10th Fibonacci number

        System.out.println("Fibonacci (" + n + ") using Tabulation: " + fibonacciBottomUp(n));
    }
}
//
//public class FibonacciDP {
//
//    // Optimized Space Approach
//    public static int fibonacciOptimized(int n) {
//        if (n <= 1) return n;
//
//        int prev2 = 0, prev1 = 1, current = 0;
//
//        for (int i = 2; i <= n; i++) {
//            current = prev1 + prev2;
//            prev2 = prev1;
//            prev1 = current;
//        }
//
//        return current;
//    }
//
//    public static void main(String[] args) {
//        int n = 5; // Example: Find the 10th Fibonacci number
//
//        System.out.println("Fibonacci (" + n + ") using Optimized Space: " + fibonacciOptimized(n));
//    }
//}

