package Practice.Graph;

import java.util.*;

class Dijkstra {

    static class Node implements Comparable<Node> {
        int vertex, distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // Function to implement Dijkstra's Algorithm
    public static void dijkstra(Map<Integer, List<int[]>> graph, int start, int V) {
        // Distance array to store shortest path to each vertex
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Min-heap (priority queue)
        //
        Comparator<Node> nodeComparator = (node1, node2) -> Integer.compare(node1.distance, node2.distance);

        PriorityQueue<Node> pq = new PriorityQueue<>(nodeComparator);
        pq.offer(new Node(start, 0));

        // Process the nodes
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            // Explore neighbors
            for (int[] neighbor : graph.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor[0];
                int weight = neighbor[1];

                // Relaxation step
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        // Print the shortest distances from start node
        System.out.println("Vertex\tDistance from Start");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + (dist[i] == Integer.MAX_VALUE ? "∞" : dist[i]));
        }
    }

    public static void main(String[] args) {
        // Graph represented as adjacency list (vertex, weight)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new int[]{1, 10}, new int[]{2, 30}));
        graph.put(1, Arrays.asList(new int[]{3, 50}));
        graph.put(2, Arrays.asList(new int[]{3, 20}));

        int V = 4; // Number of vertices
        int start = 0; // Starting node is 0 (A)

        // Run Dijkstra's algorithm
        dijkstra(graph, start, V);
    }
}

