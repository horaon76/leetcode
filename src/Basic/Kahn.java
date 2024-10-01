package Basic;

import java.util.*;

public class Kahn {
    // Function to perform Topological Sorting using Kahn's Algorithm
    public static List<Integer> kahnTopologicalSort(int n, List<List<Integer>> adjList) {
        int[] indegree = new int[n];  // Array to store indegree of each node
        List<Integer> topologicalOrder = new ArrayList<>();  // List to store topological order

        // Calculate indegree for each node
        for (int i = 0; i < n; i++) {
            for (int neighbor : adjList.get(i)) {
                indegree[neighbor]++;
            }
        }

        // Queue to process nodes with zero indegree
        Queue<Integer> queue = new LinkedList<>();

        // Add all nodes with zero indegree to the queue
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Process the nodes
        while (!queue.isEmpty()) {
            int current = queue.poll();
            topologicalOrder.add(current);

            // For each neighbor of the current node, reduce its indegree
            for (int neighbor : adjList.get(current)) {
                indegree[neighbor]--;

                // If indegree becomes zero, add it to the queue
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If the topological order does not contain all nodes, there's a cycle
        if (topologicalOrder.size() != n) {
            throw new IllegalArgumentException("The graph contains a cycle.");
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

        // Perform topological sort
        List<Integer> topologicalOrder = kahnTopologicalSort(n, adjList);
        System.out.println("Topological Sort: " + topologicalOrder);
    }
}
/**
 *
 * Time Complexity:
 * 𝑂
 * (
 * 𝑉
 * +
 * 𝐸
 * )
 * O(V+E)
 * V is the number of vertices (nodes).
 * E is the number of edges.
 * Explanation:
 * Indegree Calculation: We iterate over every edge once to compute the indegree of each vertex. This takes
 * 𝑂
 * (
 * 𝐸
 * )
 * O(E).
 * Processing the Queue: Each vertex is added and removed from the queue once, so iterating over the vertices takes
 * 𝑂
 * (
 * 𝑉
 * )
 * O(V).
 * Updating Indegree: For each vertex removed from the queue, we decrease the indegree of all its neighbors, which corresponds to iterating over all edges, taking
 * 𝑂
 * (
 * 𝐸
 * )
 * O(E).
 * Thus, the total time complexity is
 * 𝑂
 * (
 * 𝑉
 * +
 * 𝐸
 * )
 * O(V+E).
 *
 * Space Complexity:
 * 𝑂
 * (
 * 𝑉
 * +
 * 𝐸
 * )
 * O(V+E)
 * O(V) for storing the indegree array, queue, and topological order list.
 * O(E) for storing the adjacency list representing the graph.
 *
 * **/