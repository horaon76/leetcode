package BinarySearch;
/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 *
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 * **/

public class MissingNumber268 {
}

LeetCode 268: **Missing Number**

        ### **Problem Statement**
Given an array containing `n` distinct numbers taken from the range `[0, n]`, find the one that is missing from the array.

**Example:**
        ```plaintext
Input: nums = [3, 0, 1]
Output: 2
        ```

        ### **Approaches to Solve**

        #### **1. Summation Formula Approach**

The sum of the numbers in the range `[0, n]` is given by the formula:
        \[
S = \frac{n \times (n + 1)}{2}
        \]
Where `n` is the length of the array.

We can find the missing number by calculating the expected sum `S` and subtracting the sum of the numbers in the array. The difference will be the missing number.

#### **Code**
        ```java
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
}
```

        #### **Time Complexity**
        - **Time Complexity**: \(O(n)\), because we iterate through the array once to compute the sum.
- **Space Complexity**: \(O(1)\), because we only use a few extra variables for calculation.

---

        #### **2. XOR Approach**

The XOR approach works on the principle that when we XOR two equal numbers, the result is `0`. If we XOR all numbers in the range `[0, n]` with the numbers in the array, the duplicate numbers will cancel each other out, leaving only the missing number.

#### **Code**
        ```java
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int xor = 0;

        // XOR all the numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }

        // XOR all the numbers in the array
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}
```

        #### **Time Complexity**
        - **Time Complexity**: \(O(n)\), as we loop through the array and the range `[0, n]`.
        - **Space Complexity**: \(O(1)\), because we only use a constant amount of extra space for the XOR operation.

---

        #### **3. Sorting Approach**

We can also solve the problem by sorting the array and checking for the first index where the value doesn’t match the index. However, sorting takes \(O(n \log n)\) time, which is less efficient compared to the other two methods.

        #### **Code**
        ```java
import java.util.Arrays;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);  // Sort the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {  // If the number doesn't match its index
                return i;
            }
        }
        return nums.length;  // If no number is missing, the missing number is n
    }
}
```

        #### **Time Complexity**
        - **Time Complexity**: \(O(n \log n)\) due to sorting.
- **Space Complexity**: \(O(1)\), assuming we do not consider the space complexity of the sorting algorithm.

---

        #### **4. Index Marking Approach (Mutating the Input Array)**

If mutating the input array is allowed, we can use the input array itself to mark the visited elements by negating the values at those indices.

        However, this approach is not often recommended unless the problem explicitly allows for modifications to the input array.

#### **Code**
        ```java
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        // Mark the numbers present in the array
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num < n) {
                nums[num] = -Math.abs(nums[num]);
            }
        }

        // Find the first unmarked index, which is the missing number
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        return n;  // If all numbers are marked, the missing number is n
    }
}
```

        #### **Time Complexity**
        - **Time Complexity**: \(O(n)\), as we iterate through the array.
        - **Space Complexity**: \(O(1)\), because we are mutating the array itself.

---

        ### **Comparison of Solutions**

        | **Approach**               | **Time Complexity** | **Space Complexity** |
        |----------------------------|---------------------|----------------------|
        | Summation Formula          | \(O(n)\)            | \(O(1)\)             |
        | XOR Approach               | \(O(n)\)            | \(O(1)\)             |
        | Sorting Approach           | \(O(n \log n)\)     | \(O(1)\)             |
        | Index Marking (Mutating Array) | \(O(n)\)         | \(O(1)\)             |

        ---

        ### **Which Solution to Use?**

        1. **Summation Formula**: Best when you want a quick and easy solution with minimal space.
        2. **XOR Approach**: An elegant solution that doesn't require any extra memory besides the result.
        3. **Sorting**: Only use this if you're okay with sorting the array and want a solution that is easy to understand.
        4. **Index Marking**: This approach is less common and should only be used if mutating the input array is acceptable.
