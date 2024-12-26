Given the root of a binary tree, return the sum of all left leaves.

A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
Example 2:

Input: root = [1]
Output: 0


Constraints:

The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000

**LeetCode 404: Sum of Left Leaves**

---

### Problem Statement:
Given the root of a binary tree, return the sum of all **left leaves**.

A **leaf** is a node with no children. A **left leaf** is a leaf node that is the left child of its parent.

---

### Intuition:
1. A left leaf is a node that:
    - Has no children.
    - Is the left child of its parent.
2. We can traverse the tree using **recursion** or **iteration** to identify left leaves and sum their values.

---

### Approach 1: Recursive DFS

#### Algorithm:
1. Base case:
    - If the tree is empty (`root == null`), return `0`.
2. If the left child is a leaf node, add its value to the result.
3. Recursively compute the sum for the left and right subtrees.

#### Code:
```java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0; // Base case: empty tree
        }

        int sum = 0;
        // Check if the left child is a leaf
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }

        // Recursively compute the sum for left and right subtrees
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);

        return sum;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Sum of Left Leaves: " + sol.sumOfLeftLeaves(root)); // Output: 24
    }
}
```

---

### Approach 2: Iterative DFS (Using a Stack)

#### Algorithm:
1. Use a stack to traverse the tree iteratively.
2. For each node:
    - Check if its left child is a leaf. If so, add its value to the sum.
    - Push the left and right children (if they exist) onto the stack.
3. Return the sum.

#### Code:
```java
import java.util.Stack;

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0; // Base case: empty tree
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            // Check if the left child is a leaf
            if (current.left != null && current.left.left == null && current.left.right == null) {
                sum += current.left.val;
            }

            // Add children to the stack
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return sum;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Sum of Left Leaves: " + sol.sumOfLeftLeaves(root)); // Output: 24
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
    - Left child `9` is a leaf. Add its value: `sum = 9`.
    - Move to right child `20`.
2. At node `20`:
    - Left child `15` is a leaf. Add its value: `sum = 9 + 15 = 24`.
    - Right child `7` is not a left leaf, so skip.
3. Final sum = `24`.

---

### Complexity Analysis:

#### Recursive DFS:
- **Time Complexity**: \(O(n)\), where \(n\) is the number of nodes. Each node is visited once.
- **Space Complexity**: \(O(h)\), where \(h\) is the height of the tree, for the recursion stack.

#### Iterative DFS:
- **Time Complexity**: \(O(n)\), where \(n\) is the number of nodes. Each node is visited once.
- **Space Complexity**: \(O(n)\), for the stack storage in the worst case (complete binary tree).

---

Let me know if you'd like further clarification or additional examples!