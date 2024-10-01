package Graph;

public class PathWithMaxGold1219 {
    private int maxGold = 0;

    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Start DFS if we find gold
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return maxGold;
    }

    private void dfs(int[][] grid, int r, int c, int currentGold) {
        // Collect gold from the current cell
        currentGold += grid[r][c];
        // Update maxGold if we collected more
        maxGold = Math.max(maxGold, currentGold);

        // Mark the cell as visited by using a special value (e.g., -1)
        int temp = grid[r][c];
        grid[r][c] = -1; // Marking as visited

        // Explore all four directions
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            if (isValid(grid, newRow, newCol)) {
                dfs(grid, newRow, newCol, currentGold);
            }
        }

        // Backtrack: unmark the cell
        grid[r][c] = temp; // Restore the original value
    }

    private boolean isValid(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != -1;
    }
}
/**
 * Time Complexity: The time complexity is
 * 𝑂
 * (
 * 𝑁
 * ×
 * 𝑀
 * )
 * O(N×M), where
 * 𝑁
 * N is the number of rows and
 * 𝑀
 * M is the number of columns in the grid. Each cell is visited once in the worst case.
 *
 * Space Complexity: The space complexity is
 * 𝑂
 * (
 * 𝑁
 * ×
 * 𝑀
 * )
 * O(N×M) due to the recursion stack in the worst case when the path goes deep.
 * **/