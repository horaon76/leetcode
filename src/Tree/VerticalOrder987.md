**LeetCode 987: Vertical Order Traversal of a Binary Tree**

---

### Problem Statement:
Given the `root` of a binary tree, calculate the **vertical order traversal** of the binary tree.
- For each node at position `(row, col)`, its position is defined as:
    - The root node is at `(0, 0)`.
    - The left child of a node at position `(r, c)` is at `(r + 1, c - 1)`.
    - The right child of a node at position `(r, c)` is at `(r + 1, c + 1)`.
- The nodes are ordered as follows:
    1. By column (`col`) in ascending order.
    2. If two nodes are in the same column, by row (`row`) in ascending order.
    3. If two nodes share the same position `(row, col)`, sort by value.

---

### Intuition:
1. Perform a traversal of the tree and record each node's value along with its `(row, col)` position.
2. Group nodes by their `col` value.
3. Sort the nodes in each column based on `row`, and for ties, sort by node value.
4. Return the nodes grouped by column in ascending order.

---

### Approach: BFS with Coordinate Tracking

#### Algorithm:
1. Use a queue to perform a **level-order traversal** (BFS). Each queue entry contains:
    - The current node.
    - Its `(row, col)` position.
2. Use a `TreeMap` (or `SortedMap`) to store nodes grouped by column (`col`):
    - Key: `col` value.
    - Value: A list of `(row, value)` pairs.
3. While traversing:
    - Add the node's value and row to its corresponding column list.
    - Add the left and right children to the queue with updated `(row, col)` values.
4. After traversal, sort the lists in the map based on `row` (and value for ties).
5. Extract and return the sorted values for each column.

---

### Code:
```java
import java.util.*;

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TreeMap to store nodes by column (sorted by column automatically)
        TreeMap<Integer, List<int[]>> columnMap = new TreeMap<>();

        // Queue for BFS: stores node and its position (row, col)
        Queue<Pair<TreeNode, int[]>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, new int[] { 0, 0 })); // Initial position (row=0, col=0)

        while (!queue.isEmpty()) {
            Pair<TreeNode, int[]> pair = queue.poll();
            TreeNode node = pair.getKey();
            int row = pair.getValue()[0];
            int col = pair.getValue()[1];

            // Add the node's value to the column map
            columnMap.putIfAbsent(col, new ArrayList<>());
            columnMap.get(col).add(new int[] { row, node.val });

            // Add left and right children to the queue
            if (node.left != null) {
                queue.add(new Pair<>(node.left, new int[] { row + 1, col - 1 }));
            }
            if (node.right != null) {
                queue.add(new Pair<>(node.right, new int[] { row + 1, col + 1 }));
            }
        }

        // Prepare the result list
        List<List<Integer>> result = new ArrayList<>();
        for (List<int[]> nodes : columnMap.values()) {
            // Sort by row, then by value
            nodes.sort((a, b) -> {
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            });

            // Extract the sorted node values
            List<Integer> sortedColumn = new ArrayList<>();
            for (int[] node : nodes) {
                sortedColumn.add(node[1]);
            }
            result.add(sortedColumn);
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = sol.verticalTraversal(root);
        System.out.println("Vertical Order Traversal: " + result);
    }
}
```

---

### Example Walkthrough:

#### Input Tree:
```
        1
       / \
      2   3
     / \ / \
    4  6 5  7
```

#### Step-by-Step BFS Traversal:
1. Start with the root `(1, row=0, col=0)`.
    - Add `(0, 1)` to `columnMap[0]`.
    - Add left child `(2, row=1, col=-1)` and right child `(3, row=1, col=1)` to the queue.
2. Process node `2` `(row=1, col=-1)`:
    - Add `(1, 2)` to `columnMap[-1]`.
    - Add left child `(4, row=2, col=-2)` and right child `(6, row=2, col=0)` to the queue.
3. Process node `3` `(row=1, col=1)`:
    - Add `(1, 3)` to `columnMap[1]`.
    - Add left child `(5, row=2, col=0)` and right child `(7, row=2, col=2)` to the queue.
4. Continue processing nodes `4`, `6`, `5`, and `7`.

#### Sorted Column Map:
- `columnMap[-2]`: `[(2, 4)]`
- `columnMap[-1]`: `[(1, 2)]`
- `columnMap[0]`: `[(0, 1), (2, 6), (2, 5)]` → Sorted: `[(0, 1), (2, 5), (2, 6)]`
- `columnMap[1]`: `[(1, 3)]`
- `columnMap[2]`: `[(2, 7)]`

#### Output:
```
[
    [4],
    [2],
    [1, 5, 6],
    [3],
    [7]
]
```

---

### Complexity Analysis:

#### Time Complexity:
1. **Tree Traversal**: \(O(n)\), where \(n\) is the number of nodes in the tree.
2. **Sorting**: Sorting nodes within each column takes \(O(k \log k)\), where \(k\) is the number of nodes in that column. Across all columns, the sorting cost is \(O(n \log n)\).

Overall: \(O(n \log n)\).

#### Space Complexity:
1. **TreeMap Storage**: \(O(n)\), for storing nodes grouped by column.
2. **Queue Storage**: \(O(w)\), where \(w\) is the maximum width of the tree.

Overall: \(O(n)\).

---

Let me know if you'd like additional examples or alternative approaches!

Yes! Another solution to LeetCode 987 can be implemented using **DFS (Depth-First Search)** to record node positions `(row, col)` and then sorting them to construct the vertical order traversal. Here’s how it works:

---

### Approach: DFS with Coordinate Tracking

#### Intuition:
1. Perform a **DFS traversal** to record the position `(row, col)` for each node.
2. Use a `TreeMap` to group nodes by their column index `col`.
3. For each column, sort the nodes by `row`. If multiple nodes are in the same position `(row, col)`, sort them by their values.
4. Extract the sorted results for each column to generate the final output.

---

### Algorithm:
1. Use a `TreeMap` to store:
    - Key: Column index (`col`).
    - Value: A list of `(row, value)` pairs.
2. Perform a DFS traversal:
    - Track the `row` and `col` of the current node.
    - Add the node’s `(row, value)` to the appropriate column in the map.
3. After traversal, for each column in the map:
    - Sort the list of nodes by `row`, and for ties, sort by value.
4. Collect and return the sorted results for each column.

---

### Code:
```java
import java.util.*;

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TreeMap to store nodes by column (sorted by column automatically)
        TreeMap<Integer, List<int[]>> columnMap = new TreeMap<>();

        // Perform DFS to populate the columnMap
        dfs(root, 0, 0, columnMap);

        // Prepare the result list
        List<List<Integer>> result = new ArrayList<>();
        for (List<int[]> nodes : columnMap.values()) {
            // Sort by row, then by value
            nodes.sort((a, b) -> {
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            });

            // Extract the sorted node values
            List<Integer> sortedColumn = new ArrayList<>();
            for (int[] node : nodes) {
                sortedColumn.add(node[1]);
            }
            result.add(sortedColumn);
        }

        return result;
    }

    private void dfs(TreeNode node, int row, int col, TreeMap<Integer, List<int[]>> columnMap) {
        if (node == null) {
            return;
        }

        // Add the node's position and value to the column map
        columnMap.putIfAbsent(col, new ArrayList<>());
        columnMap.get(col).add(new int[] { row, node.val });

        // Traverse the left and right subtrees
        dfs(node.left, row + 1, col - 1, columnMap);
        dfs(node.right, row + 1, col + 1, columnMap);
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = sol.verticalTraversal(root);
        System.out.println("Vertical Order Traversal: " + result);
    }
}
```

---

### Example Walkthrough:

#### Input Tree:
```
        1
       / \
      2   3
     / \ / \
    4  6 5  7
```

#### Step-by-Step DFS Traversal:
1. Start with the root `(1, row=0, col=0)`.
    - Add `(0, 1)` to `columnMap[0]`.
2. Go to the left child `(2, row=1, col=-1)`:
    - Add `(1, 2)` to `columnMap[-1]`.
3. Go to the left child `(4, row=2, col=-2)`:
    - Add `(2, 4)` to `columnMap[-2]`.
4. Backtrack to `(2)` and visit its right child `(6, row=2, col=0)`:
    - Add `(2, 6)` to `columnMap[0]`.
5. Backtrack to `(1)` and visit its right child `(3, row=1, col=1)`:
    - Add `(1, 3)` to `columnMap[1]`.
6. Go to the left child `(5, row=2, col=0)`:
    - Add `(2, 5)` to `columnMap[0]`.
7. Go to the right child `(7, row=2, col=2)`:
    - Add `(2, 7)` to `columnMap[2]`.

#### Sorted Column Map:
- `columnMap[-2]`: `[(2, 4)]`
- `columnMap[-1]`: `[(1, 2)]`
- `columnMap[0]`: `[(0, 1), (2, 6), (2, 5)]` → Sorted: `[(0, 1), (2, 5), (2, 6)]`
- `columnMap[1]`: `[(1, 3)]`
- `columnMap[2]`: `[(2, 7)]`

#### Output:
```
[
    [4],
    [2],
    [1, 5, 6],
    [3],
    [7]
]
```

---

### Complexity Analysis:

#### Time Complexity:
1. **DFS Traversal**: \(O(n)\), where \(n\) is the number of nodes in the tree.
2. **Sorting**: Sorting nodes within each column takes \(O(k \log k)\), where \(k\) is the number of nodes in that column. Across all columns, the sorting cost is \(O(n \log n)\).

Overall: \(O(n \log n)\).

#### Space Complexity:
1. **TreeMap Storage**: \(O(n)\), for storing nodes grouped by column.
2. **Recursive Call Stack**: \(O(h)\), where \(h\) is the height of the tree.

Overall: \(O(n + h)\).

---

### Key Differences Between DFS and BFS Solutions:
1. **Traversal Type**:
    - BFS uses a queue to traverse the tree level-by-level.
    - DFS uses recursion to traverse the tree depth-first.
2. **Implementation**:
    - BFS requires a queue for traversal.
    - DFS requires recursion or a stack.
3. **Complexity**:
    - Both solutions have similar time and space complexity, but the DFS approach may have additional overhead due to recursive calls.

Let me know if you'd like further clarification or additional examples!