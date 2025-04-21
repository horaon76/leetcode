package Practice.Graph;

import java.util.*;

public class TopologicalSort {
    private static Map<Integer, List<Integer>> graph = new LinkedHashMap<>();

    public static void addEdge(int source, int destination){
        graph.putIfAbsent(source, new ArrayList<>());
        graph.get(source).add(destination);
//        graph.putIfAbsent(destination, new ArrayList<>()).add(source);
    }

    public static void doDFS(int node, Set<Integer> nodeVisited, Stack<Integer> nodeStack){
        nodeVisited.add(node);
        if(graph.containsKey(node)){
            for(Integer currentNode: graph.get(node)){
                if(!nodeVisited.contains(currentNode)){
                    doDFS(currentNode, nodeVisited, nodeStack);
                }
            }
        }
        nodeStack.add(node);
    }


    public static void doTopologicaSort(){
        Set<Integer> nodeVisited = new HashSet<>();
        Stack<Integer> nodeStack = new Stack<>();
        graph.forEach((key, value) -> {
            if(!nodeVisited.contains(key)){
                doDFS(key, nodeVisited, nodeStack);
            }
        });

        while(!nodeStack.isEmpty()){
            System.out.println(nodeStack.pop());
        }
    }



    public static void main(String[] args){
        addEdge(2,3);
        addEdge(3,1);
        addEdge(5,2);
        addEdge(5,0);
        addEdge(4,0);
        addEdge(4,1);
        doTopologicaSort();
    }
    //Topological Sort: [5, 4, 2, 3, 1, 0]
}
/*
* graph = {
    2 -> [3],
    3 -> [1],
    5 -> [2, 0],
    4 -> [0, 1]
}
*
* ### Dry Run of Topological Sort Implementation

The program performs **Topological Sorting** using **DFS (Depth-First Search)**. Here’s a step-by-step explanation of how it works for the given graph:

---

### **Graph Representation**

The directed acyclic graph (DAG) is represented using the adjacency list:
```
graph = {
    2 -> [3],
    3 -> [1],
    5 -> [2, 0],
    4 -> [0, 1]
}
```

---

### **Key Variables**

1. **Visited Set (`nodeVisited`)**: Keeps track of visited nodes.
2. **Stack (`nodeStack`)**: Stores the nodes in topological order (reverse of DFS finish order).

---

### **Step-by-Step Execution**

#### **Initial Setup**
- `nodeVisited = {}` (empty)
- `nodeStack = []` (empty)

---

#### **Processing Each Node in the Graph**

1. **Start DFS at Node 2 (Not Visited):**
   - Add 2 to `nodeVisited`: `{2}`
   - Explore neighbors of 2: `3`
     - **Start DFS at Node 3:**
       - Add 3 to `nodeVisited`: `{2, 3}`
       - Explore neighbors of 3: `1`
         - **Start DFS at Node 1:**
           - Add 1 to `nodeVisited`: `{1, 2, 3}`
           - No neighbors for 1; add 1 to `nodeStack`: `[1]`
       - Backtrack to 3; add 3 to `nodeStack`: `[1, 3]`
   - Backtrack to 2; add 2 to `nodeStack`: `[1, 3, 2]`

---

2. **Start DFS at Node 5 (Not Visited):**
   - Add 5 to `nodeVisited`: `{1, 2, 3, 5}`
   - Explore neighbors of 5: `2` (already visited) and `0`
     - **Start DFS at Node 0:**
       - Add 0 to `nodeVisited`: `{0, 1, 2, 3, 5}`
       - No neighbors for 0; add 0 to `nodeStack`: `[1, 3, 2, 0]`
   - Backtrack to 5; add 5 to `nodeStack`: `[1, 3, 2, 0, 5]`

---

3. **Start DFS at Node 4 (Not Visited):**
   - Add 4 to `nodeVisited`: `{0, 1, 2, 3, 4, 5}`
   - Explore neighbors of 4: `0` (already visited) and `1` (already visited)
   - Backtrack to 4; add 4 to `nodeStack`: `[1, 3, 2, 0, 5, 4]`

---

#### **Output Topological Order**
- Pop elements from `nodeStack`:
```
Topological Sort: 4 -> 5 -> 0 -> 2 -> 3 -> 1
```

---

### **Explanation of Output**
The order `[4, 5, 0, 2, 3, 1]` represents one valid **Topological Sort** for the graph. Nodes are printed in a sequence such that for every directed edge `(u -> v)`, `u` appears before `v`.

---

### **Time and Space Complexity**

#### **Time Complexity**
- Each node is visited exactly once during DFS: \(O(V)\), where \(V\) is the number of nodes.
- For each node, all its neighbors are explored: \(O(E)\), where \(E\) is the number of edges.
- Total: \(O(V + E)\).

#### **Space Complexity**
- Space for visited set: \(O(V)\).
- Space for the stack: \(O(V)\).
- Total: \(O(V)\).
* */