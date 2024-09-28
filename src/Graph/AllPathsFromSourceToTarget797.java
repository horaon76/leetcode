package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 * <p>
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Example 2:
 * <p>
 * <p>
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * All the elements of graph[i] are unique.
 * The input graph is guaranteed to be a DAG.
 **/
//https://leetcode.com/problems/all-paths-from-source-to-target/solutions/986429/python-iterative-dfs-with-detailed-time-complexity-visuals/
public class AllPathsFromSourceToTarget797 {
    public static List<List<Integer>> dfsSolution(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0); // Start with the source node
        dfsRecursive(graph, 0, path, result);
        return result;
    }

    private static void dfsRecursive(int[][] graph, int node, List<Integer> path, List<List<Integer>> result) {
        // If we reached the target node
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path)); // Add the current path to the result
            return;
        }

        // Explore each neighbor of the current node
        for (int neighbor : graph[node]) {
            path.add(neighbor); // Add the neighbor to the current path
            dfsRecursive(graph, neighbor, path, result); // Recurse on the neighbor
            path.remove(path.size() - 1); // Backtrack
        }
    }

    public static List<List<Integer>> bfsSolution(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();

        // Start with the path containing the source node (0)
        queue.offer(new ArrayList<>(List.of(0)));

        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int lastNode = path.get(path.size() - 1);

            // Check if we reached the target node
            if (lastNode == graph.length - 1) {
                result.add(path); // Add the current path to the result
            } else {
                // Explore each neighbor of the current node
                for (int neighbor : graph[lastNode]) {
                    List<Integer> newPath = new ArrayList<>(path); // Create a new path
                    newPath.add(neighbor); // Add the neighbor to the path
                    queue.offer(newPath); // Enqueue the new path
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        List<List<Integer>> paths = dfsSolution(graph);
        System.out.println(paths); // Output: [[0, 1, 3], [0, 2, 3]]

        List<List<Integer>> paths1 = bfsSolution(graph);
        System.out.println(paths1); // Output: [[0, 1, 3], [0, 2, 3]]
    }
}
