package Practice.Tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class TreeNode{
    TreeNode left, right;
    Integer data;
    public TreeNode(Integer data){
        this.data = data;
        left = right = null;
    }
}
public class Inorder {

    public static void doInorder(TreeNode root){
        if(root == null){
            return;
        }
        doInorder(root.left);
        System.out.println(root.data);
        doInorder(root.right);
    }

    public static void doIterativeInorder(TreeNode root){
        Stack<TreeNode> nodeStack = new Stack<>();
        Set<Integer> nodeVisited = new HashSet<>();
        TreeNode currentNode = root;
        while(!nodeStack.isEmpty() || currentNode != null){
            while(currentNode != null){
                nodeStack.add(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = nodeStack.pop();
            System.out.println(currentNode.data + " ");
            currentNode = currentNode.right;


        }
    }

    public static void doPreOrder(TreeNode root){
        //NLR
        if(root == null){
            return;
        }
        System.out.println(root.data);
        doPreOrder(root.left);
        doPreOrder(root.right);
    }

    public static void doIterativePreOrder(TreeNode root){
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.add(root);
        while(!nodeStack.isEmpty()){
            TreeNode currentNode = nodeStack.pop();
            System.out.println(currentNode.data);
            if(currentNode.right != null){
                nodeStack.add(currentNode.right);
            }
            if(currentNode.left != null){
                nodeStack.add(currentNode.left);
            }
        }
    }

    public static void doPostOrder(TreeNode root){
        //NLR
        if(root == null){
            return;
        }
        doPostOrder(root.left);
        doPostOrder(root.right);
        System.out.println(root.data);
    }

    public static void doIterativePostOrder(TreeNode root){
        if (root == null) return;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode current = stack1.pop();
            stack2.push(current);

            if (current.left != null) stack1.push(current.left);
            if (current.right != null) stack1.push(current.right);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println("Inorder");
        doInorder(root);
        System.out.println("Inorder - Iterative");
        doIterativeInorder(root);
        System.out.println("Preorder");
        doPreOrder(root);
        System.out.println("Preorder - iterative");
        doIterativePreOrder(root);
        System.out.println("Postorder");
        doPostOrder(root);
        System.out.println("Postorder - iterative");
        doIterativePostOrder(root);
    }
}
//Time Complexity:
//𝑂
//(
//𝑁
//)
//O(N), where
//𝑁
//N is the number of nodes. Each node is processed exactly once.
//Space Complexity:
//𝑂
//(
//𝐻
//)
//O(H), where
//𝐻
//H is the height of the tree. In the worst case (a skewed tree), it is
//𝑂
//(
//𝑁
//)
//O(N), and in the best case (a balanced tree), it is
//𝑂
//(
//log
//⁡
//𝑁
//)
//O(logN).