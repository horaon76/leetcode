package DP;

import java.util.*;

public class UniquePathII_63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        HashMap<String, Integer> memo = new HashMap<>();
        return uniquePathsHelper(obstacleGrid, 0, 0, memo);
    }

    private int uniquePathsHelper(int[][] grid, int i, int j, HashMap<String, Integer> memo) {
        // Base case: Out of bounds or obstacle
        if (i < 0 || j < 0 || grid[i][j] == 1) return 0;

        // Base case: Reached bottom-right corner
        if (i == grid.length - 1 && j == grid[0].length - 1) return 1;

        // Check memoization
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);

        // Recursive calls for down and right movements
        int down = uniquePathsHelper(grid, i + 1, j, memo);
        int right = uniquePathsHelper(grid, i, j + 1, memo);

        // Store in memoization map
        memo.put(key, down + right);
        return down + right;
    }


    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If starting or ending point has an obstacle, return 0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = 1; // Starting point

        // Fill the DP array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // Set to 0 if there's an obstacle
                } else {
                    if (j > 0) {
                        dp[j] += dp[j - 1]; // Paths from the left cell
                    }
                }
            }
        }

        return dp[n - 1];
    }


    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If starting or ending point has an obstacle, return 0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1; // Starting point

        // Fill the DP table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0; // Cell is an obstacle
                } else {
                    if (i > 0) dp[i][j] += dp[i - 1][j]; // From top
                    if (j > 0) dp[i][j] += dp[i][j - 1]; // From left
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
