import java.util.*;

class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Edge other) {
            return this.weight - other.weight; // Sort by weight
        }
    }

    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
        }

        public int find(int v) {
            if (parent[v] != v)
                parent[v] = find(parent[v]); // Path compression
            return parent[v];
        }

        public boolean union(int u, int v) {
            int rootU = find(u), rootV = find(v);
            if (rootU == rootV) return false; // Cycle detected

            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }
    }

    public static void kruskalMST(int vertices, List<Edge> edges) {
        Collections.sort(edges); // Step 1: Sort edges by weight
        UnionFind uf = new UnionFind(vertices);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                mst.add(edge);
                if (mst.size() == vertices - 1) break; // MST is complete
            }
        }

        // Print MST edges
        System.out.println("Edges in Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " (Weight: " + edge.weight + ")");
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1, 10),
            new Edge(0, 2, 6),
            new Edge(0, 3, 5),
            new Edge(1, 3, 15),
            new Edge(2, 3, 4)
        );

        kruskalMST(4, edges);
    }
}