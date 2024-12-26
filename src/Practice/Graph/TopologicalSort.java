package Practice.Graph;

import java.util.*;

public class TopologicalSort {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

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
        addEdge(5,2);
        addEdge(5,0);
        addEdge(4,0);
        addEdge(4,1);
        addEdge(2,3);
        addEdge(3,1);
        doTopologicaSort();
    }
    //Topological Sort: [5, 4, 2, 3, 1, 0]
}
