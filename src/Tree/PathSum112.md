Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.


Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There are two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.
Example 3:

Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000

Here's an alternative solution to **LeetCode 112. Path Sum** that uses **iterative traversal with a stack** instead of recursion. This approach avoids the recursion stack, which can help in scenarios where the tree is deep.

---

### **Iterative Solution Using a Stack**

Instead of recursively traversing the tree, you can simulate the process using a stack. The stack will store tuples containing:
1. The current node.
2. The remaining target sum after subtracting the node's value.

---

### **Algorithm**

1. If the root is `null`, return `false` (no tree to traverse).
2. Use a stack to perform a **Depth-First Search (DFS)**.
    - Push the root node and its value (initial `targetSum` minus the root's value) onto the stack.
3. While the stack is not empty:
    - Pop a node and its associated remaining sum.
    - If it's a leaf node and the remaining sum is 0, return `true`.
    - Push the left and right children onto the stack (if they exist), along with their updated remaining sums.
4. If the stack is empty and no path matches the target sum, return `false`.

---

### **Code Implementation (Python)**

```python
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def hasPathSum(self, root: TreeNode, targetSum: int) -> bool:
        if not root:
            return False
        
        # Stack stores tuples of (node, remaining sum)
        stack = [(root, targetSum - root.val)]
        
        while stack:
            current, remaining_sum = stack.pop()
            
            # Check if it's a leaf node with the correct remaining sum
            if not current.left and not current.right and remaining_sum == 0:
                return True
            
            # Add the right and left children to the stack if they exist
            if current.right:
                stack.append((current.right, remaining_sum - current.right.val))
            if current.left:
                stack.append((current.left, remaining_sum - current.left.val))
        
        return False
```

---

### **Example Walkthrough**

#### Input:
```plaintext
root = [5,4,8,11,null,13,4,7,2,null,null,null,1]
targetSum = 22
```

#### Stack Operations:
- Initial stack: `[(5, 17)]` (root node \(5\), \(22 - 5 = 17\)).
- Pop \(5\), push \((4, 13)\) and \((8, 9)\).
- Pop \(4\), push \((11, 2)\).
- Pop \(11\), push \((7, -5)\) and \((2, 0)\).
- Pop \(2\), return `True` (leaf node with remaining sum \(0\)).

---

### **Complexity Analysis**

#### Time Complexity:
- **Same as the recursive solution**: \(O(N)\), where \(N\) is the number of nodes.
- Each node is visited once, and stack operations are \(O(1)\) each.

#### Space Complexity:
- **Better than recursion in some cases**:
    - \(O(H)\), where \(H\) is the height of the tree (for the stack).
    - Worst case \(H = N\) for a skewed tree.
    - Balanced tree: \(H = \log N\).

---

This solution avoids recursion and can be useful for handling very deep trees. Let me know if you'd like to explore more variations or examples!

Here’s an **iterative solution** for **LeetCode 112. Path Sum** in **Java** using a stack:

---

### **Iterative Solution Using a Stack**

In this implementation, we use a stack to perform a **Depth-First Search (DFS)**. Each stack entry stores:
1. The current node being processed.
2. The remaining sum required for a path to reach the target sum.

---

### **Java Code Implementation**

```java
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // Stack stores pairs of (current node, remaining sum)
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        
        nodeStack.push(root);
        sumStack.push(targetSum - root.val);

        while (!nodeStack.isEmpty()) {
            TreeNode currentNode = nodeStack.pop();
            int currentSum = sumStack.pop();

            // Check if it's a leaf node and the remaining sum is 0
            if (currentNode.left == null && currentNode.right == null && currentSum == 0) {
                return true;
            }

            // Push right and left children onto the stack if they exist
            if (currentNode.right != null) {
                nodeStack.push(currentNode.right);
                sumStack.push(currentSum - currentNode.right.val);
            }
            if (currentNode.left != null) {
                nodeStack.push(currentNode.left);
                sumStack.push(currentSum - currentNode.left.val);
            }
        }

        return false;
    }
}
```

---

### **Example Walkthrough**

#### Input:
```plaintext
Tree: [5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1]
TargetSum: 22
```

#### Iterative Steps:
1. **Initial Stack**:
    - `nodeStack`: \([5]\)
    - `sumStack`: \([17]\) (\(22 - 5 = 17\))

2. **First Pop**: \(5\), push \((4, 13)\) and \((8, 9)\).
    - `nodeStack`: \([4, 8]\)
    - `sumStack`: \([13, 9]\)

3. **Next Pop**: \(4\), push \((11, 2)\).
    - `nodeStack`: \([8, 11]\)
    - `sumStack`: \([9, 2]\)

4. **Next Pop**: \(11\), push \((7, -5)\) and \((2, 0)\).
    - `nodeStack`: \([8, 7, 2]\)
    - `sumStack`: \([9, -5, 0]\)

5. **Next Pop**: \(2\), remaining sum is 0, return `true`.

---

### **Complexity Analysis**

#### Time Complexity:
- \(O(N)\), where \(N\) is the number of nodes.
- Each node is visited once.

#### Space Complexity:
- \(O(H)\), where \(H\) is the height of the tree (due to the stack).
- Worst case \(O(N)\) for a skewed tree.
- Best case \(O(\log N)\) for a balanced tree.

---

Let me know if you want further clarification or additional variations!