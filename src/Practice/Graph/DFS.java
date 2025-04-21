package Practice.Graph;

import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DFS {

    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void addEdge(int u, int v){
       graph.putIfAbsent(u, new ArrayList<>());
       graph.get(u).add(v);
    }

    public static void doDFS(Integer source){
        Stack<Integer> nodeStack = new Stack<>();
        Set<Integer> nodeVisited = new HashSet<>();
        nodeStack.add(source);
        while(!nodeStack.isEmpty()){
            Integer currentNode = nodeStack.pop();
            if(!nodeVisited.contains(currentNode)){
                System.out.println(currentNode);
                nodeVisited.add(currentNode);
            }
            if(graph.containsKey(currentNode)){
                for(Integer node: graph.get(currentNode)){
                    if(nodeVisited.contains(node) == false){
                        nodeStack.add(node);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        addEdge(1,2);
        addEdge(1,0);
        addEdge(2,0);
        addEdge(2,3);
        addEdge(2,4);
//        doDFS(1);
        List<Integer> numbers = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        Random rand = new Random();
        List<Integer> randomNumbers = IntStream.range(0, 10)
                .map(i -> rand.nextInt(100)) // random number from 0 to 999
                .boxed()
                .collect(Collectors.toList());
        System.out.println(randomNumbers.toString());
    }

}
