package Basic;

import Graph.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The time complexity of Breadth-First Search (BFS) using a queue is:
 *
 * Time Complexity: O(V + E)
 * Where:
 *
 * V is the number of vertices (nodes).
 * E is the number of edges.
 * Explanation:
 * Vertices (V): Each vertex is enqueued and dequeued exactly once in BFS.
 * Edges (E): For each vertex, all its adjacent edges are explored once. Therefore, every edge is checked exactly once.
 * Thus, the total time complexity is proportional to the number of vertices plus the number of edges, i.e.,
 * 𝑂
 * (
 * 𝑉
 * +
 * 𝐸
 * )
 * O(V+E). This holds true for both directed and undirected graphs.
 *
 * **/
public class BFS {


    public static void doBFSConnected(Graph g, int source, int V){
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.offer(source);
        List<List<Integer>> adjList= g.getAdjList();
        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(current + " ");
            for(int node: adjList.get(current)){
                if(!visited[node]){
                    visited[node] = true;
                    queue.offer(node);
                }
            }
            System.out.println("");
        }
    }

    public static void doBFSConnected2(Graph g, int source, int V){
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.offer(source);
        List<List<Integer>> adjList= g.getAdjList();
        while(!queue.isEmpty()){
            int size = queue.size();
            System.out.println("");
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                System.out.print(current + " ");
                for(int node: adjList.get(current)){
                    if(!visited[node]){
                        visited[node] = true;
                        queue.offer(node);
                    }
                }
            }

        }
    }

    public static void main(String args[]){
        int V = 6;
        Graph g = new Graph(V);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(2,4);
        doBFSConnected(g, 0, V);
        System.out.println("---------------------");
        doBFSConnected2(g,0, V);
    }
}
//O(V+E)