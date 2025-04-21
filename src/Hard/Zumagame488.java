package Hard;

import java.util.*;

class Zumagame488 {

    final static Map<String, Integer> memo = new HashMap<>();

    public static int findMinStep(String board, String hand) {
        int result = dfs(board, hand);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int dfs(String board, String hand) {
        if (board.length() == 0) return 0;
        if (hand.length() == 0) return Integer.MAX_VALUE;

        String key = board + "#" + hand;
        if (memo.containsKey(key)) return memo.get(key);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < hand.length(); i++) {
            char ch = hand.charAt(i);
//            String newHand = hand.substring(0, i) + hand.substring(i + 1);
            String newHand = new StringBuilder(hand).deleteCharAt(i).toString();

            // Try inserting ch at every position in board
            for (int j = 0; j <= board.length(); j++) {
                // Don't insert in the middle of same chars unless ch matches
                if (j > 0 && board.charAt(j - 1) == ch) continue;

                // Insert ch
//              String newBoard = board.substring(0, j) + ch + board.substring(j);
                String newBoard = new StringBuilder(board).insert(j, ch).toString();
                newBoard = reduce(newBoard);

                int steps = dfs(newBoard, newHand);
                if (steps != Integer.MAX_VALUE) {
                    min = Math.min(min, steps + 1);
                }
            }
        }

        memo.put(key, min);
        return min;
    }

    // Reduces the board by removing groups of 3 or more same chars
    private static String reduce(String board) {
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                j++;
            }
            if (j - i >= 3) {
                // Remove board[i..j-1]
                board = board.substring(0, i) + board.substring(j);
                // Restart from beginning after each collapse
                i = 0;
            } else {
                i++;
            }
        }
        return board;
    }

    public static void main(String[] args) {

        String board = "WRRBBW";
        String hand = "RB";

        System.out.println(findMinStep(board, hand));  // Output: -1

        board = "WWRRBBWW";
        hand = "WRBRW";
        System.out.println(findMinStep(board, hand));  // Output: 2
    }
}
/**
 * Let’s **dry run the recursive DFS** in your `Zumagame488` code using the input:
 *
 * ```java
 * board = "WRRBBW";
 * hand  = "RB";
 * ```
 *
 * We’ll simulate how the code explores all paths to clear the board using balls from the hand.
 *
 * ---
 *
 * ### 🔍 Step 1: Initial Call
 *
 * ```java
 * dfs("WRRBBW", "RB")
 * ```
 *
 * **Key:** `"WRRBBW#RB"` → not in `memo`, so continue.
 *
 * ---
 *
 * ### 🌀 Outer loop: Try every character from `hand` (`R` and `B`)
 *
 * ---
 *
 * #### 🧤 Try `R` (i = 0)
 *
 * ```java
 * ch = 'R'
 * newHand = "B"
 * ```
 *
 * ---
 *
 * ##### 💥 Inner loop: Try inserting `R` at each position in `board` (`j` from 0 to 6)
 *
 * ###### j = 0 → Insert at start → `"RWRRBBW"`
 * - Reduce: no group of ≥3 → `"RWRRBBW"`
 * - Recurse: `dfs("RWRRBBW", "B")`
 *
 * ###### j = 1 → `'W' != ch` so allowed → `"WRRRBBW"`
 * - Reduce: `'RRR'` → collapse → `"WBBW"`
 * - Recurse: `dfs("WBBW", "B")`
 *
 * ###### j = 2 → invalid: board[j-1] == ch ('R') → **skip**
 *
 * ###### j = 3 → invalid: board[j-1] == ch ('R') → **skip**
 *
 * ###### j = 4 → valid → `"WRRRBBW"` again (already visited) → skip or memoize later
 *
 * ###### j = 5 → valid → `"WRRBRBW"`
 * - Reduce: no collapse
 * - Recurse: `dfs("WRRBRBW", "B")`
 *
 * ###### j = 6 → insert at end → `"WRRBBWR"`
 * - Reduce: no collapse
 * - Recurse: `dfs("WRRBBWR", "B")`
 *
 * ---
 *
 * #### 🧤 Try `B` (i = 1)
 *
 * ```java
 * ch = 'B'
 * newHand = "R"
 * ```
 *
 * ---
 *
 * ##### 💥 Inner loop: Try inserting `B` at each position in `board`
 *
 * ###### j = 0 → `"BWRRBBW"` → no collapse → `dfs(...)`
 *
 * ###### j = 1 → `"WBRRBBW"` → no collapse → `dfs(...)`
 *
 * ###### j = 2 → `"WRBRBBW"` → no collapse → `dfs(...)`
 *
 * ###### j = 3 → `"WRRBBBW"` → `'BBB'` collapse → `"WRRW"` → `dfs("WRRW", "R")`
 *
 * ###### j = 4 → skip (board[j-1] == ch 'B')
 *
 * ###### j = 5 → skip (board[j-1] == ch 'B')
 *
 * ###### j = 6 → `"WRRBBWB"` → no collapse → `dfs(...)`
 *
 * ---
 *
 * At each step, we continue to recurse and reduce boards. When we get an empty board (`""`), we return `0`. For each valid recursive path, we add `1` to the result (since we inserted 1 ball). If a path reaches `hand.length() == 0` and the board isn’t empty, we return `Integer.MAX_VALUE`.
 *
 * ---
 *
 * ### 🔚 End State
 *
 * - The algorithm exhaustively tries all combinations.
 * - It memoizes state with key `board#hand` to prevent re-exploration.
 * - In this case, **no path can clear `"WRRBBW"` using `"RB"`**, so all attempts return `Integer.MAX_VALUE`.
 *
 * ### ✅ Final Answer:
 *
 * ```java
 * findMinStep("WRRBBW", "RB") → -1
 * ```
 *
 * ---
 *
 * ### Want a Trace with Output 2?
 *
 * Try this input:
 *
 * ```java
 * findMinStep("WWRRBBWW", "WRBRW")
 * ```
 *
 * Would you like a dry run on this one to see how a **successful path** looks and gets to a result like `2`?
 *
 *
 *
 *
 * Certainly! Let's dive deeper into the **time** and **space complexity** of the solution, with more detailed reasoning for both the DFS recursion and memoization.
 *
 * ### **Problem Recap**:
 * The problem is to clear the Zuma board by inserting balls from the hand, and each insertion may cause the board to reduce (collapse groups of 3 or more consecutive balls of the same color). We're using a **DFS approach** with **memoization** to try all possible combinations of ball insertions while optimizing for the minimum number of insertions.
 *
 * ---
 *
 * ### 1. **Time Complexity Breakdown**:
 *
 * To analyze the **time complexity**, we need to examine the key steps in the recursive DFS process:
 *
 * #### **a. Recursive DFS Function**:
 * The DFS function tries inserting each ball from the hand into every possible position of the board and then reduces the board (if needed). After each insertion, the function calls itself recursively to explore the next steps.
 *
 * ### **Key Operations** in DFS:
 * 1. **Loop Over Hand**: For each ball in `hand`, we try inserting it into all positions of the `board`.
 *    - If the length of `hand` is `n`, there are `n` balls in total to try inserting.
 *    - For each ball, there are `m+1` possible positions in the `board` where it can be inserted (since the board's length is `m`).
 *
 * 2. **Board Reduction**: After each insertion, we reduce the `board` by removing groups of 3 or more consecutive balls.
 *    - In the worst case, reducing the board could take `O(m)` time (since we may need to scan through the entire board to identify and collapse groups).
 *
 * 3. **Recursive Calls**: The DFS will recurse into smaller subproblems, where the board will become shorter as groups of balls are removed, and the hand will have one less ball.
 *
 * So, for each recursive call:
 * - We loop over `n` balls in `hand` → `O(n)` operations.
 * - For each ball, we try inserting it into `m+1` positions → `O(m)` operations for each insertion.
 * - After each insertion, we reduce the board → `O(m)` time complexity to collapse the board.
 *
 * Thus, for each recursive call, the cost is approximately:
 *
 * \[
 * O(n) \times O(m) \times O(m) = O(n \times m^2)
 * \]
 *
 * #### **b. Number of Recursive Calls**:
 * The recursive function explores all possible paths where we insert balls from the hand into the board. At each step, the hand is shortened by one ball, and we explore up to `m+1` insert positions.
 *
 * - **Depth of Recursion**: In the worst case, the recursion goes as deep as the number of balls in the `hand` (i.e., `n`), since each recursion processes one ball from the `hand`.
 * - **Total Recursive States**: The recursive calls are divided into different states based on the combination of:
 *   1. **Board configuration**: There are `m` positions in the board, and the balls may be arranged in various ways.
 *   2. **Remaining hand**: There are `n` balls to insert.
 *
 * Thus, there are at most `O(m * 5^n)` unique states, where `5^n` is derived from the assumption that there are 5 possible colors for each ball (typical for Zuma-like games).
 *
 * #### **Memoization**:
 * Memoization helps avoid recalculating the same subproblem multiple times. We store results for a specific configuration of `board` and `hand` using a `Map<String, Integer>` where the key is a string of `board + "#" + hand`.
 *
 * Memoization ensures that the recursive DFS calls do not explore the same state more than once, which significantly reduces the number of redundant calculations.
 *
 * ### **Final Time Complexity**:
 * Taking memoization into account, we only explore each unique state once. The number of unique states is proportional to the number of possible board configurations and the number of balls left in the hand.
 *
 * - The total number of unique states is at most `O(m * 5^n)`, as explained above.
 * - For each state, the recursive function does `O(m^2)` work (due to insertion and reduction).
 *
 * Thus, the total time complexity is:
 *
 * \[
 * O(m^2 \times 5^n)
 * \]
 *
 * Where:
 * - `m^2` comes from the operations to insert and reduce the board.
 * - `5^n` comes from the possible ways balls in the hand can be used.
 *
 * ---
 *
 * ### 2. **Space Complexity Breakdown**:
 *
 * #### **a. Space for Recursion (Call Stack)**:
 * In the worst case, the depth of the recursion is equal to the number of balls in the hand (`n`), since each recursive call handles one ball from the hand.
 *
 * Thus, the **space used by the recursion stack** is `O(n)`.
 *
 * #### **b. Space for Memoization**:
 * We store results in the `memo` map, where the key is a combination of the current `board` and `hand` (i.e., `board#hand`). The number of unique states is bounded by the number of ways we can arrange the board and the hand.
 *
 * - There are at most `O(m * 5^n)` unique states, where `m` is the length of the board and `5^n` is the number of possible configurations of balls in the hand (assuming 5 distinct ball colors).
 * - Each entry in the `memo` map stores a result for a state, which requires constant space.
 *
 * Thus, the space used by memoization is:
 *
 * \[
 * O(m \times 5^n)
 * \]
 *
 * #### **c. Total Space Complexity**:
 * Combining the space for the recursion call stack and the memoization map, the **total space complexity** is:
 *
 * \[
 * O(n + m \times 5^n)
 * \]
 *
 * Where:
 * - `O(n)` is the space for the recursion stack.
 * - `O(m * 5^n)` is the space for memoization.
 *
 * ---
 *
 * ### **Final Complexity Summary**:
 *
 * | **Factor**            | **Time Complexity**      | **Space Complexity**         |
 * |-----------------------|--------------------------|------------------------------|
 * | **Without Memoization** | \(O(n! \times m^n)\)   | \(O(n + m^n)\)               |
 * | **With Memoization**    | \(O(m^2 \times 5^n)\)  | \(O(n + m \times 5^n)\)     |
 *
 * - **Time Complexity (with memoization)**: `O(m^2 * 5^n)`
 * - **Space Complexity (with memoization)**: `O(n + m * 5^n)`
 *
 * ---
 *
 * ### 📌 Key Takeaways:
 * - **Memoization** drastically reduces the time complexity by avoiding redundant calculations for the same `board#hand` state.
 * - The time complexity is primarily driven by the number of unique states (`O(m * 5^n)`), with each state requiring `O(m^2)` time due to insertion and reduction.
 * - Space complexity is dominated by the memoization storage and recursion depth.
 *
 * Let me know if you need further clarification or want to explore specific scenarios!
 *
 *
 * import java.util.*;
 *
 * public class ZumaGameIterative {
 *
 *     static class State {
 *         String board;
 *         String hand;
 *         int steps;
 *
 *         public State(String board, String hand, int steps) {
 *             this.board = board;
 *             this.hand = hand;
 *             this.steps = steps;
 *         }
 *     }
 *
 *     public int findMinStep(String board, String hand) {
 *         Stack<State> stack = new Stack<>();
 *         Map<String, Integer> memo = new HashMap<>();
 *
 *         stack.push(new State(board, hand, 0));
 *         int result = Integer.MAX_VALUE;
 *
 *         while (!stack.isEmpty()) {
 *             State current = stack.pop();
 *             String b = current.board;
 *             String h = current.hand;
 *             int step = current.steps;
 *
 *             if (b.isEmpty()) {
 *                 result = Math.min(result, step);
 *                 continue;
 *             }
 *
 *             String key = b + "#" + h;
 *             if (memo.containsKey(key) && memo.get(key) <= step) {
 *                 continue;
 *             }
 *             memo.put(key, step);
 *
 *             for (int i = 0; i < h.length(); i++) {
 *                 char ch = h.charAt(i);
 *                 String newHand = h.substring(0, i) + h.substring(i + 1);
 *
 *                 for (int j = 0; j <= b.length(); j++) {
 *                     if (j > 0 && b.charAt(j - 1) == ch) continue;
 *
 *                     String newBoard = b.substring(0, j) + ch + b.substring(j);
 *                     newBoard = reduce(newBoard);
 *
 *                     stack.push(new State(newBoard, newHand, step + 1));
 *                 }
 *             }
 *         }
 *
 *         return result == Integer.MAX_VALUE ? -1 : result;
 *     }
 *
 *     private String reduce(String board) {
 *         int i = 0;
 *         while (i < board.length()) {
 *             int j = i;
 *             while (j < board.length() && board.charAt(j) == board.charAt(i)) {
 *                 j++;
 *             }
 *             if (j - i >= 3) {
 *                 board = board.substring(0, i) + board.substring(j);
 *                 i = 0;  // restart to catch new collapses
 *             } else {
 *                 i++;
 *             }
 *         }
 *         return board;
 *     }
 *
 *     public static void main(String[] args) {
 *         ZumaGameIterative solver = new ZumaGameIterative();
 *
 *         System.out.println(solver.findMinStep("WRRBBW", "RB")); // Output: -1
 *         System.out.println(solver.findMinStep("WWRRBBWW", "WRBRW")); // Output: 2
 *     }
 * }
 * */