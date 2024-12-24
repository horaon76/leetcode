package DP;
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.



Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

public class UniquePaths62 {

    public int uniquePaths1(int m, int n) {
        if (m == 1 || n == 1) return 1;
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}

Here are the Java implementations for all the approaches to solve LeetCode 62 (**Unique Paths**).

---

### **1. Recursive Solution (Without Memoization)**

```java
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }
}
```

**Time Complexity**: \(O(2^{m+n})\)  
**Space Complexity**: \(O(m + n)\) (recursion stack)

---

### **2. Recursive Solution with Memoization**

```java
import java.util.HashMap;
import java.util.Map;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(m, n, memo);
    }

    private int dfs(int m, int n, Map<String, Integer> memo) {
        if (m == 1 || n == 1) return 1;

        String key = m + "," + n;
        if (memo.containsKey(key)) return memo.get(key);

        int paths = dfs(m - 1, n, memo) + dfs(m, n - 1, memo);
        memo.put(key, paths);

        return paths;
    }
}
```

**Time Complexity**: \(O(m \times n)\)  
**Space Complexity**: \(O(m \times n)\) (for memoization table)

---

### **3. Dynamic Programming (2D Array)**

```java
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        // Fill the first row and first column with 1
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Fill the DP table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
```

**Time Complexity**: \(O(m \times n)\)  
**Space Complexity**: \(O(m \times n)\)

---

### **4. Dynamic Programming (Space Optimized)**

```java
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        // Initialize the array with 1 for the first row
        for (int i = 0; i < n; i++) dp[i] = 1;

        // Update the DP array for each row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}
```

**Time Complexity**: \(O(m \times n)\)  
**Space Complexity**: \(O(n)\)

---

### **5. Combinatorial Solution**

```java
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        long result = 1;
        int totalSteps = m + n - 2;
        int k = Math.min(m - 1, n - 1); // Choose the smaller of m-1 or n-1

        // Calculate C(totalSteps, k)
        for (int i = 1; i <= k; i++) {
            result = result * (totalSteps - i + 1) / i;
        }

        return (int) result;
    }
}
```

**Time Complexity**: \(O(\min(m, n))\)  
**Space Complexity**: \(O(1)\)

---

### **Comparison of Solutions**

| Approach                    | Time Complexity | Space Complexity | Notes                                   |
|-----------------------------|-----------------|------------------|-----------------------------------------|
| Recursive (No Memo)         | \(O(2^{m+n})\)  | \(O(m + n)\)     | Exponential, not practical.             |
| Recursive with Memoization  | \(O(m \times n)\) | \(O(m \times n)\) | Uses caching to improve efficiency.     |
| DP (2D Array)               | \(O(m \times n)\) | \(O(m \times n)\) | Easy to understand, but uses more space.|
| DP (Space Optimized)        | \(O(m \times n)\) | \(O(n)\)         | Best practical solution for large grids.|
| Combinatorial               | \(O(\min(m, n))\) | \(O(1)\)         | Fastest and uses no extra space.        |

---

### **Recommended Solution**
- Use **combinatorial** for fastest performance and minimal space usage.
- Use **space-optimized DP** if the problem size is large and requires detailed steps.
  The time complexity of the given recursive implementation for **Unique Paths** is **exponential**. Let’s analyze it step by step.

---

### Code
```java
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }
}
```

---

### Explanation of the Recursive Process

1. **Recursive Calls**:
    - At each call to `uniquePaths(m, n)`, two recursive calls are made:
        - `uniquePaths(m - 1, n)` (move down).
        - `uniquePaths(m, n - 1)` (move right).

2. **Base Case**:
    - The recursion stops when either \(m = 1\) or \(n = 1\), at which point the method directly returns 1.

3. **Tree Structure**:
    - The recursion can be visualized as a binary tree, where each node corresponds to a grid state \((m, n)\), and each branch represents a move (down or right).
    - The total number of unique subproblems (states) corresponds to the number of nodes in this recursion tree.

4. **Overlapping Subproblems**:
    - Many subproblems are solved multiple times due to overlapping recursive calls. For example:
        - To compute `uniquePaths(3, 3)`, the function calls `uniquePaths(2, 3)` and `uniquePaths(3, 2)`. Both these calls independently compute `uniquePaths(2, 2)`.

---

### Counting the Recursive Calls

The total number of calls can be approximated as:
\[
T(m, n) = T(m-1, n) + T(m, n-1)
\]

At each level of the recursion tree:
- The number of recursive calls doubles as the tree expands, leading to exponential growth.

The total number of calls is roughly proportional to the **number of paths in the binary recursion tree**. This tree has a height proportional to \(m + n - 2\), and the total number of nodes in such a tree grows **exponentially**.

---

### Time Complexity

The number of calls is proportional to the number of distinct paths from the top-left to the bottom-right, which can be approximated by:

\[
T(m, n) \sim 2^{m+n}
\]

Thus, the time complexity is:

\[
\text{Time Complexity} = O(2^{m+n})
\]

---

### Space Complexity

The space complexity is determined by the recursion stack. In the worst case, the depth of the recursion tree is \(m + n - 2\) (maximum possible moves):

\[
\text{Space Complexity} = O(m + n)
\]

---

### Key Takeaways

- **Exponential Growth**: Each call generates two additional calls, leading to \(O(2^{m+n})\) time complexity.
- **Inefficiency**: This approach is impractical for large grids due to overlapping subproblems.
- **Optimization Needed**: Use **memoization** or **dynamic programming** to reduce the redundant computations and bring the time complexity down to \(O(m \times n)\).


To understand how the time complexity for the given recursive solution is derived, let’s break it down in detail.

---

### **Recursive Structure of the Problem**

The problem starts with a grid of size \(m \times n\), where the robot starts at the top-left corner (\(m, n\)) and needs to reach the bottom-right corner (\(1, 1\)). At any point in the grid:

1. The robot can either move **down** (reducing \(m\) by 1) or move **right** (reducing \(n\) by 1).
2. This gives rise to two recursive calls:
    - `uniquePaths(m - 1, n)` (move down)
    - `uniquePaths(m, n - 1)` (move right)

---

### **Recursion Tree**

The recursive calls can be visualized as a tree:

- At the root, the problem starts with \((m, n)\).
- At each level of the recursion:
    - The problem is divided into two subproblems.
    - Each subproblem corresponds to a grid state \((m-1, n)\) or \((m, n-1)\).

---

### **Number of Recursive Calls**

The number of nodes in the recursion tree determines the time complexity. Let’s analyze the structure:

#### **1. Base Case**
The recursion stops when \(m = 1\) or \(n = 1\), at which point there is only one unique path remaining. These are the leaf nodes of the recursion tree.

#### **2. Recursive Depth**
Each move reduces either \(m\) or \(n\) by 1, so the recursion depth is \(m + n - 2\), which is the total number of moves from \((m, n)\) to \((1, 1)\).

#### **3. Total Nodes**
At each level of the recursion tree:
- The number of nodes doubles because each node creates two subproblems.
- The number of nodes at level \(k\) is \(2^k\), where \(k\) is the depth.

The total number of nodes in a binary tree with depth \(d\) is:

\[
\text{Total nodes} = 2^0 + 2^1 + 2^2 + \dots + 2^{d-1} = 2^d - 1
\]

For our problem:
- The depth of the recursion tree is \(d = m + n - 2\).
- The total number of nodes is approximately:

\[
\text{Total nodes} \sim 2^{m+n-2}
\]

Thus, the time complexity is **exponential**:

\[
O(2^{m+n})
\]

---

### **Why is it Exponential?**

Each recursive call spawns two new calls, leading to a rapid explosion in the number of subproblems as the recursion tree grows deeper. The exponential nature arises because:

1. At the first level, there is 1 call.
2. At the second level, there are 2 calls.
3. At the third level, there are 4 calls.
4. ...
5. At the \(k\)-th level, there are \(2^k\) calls.

By the time we reach the maximum depth (\(d = m + n - 2\)), the number of calls is on the order of \(2^{d}\), resulting in \(O(2^{m+n})\) time complexity.

---

### **Simplified Examples**

#### Example 1: \(m = 2, n = 2\)
The recursion tree for a \(2 \times 2\) grid looks like this:

```
        (2, 2)
       /       \
    (1, 2)    (2, 1)
    /    \    /    \
(1, 1) (1, 1)(1, 1)(1, 1)
```

- Total calls: 7
- Depth: 2 (\(m + n - 2 = 2\))
- Exponential growth for larger grids.

#### Example 2: \(m = 3, n = 3\)
The recursion tree for a \(3 \times 3\) grid becomes much larger:

```
                (3, 3)
           /             \
       (2, 3)           (3, 2)
     /      \         /       \
(1, 3)   (2, 2)   (2, 2)   (3, 1)
         /    \   /    \   /    \
       ...    ...      ...      ...
```

The number of nodes grows exponentially with \(m + n\).

---

### **Key Observations**

1. **Redundant Computations**:
    - Many states, such as `uniquePaths(2, 2)`, are recomputed multiple times in different branches of the recursion tree.
    - This inefficiency leads to the exponential growth of the recursion tree.

2. **Optimization with Memoization**:
    - By caching already computed results, we can reduce redundant computations.
    - With memoization, the time complexity improves to \(O(m \times n)\), as each unique state is computed only once.

---

### **Conclusion**

For the given recursive solution:
- **Time Complexity**: \(O(2^{m+n})\), due to the exponential growth of the recursion tree.
- **Space Complexity**: \(O(m + n)\), due to the depth of the recursion stack.

For larger grids, this approach becomes impractical, and **memoization** or **dynamic programming** should be used to avoid exponential runtime.


//DP

The given dynamic programming (DP) solution builds a table (`dp`) to compute the number of unique paths for a grid of size \( m \times n \). Let’s walk through how the `dp` table is populated step-by-step with an example.

---

### **Approach**
1. Create a 2D array `dp` where `dp[i][j]` represents the number of unique paths to reach cell \((i, j)\).
2. Base cases:
    - Any cell in the first row \((i = 0)\): Only one way to reach these cells (moving right only).
    - Any cell in the first column \((j = 0)\): Only one way to reach these cells (moving down only).
3. Transition relation:
    - For any other cell \((i, j)\), the number of unique paths is the sum of:
        - Paths from the cell above: `dp[i-1][j]`
        - Paths from the cell to the left: `dp[i][j-1]`
          \[
          dp[i][j] = dp[i-1][j] + dp[i][j-1]
          \]

4. Result:
    - The value at the bottom-right corner, `dp[m-1][n-1]`, gives the total number of unique paths.

---

### **Example: \( m = 3, n = 3 \)**

#### Step 1: Initialize the `dp` Table
The table is initialized with dimensions \(3 \times 3\) (size of the grid):

\[
\text{Initial DP Table:}
\begin{bmatrix}
0 & 0 & 0 \\
0 & 0 & 0 \\
0 & 0 & 0 \\
\end{bmatrix}
\]

#### Step 2: Fill the First Row and First Column
Set the first row and first column to `1` because there’s only one way to reach those cells:

\[
\text{After Base Case Initialization:}
\begin{bmatrix}
1 & 1 & 1 \\
1 & 0 & 0 \\
1 & 0 & 0 \\
\end{bmatrix}
\]

#### Step 3: Populate the Remaining Cells
Use the recurrence relation \(dp[i][j] = dp[i-1][j] + dp[i][j-1]\):

1. **Row 1**:
    - \(dp[1][1] = dp[0][1] + dp[1][0] = 1 + 1 = 2\)
    - \(dp[1][2] = dp[0][2] + dp[1][1] = 1 + 2 = 3\)

\[
\begin{bmatrix}
1 & 1 & 1 \\
1 & 2 & 3 \\
1 & 0 & 0 \\
\end{bmatrix}
\]

2. **Row 2**:
    - \(dp[2][1] = dp[1][1] + dp[2][0] = 2 + 1 = 3\)
    - \(dp[2][2] = dp[1][2] + dp[2][1] = 3 + 3 = 6\)

\[
\begin{bmatrix}
1 & 1 & 1 \\
1 & 2 & 3 \\
1 & 3 & 6 \\
\end{bmatrix}
\]

#### Step 4: Result
The value at `dp[2][2]` (bottom-right corner) is \(6\), which is the total number of unique paths.

---

### **Final DP Table**
The fully populated `dp` table looks like this:

\[
\begin{bmatrix}
1 & 1 & 1 \\
1 & 2 & 3 \\
1 & 3 & 6 \\
\end{bmatrix}
\]

Each cell represents the number of ways to reach that cell.

---

### **Time and Space Complexity**
1. **Time Complexity**: \(O(m \times n)\)
    - Filling each cell of the DP table requires constant time.
2. **Space Complexity**: \(O(m \times n)\)
    - The 2D array `dp` requires space proportional to \(m \times n\).

---

### **Optimization: Space-Efficient DP**
Instead of using a full \(m \times n\) table, we can use a single array of size \(n\) because the calculation for \(dp[i][j]\) only depends on the current row and the previous row. This reduces space complexity to \(O(n)\). Let me know if you'd like to see this version!

The logic to convert the 2D DP array into a 1D DP array is based on the **observation that at any point, we only need the current row and the previous row of the 2D DP table**. By using a single array (`dp`), we can efficiently simulate this behavior, reducing space complexity while maintaining correctness.

---

### **Detailed Explanation**

#### **Key Idea**
1. In the original 2D DP table, the value at `dp[i][j]` is calculated as:
   \[
   dp[i][j] = dp[i-1][j] + dp[i][j-1]
   \]
    - `dp[i-1][j]`: Value from the row above (previous row).
    - `dp[i][j-1]`: Value from the current row, but one column to the left.

2. When filling a particular row in the 2D DP table, we don’t need to retain the entire table. Only the values of the **current row** and the **previous row** are necessary. This allows us to "reuse" the same 1D array.

3. In the 1D `dp` array:
    - `dp[j]` represents the value of `dp[i-1][j]` (the previous row).
    - `dp[j - 1]` represents the value of `dp[i][j-1]` (current row, left column).

#### **Transition in the 1D DP Array**
For the 1D DP array:
\[
dp[j] = dp[j] + dp[j-1]
\]
- `dp[j]` holds the value from the previous row (`dp[i-1][j]` in the 2D table).
- `dp[j-1]` is the value to the left (`dp[i][j-1]` in the 2D table).

The result is stored back in `dp[j]`, updating it to represent the current row.

---

### **Step-by-Step Simulation**

#### Example: \(m = 3, n = 3\)

1. **Initialization**:
   The first row is filled with `1` because there is only one way to reach any cell in the first row (moving right):

   \[
   dp = [1, 1, 1]
   \]

2. **Row 2** (\(i = 1\)):
    - Start from `j = 1`:
      \[
      dp[1] = dp[1] + dp[0] = 1 + 1 = 2
      \]
      \[
      dp = [1, 2, 1]
      \]
    - Next, for `j = 2`:
      \[
      dp[2] = dp[2] + dp[1] = 1 + 2 = 3
      \]
      \[
      dp = [1, 2, 3]
      \]

3. **Row 3** (\(i = 2\)):
    - Start from `j = 1`:
      \[
      dp[1] = dp[1] + dp[0] = 2 + 1 = 3
      \]
      \[
      dp = [1, 3, 3]
      \]
    - Next, for `j = 2`:
      \[
      dp[2] = dp[2] + dp[1] = 3 + 3 = 6
      \]
      \[
      dp = [1, 3, 6]
      \]

4. **Result**:
    - The last value in the `dp` array, `dp[n - 1]`, represents the total number of unique paths:
      \[
      dp[2] = 6
      \]

---

### **Space Complexity Reduction**
1. **2D Table**:
    - Space complexity: \(O(m \times n)\), because a 2D array is used.

2. **1D Array**:
    - Space complexity: \(O(n)\), because only one row is stored at a time.

---

### **Why This Works**
- **Reusability of Data**:
    - `dp[j]` is updated sequentially from left to right in each row.
    - At each step, the new value of `dp[j]` depends only on the current `dp[j]` (previous row) and `dp[j-1]` (current row's left column).

- **Preservation of Order**:
    - Because `dp[j]` is updated after `dp[j-1]`, the computation uses the correct values at every step.

---

### **Final Thoughts**
The transition from a 2D to 1D DP array is a result of:
1. Observing that only two rows are necessary for computation.
2. Carefully updating the single array in place to simulate the behavior of a 2D table.

This optimization reduces space usage significantly, especially for large grids.