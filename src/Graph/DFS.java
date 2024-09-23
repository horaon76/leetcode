package Graph;

import java.util.*;

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
