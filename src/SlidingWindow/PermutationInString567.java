package SlidingWindow;

import java.util.Arrays;

/**
 * Given two strings s1 and s2, return true if s2 contains a
 * permutation
 * of s1, or false otherwise.
 * <p>
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * <p>
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 **/
//https://leetcode.com/problems/permutation-in-string/solutions/462086/java-o-n-time-o-1-space-w-explanation/
public class PermutationInString567 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] arr1 = new int[26];
        int[] arr2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(arr1, arr2)) return true;

        int front = 0;
        int back = s1.length();
        while (back < s2.length()) {
            arr2[s2.charAt(front) - 'a']--;
            arr2[s2.charAt(back) - 'a']++;

            if (Arrays.equals(arr1, arr2)) return true;
            front++;
            back++;
        }
        return false;
    }
}
