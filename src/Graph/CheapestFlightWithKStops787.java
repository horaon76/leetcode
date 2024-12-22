package Graph;

import java.util.*;
//There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
//
//You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
//
//
//
//Example 1:
//
//
//Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
//Output: 700
//Explanation:
//The graph is shown above.
//The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
//Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
//Example 2:
//
//
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
//Output: 200
//Explanation:
//The graph is shown above.
//The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
//Example 3:
//
//
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
//Output: 500
//Explanation:
//The graph is shown above.
//The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.

public class CheapestFlightWithKStops787 {

    public static void initGraph(int[][] flights, Map<Integer, List<List<Integer>>> graph, int n) {
        for (int[] flight : flights) {
//            graph.put(
//                    flight[0],
//                    new ArrayList<>(List.of(flight[1], flight[2]))
//            );
            graph.computeIfAbsent(flight[0], k -> new ArrayList<>()).add(new ArrayList<>(List.of(flight[1], flight[2])));
        }
        System.out.println(graph);
    }

    public static int findCheapestPriceIterativeDFS(int n, int[][] flights, int source, int destination, int hops) {
        if (hops < 0) {
            throw new Error("Invalid input");
        }
        if (source == destination) {
            return 0;
        }
        Map<Integer, List<List<Integer>>> graph = new HashMap<>();
        initGraph(flights, graph, n);
        Integer cheapestPath = doDFS(graph, source, destination, hops, n);
        return cheapestPath;
    }

    public static int doDFS(Map<Integer, List<List<Integer>>> graph, int source, int destination, int hops, int n) {
        if (!graph.containsKey(source)) {
            return -1;
        }
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(n, false));
        Stack<int[]> stack = new Stack<>();
        // Stack for DFS: [city, cost, stops]
        stack.push(new int[]{source, 0, 0});

        // Shortest cost to reach each city within `K` stops
        int[] costTo = new int[n];
        Arrays.fill(costTo, Integer.MAX_VALUE);
        costTo[source] = 0;

        Integer minCost = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            int[] currentNode = stack.peek();
            System.out.println(Arrays.toString(currentNode));
            stack.pop();
            int city = currentNode[0];
            int cost = currentNode[1];
            int stops = currentNode[2];
            // If we exceed the allowed stops, skip this path
            if (stops > hops + 1) {
                continue;
            }
            // If we reached the destination, check if it's the cheapest
            if (city == destination) {
                minCost = Math.min(minCost, cost);
            }
            // Explore neighbors
            if (graph.containsKey(city)) {
                for (List<Integer> neighbor : graph.get(city)) {
                    int nextCity = neighbor.get(0);  // Destination city
                    int price = neighbor.get(1);     // Flight cost
                    int newCost = cost + price;

                    // Push to the stack only if this new path is cheaper
                    if (newCost < costTo[nextCity]) {
                        costTo[nextCity] = newCost;
                        stack.push(new int[]{nextCity, newCost, stops + 1});
                    }
                }
            }
        }
        // If no valid path was found, return -1
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    //Dijkstra's algorithm
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Create an adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], f -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        // Priority Queue: [cost, node, stops]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src, 0});

        // Shortest distance array
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // BFS traversal with the priority queue
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0],
                    node = current[1],
                    stops = current[2];

            // If destination is reached within k stops, return the cost
            if (node == dst) return cost;

            // If number of stops exceeds k, continue
            if (stops > k) continue;

            // Explore neighbors
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0], price = neighbor[1];
                    int newCost = cost + price;
                    if (newCost < dist[nextNode]) {
                        dist[nextNode] = newCost;
                        pq.offer(new int[]{newCost, nextNode, stops + 1});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
        int[][] flights = new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200},
                {0, 3, 200},
        };
        int n = 4, source = 0, destination = 3;
        int hops = 1;
//        System.out.println(findCheapestPriceIterativeDFS(n, flights, source, destination, hops));
        System.out.println(findCheapestPrice(n, flights, source, destination, hops));
    }
}
/**
 * For each node (city): We can explore all its outgoing edges (flights) up to K times.
 * This gives a time complexity of O(K * E), where E is the number of edges.
 * Processing the priority queue (if using Dijkstra's algorithm or a BFS with a priority queue):
 * The priority queue (or simple queue) will store at most K * V elements (since each city can be added to the queue multiple times with different stop counts).
 * Each insertion and removal operation from the priority queue takes O(log(K * V)), giving a time complexity for queue operations of O(K * V * log(K * V))
 * **/