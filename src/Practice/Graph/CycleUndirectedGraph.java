package Practice.Graph;

//public class CycleUndirectedGraph {
//}

import java.util.*;

class Graph1 {
    private Map<Integer, List<Integer>> adjList;

    Graph1(int V) {
        adjList = new HashMap<>();
        for (int i = 0; i < V; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);  // Since the graph is undirected
    }

    boolean dfs(int node, int parent, Set<Integer> visited) {
        visited.add(node);

        for (int neighbor : adjList.get(node)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, node, visited)) {
                    return true;  // Cycle detected
                }
            } else if (neighbor != parent) {
                return true;  // Cycle detected
            }
        }
        return false;
    }

    boolean hasCycle(int V) {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < V; i++) {
            if (!visited.contains(i)) {
                if (dfs(i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}

public class CycleUndirectedGraph {
    public static void main(String[] args) {
        Graph1 graph = new Graph1(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(3, 4);

        System.out.println("Graph has cycle: " + graph.hasCycle(5)); // Should return true
    }
}

//In the given graph and example, the cycle exists between the nodes **1 → 2 → 4 → 1**. Let's analyze the steps to see how this cycle is formed:
//
//---
//
//### **Graph Structure**
//```
//0 → 1 → 2 → 3
//       ↑     ↓
//       4 ←----
//```
//
//- **Edges:**
//  - `0 → 1`
//  - `1 → 2`
//  - `2 → 3`
//  - `2 → 4`
//  - `4 → 1` (This edge completes the cycle **1 → 2 → 4 → 1**)
//
//---
//
//### **Cycle Detection Walkthrough**
//We’ll use DFS and the recursion stack (`recStack`) to detect cycles.
//
//1. **Start at Node `0`:**
//   - `visited = {0}`
//   - `recStack = {0}`
//   - Move to `1` (neighbor of `0`).
//
//2. **Move to Node `1`:**
//   - `visited = {0, 1}`
//   - `recStack = {0, 1}`
//   - Move to `2` (neighbor of `1`).
//
//3. **Move to Node `2`:**
//   - `visited = {0, 1, 2}`
//   - `recStack = {0, 1, 2}`
//   - Explore neighbors of `2`:
//     - Visit `3` first.
//
//4. **Move to Node `3`:**
//   - `visited = {0, 1, 2, 3}`
//   - `recStack = {0, 1, 2, 3}`
//   - Node `3` has no neighbors, so backtrack:
//     - Remove `3` from `recStack`: `recStack = {0, 1, 2}`.
//
//5. **Back to Node `2`:**
//   - Explore the next neighbor, `4`.
//
//6. **Move to Node `4`:**
//   - `visited = {0, 1, 2, 3, 4}`
//   - `recStack = {0, 1, 2, 4}`
//   - Explore neighbor `1` of `4`.
//
//7. **Neighbor `1` of Node `4`:**
//   - `1` is already in `recStack`!
//   - This means there is a back edge to a node in the current DFS path:
//     - **Cycle Det

//The **above code** is designed specifically for detecting **cycles in a directed graph**. It does not handle the case for **undirected graphs** without modifications because the logic for detecting cycles is different between directed and undirected graphs.
//
//---
//
//### **Why the Code Only Works for Directed Graphs**
//1. **Directed Graph Logic:**
//   - The code uses a `recStack` (recursion stack) to detect cycles, which is specific to directed graphs.
//   - A cycle is detected when a node that is already in the recursion stack is encountered during the DFS.
//
//2. **Undirected Graph Logic:**
//   - For an undirected graph, encountering a previously visited node does not necessarily mean a cycle exists because it could simply be the parent node of the current node in the DFS tree.
//   - To handle this, undirected graphs require an additional condition to check that the neighbor is not the parent of the current node.
//
//---
//
//### **How to Modify the Code for Undirected Graphs**
//For an **undirected graph**, the `recStack` is unnecessary. Instead, we use the `parent` parameter to ensure we don't mistakenly detect a cycle involving the node that we just came from.
//
//#### **Modified Code for Undirected Graph**
//```java
//boolean dfsUndirected(int node, int parent, Set<Integer> visited, Map<Integer, List<Integer>> adjList) {
//    visited.add(node);
//
//    for (int neighbor : adjList.get(node)) {
//        if (!visited.contains(neighbor)) {
//            if (dfsUndirected(neighbor, node, visited, adjList)) {
//                return true;  // Cycle detected
//            }
//        } else if (neighbor != parent) {
//            return true;  // Cycle detected
//        }
//    }
//
//    return false;
//}
//
//boolean hasCycleUndirected(int V) {
//    Set<Integer> visited = new HashSet<>();
//    for (int i = 0; i < V; i++) {
//        if (!visited.contains(i)) {
//            if (dfsUndirected(i, -1, visited, adjList)) {
//                return true;
//            }
//        }
//    }
//    return false;
//}
//```
//
//---
//
//### **Summary of the Two Cases**
//| **Graph Type**      | **Cycle Detection Technique**                                                                                                                                                       |
//|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
//| **Directed Graph**   | Use a `recStack` to detect back edges. If a node is encountered that is already in the recursion stack, a cycle exists.                                                           |
//| **Undirected Graph** | Use a `parent` parameter to avoid falsely detecting cycles due to revisiting the node you just came from. A cycle exists if a visited node is not the parent of the current node. |
//
//---
//
//### **Key Differences**
//1. **Directed Graph**: Recursion stack (`recStack`) is needed to track nodes in the current DFS path.
//2. **Undirected Graph**: The `parent` node is used to ensure we don’t count the back edge to the parent as a cycle.
//
//---
//
//### **Unified Handling**
//If