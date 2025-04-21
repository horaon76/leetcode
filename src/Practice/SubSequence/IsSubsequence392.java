package Practice.SubSequence;

public class IsSubsequence392 {

    public static Boolean checkIsSubsequence(char[] compareString, char[] compareWithString) {
        Integer compareStringPos = 0;
        Integer compareWithStringPos = 0;
        do {
            if (compareString[compareStringPos] == compareWithString[compareWithStringPos]) {
                compareStringPos++;
            }
            compareWithStringPos++;
        } while (compareStringPos < compareString.length);
        return compareStringPos == compareString.length;
    }

    public static Boolean checkIsSubsequenceDP(char[] compareString, char[] compareWithString) {
        int m = compareString.length;
        int n = compareWithString.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            dp[i][1] = 0;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (compareString[i - 1] == compareWithString[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[compareString.length][compareWithString.length] == 1 ? true : false;
    }

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "ahbgdc";
        System.out.println(checkIsSubsequenceDP(str1.toCharArray(), str2.toCharArray()));
    }
}
