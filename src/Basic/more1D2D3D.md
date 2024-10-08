Dynamic Programming (DP) can be extended to solve problems in 2D and 3D spaces, commonly found in fields like computer science, combinatorial optimization, and operations research. Understanding the structure of these problems is crucial for designing efficient solutions. Let's delve into 2D and 3D DP problems, their common variations, and code snippets to illustrate solutions.

### 1D 

Certainly! Here are some classic 1D dynamic programming problems along with their explanations, problem statements, and Java implementations.

### 1. Fibonacci Sequence

**Problem Statement**: Given an integer \( n \), calculate the \( n \)-th Fibonacci number. The Fibonacci sequence is defined as:
- \( F(0) = 0 \)
- \( F(1) = 1 \)
- \( F(n) = F(n-1) + F(n-2) \) for \( n > 1 \)

**Approach**:
- Use a DP array where `dp[i]` stores the \( i \)-th Fibonacci number.
- The recurrence relation is straightforward.

**Java Implementation**:
```java
public class Fibonacci {
    public int fibonacci(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        int n = 10; // Change this value to compute different Fibonacci numbers
        System.out.println("Fibonacci number at position " + n + " is: " + fib.fibonacci(n));
    }
}
```

### 2. Climbing Stairs

**Problem Statement**: You are climbing a staircase with \( n \) steps. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

**Approach**:
- Use a DP array where `dp[i]` stores the number of ways to reach step \( i \).
- The recurrence relation is:
  \[
  dp[i] = dp[i - 1] + dp[i - 2]
  \]

**Java Implementation**:
```java
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        int n = 5; // Change this value to compute ways to climb different stair lengths
        System.out.println("Number of ways to climb " + n + " steps: " + cs.climbStairs(n));
    }
}
```

### 3. Coin Change Problem

**Problem Statement**: Given a list of coin denominations and a total amount, determine the minimum number of coins needed to make that amount. If it's not possible, return -1.

**Approach**:
- Use a DP array where `dp[i]` represents the minimum number of coins needed to make amount \( i \).
- Initialize the array with a large value (like `amount + 1`) for all indices except `dp[0]`, which is 0.
- The recurrence relation is:
  \[
  dp[i] = \min(dp[i], dp[i - coin] + 1)
  \]

**Java Implementation**:
```java
import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11; // Change this value to test with different amounts
        System.out.println("Minimum coins needed: " + cc.coinChange(coins, amount));
    }
}
```

### 4. Longest Increasing Subsequence

**Problem Statement**: Given an integer array, find the length of the longest strictly increasing subsequence.

**Approach**:
- Use a DP array where `dp[i]` represents the length of the longest increasing subsequence that ends with the \( i \)-th element.
- The recurrence relation is:
  \[
  dp[i] = \max(dp[i], dp[j] + 1) \text{ for all } j < i \text{ where } arr[j] < arr[i]
  \]

**Java Implementation**:
```java
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // Each element is a subsequence of length 1

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18}; // Change this array to test with different inputs
        System.out.println("Length of Longest Increasing Subsequence: " + lis.lengthOfLIS(nums));
    }
}
```

### 5. Maximum Sum of Non-Adjacent Elements

**Problem Statement**: Given an array of integers, find the maximum sum of non-adjacent elements.

**Approach**:
- Use a DP array where `dp[i]` represents the maximum sum that can be obtained from the first \( i \) elements.
- The recurrence relation is:
  \[
  dp[i] = \max(dp[i-1], dp[i-2] + arr[i])
  \]

**Java Implementation**:
```java
public class MaxSumNonAdjacent {
    public int maxSumNonAdjacent(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        MaxSumNonAdjacent msna = new MaxSumNonAdjacent();
        int[] nums = {2, 4, 6, 2, 5}; // Change this array to test with different inputs
        System.out.println("Maximum sum of non-adjacent elements: " + msna.maxSumNonAdjacent(nums));
    }
}
```

These examples illustrate how to tackle various 1D dynamic programming problems using Java. If you have specific problems in mind or need more details, feel free to ask!

### 2D Dynamic Programming

2D DP problems often involve problems that can be represented in a grid or matrix format. The state is typically defined by two indices, \(i\) and \(j\), representing the position in the grid.

#### Common 2D DP Problems

1. **Longest Common Subsequence (LCS)**
    - **Problem**: Given two strings, find the length of the longest subsequence present in both strings.
    - **DP Table**: Let `dp[i][j]` be the length of LCS of the first `i` characters of string1 and the first `j` characters of string2.

   **Java Implementation**:
   ```java
   public class LongestCommonSubsequence {
       public int lcs(String text1, String text2) {
           int m = text1.length();
           int n = text2.length();
           int[][] dp = new int[m + 1][n + 1];

           for (int i = 1; i <= m; i++) {
               for (int j = 1; j <= n; j++) {
                   if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                       dp[i][j] = dp[i - 1][j - 1] + 1;
                   } else {
                       dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                   }
               }
           }

           return dp[m][n];
       }

       public static void main(String[] args) {
           LongestCommonSubsequence lcs = new LongestCommonSubsequence();
           String text1 = "abcde";
           String text2 = "ace";
           System.out.println("Length of LCS: " + lcs.lcs(text1, text2));
       }
   }
   ```

2. **Edit Distance**
    - **Problem**: Given two strings, calculate the minimum number of operations required to convert one string into the other. Operations are insertion, deletion, and substitution.
    - **DP Table**: Let `dp[i][j]` be the edit distance between the first `i` characters of string1 and the first `j` characters of string2.

   **Java Implementation**:
   ```java
   public class EditDistance {
       public int minDistance(String word1, String word2) {
           int m = word1.length();
           int n = word2.length();
           int[][] dp = new int[m + 1][n + 1];

           for (int i = 0; i <= m; i++) {
               for (int j = 0; j <= n; j++) {
                   if (i == 0) {
                       dp[i][j] = j; // If word1 is empty
                   } else if (j == 0) {
                       dp[i][j] = i; // If word2 is empty
                   } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                       dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                   } else {
                       dp[i][j] = 1 + Math.min(dp[i - 1][j], // Delete
                                               Math.min(dp[i][j - 1], // Insert
                                                        dp[i - 1][j - 1])); // Substitute
                   }
               }
           }

           return dp[m][n];
       }

       public static void main(String[] args) {
           EditDistance ed = new EditDistance();
           String word1 = "intention";
           String word2 = "execution";
           System.out.println("Minimum Edit Distance: " + ed.minDistance(word1, word2));
       }
   }
   ```

3. **Matrix Chain Multiplication**
    - **Problem**: Given a sequence of matrices, find the most efficient way to multiply these matrices together. The goal is to minimize the number of scalar multiplications.
    - **DP Table**: Let `dp[i][j]` represent the minimum cost to multiply matrices from index `i` to `j`.

   **Java Implementation**:
   ```java
   public class MatrixChainMultiplication {
       public int matrixChainOrder(int[] p) {
           int n = p.length;
           int[][] dp = new int[n][n];

           for (int len = 2; len < n; len++) {
               for (int i = 1; i < n - len + 1; i++) {
                   int j = i + len - 1;
                   dp[i][j] = Integer.MAX_VALUE;
                   for (int k = i; k < j; k++) {
                       int q = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                       if (q < dp[i][j]) {
                           dp[i][j] = q;
                       }
                   }
               }
           }

           return dp[1][n - 1]; // Minimum number of multiplications
       }

       public static void main(String[] args) {
           MatrixChainMultiplication mcm = new MatrixChainMultiplication();
           int[] p = {1, 2, 3, 4}; // Dimensions
           System.out.println("Minimum number of multiplications: " + mcm.matrixChainOrder(p));
       }
   }
   ```

### 3D Dynamic Programming

3D DP problems involve three indices and are often more complex. These problems usually relate to scenarios where you have multiple constraints or dimensions to consider.

#### Common 3D DP Problems

1. **3D Grid/Matrix Problems**
    - Problems involving paths in a 3D grid or multi-dimensional spaces.
    - Examples include finding paths with certain constraints, like maximum values, minimum costs, or specific conditions.

2. **Edit Distance with 3 Strings**
    - You can extend the edit distance problem to three strings. This involves finding the minimum operations to transform one string into another using a third string.

3. **Count of Palindromic Substrings in 3D**
    - This involves checking for palindromic structures in a three-dimensional array.

### Variations of Knapsack Problems in 2D and 3D

1. **2D Variations**:
    - **2D Bounded Knapsack**: A grid where each cell represents an item, and weights/values are associated with 2D positions.
    - **2D Coin Change Problem**: Given a 2D array of coins, find ways to make a certain amount using those coins.

2. **3D Variations**:
    - **3D Knapsack Problem**: Similar to 0/1 knapsack but involves three dimensions for items, weights, and values.
    - **3D Game Simulation**: Solving paths, states, or moves in a three-dimensional game board.

### Example Problem: 3D Knapsack Problem

**Problem Statement**: You have a 3D knapsack where items have three attributes (for example, weight, volume, and value). The goal is to maximize the value without exceeding the limits for weight, volume, and another constraint.

#### Java Implementation (Conceptual)
```java
public class ThreeDKnapsack {
    public int knapsack(int[][][] items, int W, int V, int C) {
        int[][][] dp = new int[W + 1][V + 1][C + 1];

        for (int i = 0; i < items.length; i++) {
            for (int w = W; w >= items[i][0][0]; w--) {
                for (int v = V; v >= items[i][0][1]; v--) {
                    for (int c = C; c >= items[i][0][2]; c--) {
                        dp[w][v][c] = Math.max(dp[w][v][c], dp[w - items[i][0][0]][v - items[i][0][1]][c - items[i][0][2]] + items[i][1][0]);
                    }
                }
            }
        }
        return dp[W][V][C];
    }

    public static void main(String[] args) {
        ThreeDKnapsack tdk = new ThreeDKnapsack();
        int[][][] items = {
            {{1, 2, 3}, {10}}, // {weight, volume, another constraint}, {value}
            {{4, 5, 6}, {40}},
            {{3, 4, 5}, {30}}
        };
        int W = 10, V = 10, C = 10; // Max capacities
        System.out.println("Maximum value in 3D Knapsack: " + tdk.knapsack(items, W, V, C));
    }
}
```

### Conclusion

Dynamic programming can effectively solve complex problems by breaking them down into simpler subproblems. Understanding how to structure DP tables in 2D and 3D is essential for tackling a wide range of problems. Practice these concepts with various problems to strengthen your understanding and problem-solving skills in dynamic programming.

### More Resources

- **LeetCode**: Practice problems categorized by type (2D/3D DP).
- **GeeksforGeeks**: Articles and
# 2D
Sure! Here are more examples of 2D dynamic programming problems along with their explanations, problem statements, and Java implementations.

### 1. Minimum Path Sum

**Problem Statement**: Given a grid filled with non-negative numbers, find a path from the top-left corner to the bottom-right corner, which minimizes the sum of the numbers along the path. You can only move down or right at any point in time.

**Approach**:
- Use a DP table where `dp[i][j]` represents the minimum path sum to reach cell `(i, j)` from `(0, 0)`.
- The recurrence relation will be:
  \[
  dp[i][j] = \text{grid}[i][j] + \min(dp[i-1][j], dp[i][j-1])
  \]

**Java Implementation**:
```java
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        // Initialize the first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Initialize the first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Fill the rest of the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1]; // Return the bottom-right corner value
    }

    public static void main(String[] args) {
        MinimumPathSum mps = new MinimumPathSum();
        int[][] grid = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println("Minimum Path Sum: " + mps.minPathSum(grid));
    }
}
```

### 2. Unique Paths

**Problem Statement**: Given a `m x n` grid, you start at the top-left corner and want to reach the bottom-right corner. You can only move either down or right at any point in time. Find the number of unique paths to reach the bottom-right corner.

**Approach**:
- Use a DP table where `dp[i][j]` represents the number of unique paths to reach cell `(i, j)`.
- The recurrence relation will be:
  \[
  dp[i][j] = dp[i-1][j] + dp[i][j-1]
  \]
- Initialize the first row and first column to 1 since there's only one way to reach any cell in the first row or first column.

**Java Implementation**:
```java
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Initialize the first row and first column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // Only one way to reach any cell in the first column
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1; // Only one way to reach any cell in the first row
        }

        // Fill the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1]; // Return the bottom-right corner value
    }

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        int m = 3, n = 7;
        System.out.println("Number of Unique Paths: " + up.uniquePaths(m, n));
    }
}
```

### 3. Coin Change II

**Problem Statement**: You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money. You want to determine the number of combinations that make up that amount using the coins. Note that you may use the same coin an unlimited number of times.

**Approach**:
- Use a DP table where `dp[j]` represents the number of ways to make change for amount `j`.
- Iterate over each coin and update the `dp` array based on the current coin.

**Java Implementation**:
```java
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // There is one way to make amount 0

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount]; // Return the number of combinations for the target amount
    }

    public static void main(String[] args) {
        CoinChangeII cc = new CoinChangeII();
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("Number of Combinations: " + cc.change(amount, coins));
    }
}
```

### 4. Longest Increasing Subsequence

**Problem Statement**: Given an integer array, return the length of the longest increasing subsequence.

**Approach**:
- Use a DP array where `dp[i]` represents the length of the longest increasing subsequence ending at index `i`.
- Iterate through the array, and for each element, check all previous elements to find the longest increasing subsequence that can be formed.

**Java Implementation**:
```java
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // Minimum length is 1 for each element

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int length : dp) {
            max = Math.max(max, length);
        }

        return max; // Return the maximum length found
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of Longest Increasing Subsequence: " + lis.lengthOfLIS(nums));
    }
}
```

### 5. Maximum Size Subarray Sum Equals k

**Problem Statement**: Given an array of integers `nums` and an integer `k`, return the maximum size of a subarray that sums to `k`. If there is no such subarray, return 0.

**Approach**:
- Use a HashMap to store the cumulative sum and its index, allowing you to check if a subarray that sums to `k` exists.

**Java Implementation**:
```java
import java.util.HashMap;

public class MaxSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1); // To handle the case where subarray starts from index 0

        int maxLength = 0, cumulativeSum = 0;

        for (int i = 0; i < nums.length; i++) {
            cumulativeSum += nums[i];

            // Check if there is a subarray that sums to k
            if (sumMap.containsKey(cumulativeSum - k)) {
                maxLength = Math.max(maxLength, i - sumMap.get(cumulativeSum - k));
            }

            // Store the cumulative sum with the first occurrence index
            if (!sumMap.containsKey(cumulativeSum)) {
                sumMap.put(cumulativeSum, i);
            }
        }

        return maxLength; // Return the maximum length found
    }

    public static void main(String[] args) {
        MaxSizeSubarraySumEqualsK ms = new MaxSizeSubarraySumEqualsK();
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println("Maximum Size of Subarray Sum Equals k: " + ms.maxSubArrayLen(nums, k));
    }
}
```

### Summary of 2D DP Problems

- **Minimum Path Sum**: Find the path with the minimum sum from the top-left to the bottom-right of a grid.
- **Unique Paths**: Count the unique paths to reach the bottom-right corner of a grid.
- **

# 3D

Sure! Here are some classic 3D dynamic programming problems along with their explanations, problem statements, and Java implementations.

### 1. 3D Matrix Chain Multiplication

**Problem Statement**: Given an array of matrices, find the minimum cost of multiplying them together. The cost of multiplying two matrices is equal to the product of their dimensions.

**Approach**:
- Use a DP table `dp[i][j]` that stores the minimum number of scalar multiplications needed to compute the product of matrices from index `i` to `j`.
- The recurrence relation is:
  \[
  dp[i][j] = \min_{i \leq k < j}(dp[i][k] + dp[k+1][j] + \text{dimensions}[i-1] \times \text{dimensions}[k] \times \text{dimensions}[j])
  \]

**Java Implementation**:
```java
public class MatrixChainMultiplication {
    public int matrixChainOrder(int[] p) {
        int n = p.length;
        int[][] dp = new int[n][n];

        // L is the chain length
        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                dp[i][j] = Integer.MAX_VALUE;

                // Try splitting the product at every possible position
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n - 1]; // Minimum cost for multiplying matrices from 1 to n-1
    }

    public static void main(String[] args) {
        MatrixChainMultiplication mcm = new MatrixChainMultiplication();
        int[] dimensions = {10, 20, 30, 40, 30};
        System.out.println("Minimum Cost of Matrix Multiplication: " + mcm.matrixChainOrder(dimensions));
    }
}
```

### 2. Longest Common Subsequence in 3D

**Problem Statement**: Given three strings, find the length of the longest subsequence that is common to all three strings.

**Approach**:
- Use a 3D DP table `dp[i][j][k]` where `i`, `j`, and `k` represent the lengths of the three strings.
- The recurrence relation will be:
    - If `s1[i-1] == s2[j-1] == s3[k-1]`:
      \[
      dp[i][j][k] = dp[i-1][j-1][k-1] + 1
      \]
    - Else:
      \[
      dp[i][j][k] = \max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1])
      \]

**Java Implementation**:
```java
public class LongestCommonSubsequence3D {
    public int longestCommonSubsequence(String s1, String s2, String s3) {
        int[][][] dp = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                for (int k = 1; k <= s3.length(); k++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1) && s1.charAt(i - 1) == s3.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }

        return dp[s1.length()][s2.length()][s3.length()]; // Return the length of LCS
    }

    public static void main(String[] args) {
        LongestCommonSubsequence3D lcs3d = new LongestCommonSubsequence3D();
        String s1 = "abc";
        String s2 = "ac";
        String s3 = "bc";
        System.out.println("Length of Longest Common Subsequence: " + lcs3d.longestCommonSubsequence(s1, s2, s3));
    }
}
```

### 3. 3D Box Stacking

**Problem Statement**: Given a list of boxes, each box has a height, width, and depth. You want to stack these boxes such that each box on top of another must have a smaller width and depth than the box below. Find the maximum height of the stack.

**Approach**:
- Use a 1D DP array where `dp[i]` represents the maximum height of the stack ending with box `i`.
- Sort the boxes based on their base area.
- For each box, check all previous boxes to see if they can be stacked.

**Java Implementation**:
```java
import java.util.Arrays;

class Box implements Comparable<Box> {
    int height, width, depth;

    public Box(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public int getBaseArea() {
        return width * depth;
    }

    @Override
    public int compareTo(Box other) {
        return other.getBaseArea() - this.getBaseArea(); // Sort in descending order
    }
}

public class BoxStacking {
    public int maxStackHeight(Box[] boxes) {
        Arrays.sort(boxes); // Sort boxes by base area
        int n = boxes.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = boxes[i].height; // Initialize dp[i] with the height of the box itself
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[i].width < boxes[j].width && boxes[i].depth < boxes[j].depth) {
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i].height);
                }
            }
        }

        int maxHeight = 0;
        for (int height : dp) {
            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight; // Return the maximum height found
    }

    public static void main(String[] args) {
        Box[] boxes = {
            new Box(4, 6, 7),
            new Box(1, 2, 3),
            new Box(4, 5, 6),
            new Box(10, 12, 32)
        };
        BoxStacking bs = new BoxStacking();
        System.out.println("Maximum Stack Height: " + bs.maxStackHeight(boxes));
    }
}
```

### 4. 3D Game Path

**Problem Statement**: In a 3D grid, each cell contains an integer value. You need to find a path from the top front corner to the bottom back corner with the maximum sum of values. You can move in six directions: up, down, left, right, forward, and backward.

**Approach**:
- Use a 3D DP array where `dp[x][y][z]` represents the maximum sum obtainable to reach cell `(x, y, z)`.
- The recurrence relation will consider all six possible moves.

**Java Implementation**:
```java
public class GamePath3D {
    public int maxPathSum(int[][][] grid) {
        int xLength = grid.length;
        int yLength = grid[0].length;
        int zLength = grid[0][0].length;
        int[][][] dp = new int[xLength][yLength][zLength];

        dp[0][0][0] = grid[0][0][0];

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                for (int z = 0; z < zLength; z++) {
                    if (x > 0) dp[x][y][z] = Math.max(dp[x][y][z], dp[x - 1][y][z] + grid[x][y][z]);
                    if (y > 0) dp[x][y][z] = Math.max(dp[x][y][z], dp[x][y - 1][z] + grid[x][y][z]);
                    if (z > 0) dp[x][y][z] = Math.max(dp[x][y][z], dp[x][y][z - 1] + grid[x][y][z]);
                }
            }
        }

        return dp[xLength - 1][yLength - 1][zLength - 1]; // Return the value at the bottom-back corner
    }

    public static void main(String[] args) {
        GamePath3D g3d = new GamePath3D


Sure! Here are some classic 3D dynamic programming problems along with their explanations, problem statements, and Java implementations.

### 1. 3D Matrix Chain Multiplication

**Problem Statement**: Given an array of matrices, find the minimum cost of multiplying them together. The cost of multiplying two matrices is equal to the product of their dimensions.

**Approach**:
- Use a DP table `dp[i][j]` that stores the minimum number of scalar multiplications needed to compute the product of matrices from index `i` to `j`.
- The recurrence relation is:
  \[
  dp[i][j] = \min_{i \leq k < j}(dp[i][k] + dp[k+1][j] + \text{dimensions}[i-1] \times \text{dimensions}[k] \times \text{dimensions}[j])
  \]

**Java Implementation**:
```java
public class MatrixChainMultiplication {
    public int matrixChainOrder(int[] p) {
        int n = p.length;
        int[][] dp = new int[n][n];

        // L is the chain length
        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                int j = i + L - 1;
                dp[i][j] = Integer.MAX_VALUE;

                // Try splitting the product at every possible position
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n - 1]; // Minimum cost for multiplying matrices from 1 to n-1
    }

    public static void main(String[] args) {
        MatrixChainMultiplication mcm = new MatrixChainMultiplication();
        int[] dimensions = {10, 20, 30, 40, 30};
        System.out.println("Minimum Cost of Matrix Multiplication: " + mcm.matrixChainOrder(dimensions));
    }
}
```

### 2. Longest Common Subsequence in 3D

**Problem Statement**: Given three strings, find the length of the longest subsequence that is common to all three strings.

**Approach**:
- Use a 3D DP table `dp[i][j][k]` where `i`, `j`, and `k` represent the lengths of the three strings.
- The recurrence relation will be:
    - If `s1[i-1] == s2[j-1] == s3[k-1]`:
      \[
      dp[i][j][k] = dp[i-1][j-1][k-1] + 1
      \]
    - Else:
      \[
      dp[i][j][k] = \max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1])
      \]

**Java Implementation**:
```java
public class LongestCommonSubsequence3D {
    public int longestCommonSubsequence(String s1, String s2, String s3) {
        int[][][] dp = new int[s1.length() + 1][s2.length() + 1][s3.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                for (int k = 1; k <= s3.length(); k++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1) && s1.charAt(i - 1) == s3.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }

        return dp[s1.length()][s2.length()][s3.length()]; // Return the length of LCS
    }

    public static void main(String[] args) {
        LongestCommonSubsequence3D lcs3d = new LongestCommonSubsequence3D();
        String s1 = "abc";
        String s2 = "ac";
        String s3 = "bc";
        System.out.println("Length of Longest Common Subsequence: " + lcs3d.longestCommonSubsequence(s1, s2, s3));
    }
}
```

### 3. 3D Box Stacking

**Problem Statement**: Given a list of boxes, each box has a height, width, and depth. You want to stack these boxes such that each box on top of another must have a smaller width and depth than the box below. Find the maximum height of the stack.

**Approach**:
- Use a 1D DP array where `dp[i]` represents the maximum height of the stack ending with box `i`.
- Sort the boxes based on their base area.
- For each box, check all previous boxes to see if they can be stacked.

**Java Implementation**:
```java
import java.util.Arrays;

class Box implements Comparable<Box> {
    int height, width, depth;

    public Box(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public int getBaseArea() {
        return width * depth;
    }

    @Override
    public int compareTo(Box other) {
        return other.getBaseArea() - this.getBaseArea(); // Sort in descending order
    }
}

public class BoxStacking {
    public int maxStackHeight(Box[] boxes) {
        Arrays.sort(boxes); // Sort boxes by base area
        int n = boxes.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = boxes[i].height; // Initialize dp[i] with the height of the box itself
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[i].width < boxes[j].width && boxes[i].depth < boxes[j].depth) {
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i].height);
                }
            }
        }

        int maxHeight = 0;
        for (int height : dp) {
            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight; // Return the maximum height found
    }

    public static void main(String[] args) {
        Box[] boxes = {
            new Box(4, 6, 7),
            new Box(1, 2, 3),
            new Box(4, 5, 6),
            new Box(10, 12, 32)
        };
        BoxStacking bs = new BoxStacking();
        System.out.println("Maximum Stack Height: " + bs.maxStackHeight(boxes));
    }
}
```

### 4. 3D Game Path

**Problem Statement**: In a 3D grid, each cell contains an integer value. You need to find a path from the top front corner to the bottom back corner with the maximum sum of values. You can move in six directions: up, down, left, right, forward, and backward.

**Approach**:
- Use a 3D DP array where `dp[x][y][z]` represents the maximum sum obtainable to reach cell `(x, y, z)`.
- The recurrence relation will consider all six possible moves.

**Java Implementation**:
```java
public class GamePath3D {
    public int maxPathSum(int[][][] grid) {
        int xLength = grid.length;
        int yLength = grid[0].length;
        int zLength = grid[0][0].length;
        int[][][] dp = new int[xLength][yLength][zLength];

        dp[0][0][0] = grid[0][0][0];

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                for (int z = 0; z < zLength; z++) {
                    if (x > 0) dp[x][y][z] = Math.max(dp[x][y][z], dp[x - 1][y][z] + grid[x][y][z]);
                    if (y > 0) dp[x][y][z] = Math.max(dp[x][y][z], dp[x][y - 1][z] + grid[x][y][z]);
                    if (z > 0) dp[x][y][z] = Math.max(dp[x][y][z], dp[x][y][z - 1] + grid[x][y][z]);
                }
            }
        }

        return dp[xLength - 1][yLength - 1][zLength - 1]; // Return the value at the bottom-back corner
    }

    public static void main(String[] args) {
        GamePath3D g3d = new GamePath3D

();
        int[][][] grid = {
            {
                {1, 2, 3},
                {4, 5, 6}
            },
            {
                {7, 8, 9},
                {1, 2, 3}
            }
        };
        System.out.println("Maximum Path Sum: " + g3d.maxPathSum(grid));
    }
}
```

These examples illustrate how to approach and implement various dynamic programming problems in a 3D space. 

