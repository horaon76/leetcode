package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int V;
    List<List<Integer>> adjList;

    public Graph(int V){
        this.V = V;
        adjList = new ArrayList<>(V);
        for(int i =0; i<V; i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v){
        adjList.get(u).add(v);
    }

    public List<List<Integer>> getAdjList(){
        return adjList;
    }
}

