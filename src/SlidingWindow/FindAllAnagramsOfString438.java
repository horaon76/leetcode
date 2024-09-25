package SlidingWindow;

import java.util.*;

/**
 * Given two strings s and p, return an array of all the start indices of p's
 * anagrams
 * in s. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 **/

public class FindAllAnagramsOfString438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int k = p.length();
        int n = s.length();

        if (n < k)
            return new ArrayList<Integer>();

        char arr1[] = new char[26];
        char arr2[] = new char[26];
        for (int i = 0; i < k; i++) {
            arr1[s.charAt(i) - 'a']++;
            arr2[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(arr1, arr2))
            list.add(0);

        for (int i = k; i < n; i++) {
            arr1[s.charAt(i) - 'a']++;
            arr1[s.charAt(i - k) - 'a']--;

            if (Arrays.equals(arr1, arr2))
                list.add(i - k + 1);
        }
        return list;
    }
}
