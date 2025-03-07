import java.util.*;

class Dijkstra {
    static class Edge {
        int dest, weight;
        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Graph {
        int V;
        LinkedList<Edge>[] adjList;

        public Graph(int v) {
            this.V = v;
            adjList = new LinkedList[v];

            for (int i = 0; i < v; i++) {
                adjList[i] = new LinkedList<Edge>();  // ✅ Explicit Type
            }
        }

        public void addEdge(int src, int dest, int weight) {
            adjList[src].add(new Edge(dest, weight));
            adjList[dest].add(new Edge(src, weight)); // For undirected graph
        }

        public void dijkstra(int src) {
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;
            pq.add(new Edge(src, 0));

            while (!pq.isEmpty()) {
                Edge curr = pq.poll();
                int u = curr.dest;

                for (Edge neighbor : adjList[u]) {
                    int v = neighbor.dest;
                    int weight = neighbor.weight;

                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Edge(v, dist[v]));
                    }
                }
            }

            System.out.println("Shortest distances from source " + src + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("Node " + i + " → " + dist[i]);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 4, 3);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 4, 4);
        g.addEdge(2, 3, 9);
        g.addEdge(3, 4, 7);
        g.dijkstra(0);
    }
}