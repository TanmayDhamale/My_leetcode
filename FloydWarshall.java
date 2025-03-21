import java.util.Arrays;

public class FloydWarshall {
    final static int INF = 99999; // Large value representing infinity

    public static void floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];

        // Step 1: Initialize the distance matrix
        for (int i = 0; i < V; i++) {
            dist[i] = Arrays.copyOf(graph[i], V);
        }

        // Step 2: Apply Floyd-Warshall Algorithm
        for (int k = 0; k < V; k++) { // Intermediate node
            for (int i = 0; i < V; i++) { // Source node
                for (int j = 0; j < V; j++) { // Destination node
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Step 3: Print the shortest distance matrix
        printSolution(dist);
    }

    public static void printSolution(int[][] dist) {
        System.out.println("Shortest Distance Matrix:");
        for (int[] row : dist) {
            for (int val : row) {
                if (val == INF)
                    System.out.print("INF ");
                else
                    System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 3, INF, 5},
            {2, 0, INF, 4},
            {INF, 1, 0, INF},
            {INF, INF, 2, 0}
        };

        floydWarshall(graph);
    }
}