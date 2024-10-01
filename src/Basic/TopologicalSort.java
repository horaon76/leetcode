package Basic;

import java.util.*;

public class TopologicalSort {
    // Function to perform DFS-based Topological Sort
    public static void topologicalSortUtil(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adjList) {
        visited[node] = true;  // Mark the current node as visited

        // Recur for all the adjacent vertices
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack, adjList);
            }
        }

        // Push current node to stack (to be processed after its neighbors)
        stack.push(node);
    }

    // Function to perform Topological Sort using DFS
    public static List<Integer> topologicalSort(int n, List<List<Integer>> adjList) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        // Call the recursive helper function for each node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack, adjList);
            }
        }

        // Create the topological order by popping from the stack
        List<Integer> topologicalOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topologicalOrder.add(stack.pop());
        }

        return topologicalOrder;
    }

    public static void main(String[] args) {
        int n = 6;  // Number of nodes
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Define directed edges (graph)
        adjList.get(5).add(2);
        adjList.get(5).add(0);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(2).add(3);
        adjList.get(3).add(1);

        // Perform topological sort using DFS
        List<Integer> topologicalOrder = topologicalSort(n, adjList);
        System.out.println("Topological Sort: " + topologicalOrder);
    }
}
