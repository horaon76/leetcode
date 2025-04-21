package Subarray;
/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less
 * than 10-5 will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 * **/
public class MaxAverageSubarray643 {

    public static Double findAverageSum(int[] nums, int k){
        Double average = 0.0;
        int sum = 0;
        for(int i = 0; i<k; i++){
            sum += nums[i];
        }
        average = (double) (sum / k);
        for(int i = k; i< nums.length; i++){
            sum += nums[i] - nums[i - k];
            Double currentAverage = (double) sum / k;
            average = Math.max(average, currentAverage);
        }
        return average;
    }

    public static void main(String args[]){
        int k = 4;
        int[] nums = new int[]{1,12,-5,-6,50,3};
        int[] nums1 = new int[]{1,1,1,1,1,1};
        System.out.println(findAverageSum(nums, k));
        System.out.println(findAverageSum(nums1, k));
    }
}
//Complexity:
//Time Complexity:
//𝑂
//(
//𝑛
//)
//O(n)
//We iterate through the array once.
//
//Space Complexity:
//𝑂
//(
//1
//)
//O(1)
//Only constant space is used.
   Yes, an alternative approach to solve LeetCode 643 is to precompute a **prefix sum array** and use it to calculate the sum of subarrays of length \( k \) in constant time.

---

### Approach 2: Using Prefix Sum

#### Intuition:
The prefix sum array allows us to compute the sum of any subarray in \( O(1) \) time. The sum of a subarray from index \( i \) to \( j \) can be calculated as:

\[
\text{Sum} = \text{prefixSum}[j + 1] - \text{prefixSum}[i]
\]

We can iterate through all subarrays of length \( k \) and find the maximum sum.

---

### Steps:
1. Create a prefix sum array where `prefixSum[i]` stores the sum of elements from the start of the array up to index \( i-1 \).
2. Iterate through all subarrays of length \( k \):
    - Compute their sum using the prefix sum array.
    - Track the maximum sum encountered.
3. Compute the maximum average as \( \text{maxSum} / k \).

---

### Code:

```java
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(findMaxAverage(nums, k));
    }

    public static double findMaxAverage(int[] nums, int k) {
        // Step 1: Compute the prefix sum array
        int n = nums.length;
        int[] prefixSum = new int[n + 1]; // prefixSum[i] stores sum of nums[0] to nums[i-1]
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        // Step 2: Iterate through subarrays of length k
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i <= n - k; i++) {
            int currentSum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        // Step 3: Return the maximum average
        return (double) maxSum / k;
    }
}
```

---

### Explanation:

1. **Prefix Sum Construction**:
    - `prefixSum[i]` stores the sum of elements from `nums[0]` to `nums[i-1]`.
    - For example, for `nums = [1, 12, -5, -6, 50, 3]`, the `prefixSum` array will be:
      \[
      \text{prefixSum} = [0, 1, 13, 8, 2, 52, 55]
      \]

2. **Iterate Through Subarrays**:
    - For every subarray of length \( k \), calculate its sum using:
      \[
      \text{currentSum} = \text{prefixSum}[i + k] - \text{prefixSum}[i]
      \]
    - Update the `maxSum` with the largest sum encountered.

3. **Compute Average**:
    - Divide `maxSum` by \( k \) to get the maximum average.

---

### Complexity:

- **Time Complexity**:
    - Building the prefix sum array takes \( O(n) \).
    - Iterating through all subarrays of length \( k \) takes \( O(n) \).
    - Total: \( O(n) \).

- **Space Complexity**:
    - The prefix sum array requires \( O(n) \) space.

---

### Example Walkthrough:

**Input**: `nums = [1, 12, -5, -6, 50, 3], k = 4`

1. **Prefix Sum**:
   \[
   \text{prefixSum} = [0, 1, 13, 8, 2, 52, 55]
   \]

2. **Compute Subarray Sums**:
    - Subarray `[1, 12, -5, -6]`: \( \text{currentSum} = \text{prefixSum}[4] - \text{prefixSum}[0] = 2 \)
    - Subarray `[12, -5, -6, 50]`: \( \text{currentSum} = \text{prefixSum}[5] - \text{prefixSum}[1] = 51 \)
    - Subarray `[-5, -6, 50, 3]`: \( \text{currentSum} = \text{prefixSum}[6] - \text{prefixSum}[2] = 42 \)

3. **Maximum Average**:
    - \( \text{maxSum} = 51 \), so \( \text{maxAverage} = 51 / 4 = 12.75 \).

---

### Comparison to Sliding Window:

- **Sliding Window** is more space-efficient with \( O(1) \) space but requires maintaining a dynamic sum.
- **Prefix Sum** is easier to implement and can be useful if subarray sums need to be queried multiple times.

Both methods achieve \( O(n) \) time complexity.