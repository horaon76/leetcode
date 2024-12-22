Here's a detailed example of calculating the Fibonacci series in Java using Dynamic Programming (DP). We'll include both a **Top-Down (Memoization)** and a **Bottom-Up (Tabulation)** approach, along with a few examples.

---

### Top-Down Approach (Memoization)
This approach uses recursion with a cache (array) to store previously computed values.

```java
import java.util.Arrays;

public class FibonacciDP {

    // Top-Down Approach (Memoization)
    public static int fibonacciTopDown(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n]; // Return cached result if available

        memo[n] = fibonacciTopDown(n - 1, memo) + fibonacciTopDown(n - 2, memo); // Recursive call
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 10; // Example: Find the 10th Fibonacci number
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1); // Initialize cache with -1
        
        System.out.println("Fibonacci (" + n + ") using Memoization: " + fibonacciTopDown(n, memo));
    }
}
```

---

### Bottom-Up Approach (Tabulation)
This approach uses an iterative method to fill up a table (array) from the base case.

```java
public class FibonacciDP {

    // Bottom-Up Approach (Tabulation)
    public static int fibonacciBottomUp(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 10; // Example: Find the 10th Fibonacci number

        System.out.println("Fibonacci (" + n + ") using Tabulation: " + fibonacciBottomUp(n));
    }
}
```

---

### Optimized Space Approach
To optimize space, we can store only the last two Fibonacci numbers instead of maintaining a full array.

```java
public class FibonacciDP {

    // Optimized Space Approach
    public static int fibonacciOptimized(int n) {
        if (n <= 1) return n;

        int prev2 = 0, prev1 = 1, current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    public static void main(String[] args) {
        int n = 10; // Example: Find the 10th Fibonacci number

        System.out.println("Fibonacci (" + n + ") using Optimized Space: " + fibonacciOptimized(n));
    }
}
```

---

### Example Output
For \( n = 10 \):
- Fibonacci number using Memoization: `55`
- Fibonacci number using Tabulation: `55`
- Fibonacci number using Optimized Space: `55`

### Explanation of DP:
- **Subproblem**: Fibonacci at position `n` depends on the Fibonacci at positions `n-1` and `n-2`.
- **State**: `dp[i]` stores the Fibonacci number at position `i`.
- **Transition**: `dp[i] = dp[i-1] + dp[i-2]`.
- **Base Case**: `dp[0] = 0` and `dp[1] = 1`.

### Complexity:
- **Time Complexity**: \( O(n) \) for all approaches.
- **Space Complexity**:
    - Memoization: \( O(n) \) for the cache.
    - Tabulation: \( O(n) \) for the array.
    - Optimized Space: \( O(1) \).
- The DP array in Fibonacci calculations is often of size \( n+1 \) because we want to store the Fibonacci numbers for indices from \( 0 \) to \( n \), inclusive. Here's a detailed explanation:

---

### Key Points:

1. **Indexing Starts from 0**:
    - Fibonacci numbers are indexed starting from \( 0 \), i.e., \( F(0) = 0 \) and \( F(1) = 1 \).
    - To compute \( F(n) \), we need values for \( F(0), F(1), \dots, F(n) \).

2. **Storage for All Values**:
    - If the DP array is of size \( n \), the highest index we can access is \( n-1 \).
    - For \( F(n) \), we need the \( n \)-th index as well, so the array needs to be of size \( n+1 \).

3. **Example**:
    - For \( n = 5 \):
        - Fibonacci sequence: \( 0, 1, 1, 2, 3, 5 \).
        - DP array indices: \( 0, 1, 2, 3, 4, 5 \).
        - The DP array must be of size \( 6 \) (i.e., \( n+1 \)) to accommodate all these values.

---

### Code Example:

#### Incorrect Array Size
If we use an array of size \( n \), it will result in an error:

```java
int n = 5;
int[] dp = new int[n]; // Size is 5

dp[0] = 0;
dp[1] = 1;

for (int i = 2; i <= n; i++) { // Accessing dp[5] will throw ArrayIndexOutOfBoundsException
    dp[i] = dp[i-1] + dp[i-2];
}
```

#### Correct Array Size
To avoid the error, the array must be of size \( n+1 \):

```java
int n = 5;
int[] dp = new int[n + 1]; // Size is 6

dp[0] = 0;
dp[1] = 1;

for (int i = 2; i <= n; i++) { // Accessing dp[5] is valid
    dp[i] = dp[i-1] + dp[i-2];
}
System.out.println("Fibonacci (" + n + ") = " + dp[n]); // Output: 5
```

---

### Why is This Important?

- **Prevention of Indexing Errors**: Using \( n+1 \) ensures we don't run into `ArrayIndexOutOfBoundsException`.
- **Inclusive Computation**: It allows us to compute and store Fibonacci numbers up to \( F(n) \), including the \( n \)-th number.
- **Consistency**: This approach makes it easier to understand and implement DP solutions without special edge-case handling.

By allocating \( n+1 \) space, the DP table cleanly handles indices \( 0 \) through \( n \).