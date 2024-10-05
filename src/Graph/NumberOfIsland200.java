package Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 **/
public class NumberOfIsland200 {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Traverse through each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {  // Start DFS when we encounter a '1' (land)
                    numIslands++;
                    dfs(grid, i, j);  // Mark all the land in this island
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check for boundary conditions and if the current cell is water ('0')
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';  // Mark the current cell as visited by changing '1' to '0'

        // Perform DFS on all 4 adjacent directions (up, down, left, right)
        dfs(grid, i + 1, j);  // Down
        dfs(grid, i - 1, j);  // Up
        dfs(grid, i, j + 1);  // Right
        dfs(grid, i, j - 1);  // Left
    }

    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions for moving in the grid (up, down, left, right)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Traverse through each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {  // Start BFS when we encounter a '1' (land)
                    numIslands++;
                    grid[i][j] = '0';  // Mark the land as visited

                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});

                    // BFS loop
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];

                        // Check all adjacent cells
                        for (int[] direction : directions) {
                            int newX = x + direction[0];
                            int newY = y + direction[1];

                            // Check boundaries and whether the cell is land
                            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == '1') {
                                grid[newX][newY] = '0';  // Mark as visited
                                queue.add(new int[]{newX, newY});
                            }
                        }
                    }
                }
            }
        }
        return numIslands;
    }
}
// both DFS and BFS approaches is O(M * N).
//Space Complexity: O(M * N) in the worst case due to the recursion stack in DFS or the queue in BFS.