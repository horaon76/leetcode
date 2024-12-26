The **Knapsack Problem** is a classic dynamic programming problem with several variations, each with unique constraints and use cases. Below is an overview of the main variations of the knapsack problem and their respective solutions.

---

### **1. 0/1 Knapsack Problem**
- **Problem**: Given weights and values of `n` items, find the maximum value that can be obtained with a given weight capacity `W`. Each item can be included at most once.

**Dynamic Programming Solution**:
```java
public class Knapsack {
    public static int knapsack01(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }
}
```
- **Time Complexity**: \(O(n \cdot W)\)
- **Space Complexity**: \(O(n \cdot W)\)

---

### **2. Fractional Knapsack Problem**
- **Problem**: Similar to 0/1 Knapsack, but items can be divided into fractions.

**Greedy Algorithm Solution**:
```java
import java.util.Arrays;

public class FractionalKnapsack {
    static class Item {
        int weight, value;
        double ratio;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = value * 1.0 / weight;
        }
    }

    public static double fractionalKnapsack(int W, int[] weights, int[] values) {
        int n = weights.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double maxValue = 0;
        for (Item item : items) {
            if (W >= item.weight) {
                W -= item.weight;
                maxValue += item.value;
            } else {
                maxValue += item.ratio * W;
                break;
            }
        }

        return maxValue;
    }
}
```
- **Time Complexity**: \(O(n \log n)\) (for sorting)
- **Space Complexity**: \(O(n)\)

---

### **3. Unbounded Knapsack Problem**
- **Problem**: Each item can be included an unlimited number of times.

**Dynamic Programming Solution**:
```java
public class UnboundedKnapsack {
    public static int unboundedKnapsack(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[] dp = new int[W + 1];

        for (int w = 0; w <= W; w++) {
            for (int i = 0; i < n; i++) {
                if (weights[i] <= w) {
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                }
            }
        }

        return dp[W];
    }
}
```
- **Time Complexity**: \(O(n \cdot W)\)
- **Space Complexity**: \(O(W)\)

---

### **4. Bounded Knapsack Problem**
- **Problem**: Similar to 0/1 Knapsack, but each item can be included at most a specific number of times.

**Dynamic Programming Solution**:
```java
public class BoundedKnapsack {
    public static int boundedKnapsack(int[] weights, int[] values, int[] counts, int W) {
        int n = weights.length;
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < counts[i]; k++) {
                for (int w = W; w >= weights[i]; w--) {
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                }
            }
        }

        return dp[W];
    }
}
```
- **Time Complexity**: \(O(n \cdot W \cdot \text{maxCount})\)
- **Space Complexity**: \(O(W)\)

---

### **5. Subset Sum Problem**
- **Problem**: A variation where each item has no value, and the goal is to determine if a subset exists with a given sum.

**Dynamic Programming Solution**:
```java
public class SubsetSum {
    public static boolean subsetSum(int[] nums, int target) {
        int n = nums.length;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }

        return dp[target];
    }
}
```
- **Time Complexity**: \(O(n \cdot \text{target})\)
- **Space Complexity**: \(O(\text{target})\)

---

### **6. Partition Equal Subset Sum**
- **Problem**: Check if an array can be partitioned into two subsets with equal sums.

**Solution**:
```java
public class PartitionEqualSubsetSum {
    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;

        if (total % 2 != 0) return false;

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
        }

        return dp[target];
    }
}
```
- **Time Complexity**: \(O(n \cdot \text{target})\)
- **Space Complexity**: \(O(\text{target})\)

---

### Summary of Knapsack Variations

| **Variation**                   | **Constraint**                                               | **Approach**          | **Time Complexity**   |
|---------------------------------|-------------------------------------------------------------|-----------------------|-----------------------|
| 0/1 Knapsack                   | Each item can be included at most once                      | Dynamic Programming   | \(O(n \cdot W)\)      |
| Fractional Knapsack            | Items can be divided into fractions                         | Greedy                | \(O(n \log n)\)       |
| Unbounded Knapsack             | Each item can be included unlimited times                   | Dynamic Programming   | \(O(n \cdot W)\)      |
| Bounded Knapsack               | Each item has a specific count limit                        | Dynamic Programming   | \(O(n \cdot W \cdot \text{maxCount})\) |
| Subset Sum                     | Determine if a subset with a given sum exists               | Dynamic Programming   | \(O(n \cdot \text{target})\) |
| Partition Equal Subset Sum     | Divide array into two subsets with equal sums               | Dynamic Programming   | \(O(n \cdot \text{target})\) |

Would you like to explore a specific variation in more detail or see additional examples?


//
Here are **additional solutions** to the 0/1 Knapsack Problem, covering different variations and optimizations:

---

### **1. Space-Optimized Dynamic Programming (1D Array)**

Instead of using a 2D array, use a 1D array (`dp`) where each index represents the maximum value achievable for a given weight.

**Implementation**:
```java
public class Knapsack {
    public static int knapsack01(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int w = W; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }

        return dp[W];
    }
}
```
- **Time Complexity**: \(O(n \cdot W)\)
- **Space Complexity**: \(O(W)\)

---

### **2. Recursive Solution (Top-Down Approach)**

Use recursion to explore all possibilities and memoization to optimize repeated computations.

**Implementation**:
```java
public class Knapsack {
    public static int knapsack01(int[] weights, int[] values, int W) {
        int[][] memo = new int[weights.length][W + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return knapsackHelper(weights, values, W, weights.length - 1, memo);
    }

    private static int knapsackHelper(int[] weights, int[] values, int W, int index, int[][] memo) {
        if (index < 0 || W == 0) return 0;

        if (memo[index][W] != -1) return memo[index][W];

        // Exclude current item
        int exclude = knapsackHelper(weights, values, W, index - 1, memo);

        // Include current item (if it fits)
        int include = 0;
        if (weights[index] <= W) {
            include = values[index] + knapsackHelper(weights, values, W - weights[index], index - 1, memo);
        }

        memo[index][W] = Math.max(exclude, include);
        return memo[index][W];
    }
}
```
- **Time Complexity**: \(O(n \cdot W)\) (due to memoization)
- **Space Complexity**: \(O(n \cdot W)\) (for memo array and recursion stack)

---

### **3. Iterative Bottom-Up Dynamic Programming**

Instead of recursion, use a standard bottom-up approach with a 2D array.

**Implementation**:
```java
public class Knapsack {
    public static int knapsack01(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }
}
```
- **Time Complexity**: \(O(n \cdot W)\)
- **Space Complexity**: \(O(n \cdot W)\)

---

### **4. Optimized Recursive Solution (Without Memoization)**

This approach explores all possibilities but doesn't optimize with memoization. It's less efficient but useful for understanding the problem.

**Implementation**:
```java
public class Knapsack {
    public static int knapsack01(int[] weights, int[] values, int W) {
        return knapsackHelper(weights, values, W, weights.length - 1);
    }

    private static int knapsackHelper(int[] weights, int[] values, int W, int index) {
        if (index < 0 || W == 0) return 0;

        // Exclude current item
        int exclude = knapsackHelper(weights, values, W, index - 1);

        // Include current item (if it fits)
        int include = 0;
        if (weights[index] <= W) {
            include = values[index] + knapsackHelper(weights, values, W - weights[index], index - 1);
        }

        return Math.max(exclude, include);
    }
}
```
- **Time Complexity**: \(O(2^n)\) (no memoization, exponential growth)
- **Space Complexity**: \(O(n)\) (recursion stack)

---

### **5. Using Bitmasking**

This approach uses bitmasking to generate all subsets and calculate the maximum value. It is computationally expensive and not practical for large inputs.

**Implementation**:
```java
public class Knapsack {
    public static int knapsack01(int[] weights, int[] values, int W) {
        int n = weights.length;
        int maxValue = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            int weight = 0, value = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    weight += weights[i];
                    value += values[i];
                }
            }

            if (weight <= W) {
                maxValue = Math.max(maxValue, value);
            }
        }

        return maxValue;
    }
}
```
- **Time Complexity**: \(O(2^n \cdot n)\)
- **Space Complexity**: \(O(1)\)

---

### Summary of Alternative Solutions

| **Approach**                     | **Time Complexity** | **Space Complexity** | **Notes**                                |
|-----------------------------------|---------------------|-----------------------|------------------------------------------|
| Space-Optimized DP (1D Array)     | \(O(n \cdot W)\)    | \(O(W)\)             | Efficient and commonly used.             |
| Recursive with Memoization        | \(O(n \cdot W)\)    | \(O(n \cdot W)\)     | Avoids recomputation, intuitive for recursion. |
| Iterative DP (Bottom-Up)          | \(O(n \cdot W)\)    | \(O(n \cdot W)\)     | Standard tabulation method.              |
| Recursive (Without Memoization)   | \(O(2^n)\)          | \(O(n)\)             | Inefficient, good for understanding.     |
| Bitmasking                        | \(O(2^n \cdot n)\)  | \(O(1)\)             | Useful for small `n` and brute-force solutions.|

---

Would you like detailed explanations, illustrations, or examples for any of these approaches?