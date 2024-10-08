# 3D DP

Certainly! The **3D Knapsack Problem** extends the classic knapsack problem by introducing additional constraints, requiring a more complex state representation. In this problem, we track three dimensions, allowing us to incorporate multiple constraints (e.g., weight, value, and time or other resources).

### Problem Statement
In a typical 3D knapsack scenario, you might be given:
- A list of items, each with a specific weight and value.
- An additional constraint that could represent a second resource (e.g., time, volume, or capacity).
- A maximum weight limit and a maximum constraint for the second resource.

The goal is to maximize the total value of the items that can be packed into the knapsack without exceeding the weight limit and the limit for the additional constraint.

### Example Scenario
Let’s say you have the following items:
- Item 1: Weight = 2, Value = 3, Time = 1
- Item 2: Weight = 3, Value = 4, Time = 2
- Item 3: Weight = 4, Value = 5, Time = 3

And the constraints:
- Maximum weight = 5
- Maximum time = 3

### Dynamic Programming Approach
We can use a 3D DP array `dp[i][w][t]` where:
- `i` represents the number of items considered.
- `w` represents the current weight capacity.
- `t` represents the current capacity for the additional constraint (e.g., time).

#### Transition Formula
For each item `i`, you have two choices:
1. **Do not include the item**: In this case, the value remains the same as the previous state:
   \[
   dp[i][w][t] = dp[i - 1][w][t]
   \]

2. **Include the item**: This is valid only if the current weight `w` is greater than or equal to the item's weight and the current constraint `t` is greater than or equal to the item's time:
   \[
   dp[i][w][t] = \max(dp[i][w][t], dp[i - 1][w - \text{weight}[i - 1]][t - \text{time}[i - 1]] + \text{value}[i - 1])
   \]

### Java Implementation
Here’s the complete Java implementation of the 3D Knapsack problem:

```java
public class Knapsack3D {
    public int knapsackWithConstraint(int[] weights, int[] values, int[] times, int maxWeight, int maxTime) {
        int n = weights.length;
        int[][][] dp = new int[n + 1][maxWeight + 1][maxTime + 1]; // 3D DP array

        // Fill the DP array
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                for (int t = 0; t <= maxTime; t++) {
                    // Case when we don't take the item
                    dp[i][w][t] = dp[i - 1][w][t];

                    // Case when we take the item
                    if (w >= weights[i - 1] && t >= times[i - 1]) {
                        dp[i][w][t] = Math.max(dp[i][w][t], 
                                dp[i - 1][w - weights[i - 1]][t - times[i - 1]] + values[i - 1]);
                    }
                }
            }
        }
        return dp[n][maxWeight][maxTime]; // The maximum value we can get
    }

    public static void main(String[] args) {
        Knapsack3D ks = new Knapsack3D();
        int[] weights = {2, 3, 4};  // Weights of the items
        int[] values = {3, 4, 5};   // Values of the items
        int[] times = {1, 2, 3};    // Time/resource for each item
        int maxWeight = 5;          // Maximum weight capacity
        int maxTime = 3;            // Maximum time/resource capacity
        
        // Calculate and print the maximum value
        System.out.println(ks.knapsackWithConstraint(weights, values, times, maxWeight, maxTime)); // Output: 7
    }
}
```

### Explanation of the Code
1. **Initialization**: A 3D array `dp` is created to store maximum values for all combinations of items, weights, and time constraints.

2. **Filling the DP Array**: For each item:
    - It calculates the maximum value for each combination of weight and time by considering both cases: including and excluding the item.
    - If including the item is feasible (weight and time constraints are not exceeded), it updates the DP table.

3. **Result**: The maximum value that can be obtained with the given constraints is stored in `dp[n][maxWeight][maxTime]`.

### Complexity
- **Time Complexity**: \(O(n \times \text{maxWeight} \times \text{maxTime})\)
- **Space Complexity**: \(O(n \times \text{maxWeight} \times \text{maxTime})\)

### Applications
- The 3D Knapsack problem can be applied in various fields, including resource allocation, budgeting, project selection, and optimization in logistics where multiple constraints exist.

This approach effectively captures the essence of dynamic programming by breaking down the problem into manageable states and solving them recursively, leveraging previously computed results to build the final solution.

# Knapsack 0/1

The **0/1 Knapsack Problem** is a classic problem in combinatorial optimization. In this problem, we have a set of items, each with a weight and a value, and we need to determine the maximum value we can carry in a knapsack of a fixed capacity. The key constraint is that we can either take an item (1) or leave it (0), hence the name "0/1 Knapsack".

### Problem Statement
Given:
- `weights[]`: an array of weights of the items.
- `values[]`: an array of values of the items.
- `W`: the maximum weight capacity of the knapsack.

### Objective
Maximize the total value in the knapsack without exceeding the weight capacity.

### Approach
To solve the 0/1 Knapsack Problem and find all possible combinations of items that yield the maximum value, we can use a recursive backtracking approach along with dynamic programming to store intermediate results.

### 1. Dynamic Programming Approach
We'll first create a DP solution to find the maximum value, and then a backtracking function to extract all combinations of items that contribute to this maximum value.

### Java Implementation
Here’s a complete Java implementation:

```java
import java.util.ArrayList;
import java.util.List;

public class Knapsack01 {

    // Method to find the maximum value using dynamic programming
    public int knapsackDP(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W]; // Maximum value
    }

    // Backtracking method to find all combinations contributing to the maximum value
    public List<List<Integer>> findAllSolutions(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        // Fill the DP table again to keep track of max value
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // List to store all combinations
        List<List<Integer>> allSolutions = new ArrayList<>();
        findCombinations(weights, values, n, W, dp, new ArrayList<>(), allSolutions);
        return allSolutions;
    }

    // Recursive function to find combinations
    private void findCombinations(int[] weights, int[] values, int i, int w, int[][] dp,
                                   List<Integer> current, List<List<Integer>> allSolutions) {
        if (i == 0 || w == 0) {
            if (dp[i][w] == 0) {
                allSolutions.add(new ArrayList<>(current));
            }
            return;
        }

        // If the item is included
        if (dp[i][w] != dp[i - 1][w]) {
            current.add(i - 1); // include the item (0-based index)
            findCombinations(weights, values, i - 1, w - weights[i - 1], dp, current, allSolutions);
            current.remove(current.size() - 1); // backtrack
        }

        // If the item is not included
        findCombinations(weights, values, i - 1, w, dp, current, allSolutions);
    }

    public static void main(String[] args) {
        Knapsack01 knapsack = new Knapsack01();
        int[] weights = {1, 3, 4, 5}; // Weights of the items
        int[] values = {1, 4, 5, 7};   // Values of the items
        int W = 7;                    // Maximum weight capacity

        // Find maximum value
        int maxValue = knapsack.knapsackDP(weights, values, W);
        System.out.println("Maximum Value: " + maxValue);

        // Find all combinations contributing to the maximum value
        List<List<Integer>> allSolutions = knapsack.findAllSolutions(weights, values, W);
        System.out.println("All combinations of items contributing to the maximum value:");
        for (List<Integer> solution : allSolutions) {
            System.out.println(solution);
        }
    }
}
```

### Explanation of the Code

1. **Dynamic Programming Method (`knapsackDP`)**:
    - Create a DP table `dp[i][w]`, where `i` represents the number of items considered and `w` represents the current weight capacity.
    - Fill the table using the 0/1 knapsack logic:
        - If the item's weight is less than or equal to the current capacity, take the maximum of either including or excluding the item.
        - If not, simply carry forward the previous maximum value.

2. **Finding All Solutions**:
    - The `findAllSolutions` method first fills the DP table to get the maximum value.
    - It then uses a recursive method `findCombinations` to backtrack through the DP table and find all combinations of items that contribute to this maximum value.
    - If an item is included (as determined by comparing DP values), it's added to the current combination, and the function is recursively called for the remaining items and reduced weight.
    - If not included, it continues exploring other options.

3. **Output**:
    - The `main` method demonstrates how to use the above methods, printing the maximum value and all combinations of item indices that lead to this value.

### Time Complexity
- **Time Complexity**:
    - The time complexity of the DP approach is \(O(n \times W)\), where \(n\) is the number of items and \(W\) is the maximum weight capacity of the knapsack.
    - The time complexity for finding all combinations will vary depending on the number of valid combinations but can be up to \(O(2^n)\) in the worst case, as it explores all possible subsets.

- **Space Complexity**:
    - The space complexity is \(O(n \times W)\) due to the DP table. The recursive call stack may also contribute to the space usage, but it's dominated by the DP array.

### Conclusion
This implementation captures both the maximum value achievable with the given constraints and lists all the combinations of items that contribute to that value. The 0/1 Knapsack Problem is widely applicable in resource allocation scenarios, budget management, and various optimization tasks in computer science and operations research.


# Other variations

In addition to the **0/1 Knapsack Problem**, there are several other variations of the knapsack problem, each with unique characteristics and constraints. Here are a few notable ones:

### 1. Fractional Knapsack Problem
In the **Fractional Knapsack Problem**, you can take fractions of an item rather than being restricted to either taking the whole item or leaving it. This problem can be solved using a greedy approach.

#### Problem Statement
- Given a set of items, each with a weight and a value, and a maximum weight capacity, the goal is to maximize the total value in the knapsack by taking fractions of items.

#### Approach
1. Calculate the value-to-weight ratio for each item.
2. Sort the items based on this ratio in descending order.
3. Start adding items to the knapsack until the capacity is full:
    - If the entire item can fit, add it completely.
    - If not, add the fractional part of the item that fits.

#### Java Implementation
Here’s how you might implement the Fractional Knapsack Problem in Java:

```java
import java.util.Arrays;
import java.util.Comparator;

class Item {
    int value;
    int weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {
    public double getMaxValue(Item[] items, int capacity) {
        // Sort items by value/weight ratio
        Arrays.sort(items, Comparator.comparingDouble(item -> (double) item.value / item.weight).reversed());

        double totalValue = 0.0;
        for (Item item : items) {
            if (capacity == 0) {
                break; // Knapsack is full
            }

            if (item.weight <= capacity) {
                // Take the whole item
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                // Take the fractional part of the item
                totalValue += item.value * ((double) capacity / item.weight);
                capacity = 0; // Knapsack is now full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int capacity = 50;

        FractionalKnapsack ks = new FractionalKnapsack();
        System.out.println("Maximum value in Fractional Knapsack: " + ks.getMaxValue(items, capacity));
    }
}
```

### 2. Bounded Knapsack Problem
The **Bounded Knapsack Problem** allows you to have a limited number of each item rather than just one. Each item can be included in the knapsack a specific number of times.

#### Problem Statement
- Given a set of items, each with a weight, value, and maximum quantity available, maximize the total value in the knapsack without exceeding its capacity.

#### Approach
Use a dynamic programming approach similar to the 0/1 Knapsack Problem but loop through each item for the number of times it can be included.

### 3. Unbounded Knapsack Problem
The **Unbounded Knapsack Problem** allows you to take an unlimited number of each item. This means you can use each item as many times as you want.

#### Problem Statement
- Given a set of items, each with a weight and a value, maximize the total value in the knapsack without exceeding its capacity.

#### Approach
Use a dynamic programming approach similar to the 0/1 Knapsack Problem, but for each item, iterate through all possible weights.

#### Java Implementation
Here's how to implement the Unbounded Knapsack Problem in Java:

```java
public class UnboundedKnapsack {
    public int getMaxValue(int[] weights, int[] values, int W) {
        int n = values.length;
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int w = weights[i]; w <= W; w++) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }

        return dp[W]; // Maximum value
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3}; // Weights of the items
        int[] values = {10, 15, 40}; // Values of the items
        int W = 7; // Maximum weight capacity

        UnboundedKnapsack ks = new UnboundedKnapsack();
        System.out.println("Maximum value in Unbounded Knapsack: " + ks.getMaxValue(weights, values, W));
    }
}
```

### Summary of Variations
- **0/1 Knapsack**: Each item can be included at most once.
- **Fractional Knapsack**: You can include fractions of an item.
- **Bounded Knapsack**: Each item can be included a limited number of times.
- **Unbounded Knapsack**: You can include an unlimited number of each item.

### Time Complexity
- **0/1 Knapsack**: \(O(n \times W)\)
- **Fractional Knapsack**: \(O(n \log n)\) due to sorting items based on value-to-weight ratio.
- **Bounded Knapsack**: \(O(n \times W)\), similar to 0/1 Knapsack.
- **Unbounded Knapsack**: \(O(n \times W)\), iterating through all possible weights for each item.

### Applications
Different knapsack problems are used in various fields, such as:
- Resource allocation.
- Budget management.
- Cargo loading.
- Investment portfolio selection.

By understanding these variations, you can apply the appropriate algorithm to optimize resource allocation in your specific context.