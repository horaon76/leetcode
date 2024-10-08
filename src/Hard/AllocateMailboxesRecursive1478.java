package Hard;

import java.util.Arrays;

public class AllocateMailboxesRecursive1478 {
    private static int[][] cost;  // Precomputed cost for placing a mailbox between houses[i] and houses[j]
    private static int[][] memo;  // Memoization table

    public static int minDistance(int[] houses, int k) {
        int n = houses.length;
        Arrays.sort(houses);  // Sort the houses' positions to handle subarrays easily

        // Precompute the cost of placing a mailbox between houses[i] to houses[j]
        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int median = houses[(i + j) / 2];  // Optimal place for a mailbox is at the median
                for (int p = i; p <= j; p++) {
                    cost[i][j] += Math.abs(houses[p] - median);
                }
            }
        }

        // Memoization table to store results for dp[i][k] where i is house index and k is mailboxes left
        memo = new int[n][k + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Start recursion from the first house with k mailboxes
        return dp(houses, 0, k);
    }

    // Recursive function to calculate the minimum cost
    private static int dp(int[] houses, int i, int k) {
        if (k == 0 && i == houses.length) return 0;  // If no houses left and no mailboxes to place
        if (k == 0 || i == houses.length) return Integer.MAX_VALUE;  // If no more mailboxes or houses left but not both
        if (memo[i][k] != -1) return memo[i][k];  // Return memoized result if available

        int minCost = Integer.MAX_VALUE;

        // Try placing a mailbox between houses[i] to houses[j] and recurse for the remaining houses
        for (int j = i; j < houses.length; j++) {
            int currentCost = cost[i][j] + dp(houses, j + 1, k - 1);
            minCost = Math.min(minCost, currentCost);
        }

        memo[i][k] = minCost;  // Memoize the result
        return minCost;
    }

    public static void main(String[] args) {

        int[] houses = {1, 4, 8, 10, 20};
        int k = 3;

        System.out.println(minDistance(houses, k));  // Output: 5
    }
}

