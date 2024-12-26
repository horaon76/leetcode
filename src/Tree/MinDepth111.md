**LeetCode 111: Minimum Depth of Binary Tree**

This problem asks us to find the **minimum depth** of a binary tree, which is defined as the number of nodes along the shortest path from the root node down to the nearest leaf node.

---

### Problem Statement:

Given a binary tree, find its minimum depth.
- The minimum depth is the number of nodes along the shortest path from the root node down to the nearest **leaf node**.
- A **leaf node** is a node with no children.

---

### Intuition:

To determine the minimum depth:
- If a node has both left and right children, we calculate the minimum depth of its subtrees and add `1`.
- If a node has only one child, the depth of that path depends entirely on the non-null subtree.
- A **leaf node** represents the base case with depth `1`.

---

### Approach 1: Recursive DFS

#### Algorithm:
1. If the tree is empty (`root == null`), return `0`.
2. If a node has no children, it is a **leaf node**, return `1`.
3. If a node has only one child, recursively calculate the depth of the non-null subtree and add `1`.
4. If a node has both children, calculate the minimum of the depths of the left and right subtrees and add `1`.

---

#### Code:

```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0; // Base case: empty tree
        }

        // If the node has no children, it's a leaf node
        if (root.left == null && root.right == null) {
            return 1;
        }

        // If one of the subtrees is null, return the depth of the non-null subtree
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        // If both subtrees are non-null, return the minimum depth
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Minimum Depth: " + sol.minDepth(root)); // Output: 2
    }
}
```

---

### Approach 2: Iterative BFS

Using **BFS** (level-order traversal) allows us to find the minimum depth by exploring level by level. The first time we encounter a **leaf node**, we return the depth since it represents the shortest path.

#### Algorithm:
1. Use a queue to store nodes along with their depth.
2. Start with the root node and depth `1`.
3. For each node:
    - If it’s a leaf node, return its depth.
    - Otherwise, add its children to the queue with depth incremented by `1`.

---

#### Code:

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0; // Base case: empty tree
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // If it's a leaf node, return the current depth
                if (current.left == null && current.right == null) {
                    return depth;
                }

                // Add children to the queue
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            depth++; // Increment depth after processing all nodes at the current level
        }

        return depth;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Minimum Depth: " + sol.minDepth(root)); // Output: 2
    }
}
```

---

### Example Walkthrough:

#### Input Tree:
```
        3
       / \
      9   20
         /  \
        15   7
```

#### Recursive Walkthrough:
1. Start at root `3`:
    - Left subtree rooted at `9` → depth = `1` (leaf node).
    - Right subtree rooted at `20`:
        - Left subtree rooted at `15` → depth = `1`.
        - Right subtree rooted at `7` → depth = `1`.
    - Depth of subtree rooted at `20` = `1 + min(1, 1) = 2`.
2. Minimum depth of tree = `1 + min(1, 2) = 2`.

---

#### BFS Walkthrough:
1. **Level 1**: Node `3`:
    - Add `9` and `20` to the queue.
    - Depth = `1`.
2. **Level 2**: Nodes `9` and `20`:
    - Node `9` is a leaf node.
    - Return depth = `2`.

---

### Complexity Analysis:

#### Recursive DFS:
- **Time Complexity**: \(O(n)\), where \(n\) is the number of nodes. Each node is visited once.
- **Space Complexity**: \(O(h)\), where \(h\) is the height of the tree, for the recursion stack.

#### Iterative BFS:
- **Time Complexity**: \(O(n)\), where \(n\) is the number of nodes. Each node is visited once.
- **Space Complexity**: \(O(n)\), for the queue storage in the worst case (complete binary tree).

---

Let me know if you'd like further explanation or additional examples!