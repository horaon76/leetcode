Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.



Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200

LeetCode 64: **Minimum Path Sum**

### **Problem Statement**
You are given a **m x n** grid filled with non-negative numbers. You start at the top-left corner of the grid (i.e., `grid[0][0]`) and want to reach the bottom-right corner (i.e., `grid[m-1][n-1]`).

You can only move either **down** or **right** at any point in time.

Return the **minimum sum** of the path from the top-left corner to the bottom-right corner.

---

### **Approaches to Solve**

#### **1. Recursive Solution (Naive Approach)**

In this solution, we use a simple recursive approach where at each cell, we recursively explore the two possible moves:
- **Move Right** to `(i, j+1)`.
- **Move Down** to `(i+1, j)`.

#### **Code**
```java
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        return helper(grid, 0, 0);
    }

    private int helper(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE; // Out of bounds
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j]; // Reached bottom-right corner

        // Move down or right and return the minimum path sum
        int down = helper(grid, i + 1, j);
        int right = helper(grid, i, j + 1);

        return grid[i][j] + Math.min(down, right);
    }
}
```

#### **Complexity**
- **Time Complexity**: \(O(2^{m+n})\) because at each cell, two recursive calls are made.
- **Space Complexity**: \(O(m+n)\) due to recursion stack depth.

---

#### **2. Recursive Solution with Memoization**

To optimize the recursive approach, we can store already computed values in a memoization table (`memo`), reducing redundant calculations.

#### **Code**
```java
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) Arrays.fill(row, -1); // Initialize memo table
        return helper(grid, 0, 0, memo);
    }

    private int helper(int[][] grid, int i, int j, int[][] memo) {
        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];

        if (memo[i][j] != -1) return memo[i][j]; // Return already computed value

        int down = helper(grid, i + 1, j, memo);
        int right = helper(grid, i, j + 1, memo);

        memo[i][j] = grid[i][j] + Math.min(down, right);
        return memo[i][j];
    }
}
```

#### **Complexity**
- **Time Complexity**: \(O(m \times n)\), as each cell is computed only once.
- **Space Complexity**: \(O(m \times n)\), for the memoization table.

---

#### **3. Dynamic Programming (Tabulation)**

In the DP approach, we use a 2D array `dp` where `dp[i][j]` represents the minimum path sum to reach the cell `(i, j)` from the top-left corner.

#### **Algorithm**
1. Initialize the first cell `dp[0][0] = grid[0][0]`.
2. For the first row, each cell is the sum of the cell to the left: `dp[0][j] = dp[0][j-1] + grid[0][j]`.
3. For the first column, each cell is the sum of the cell above: `dp[i][0] = dp[i-1][0] + grid[i][0]`.
4. For the rest of the cells, the minimum path sum is the minimum of moving from above or from the left:
   \[
   dp[i][j] = grid[i][j] + \min(dp[i-1][j], dp[i][j-1])
   \]
5. The result is in `dp[m-1][n-1]`.

#### **Code**
```java
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        // Initialize the top-left corner
        dp[0][0] = grid[0][0];

        // Initialize the first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Initialize the first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
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

#### **4. Space-Optimized Dynamic Programming**

Since each cell in the DP table only depends on the current row and the previous row, we can optimize space by using a single 1D array.

#### **Algorithm**
1. Use a 1D array `dp` of size `n`.
2. Initialize `dp[0] = grid[0][0]`.
3. For the first row, update each cell in the array as `dp[j] = dp[j-1] + grid[0][j]`.
4. For the subsequent rows, update the array in place:
   \[
   dp[j] = grid[i][j] + \min(dp[j], dp[j-1])
   \]
5. The result is in `dp[n-1]`.

#### **Code**
```java
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];

        dp[0] = grid[0][0];

        // Initialize the first row
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // Fill the DP table row by row
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];  // Update first column

            for (int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }

        return dp[n - 1];
    }
}
```

#### **Complexity**
- **Time Complexity**: \(O(m \times n)\), for filling the DP table.
- **Space Complexity**: \(O(n)\), for the 1D DP array.

---

### **Comparison of Solutions**

| **Approach**                | **Time Complexity** | **Space Complexity** |
|-----------------------------|---------------------|-----------------------|
| Recursive (Naive)           | \(O(2^{m+n})\)      | \(O(m+n)\)            |
| Recursive with Memoization  | \(O(m \times n)\)   | \(O(m \times n)\)     |
| Dynamic Programming (2D)    | \(O(m \times n)\)   | \(O(m \times n)\)     |
| Space-Optimized DP          | \(O(m \times n)\)   | \(O(n)\)              |

---

### **Which Solution to Use?**
1. **Recursive (Naive)**: Not practical due to exponential time complexity.
2. **Recursive with Memoization**: Efficient for smaller grids, but still uses extra space for the memoization table.
3. **Dynamic Programming (2D)**: Simple and intuitive but uses extra space for the 2D DP table.
4. **Space-Optimized DP**: Best choice for large grids due to minimal space usage.