
### Problem: **LeetCode 1373 - Maximum Sum BST in Binary Tree**

Here are the possible approaches to solve the problem, including both DFS and BFS strategies.

---

### **1. DFS (Postorder Traversal) with Validation**

This approach involves using **postorder traversal** to check whether each subtree is a valid Binary Search Tree (BST) and calculating the maximum sum for the valid BST subtrees.

#### **Algorithm**:
1. Perform a postorder traversal of the tree.
2. For each node, check if the left and right subtrees are valid BSTs and whether the current node's value is greater than the maximum value of the left subtree and smaller than the minimum value of the right subtree.
3. If the subtree is a BST, compute its sum and update the global maximum sum.

#### **Code**:

```java
class Solution {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        postorder(root);
        return maxSum;
    }

    private int[] postorder(TreeNode node) {
        if (node == null) {
            return new int[] { 1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        }

        int[] left = postorder(node.left);
        int[] right = postorder(node.right);

        if (left[0] == 1 && right[0] == 1 && node.val > left[2] && node.val < right[1]) {
            int sum = left[3] + right[3] + node.val;
            maxSum = Math.max(maxSum, sum);
            return new int[] { 1, Math.min(node.val, left[1]), Math.max(node.val, right[2]), sum };
        }

        return new int[] { 0, 0, 0, 0 };
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

        int result = sol.maxSumBST(root);
        System.out.println("Maximum Sum of BST in the Tree: " + result);
    }
}
```

#### **Time Complexity**:
- **Time Complexity**: \(O(n)\) where \(n\) is the number of nodes, as each node is processed once.
- **Space Complexity**: \(O(h)\) where \(h\) is the height of the tree due to the recursion stack.

---

### **2. BFS (Level Order Traversal) with Validation**

In this approach, **level-order traversal** is used to traverse the binary tree level by level using a queue. For each node, we verify if it forms a valid BST with its left and right children by checking its value in relation to the children’s values.

#### **Algorithm**:
1. Perform level order traversal (BFS) using a queue.
2. For each node, check if the subtree rooted at the node is a valid BST by ensuring the left child is smaller, the right child is larger, and the current node follows the BST properties.
3. Track the sum of nodes for valid BST subtrees and update the maximum sum.

#### **Code**:

```java
import java.util.*;

class Solution {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> sumMap = new HashMap<>();
        Map<TreeNode, Integer> minMap = new HashMap<>();
        Map<TreeNode, Integer> maxMap = new HashMap<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            int leftSum = sumMap.getOrDefault(node.left, 0);
            int rightSum = sumMap.getOrDefault(node.right, 0);
            int leftMin = minMap.getOrDefault(node.left, Integer.MAX_VALUE);
            int rightMax = maxMap.getOrDefault(node.right, Integer.MIN_VALUE);

            if (node.left == null || node.right == null || (node.val > leftMax && node.val < rightMin)) {
                int sum = node.val + leftSum + rightSum;
                maxSum = Math.max(maxSum, sum);

                sumMap.put(node, sum);
                minMap.put(node, Math.min(node.val, leftMin));
                maxMap.put(node, Math.max(node.val, rightMax));
            } else {
                sumMap.put(node, 0);
                minMap.put(node, 0);
                maxMap.put(node, 0);
            }

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

        int result = sol.maxSumBST(root);
        System.out.println("Maximum Sum of BST in the Tree: " + result);
    }
}
```

#### **Time Complexity**:
- **Time Complexity**: \(O(n)\) where \(n\) is the number of nodes, as each node is processed once in the level-order traversal.
- **Space Complexity**: \(O(n)\) for storing the queue and maps.

---

### **3. DFS with In-Place Validation**

This solution follows a **recursive DFS approach**, where we recursively calculate the sum of valid BST subtrees. Unlike the previous solutions, we modify the tree itself to store information (sum, min, max) for each subtree.

#### **Algorithm**:
1. Use DFS to recursively check each node’s subtree.
2. Track whether a node’s subtree is a valid BST.
3. If valid, calculate the sum and update the global maximum sum.

#### **Code**:

```java
class Solution {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[] { 1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        if (left[0] == 1 && right[0] == 1 && node.val > left[2] && node.val < right[1]) {
            int sum = left[3] + right[3] + node.val;
            maxSum = Math.max(maxSum, sum);
            return new int[] { 1, Math.min(node.val, left[1]), Math.max(node.val, right[2]), sum };
        }

        return new int[] { 0, 0, 0, 0 };
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

        int result = sol.maxSumBST(root);
        System.out.println("Maximum Sum of BST in the Tree: " + result);
    }
}
```

#### **Time Complexity**:
- **Time Complexity**: \(O(n)\) where \(n\) is the number of nodes in the tree.
- **Space Complexity**: \(O(h)\) where \(h\) is the height of the tree due to recursion.

---

### Summary of Approaches:

1. **DFS (Postorder Traversal) with Validation**:
    - **Time Complexity**: \(O(n)\)
    - **Space Complexity**: \(O(h)\)
    - Efficient and intuitive solution using recursion and postorder traversal.

2. **BFS (Level Order Traversal) with Validation**:
    - **Time Complexity**: \(O(n)\)
    - **Space Complexity**: \(O(n)\)
    - Alternative approach using level-order traversal with a queue.

3. **DFS with In-Place Validation**:
    - **Time Complexity**: \(O(n)\)
    - **Space Complexity**: \(O(h)\)
    - Recursively checks subtrees while updating information in-place.

---

### Conclusion:
All three solutions are efficient with time complexity of \(O(n)\), where \(n\) is the number of nodes. The choice of approach depends on the preference for recursion vs. iteration and how space is managed (using recursion stack vs. queue). The first DFS solution is the most common and optimal in terms of both space and time efficiency.

Let me know if you need further

### Problem Description:
**LeetCode 1373** - **Maximum Sum BST in Binary Tree**

You are given a binary tree `root`. A subtree of the binary tree is a BST if:
- The values of all nodes in the left subtree are strictly less than the value of the root.
- The values of all nodes in the right subtree are strictly greater than the value of the root.
- Both the left and right subtrees are also BSTs.

Your task is to return the **maximum sum of any BST subtree** in the binary tree. If no subtree is a BST, return 0.

---

### Approach 1: Postorder Traversal with Validation
We use a **postorder traversal** to calculate the properties of each subtree (left → right → root). At each node, determine:
1. Whether the subtree rooted at this node is a valid BST.
2. If valid, calculate the sum of all nodes in this subtree.
3. Track the maximum sum across all valid BST subtrees.

---

### Key Idea:
1. Each subtree should return:
    - Whether it is a valid BST.
    - The minimum value in the subtree.
    - The maximum value in the subtree.
    - The sum of values in the subtree.
2. Use postorder traversal to recursively gather information from the left and right subtrees.
3. At each node:
    - Check if the current subtree satisfies the BST conditions.
    - If valid, calculate the sum and update the global maximum sum.
    - If invalid, return information that prevents this subtree from contributing further.

---

### Algorithm:
1. Perform a postorder traversal:
    - For each node, get results from the left and right subtrees.
    - Check if the current subtree is a BST.
    - If yes:
        - Compute its sum and update the global maximum.
        - Return the minimum, maximum, and sum of the subtree.
    - If no:
        - Mark it invalid and pass results up to prevent its inclusion.
2. Return the global maximum at the end.

---

### Code:
```java
class Solution {
    // Global variable to track the maximum sum of any BST subtree
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        postorder(root);
        return maxSum;
    }

    private int[] postorder(TreeNode node) {
        if (node == null) {
            // Base case: [isBST, min, max, sum] for an empty subtree
            return new int[] { 1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };
        }

        // Recursively check the left and right subtrees
        int[] left = postorder(node.left);
        int[] right = postorder(node.right);

        // Check if the current subtree is a valid BST
        if (left[0] == 1 && right[0] == 1 && node.val > left[2] && node.val < right[1]) {
            // Current subtree is a BST
            int sum = left[3] + right[3] + node.val; // Total sum of this subtree
            maxSum = Math.max(maxSum, sum); // Update global maxSum

            // Return information for this subtree
            return new int[] { 1, Math.min(node.val, left[1]), Math.max(node.val, right[2]), sum };
        }

        // Current subtree is NOT a BST
        return new int[] { 0, 0, 0, 0 };
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

        int result = sol.maxSumBST(root);
        System.out.println("Maximum Sum of BST in the Tree: " + result);
    }
}
```

---

### Example Walkthrough:

#### Input Tree:
```
        1
       / \
      4   3
     / \  / \
    2   4 2  5
              / \
             4   6
```

#### Step-by-Step Postorder Traversal:
1. Start at the leaf nodes:
    - `(2)`: Valid BST with sum `2`. [BST = true, min = 2, max = 2, sum = 2].
    - `(4)`: Valid BST with sum `4`. [BST = true, min = 4, max = 4, sum = 4].
2. Move to `(4)` (left subtree of `1`):
    - Left = `(2)` → Valid BST.
    - Right = `(4)` → Valid BST.
    - Root value `4` violates BST property (`4 <= 4`).
    - Mark this subtree invalid.
3. Process `(3)` (right subtree of `1`):
    - Left = `(2)` → Valid BST.
    - Right = `(5)`:
        - Left = `(4)` → Valid BST.
        - Right = `(6)` → Valid BST.
        - Subtree rooted at `(5)` is valid, sum = `4 + 5 + 6 = 15`.
        - [BST = true, min = 4, max = 6, sum = 15].
    - Subtree rooted at `(3)` is invalid (left max `2` >= `3`).
4. Process the root `(1)`:
    - Left subtree = invalid.
    - Right subtree = invalid.
    - Subtree rooted at `(1)` is invalid.

#### Output:
The maximum valid BST sum is `15` from the subtree rooted at `(5)`.

---

### Complexity Analysis:

#### Time Complexity:
- \(O(n)\): Each node is visited exactly once during the traversal.

#### Space Complexity:
- \(O(h)\): The maximum depth of the recursion stack, where \(h\) is the height of the tree.

---

Let me know if you need further clarifications or additional approaches!