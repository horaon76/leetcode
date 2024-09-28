package Graph;

import java.util.Stack;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * The area of an island is the number of cells with a value 1 in the island.
 * <p>
 * Return the maximum area of an island in grid. If there is no island, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * <p>
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 **/
public class MaxAreaOfIsland695 {
    public static int maxAreaOfIsland(int[][] grid) {
        int max_area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max_area = Math.max(max_area, AreaOfIsland(grid, i, j));
                }
            }
        }
        return max_area;
    }

    public static int AreaOfIsland(int[][] grid, int i, int j) {
        // Check if out of bounds or on water
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        // Mark the land as visited by setting it to 0
        grid[i][j] = 0;
        // Calculate the area recursively for the 4 adjacent directions (up, down, left, right)
        int area = 1;
        area += AreaOfIsland(grid, i - 1, j); // up
        area += AreaOfIsland(grid, i + 1, j); // down
        area += AreaOfIsland(grid, i, j - 1); // left
        area += AreaOfIsland(grid, i, j + 1); // right

        return area;
    }

    /**
     * Time Complexity:
     * The time complexity is O(n * m), where n is the number of rows and m is the number of columns. Each cell is processed at most once.
     * Space Complexity:
     * The space complexity is O(n * m) in the worst case due to the stack usage. It can grow as large as the total number of land cells in the grid.
     * **/
    public static int maxAreaOfIslandIterative(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, iterativeDFS(grid, i, j));
                }
            }
        }

        return maxArea;
    }

    private static int iterativeDFS(int[][] grid, int i, int j) {
        // Stack to keep track of coordinates (i, j)
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});

        int area = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions for moving in 4 possible ways (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int x = cell[0];
            int y = cell[1];

            // If this cell is out of bounds or already visited (water)
            if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == 0) {
                continue;
            }

            // Mark the current cell as visited
            grid[x][y] = 0;
            area++;

            // Push all the adjacent land cells onto the stack
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                stack.push(new int[]{newX, newY});
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
//        System.out.println(maxAreaOfIsland(grid));
        System.out.println(maxAreaOfIslandIterative(grid));
    }
}
/**
 * Let’s break it down step-by-step to clarify how the time complexity of the recursive DFS approach for the "Max Area of Island" problem is
 * 𝑂
 * (
 * 𝑛
 * ×
 * 𝑚
 * )
 * O(n×m):
 * <p>
 * 1. Understanding the Problem
 * You have a grid of size
 * 𝑛
 * ×
 * 𝑚
 * n×m, where each cell can be:
 * <p>
 * Land (represented by 1)
 * Water (represented by 0)
 * The task is to find the maximum area of connected land cells (islands).
 * <p>
 * 2. Outer Loop: Iterating Through the Grid
 * The function maxAreaOfIsland includes a nested loop that iterates through each cell in the grid:
 * <p>
 * java
 * Copy code
 * for (int i = 0; i < grid.length; i++) {          // Loop through rows (n)
 * for (int j = 0; j < grid[0].length; j++) {   // Loop through columns (m)
 * if (grid[i][j] == 1) {
 * // Initiate DFS if we find land
 * }
 * }
 * }
 * Total Iterations: This means you are examining every single cell in the grid, leading to
 * 𝑛
 * ×
 * 𝑚
 * n×m total iterations.
 * 3. Inside the DFS Call
 * When you find a land cell (1), you call the dfs function:
 * <p>
 * java
 * Copy code
 * private int dfs(int[][] grid, int i, int j) {
 * // Base cases and visit logic
 * // Call dfs recursively on adjacent cells
 * }
 * 4. How DFS Works
 * Visiting Cells: The DFS function will explore all connected land cells. Here’s what happens:
 * When you call DFS on a cell, it checks its 4 adjacent cells (up, down, left, right).
 * If an adjacent cell is also land (1), it calls DFS on that cell too.
 * 5. Key Point: Each Cell is Processed Once
 * Here’s the crucial part of the explanation:
 * <p>
 * Marking Visited: Each time a cell is processed in the DFS, you mark it as visited by changing its value to 0 (or some other value indicating it’s no longer land).
 * java
 * Copy code
 * grid[i][j] = 0; // Mark the cell as visited
 * No Re-Processing: Because you mark each land cell as visited when you first encounter it, that cell cannot be revisited by any future DFS calls. This means:
 * Each land cell is visited exactly once during the entire run of the algorithm.
 * 6. Total Operations
 * Cells Processed: In the worst-case scenario (when the entire grid is land), you will call DFS on each of the
 * 𝑛
 * ×
 * 𝑚
 * n×m cells exactly once.
 * Area Calculation: Inside the DFS, while calculating the area, you’re doing a constant amount of work for each land cell (checking neighbors and marking it).
 * Summary
 * Total Cells: The entire grid has
 * 𝑛
 * ×
 * 𝑚
 * n×m cells.
 * DFS Calls: Each land cell (1) is visited exactly once, leading to a total of
 * 𝑛
 * ×
 * 𝑚
 * n×m visits in the worst case.
 * Overall Complexity: Since the outer loop goes through every cell, and the DFS processes each cell once, the total time complexity for the algorithm is
 * 𝑂
 * (
 * 𝑛
 * ×
 * 𝑚
 * )
 * O(n×m).
 * Visualization
 * To visualize:
 * <p>
 * Suppose you have a grid of size
 * 3
 * ×
 * 4
 * 3×4:
 * Copy code
 * 1 1 0 0
 * 1 0 0 1
 * 0 0 1 1
 * In the outer loop, you iterate through all
 * 12
 * 12 cells:
 * <p>
 * When you reach the first 1, you initiate DFS.
 * The DFS explores all connected 1s, marking them 0.
 * All other cells are either 0 or have been marked 0, so they are skipped in future iterations.
 * In total, you process all
 * 12
 * 12 cells just once, confirming the
 * 𝑂
 * (
 * 𝑛
 * ×
 * 𝑚
 * )
 * O(n×m) complexity.
 **/
