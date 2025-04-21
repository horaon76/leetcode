package Subarray;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 * <p>
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 **/
public class MaximumLengthOfRepeatedSubarray718 {
    /**
     * Time and Space Complexity:
     * Time Complexity: O(M * N) where M and N are the lengths of nums1 and nums2 respectively.
     * Space Complexity: O(M * N) because of the dp table.
     **/
    public int findLengthDP(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        return maxLength;
    }

    //Space optimized DP
//    Time and Space Complexity:
//    Time Complexity: O(M * N)
//    Space Complexity: O(N) since we only store two rows at a time.
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] dp = new int[n + 1];
        int maxLength = 0;

        for (int i = 1; i <= m; i++) {
            int[] currentRow = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    currentRow[j] = dp[j - 1] + 1;
                    maxLength = Math.max(maxLength, currentRow[j]);
                }
            }
            dp = currentRow;  // Move to the next row
        }

        return maxLength;
    }
}


### LeetCode 718: **Maximum Length of Repeated Subarray**

---

### Problem Statement:

Given two integer arrays `nums1` and `nums2`, return the maximum length of a subarray that appears in both arrays.

---

### Example:

#### Example 1:
**Input:**
```plaintext
nums1 = [1, 2, 3, 2, 1], nums2 = [3, 2, 1, 4, 7]
```

**Output:**
```plaintext
3
```

**Explanation:**
The repeated subarray is `[3, 2, 1]`.

#### Example 2:
**Input:**
```plaintext
nums1 = [0, 1, 1, 1, 1], nums2 = [1, 0, 1, 0, 1]
```

**Output:**
```plaintext
2
```

---

### Approach 1: Dynamic Programming

#### Intuition:
Use a 2D dynamic programming table where `dp[i][j]` represents the length of the longest common subarray ending at `nums1[i-1]` and `nums2[j-1]`.

#### Steps:
1. Create a DP array `dp` of size `(len(nums1) + 1) x (len(nums2) + 1)`, initialized to zero.
2. For every pair of indices \( i \) and \( j \):
    - If `nums1[i-1] == nums2[j-1]`, then `dp[i][j] = dp[i-1][j-1] + 1`.
    - Otherwise, `dp[i][j] = 0`.
3. Track the maximum value in the DP table during iteration.
4. The maximum value represents the length of the longest repeated subarray.

---

#### Code:

```java
public class Solution {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLength(nums1, nums2)); // Output: 3
    }

    public static int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        return maxLength;
    }
}
```

---

### Complexity:

- **Time Complexity**: \( O(m \cdot n) \)  
  Nested loops iterate through `nums1` and `nums2`.

- **Space Complexity**: \( O(m \cdot n) \)  
  The DP table stores values for all pairs of indices.

---

### Approach 2: Sliding Window

#### Intuition:
Align the two arrays at different positions (like sliding windows). For each alignment, calculate the length of the common subarray.

#### Steps:
1. Slide `nums2` over `nums1` from left to right.
2. For each alignment, calculate the maximum length of common subarray starting at the respective positions.
3. Repeat the same by sliding `nums1` over `nums2`.

---

#### Code:

```java
public class Solution {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLength(nums1, nums2)); // Output: 3
    }

    public static int findLength(int[] nums1, int[] nums2) {
        int maxLength = 0;

        // Slide nums2 over nums1
        for (int offset = 0; offset < nums1.length; offset++) {
            maxLength = Math.max(maxLength, calculateOverlap(nums1, nums2, offset, 0));
        }

        // Slide nums1 over nums2
        for (int offset = 0; offset < nums2.length; offset++) {
            maxLength = Math.max(maxLength, calculateOverlap(nums1, nums2, 0, offset));
        }

        return maxLength;
    }

    private static int calculateOverlap(int[] nums1, int[] nums2, int offset1, int offset2) {
        int count = 0, maxCount = 0;

        while (offset1 < nums1.length && offset2 < nums2.length) {
            if (nums1[offset1] == nums2[offset2]) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
            offset1++;
            offset2++;
        }

        return maxCount;
    }
}
```

---

### Complexity:

- **Time Complexity**: \( O((m + n) \cdot \min(m, n)) \)  
  For each alignment, the overlap is calculated in \( O(\min(m, n)) \), and there are \( m + n \) alignments.

- **Space Complexity**: \( O(1) \)  
  No additional space is used apart from variables.

---

### Approach 3: Binary Search + Hashing (Rabin-Karp)

#### Intuition:
Use binary search to find the maximum length of a subarray and Rabin-Karp hashing to check for common subarrays of a given length.

#### Steps:
1. Use binary search to find the maximum possible subarray length.
2. For a given length \( L \), check if there is a common subarray of length \( L \) using a hash set to store seen hashes.

#### Complexity:

- **Time Complexity**: \( O((m + n) \cdot \log(\min(m, n))) \)  
  Binary search iterates \( O(\log(\min(m, n))) \) times, and each check is \( O(m + n) \).

- **Space Complexity**: \( O(\min(m, n)) \)  
  Hash set stores all hashes of the smaller array.

Let me know if you want the implementation of this approach!

Here’s the implementation for the **Binary Search + Hashing (Rabin-Karp)** approach for solving LeetCode 718.

---

### Approach: Binary Search + Hashing (Rabin-Karp)

#### Key Idea:
1. Perform binary search to determine the maximum length of the repeated subarray.
2. For a given length \( L \), use the **Rabin-Karp algorithm** to compute hashes for subarrays of length \( L \) in `nums1` and `nums2` and check for overlaps.

#### Steps:
1. Use binary search with the range \( [0, \text{min}(\text{nums1.length}, \text{nums2.length})] \).
2. For each length \( L \) in the search:
    - Compute hashes for all subarrays of length \( L \) in `nums1` and store them in a hash set.
    - Check if any subarray of length \( L \) in `nums2` has the same hash.
3. If a common subarray exists for \( L \), increase the search range; otherwise, decrease it.
4. The largest \( L \) found is the length of the longest repeated subarray.

---

### Code:

```java
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        System.out.println(findLength(nums1, nums2)); // Output: 3
    }

    public static int findLength(int[] nums1, int[] nums2) {
        int left = 0, right = Math.min(nums1.length, nums2.length);
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(nums1, nums2, mid)) {
                result = mid; // Update result if length mid is valid
                left = mid + 1; // Try for larger lengths
            } else {
                right = mid - 1; // Try for smaller lengths
            }
        }

        return result;
    }

    private static boolean check(int[] nums1, int[] nums2, int length) {
        long base = 101; // A prime number base for hashing
        long mod = (long) 1e9 + 7; // A large prime modulus to avoid overflow

        // Compute hash for subarrays of nums1
        Set<Long> seenHashes = new HashSet<>();
        long hash1 = 0, power = 1;

        for (int i = 0; i < nums1.length; i++) {
            hash1 = (hash1 * base + nums1[i]) % mod;
            if (i >= length - 1) {
                if (i >= length) {
                    hash1 = (hash1 - nums1[i - length] * power % mod + mod) % mod;
                }
                seenHashes.add(hash1);
            } else {
                power = (power * base) % mod;
            }
        }

        // Compute hash for subarrays of nums2
        long hash2 = 0;
        power = 1;

        for (int i = 0; i < nums2.length; i++) {
            hash2 = (hash2 * base + nums2[i]) % mod;
            if (i >= length - 1) {
                if (i >= length) {
                    hash2 = (hash2 - nums2[i - length] * power % mod + mod) % mod;
                }
                if (seenHashes.contains(hash2)) {
                    return true; // Found a common subarray
                }
            } else {
                power = (power * base) % mod;
            }
        }

        return false;
    }
}
```

---

### Explanation:

1. **Binary Search**:
    - Starts with the smallest possible subarray length \( L = 0 \) and goes up to the smallest array's length.
    - For each \( L \), checks if there is a common subarray of length \( L \) using hashing.

2. **Rabin-Karp Hashing**:
    - Uses a rolling hash to efficiently calculate hashes for all subarrays of length \( L \).
    - The hash for a subarray is updated in \( O(1) \) using:
      \[
      \text{hash} = \text{(hash * base + newElement - oldElement * base^{length})} \, \% \, \text{mod}
      \]
    - This ensures that the hash computation is fast and avoids recomputation for overlapping subarrays.

3. **Hash Set**:
    - Stores hashes of subarrays in `nums1` and checks for matching hashes in `nums2`.

---

### Complexity:

- **Time Complexity**:  
  \( O((m + n) \cdot \log(\min(m, n))) \)
    - Binary search iterates \( \log(\min(m, n)) \) times.
    - Hash computation and comparison take \( O(m + n) \) for each \( L \).

- **Space Complexity**:  
  \( O(\min(m, n)) \)
    - The hash set stores hashes of subarrays from the smaller array.

---

### Example Walkthrough:

**Input**:  
`nums1 = [1, 2, 3, 2, 1]`, `nums2 = [3, 2, 1, 4, 7]`

1. **Binary Search**:
    - Mid = 2: Common subarray of length 2 found: `[3, 2]`.
    - Mid = 3: Common subarray of length 3 found: `[3, 2, 1]`.
    - Mid = 4: No common subarray of length 4.

2. **Result**: The longest repeated subarray length is \( 3 \).

---

This method is efficient for large inputs and balances the use of hashing and binary search for optimal performance.