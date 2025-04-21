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
/*
* Time Complexity Analysis:
The function uses two pointers:

startPositionString1 iterates over string1.
startPositionString2 iterates over string2.
The outer do-while loop continues until all characters of string1 are matched within string2 or until startPositionString2 reaches the end of string2.

In the worst case, every character of string2 is traversed to find a subsequence match for string1. This happens if:

string1 is a subsequence and its characters are scattered throughout string2.
Or, string1 is not a subsequence, and we traverse the entire string2 to confirm it.
Therefore, the time complexity is:

𝑂
(
𝑛
)
O(n)
where
𝑛
n is the length of string2.

Since checking equality and updating pointers are constant-time operations, the complexity is dominated by the traversal of string2.

Space Complexity Analysis:
The function uses a constant amount of extra space for variables:

Two integer pointers: startPositionString1 and startPositionString2.
No additional data structures are used.
Converting the input strings to character arrays incurs space proportional to the size of the input strings,
𝑂
(
𝑚
+
𝑛
)
O(m+n), where
𝑚
m and
𝑛
n are the lengths of string1 and string2, respectively. However, this conversion is not necessary if the strings are accessed directly.

If we exclude the conversion step, the space complexity is:

𝑂
(
1
)
O(1)
*
* Using **Dynamic Programming (DP)**, we solve the problem by constructing a **bottom-up DP table** that answers the question: "For any prefix of `s` and any prefix of `t`, is the prefix of `s` a subsequence of the prefix of `t`?"

---

### **Approach**

#### **Key Idea**
We use a 2D DP table where:
- `dp[i][j]` is a boolean that represents whether the first `i` characters of `s` are a subsequence of the first `j` characters of `t`.

#### **Base Cases**
1. If `i == 0` (empty string `s`), it is always a subsequence of any prefix of `t`. Hence, `dp[0][j] = true` for all `j`.
2. If `j == 0` (empty string `t`), a non-empty `s` cannot be a subsequence of `t`. Hence, `dp[i][0] = false` for all `i > 0`.

#### **Transition**
- If `s[i-1] == t[j-1]`, then:
  - `dp[i][j] = dp[i-1][j-1]` (we match the current characters and move to the next characters in both strings).
- Otherwise:
  - `dp[i][j] = dp[i][j-1]` (skip the current character of `t` and check if `s` is a subsequence of the remaining `t`).

#### **Final Answer**
The value of `dp[m][n]` (where `m` is the length of `s` and `n` is the length of `t`) tells us whether `s` is a subsequence of `t`.

---

### **Code**

```java
public class IsSubsequenceDP {
    public static boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();

        // DP table to store the results
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: Empty `s` is a subsequence of any prefix of `t`
        for (int j = 0; j <= n; j++) {
            dp[0][j] = true;
        }

        // Base case: Non-empty `s` cannot be a subsequence of an empty `t`
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        // The final answer is whether the entire `s` is a subsequence of `t`
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String t1 = "ahbgdc";
        System.out.println(isSubsequence(s1, t1)); // Output: true

        String s2 = "axc";
        String t2 = "ahbgdc";
        System.out.println(isSubsequence(s2, t2)); // Output: false
    }
}
```

---

### **Dry Run**

#### Input: `s = "abc"`, `t = "ahbgdc"`

1. **Initialize Base Cases**:
   - `dp[0][...] = true` (empty `s` is a subsequence of any prefix of `t`).
   - `dp[1][0], dp[2][0], dp[3][0] = false` (non-empty `s` cannot be a subsequence of an empty `t`).

2. **Fill the Table**:

|   `i/j`  |   0   |   1   |   2   |   3   |   4   |   5   |   6   |
|----------|--------|-------|-------|-------|-------|-------|-------|
| **0**    | `true` | `true`| `true`| `true`| `true`| `true`| `true`|
| **1**    | `false`| `true`| `true`| `true`| `true`| `true`| `true`|
| **2**    | `false`| `false`| `false`| `true`| `true`| `true`| `true`|
| **3**    | `false`| `false`| `false`| `false`| `false`| `false`| `true`|

3. **Result**: `dp[3][6] = true`.

---

### **Time and Space Complexity**

#### **Time Complexity**:
- Filling the DP table takes \(O(m \times n)\), where \(m\) is the length of `s` and \(n\) is the length of `t`.

#### **Space Complexity**:
- The space complexity is \(O(m \times n)\) due to the DP table.

#### **Optimization**:
We can reduce the space complexity to \(O(n)\) by using a 1D array and iterating backward for the `dp` transitions.

Let me know if you'd like an optimized version!
* */