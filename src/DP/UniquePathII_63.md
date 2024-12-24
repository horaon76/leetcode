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

LeetCode 63: **Unique Paths II**  
This problem is a variation of LeetCode 62 with an added twist: some cells in the grid are blocked, and you cannot traverse through them.

---

### **Problem Statement**
Given a \(m \times n\) grid with obstacles where:
- `grid[i][j] = 0` means the cell is free to move through.
- `grid[i][j] = 1` means the cell is blocked.

Find the number of unique paths from the top-left corner to the bottom-right corner of the grid. You can only move down or right.

---

### **Approaches to Solve**

---

### **1. Recursive Solution (Naive Approach)**

#### **Algorithm**
1. Use a recursive function starting at `(0, 0)` and try to move:
    - Down to `(i+1, j)` if it's within bounds and not blocked.
    - Right to `(i, j+1)` if it's within bounds and not blocked.
2. Base cases:
    - If you go out of bounds or hit a blocked cell, return 0.
    - If you reach the bottom-right corner, return 1 (a valid path).
3. Recursively compute the sum of paths from the two possible directions.

#### **Code**
```java
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return helper(obstacleGrid, 0, 0);
    }

    private int helper(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || grid[i][j] == 1) return 0;
        if (i == grid.length - 1 && j == grid[0].length - 1) return 1;
        return helper(grid, i + 1, j) + helper(grid, i, j + 1);
    }
}
```

#### **Complexity**
- **Time Complexity**: \(O(2^{m+n})\), as each recursive call splits into two branches.
- **Space Complexity**: \(O(m+n)\), due to recursion stack depth.

---

### **2. Recursive Solution with Memoization**

#### **Algorithm**
1. Extend the recursive approach by storing intermediate results in a 2D array `memo`.
2. If the value at `memo[i][j]` is not -1, return it to avoid recomputing.
3. Continue as in the naive recursive solution but with memoization.

#### **Code**
```java
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        return helper(obstacleGrid, 0, 0, memo);
    }

    private int helper(int[][] grid, int i, int j, int[][] memo) {
        if (i >= grid.length || j >= grid[0].length || grid[i][j] == 1) return 0;
        if (i == grid.length - 1 && j == grid[0].length - 1) return 1;
        if (memo[i][j] != -1) return memo[i][j];
        memo[i][j] = helper(grid, i + 1, j, memo) + helper(grid, i, j + 1, memo);
        return memo[i][j];
    }
}
```

#### **Complexity**
- **Time Complexity**: \(O(m \times n)\), as each cell is computed only once.
- **Space Complexity**: \(O(m \times n)\), for the `memo` table.

---

### **3. Dynamic Programming (Tabulation)**

#### **Algorithm**
1. Use a 2D `dp` array where `dp[i][j]` represents the number of unique paths to cell `(i, j)`.
2. Initialize:
    - `dp[0][0] = 1` if there is no obstacle at the start; otherwise, `dp[0][0] = 0`.
3. Transition:
    - If `grid[i][j] == 1`, set `dp[i][j] = 0` (blocked).
    - Otherwise:
      \[
      dp[i][j] = dp[i-1][j] + dp[i][j-1]
      \]
      (if those cells are within bounds).
4. Return `dp[m-1][n-1]`.

#### **Code**
```java
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // Initialize the start cell
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        // Fill the first row
        for (int i = 1; i < n; i++) {
            dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : dp[0][i - 1];
        }

        // Fill the first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
```

#### **Complexity**
- **Time Complexity**: \(O(m \times n)\), for filling the DP table.
- **Space Complexity**: \(O(m \times n)\), for the DP table.

---

### **4. Space-Optimized Dynamic Programming**

#### **Logic**
Since each cell depends only on the current row and the row above, we can reduce the 2D array to a 1D array.

#### **Code**
```java
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[] dp = new int[n];
        
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n - 1];
    }
}
```

#### **Complexity**
- **Time Complexity**: \(O(m \times n)\), for iterating through the grid.
- **Space Complexity**: \(O(n)\), for the 1D DP array.

---

### **Comparison of Solutions**

| **Approach**              | **Time Complexity** | **Space Complexity** |
|----------------------------|---------------------|-----------------------|
| Recursive (Naive)          | \(O(2^{m+n})\)      | \(O(m+n)\)            |
| Recursive with Memoization | \(O(m \times n)\)   | \(O(m \times n)\)     |
| Dynamic Programming (2D)   | \(O(m \times n)\)   | \(O(m \times n)\)     |
| DP with Space Optimization | \(O(m \times n)\)   | \(O(n)\)              |

---

### **Which Solution to Use?**
1. **Recursive (Naive)**: Not practical due to exponential time complexity.
2. **Memoized Recursion**: Good for small grids but uses extra space.
3. **Dynamic Programming (2D)**: Straightforward and clear but consumes more memory.
4. **Space-Optimized DP**: Best choice for large grids due to optimal space usage.
