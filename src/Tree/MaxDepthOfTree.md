**LeetCode 104: Maximum Depth of Binary Tree**

This problem asks us to determine the maximum depth (or height) of a binary tree. The depth of a binary tree is the number of nodes along the longest path from the root node to the furthest leaf node.

---

### Problem Statement:
Given the root of a binary tree, return its maximum depth.

**Constraints:**
- The number of nodes in the tree is in the range `[0, 10^4]`.
- `-100 <= Node.val <= 100`.

---

### Intuition:
The maximum depth of a binary tree can be calculated by:
1. Using **recursion** to explore each subtree.
2. Using **iteration** with a level-order traversal (BFS).

At each node:
- The maximum depth of the tree is `1 + max(left_subtree_depth, right_subtree_depth)`.

---

### Recursive Approach (DFS):
We recursively compute the depth of the left and right subtrees and return the maximum depth.

#### Algorithm:
1. If the tree is empty (`root == null`), return `0`.
2. For each node:
    - Recursively find the maximum depth of the left subtree.
    - Recursively find the maximum depth of the right subtree.
    - The depth of the current node is `1 + max(left_depth, right_depth)`.

#### Code:
```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // Base case: empty tree has depth 0
        }
        int leftDepth = maxDepth(root.left);  // Depth of left subtree
        int rightDepth = maxDepth(root.right); // Depth of right subtree
        return 1 + Math.max(leftDepth, rightDepth); // Current depth
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Maximum Depth: " + sol.maxDepth(root));
    }
}
```

---

### Iterative Approach (BFS):
Using **level-order traversal**, we calculate the depth by counting the number of levels in the tree.

#### Algorithm:
1. Use a queue to store nodes at each level.
2. For each level:
    - Count the nodes in the queue.
    - Process all nodes in the queue and add their children to the queue.
    - Increment the depth counter after processing all nodes at the current level.

#### Code:
```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // Base case: empty tree has depth 0
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll(); // Remove the front node
                if (current.left != null) queue.add(current.left); // Add left child
                if (current.right != null) queue.add(current.right); // Add right child
            }
            depth++; // Increment depth after processing each level
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

        System.out.println("Maximum Depth: " + sol.maxDepth(root));
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
    - Compute depth of left subtree rooted at `9` → depth = 1.
    - Compute depth of right subtree rooted at `20`:
        - Left subtree rooted at `15` → depth = 1.
        - Right subtree rooted at `7` → depth = 1.
    - Depth of subtree rooted at `20` = `1 + max(1, 1) = 2`.
2. Depth of entire tree = `1 + max(1, 2) = 3`.

---

#### Iterative Walkthrough (BFS):
1. **Level 1**: Root `3`.
    - Nodes at this level = 1.
    - Add `9` and `20` to the queue.
    - Depth = 1.
2. **Level 2**: Nodes `9` and `20`.
    - Nodes at this level = 2.
    - Add `15` and `7` to the queue.
    - Depth = 2.
3. **Level 3**: Nodes `15` and `7`.
    - Nodes at this level = 2.
    - No more children to add.
    - Depth = 3.

---

### Complexity Analysis:
#### Recursive:
- **Time Complexity**: \(O(n)\), where \(n\) is the number of nodes. Each node is visited once.
- **Space Complexity**: \(O(h)\), where \(h\) is the height of the tree, for the recursion stack.

#### Iterative:
- **Time Complexity**: \(O(n)\), where \(n\) is the number of nodes. Each node is visited once.
- **Space Complexity**: \(O(n)\), for the queue storage in the worst case (complete binary tree).

---

Let me know if you need further clarification or additional examples!