package Graph;

import java.util.*;
/**
 * The time complexity of Depth-First Search (DFS) using a stack (either an explicit stack in an iterative approach or the call stack in the recursive approach) is typically:
 *
 * Time Complexity: O(V + E)
 * Where:
 *
 * V is the number of vertices (or nodes) in the graph.
 * E is the number of edges in the graph.
 * Explanation:
 * Vertices: Each vertex is visited exactly once.
 * Edges: Each edge is explored once when the algorithm checks the adjacent vertices of each node.
 * Thus, the total work done is proportional to the number of vertices plus the number of edges, hence
 * 𝑂
 * (
 * 𝑉
 * +
 * 𝐸
 * )
 * O(V+E). This applies to both the iterative version using an explicit stack and the recursive version using the function call stack.
 *
 * Time Complexity: O(V + E)
 *
 * **/
public class DFS {

    public static void doDFS(Graph g, int source, int V){
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(V, false));
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        List<List<Integer>> adjList= g.getAdjList();
        while(!stack.isEmpty()){
            int currentNode = stack.peek();
            stack.pop();
            if(visited.get(currentNode) != true){
                System.out.println(currentNode);
                visited.set(currentNode, true);
            }
            for(Integer node: adjList.get(currentNode)){
                if(visited.get(node) != true){
                    stack.add(node);
                }
            }
        }
    }

    public static void main(String args[]){
        int V = 6;
        Graph g = new Graph(V);
        g.addEdge(1,0);
        g.addEdge(0,2);
        g.addEdge(2,1);
        g.addEdge(0,3);
        g.addEdge(1,4);
        //0 3 2 1 4
        doDFS(g, 0, V);
    }
}

