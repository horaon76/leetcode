package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {


    public static void doBFSConnected(Graph g, int source, int V){
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.offer(source);
        List<List<Integer>> adjList= g.getAdjList();
        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.println(current);
            for(int node: adjList.get(current)){
                if(!visited[node]){
                    visited[node] = true;
                    queue.offer(node);
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
    }
}
