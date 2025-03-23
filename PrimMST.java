import java.util.*;

class PrimMST {
    static class Edge implements Comparable<Edge> {
        int vertex, weight;
        public Edge(int v, int w) {
            this.vertex = v;
            this.weight = w;
        }
        public int compareTo(Edge other) {
            return this.weight - other.weight; // Sort by weight (MinHeap)
        }
    }

    public static void primMST(int[][] graph) {
        int V = graph.length; // Number of vertices
        boolean[] inMST = new boolean[V]; // Track included vertices
        int[] key = new int[V]; // Minimum weight edge for each vertex
        int[] parent = new int[V]; // Store MST structure

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0; // Start from vertex 0
        parent[0] = -1;

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        minHeap.add(new Edge(0, 0)); // Start with vertex 0

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int u = current.vertex;
            inMST[u] = true;

            // Update adjacent vertices
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                    minHeap.add(new Edge(v, key[v]));
                }
            }
        }

        // Print MST edges
        System.out.println("Edges in Minimum Spanning Tree:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " (Weight: " + graph[i][parent[i]] + ")");
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        primMST(graph);
    }
}