package Basic;

class RollingHash {
    public static int searchPattern(String text, String pattern, int base, int prime) {
        int n = text.length();
        int m = pattern.length();

        // Precompute base^(m-1) % prime
        int h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % prime;
        }

        int patternHash = 0; // Hash for the pattern
        int textHash = 0;    // Hash for the current window in the text

        // Compute the hash value for the pattern and the first window of the text
        for (int i = 0; i < m; i++) {
            patternHash = (base * patternHash + pattern.charAt(i)) % prime;
            textHash = (base * textHash + text.charAt(i)) % prime;
        }

        // Slide the window over the text one by one
        for (int i = 0; i <= n - m; i++) {
            // Check if the hash values of the current window of text matches the pattern
            if (patternHash == textHash) {
                // If the hash values match, check character by character
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i; // Pattern found at index i
                }
            }

            // Calculate the hash value for the next window
            if (i < n - m) {
                textHash = (base * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;

                // We might get a negative value of textHash, so convert it to positive
                if (textHash < 0) {
                    textHash += prime;
                }
            }
        }
        return -1; // Pattern not found
    }

    public static void main(String[] args) {
        String text = "abcdeabcdf";
        String pattern = "abcd";
        int base = 256; // Extended ASCII characters
        int prime = 101; // A prime number to use as modulus

        int result = searchPattern(text, pattern, base, prime);
        if (result != -1) {
            System.out.println("Pattern found at index: " + result);
        } else {
            System.out.println("Pattern not found.");
        }
    }
}
/**
 * The **Rolling Hash** technique is a hashing method that allows efficient recalculation of hash values when sliding a window over a string or array. It’s particularly useful in algorithms like **Rabin-Karp** for pattern matching, where we need to compute hashes of consecutive substrings quickly.
 *
 * ### Key Idea:
 * A rolling hash allows us to compute the hash value of a new substring based on the hash value of the previous substring. This avoids recomputing the hash from scratch and instead uses a fast update operation when sliding the window one character at a time.
 *
 * Let’s go through the concept of rolling hash step by step.
 *
 * ### Rolling Hash Formula:
 *
 * Assume we are using a **base `b`** and a **modulus `m`** (usually a prime number) to calculate the hash of a string `S` of length `n`. The hash for the substring `S` can be defined as:
 *
 * \[ H(S) = (S[0] \times b^{n-1} + S[1] \times b^{n-2} + \dots + S[n-1] \times b^0) \mod m \]
 *
 * ### Sliding the Window:
 * Suppose we have the hash value for a substring `S[i:i+n]` (starting at index `i` with length `n`), and we want to compute the hash value for the next substring `S[i+1:i+n+1]`. Instead of recalculating the entire hash, we can compute it using the **rolling hash** formula:
 *
 * Let:
 * - `old_hash` be the hash of the previous substring `S[i:i+n]`,
 * - `new_hash` be the hash of the new substring `S[i+1:i+n+1]`,
 * - `base` be a constant integer (like 31 or 256 for ASCII characters),
 * - `mod` be a prime number used to reduce the hash value (to avoid overflow).
 *
 * The new hash can be computed by:
 * 1. **Removing the effect** of the old character `S[i]` from the `old_hash`.
 * 2. **Adding the effect** of the new character `S[i+n]` to the `new_hash`.
 *
 * The formula is:
 *
 * \[
 * \text{new\_hash} = \left( \left( \text{old\_hash} - S[i] \times b^{n-1} \right) \times b + S[i+n] \right) \mod m
 * \]
 *
 * ### Breaking Down the Formula:
 * 1. **Remove the old character**: To remove the contribution of the old character `S[i]` from the previous hash, subtract the term `S[i] * b^{n-1}` from `old_hash`.
 * 2. **Shift the remaining substring**: Multiply the result by `b` to shift the substring left by one position (effectively moving the window).
 * 3. **Add the new character**: Add the new character `S[i+n]` to account for the next substring.
 * 4. **Modulo operation**: To ensure the hash value stays within bounds (avoid overflow), use the modulus `m`.
 *
 * ### Example of Rolling Hash:
 *
 * Let’s see a simple example of applying rolling hash to find a pattern `P` in a text `T`.
 *
 * #### Example:
 *
 * ```java
 * class RollingHash {
 *     public static int searchPattern(String text, String pattern, int base, int prime) {
 *         int n = text.length();
 *         int m = pattern.length();
 *
 *         // Precompute base^(m-1) % prime
 *         int h = 1;
 *         for (int i = 0; i < m - 1; i++) {
 *             h = (h * base) % prime;
 *         }
 *
 *         int patternHash = 0; // Hash for the pattern
 *         int textHash = 0;    // Hash for the current window in the text
 *
 *         // Compute the hash value for the pattern and the first window of the text
 *         for (int i = 0; i < m; i++) {
 *             patternHash = (base * patternHash + pattern.charAt(i)) % prime;
 *             textHash = (base * textHash + text.charAt(i)) % prime;
 *         }
 *
 *         // Slide the window over the text one by one
 *         for (int i = 0; i <= n - m; i++) {
 *             // Check if the hash values of the current window of text matches the pattern
 *             if (patternHash == textHash) {
 *                 // If the hash values match, check character by character
 *                 boolean match = true;
 *                 for (int j = 0; j < m; j++) {
 *                     if (text.charAt(i + j) != pattern.charAt(j)) {
 *                         match = false;
 *                         break;
 *                     }
 *                 }
 *                 if (match) {
 *                     return i; // Pattern found at index i
 *                 }
 *             }
 *
 *             // Calculate the hash value for the next window
 *             if (i < n - m) {
 *                 textHash = (base * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % prime;
 *
 *                 // We might get a negative value of textHash, so convert it to positive
 *                 if (textHash < 0) {
 *                     textHash += prime;
 *                 }
 *             }
 *         }
 *         return -1; // Pattern not found
 *     }
 *
 *     public static void main(String[] args) {
 *         String text = "abcdeabcdf";
 *         String pattern = "abcd";
 *         int base = 256; // Extended ASCII characters
 *         int prime = 101; // A prime number to use as modulus
 *
 *         int result = searchPattern(text, pattern, base, prime);
 *         if (result != -1) {
 *             System.out.println("Pattern found at index: " + result);
 *         } else {
 *             System.out.println("Pattern not found.");
 *         }
 *     }
 * }
 * ```
 *
 * ### Example Walkthrough:
 *
 * Let’s say we want to find the pattern `"abcd"` in the text `"abcdeabcdf"`.
 *
 * 1. **Initial hash calculation**:
 *    - The hash for the pattern `"abcd"` is computed as:
 *      \[
 *      \text{hash("abcd")} = (97 \times 256^3 + 98 \times 256^2 + 99 \times 256^1 + 100 \times 256^0) \mod 101
 *      \]
 *    - The hash for the first window of the text (`"abcd"`) is also calculated.
 *
 * 2. **Sliding the window**:
 *    - After checking the first window, the window slides by one character to look at `"bcde"`, and the new hash is computed by adjusting the old hash.
 *    - This process continues until the entire text is scanned.
 *
 * ### Time Complexity of Rolling Hash:
 * - **Preprocessing the initial hash**: This takes `O(m)` time, where `m` is the length of the pattern.
 * - **Rolling the hash**: Each window hash is computed in constant time `O(1)`, so for a text of length `n` and pattern length `m`, it takes `O(n - m + 1)` or approximately `O(n)` to compute all window hashes.
 *
 * Thus, the total time complexity of the Rabin-Karp algorithm with rolling hash is:
 * - **Best case**: `O(n + m)` when there are no hash collisions.
 * - **Worst case**: `O(n * m)` if hash collisions occur frequently, leading to character-by-character comparisons.
 *
 * ### Space Complexity:
 * - **O(1)** for storing the rolling hash value, `base`, `prime`, and some intermediate variables.
 *
 * ### Summary of Benefits:
 * - **Efficiency**: Rolling hash allows you to slide a window over a text efficiently without recalculating the hash for each substring from scratch.
 * - **Modular Arithmetic**: The use of modulo operations ensures that the hash value remains manageable even for large strings.
 * **/