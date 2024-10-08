package Hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ou are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.
 * <p>
 * You have two robots that can collect cherries for you:
 * <p>
 * Robot #1 is located at the top-left corner (0, 0), and
 * Robot #2 is located at the top-right corner (0, cols - 1).
 * Return the maximum number of cherries collection using both robots by following the rules below:
 * <p>
 * From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
 * When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
 * When both robots stay in the same cell, only one takes the cherries.
 * Both robots cannot move outside of the grid at any moment.
 * Both robots should reach the bottom row in grid.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
 * Output: 24
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
 * Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
 * Total of cherries: 12 + 12 = 24.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
 * Output: 28
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
 * Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
 * Total of cherries: 17 + 11 = 28.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rows == grid.length
 * cols == grid[i].length
 * 2 <= rows, cols <= 70
 * 0 <= grid[i][j] <= 100
 **/
public class CherryPickII_1463 {
    public static int cherryPickup(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Memoization table to avoid recomputation
        int[][][] memo = new int[rows][cols][cols];

        // Fill memo table with -1 (unvisited state)
        for (int[][] mat : memo) {
            for (int[] arr : mat) {
                Arrays.fill(arr, -1);
            }
        }

        // Start DFS from row 0, with robot1 at column 0 and robot2 at column (cols - 1)
        return dfs(grid, 0, 0, cols - 1, memo);
        //return dp(grid, 0, 0, cols - 1, memo);
    }
    private static int dfs(int[][] grid, int row, int col1, int col2, int[][][] memo) {
        int rows = grid.length;
        int cols = grid[0].length;

        // If robots go out of bounds, return a very small number (invalid path)
        if (col1 < 0 || col1 >= cols || col2 < 0 || col2 >= cols) {
            return 0;
        }

        // If this state has been visited, return the memoized result
        if (memo[row][col1][col2] != -1) {
            return memo[row][col1][col2];
        }
        // Current cherries collected by both robots
        int result = grid[row][col1];
        if (col1 != col2) {
            result += grid[row][col2];
        }
        // If we haven't reached the last row, explore the next moves
        if (row < rows - 1) {
            int maxCherries = 0;
            // Explore all 9 possible moves for both robots (left, stay, right)
            for (int newCol1 = col1 - 1; newCol1 <= col1 + 1; newCol1++) {
                for (int newCol2 = col2 - 1; newCol2 <= col2 + 1; newCol2++) {
                    maxCherries = Math.max(maxCherries, dfs(grid, row + 1, newCol1, newCol2, memo));
                }
            }
            result += maxCherries;
        }

        // Memoize and return the result
        memo[row][col1][col2] = result;
        return result;
    }

    private static int dp(int[][] grid, int row, int col1, int col2, int[][][] memo) {
        int rows = grid.length;
        int cols = grid[0].length;

        // If either of the robots moves out of bounds
        if (col1 < 0 || col1 >= cols || col2 < 0 || col2 >= cols) {
            return 0;
        }

        // If we have already computed this state, return the stored result
        if (memo[row][col1][col2] != -1) {
            return memo[row][col1][col2];
        }

        // Current cell value
        int result = 0;
        result += grid[row][col1];

        // If both robots are not on the same cell, add the value of the second robot's cell
        if (col1 != col2) {
            result += grid[row][col2];
        }

        // If we haven't reached the last row, try all possible moves for both robots
        if (row < rows - 1) {
            int max = 0;
            for (int newCol1 = col1 - 1; newCol1 <= col1 + 1; newCol1++) {
                for (int newCol2 = col2 - 1; newCol2 <= col2 + 1; newCol2++) {
                    max = Math.max(max, dp(grid, row + 1, newCol1, newCol2, memo));
                }
            }
            result += max;
        }

        // Memoize the result
        memo[row][col1][col2] = result;
        return result;
    }

    private static class State {
        int row, col1, col2, cherries;

        public State(int row, int col1, int col2, int cherries) {
            this.row = row;
            this.col1 = col1;
            this.col2 = col2;
            this.cherries = cherries;
        }
    }

    public static int cherryPickupBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // BFS queue to store the states
        Queue<State> queue = new LinkedList<>();
        // DP array to store the maximum cherries collected up to row r, col1, and col2
        int[][][] dp = new int[rows][cols][cols];

        // Initialize dp array with -1 (unvisited state)
        for (int[][] mat : dp) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }

        // Start BFS with both robots at the top row, at positions (0, 0) and (0, cols - 1)
        queue.add(new State(0, 0, cols - 1, grid[0][0] + grid[0][cols - 1]));
        dp[0][0][cols - 1] = grid[0][0] + grid[0][cols - 1];

        // Directions representing robot moves (left, stay, right)
        int[] directions = {-1, 0, 1};

        // Start BFS loop
        while (!queue.isEmpty()) {
            State current = queue.poll();
            int row = current.row;
            int col1 = current.col1;
            int col2 = current.col2;
            int cherries = current.cherries;

            // If we reached the last row, continue (no more rows to explore from this point)
            if (row == rows - 1) continue;

            // Explore all possible moves for both robots
            for (int d1 : directions) {
                for (int d2 : directions) {
                    int newCol1 = col1 + d1;
                    int newCol2 = col2 + d2;

                    // Ensure both robots stay within the grid boundaries
                    if (newCol1 < 0 || newCol1 >= cols || newCol2 < 0 || newCol2 >= cols) {
                        continue;
                    }

                    // Calculate cherries collected at the new positions
                    int newCherries = 0;
                    if (newCol1 == newCol2) {
                        // If both robots land on the same cell, collect cherries only once
                        newCherries = grid[row + 1][newCol1];
                    } else {
                        // If robots land on different cells, collect from both
                        newCherries = grid[row + 1][newCol1] + grid[row + 1][newCol2];
                    }

                    // Total cherries collected up to the new positions
                    newCherries += cherries;

                    // If this new state collects more cherries than previously recorded, update dp and queue
                    if (newCherries > dp[row + 1][newCol1][newCol2]) {
                        dp[row + 1][newCol1][newCol2] = newCherries;
                        queue.add(new State(row + 1, newCol1, newCol2, newCherries));
                    }
                }
            }
        }

        // Find the maximum cherries collected at the last row for all possible positions of both robots
        int maxCherries = 0;
        for (int col1 = 0; col1 < cols; col1++) {
            for (int col2 = 0; col2 < cols; col2++) {
                maxCherries = Math.max(maxCherries, dp[rows - 1][col1][col2]);
            }
        }

        return maxCherries;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {3,1,1},
                {2,5,1},
                {1,5,5},
                {2,1,1}
        };
        System.out.println(cherryPickup(grid));
    }
}
//DFS: The function dfs recursively explores all possible paths starting from the top row to the bottom row for both robots simultaneously.
//Memoization: The 3D array memo[row][col1][col2] stores the maximum number of cherries that can be collected starting from row row, with robot 1 at column col1 and robot 2 at column col2. This avoids recomputing the same state multiple times.
//Transition: For each move, we try all 9 possible combinations of moves for both robots (robot 1 can go left, stay, or right, and the same for robot 2). This covers all possible future positions.

//### Time Complexity:
//- Using DFS with memoization, the time complexity becomes \(O(m \times n \times n)\), where \(m\) is the number of rows and \(n\) is the number of columns. This is much more efficient than the brute-force DFS or BFS.


//BFS
/*
* ### Explanation:

1. **State Representation**:
   - Each state represents a position of the two robots on a given row, which is stored in a class `State`. Each `State` object keeps track of:
     - `row`: The current row.
     - `col1` and `col2`: The column positions of robot 1 and robot 2.
     - `cherries`: The number of cherries collected so far up to that state.

2. **BFS Queue**:
   - We start BFS from the top row with both robots at their starting positions `(0, 0)` and `(0, cols - 1)`. The BFS explores all possible moves for both robots in each iteration.

3. **Dynamic Programming Table (`dp`)**:
   - `dp[row][col1][col2]` represents the maximum number of cherries collected when robot 1 is at column `col1` and robot 2 is at column `col2` on row `row`.

4. **BFS Exploration**:
   - For each state, we explore all 9 possible moves for both robots (left, stay, right).
   - If a new move results in collecting more cherries than previously recorded in `dp`, we update the DP table and enqueue the new state.

5. **Result**:
   - Once we finish the BFS, the maximum cherries collected can be found in the last row by checking all possible positions of robot 1 and robot 2.

### Time Complexity:
- The time complexity is \(O(m \times n^2)\), where `m` is the number of rows and `n` is the number of columns. This is because:
  - For each row, we evaluate all possible positions of robot 1 and robot 2, which is \(n \times n\), and for each state, we explore 9 possible moves.

### Space Complexity:
- The space complexity is also \(O(m \times n^2)\) due to the 3D DP array and the queue used for BFS.

### Conclusion:
The BFS approach works for this problem, but it requires careful management of state space and exploration, especially when handling two moving entities (robots) simultaneously. However, BFS is not as natural a fit for this problem as DFS with memoization because of the nature of backtracking, but it still gives a clear picture of how BFS can be applied to dynamic programming problems involving multiple agents.
* */