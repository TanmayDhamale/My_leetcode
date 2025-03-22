import java.util.Arrays;

class BellmanFord {
    static class Edge {
        int src, dest, weight;
        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    public static void bellmanFord(int vertices, Edge[] edges, int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Step 2: Relax edges (V-1) times
        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        // Step 3: Check for negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                System.out.println("Graph contains a negative weight cycle!");
                return;
            }
        }

        // Step 4: Print shortest distances
        System.out.println("Vertex Distance from Source:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + " -> " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        Edge[] edges = {
            new Edge(0, 1, -1),
            new Edge(0, 2, 4),
            new Edge(1, 2, 3),
            new Edge(1, 3, 2),
            new Edge(1, 4, 2),
            new Edge(3, 2, 5),
            new Edge(3, 1, 1),
            new Edge(4, 3, -3)
        };

        bellmanFord(vertices, edges, 0);
    }
}