//package DP;
//
///**
// * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
// * <p>
// * Note that the same word in the dictionary may be reused multiple times in the segmentation.
// * <p>
// * <p>
// * <p>
// * Example 1:
// * <p>
// * Input: s = "leetcode", wordDict = ["leet","code"]
// * Output: true
// * Explanation: Return true because "leetcode" can be segmented as "leet code".
// * Example 2:
// * <p>
// * Input: s = "applepenapple", wordDict = ["apple","pen"]
// * Output: true
// * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
// * Note that you are allowed to reuse a dictionary word.
// * Example 3:
// * <p>
// * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
// * Output: false
// * <p>
// * <p>
// * Constraints:
// * <p>
// * 1 <= s.length <= 300
// * 1 <= wordDict.length <= 1000
// * 1 <= wordDict[i].length <= 20
// * s and wordDict[i] consist of only lowercase English letters.
// * All the strings of wordDict are unique.
// **/
//public class wordBreak139 {
//
//
//}
//
//
//### LeetCode 139 - Word Break
//
//**Problem Statement:**
//
//Given a string `s` and a dictionary of strings `wordDict`, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words.
//
//You may assume the dictionary does not contain duplicate words.
//
//        ### Example
//
//**Input:**
//        ```plaintext
//        s = "leetcode"
//wordDict = ["leet", "code"]
//        ```
//
//        **Output:** `true`
//
//        **Input:**
//        ```plaintext
//        s = "applepenapple"
//wordDict = ["apple", "pen"]
//        ```
//
//        **Output:** `true` (Because "apple pen apple" is a valid segmentation.)
//
//        **Input:**
//        ```plaintext
//        s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//        ```
//
//        **Output:** `false`
//
//        ---
//
//        ### Approach 1: Dynamic Programming
//
//We can solve this problem using dynamic programming by creating a boolean DP array where `dp[i]` will be `true` if the substring `s[0..i-1]` can be segmented into words from the dictionary.
//
//        #### Steps:
//
//        1. Create a DP array of size `n+1` (where `n` is the length of the string `s`) and initialize `dp[0]` to `true` (empty string).
//        2. Iterate through each index `i` from `1` to `n`.
//        - For each `i`, iterate through each `j` from `0` to `i-1`.
//        - Check if `dp[j]` is `true` and if the substring `s[j..i-1]` exists in the dictionary. If both conditions are satisfied, set `dp[i]` to `true`.
//        3. The answer will be found in `dp[n]`.
//
//        #### Code (Java):
//
//        ```java
//import java.util.HashSet;
//import java.util.Set;
//
//public class Solution {
//    public boolean wordBreak(String s, List<String> wordDict) {
//        Set<String> wordSet = new HashSet<>(wordDict);
//        int n = s.length();
//        boolean[] dp = new boolean[n + 1];
//        dp[0] = true; // Base case: empty string
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                if (dp[j] && wordSet.contains(s.substring(j, i))) {
//                    dp[i] = true;
//                    break; // No need to check further
//                }
//            }
//        }
//
//        return dp[n];
//    }
//}
//```
//
//        ### Explanation:
//
//        - **Initialization**: We initialize `dp[0]` to `true` because an empty string can always be segmented.
//        - **Filling the DP Table**:
//        - For each position `i`, we check all previous positions `j`. If the substring `s[j..i-1]` exists in the dictionary and `dp[j]` is true, we set `dp[i]` to true.
//        - **Result**: The last cell `dp[n]` tells us if the entire string can be segmented.
//
//        ### Time Complexity:
//
//        - **Time Complexity**: \(O(n^2)\) where \(n\) is the length of the string `s`, because for each position \(i\), we may check every position \(j\) before it.
//
//        ### Space Complexity:
//
//        - **Space Complexity**: \(O(n)\) for the DP array.
//
//---
//
//        ### Approach 2: Backtracking with Memoization
//
//Another approach is to use backtracking combined with memoization. This method tries to segment the string and stores already computed results to avoid recalculating.
//
//#### Steps:
//
//        1. Create a memoization map to store results for substrings.
//2. Define a recursive function that takes the current index as a parameter.
//        3. Base case: If the current index reaches the length of the string, return true.
//        4. If the result for the current index is already computed, return it.
//5. Iterate through all possible substrings starting from the current index:
//        - If the substring exists in the dictionary, recursively check for the next part of the string.
//        6. Store the result in the memoization map.
//
//#### Code (Java):
//
//        ```java
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//
//public class Solution {
//    public boolean wordBreak(String s, List<String> wordDict) {
//        Set<String> wordSet = new HashSet<>(wordDict);
//        Map<Integer, Boolean> memo = new HashMap<>();
//        return wordBreakHelper(s, 0, wordSet, memo);
//    }
//
//    private boolean wordBreakHelper(String s, int start, Set<String> wordSet, Map<Integer, Boolean> memo) {
//        if (start == s.length()) return true; // Reached the end
//        if (memo.containsKey(start)) return memo.get(start); // Return cached result
//
//        for (int end = start + 1; end <= s.length(); end++) {
//            String word = s.substring(start, end);
//            if (wordSet.contains(word) && wordBreakHelper(s, end, wordSet, memo)) {
//                memo.put(start, true); // Cache the result
//                return true;
//            }
//        }
//
//        memo.put(start, false); // Cache the result
//        return false;
//    }
//}
//```
//
//        ### Explanation:
//
//        - **Memoization**: This approach uses a map to store the results of previously computed indices to reduce computation time.
//- **Recursion**: For each position, we check all possible substrings and recursively explore the remaining part of the string.
//        - **Result**: The base case checks if we have reached the end of the string.
//
//        ### Time Complexity:
//
//        - **Time Complexity**: \(O(n^2)\) in the worst case for generating substrings, but average case performance is better due to memoization.
//
//        ### Space Complexity:
//
//        - **Space Complexity**: \(O(n)\) for the memoization map.
//
//---
//
//        ### Summary of Solutions
//
//| Approach                   | Time Complexity      | Space Complexity      | Explanation                                               |
//        |----------------------------|----------------------|-----------------------|-----------------------------------------------------------|
//        | Dynamic Programming         | \(O(n^2)\)           | \(O(n)\)              | Uses a DP table to find the number of segmentations.      |
//        | Backtracking with Memoization| \(O(n^2)\) (average) | \(O(n)\)              | Uses recursion and memoization to compute segmentations.  |
//
//        ### Conclusion
//
//These approaches effectively solve the Word Break problem, utilizing dynamic programming and backtracking with memoization techniques. The DP approach is generally simpler and clearer, while the backtracking method can be more flexible for certain modifications or variations of the problem.
