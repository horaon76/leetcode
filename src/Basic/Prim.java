//package Basic;
//
//import java.util.*;
//
//// Class to represent a graph edge
//class Edge {
//    int vertex;
//    int weight;
//
//    public Edge(int vertex, int weight) {
//        this.vertex = vertex;
//        this.weight = weight;
//    }
//}
//
//public class Prim {
//    // Function to perform Prim's Algorithm
//    public static void primMST(List<List<edge>> graph, int V) {
//        // Array to store constructed MST
//        int[] parent = new int[V];
//
//        // Key values used to pick minimum weight edge
//        int[] key = new int[V];
//
//        // To represent set of vertices included in MST
//        boolean[] mstSet = new boolean[V];
//
//        // Initialize all keys as INFINITE
//        Arrays.fill(key, Integer.MAX_VALUE);
//
//        // Priority Queue to pick the minimum edge weight
//        PriorityQueue<edge> pq = new PriorityQueue<>(V, Comparator.comparingInt(edge -> edge.weight));
//
//        // Start from the first vertex (0)
//        key[0] = 0;
//        parent[0] = -1;
//        pq.add(new edge(0, key[0]));
//
//        while (!pq.isEmpty()) {
//            // Pick the vertex with the minimum key value
//            int u = pq.poll().vertex;
//            mstSet[u] = true;
//
//            // Loop over all adjacent vertices
//            for (Basic.edge edge : graph.get(u)) {
//                int v = edge.vertex;
//                int weight = edge.weight;
//
//                // If v is not in mstSet and the weight of (u, v) is smaller than the current key of v
//                if (!mstSet[v] && weight < key[v]) {
//                    parent[v] = u;
//                    key[v] = weight;
//                    pq.add(new edge(v, key[v]));
//                }
//            }
//        }
//
//        // Print the MST
//        printMST(parent, graph, V);
//    }
//
//    // Function to print the constructed MST
//    public static void printMST(int[] parent, List<List<edge>> graph, int V) {
//        System.out.println("Edge \tWeight");
//        for (int i = 1; i < V; i++) {
//            for (Basic.edge edge : graph.get(i)) {
//                if (edge.vertex == parent[i]) {
//                    System.out.println(parent[i] + " - " + i + "\t" + edge.weight);
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        int V = 5; // Number of vertices
//
//        // Initialize graph as adjacency list
//        List<List<edge>> graph = new ArrayList<>();
//        for (int i = 0; i < V; i++) {
//            graph.add(new ArrayList<>());
//        }
//
//        // Add edges (u, v, weight)
//        graph.get(0).add(new edge(1, 2));
//        graph.get(0).add(new edge(3, 6));
//        graph.get(1).add(new edge(0, 2));
//        graph.get(1).add(new edge(2, 3));
//        graph.get(1).add(new edge(3, 8));
//        graph.get(1).add(new edge(4, 5));
//        graph.get(2).add(new edge(1, 3));
//        graph.get(2).add(new edge(4, 7));
//        graph.get(3).add(new edge(0, 6));
//        graph.get(3).add(new edge(1, 8));
//        graph.get(4).add(new edge(1, 5));
//        graph.get(4).add(new edge(2, 7));
//
//        // Run Prim's algorithm
//        primMST(graph, V);
//    }
//}
