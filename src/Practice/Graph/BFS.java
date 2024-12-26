package Practice.Graph;

import java.util.*;

public class BFS {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void addEdge(int u, int v){
        graph.putIfAbsent(u, new ArrayList<>());
        graph.get(u).add(v);
    }

    public static void doBFS(int source){
        Queue<Integer> nodeQueue = new LinkedList<>();
        Set<Integer> nodeVisited = new HashSet<>();
        nodeQueue.add(source);
        nodeVisited.add(source);
        while(!nodeQueue.isEmpty()){
            Integer nodeCurrent = nodeQueue.poll();
            System.out.println(nodeCurrent);

            if(graph.containsKey(nodeCurrent)){
                for(Integer node: graph.get(nodeCurrent)){
                    if(!nodeVisited.contains(node)){
                        nodeQueue.add(node);
                        nodeVisited.add(node);
                    }
                }
            }
        }
    }

    public static void doBFSLevelOrder(int source){
        Queue<Integer> nodeQueue = new LinkedList<>();
        Set<Integer> nodeVisited = new HashSet<>();
        nodeQueue.add(source);
        nodeVisited.add(source);
        while(!nodeQueue.isEmpty()){
            System.out.println("");
            int size = nodeQueue.size();
            for(int i=0; i< size; i++){
                Integer currentNode = nodeQueue.poll();
                System.out.print(currentNode + " ");
                if(graph.containsKey(currentNode)){
                    for(Integer node: graph.get(currentNode)){
                        if(!nodeVisited.contains(node)){
                            nodeQueue.offer(node);
                            nodeVisited.add(node);
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        addEdge(0,1);
        addEdge(0,2);
        addEdge(1,3);
        addEdge(1,4);
        addEdge(2,4);
        doBFSLevelOrder(0);
    }
}
//The time complexity of nodeQueue.size() is: O(1)
//Let's analyze the **time complexity** and **space complexity** of the given `doBFSLevelOrder` method step by step.
//
//### **Intuition**:
//This method performs a **Breadth-First Search (BFS)** on a graph starting from the given `source` node. It uses a **queue** to traverse the graph level by level and a **set** to keep track of visited nodes.
//
//---
//
//### **Time Complexity**:
//1. **Outer while loop**:
//   - The loop runs until all nodes connected to the source are visited.
//   - Each node is dequeued **exactly once**, so the total number of iterations is \( O(V) \), where \( V \) is the number of nodes (vertices).
//
//2. **Inner for loop**:
//   - For each node, its neighbors are iterated over. The total number of iterations across all nodes is proportional to the total number of edges \( E \).
//
//3. **Operations within the loops**:
//   - Adding a node to the queue and the visited set are \( O(1) \) operations.
//   - Checking if a node is in the set (`nodeVisited.contains(node)`) is \( O(1) \) on average for a `HashSet`.
//
//Thus, the total time complexity is:
//
//\[
//O(V + E)
//\]
//
//This is the standard time complexity for BFS, where \( V \) is the number of vertices and \( E \) is the number of edges in the graph.
//
//---
//
//### **Space Complexity**:
//1. **Queue (`nodeQueue`)**:
//   - At most, the queue can hold all the nodes at the largest level in the graph. In the worst case (a complete graph), this can be up to \( O(V) \).
//
//2. **Visited set (`nodeVisited`)**:
//   - The `HashSet` can grow to store all \( V \) nodes in the graph, i.e., \( O(V) \).
//
//3. **Adjacency list (`graph`)**:
//   - Assuming the graph is represented as an adjacency list, it will require \( O(V + E) \) space to store.
//
//Thus, the space complexity is dominated by the queue and visited set:
//
//\[
//O(V + E)
//\]
//
//---
//
//### **Final Complexity**:
//- **Time Complexity**: \( O(V + E) \)
//- **Space Complexity**: \( O(V + E) \)
//
//This is efficient for graph traversal and scales well with sparse graphs.
