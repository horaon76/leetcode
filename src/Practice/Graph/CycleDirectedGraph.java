package Practice.Graph;

//public class CycleDirectedGraph {
//}
import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> adjList;

    Graph(int V) {
        adjList = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    void addEdge(int u, int v) {
        adjList.get(u).add(v);  // Directed edge
    }

    boolean dfs(int node, Set<Integer> visited, Set<Integer> recStack) {
        visited.add(node);
        recStack.add(node);

        for (int neighbor : adjList.get(node)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, visited, recStack)) {
                    return true;  // Cycle detected
                }
            } else if (recStack.contains(neighbor)) {
                return true;  // Cycle detected
            }
        }

        recStack.remove(node);  // Backtracking step
        return false;
    }

    boolean hasCycle(int V) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();

        for (int i = 0; i < V; i++) {
            if (!visited.contains(i)) {
                if (dfs(i, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }
}

public class CycleDirectedGraph {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 1);

        System.out.println("Cycle detected: " + graph.hasCycle(5));  // Output: true
    }
}
