package Hard;

public class NoOfWaysToSeparateNumber1977 {
    private static final int MOD = 1000000007;

    public static int numberOfCombinations(String num) {
        int n = num.length();
        if (num.charAt(0) == '0') {
            return 0;
        }

        // DP array where dp[i] is the number of ways to split num[0:i+1]
        int[] dp = new int[n + 1];
        dp[0] = 1;

        // Prefix sum for fast summation of dp values
        int[] prefix = new int[n + 1];
        prefix[0] = 1;

        // LCP (Longest Common Prefix) table: lcp[i][j] tells the length of the LCP between num[i:] and num[j:]
        int[][] lcp = new int[n + 1][n + 1];

        // Precompute the LCP array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (num.charAt(i) == num.charAt(j)) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        // DP processing
        for (int i = 1; i <= n; i++) {
            if (num.charAt(i - 1) == '0') {
                continue;  // Skip if the number starts with a zero
            }

            for (int len = 1; len <= i; len++) {
                int j = i - len;  // The start of the substring

                if (j == 0 || compare(num, lcp, j - len, j, len)) {
                    dp[i] = (dp[i] + dp[j]) % MOD;
                }
            }

            prefix[i] = (prefix[i - 1] + dp[i]) % MOD;
        }

        return dp[n];
    }

    // Helper method to compare two substrings of the same length
    private static boolean compare(String num, int[][] lcp, int i1, int i2, int len) {
        if (i1 < 0) return true;  // There's no previous number to compare with
        int l = lcp[i1][i2];  // The length of the longest common prefix
        if (l >= len) return true;  // The substrings are identical
        return num.charAt(i1 + l) <= num.charAt(i2 + l);  // Compare the first differing character
    }

    public static void main(String[] args) {
        String num = "3927";
        System.out.println(numberOfCombinations(num));
    }
}
/**
 * LeetCode **1977: "Number of Ways to Separate Numbers"** is a complex problem where the goal is to count the number of ways to split a string of digits into non-decreasing contiguous substrings, such that each substring represents a valid number (with no leading zeros except for the number "0"). The result should be returned modulo \(10^9 + 7\).
 * <p>
 * ### Problem Breakdown:
 * - **Input**: A string `num` consisting of digits.
 * - **Output**: The number of ways to split the string into one or more contiguous substrings where:
 * - Each substring is a valid number (no leading zeros except "0").
 * - The sequence of numbers is non-decreasing.
 * - The result should be modulo \(10^9 + 7\).
 * <p>
 * ### Key Constraints:
 * 1. Substrings must be **non-decreasing**.
 * 2. Leading zeros are not allowed unless the substring is "0".
 * 3. Output should be modulo \(10^9 + 7\).
 * <p>
 * ---
 * <p>
 * ### Efficient Approach: Dynamic Programming with LCP
 * <p>
 * This problem can be approached using **dynamic programming (DP)** combined with **longest common prefix (LCP)** to efficiently compare substrings.
 * <p>
 * #### DP Definition:
 * - Let `dp[i]` represent the number of valid ways to split the substring `num[0:i]`.
 * <p>
 * #### Key Challenges:
 * 1. Avoid recomputing substring comparisons.
 * 2. Avoid overlapping or redundant computations by using memoization.
 * 3. Ensure that the resulting sequence is non-decreasing.
 * <p>
 * #### DP Transition:
 * For each `i`, check all possible splits `num[j:i]`, where `j < i`. For each split:
 * - Ensure that the new number formed by `num[j:i]` is non-decreasing compared to the previous number (if there is one).
 * - Add the number of ways `dp[j]` to `dp[i]` if the split is valid.
 * <p>
 * #### Longest Common Prefix (LCP):
 * To efficiently compare two substrings, use an **LCP table** to compute the longest common prefix between any two substrings. This table speeds up comparisons.
 * <p>
 * ### Solution Code (DP + LCP):
 * <p>
 * ```java
 * import java.util.Arrays;
 * <p>
 * class Solution {
 * private static final int MOD = 1000000007;
 * <p>
 * public int numberOfCombinations(String num) {
 * int n = num.length();
 * if (num.charAt(0) == '0') {
 * return 0;
 * }
 * <p>
 * // DP array where dp[i] is the number of ways to split num[0:i+1]
 * int[] dp = new int[n + 1];
 * dp[0] = 1;
 * <p>
 * // Prefix sum for fast summation of dp values
 * int[] prefix = new int[n + 1];
 * prefix[0] = 1;
 * <p>
 * // LCP (Longest Common Prefix) table: lcp[i][j] tells the length of the LCP between num[i:] and num[j:]
 * int[][] lcp = new int[n + 1][n + 1];
 * <p>
 * // Precompute the LCP array
 * for (int i = n - 1; i >= 0; i--) {
 * for (int j = n - 1; j > i; j--) {
 * if (num.charAt(i) == num.charAt(j)) {
 * lcp[i][j] = lcp[i + 1][j + 1] + 1;
 * }
 * }
 * }
 * <p>
 * // DP processing
 * for (int i = 1; i <= n; i++) {
 * if (num.charAt(i - 1) == '0') {
 * continue;  // Skip if the number starts with a zero
 * }
 * <p>
 * for (int len = 1; len <= i; len++) {
 * int j = i - len;  // The start of the substring
 * <p>
 * if (j == 0 || compare(num, lcp, j - len, j, len)) {
 * dp[i] = (dp[i] + dp[j]) % MOD;
 * }
 * }
 * <p>
 * prefix[i] = (prefix[i - 1] + dp[i]) % MOD;
 * }
 * <p>
 * return dp[n];
 * }
 * <p>
 * // Helper method to compare two substrings of the same length
 * private boolean compare(String num, int[][] lcp, int i1, int i2, int len) {
 * if (i1 < 0) return true;  // There's no previous number to compare with
 * int l = lcp[i1][i2];  // The length of the longest common prefix
 * if (l >= len) return true;  // The substrings are identical
 * return num.charAt(i1 + l) <= num.charAt(i2 + l);  // Compare the first differing character
 * }
 * }
 * ```
 * <p>
 * ### Explanation:
 * <p>
 * 1. **Precompute LCP**:
 * - First, precompute the LCP table where `lcp[i][j]` gives the length of the longest common prefix between the substrings starting at `i` and `j`. This helps in quickly comparing whether two substrings are equal or whether one is lexicographically smaller than the other.
 * <p>
 * 2. **DP Transition**:
 * - For each index `i`, explore all possible splits of length `len`. Ensure that the substring formed by the last `len` characters is non-decreasing compared to the previous substring (if there is one).
 * - If the new substring is valid, add the number of ways to split the string up to index `j` to `dp[i]`.
 * <p>
 * 3. **Comparing Substrings**:
 * - The `compare` function checks if two substrings are non-decreasing by using the precomputed LCP table. If the common prefix is long enough, they are considered equal; otherwise, the first differing character is used to determine the lexicographical order.
 * <p>
 * 4. **Final Result**:
 * - The final answer is stored in `dp[n]`, which represents the number of valid ways to split the entire string.
 * <p>
 * ### Time Complexity:
 * - **Time Complexity**: \(O(n^2)\), where \(n\) is the length of the string. This is due to the nested loops and the computation of the LCP array.
 * - **Space Complexity**: \(O(n^2)\) for storing the DP table and the LCP table.
 * <p>
 * ### Conclusion:
 * <p>
 * This approach efficiently solves the problem using dynamic programming with precomputed longest common prefixes (LCPs) to handle substring comparisons. The use of memoization ensures that the solution runs within acceptable time limits even for larger input sizes.
 **/

/**
 * Here’s a Java solution using a subset-based approach to solve LeetCode 1977. This solution generates all possible contiguous splits (subsets) of the string and checks whether the resulting sequence of numbers is non-decreasing.
 *
 * ### Key Considerations:
 * - The approach will generate all possible ways to split the string.
 * - It will then check if the resulting sequence of numbers is non-decreasing.
 * - Leading zeros are not allowed, except for the single digit "0."
 *
 * ### Java Code:
 * ```java
 * import java.util.ArrayList;
 * import java.util.List;
 *
 * public class SubsetApproachSolution {
 *     private static final int MOD = 1000000007;
 *
 *     public int numberOfCombinations(String num) {
 *         // Return 0 if the input is invalid
 *         if (num.charAt(0) == '0') return 0;
 *
 *         List<List<String>> allSubsets = new ArrayList<>();
 *         generateSubsets(num, 0, new ArrayList<>(), allSubsets);
 *
 *         int count = 0;
 *         for (List<String> subset : allSubsets) {
 *             if (isValidSubset(subset)) {
 *                 count = (count + 1) % MOD;
 *             }
 *         }
 *
 *         return count;
 *     }
 *
 *     // Generate all possible contiguous subsets (partitions) of the string
 *     private void generateSubsets(String num, int start, List<String> current, List<List<String>> allSubsets) {
 *         if (start == num.length()) {
 *             allSubsets.add(new ArrayList<>(current));
 *             return;
 *         }
 *
 *         for (int i = start; i < num.length(); i++) {
 *             String part = num.substring(start, i + 1);
 *             current.add(part);
 *             generateSubsets(num, i + 1, current, allSubsets);
 *             current.remove(current.size() - 1);
 *         }
 *     }
 *
 *     // Check if the subset is valid: numbers must be non-decreasing and have no leading zeros
 *     private boolean isValidSubset(List<String> subset) {
 *         for (int i = 0; i < subset.size(); i++) {
 *             String current = subset.get(i);
 *             // Invalid if the number starts with a zero but is not "0"
 *             if (current.length() > 1 && current.charAt(0) == '0') {
 *                 return false;
 *             }
 *             // Check if numbers are non-decreasing
 *             if (i > 0) {
 *                 String prev = subset.get(i - 1);
 *                 if (compare(prev, current) > 0) {
 *                     return false;
 *                 }
 *             }
 *         }
 *         return true;
 *     }
 *
 *     // Compare two numeric strings lexicographically
 *     private int compare(String s1, String s2) {
 *         // First compare by length
 *         if (s1.length() != s2.length()) {
 *             return s1.length() - s2.length();
 *         }
 *         // If the lengths are the same, compare character by character
 *         return s1.compareTo(s2);
 *     }
 *
 *     public static void main(String[] args) {
 *         SubsetApproachSolution solution = new SubsetApproachSolution();
 *         String num = "327";
 *         System.out.println("Number of valid combinations: " + solution.numberOfCombinations(num));
 *     }
 * }
 * ```
 *
 * ### Explanation:
 * 1. **`generateSubsets`**:
 *    - This function recursively generates all possible contiguous subsets (splits) of the string `num`. It uses a backtracking technique to explore all possible partitions of the string.
 *
 * 2. **`isValidSubset`**:
 *    - This function checks if the current subset (list of numbers) meets the criteria:
 *      - Each number must not have leading zeros unless it is the number "0".
 *      - The sequence of numbers must be non-decreasing.
 *
 * 3. **`compare`**:
 *    - This function compares two strings as numbers:
 *      - It first compares their lengths. If the lengths differ, the shorter one is smaller.
 *      - If the lengths are the same, it compares the strings lexicographically (character by character).
 *
 * ### How it works:
 * - The algorithm generates all possible ways to partition the string into contiguous substrings.
 * - Then, for each partition, it checks if the numbers are valid and non-decreasing.
 * - It counts the number of valid partitions.
 *
 * ### Example:
 * For input `num = "327"`, possible partitions are:
 * - `["3", "2", "7"]`
 * - `["3", "27"]`
 * - `["32", "7"]`
 * - `["327"]`
 *
 * The only valid non-decreasing partition here is `["327"]`.
 *
 * ### Limitations:
 * - This solution uses a brute-force approach by generating all possible subsets, which leads to **exponential time complexity** (O(2^n)). This approach will **not scale well** for large inputs, as it is computationally expensive.
 *
 * For larger inputs, the dynamic programming approach is much more efficient.
 *
 * ### Conclusion:
 * This subset-based approach is a valid but inefficient solution for small inputs. For larger inputs, you should opt for the dynamic programming approach or DFS with memoization for better time complexity.
 * **/