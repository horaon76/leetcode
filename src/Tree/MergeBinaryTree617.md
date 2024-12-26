**LeetCode 617: Merge Two Binary Trees**

---

### Problem Statement:
You are given two binary trees, `root1` and `root2`. Imagine that when you overlay one tree on top of the other, some nodes overlap, and some do not. The merge rule is:
- If two nodes overlap, their values should be summed up.
- Otherwise, the non-null node will be used as the node of the new tree.

Return the merged tree.

---

### Intuition:
The problem can be solved using **recursion** or **iteration**:
1. If both trees are empty, return `null`.
2. If one of the trees is empty, return the non-empty tree.
3. If both nodes are non-empty, sum their values, and recursively merge their left and right children.

---

### Approach 1: Recursive DFS

#### Algorithm:
1. Start with the roots of both trees.
2. If one of the nodes is `null`, return the other node.
3. If both nodes are non-null:
    - Sum their values to create a new node.
    - Recursively merge their left and right subtrees.

#### Code:
```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2; // If the first tree is empty, return the second tree
        }
        if (root2 == null) {
            return root1; // If the second tree is empty, return the first tree
        }

        // Merge the current nodes
        TreeNode merged = new TreeNode(root1.val + root2.val);

        // Recursively merge the left and right children
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);

        return merged;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        TreeNode mergedTree = sol.mergeTrees(root1, root2);
        System.out.println("Merged Tree Root Value: " + mergedTree.val); // Output: 3
    }
}
```

---

### Approach 2: Iterative BFS (Using a Queue)

#### Algorithm:
1. Use a queue to store pairs of nodes to be merged.
2. For each pair:
    - If both nodes are `null`, skip.
    - If one node is `null`, use the non-null node for the merged tree.
    - Otherwise, sum their values and enqueue their left and right children for further merging.
3. Repeat until the queue is empty.

#### Code:
```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.add(new TreeNode[] { root1, root2 });

        while (!queue.isEmpty()) {
            TreeNode[] pair = queue.poll();
            TreeNode node1 = pair[0];
            TreeNode node2 = pair[1];

            // Merge the values
            node1.val += node2.val;

            // If both left children exist, enqueue them for merging
            if (node1.left != null && node2.left != null) {
                queue.add(new TreeNode[] { node1.left, node2.left });
            } else if (node1.left == null) {
                // If only one left child exists, use it
                node1.left = node2.left;
            }

            // If both right children exist, enqueue them for merging
            if (node1.right != null && node2.right != null) {
                queue.add(new TreeNode[] { node1.right, node2.right });
            } else if (node1.right == null) {
                // If only one right child exists, use it
                node1.right = node2.right;
            }
        }

        return root1;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        TreeNode mergedTree = sol.mergeTrees(root1, root2);
        System.out.println("Merged Tree Root Value: " + mergedTree.val); // Output: 3
    }
}
```

---

### Example Walkthrough:

#### Input Trees:
**Tree 1**:
```
        1
       / \
      3   2
     /
    5
```

**Tree 2**:
```
        2
       / \
      1   3
       \    \
        4    7
```

---

#### Recursive Walkthrough:
1. At root nodes `1` (Tree 1) and `2` (Tree 2):
    - Merge values: `1 + 2 = 3`.
    - Recurse on left and right children.
2. Left children `3` (Tree 1) and `1` (Tree 2):
    - Merge values: `3 + 1 = 4`.
    - Recurse on left and right children.
    - Left child `5` (Tree 1) and `null` (Tree 2): Use `5`.
    - Right child `null` (Tree 1) and `4` (Tree 2): Use `4`.
3. Right children `2` (Tree 1) and `3` (Tree 2):
    - Merge values: `2 + 3 = 5`.
    - Right child `null` (Tree 1) and `7` (Tree 2): Use `7`.

**Merged Tree**:
```
        3
       / \
      4   5
     / \    \
    5   4    7
```

---

### Complexity Analysis:

#### Recursive DFS:
- **Time Complexity**: \(O(n)\), where \(n\) is the total number of nodes in both trees. Every node is visited once.
- **Space Complexity**: \(O(h)\), where \(h\) is the height of the tree, for the recursion stack.

#### Iterative BFS:
- **Time Complexity**: \(O(n)\), where \(n\) is the total number of nodes in both trees. Every node is visited once.
- **Space Complexity**: \(O(n)\), for the queue storage in the worst case (complete binary tree).

---

Let me know if you want to explore this further!