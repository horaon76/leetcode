package Graph;

import java.util.HashMap;
import java.util.List;
import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * Test case format:
 * <p>
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 * <p>
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 * <p>
 * <p>
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 * <p>
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 **/


class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    private static Map<Node, Node> visited1 = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // HashMap to keep track of cloned nodes
        Map<Node, Node> visited = new HashMap<>();
        // Stack to perform DFS
        Stack<Node> stack = new Stack<>();

        // Clone the first node and add to stack and map
        Node cloneNode = new Node(node.val);
        stack.push(node);
        visited.put(node, cloneNode);

        // Perform DFS using stack
        while (!stack.isEmpty()) {
            Node current = stack.pop();

            // Process each neighbor
            for (Node neighbor : current.neighbors) {
                // If neighbor hasn't been cloned yet
                if (!visited.containsKey(neighbor)) {
                    // Clone it and push to stack
                    Node neighborClone = new Node(neighbor.val);
                    visited.put(neighbor, neighborClone);
                    stack.push(neighbor);
                }
                // Link the cloned current node to its cloned neighbors
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }

        // Return the cloned graph's starting node
        return visited.get(node);
    }

    public static Node cloneGraphRecursively(Node node) {
        if (node == null) {
            return null;
        }
        if (visited1.containsKey(node)) {
            return visited1.get(node);
        }
        Node cloneNode = new Node(node.val);
        visited1.put(node, cloneNode);
        for (Node neighbour : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphRecursively(neighbour));
        }
        return cloneNode;
    }
}

public class CloneGraph133 {

    public static void main(String[] args) {
        // Create a sample graph:
        // 1 -- 2
        // |    |
        // 4 -- 3

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        // Instantiate Solution class and clone the graph
        Solution solution = new Solution();
        Node clonedGraph = solution.cloneGraphRecursively(node1);

        // Print both original and cloned graphs to verify
        System.out.println("Original Graph:");
        printGraph(node1, new HashSet<>());

        System.out.println("\nCloned Graph:");
        printGraph(clonedGraph, new HashSet<>());
    }

    // Helper function to print the graph
    public static void printGraph(Node node, Set<Integer> visited) {
        if (node == null || visited.contains(node.val)) {
            return;
        }

        // Mark node as visited
        visited.add(node.val);

        // Print current node and its neighbors
        System.out.print("Node " + node.val + " -> Neighbors: ");
        for (Node neighbor : node.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();

        // Recursively print neighbors
        for (Node neighbor : node.neighbors) {
            printGraph(neighbor, visited);
        }
    }
}
/**
 * Time and Space Complexity
 * Time Complexity:
 * 𝑂
 * (
 * 𝑁
 * +
 * 𝑀
 * )
 * O(N+M), where
 * 𝑁
 * N is the number of nodes and
 * 𝑀
 * M is the number of edges. Each node and edge is processed once during the DFS traversal.
 * <p>
 * Space Complexity:
 * 𝑂
 * (
 * 𝑁
 * )
 * O(N), because of the additional space needed for the visited map and the stack, which both store up to
 * 𝑁
 * N nodes.
 * <p>
 * This iterative DFS solution avoids recursion limits and efficiently clones the graph.
 **/