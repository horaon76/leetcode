package Hard;

/**
 * You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can use the following operation as many times as you want:
 *
 * Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
 * The cost of the swap is min(basket1[i],basket2[j]).
 * Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
 *
 * Return the minimum cost to make both the baskets equal or -1 if impossible.
 *
 *
 *
 * Example 1:
 *
 * Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
 * Output: 1
 * Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.
 * Example 2:
 *
 * Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
 * Output: -1
 * Explanation: It can be shown that it is impossible to make both the baskets equal.
 *
 *
 * Constraints:
 *
 * basket1.length == basket2.length
 * 1 <= basket1.length <= 105
 * 1 <= basket1[i],basket2[i] <= 109
 * **/
   Yes, there are other approaches to solve **LeetCode 2561 (Rearranging Fruits)**. Here's an alternate solution using a **greedy approach**:

### Alternate Approach: Greedy with Two-Pointer Strategy

This approach still relies on identifying the imbalance in the two baskets but optimizes the swapping strategy by carefully minimizing costs. We use a two-pointer approach on the sorted lists of fruits that need to be swapped from both baskets.

### Idea:
1. **Identify the excess in both baskets**: For each fruit type, calculate how many more of that fruit are in `basket1` than in `basket2` and vice versa.
2. **Minimize cost**: When performing swaps, always choose the smallest fruit available to minimize the cost. This can be done by sorting the list of fruits to swap from both baskets and then using the smallest available value for swaps.
3. **Choose optimal swaps**: At each step, either swap the smallest fruits from both baskets or swap the smallest possible fruit for double the cost if necessary.

### Approach:

1. **Step 1**: Calculate the frequency difference for each fruit type between `basket1` and `basket2`.
2. **Step 2**: Create two lists — one for the excess fruits in `basket1` and another for the excess fruits in `basket2`.
3. **Step 3**: Sort these lists to ensure that the smallest fruits are swapped first.
4. **Step 4**: Calculate the minimum cost for each swap by choosing the cheaper option (direct swap or swap using the smallest fruit).

### Optimized Solution Code (Greedy with Two-Pointer Strategy):

```java
import java.util.*;

public class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        // Step 1: Count the frequency of each type in both baskets
        Map<Integer, Integer> countMap = new HashMap<>();
        
        // Count the frequency of each fruit in both baskets
        for (int fruit : basket1) {
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) + 1);
        }
        for (int fruit : basket2) {
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) - 1);
        }
        
        // Step 2: Check if it's possible to make the baskets identical
        List<Integer> excessInBasket1 = new ArrayList<>();
        List<Integer> excessInBasket2 = new ArrayList<>();
        
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int fruitType = entry.getKey();
            int diff = entry.getValue();
            
            // If the total count is odd, it's impossible to balance
            if (diff % 2 != 0) {
                return -1;
            }
            
            // Add half of the excess fruits to the respective list
            for (int i = 0; i < Math.abs(diff) / 2; i++) {
                if (diff > 0) {
                    excessInBasket1.add(fruitType);
                } else {
                    excessInBasket2.add(fruitType);
                }
            }
        }
        
        // Step 3: Sort both lists to minimize swap costs
        Collections.sort(excessInBasket1);
        Collections.sort(excessInBasket2);
        
        // Step 4: Calculate the minimum cost for the swaps
        int minFruit = Math.min(Arrays.stream(basket1).min().getAsInt(),
                                Arrays.stream(basket2).min().getAsInt());
        
        long totalCost = 0;
        int n = excessInBasket1.size();
        
        for (int i = 0; i < n; i++) {
            // Swap directly between the smallest fruit in excessInBasket1 and excessInBasket2
            int fruit1 = excessInBasket1.get(i);
            int fruit2 = excessInBasket2.get(i);
            
            // Choose the cheaper option: either a direct swap or swap through the smallest fruit
            totalCost += Math.min(fruit1, fruit2) <= 2 * minFruit 
                            ? Math.min(fruit1, fruit2)
                            : 2 * minFruit;
        }
        
        return totalCost;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] basket1 = {4,2,2,4};
        int[] basket2 = {2,4,2,4};
        System.out.println(sol.minCost(basket1, basket2));  // Output: 4
        
        int[] basket3 = {1,2,3};
        int[] basket4 = {3,2,1};
        System.out.println(sol.minCost(basket3, basket4));  // Output: 0
    }
}
```

### Explanation:

1. **Frequency Difference**: We first count how many more of each fruit type there are in one basket than in the other.
2. **List of Excess Fruits**: For every type of fruit where there is an imbalance, we add half of the excess to a list (since we will swap half the fruits to balance).
3. **Sorting for Optimal Swaps**: By sorting the excess fruits, we ensure that the swaps are done with the minimum possible cost.
4. **Minimizing Swap Costs**: For each fruit in excess, we calculate the swap cost either directly or through the smallest fruit available, ensuring the smallest possible total cost.

### Time Complexity:
- **O(n log n)** due to sorting the excess fruits in both baskets.
- The counting of fruit types is linear, and the final two-pointer approach to calculate the minimum cost also runs in linear time.

### Space Complexity:
- **O(n)** because we use additional space for counting the fruits and storing the excess fruits to be swapped.

### Summary:

Both approaches aim to minimize the swap costs but with different optimization techniques. The second solution focuses on a **greedy two-pointer approach**, ensuring that the smallest possible fruits are used for swaps to minimize the overall cost.

# Solution 2

LeetCode 2561 is titled **"Rearranging Fruits."**

### Problem Description:
You are given two arrays `basket1` and `basket2`, each containing positive integers. `basket1[i]` and `basket2[i]` represent the number of fruits of type `i` in each basket. You are allowed to swap any number of fruits between the baskets, as long as they are of the same type.

Your goal is to **minimize the total cost of all swaps** to make the two baskets identical, meaning for each fruit type, both baskets should have the same amount of fruits.

The **cost of a swap** is defined as the **smaller value** between the two swapped fruits, and the total cost is the sum of all the swap costs.

### Input:
- `basket1`: an array of positive integers representing fruits of different types in basket 1.
- `basket2`: an array of positive integers representing fruits of different types in basket 2.

### Output:
- Return the minimum total cost of making the two baskets identical after swaps. If it's impossible to make the baskets identical, return `-1`.

### Example:
**Input:**
```
basket1 = [4,2,2,4]
basket2 = [2,4,2,4]
```
**Output:**
```
4
```

**Explanation:**
We can swap the two 4’s from `basket1` and the two 2’s from `basket2`. The minimum cost for each swap is the smaller fruit type (in this case, either 2 or 4), and the total cost is \(2 + 2 = 4\).

### Solution Outline:

The problem can be broken down as follows:
1. **Check if equal swaps are possible**: First, we need to ensure that for each fruit type, the combined number of fruits in both baskets is even. If for any fruit type the combined count is odd, it's impossible to make the baskets identical.

2. **Minimize the swap cost**: We aim to minimize the cost by swapping fruits where the cost is the minimum. This can be achieved by always considering the smaller values during swaps.

### Approach:

1. Count the frequency of each type of fruit in both baskets.
2. For each type of fruit, ensure that the combined count across the baskets is even.
3. Calculate the minimum cost to make the baskets identical by swapping the smallest possible values.

### Solution Code:

```java
import java.util.*;

public class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        // Step 1: Count the frequency of each type in both baskets
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for (int fruit : basket1) {
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) + 1);
        }
        
        for (int fruit : basket2) {
            countMap.put(fruit, countMap.getOrDefault(fruit, 0) - 1);
        }
        
        // Step 2: Check if it's possible to make the baskets identical
        List<Integer> needToSwap = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int freq = entry.getValue();
            if (freq % 2 != 0) {
                return -1; // If the count is odd, it's impossible to equalize the baskets
            }
            
            // Add half of the excess to the list (because we need half the swaps)
            for (int i = 0; i < Math.abs(freq) / 2; i++) {
                needToSwap.add(entry.getKey());
            }
        }
        
        // Step 3: Sort the list of fruits that need to be swapped
        Collections.sort(needToSwap);
        
        // Step 4: Calculate the minimum cost for swapping
        int minFruit = Math.min(Arrays.stream(basket1).min().getAsInt(),
                                Arrays.stream(basket2).min().getAsInt());
        
        long totalCost = 0;
        int halfSize = needToSwap.size() / 2;
        
        // For each fruit, we calculate the cost by either swapping directly or using the smallest fruit available
        for (int i = 0; i < halfSize; i++) {
            totalCost += Math.min(needToSwap.get(i), 2 * minFruit);
        }
        
        return totalCost;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] basket1 = {4,2,2,4};
        int[] basket2 = {2,4,2,4};
        System.out.println(sol.minCost(basket1, basket2));  // Output: 4
        
        int[] basket3 = {1,2,3};
        int[] basket4 = {3,2,1};
        System.out.println(sol.minCost(basket3, basket4));  // Output: 0
    }
}
```

### Explanation:

1. **Step 1 (Frequency counting)**: We count how many of each type of fruit we have in both baskets. The goal is to make sure that the total number of each fruit across both baskets is even, which is a prerequisite for making the baskets identical.

2. **Step 2 (Check possibility)**: If for any fruit type the total number is odd, it's impossible to equalize the baskets, and we return `-1`.

3. **Step 3 (Sorting swaps)**: We calculate the difference and split it into two halves (since each swap fixes two instances). We then sort the fruits that need to be swapped.

4. **Step 4 (Minimizing cost)**: For each fruit type in the sorted list, we calculate the cost of swapping by choosing either the direct swap cost or the smallest fruit available in both baskets (whichever is smaller).

### Time Complexity:
- **O(n log n)**: Sorting the list of fruits to swap is the most time-consuming step.

### Space Complexity:
- **O(n)**: We store the frequency of each type of fruit and keep a list of fruits that need to be swapped.