package DP;

import java.util.HashMap;
import java.util.Map;

/**
 * You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
 *
 * "1" -> 'A'
 *
 * "2" -> 'B'
 *
 * ...
 *
 * "25" -> 'Y'
 *
 * "26" -> 'Z'
 *
 * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
 *
 * For example, "11106" can be decoded into:
 *
 * "AAJF" with the grouping (1, 1, 10, 6)
 * "KJF" with the grouping (11, 10, 6)
 * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 * Note: there may be strings that are impossible to decode.
 *
 * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 *
 * Output: 2
 *
 * Explanation:
 *
 * "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 *
 * Input: s = "226"
 *
 * Output: 3
 *
 * Explanation:
 *
 * "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 *
 * Input: s = "06"
 *
 * Output: 0
 *
 * Explanation:
 *
 * "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 * **/
public class DecodeWays91 {
    /**
     * Time complexity - 2^n
     * Space Complexity - O(n)
     * **/
    public static int recursive(String s, int index){
        if(s.length() == index){
            return 1;
        }
        if(s.charAt(index) == 0){
            return 0;
        }
        int countSingleCharPath = recursive(s, index +1);
        int countDoubleCharPath = 0;
        if(
                index + 1 < s.length() &&
                        Integer.parseInt(s.substring(index, index+2)) <= 26
        ){
            countDoubleCharPath = recursive(s, index + 2);
        }
        return countSingleCharPath + countDoubleCharPath;
    }


    /**
     * Time Complexity: O(n)
     * Explanation: We only calculate each subproblem once, and memoize the results, which reduces the time complexity to linear.
     *
     * Space Complexity: O(n)
     * Explanation: We need additional space for the memoization table and recursion stack depth.
     * **/
    private Map<Integer, Integer> memo = new HashMap<>();
    private int recursiveHelperMemo(String s, int index) {
        if (index == s.length()) {
            return 1; // Reached the end of the string, count this as 1 way
        }

        if (s.charAt(index) == '0') {
            return 0; // '0' cannot be decoded
        }

        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int count = recursiveHelperMemo(s, index + 1); // Decode one character

        if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            count += recursiveHelperMemo(s, index + 2); // Decode two characters
        }

        memo.put(index, count);
        return count;
    }

    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') {
            return 0; // If the string is empty or starts with '0', no valid decodings
        }

        int[] dp = new int[n + 1];
        dp[n] = 1; // Base case: There's 1 way to decode an empty string

        // Initialize the last character (1 way if not '0')
        dp[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

        // Fill the dp array from the end to the beginning
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0; // A '0' cannot be decoded
            } else {
                dp[i] = dp[i + 1]; // Decode one character
                if (Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                    dp[i] += dp[i + 2]; // Decode two characters
                }
            }
        }

        return dp[0];
    }

    public static void main(String args[]) {
        String input = "226";
        System.out.println(recursive(input, 0));
    }
}
