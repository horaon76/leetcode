package DP;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class DecodeWaysII_639 {
    public void flatten(TreeNode root) {
        if (root == null) return;

        // Flatten the left and right subtrees
        flatten(root.left);
        flatten(root.right);

        // Store the right subtree
        TreeNode tempRight = root.right;

        // Move the left subtree to the right
        root.right = root.left;
        root.left = null; // Set the left pointer to null

        // Find the last node of the new right subtree
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }

        // Attach the original right subtree
        current.right = tempRight;
    }
    //O(n)
    //Space Complexity:
    //𝑂
    //(
    //ℎ
    //)
    //O(h), where
    //ℎ
    //h is the height of the tree. This space is used by the recursion stack. In the worst case (skewed tree), it could be
    //𝑂
    //(
    //𝑛
    //)
    //O(n). In the best case (balanced tree), it would be
    //𝑂
    //(
    //log
    //⁡
    //𝑛
    //)
    //O(logn).
}
