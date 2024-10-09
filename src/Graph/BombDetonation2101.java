package Graph;

import java.util.ArrayList;
import java.util.*;

public class BombDetonation2101 {
    // DFS function to count the number of bombs that can be detonated
    private static int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        int count = 1; // Count the current bomb

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                count += dfs(neighbor, graph, visited); // Explore neighbors recursively
            }
        }

        return count;
    }

    public static int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> graph = new ArrayList<>();

        // Build the adjacency list graph
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    long x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
                    long x2 = bombs[j][0], y2 = bombs[j][1];
                    long distanceSquared = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                    if (distanceSquared <= r1 * r1) { // Bomb j is within the blast radius of bomb i
                        graph.get(i).add(j);
                    }
                }
            }
        }

        // Find the maximum number of bombs that can be detonated
        int maxDetonated = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            maxDetonated = Math.max(maxDetonated, dfs(i, graph, visited));
        }

        return maxDetonated;
    }

    public static int maximumDetonationBFS(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> graph = new ArrayList<>();

        // Build the adjacency list graph
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    long x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
                    long x2 = bombs[j][0], y2 = bombs[j][1];
                    long distanceSquared = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                    if (distanceSquared <= r1 * r1) { // Bomb j is within the blast radius of bomb i
                        graph.get(i).add(j);
                    }
                }
            }
        }

        // BFS function to count the number of bombs that can be detonated
        int maxDetonated = 0;

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visited[i] = true;
            int count = 1;

            while (!queue.isEmpty()) {
                int current = queue.poll();
                for (int neighbor : graph.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                        count++;
                    }
                }
            }
            maxDetonated = Math.max(maxDetonated, count);
        }

        return maxDetonated;
    }

    public static void main(String[] args) {
        int[][] bombs = {{2, 1, 3}, {6, 1, 4}};
        System.out.println(maximumDetonation(bombs)); // Output: 2
    }
}

/**
 * LeetCode 2101, **"Detonate the Maximum Bombs"**, is a problem where you're given a list of bombs, each having a position and a blast radius, and you need to determine the maximum number of bombs that can be detonated if you start by detonating one bomb, which may cause a chain reaction.
 * <p>
 * ### Problem statement:
 * You are given a list of bombs, where `bombs[i] = [xi, yi, ri]` represents the coordinates `(xi, yi)` of a bomb and its blast radius `ri`. You may choose to detonate any bomb. When a bomb explodes, it detonates all bombs that lie within its blast radius. These bombs will further detonate other bombs. Determine the maximum number of bombs that can be detonated if you start by detonating one bomb.
 * <p>
 * ### Example:
 * **Input:** `bombs = [[2,1,3],[6,1,4]]`
 * **Output:** `2`
 * **Explanation:** The first bomb detonates the second, which results in a total of 2 bombs.
 * <p>
 * ### Solution Approach:
 * <p>
 * This problem can be modeled as a **graph traversal** problem where:
 * - Each bomb is a node.
 * - An edge exists from bomb `i` to bomb `j` if bomb `j` is within the blast radius of bomb `i`.
 * <p>
 * We can use **Depth-First Search (DFS)** or **Breadth-First Search (BFS)** to explore the chain reactions starting from each bomb and compute how many bombs can be detonated.
 * <p>
 * #### Steps:
 * 1. For each bomb, check which other bombs are within its blast radius and create a directed graph (adjacency list).
 * 2. For each bomb, perform a DFS or BFS to count the total number of detonated bombs starting from that bomb.
 * 3. Track the maximum number of bombs detonated.
 * <p>
 * ### Java Solution Using DFS:
 * <p>
 * ```java
 * import java.util.ArrayList;
 * import java.util.List;
 * <p>
 * public class Solution {
 * // DFS function to count the number of bombs that can be detonated
 * private int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
 * visited[node] = true;
 * int count = 1; // Count the current bomb
 * <p>
 * for (int neighbor : graph.get(node)) {
 * if (!visited[neighbor]) {
 * count += dfs(neighbor, graph, visited); // Explore neighbors recursively
 * }
 * }
 * <p>
 * return count;
 * }
 * <p>
 * public int maximumDetonation(int[][] bombs) {
 * int n = bombs.length;
 * List<List<Integer>> graph = new ArrayList<>();
 * <p>
 * // Build the adjacency list graph
 * for (int i = 0; i < n; i++) {
 * graph.add(new ArrayList<>());
 * }
 * <p>
 * for (int i = 0; i < n; i++) {
 * for (int j = 0; j < n; j++) {
 * if (i != j) {
 * long x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
 * long x2 = bombs[j][0], y2 = bombs[j][1];
 * long distanceSquared = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
 * if (distanceSquared <= r1 * r1) { // Bomb j is within the blast radius of bomb i
 * graph.get(i).add(j);
 * }
 * }
 * }
 * }
 * <p>
 * // Find the maximum number of bombs that can be detonated
 * int maxDetonated = 0;
 * for (int i = 0; i < n; i++) {
 * boolean[] visited = new boolean[n];
 * maxDetonated = Math.max(maxDetonated, dfs(i, graph, visited));
 * }
 * <p>
 * return maxDetonated;
 * }
 * <p>
 * public static void main(String[] args) {
 * Solution sol = new Solution();
 * int[][] bombs = {{2, 1, 3}, {6, 1, 4}};
 * System.out.println(sol.maximumDetonation(bombs)); // Output: 2
 * }
 * }
 * ```
 * <p>
 * ### Explanation:
 * 1. **Graph Construction**:
 * - We check the distance between every pair of bombs `i` and `j`. If the distance is less than or equal to the blast radius of bomb `i`, then we add a directed edge from bomb `i` to bomb `j`.
 * - Distance between two bombs `i` and `j` is computed using the formula:
 * \[
 * \text{distanceSquared} = (x_2 - x_1)^2 + (y_2 - y_1)^2
 * \]
 * We compare it with the squared blast radius:
 * \[
 * \text{radiusSquared} = r_1^2
 * \]
 * If `distanceSquared <= radiusSquared`, bomb `j` is within the blast range of bomb `i`.
 * <p>
 * 2. **DFS Traversal**:
 * - For each bomb, we perform a DFS starting from that bomb to count how many bombs can be detonated, considering a chain reaction.
 * - We maintain a `visited` array to avoid counting the same bomb multiple times.
 * <p>
 * 3. **Maximizing Detonation**:
 * - For each bomb, we track the maximum number of bombs that can be detonated through DFS and return the largest count.
 * <p>
 * ### Time Complexity:
 * - **Graph construction**: `O(n^2)`, where `n` is the number of bombs. We check each pair of bombs to determine if there's a directed edge between them.
 * - **DFS traversal**: `O(n)` for each bomb.
 * - Overall complexity: `O(n^2)`.
 * <p>
 * ### Space Complexity:
 * - **Graph storage**: `O(n^2)` in the worst case, where each bomb might detonate every other bomb.
 * - **DFS recursion stack and visited array**: `O(n)`.
 * <p>
 * ### Alternative Approaches:
 * 1. **BFS Solution**:
 * - Instead of DFS, you can implement **Breadth-First Search (BFS)**, which will also give the correct result.
 * - In BFS, you explore all neighbors of a bomb before moving to the next level of neighbors.
 * <p>
 * 2. **Union-Find** (Disjoint Set Union):
 * - Another approach could be using the **Union-Find** (or DSU) to group bombs into connected components based on their detonation range. Once the graph is built, Union-Find can be used to quickly find the largest group of detonatable bombs.
 * <p>
 * ### Conclusion:
 * - The DFS solution efficiently models the problem as a graph and solves it by exploring all possible chain reactions starting from each bomb.
 *
 *
 * BFS
 * Explanation:
 * Graph Construction:
 *
 * We build an adjacency list where each bomb i has a list of bombs j that can be detonated if bomb i is detonated (i.e., bomb j is within the blast radius of bomb i).
 * For each bomb i, check all other bombs j, calculate the Euclidean distance squared, and compare it to the squared blast radius to determine if bomb j is within the blast range of bomb i.
 * BFS Traversal:
 *
 * For each bomb, we perform a BFS starting from that bomb.
 * The queue keeps track of bombs to be processed, and we explore all bombs reachable from the current bomb using BFS.
 * We use a visited array to ensure we don’t process the same bomb multiple times during a BFS traversal.
 * Maximizing Detonation:
 *
 * For each bomb, count how many bombs can be detonated via BFS and keep track of the maximum number of detonated bombs.
 * Dry Run of BFS Solution:
 * Example Input: bombs = [[2, 1, 3], [6, 1, 4]]
 * Step 1: Graph Construction
 * For bomb 0 ([2, 1, 3]), calculate the distance to bomb 1 ([6, 1, 4]):
 * \text{distanceSquared} = (6 - 2)^2 + (1 - 1)^2 = 16 ]
 * Since the radius squared of bomb 0 is r1^2 = 3^2 = 9, bomb 1 is not within the blast range of bomb 0.
 * For bomb 1 ([6, 1, 4]), calculate the distance to bomb 0 ([2, 1, 3]):
 * \text{distanceSquared} = (2 - 6)^2 + (1 - 1)^2 = 16 ]
 * Since the radius squared of bomb 1 is r2^2 = 4^2 = 16, bomb 0 is within the blast range of bomb 1.
 * Thus, the adjacency list (graph) becomes:
 *
 * Bomb 0: []
 * Bomb 1: [0]
 * Step 2: BFS Traversal
 * Starting BFS from bomb 0:
 *
 * visited = [true, false]
 * Queue: [0]
 * Detonated bombs: 1 (only bomb 0 can be detonated).
 * Starting BFS from bomb 1:
 *
 * visited = [false, true]
 * Queue: [1]
 * Process bomb 1, then bomb 0 can be detonated.
 * visited = [true, true]
 * Detonated bombs: 2 (bomb 1 detonates bomb 0).
 * Step 3: Maximum Detonation
 * The maximum number of bombs that can be detonated is 2.
 * Time Complexity:
 * Graph Construction:
 *
 * Constructing the adjacency list requires comparing every pair of bombs, which takes O(n^2) where n is the number of bombs.
 * For each bomb i, we calculate the distance to bomb j in O(1) time, and there are n(n - 1) comparisons.
 * BFS Traversal:
 *
 * For each bomb, we perform a BFS. In the worst case, BFS explores all n bombs and checks each edge once, so the BFS traversal for all bombs is O(n^2).
 * Overall Complexity:
 *
 * Time Complexity: O(n^2) for both graph construction and BFS traversal.
 * Space Complexity:
 * Graph storage: O(n^2) for the adjacency list in the worst case.
 * BFS visited array and queue: O(n).
 * Thus, the overall space complexity is O(n^2).
 *
 *
 * Let's implement a solution for LeetCode 2101 (**"Detonate the Maximum Bombs"**) using **Union-Find** (also known as **Disjoint Set Union** or DSU). The idea is to group bombs that can detonate each other into **connected components**. After building these components, the goal is to find the component with the most bombs that can be detonated.
 *
 * ### Union-Find Approach:
 *
 * In this approach, we treat each bomb as a node and use Union-Find to group bombs that are within each other’s blast radius. Once we have built these groups, we can determine the maximum number of bombs that can be detonated by finding the largest connected component.
 *
 * ### Steps:
 *
 * 1. **Graph Construction**: For each pair of bombs, check if they can detonate each other. If they can, union them in the same group using the Union-Find data structure.
 * 2. **Find Maximum Detonation**: Once the groups are formed, we count the number of bombs in each group and find the group with the largest size.
 *
 * ### Key Concepts of Union-Find:
 *
 * - **Find**: Returns the root of the set containing a particular element.
 * - **Union**: Merges two sets containing two different elements.
 * - **Path Compression**: Optimizes the find operation by pointing each node directly to the root.
 * - **Union by Rank**: Ensures the smaller tree is attached under the root of the larger tree, minimizing the depth of the trees.
 *
 * ### Java Solution Using Union-Find:
 *
 * ```java
 * public class SolutionUF {
 *
 *     // Union-Find class to manage connected components
 *     static class UnionFind {
 *         int[] parent;
 *         int[] rank;
 *
 *         public UnionFind(int n) {
 *             parent = new int[n];
 *             rank = new int[n];
 *             for (int i = 0; i < n; i++) {
 *                 parent[i] = i;
 *                 rank[i] = 1;
 *             }
 *         }
 *
 *         public int find(int x) {
 *             if (x != parent[x]) {
 *                 parent[x] = find(parent[x]); // Path compression
 *             }
 *             return parent[x];
 *         }
 *
 *         public void union(int x, int y) {
 *             int rootX = find(x);
 *             int rootY = find(y);
 *             if (rootX != rootY) {
 *                 // Union by rank
 *                 if (rank[rootX] > rank[rootY]) {
 *                     parent[rootY] = rootX;
 *                 } else if (rank[rootX] < rank[rootY]) {
 *                     parent[rootX] = rootY;
 *                 } else {
 *                     parent[rootY] = rootX;
 *                     rank[rootX]++;
 *                 }
 *             }
 *         }
 *     }
 *
 *     public int maximumDetonation(int[][] bombs) {
 *         int n = bombs.length;
 *         UnionFind uf = new UnionFind(n);
 *
 *         // Build the union-find structure by checking the distance between bombs
 *         for (int i = 0; i < n; i++) {
 *             for (int j = 0; j < n; j++) {
 *                 if (i != j) {
 *                     long x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
 *                     long x2 = bombs[j][0], y2 = bombs[j][1];
 *                     long distanceSquared = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
 *                     if (distanceSquared <= r1 * r1) {
 *                         uf.union(i, j); // Union bomb i and bomb j if they can detonate each other
 *                     }
 *                 }
 *             }
 *         }
 *
 *         // Count the number of bombs in each group
 *         int[] groupSize = new int[n];
 *         for (int i = 0; i < n; i++) {
 *             int root = uf.find(i);
 *             groupSize[root]++;
 *         }
 *
 *         // Find the largest group
 *         int maxDetonated = 0;
 *         for (int size : groupSize) {
 *             maxDetonated = Math.max(maxDetonated, size);
 *         }
 *
 *         return maxDetonated;
 *     }
 *
 *     public static void main(String[] args) {
 *         SolutionUF sol = new SolutionUF();
 *         int[][] bombs = {{2, 1, 3}, {6, 1, 4}};
 *         System.out.println(sol.maximumDetonation(bombs)); // Output: 2
 *     }
 * }
 * ```
 *
 * ### Explanation:
 *
 * 1. **Union-Find Data Structure**:
 *    - We use the `UnionFind` class to manage connected components. Each bomb starts as its own parent.
 *    - The `find()` function returns the root of the component containing bomb `i`, and `union()` merges two components.
 *    - **Path compression** in `find()` ensures that we flatten the tree, making future `find()` operations faster.
 *    - **Union by rank** ensures we attach the smaller tree under the larger one, preventing the tree from growing too deep.
 *
 * 2. **Graph Construction**:
 *    - For each bomb `i`, we compare it with every other bomb `j`. If bomb `j` is within the blast radius of bomb `i`, we perform a union operation to group them together.
 *    - We calculate the Euclidean distance squared between bombs `i` and `j` and compare it with the square of bomb `i`'s blast radius.
 *
 * 3. **Counting Group Sizes**:
 *    - After building the union-find structure, we count the size of each group. For each bomb `i`, we find its root (the component leader) and increase the size of that group.
 *
 * 4. **Finding Maximum Detonation**:
 *    - Finally, we iterate over the group sizes and find the largest component, which gives us the maximum number of bombs that can be detonated.
 *
 * ### Dry Run:
 *
 * #### Input: `bombs = [[2, 1, 3], [6, 1, 4]]`
 *
 * #### Step 1: Graph Construction
 * - Bomb 0 (`[2, 1, 3]`) and Bomb 1 (`[6, 1, 4]`) are compared:
 *   - Distance squared = `16`.
 *   - Radius squared for Bomb 0 is `9`, so Bomb 1 is not in the blast range of Bomb 0.
 *   - Radius squared for Bomb 1 is `16`, so Bomb 0 **is** within the blast range of Bomb 1.
 *   - Union bomb 0 and bomb 1 (they are grouped together).
 *
 * After this step, the bombs are grouped as:
 * - Bomb 0 and Bomb 1 are in the same component.
 *
 * #### Step 2: Counting Group Sizes
 * - Bomb 0's root is 1 (since we unioned them).
 * - Bomb 1's root is also 1.
 * - The group size for root 1 is 2.
 *
 * #### Step 3: Maximum Detonation
 * - The maximum size of any group is `2`.
 *
 * #### Output: `2`
 *
 * ### Time Complexity:
 * 1. **Graph Construction**:
 *    - We compare each pair of bombs, so this takes `O(n^2)` where `n` is the number of bombs.
 *
 * 2. **Union-Find Operations**:
 *    - Each `find()` and `union()` operation takes almost constant time, `O(α(n))`, where `α(n)` is the inverse Ackermann function (which grows very slowly and is almost constant in practice).
 *
 * 3. **Counting Group Sizes**:
 *    - This takes `O(n)` since we check the root of each bomb and count the size of the groups.
 *
 * So the overall **time complexity** is `O(n^2)` due to the pairwise comparisons of bombs.
 *
 * ### Space Complexity:
 * - **Union-Find** data structure requires `O(n)` space to store the parent and rank arrays.
 * - **Group sizes** array also requires `O(n)` space.
 * - Overall, the **space complexity** is `O(n)`.
 *
 * ### Conclusion:
 * The Union-Find approach provides a way to efficiently group bombs into connected components and determine the largest component. This solution also has a time complexity of `O(n^2)`, similar to the DFS/BFS approaches.
 **/