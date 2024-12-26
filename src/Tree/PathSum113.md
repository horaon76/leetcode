### LeetCode Problem 113: Path Sum II

#### Problem Summary:
Given the `root` of a binary tree and an integer `targetSum`, return all root-to-leaf paths where the sum of the node values in the path equals `targetSum`.

A **root-to-leaf** path is a path starting from the root and ending at any leaf node.

---

### Approach 1: Depth-First Search (DFS) with Backtracking

We will use **DFS** to explore all the root-to-leaf paths and keep track of the sum of the nodes in the current path. Once we reach a leaf node, we check if the path sum equals `targetSum`.

#### Solution Code:

```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, targetSum, currentPath, result);
        return result;
    }

    private void dfs(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;

        // Add current node to the path
        currentPath.add(node.val);

        // Check if it's a leaf node and the path sum equals targetSum
        if (node.left == null && node.right == null && targetSum == node.val) {
            result.add(new ArrayList<>(currentPath));
        } else {
            // Continue searching in left and right subtrees
            dfs(node.left, targetSum - node.val, currentPath, result);
            dfs(node.right, targetSum - node.val, currentPath, result);
        }

        // Backtrack to explore other paths
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        // Example to test the function
        Solution solution = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        int targetSum = 22;
        List<List<Integer>> result = solution.pathSum(root, targetSum);
        System.out.println(result);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
```

---

### Time and Space Complexity Analysis

#### Time Complexity:
- **DFS Traversal**: We visit each node in the tree once. Therefore, the time complexity for the traversal is \( O(N) \), where \( N \) is the number of nodes in the binary tree.
- **Processing Each Path**: At each leaf node, the path length is proportional to the height of the tree, which can be at most \( O(H) \). In the worst case, there can be \( O(N) \) leaf nodes, but this does not affect the overall time complexity since each node is processed once.

Thus, the overall **Time Complexity** is \( O(N) \), where \( N \) is the number of nodes in the tree.

#### Space Complexity:
- **Recursive Stack**: The maximum depth of the recursion stack will be the height of the tree, so the space complexity for the recursion is \( O(H) \), where \( H \) is the height of the tree.
- **Result Storage**: In the worst case, we may store all the paths in the result. If the tree has \( L \) leaf nodes and the maximum path length is \( H \), the space used by the result will be \( O(L \times H) \).

Thus, the overall **Space Complexity** is \( O(H + L \times H) \), where:
- \( H \) is the height of the tree.
- \( L \) is the number of leaf nodes.

---

### Approach 2: Iterative DFS using Stack

An iterative DFS can be used to avoid recursion, which may be preferable for deep trees to avoid stack overflow.

#### Solution Code for Iterative DFS:

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, targetSum, new ArrayList<>()));

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            TreeNode node = current.node;
            int remainingSum = current.remainingSum;
            List<Integer> path = current.path;

            path.add(node.val);

            // If it's a leaf node and the sum matches, add to result
            if (node.left == null && node.right == null && remainingSum == node.val) {
                result.add(new ArrayList<>(path));
            } else {
                // Push left and right children to the stack
                if (node.right != null) stack.push(new Node(node.right, remainingSum - node.val, new ArrayList<>(path)));
                if (node.left != null) stack.push(new Node(node.left, remainingSum - node.val, new ArrayList<>(path)));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example to test the function
        Solution solution = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        int targetSum = 22;
        List<List<Integer>> result = solution.pathSum(root, targetSum);
        System.out.println(result);
    }

    static class Node {
        TreeNode node;
        int remainingSum;
        List<Integer> path;

        Node(TreeNode node, int remainingSum, List<Integer> path) {
            this.node = node;
            this.remainingSum = remainingSum;
            this.path = path;
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
```

---

### Time and Space Complexity for Iterative Approach

- **Time Complexity**: Same as the recursive DFS, \( O(N) \), because we visit each node once.
- **Space Complexity**: The space complexity is \( O(H + L \times H) \), where \( H \) is the height of the tree, and \( L \) is the number of leaf nodes.

---

### Conclusion

- **DFS (Recursive)**:
    - **Time Complexity**: \( O(N) \)
    - **Space Complexity**: \( O(H + L \times H) \)

- **DFS (Iterative)**:
    - **Time Complexity**: \( O(N) \)
    - **Space Complexity**: \( O(H + L \times H) \)

Both approaches are efficient, and the choice between them depends on whether you prefer recursion or iteration. Let me know if you need further assistance!                   mn

Here’s the **complete example walkthrough** of solving **LeetCode 113: Path Sum II** using the BFS approach for the given tree and target sum.

---

### Input Example:

Tree structure:
```
        5
       / \
      4   8
     /   / \
    11  13  4
   /  \      \
  7    2      1
```

Target sum: `22`.

---

### BFS Initialization:
We use a queue to explore nodes in a **level-order fashion**. Each element in the queue is a `Node` containing:
1. The current `TreeNode`.
2. The remaining sum from the root to this node.
3. The current path from the root to this node.

#### Initial Queue:
```
Queue = [(5, 22, [5])]  // Root node with target sum
```

---

### Step-by-Step Walkthrough:

#### 1. Process Root Node `5`:
- **Dequeue**: `(5, 22, [5])`.
- **Remaining Target**: `22 - 5 = 17`.
- **Path So Far**: `[5]`.

Add its children to the queue:
- Left Child `4`: `(4, 17, [5, 4])`.
- Right Child `8`: `(8, 17, [5, 8])`.

**Updated Queue**:
```
Queue = [(4, 17, [5, 4]), (8, 17, [5, 8])]
```

---

#### 2. Process Node `4`:
- **Dequeue**: `(4, 17, [5, 4])`.
- **Remaining Target**: `17 - 4 = 13`.
- **Path So Far**: `[5, 4]`.

Add its children to the queue:
- Left Child `11`: `(11, 13, [5, 4, 11])`.

**Updated Queue**:
```
Queue = [(8, 17, [5, 8]), (11, 13, [5, 4, 11])]
```

---

#### 3. Process Node `8`:
- **Dequeue**: `(8, 17, [5, 8])`.
- **Remaining Target**: `17 - 8 = 9`.
- **Path So Far**: `[5, 8]`.

Add its children to the queue:
- Left Child `13`: `(13, 9, [5, 8, 13])`.
- Right Child `4`: `(4, 9, [5, 8, 4])`.

**Updated Queue**:
```
Queue = [(11, 13, [5, 4, 11]), (13, 9, [5, 8, 13]), (4, 9, [5, 8, 4])]
```

---

#### 4. Process Node `11`:
- **Dequeue**: `(11, 13, [5, 4, 11])`.
- **Remaining Target**: `13 - 11 = 2`.
- **Path So Far**: `[5, 4, 11]`.

Add its children to the queue:
- Left Child `7`: `(7, 2, [5, 4, 11, 7])`.
- Right Child `2`: `(2, 2, [5, 4, 11, 2])`.

**Updated Queue**:
```
Queue = [(13, 9, [5, 8, 13]), (4, 9, [5, 8, 4]), (7, 2, [5, 4, 11, 7]), (2, 2, [5, 4, 11, 2])]
```

---

#### 5. Process Node `13`:
- **Dequeue**: `(13, 9, [5, 8, 13])`.
- **Remaining Target**: `9 - 13 = -4`.
- **Path So Far**: `[5, 8, 13]`.

This is a leaf node, but the remaining sum is negative (`-4`), so it is invalid. Discard this path.

**Updated Queue**:
```
Queue = [(4, 9, [5, 8, 4]), (7, 2, [5, 4, 11, 7]), (2, 2, [5, 4, 11, 2])]
```

---

#### 6. Process Node `4` (Right Child of Node `8`):
- **Dequeue**: `(4, 9, [5, 8, 4])`.
- **Remaining Target**: `9 - 4 = 5`.
- **Path So Far**: `[5, 8, 4]`.

Add its child to the queue:
- Right Child `1`: `(1, 5, [5, 8, 4, 1])`.

**Updated Queue**:
```
Queue = [(7, 2, [5, 4, 11, 7]), (2, 2, [5, 4, 11, 2]), (1, 5, [5, 8, 4, 1])]
```

---

#### 7. Process Node `7`:
- **Dequeue**: `(7, 2, [5, 4, 11, 7])`.
- **Remaining Target**: `2 - 7 = -5`.
- **Path So Far**: `[5, 4, 11, 7]`.

This is a leaf node, but the remaining sum is negative (`-5`), so it is invalid. Discard this path.

**Updated Queue**:
```
Queue = [(2, 2, [5, 4, 11, 2]), (1, 5, [5, 8, 4, 1])]
```

---

#### 8. Process Node `2`:
- **Dequeue**: `(2, 2, [5, 4, 11, 2])`.
- **Remaining Target**: `2 - 2 = 0`.
- **Path So Far**: `[5, 4, 11, 2]`.

This is a leaf node, and the remaining sum is `0`. This path is valid, so add it to the result:
```
Result = [[5, 4, 11, 2]]
```

**Updated Queue**:
```
Queue = [(1, 5, [5, 8, 4, 1])]
```

---

#### 9. Process Node `1`:
- **Dequeue**: `(1, 5, [5, 8, 4, 1])`.
- **Remaining Target**: `5 - 1 = 4`.
- **Path So Far**: `[5, 8, 4, 1]`.

This is a leaf node, but the remaining sum is not `0`. Discard this path.

**Updated Queue**:
```
Queue = []
```

---

### Final Result:
After processing all nodes, the result contains only one valid path:
```
Result = [[5, 4, 11, 2]]
```

---

### BFS Summary:

- BFS processes nodes level by level using a queue.
- For each node, it calculates the remaining sum and updates the path.
- Valid paths (leaf nodes with remaining sum `0`) are added to the result.
- Invalid paths are discarded during traversal.

---

Let me know if you need further clarification or more examples!


package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {
public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
List<List<Integer>> result = new ArrayList<>();
if (root == null) return result;

        // Queue to store the node, current path, and remaining sum
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, targetSum, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            TreeNode node = current.node;
            int remainingSum = current.remainingSum;
            List<Integer> path = current.path;

            // Add current node to the path
            path.add(node.val);

            // If it's a leaf node and the remaining sum is zero, add to the result
            if (node.left == null && node.right == null && remainingSum == node.val) {
                result.add(new ArrayList<>(path));
            } else {
                // Add left and right children to the queue with updated sum and path
                if (node.left != null) {
                    queue.offer(new Node(node.left, remainingSum - node.val, new ArrayList<>(path)));
                }
                if (node.right != null) {
                    queue.offer(new Node(node.right, remainingSum - node.val, new ArrayList<>(path)));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example to test the function
        Test solution = new Test();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        int targetSum = 22;
        List<List<Integer>> result = solution.pathSum(root, targetSum);
        System.out.println(result);
    }

    static class Node {
        TreeNode node;
        int remainingSum;
        List<Integer> path;

        Node(TreeNode node, int remainingSum, List<Integer> path) {
            this.node = node;
            this.remainingSum = remainingSum;
            this.path = path;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

