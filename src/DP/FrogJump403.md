package DP;

import java.util.*;
//A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

[//]: # (Given a list of stones positions &#40;in units&#41; in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.)

[//]: # ()
[//]: # (If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.)

[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (Example 1:)

[//]: # ()
[//]: # (Input: stones = [0,1,3,5,6,8,12,17])

[//]: # (Output: true)

[//]: # (Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.)

[//]: # (Example 2:)

[//]: # ()
[//]: # (Input: stones = [0,1,2,3,4,8,9,11])

[//]: # (Output: false)

[//]: # (Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.)

[//]: # ()
[//]: # ()
[//]: # (Constraints:)

[//]: # ()
[//]: # (2 <= stones.length <= 2000)

[//]: # (0 <= stones[i] <= 231 - 1)

[//]: # (stones[0] == 0)

[//]: # (stones is sorted in a strictly increasing order.)
public class FrogJump403 {

    public static boolean canCross(int[] stones) {
        // If the first jump is not possible, return false
        if (stones[1] != 1) return false;

        // Memoization map to store results of visited stone and last jump
        Map<Integer, Set<Integer>> memo = new HashMap<>();
        for (int stone : stones) {
            memo.put(stone, new HashSet<>());
        }

        // Starting the DFS from the first stone
        return canReachLastStone(stones, 0, 0, memo);
    }

    private static boolean canReachLastStone(int[] stones, int index, int lastJump, Map<Integer, Set<Integer>> memo) {
        int currentStone = stones[index];

        // If we've reached the last stone, return true
        if (index == stones.length - 1) return true;

        // If this combination of stone and jump has been visited, skip it
        if (memo.get(currentStone).contains(lastJump)) return false;

        // Try all possible next jumps (lastJump - 1, lastJump, lastJump + 1)
        for (int jump = lastJump - 1; jump <= lastJump + 1; jump++) {
            if (jump <= 0) continue; // Skip invalid jumps

            int nextPosition = currentStone + jump;
            int nextIndex = findStoneIndex(stones, nextPosition);

            if (nextIndex != -1) {
                if (canReachLastStone(stones, nextIndex, jump, memo)) {
                    return true;
                }
            }
        }

        // Mark this combination of stone and jump as visited
        memo.get(currentStone).add(lastJump);
        return false;
    }

    private static int findStoneIndex(int[] stones, int target) {
        // Binary search to find the stone at target position
        int left = 0, right = stones.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (stones[mid] == target) return mid;
            if (stones[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(canCross(stones)); // Output: true
    }
}

//Time Complexity:
//O(N^2) where N is the number of stones, as the algorithm checks for possible jumps for each stone.
//The intuition behind the solution to the **"Frog Jump"** problem is rooted in understanding **reachability** and using **dynamic programming with a hash map** to efficiently track which stones can be reached with specific jump sizes. Here's the breakdown:
//
//---
//
//### Key Insights and Intuition:
//
//1. **The Problem Is About Reachability:**
//   - The frog can only land on stones that it can reach by making a valid jump of size \( k-1 \), \( k \), or \( k+1 \), where \( k \) is the size of the previous jump.
//   - For each stone, you need to determine if it's reachable and, if so, record the possible jump sizes that can get the frog there.
//
//2. **Why Use a HashMap?**
//   - Stones are not necessarily at uniform intervals, so a simple array-based approach wouldn't work efficiently.
//   - The hash map `dp` maps each stone (its position) to a set of possible jump sizes that can land the frog there. This is crucial because different stones might be reachable via different jump sizes.
//
//3. **Dynamic Programming (DP) Perspective:**
//   - The solution is a variation of DP where:
//     - Each stone represents a "state."
//     - The possible jump sizes that can land on the stone are the "substates."
//     - You build the solution iteratively by checking how a stone can be reached based on previous stones.
//
//4. **Incremental Approach:**
//   - Start with the first stone, which is always reachable with a jump size of 0.
//   - For each reachable stone, try all possible jumps (\( k-1 \), \( k \), \( k+1 \)) to see if they can land on subsequent stones.
//   - Update the hash map for reachable stones with the new jump sizes.
//
//5. **Avoid Redundant Computation:**
//   - By storing only the jump sizes that lead to each stone in the hash map, you avoid repeatedly recalculating which jumps work for a stone.
//   - This drastically reduces the number of calculations compared to brute force.
//
//6. **Goal Check:**
//   - At the last stone, if there are any valid jump sizes recorded in the hash map, the frog can cross the river.
//
//---
//
//### Why This Intuition Works:
//The problem is similar to finding a path in a directed graph, where:
//- Each stone is a node.
//- A valid jump represents an edge to another node.
//Instead of building a graph explicitly, the hash map dynamically records edges (reachable states) as the algorithm progresses, making it space and time efficient.
//
//---
//
//### Visual Intuition:
//1. Imagine the stones as points on a number line.
//2. The frog starts at stone `0` with a "momentum" of `0` (no jump).
//3. Each jump builds potential momentum (\( k-1, k, k+1 \)) that can reach new stones.
//4. Use the hash map to track and carry forward these momenta, eventually checking if the last stone can be reached.
//
//This systematic approach leverages the constraints (stones are sorted and reachable only with specific jumps) to efficiently determine if crossing is possible.

//Yes, an alternative solution to the **"Frog Jump"** problem involves **backtracking with memoization**. This approach avoids using the hash map but relies on recursion to explore possible paths and memoization to cache results for overlapping subproblems.
//
//---
//
//### Backtracking with Memoization
//
//Here is the Java implementation:
//
//```java
//import java.util.*;
//
//public class FrogJump {
//    public boolean canCross(int[] stones) {
//        // Special case: If the second stone is not at position 1, the frog can't jump
//        if (stones[1] != 1) return false;
//
//        // Use a set for quick lookup of stone positions
//        Set<Integer> stoneSet = new HashSet<>();
//        for (int stone : stones) {
//            stoneSet.add(stone);
//        }
//
//        // Memoization map: Key is "stone_position,jump_size", value is boolean (can reach or not)
//        Map<String, Boolean> memo = new HashMap<>();
//        return canCrossHelper(stoneSet, stones[stones.length - 1], 0, 0, memo);
//    }
//
//    private boolean canCrossHelper(Set<Integer> stoneSet, int lastStone, int currentStone, int lastJump, Map<String, Boolean> memo) {
//        // If we reach the last stone, the frog can cross
//        if (currentStone == lastStone) {
//            return true;
//        }
//
//        // Create a unique key for memoization
//        String key = currentStone + "," + lastJump;
//        if (memo.containsKey(key)) {
//            return memo.get(key);
//        }
//
//        // Try all possible jumps: lastJump-1, lastJump, lastJump+1
//        for (int jump = lastJump - 1; jump <= lastJump + 1; jump++) {
//            if (jump > 0 && stoneSet.contains(currentStone + jump)) {
//                if (canCrossHelper(stoneSet, lastStone, currentStone + jump, jump, memo)) {
//                    memo.put(key, true);
//                    return true;
//                }
//            }
//        }
//
//        // If none of the jumps work, record false in memo
//        memo.put(key, false);
//        return false;
//    }
//
//    public static void main(String[] args) {
//        FrogJump solution = new FrogJump();
//        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
//        System.out.println(solution.canCross(stones)); // Output: true
//    }
//}
//```
//
//---
//
//### Intuition:
//
//1. **Backtracking to Explore All Paths:**
//   - Start from the first stone (index 0) with an initial jump size of 0.
//   - For each position, recursively explore all possible jump sizes (\( k-1 \), \( k \), \( k+1 \)).
//   - If a valid jump reaches the last stone, return `true`.
//
//2. **Memoization to Avoid Redundancy:**
//   - Many subproblems (same position and jump size) may be visited multiple times during backtracking.
//   - Use a map to store results for each combination of position and jump size to prevent redundant computation.
//
//3. **Efficient Lookup of Stones:**
//   - Use a `HashSet` to store the stones for \( O(1) \) lookup when checking if a stone exists at a particular position.
//
//---
//
//### Complexity Analysis:
//
//#### Time Complexity:
//- **O(n^2):**
//  In the worst case, each stone can have up to \( n \) possible jump sizes, and memoization ensures we process each combination only once.
//
//#### Space Complexity:
//- **O(n^2):**
//  The memoization map can store up to \( n^2 \) states in the worst case.
//
//---
//
//### Comparison with the DP HashMap Solution:
//| Feature              | DP HashMap Solution      | Backtracking with Memoization |
//|----------------------|--------------------------|--------------------------------|
//| **Approach**         | Iterative with DP        | Recursive with backtracking   |
//| **Ease of Implementation** | Slightly more complex  | Simpler and intuitive         |
//| **Space Usage**      | O(n^2)                  | O(n^2)                        |
//| **Performance**      | Slightly faster         | Slightly slower for large inputs due to recursion overhead |
//
//---
//
//### Why Use This Solution?
//- This solution is easier to understand conceptually because it directly simulates the frog's jumps step-by-step.
//- It’s a good choice for small to medium-sized inputs or when you're more comfortable with recursive algorithms.

Yes, you can solve the **"Frog Jump"** problem using **DFS** or **BFS**. These approaches are intuitive as they explore possible paths like a graph traversal, where stones are nodes and valid jumps are edges.

---

        ### Solution Using Depth-First Search (DFS)
DFS explores all possible paths from the start to the last stone. To avoid redundant computations, we use memoization.

        #### DFS Implementation:

        ```java
import java.util.*;

public class FrogJump {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) return false; // Frog can't make the first jump

        // Use a set for quick lookup of stone positions
        Set<Integer> stoneSet = new HashSet<>();
        for (int stone : stones) {
            stoneSet.add(stone);
        }

        // Memoization: Map to store results for (current position, last jump)
        Map<String, Boolean> memo = new HashMap<>();
        return dfs(stoneSet, stones[stones.length - 1], 0, 0, memo);
    }

    private boolean dfs(Set<Integer> stoneSet, int lastStone, int currentStone, int lastJump, Map<String, Boolean> memo) {
        // If the frog reaches the last stone, return true
        if (currentStone == lastStone) {
            return true;
        }

        // Use a unique key for memoization
        String key = currentStone + "," + lastJump;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Try jumps of size lastJump-1, lastJump, and lastJump+1
        for (int jump = lastJump - 1; jump <= lastJump + 1; jump++) {
            if (jump > 0 && stoneSet.contains(currentStone + jump)) {
                if (dfs(stoneSet, lastStone, currentStone + jump, jump, memo)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }

        // If none of the jumps work, memoize and return false
        memo.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        FrogJump solution = new FrogJump();
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(solution.canCross(stones)); // Output: true
    }
}
```

        ---

        ### Solution Using Breadth-First Search (BFS)
BFS explores paths level by level, starting from the first stone. This approach avoids recursion and is easier to debug.

#### BFS Implementation:

        ```java
import java.util.*;

public class FrogJump {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) return false; // Frog can't make the first jump

        // Use a set for quick lookup of stone positions
        Set<Integer> stoneSet = new HashSet<>();
        for (int stone : stones) {
            stoneSet.add(stone);
        }

        // BFS Queue: Each element is a pair (current position, last jump size)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // Start from stone 0 with jump size 0

        // Visited Set to avoid redundant processing
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int jump = current[1];

            // Try jumps of size jump-1, jump, and jump+1
            for (int step = jump - 1; step <= jump + 1; step++) {
                if (step > 0) {
                    int nextPosition = position + step;

                    // If the frog reaches the last stone, return true
                    if (nextPosition == stones[stones.length - 1]) {
                        return true;
                    }

                    // If the next position is a stone and not visited, add to the queue
                    if (stoneSet.contains(nextPosition)) {
                        String key = nextPosition + "," + step;
                        if (!visited.contains(key)) {
                            visited.add(key);
                            queue.offer(new int[]{nextPosition, step});
                        }
                    }
                }
            }
        }

        // If we exhaust the queue without reaching the last stone, return false
        return false;
    }

    public static void main(String[] args) {
        FrogJump solution = new FrogJump();
        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println(solution.canCross(stones)); // Output: true
    }
}
```

        ---

        ### Complexity Analysis:

        #### DFS:
        - **Time Complexity:** \( O(n^2) \)
Each stone can be reached with up to \( n \) jump sizes, and memoization ensures we only process each state once.
        - **Space Complexity:** \( O(n^2) \)
Due to the memoization map and the recursion stack.

#### BFS:
        - **Time Complexity:** \( O(n^2) \)
In the worst case, we process \( n \) stones, each with up to \( n \) possible jump sizes.
- **Space Complexity:** \( O(n^2) \)
Due to the queue and visited set.

---

        ### Intuition Behind BFS:
        - Imagine the stones as nodes in a graph and the jumps as edges. BFS explores all possible paths (jumps) level by level, ensuring we find the shortest path to the last stone if it exists.
        - Using a `visited` set avoids revisiting the same state, ensuring efficiency.

---

        ### Comparison of DFS and BFS:

        | Feature              | DFS                         | BFS                         |
        |----------------------|-----------------------------|-----------------------------|
        | **Approach**         | Recursive (with memoization) | Iterative (with queue)      |
        | **Ease of Debugging** | Moderate                   | Easier                      |
        | **Performance**      | Similar                     | Similar                     |
        | **Preferred For**    | Problems with deep recursion | Problems with clear levels |

Both solutions are efficient and work well for this problem.