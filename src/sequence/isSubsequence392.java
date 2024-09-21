package sequence;
/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 104
 * s and t consist only of lowercase English letters.
 *
 *
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 *
 * **/

public class isSubsequence392 {

    public static Boolean isSubsequence(char[] string1, char[] string2) throws Exception {
        if(string1 == null || string2 == null){
            throw new Exception("isSubsequence:: Invalid Input");
        }
        int startPositionString1 = 0;
        int startPositionString2 = 0;
        do{
            if(string1[startPositionString1] == string2[startPositionString2]) {
                startPositionString1 += 1;
            }
            startPositionString2 +=1;
        }while (
                startPositionString1 < string1.length
        );
        return startPositionString1 == string1.length;
    }

    public static void main(String args[]) throws Exception {
        String string1 = "abc";
        String string2 = "ahbgdc";
        System.out.print(isSubsequence(string1.toCharArray(), string2.toCharArray()));
    }

}
