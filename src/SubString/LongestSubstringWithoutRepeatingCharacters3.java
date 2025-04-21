package SubString;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest
 * substring
 *  without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * **/

/*
* Intuition
The problem can be solved using the sliding window technique. The idea is to use two pointers (start and end) to represent the current substring and a set or map to track characters in the substring.

Expand the window by moving the end pointer and include the character at end in the current substring.
If a character is repeated, move the start pointer to shrink the window until the substring no longer contains duplicate characters.
Track the maximum length of the substring without repeating characters during the process.
*
* */
public class LongestSubstringWithoutRepeatingCharacters3 {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (!charSet.contains(s.charAt(right))) {
                charSet.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
            } else {
                while (charSet.contains(s.charAt(right))) {
                    charSet.remove(s.charAt(left));
                    left++;
                }
                charSet.add(s.charAt(right));
            }
        }

        return maxLength;
    }
}
/*

Time Complexity
The algorithm uses the sliding window technique, where two pointers (start and end) traverse the string s.

The end pointer iterates through the string, examining each character exactly once. This is a straightforward traversal and takes O(n) time, where n is the length of the string.

The start pointer moves forward only when a duplicate character is encountered. This operation involves removing elements from the set, but each character is added and removed from the set at most once.

Thus, the combined movement of both start and end pointers results in each character being processed exactly twice (once when added and once when removed). Therefore, the time complexity is:

O(n).


The algorithm uses a HashSet to store the characters of the current substring.

The maximum size of the set is determined by the size of the character set in the input string. If the string contains only unique characters, the set can grow up to a size of min(n, a), where:

n is the length of the string.
a is the size of the character set (e.g., 26 for lowercase English letters or 128 for ASCII characters).
In the worst case, the space complexity is proportional to the size of the set, which is O(min(n, a)).




* Example Walkthrough
Input: "abcabcbb"
Expand window: a → ab → abc
Encounter duplicate a: shrink window to bc → bca
Continue expanding and shrinking as needed.
Output: 3 (substring "abc" or "bca")
Input: "bbbbb"
Expand window: b
Encounter duplicate: shrink window repeatedly.
Output: 1 (substring "b")


Yes! There are alternative solutions to solve **LeetCode 3 (Longest Substring Without Repeating Characters)**. Here's a breakdown of other approaches:

---

### **1. Sliding Window with HashMap**
Instead of using a `HashSet`, we can use a `HashMap` to track the last seen index of each character. This allows us to move the `start` pointer directly to skip over duplicates, making the algorithm more efficient in practice.

#### **Algorithm**
1. Use a `HashMap` to store the characters and their most recent indices.
2. Iterate through the string with the `end` pointer.
3. If the current character is already in the map and its last seen index is within the current window, move the `start` pointer to the right of the duplicate character's last index.
4. Update the character's index in the map and calculate the maximum length.

#### **Java Implementation**
```java
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharactersMap {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0, start = 0;

        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);
            if (map.containsKey(current) && map.get(current) >= start) {
                // Move the start pointer to the right of the duplicate
                start = map.get(current) + 1;
            }
            // Update the character's index
            map.put(current, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of longest substring without repeating characters: " + lengthOfLongestSubstring(s));
    }
}
```

#### **Time and Space Complexity**
- **Time Complexity**: **O(n)**
  Both pointers traverse the string once. The `HashMap` operations (get, put, and containsKey) take **O(1)** time on average.
- **Space Complexity**: **O(min(n, a))**, where `a` is the size of the character set.

---

### **2. Optimized Sliding Window with Fixed Array**
If the input string is guaranteed to consist of a fixed set of characters (e.g., ASCII), we can use a fixed-size array instead of a `HashMap` to store the last seen indices of characters. This reduces the overhead of hash-based operations.

#### **Algorithm**
1. Create an array `index[128]` (for ASCII characters) initialized to `-1`.
2. Iterate through the string with the `end` pointer.
3. Update the `start` pointer to skip over duplicates using the character's last seen index stored in the array.
4. Update the character's index in the array and calculate the maximum length.

#### **Java Implementation**
```java
public class LongestSubstringWithoutRepeatingCharactersArray {
    public static int lengthOfLongestSubstring(String s) {
        int[] index = new int[128]; // Array to store the last seen index of each character
        for (int i = 0; i < 128; i++) {
            index[i] = -1;
        }
        int maxLength = 0, start = 0;

        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);
            if (index[current] >= start) {
                // Move the start pointer to the right of the duplicate
                start = index[current] + 1;
            }
            // Update the last seen index of the current character
            index[current] = end;
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of longest substring without repeating characters: " + lengthOfLongestSubstring(s));
    }
}
```

#### **Time and Space Complexity**
- **Time Complexity**: **O(n)**
  The string is traversed once, and updating the array is **O(1)** per operation.
- **Space Complexity**: **O(1)**
  The size of the array is fixed at 128 (for ASCII).

---

### **3. Brute Force**
The brute-force approach is to generate all substrings, check if each substring contains unique characters, and keep track of the maximum length.

#### **Algorithm**
1. Generate all possible substrings using nested loops.
2. For each substring, use a set to check if all characters are unique.
3. Track the maximum length of valid substrings.

#### **Java Implementation**
```java
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharactersBruteForce {
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                char current = s.charAt(j);
                if (set.contains(current)) {
                    break;
                }
                set.add(current);
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of longest substring without repeating characters: " + lengthOfLongestSubstring(s));
    }
}
```

#### **Time and Space Complexity**
- **Time Complexity**: **O(n²)**
  The outer loop runs `n` times, and the inner loop runs up to `n` times in the worst case.
- **Space Complexity**: **O(n)**
  The `HashSet` used to check for duplicates can grow up to the size of the substring.

---

### **Comparison of Solutions**

| Approach                         | Time Complexity | Space Complexity | Notes                                      |
|----------------------------------|-----------------|------------------|--------------------------------------------|
| Sliding Window with HashSet      | O(n)            | O(min(n, a))     | Simple and intuitive.                     |
| Sliding Window with HashMap      | O(n)            | O(min(n, a))     | Efficient for variable-sized character sets. |
| Sliding Window with Fixed Array  | O(n)            | O(1)             | Most efficient for fixed character sets.  |
| Brute Force                      | O(n²)           | O(n)             | Inefficient for large inputs.             |

---

### **Recommendation**
- **Fixed Array**: Use if the input string is restricted to ASCII characters.
- **HashMap**: Use for strings with non-ASCII characters (e.g., Unicode).

* */