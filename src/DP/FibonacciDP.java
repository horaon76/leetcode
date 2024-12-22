package DP;

import java.util.Arrays;

public class FibonacciDP {

    // Top-Down Approach (Memoization)
    public static int fibonacciTopDown(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n]; // Return cached result if available

        memo[n] = fibonacciTopDown(n - 1, memo) + fibonacciTopDown(n - 2, memo); // Recursive call
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 5; // Example: Find the 10th Fibonacci number
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1); // Initialize cache with -1

        System.out.println("Fibonacci (" + n + ") using Memoization: " + fibonacciTopDown(n, memo));
    }
}

