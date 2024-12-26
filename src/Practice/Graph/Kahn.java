package Practice.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class Kahn {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void addEdge(int source, int destination){
        graph.putIfAbsent(source, new ArrayList<>());
        graph.get(source).add(destination);
//        graph.putIfAbsent(destination, new ArrayList<>()).add(source);
    }

    public static void doTopologicaSort(){
        Map<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        // Update in-degree based on edges
        graph.forEach((key, value) -> {
            inDegree.put(key, 0);
            if(!value.isEmpty()){
                for (Integer neighbor : value) {
                    Integer degree = inDegree.getOrDefault(neighbor, 0);
                    inDegree.putIfAbsent(neighbor, degree);
                }
            }
        });
        inDegree.forEach((key, value) -> {
            if(value == 0){
                queue.offer(key);
            }
        });
// Step 3: Process nodes from the queue
        List<Integer> topologicalOrder = new ArrayList<>();
        int count = 0; // To keep track of number of nodes processed

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topologicalOrder.add(node);
            count++;

            // For each neighbor of the current node, reduce in-degree by 1
            for (Integer neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: If count is less than V, there is a cycle in the graph
//        if (count != V) {
//            // If the topological sort is not possible (i.e., the graph has a cycle)
//            System.out.println("Cycle detected! Topological sorting is not possible.");
//            return new ArrayList<>(); // Return an empty list to indicate failure
//        }

        System.out.println(topologicalOrder);

    }

    public static void main(String[] args){
        addEdge(5,2);
        addEdge(5,0);
        addEdge(4,0);
        addEdge(4,1);
        addEdge(2,3);
        addEdge(3,1);
        doTopologicaSort();
        //Topological Sort: [4, 5, 2, 0, 3, 1]
    }
}
