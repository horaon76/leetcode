package DP;
//You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
//
//You can either start from the step with index 0, or the step with index 1.
//
//Return the minimum cost to reach the top of the floor.
//
//
//
//Example 1:
//
//Input: cost = [10,15,20]
//Output: 15
//Explanation: You will start at index 1.
//- Pay 15 and climb two steps to reach the top.
//The total cost is 15.
//Example 2:
//
//Input: cost = [1,100,1,1,1,100,1,1,100,1]
//Output: 6
//Explanation: You will start at index 0.
//- Pay 1 and climb two steps to reach index 2.
//- Pay 1 and climb two steps to reach index 4.
//- Pay 1 and climb two steps to reach index 6.
//- Pay 1 and climb one step to reach index 7.
//- Pay 1 and climb two steps to reach index 9.
//- Pay 1 and climb one step to reach the top.
//The total cost is 6.
//
//
//Constraints:
//
//2 <= cost.length <= 1000
//0 <= cost[i] <= 999
public class MinCostClimbingStair746 {
}

LeetCode Problem 746, **Min Cost Climbing Stairs**, is a dynamic programming problem. The task is to find the minimum cost to reach the top of a staircase, where you can start from either the 0th or 1st step, and pay a cost associated with each step.

---

### Problem Statement
You are given an integer array `cost` where `cost[i]` is the cost of the \(i\)-th step on a staircase. Once you pay the cost, you can either climb one or two steps. You need to minimize the total cost to reach the top.

---

Here are all possible solutions in Java:

---

### 1. **Recursive Solution (Brute Force)**

This approach tries all possible paths but is inefficient for larger inputs.

```java
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        return Math.min(minCost(cost, cost.length - 1), minCost(cost, cost.length - 2));
    }

    private int minCost(int[] cost, int i) {
        if (i < 0) return 0;
        if (i == 0 || i == 1) return cost[i];
        return cost[i] + Math.min(minCost(cost, i - 1), minCost(cost, i - 2));
    }
}
```

*Time Complexity:* \( O(2^n) \)  
*Space Complexity:* \( O(n) \) (call stack)

---

### 2. **Recursive Solution with Memoization (Top-Down DP)**

This optimizes the recursive solution by caching results of subproblems.

```java
import java.util.HashMap;
import java.util.Map;

public class MinCostClimbingStairs {
    private Map<Integer, Integer> memo = new HashMap<>();

    public int minCostClimbingStairs(int[] cost) {
        return Math.min(minCost(cost, cost.length - 1), minCost(cost, cost.length - 2));
    }

    private int minCost(int[] cost, int i) {
        if (i < 0) return 0;
        if (memo.containsKey(i)) return memo.get(i);
        int result = cost[i] + Math.min(minCost(cost, i - 1), minCost(cost, i - 2));
        memo.put(i, result);
        return result;
    }
}
```

*Time Complexity:* \( O(n) \)  
*Space Complexity:* \( O(n) \)

---

### 3. **Dynamic Programming (Bottom-Up)**

Iteratively build the solution using an array.

```java
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
```

*Time Complexity:* \( O(n) \)  
*Space Complexity:* \( O(n) \)

---

### 4. **Dynamic Programming with Constant Space**

Optimize space by keeping track of only the last two steps.

```java
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int prev1 = cost[1];
        int prev2 = cost[0];
        for (int i = 2; i < cost.length; i++) {
            int current = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return Math.min(prev1, prev2);
    }
}
```

*Time Complexity:* \( O(n) \)  
*Space Complexity:* \( O(1) \)

---

### 5. **In-Place Modification**

Use the input array `cost` as the `dp` array to save space.

```java
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
}
```

*Time Complexity:* \( O(n) \)  
*Space Complexity:* \( O(1) \)

---

### Comparison of Approaches

| **Approach**              | **Time Complexity** | **Space Complexity** | **Notes**                                  |
|---------------------------|---------------------|----------------------|--------------------------------------------|
| Recursive (Brute Force)   | \( O(2^n) \)        | \( O(n) \)           | Slow and inefficient for large \( n \).   |
| Memoization (Top-Down DP) | \( O(n) \)          | \( O(n) \)           | Good for medium-sized inputs.             |
| Bottom-Up DP              | \( O(n) \)          | \( O(n) \)           | Iterative, easy to implement.             |
| Constant Space DP         | \( O(n) \)          | \( O(1) \)           | Optimal for time and space.               |
| In-Place Modification     | \( O(n) \)          | \( O(1) \)           | Efficient and does not require extra memory. |

The **Constant Space DP** or **In-Place Modification** approach is typically preferred for optimal performance.