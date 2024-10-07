package Hard;

import java.util.*;

public class BusRoutes815 {
    public static int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0; // If starting and target are the same, no transfers needed

        // Step 1: Build a map of bus stops to bus routes
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new ArrayList<>());
                stopToRoutes.get(stop).add(i); // Map stops to their corresponding bus routes
            }
        }

        // Step 2: BFS initialization
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedRoutes = new HashSet<>();
        queue.offer(S);
        visitedStops.add(S);
        int transfers = 0;

        // Step 3: Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();

                // Explore routes connected to the current stop
                for (int routeIndex : stopToRoutes.getOrDefault(currentStop, new ArrayList<>())) {
                    if (visitedRoutes.contains(routeIndex)) continue; // Skip already visited routes
                    visitedRoutes.add(routeIndex); // Mark route as visited

                    // Explore all stops in the current bus route
                    for (int stop : routes[routeIndex]) {
                        if (stop == T) return transfers + 1; // Target found
                        if (!visitedStops.contains(stop)) {
                            visitedStops.add(stop);
                            queue.offer(stop); // Add to queue for further exploration
                        }
                    }
                }
            }
            transfers++; // Increment the number of transfers after processing all stops at current level
        }

        return -1; // Target not reachable
    }

    public static void main(String[] args) {
        int[][] routes1 = {{1, 2, 7}, {3, 6, 7}};
        System.out.println(numBusesToDestination(routes1, 1, 6)); // Output: 2

        int[][] routes2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(numBusesToDestination(routes2, 1, 6)); // Output: -1
    }
}
/**
 * Time and Space Complexity
 * Time Complexity: O(N + R) where N is the number of bus stops and R is the total number of routes. We may visit each stop and route at most once.
 * Space Complexity: O(N + R) due to the space needed for the stop-to-routes mapping and the queue.
 * **/


class DFSSolution {
    private Map<Integer, Set<Integer>> stopToRoutes = new HashMap<>();
    private boolean[] visitedRoutes; // To keep track of visited routes
    private boolean[] visitedStops; // To keep track of visited stops
    private int minTransfers = Integer.MAX_VALUE; // To track minimum transfers

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;

        // Step 1: Build the graph (stop to routes mapping)
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.putIfAbsent(stop, new HashSet<>());
                stopToRoutes.get(stop).add(i);
            }
        }

        visitedRoutes = new boolean[routes.length];
        visitedStops = new boolean[100000]; // Assuming maximum stop index

        // Step 2: Start DFS from the starting stop
        dfs(S, T, routes, 0);

        return minTransfers == Integer.MAX_VALUE ? -1 : minTransfers;
    }

    private void dfs(int currentStop, int targetStop, int[][] routes, int transfers) {
        if (currentStop == targetStop) {
            minTransfers = Math.min(minTransfers, transfers);
            return;
        }

        if (!stopToRoutes.containsKey(currentStop)) return;

        // Explore all routes that go through the current stop
        for (int routeIndex : stopToRoutes.get(currentStop)) {
            if (visitedRoutes[routeIndex]) continue; // Skip visited routes

            visitedRoutes[routeIndex] = true; // Mark the route as visited

            // Explore all stops in the current route
            for (int stop : routes[routeIndex]) {
                if (visitedStops[stop]) continue; // Skip visited stops

                visitedStops[stop] = true; // Mark the stop as visited
                dfs(stop, targetStop, routes, transfers + 1); // Recur with an incremented transfer count
                visitedStops[stop] = false; // Backtrack
            }

            visitedRoutes[routeIndex] = false; // Backtrack
        }
    }

    public static void main(String[] args) {
        DFSSolution solution = new DFSSolution();
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int S = 1;
        int T = 6;
        System.out.println(solution.numBusesToDestination(routes, S, T)); // Output: 2
    }
}
/**
 * Complexity Analysis
 * Time Complexity: O(N + M), where N is the number of stops, and M is the number of routes. Each route and stop is visited at most once.
 * Space Complexity: O(N + M) for storing the graph and visited sets.
 * **/