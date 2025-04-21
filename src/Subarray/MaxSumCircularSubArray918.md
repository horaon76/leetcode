package Subarray;

public class MaxSumCircularSubArray918 {

    public static int maxSubarraySumCircular(int[] nums) {
        return 0;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,-2,3,-2};
        System.out.println(maxSubarraySumCircular(nums));
    }
}

### LeetCode 918: **Maximum Sum Circular Subarray**

---

### Problem Statement:

Given a circular integer array `nums`, return the maximum possible sum of a non-empty subarray of `nums`.

- A circular array means that the end of the array wraps around to the beginning.
- A subarray can only include each index once.

---

### Example:

#### Example 1:
**Input:**
```plaintext
nums = [1, -2, 3, -2]
```

**Output:**
```plaintext
3
```

**Explanation:**  
The maximum sum subarray is `[3]`.

---

#### Example 2:
**Input:**
```plaintext
nums = [5, -3, 5]
```

**Output:**
```plaintext
10
```

**Explanation:**  
The subarray `[5, 5]` includes wrapping around the end of the array.

---

#### Example 3:
**Input:**
```plaintext
nums = [-3, -2, -3]
```

**Output:**
```plaintext
-2
```

**Explanation:**  
The maximum sum subarray is `[-2]`. Wrapping does not help since all elements are negative.

---

### Approach:

1. **Two Cases:**
    - Case 1: The maximum sum subarray is a normal subarray (non-circular). Use **Kadane's Algorithm** to find the maximum sum.
    - Case 2: The maximum sum subarray is circular. In this case:
        - Calculate the **total sum** of the array.
        - Subtract the minimum subarray sum from the total sum to get the circular maximum sum.

2. **Edge Case:**
    - If all elements are negative, the result is the maximum element (wrapping doesn't help).

3. **Final Result:**
    - Return the maximum of the two cases:
      \[
      \text{Result} = \max(\text{Max Normal Subarray}, \text{Max Circular Subarray})
      \]

---

### Code:

```java
public class Solution {
    public static void main(String[] args) {
        int[] nums = {5, -3, 5};
        System.out.println(maxSubarraySumCircular(nums)); // Output: 10
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int maxNormal = kadane(nums);
        
        // If all numbers are negative, the max circular sum will be the maxNormal
        int totalSum = 0;
        for (int num : nums) totalSum += num;

        // Find the minimum subarray sum
        int minSum = kadaneInvert(nums);

        // Max circular sum = totalSum - minSum
        int maxCircular = totalSum - minSum;

        // Handle edge case where all elements are negative
        if (maxNormal < 0) return maxNormal;

        // Return the maximum of normal and circular subarray sums
        return Math.max(maxNormal, maxCircular);
    }

    // Standard Kadane's Algorithm to find max subarray sum
    private static int kadane(int[] nums) {
        int maxEndingHere = 0, maxSoFar = Integer.MIN_VALUE;
        for (int num : nums) {
            maxEndingHere = Math.max(num, maxEndingHere + num);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    // Kadane's Algorithm to find the minimum subarray sum
    private static int kadaneInvert(int[] nums) {
        int minEndingHere = 0, minSoFar = Integer.MAX_VALUE;
        for (int num : nums) {
            minEndingHere = Math.min(num, minEndingHere + num);
            minSoFar = Math.min(minSoFar, minEndingHere);
        }
        return minSoFar;
    }
}
```

---

### Explanation:

1. **Kadane's Algorithm (Normal Subarray):**
    - Finds the maximum sum of a non-circular subarray.

2. **Circular Subarray Calculation:**
    - Use the total sum of the array.
    - Subtract the minimum subarray sum from the total sum to get the maximum circular subarray sum.

3. **Edge Case:**
    - If all elements are negative, the maximum sum subarray is the largest single element.

---

### Complexity:

- **Time Complexity**: \( O(n) \)
    - \( O(n) \) for normal Kadane's algorithm.
    - \( O(n) \) for inverted Kadane's algorithm.

- **Space Complexity**: \( O(1) \)
    - Constant space for variables.

---

### Example Walkthrough:

#### Input:
```plaintext
nums = [5, -3, 5]
```

1. **Normal Kadane:**
    - Max normal subarray: \( 5 + -3 + 5 = 10 \).

2. **Circular Calculation:**
    - Total sum: \( 5 + -3 + 5 = 7 \).
    - Min subarray sum (inverted Kadane): \( -3 \).
    - Max circular subarray: \( 7 - (-3) = 10 \).

3. **Result:**
    - \( \max(10, 10) = 10 \).

**Output:**
```plaintext
10
```