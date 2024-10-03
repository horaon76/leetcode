//package Basic;
//
//import java.util.*;
//
//class edge implements Comparable<edge> {
//    int src, dest, weight;
//
//    // Constructor
//    public edge(int src, int dest, int weight) {
//        this.src = src;
//        this.dest = dest;
//        this.weight = weight;
//    }
//
//    // Comparator function used for sorting edges based on weight
//    public int compareTo(edge compareEdge) {
//        return this.weight - compareEdge.weight;
//    }
//}
//
//class Subset {
//    int parent, rank;
//}
//
//public class KruskalAlgorithm {
//    private int vertices;
//    private List<edge> allEdges = new ArrayList<>();
//
//    // Constructor
//    public KruskalAlgorithm(int vertices) {
//        this.vertices = vertices;
//    }
//
//    // Add edges to the graph
//    public void addEdge(int src, int dest, int weight) {
//        edge edge = new edge(src, dest, weight);
//        allEdges.add(edge);
//    }
//
//    // Function to find the root of an element (with path compression)
//    private int find(Subset[] subsets, int i) {
//        if (subsets[i].parent != i) {
//            subsets[i].parent = find(subsets, subsets[i].parent); // Path compression
//        }
//        return subsets[i].parent;
//    }
//
//    // Function to perform the union of two subsets (by rank)
//    private void union(Subset[] subsets, int x, int y) {
//        int rootX = find(subsets, x);
//        int rootY = find(subsets, y);
//
//        // Attach smaller rank tree under the root of the larger rank tree
//        if (subsets[rootX].rank < subsets[rootY].rank) {
//            subsets[rootX].parent = rootY;
//        } else if (subsets[rootX].rank > subsets[rootY].rank) {
//            subsets[rootY].parent = rootX;
//        } else {
//            subsets[rootY].parent = rootX;
//            subsets[rootX].rank++;
//        }
//    }
//
//    // Main function to perform Kruskal's algorithm
//    public void kruskalMST() {
//        // List to store the resulting MST
//        List<edge> result = new ArrayList<>();
//
//        // Step 1: Sort all the edges in non-decreasing order of their weight
//        Collections.sort(allEdges);
//
//        // Create subsets for each vertex
//        Subset[] subsets = new Subset[vertices];
//        for (int i = 0; i < vertices; i++) {
//            subsets[i] = new Subset();
//            subsets[i].parent = i;  // Initially, each vertex is its own parent
//            subsets[i].rank = 0;    // Initially, rank is 0
//        }
//
//        int edgeCount = 0; // Number of edges in MST
//
//        // Step 2: Pick the smallest edge and add it to the result if it doesn't form a cycle
//        for (Basic.edge edge : allEdges) {
//            int x = find(subsets, edge.src);
//            int y = find(subsets, edge.dest);
//
//            // If including this edge does not cause a cycle, include it in the result
//            if (x != y) {
//                result.add(edge);
//                union(subsets, x, y);
//                edgeCount++;
//            }
//
//            // If we already have V-1 edges in the MST, we can stop
//            if (edgeCount == vertices - 1) {
//                break;
//            }
//        }
//
//        // Print the resulting MST
//        printMST(result);
//    }
//
//    // Function to print the edges of the Minimum Spanning Tree
//    private void printMST(List<edge> result) {
//        System.out.println("Edges in the Minimum Spanning Tree:");
//        for (Basic.edge edge : result) {
//            System.out.println(edge.src + " -- " + edge.dest + " == " + edge.weight);
//        }
//    }
//
//    public static void main(String[] args) {
//        // Number of vertices in the graph
//        int vertices = 4;
//
//        // Create a graph
//        KruskalAlgorithm graph = new KruskalAlgorithm(vertices);
//
//        // Add edges to the graph
//        graph.addEdge(0, 1, 10);
//        graph.addEdge(0, 2, 6);
//        graph.addEdge(0, 3, 5);
//        graph.addEdge(1, 3, 15);
//        graph.addEdge(2, 3, 4);
//
//        // Run Kruskal's algorithm to find the MST
//        graph.kruskalMST();
//    }
//}
//
