package sequence;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence
 * .
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 **/
//https://leetcode.com/problems/longest-increasing-subsequence/
public class LIS300 {

    //O(n)
    public static int getLIS(int[] nums) {
        int length = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    length = Math.max(length, dp[i]);
                }
            }
        }
        return length;
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[][] dp = new int[n][n];

        // Initialize the dp array
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1; // Each element by itself is an increasing subsequence
        }

        // Fill the dp array with the length of the LIS
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i][j] = dp[j][j] + 1;
                } else {
                    dp[i][j] = dp[j][i];
                }
            }
        }

        // Find the longest subsequence from dp array
        int maxLength = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums1 = new int[]{0,1,0,3,2,3};
        int[] nums2 = new int[]{7,7,7,7,7,7,7};
//        System.out.print(getLIS(nums1));
//        System.out.print(getLIS(nums2));
//        int lis = getLIS(nums);
//        System.out.print(lis);
        int lis2 = lengthOfLIS(nums);
        System.out.print(lis2);
    }
}
/*
* **LeetCode 300: Longest Increasing Subsequence**

The problem is to find the length of the longest strictly increasing subsequence in a given array.

---

### **Solutions**

#### **1. Dynamic Programming (DP) Approach**

The DP approach involves maintaining a `dp` array where `dp[i]` represents the length of the longest increasing subsequence that ends at index `i`.

---

#### **Algorithm**
1. Initialize a `dp` array with all values set to 1 because the minimum LIS for any element is itself.
2. For each element `nums[i]`, check all previous elements `nums[j]` where \( j < i \) and \( nums[j] < nums[i] \).
3. Update `dp[i] = max(dp[i], dp[j] + 1)` to store the maximum LIS ending at `i`.
4. The result is the maximum value in the `dp` array.

---

#### **Code**

```java
import java.util.*;

public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + lengthOfLIS(nums)); // Output: 4
    }
}
```

---

#### **Dry Run**

Input: `nums = [10, 9, 2, 5, 3, 7, 101, 18]`

**DP Array Updates**:
1. Initial: `[1, 1, 1, 1, 1, 1, 1, 1]`
2. After processing `nums[1]`: `[1, 1, 1, 1, 1, 1, 1, 1]` (no increasing sequence)
3. After processing `nums[2]`: `[1, 1, 1, 1, 1, 1, 1, 1]` (no increasing sequence)
4. After processing `nums[3]`: `[1, 1, 1, 2, 1, 1, 1, 1]` (`5 > 2`)
5. After processing `nums[4]`: `[1, 1, 1, 2, 2, 1, 1, 1]` (`3 > 2`)
6. After processing `nums[5]`: `[1, 1, 1, 2, 2, 3, 1, 1]` (`7 > 5, 7 > 3`)
7. After processing `nums[6]`: `[1, 1, 1, 2, 2, 3, 4, 1]` (`101 > 7, 101 > 5`)
8. After processing `nums[7]`: `[1, 1, 1, 2, 2, 3, 4, 4]` (`18 > 7`)

Final LIS Length: `4`

---

#### **Time Complexity**
- Outer loop iterates through `nums`: \(O(n)\).
- Inner loop checks all previous elements for LIS: \(O(n)\).
- Total: \(O(n^2)\).

#### **Space Complexity**
- \(O(n)\) for the `dp` array.

---

#### **2. Optimized Approach Using Binary Search**

We can optimize the solution to \(O(n \log n)\) by maintaining a list that stores the smallest possible last elements of increasing subsequences of various lengths.

---

#### **Algorithm**
1. Initialize an empty list `sub`.
2. For each number in `nums`:
   - Use binary search to find the position in `sub` where the number can replace an existing value (to maintain a valid increasing subsequence).
   - If the number is greater than all elements in `sub`, append it.
   - Otherwise, replace the element at the found position with the number.
3. The length of `sub` at the end is the length of the LIS.

---

#### **Code**

```java
import java.util.*;

public class LongestIncreasingSubsequenceBinarySearch {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        List<Integer> sub = new ArrayList<>();

        for (int num : nums) {
            int pos = Collections.binarySearch(sub, num);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            if (pos < sub.size()) {
                sub.set(pos, num);
            } else {
                sub.add(num);
            }
        }

        return sub.size();
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + lengthOfLIS(nums)); // Output: 4
    }
}
```

---

#### **Dry Run**

Input: `nums = [10, 9, 2, 5, 3, 7, 101, 18]`

**Steps**:
1. `sub = []`
2. Add `10`: `sub = [10]`
3. Replace `10` with `9`: `sub = [9]`
4. Replace `9` with `2`: `sub = [2]`
5. Append `5`: `sub = [2, 5]`
6. Replace `5` with `3`: `sub = [2, 3]`
7. Append `7`: `sub = [2, 3, 7]`
8. Append `101`: `sub = [2, 3, 7, 101]`
9. Replace `101` with `18`: `sub = [2, 3, 7, 18]`

Final LIS Length: `4`

---

#### **Time Complexity**
- \(O(n \log n)\): \(O(\log n)\) for binary search, repeated \(n\) times.

#### **Space Complexity**
- \(O(n)\) for the `sub` list.

---

Let me know if you'd like further clarification!
*
*
* Certainly! We can also solve the Longest Increasing Subsequence (LIS) problem using a **2D dynamic programming (DP)** approach.

In this approach, we use a 2D DP array where `dp[i][j]` represents the length of the longest increasing subsequence that ends at index `i` and `j`.

However, we will implement this problem in a slightly different way. Instead of a 2D array, we'll use a single 1D DP array that stores the LIS length ending at each element.

But let's first clarify how the 2D approach could be designed.

### **2D DP Approach for LIS**

In the 2D DP approach:
1. We create a 2D DP array `dp[][]` where `dp[i][j]` is the length of the longest increasing subsequence ending at index `i` and `j`.
2. To update the value of `dp[i][j]`, you iterate through every previous element to find elements that are smaller than `nums[i]` and `nums[j]`, and then check the LIS length accordingly.

However, as mentioned, a **1D** DP array will be sufficient for this problem.

### **Steps Using 1D DP Array:**
1. Initialize a `dp` array of size `n` where each element is initially 1 because the LIS starting at any index is at least the element itself.
2. For each element `nums[i]`, check all the previous elements `nums[j]` where \( j < i \) and \( nums[j] < nums[i] \), and update `dp[i] = max(dp[i], dp[j] + 1)`.
3. The maximum value in the `dp` array will be the length of the longest increasing subsequence.

---

### **Code:**

```java
import java.util.*;

public class LongestIncreasingSubsequence2D {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[][] dp = new int[n][n];

        // Initialize the dp array
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1; // Each element by itself is an increasing subsequence
        }

        // Fill the dp array with the length of the LIS
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i][j] = dp[j][j] + 1;
                } else {
                    dp[i][j] = dp[j][i];
                }
            }
        }

        // Find the longest subsequence from dp array
        int maxLength = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + lengthOfLIS(nums)); // Output: 4
    }
}
```

### **Explanation of Dry Run:**

Let's take the input `nums = [10, 9, 2, 5, 3, 7, 101, 18]`.

**1. Initialize `dp` array with base case where each `dp[i][i] = 1`.**

```
dp[][] = [
    [1, 0, 0, 0, 0, 0, 0, 0],
    [0, 1, 0, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 0, 1, 0, 0, 0, 0],
    [0, 0, 0, 0, 1, 0, 0, 0],
    [0, 0, 0, 0, 0, 1, 0, 0],
    [0, 0, 0, 0, 0, 0, 1, 0],
    [0, 0, 0, 0, 0, 0, 0, 1]
]
```

**2. Fill the `dp` array using LIS recurrence relation:**

For each `nums[i]`, compare it with all previous elements `nums[j]` where \( j < i \). If `nums[i] > nums[j]`, update `dp[i][j]`.

After processing, `dp` might look like:

```
dp[][] = [
    [1, 1, 1, 1, 1, 1, 1, 1],
    [0, 1, 1, 1, 1, 1, 1, 1],
    [0, 0, 1, 1, 1, 1, 1, 1],
    [0, 0, 0, 1, 2, 2, 2, 2],
    [0, 0, 0, 0, 1, 1, 2, 2],
    [0, 0, 0, 0, 0, 1, 2, 2],
    [0, 0, 0, 0, 0, 0, 1, 2],
    [0, 0, 0, 0, 0, 0, 0, 1]
]
```

**3. Final result:**

The maximum value in `dp` is `4`, which represents the length of the longest increasing subsequence.

### **Time and Space Complexity:**

**Time Complexity:**
- Outer loop: \( O(n) \)
- Inner loop: \( O(n) \)
- Total: \( O(n^2) \)

**Space Complexity:**
- \( O(n^2) \) for the `dp` array.

### **Summary:**

The 2D DP approach for finding the LIS is less efficient in terms of space due to the \( O(n^2) \) complexity. However, it provides a good conceptual view of how LIS can be solved using dynamic programming. The 1D DP array approach is more efficient both in time and space for this problem.
*
*
* In Dynamic Programming (DP), the use of a 2D DP array with sizes like `new int[n][m]` or `new int[n+1][m+1]` depends on the problem being solved and how the state transitions are represented. Here's an explanation of when and why we might use these specific dimensions:

### 1. **`new int[n][m]` (Regular 2D DP Array)**

When we have two indices or parameters that affect the state of the solution (e.g., when we need to track two variables or decisions in each state), we use a 2D DP array to store intermediate results. The size `n x m` is chosen based on the problem's requirements, where `n` and `m` represent the different variables or choices involved.

#### **Use Case Example:**
For problems like **Longest Common Subsequence (LCS)** or **Edit Distance**, we use two indices: one for each string in the comparison.

For example, in the **LCS** problem, given two strings `str1` and `str2`, we define a DP array `dp[i][j]` where:
- `i` is the index in `str1`
- `j` is the index in `str2`

The state `dp[i][j]` represents the length of the longest common subsequence of the substrings `str1[0...i-1]` and `str2[0...j-1]`.

Here, we use a DP array of size `dp[str1.length()][str2.length()]`, which is `new int[n][m]` where:
- `n = str1.length()`
- `m = str2.length()`

#### **Code Example (LCS)**:
```java
public static int longestCommonSubsequence(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();
    int[][] dp = new int[n+1][m+1]; // DP array of size (n+1) x (m+1)

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1] + 1; // Match, extend the subsequence
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); // No match, take the max of previous
            }
        }
    }

    return dp[n][m]; // Result in the bottom-right cell
}
```

### 2. **`new int[n+1][m+1]` (Padding for Base Case)**

In many DP problems, especially those that involve 0-indexed arrays or string comparisons, we use an extra row and column (i.e., `n+1` and `m+1`) to handle base cases easily. This is known as **padding** and is a common technique to simplify the logic of the DP transition and avoid boundary checks.

#### **Why `n+1` and `m+1`?**
- **Base Cases:** When using 1-based indexing or working with an empty substring, we want to represent the situation where either string is of length 0 (empty string). The additional row and column provide a natural way to handle these base cases.
- **Avoiding Boundary Conditions:** By starting the DP table from index 1 and using index 0 for base cases, we avoid needing extra conditions for when `i = 0` or `j = 0`.

#### **Use Case Example:**
For the **Edit Distance** (Levenshtein distance) problem, where we want to calculate the minimum number of operations (insertions, deletions, substitutions) to convert one string to another:

We define `dp[i][j]` as the minimum number of operations required to convert the first `i` characters of string `str1` to the first `j` characters of string `str2`.

We use a DP array of size `(n+1) x (m+1)` where:
- `n = str1.length()`
- `m = str2.length()`

#### **Code Example (Edit Distance)**:
```java
public static int minDistance(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();

    int[][] dp = new int[n+1][m+1]; // DP array of size (n+1) x (m+1)

    // Initialize the base cases for empty strings
    for (int i = 0; i <= n; i++) {
        dp[i][0] = i; // Deletions to make word1 empty
    }

    for (int j = 0; j <= m; j++) {
        dp[0][j] = j; // Insertions to make word2 from empty
    }

    // Fill the DP table based on the recurrence relation
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (word1.charAt(i-1) == word2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1]; // No change required
            } else {
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1; // Min operation
            }
        }
    }

    return dp[n][m]; // Result in the bottom-right cell
}
```

### **When to Use `new int[n+1][m+1]`:**
- **Base case initialization:** If the problem requires a base case where one or both of the parameters are 0 (e.g., one string is empty), we often initialize the first row and column to represent these cases.
- **Avoiding index errors:** By adding an extra row and column, we can easily manage transitions without needing additional checks for array boundaries.
- **Space complexity consideration:** While adding an extra row/column increases the space complexity by 1 extra dimension, it often simplifies the problem and avoids edge case handling.

### **Summary of Use Cases:**
1. **`new int[n][m]`**: Use this when you need a 2D table to store intermediate results, and both `n` and `m` directly represent the number of states (e.g., two strings, two sequences).
2. **`new int[n+1][m+1]`**: Use this when you want to handle base cases or avoid edge-case checks. The extra row and column are used to represent the case where one or both sequences are empty, or for 1-based indexing.

These two patterns are standard and are often seen in problems like:
- **Longest Common Subsequence**
- **Edit Distance**
- **Knapsack Problem**
- **Matrix Chain Multiplication**
- **Subsequence Problems** (where base cases represent empty substrings or sequences)
*
*
* Certainly! We can also solve the Longest Increasing Subsequence (LIS) problem using a **2D dynamic programming (DP)** approach.

In this approach, we use a 2D DP array where `dp[i][j]` represents the length of the longest increasing subsequence that ends at index `i` and `j`.

However, we will implement this problem in a slightly different way. Instead of a 2D array, we'll use a single 1D DP array that stores the LIS length ending at each element.

But let's first clarify how the 2D approach could be designed.

### **2D DP Approach for LIS**

In the 2D DP approach:
1. We create a 2D DP array `dp[][]` where `dp[i][j]` is the length of the longest increasing subsequence ending at index `i` and `j`.
2. To update the value of `dp[i][j]`, you iterate through every previous element to find elements that are smaller than `nums[i]` and `nums[j]`, and then check the LIS length accordingly.

However, as mentioned, a **1D** DP array will be sufficient for this problem.

### **Steps Using 1D DP Array:**
1. Initialize a `dp` array of size `n` where each element is initially 1 because the LIS starting at any index is at least the element itself.
2. For each element `nums[i]`, check all the previous elements `nums[j]` where \( j < i \) and \( nums[j] < nums[i] \), and update `dp[i] = max(dp[i], dp[j] + 1)`.
3. The maximum value in the `dp` array will be the length of the longest increasing subsequence.

---

### **Code:**

```java
import java.util.*;

public class LongestIncreasingSubsequence2D {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[][] dp = new int[n][n];

        // Initialize the dp array
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1; // Each element by itself is an increasing subsequence
        }

        // Fill the dp array with the length of the LIS
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i][j] = dp[j][j] + 1;
                } else {
                    dp[i][j] = dp[j][i];
                }
            }
        }

        // Find the longest subsequence from dp array
        int maxLength = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + lengthOfLIS(nums)); // Output: 4
    }
}
```

### **Explanation of Dry Run:**

Let's take the input `nums = [10, 9, 2, 5, 3, 7, 101, 18]`.

**1. Initialize `dp` array with base case where each `dp[i][i] = 1`.**

```
dp[][] = [
    [1, 0, 0, 0, 0, 0, 0, 0],
    [0, 1, 0, 0, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 0],
    [0, 0, 0, 1, 0, 0, 0, 0],
    [0, 0, 0, 0, 1, 0, 0, 0],
    [0, 0, 0, 0, 0, 1, 0, 0],
    [0, 0, 0, 0, 0, 0, 1, 0],
    [0, 0, 0, 0, 0, 0, 0, 1]
]
```

**2. Fill the `dp` array using LIS recurrence relation:**

For each `nums[i]`, compare it with all previous elements `nums[j]` where \( j < i \). If `nums[i] > nums[j]`, update `dp[i][j]`.

After processing, `dp` might look like:

```
dp[][] = [
    [1, 1, 1, 1, 1, 1, 1, 1],
    [0, 1, 1, 1, 1, 1, 1, 1],
    [0, 0, 1, 1, 1, 1, 1, 1],
    [0, 0, 0, 1, 2, 2, 2, 2],
    [0, 0, 0, 0, 1, 1, 2, 2],
    [0, 0, 0, 0, 0, 1, 2, 2],
    [0, 0, 0, 0, 0, 0, 1, 2],
    [0, 0, 0, 0, 0, 0, 0, 1]
]
```

**3. Final result:**

The maximum value in `dp` is `4`, which represents the length of the longest increasing subsequence.

### **Time and Space Complexity:**

**Time Complexity:**
- Outer loop: \( O(n) \)
- Inner loop: \( O(n) \)
- Total: \( O(n^2) \)

**Space Complexity:**
- \( O(n^2) \) for the `dp` array.

### **Summary:**

The 2D DP approach for finding the LIS is less efficient in terms of space due to the \( O(n^2) \) complexity. However, it provides a good conceptual view of how LIS can be solved using dynamic programming. The 1D DP array approach is more efficient both in time and space for this problem.
* */


//DRY RUN 1D
//Let’s perform a **dry run** for the \(O(n^2)\) dynamic programming solution for **Longest Increasing Subsequence (LIS)** on the input:
//\[ \text{nums} = [10, 9, 2, 5, 3, 7, 101, 18] \]
//
//---
//
//### **Initial Setup**
//1. **Define DP Array**:
//   `dp[i]` represents the length of the LIS ending at index \(i\).
//   Initialize:
//   \[
//   dp = [1, 1, 1, 1, 1, 1, 1, 1]
//   \]
//   Every element is an LIS of length 1 by itself.
//
//2. **Outer Loop (index \(i\))** iterates from 1 to \(n-1\).
//
//---
//
//### **Dry Run**
//
//#### **Iteration 1 (\(i = 1\)):**
//- Compare `nums[1] = 9` with all previous elements (\(j = 0\)):
//  - \(j = 0\): \(nums[1] < nums[0]\) (9 < 10) → No update to `dp[1]`.
//
//- \(dp = [1, 1, 1, 1, 1, 1, 1, 1]\).
//
//---
//
//#### **Iteration 2 (\(i = 2\)):**
//- Compare `nums[2] = 2` with all previous elements (\(j = 0, 1\)):
//  - \(j = 0\): \(nums[2] < nums[0]\) (2 < 10) → No update.
//  - \(j = 1\): \(nums[2] < nums[1]\) (2 < 9) → No update.
//
//- \(dp = [1, 1, 1, 1, 1, 1, 1, 1]\).
//
//---
//
//#### **Iteration 3 (\(i = 3\)):**
//- Compare `nums[3] = 5` with all previous elements (\(j = 0, 1, 2\)):
//  - \(j = 0\): \(nums[3] < nums[0]\) (5 < 10) → No update.
//  - \(j = 1\): \(nums[3] < nums[1]\) (5 < 9) → No update.
//  - \(j = 2\): \(nums[3] > nums[2]\) (5 > 2) → Update `dp[3]`:
//    \[
//    dp[3] = \max(dp[3], dp[2] + 1) = \max(1, 1 + 1) = 2
//    \]
//
//- \(dp = [1, 1, 1, 2, 1, 1, 1, 1]\).
//
//---
//
//#### **Iteration 4 (\(i = 4\)):**
//- Compare `nums[4] = 3` with all previous elements (\(j = 0, 1, 2, 3\)):
//  - \(j = 0\): \(nums[4] < nums[0]\) (3 < 10) → No update.
//  - \(j = 1\): \(nums[4] < nums[1]\) (3 < 9) → No update.
//  - \(j = 2\): \(nums[4] > nums[2]\) (3 > 2) → Update `dp[4]`:
//    \[
//    dp[4] = \max(dp[4], dp[2] + 1) = \max(1, 1 + 1) = 2
//    \]
//  - \(j = 3\): \(nums[4] < nums[3]\) (3 < 5) → No update.
//
//- \(dp = [1, 1, 1, 2, 2, 1, 1, 1]\).
//
//---
//
//#### **Iteration 5 (\(i = 5\)):**
//- Compare `nums[5] = 7` with all previous elements (\(j = 0, 1, 2, 3, 4\)):
//  - \(j = 0\): \(nums[5] < nums[0]\) (7 < 10) → No update.
//  - \(j = 1\): \(nums[5] < nums[1]\) (7 < 9) → No update.
//  - \(j = 2\): \(nums[5] > nums[2]\) (7 > 2) → Update `dp[5]`:
//    \[
//    dp[5] = \max(dp[5], dp[2] + 1) = \max(1, 1 + 1) = 2
//    \]
//  - \(j = 3\): \(nums[5] > nums[3]\) (7 > 5) → Update `dp[5]`:
//    \[
//    dp[5] = \max(dp[5], dp[3] + 1) = \max(2, 2 + 1) = 3
//    \]
//  - \(j = 4\): \(nums[5] > nums[4]\) (7 > 3) → Update `dp[5]`:
//    \[
//    dp[5] = \max(dp[5], dp[4] + 1) = \max(3, 2 + 1) = 3
//    \]
//
//- \(dp = [1, 1, 1, 2, 2, 3, 1, 1]\).
//
//---
//
//#### **Iteration 6 (\(i = 6\)):**
//- Compare `nums[6] = 101` with all previous elements (\(j = 0, 1, 2, 3, 4, 5\)):
//  - \(j = 0\): \(nums[6] > nums[0]\) (101 > 10) → Update `dp[6]`:
//    \[
//    dp[6] = \max(dp[6], dp[0] + 1) = \max(1, 1 + 1) = 2
//    \]
//  - \(j = 1\): \(nums[6] > nums[1]\) (101 > 9) → Update `dp[6]`:
//    \[
//    dp[6] = \max(dp[6], dp[1] + 1) = \max(2, 1 + 1) = 2
//    \]
//  - \(j = 2\): \(nums[6] > nums[2]\) (101 > 2) → Update `dp[6]`:
//    \[
//    dp[6] = \max(dp[6], dp[2] + 1) = \max(2, 1 + 1) = 2
//    \]
//  - \(j = 3\): \(nums[6] > nums[3]\) (101 > 5) → Update `dp[6]`:
//    \[
//    dp[6] = \max(dp[6], dp[3] + 1) = \max(2, 2 + 1) = 3
//    \]
//  - \(j = 4\): \(nums[6] > nums[4]\) (101 > 3) → Update `dp[6]`:
//    \[
//    dp[6] = \max(dp[6], dp[4] + 1) = \max(3, 2 + 1) = 3
//    \]
//  - \(j = 5\): \(nums[6] > nums[5]\) (101 > 7) → Update `dp[6]`:
//    \[
//    dp[6] = \max(dp[6], dp[5] + 1) = \max(3, 3 + 1) = 4
//    \]
//
//- \(dp = [1, 1, 1, 2, 2, 3, 4, 1]\).
//
//---
//
//#### **Iteration 7 (\(i = 7\)):**
//- Compare `nums[7] = 18` with all previous elements (\(j = 0, 1, 2, 3, 4, 5, 6\)):
//  - \(j = 0\): \(nums[7] > nums[0]\) (18 > 10) → Update `dp[7]`:
//    \[
//    dp[7] = \max(dp[7], dp[0] + 1) = \max(1, 1 + 1) = 2
//    \]
//  - \(j = 1\): \(nums[7] > nums[1]\) (18 > 9) → Update `dp[7]`:
//    \[
//    dp[7] = \max(dp[7], dp[1] + 1) = \max(2, 1 + 1) = 2
//    \]
//  - \(j = 2\): \(nums[7] > nums[2]\) (18 > 2) → Update `dp[7]`:
//    \[
//    dp[7] = \max(dp[7], dp[2] + 1) = \max(2, 1 + 1) = 2
//    \]
//  - \(j = 3\): \(nums[7] > nums[3]\) (18 > 5) → Update `dp[7]`:
//    \[
//    dp[7] = \max(dp[7], dp[3] + 1) = \max(2, 2 + 1) = 3
//    \]
//  - \(j = 4\): \(nums[7] > nums[4]\) (18 > 3) → Update `dp[7]`:
//    \[
//    dp[7] = \max(dp[7], dp[4] + 1) = \max(3, 2 + 1) = 3
//    \]
//  - \(j = 5\): \(nums[7] > nums[5]\) (18 > 7) → Update `dp[7]`:
//    \[
//    dp[7] = \max(dp[7], dp[5] + 1) = \max(3, 3 + 1) = 4
//    \]
//  - \(j = 6\
//
//): \(nums[7] < nums[6]\) (18 < 101) → No update.
//
//- \(dp = [1, 1, 1, 2, 2, 3, 4, 4]\).
//
//---
//
//### **Final Result**
//- The longest LIS length is the maximum value in `dp`:
//  \[
//  \text{LIS Length} = \max(dp) = 4
//  \]
//- Example LIS: \([2, 3, 7, 18]\) or \([2, 5, 7, 101]\).