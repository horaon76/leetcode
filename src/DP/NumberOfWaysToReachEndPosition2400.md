LeetCode Problem 2400, **Number of Ways to Reach a Position After Exactly k Steps**, requires calculating the number of distinct ways to move from a starting position to an ending position on an infinite number line, using exactly \( k \) steps. Each step can be either to the left or to the right.

**Problem Statement:**

Given two integers `startPos` and `endPos`, and a positive integer `k`, determine the number of different ways to reach `endPos` from `startPos` in exactly `k` steps. Since the result can be large, return it modulo \( 10^9 + 7 \).

**Constraints:**

- \( 1 \leq \text{startPos}, \text{endPos}, k \leq 1000 \)

**Key Observations:**

1. **Parity Check:** To reach `endPos` from `startPos` in exactly `k` steps, the difference between `endPos` and `startPos` must have the same parity as `k`. If they differ in parity, it's impossible to reach the destination in exactly `k` steps, and the result should be 0.

2. **Step Calculation:** Let \( d = \text{endPos} - \text{startPos} \). To achieve this displacement in exactly `k` steps:
    - The number of right steps minus the number of left steps must equal \( d \).
    - The total number of steps (right + left) must equal `k`.

   Solving these equations, we find:
    - Number of right steps \( r = \frac{k + d}{2} \)
    - Number of left steps \( l = \frac{k - d}{2} \)

   Both \( r \) and \( l \) must be non-negative integers. If \( r \) or \( l \) is negative or not an integer, it's impossible to reach `endPos` in exactly `k` steps.

**Solution Approach:**

1. **Check Parity:** If \( d \) and `k` have different parity, return 0.

2. **Calculate Steps:** Compute \( r \) and \( l \). If either is negative or not an integer, return 0.

3. **Combinatorial Calculation:** If valid, the number of ways to arrange \( r \) right steps and \( l \) left steps in `k` steps is given by the binomial coefficient \( C(k, r) \), which represents choosing \( r \) positions for right steps out of `k` total steps.

**Implementation in Java:**

```java
public class NumberOfWays {
    private static final int MOD = 1_000_000_007;

    public int numberOfWays(int startPos, int endPos, int k) {
        int d = endPos - startPos;

        // Check if d and k have the same parity
        if ((d & 1) != (k & 1)) return 0;

        // Calculate number of right and left steps
        int r = (k + d) / 2;
        int l = (k - d) / 2;

        // Check if r and l are non-negative
        if (r < 0 || l < 0) return 0;

        // Calculate binomial coefficient C(k, r)
        return binomialCoefficient(k, r);
    }

    private int binomialCoefficient(int n, int k) {
        if (k > n) return 0;
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        long denominator = (fact[k] * fact[n - k]) % MOD;
        return (int) (fact[n] * modInverse(denominator, MOD) % MOD);
    }

    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long x, long y, int mod) {
        long result = 1;
        x = x % mod;
        while (y > 0) {
            if ((y & 1) == 1) {
                result = (result * x) % mod;
            }
            y >>= 1;
            x = (x * x) % mod;
        }
        return result;
    }
}
```

**Explanation:**

- **Parity Check:** Ensures that the difference `d` and the number of steps `k` have the same parity, which is necessary for a valid path.

- **Binomial Coefficient Calculation:** Computes the number of ways to choose `r` right steps out of `k` total steps using factorials and modular arithmetic to handle large numbers and prevent overflow.

- **Modular Inverse:** Utilizes Fermat's Little Theorem to compute the modular inverse, which is essential for division in modular arithmetic.

This approach efficiently computes the number of distinct ways to reach `endPos` from `startPos` in exactly `k` steps, adhering to the problem constraints and ensuring results are within the required modulo.

For a visual explanation and further insights, you may find the following video helpful:

Here are other solutions for **LeetCode 2400 - Number of Ways to Reach a Position After Exactly k Steps**, considering the constraints:

---

### **1. Dynamic Programming (DP Solution)**

Instead of using combinatorics, this solution builds a DP table to calculate the number of ways to reach every possible position using exactly `k` steps.

#### Approach:
1. Define `dp[steps][pos]` as the number of ways to reach position `pos` in `steps` steps.
2. Base Case:
    - `dp[0][startPos] = 1` (1 way to be at the starting position with 0 steps).
    - All other `dp[0][...] = 0`.
3. Transition:
    - To reach `pos` in `steps`, you can come from either `pos - 1` or `pos + 1` in `steps - 1`:
      \[
      dp[steps][pos] = dp[steps-1][pos-1] + dp[steps-1][pos+1]
      \]
4. The result is `dp[k][endPos]`.

#### Java Implementation:

```java
import java.util.HashMap;

public class NumberOfWaysDP {
    private static final int MOD = 1_000_000_007;

    public int numberOfWays(int startPos, int endPos, int k) {
        int maxDisplacement = k + Math.abs(startPos - endPos); // Ensure enough space for all positions
        HashMap<Integer, Integer>[] dp = new HashMap[k + 1];

        for (int i = 0; i <= k; i++) {
            dp[i] = new HashMap<>();
        }
        dp[0].put(startPos, 1); // Base case

        for (int steps = 1; steps <= k; steps++) {
            for (int pos : dp[steps - 1].keySet()) {
                int ways = dp[steps - 1].get(pos);
                dp[steps].put(pos - 1, (dp[steps].getOrDefault(pos - 1, 0) + ways) % MOD);
                dp[steps].put(pos + 1, (dp[steps].getOrDefault(pos + 1, 0) + ways) % MOD);
            }
        }
        return dp[k].getOrDefault(endPos, 0);
    }
}
```

#### Complexity:
- **Time Complexity**: \( O(k \times m) \), where \( m \) is the number of distinct positions reachable in \( k \) steps.
- **Space Complexity**: \( O(k \times m) \) (can be optimized to \( O(m) \)).

---

### **2. Dynamic Programming with Space Optimization**

Instead of maintaining a DP table for all `k` steps, keep track of only the current and previous step's states.

#### Optimized Java Implementation:

```java
import java.util.HashMap;

public class NumberOfWaysOptimizedDP {
    private static final int MOD = 1_000_000_007;

    public int numberOfWays(int startPos, int endPos, int k) {
        HashMap<Integer, Integer> prev = new HashMap<>();
        prev.put(startPos, 1); // Base case

        for (int steps = 1; steps <= k; steps++) {
            HashMap<Integer, Integer> curr = new HashMap<>();
            for (int pos : prev.keySet()) {
                int ways = prev.get(pos);
                curr.put(pos - 1, (curr.getOrDefault(pos - 1, 0) + ways) % MOD);
                curr.put(pos + 1, (curr.getOrDefault(pos + 1, 0) + ways) % MOD);
            }
            prev = curr;
        }
        return prev.getOrDefault(endPos, 0);
    }
}
```

#### Complexity:
- **Time Complexity**: \( O(k \times m) \), where \( m \) is the number of distinct positions reachable in \( k \) steps.
- **Space Complexity**: \( O(m) \).

---

### **3. Precompute Factorials for Binomial Coefficients**

If there are multiple queries, precomputing factorials can save computation time.

#### Approach:
1. Precompute all factorials up to \( k \) and their modular inverses.
2. Use the binomial coefficient formula directly for each query.

#### Java Implementation:

```java
public class NumberOfWaysPrecomputed {
    private static final int MOD = 1_000_000_007;
    private static long[] fact;
    private static long[] invFact;

    public NumberOfWaysPrecomputed() {
        int max = 1000; // Maximum k constraint
        fact = new long[max + 1];
        invFact = new long[max + 1];
        fact[0] = 1;
        for (int i = 1; i <= max; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[max] = modInverse(fact[max], MOD);
        for (int i = max - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }

    public int numberOfWays(int startPos, int endPos, int k) {
        int d = endPos - startPos;
        if ((d & 1) != (k & 1) || d > k || d < -k) return 0;

        int r = (k + d) / 2;
        return (int) (fact[k] * invFact[r] % MOD * invFact[k - r] % MOD);
    }

    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long x, long y, int mod) {
        long res = 1;
        x %= mod;
        while (y > 0) {
            if ((y & 1) == 1) res = res * x % mod;
            y >>= 1;
            x = x * x % mod;
        }
        return res;
    }
}
```

#### Complexity:
- **Precomputation Time**: \( O(k) \).
- **Query Time**: \( O(1) \).
- **Space Complexity**: \( O(k) \) for factorial arrays.

---

### Comparison of Solutions:

| **Approach**            | **Time Complexity**  | **Space Complexity** | **Best Use Case**                                    |
|--------------------------|----------------------|-----------------------|-----------------------------------------------------|
| DP (HashMap)             | \( O(k \times m) \) | \( O(k \times m) \)   | Single query, larger constraints.                  |
| Optimized DP             | \( O(k \times m) \) | \( O(m) \)            | Memory-efficient for single query.                 |
| Precomputed Factorials   | \( O(1) \)          | \( O(k) \)            | Best for multiple queries with the same \( k \).    |

---

The **Precomputed Factorials** approach is the most efficient for multiple queries, while the **Optimized DP** is the simplest for single queries with moderate constraints.