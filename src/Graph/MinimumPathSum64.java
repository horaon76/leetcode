package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 * <p>
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 **/
public class MinimumPathSum64 {
    private static class Cell {
        int row, col, cost;

        Cell(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    public static int minPathSumBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions for moving right and down
        int[][] directions = {{1, 0}, {0, 1}};

        // Queue for BFS
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(0, 0, grid[0][0]));

        // BFS to find the minimum path sum
        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            // If we've reached the bottom-right corner, update minCost
            if (current.row == rows - 1 && current.col == cols - 1) {
                minCost = Math.min(minCost, current.cost);
            }

            // Explore neighbors (right and down)
            for (int[] direction : directions) {
                int newRow = current.row + direction[0];
                int newCol = current.col + direction[1];

                if (newRow < rows && newCol < cols) {
                    queue.add(new Cell(newRow, newCol, current.cost + grid[newRow][newCol]));
                }
            }
        }

        return minCost;
    }

    public static int minPathSumDFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Memoization table to store minimum path sum for each cell
        int[][] memo = new int[rows][cols];

        // Call DFS from the top-left corner
        return dfs(grid, 0, 0, memo);
    }

    private static int dfs(int[][] grid, int row, int col, int[][] memo) {
        // Base case: if we are out of bounds, return a large number (infinity)
        if (row >= grid.length || col >= grid[0].length) {
            return Integer.MAX_VALUE;
        }

        // If we reach the bottom-right corner, return its value
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }

        // If we already calculated the minimum path for this cell, return it
        if (memo[row][col] != 0) {
            return memo[row][col];
        }

        // Explore the path by moving right and down
        int right = dfs(grid, row, col + 1, memo);
        int down = dfs(grid, row + 1, col, memo);

        // Store the result in memo table
        memo[row][col] = grid[row][col] + Math.min(right, down);

        return memo[row][col];
    }

    public static int minPathSumDP(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Update the first row by accumulating values
        for (int i = 1; i < cols; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        // Update the first column by accumulating values
        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // Fill the rest of the grid by choosing the minimum of top or left neighbor
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        // The bottom-right corner contains the minimum path sum
        return grid[rows - 1][cols - 1];
    }

    public static int minPathSumDFSIterative(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Stack for DFS, initialized with the top-left corner
        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(0, 0, grid[0][0]));

        // Variable to store the minimum path sum
        int minSum = Integer.MAX_VALUE;

        // DFS using stack
        while (!stack.isEmpty()) {
            Cell current = stack.pop();

            int row = current.row;
            int col = current.col;
            int sum = current.cost;

            // If we've reached the bottom-right corner, update the minimum sum
            if (row == rows - 1 && col == cols - 1) {
                minSum = Math.min(minSum, sum);
            }

            // Move down if it's within bounds
            if (row + 1 < rows) {
                stack.push(new Cell(row + 1, col, sum + grid[row + 1][col]));
            }

            // Move right if it's within bounds
            if (col + 1 < cols) {
                stack.push(new Cell(row, col + 1, sum + grid[row][col + 1]));
            }
        }

        return minSum;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int result = minPathSumBFS(grid);
        System.out.println("Minimum Path Sum using BFS: " + result);

        int result1 = minPathSumDFS(grid);
        System.out.println("Minimum Path Sum: " + result1);

        int result2 = minPathSumDP(grid);
        System.out.println("Minimum Path Sum: " + result2);

        int result3 = minPathSumDFSIterative(grid1);
        System.out.println("Minimum Path Sum: " + result3);

    }
}
/**
 * Time and Space Complexity:
 * Time Complexity:
 * 𝑂
 * (
 * 𝑚
 * ×
 * 𝑛
 * )
 * O(m×n), where
 * 𝑚
 * m is the number of rows and
 * 𝑛
 * n is the number of columns. We explore each cell once in the DFS.
 * <p>
 * Space Complexity:
 * 𝑂
 * (
 * 𝑚
 * ×
 * 𝑛
 * )
 * O(m×n), due to the stack that stores each cell during the traversal.
 **/