package Practice.Graph;

import java.util.*;

public class Test {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void addEdge(Integer fromNode, Integer toNode) {
        graph.putIfAbsent(fromNode, new ArrayList<>());
        graph.get(fromNode).add(toNode);
    }

    public static void doBFS(int sourceNode){
        if(graph == null || graph.isEmpty()){
            System.out.println("Graph is empty. Nothing to traverse.");
            return;
        }
        if (!graph.containsKey(sourceNode)) {
            System.out.println("Source node " + sourceNode + " does not exist in the graph.");
            return;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> nodeQueue = new LinkedList<>();

        nodeQueue.offer(sourceNode);
        visited.add(sourceNode);

        while(nodeQueue.isEmpty() == false){
            Integer currentNode = nodeQueue.poll();
            System.out.println(currentNode);
            if(graph.containsKey(currentNode)){
                for(Integer node: graph.get(currentNode)){
                    if(!visited.contains(node)){
                        nodeQueue.offer(node);
                        visited.add(node);
                    }
                }
            }
        }
    }




    public static void doDFS(int sourceNode){
        Stack<Integer> nodeStack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        nodeStack.add(sourceNode);
        while(!nodeStack.isEmpty()){
            Integer currentNode = nodeStack.pop();
            if(!visited.contains(currentNode)){
                System.out.println(currentNode);
                visited.add(currentNode);
            }
            if(graph.containsKey(currentNode)){
                for(Integer node: graph.get(currentNode)){
                    if(!visited.contains(node)){
                        nodeStack.push(node);
                    }
                }
            }
        }
    }
}
