package Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 * <p>
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 * <p>
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 **/


//Approach:
//This problem can be solved using Breadth-First Search (BFS), which is ideal for simulating the spread of rot, as BFS explores level by level (minute by minute in this case). You can think of this as a multi-source BFS problem, where all the rotten oranges are initially the starting points.
//
//Steps:
//Initialize a Queue:
//First, push all the initially rotten oranges (2) into a queue. These will act as the starting points for BFS.
//Also, count the number of fresh oranges (1).
//Perform BFS:
//For each rotten orange, process its 4-directional neighbors. If a neighbor is a fresh orange, mark it as rotten and push it into the queue.
//Track the number of minutes it takes to rot all reachable oranges.
//Check for Remaining Fresh Oranges:
//After BFS, if there are still fresh oranges left, return -1. Otherwise, return the number of minutes taken.
public class RottingOrange994 {

    public static int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        // Step 1: Add all initially rotten oranges to the queue and count fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // Add rotten orange to queue
                } else if (grid[i][j] == 1) {
                    freshOranges++; // Count fresh oranges
                }
            }
        }

        // If there are no fresh oranges, return 0
        if (freshOranges == 0) return 0;

        // Step 2: Perform BFS to rot adjacent fresh oranges
        int minutes = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 4 possible directions

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottenThisRound = false;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];

                // Explore 4 possible neighbors (up, down, left, right)
                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];

                    // Check if the new position is within the grid and has a fresh orange
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Rot the fresh orange
                        queue.offer(new int[]{newRow, newCol}); // Add new rotten orange to queue
                        freshOranges--; // Decrease fresh orange count
                        rottenThisRound = true;
                    }
                }
            }

            // If at least one orange was rotten during this round, increment the minutes
            if (rottenThisRound) {
                minutes++;
            }
        }

        // Step 3: If there are still fresh oranges left, return -1
        return freshOranges == 0 ? minutes : -1;
    }

    public static int orangesRottingDFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int freshOranges = 0;
        int time = 0;

        // Count all fresh oranges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        // If there are no fresh oranges, return 0
        if (freshOranges == 0) return 0;

        // Perform DFS from each rotten orange to rot its neighbors
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    dfs(grid, visited, i, j, 0);
                }
            }
        }

        // After DFS, check if there are still fresh oranges left
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    return -1; // There are still fresh oranges left
                }
                if (grid[i][j] > 2) {
                    time = Math.max(time, grid[i][j] - 2); // Find maximum time to rot
                }
            }
        }

        return time;
    }

    // Recursive DFS function to propagate rotting
    private static void dfs(int[][] grid, boolean[][] visited, int row, int col, int currentTime) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Out of bounds or already visited or empty cell
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0 || grid[row][col] == 2 || visited[row][col]) {
            return;
        }

        // If the current cell is a fresh orange or a cell that is being rotted
        if (grid[row][col] == 1 || grid[row][col] > currentTime + 2) {
            grid[row][col] = currentTime + 2; // Increment the time to rot this orange
            visited[row][col] = true;

            // Move in all 4 possible directions
            dfs(grid, visited, row - 1, col, grid[row][col]);
            dfs(grid, visited, row + 1, col, grid[row][col]);
            dfs(grid, visited, row, col - 1, grid[row][col]);
            dfs(grid, visited, row, col + 1, grid[row][col]);

            visited[row][col] = false; // Mark as unvisited for other potential paths
        }
    }

    public static void main(String[] args) {
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        int[][] grid3 = {{0, 2}};

        System.out.println(orangesRotting(grid1)); // Output: 4
        System.out.println(orangesRotting(grid2)); // Output: -1
        System.out.println(orangesRotting(grid3)); // Output: 0
    }
}
//Explanation:
//Initialization:
//
//We scan the grid and add the coordinates of all rotten oranges to the queue.
//We also count the total number of fresh oranges (freshOranges).
//BFS Traversal:
//
//For each minute, we process all the rotten oranges in the queue.
//For each rotten orange, check its 4 neighboring cells. If a neighbor is a fresh orange, it becomes rotten, and we add it to the queue for the next minute's processing.
//After each round, we increase the minute counter only if at least one fresh orange rots.
//Termination:
//
//The BFS ends when there are no more rotten oranges to process (the queue is empty).
//If there are still fresh oranges left (freshOranges > 0), return -1. Otherwise, return the total number of minutes.
//Dry Run:
//Input: [[2,1,1],[1,1,0],[0,1,1]]
//Initial Grid:
//
//Copy code
//2 1 1
//1 1 0
//0 1 1
//Initially rotten orange coordinates: (0, 0)
//Fresh oranges count: 6
//Minute 0:
//
//Rotten orange (0, 0) infects (0, 1) and (1, 0).
//Queue: [(0, 1), (1, 0)]
//Fresh oranges: 4
//Grid after minute 0:
//
//Copy code
//2 2 1
//2 1 0
//0 1 1
//Minute 1:
//
//Rotten orange (0, 1) infects (0, 2).
//Rotten orange (1, 0) infects (1, 1).
//Queue: [(0, 2), (1, 1)]
//Fresh oranges: 2
//Grid after minute 1:
//
//Copy code
//2 2 2
//2 2 0
//0 1 1
//Minute 2:
//
//Rotten orange (0, 2) infects (1, 2).
//Rotten orange (1, 1) infects (2, 1).
//Queue: [(2, 1)]
//Fresh oranges: 0
//Grid after minute 2:
//
//Copy code
//2 2 2
//2 2 0
//0 2 2
//Minute 3:
//
//All oranges are rotten. No more fresh oranges remain.
//Output: 4 minutes
//
//Time Complexity:
//Grid Traversal: We traverse the grid once to find all rotten and fresh oranges, which takes O(m * n) where m is the number of rows and n is the number of columns.
//BFS: In the worst case, we might need to process each orange once in the BFS, which also takes O(m * n).
//Thus, the overall time complexity is O(m * n).
//
//Space Complexity:
//The space complexity is also O(m * n) due to the queue and the grid size.

// DFS
//Explanation:
//Initialization:
//
//We first count all the fresh oranges. If there are no fresh oranges, we return 0.
//We use a 2D visited array to track whether a cell has already been visited by DFS.
//DFS Propagation:
//
//For each initially rotten orange, we perform DFS to spread the rot recursively to all reachable fresh oranges. Each recursive call increases the "time" it takes to rot an orange.
//For every rotten orange, we attempt to propagate the rot to its 4 neighbors. If the neighbor is a fresh orange (1), we mark it as rotten and continue the DFS.
//We use the value grid[row][col] to track how long it took for each orange to rot (currentTime + 2 ensures the propagation of time).
//Check for Fresh Oranges:
//
//After all DFS calls are complete, we check if there are any remaining fresh oranges (1). If so, return -1.
//If all oranges are rotten, we find the maximum time required to rot all oranges and return it.
//Dry Run:
//Input: [[2,1,1],[1,1,0],[0,1,1]]
//Initial Grid:
//
//Copy code
//2 1 1
//1 1 0
//0 1 1
//Initially rotten orange coordinates: (0, 0)
//Fresh oranges count: 6
//DFS Step 1:
//
//Start DFS from (0, 0) (rotten orange).
//Infect (0, 1) (neighboring fresh orange) and (1, 0).
//Grid after DFS Step 1:
//
//Copy code
//2 3 1
//3 1 0
//0 1 1
//DFS Step 2:
//
//Infect (0, 2) and (1, 1) in further DFS steps.
//Grid after DFS Step 2:
//
//Copy code
//2 3 4
//3 4 0
//0 1 1
//DFS Step 3:
//
//Infect the remaining fresh oranges: (1, 2) and (2, 1).
//Final Grid:
//
//Copy code
//2 3 4
//3 4 0
//0 5 6
//The maximum time it took to rot all oranges is 4 minutes.
//
//Time Complexity:
//DFS Traversal: In the worst case, DFS can touch each cell in the grid, giving a time complexity of O(m * n) where m is the number of rows and n is the number of columns.
//Space Complexity:
//Space for Recursion: The space complexity is O(m * n) due to the recursion stack and the visited array.