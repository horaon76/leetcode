package Graph;

import java.util.*;

/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * <p>
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 * <p>
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * Example 2:
 * <p>
 * <p>
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2 * 105
 * 0 <= edges.length <= 2 * 105
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 **/
public class FindPathExists1971 {

    public static Boolean validPath(int n, int[][] edges, int source, int destination) throws Exception {
        if (source == destination) {
            throw new Exception("Invalid Input");
        }

        Graph graph = new Graph(n);
        initGraph(edges, graph);
        Boolean found = doDFS(graph, source, destination, n);
        return found;
    }

    private static boolean doDFS(Graph graph, int source, int destination, int V) {
        boolean found = false;
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(V, false));
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        List<List<Integer>> adjList = graph.getAdjList();
        while (!stack.isEmpty()) {
            int currentNode = stack.peek();
            stack.pop();
            if (found == false) {
                if (currentNode == destination) {
                    found = true;
                    break;
                }
                if (visited.get(currentNode) != true) {
                    visited.set(currentNode, true);
                }
                List<Integer> neighbours = adjList.get(currentNode);
                for (Integer neighbour : neighbours) {
                    if (visited.get(neighbour) == false) {
                        stack.push(neighbour);
                    }
                }
            }
        }
        return found;
    }

    public boolean validPathWithMap(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, (k) -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, (k) -> new ArrayList<>()).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        return dfsRecursion(source, destination, graph, visited);
    }

    private boolean dfsRecursion(int node, int destination, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (node == destination) {
            return true;
        }
        visited.add(node);
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfsRecursion(neighbor, destination, graph, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void initGraph(int[][] edges, Graph graph) {
        for (int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
    }

    public static void main(String[] args) throws Exception {
        //Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
        int source = 0;
        int destination = 5;
        int n = 6;
        int[][] edges = new int[][]{
                {0, 1},
                {0, 2},
                {3, 5},
                {5, 4},
                {4, 3}
        };
//        System.out.println(validPath(n, edges, source, destination));
        // Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
        int source1 = 0;
        int destination1 = 2;
        int n1 = 3;
        int[][] edges1 = new int[][]{
                {0, 1},
                {1, 2},
                {2, 0}
        };
        System.out.println(validPath(n1, edges1, source1, destination1));
    }
}
