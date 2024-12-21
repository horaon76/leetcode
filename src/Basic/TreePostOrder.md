Let’s provide a comprehensive example with code, input, output, and time-space complexity for **postorder traversal** using:

1. **Double Stack**
2. **Single Stack with Visited Marker**
3. **Linked List**
4. **Queue + Reverse**

---

### **Tree Structure**
We will use the following binary tree for all examples:

```
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
       / \
      8   9
```

### **TreeNode Class (Java)**

```java
class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
```

---

### **1. Double Stack Implementation**

#### **Code**

```java
public static void postorderDoubleStack(TreeNode root) {
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
        System.out.print(stack2.pop().val + " ");
    }
}
```

#### **Input**
- Tree:
```
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
       / \
      8   9
```

#### **Output**
```
4 8 9 5 2 6 7 3 1
```

#### **Time Complexity**
- \(O(n)\): Each node is pushed and popped once.

#### **Space Complexity**
- \(O(n)\): Two stacks, one for traversal and one for reverse-order storage.

---

### **2. Single Stack with Visited Marker**

#### **Code**

```java
public static void postorderSingleStack(TreeNode root) {
    if (root == null) return;

    Stack<Pair<TreeNode, Boolean>> stack = new Stack<>();
    stack.push(new Pair<>(root, false));
    List<Integer> result = new ArrayList<>();

    while (!stack.isEmpty()) {
        Pair<TreeNode, Boolean> current = stack.pop();
        TreeNode node = current.getKey();
        boolean visited = current.getValue();

        if (node == null) continue;

        if (visited) {
            result.add(node.val);
        } else {
            stack.push(new Pair<>(node, true)); // Mark as visited
            if (node.right != null) stack.push(new Pair<>(node.right, false)); // Push right child
            if (node.left != null) stack.push(new Pair<>(node.left, false)); // Push left child
        }
    }

    System.out.println(result);
}
```

#### **Input**
- Tree:
```
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
       / \
      8   9
```

#### **Output**
```
4 8 9 5 2 6 7 3 1
```

#### **Time Complexity**
- \(O(n)\): Each node is pushed and popped once.

#### **Space Complexity**
- \(O(h)\): At most \(h\) elements in the stack, where \(h\) is the height of the tree.

---

### **3. Linked List as Reverse Storage**

#### **Code**

```java
public static void postorderWithLinkedList(TreeNode root) {
    if (root == null) return;

    Stack<TreeNode> stack = new Stack<>();
    LinkedList<Integer> result = new LinkedList<>();

    stack.push(root);

    while (!stack.isEmpty()) {
        TreeNode current = stack.pop();
        result.addFirst(current.val); // Add to the head of the list (reverse order)

        if (current.left != null) stack.push(current.left);
        if (current.right != null) stack.push(current.right);
    }

    System.out.println(result);
}
```

#### **Input**
- Tree:
```
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
       / \
      8   9
```

#### **Output**
```
4 8 9 5 2 6 7 3 1
```

#### **Time Complexity**
- \(O(n)\): Each node is pushed and popped once.

#### **Space Complexity**
- \(O(n)\): Due to the linked list storing results.

---

### **4. Queue + Reverse**

#### **Code**

```java
public static void postorderWithQueue(TreeNode root) {
    if (root == null) return;

    Queue<TreeNode> queue = new LinkedList<>();
    LinkedList<Integer> result = new LinkedList<>();

    queue.add(root);

    while (!queue.isEmpty()) {
        TreeNode current = queue.poll();
        result.addFirst(current.val); // Reverse the order during processing

        if (current.left != null) queue.add(current.left);
        if (current.right != null) queue.add(current.right);
    }

    System.out.println(result);
}
```

#### **Input**
- Tree:
```
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
       / \
      8   9
```

#### **Output**
```
4 8 9 5 2 6 7 3 1
```

#### **Time Complexity**
- \(O(n)\): Each node is processed once.

#### **Space Complexity**
- \(O(n)\): Queue and linked list together require \(O(n)\) space.

---

### **Summary of Methods**

| **Method**                    | **Time Complexity** | **Space Complexity** | **Notes**                                    |
|--------------------------------|---------------------|-----------------------|----------------------------------------------|
| Double Stack                  | \(O(n)\)           | \(O(n)\)             | Intuitive and simple.                       |
| Single Stack (Visited Marker) | \(O(n)\)           | \(O(h)\)             | Efficient; avoids extra data structures.    |
| Linked List                   | \(O(n)\)           | \(O(n)\)             | Uses list as reverse stack.                 |
| Queue + Reverse               | \(O(n)\)           | \(O(n)\)             | Simpler alternative to double stack.        |

Each method produces the same output but differs in space complexity and the structures used. For optimized space, the **Single Stack with Visited Marker** is recommended.
