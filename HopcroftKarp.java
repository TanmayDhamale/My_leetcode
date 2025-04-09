import java.util.*;

public class HopcroftKarp {
    static final int NIL = 0;
    static final int INF = Integer.MAX_VALUE;

    int uSize, vSize;
    List<Integer>[] adj;  // Adjacency list for U-side nodes
    int[] pairU, pairV, dist;

    public HopcroftKarp(int uSize, int vSize) {
        this.uSize = uSize;
        this.vSize = vSize;

        adj = new List[uSize + 1];
        for (int i = 0; i <= uSize; i++) {
            adj[i] = new ArrayList<>();
        }

        pairU = new int[uSize + 1];
        pairV = new int[vSize + 1];
        dist = new int[uSize + 1];
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    public boolean bfs() {
        Queue1<Integer> queue = new LinkedList<>();

        for (int u = 1; u <= uSize; u++) {
            if (pairU[u] == NIL) {
                dist[u] = 0;
                queue.add(u);
            } else {
                dist[u] = INF;
            }
        }

        dist[NIL] = INF;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (dist[u] < dist[NIL]) {
                for (int v : adj[u]) {
                    if (dist[pairV[v]] == INF) {
                        dist[pairV[v]] = dist[u] + 1;
                        queue.add(pairV[v]);
                    }
                }
            }
        }

        return dist[NIL] != INF;
    }

    public boolean dfs(int u) {
        if (u != NIL) {
            for (int v : adj[u]) {
                if (dist[pairV[v]] == dist[u] + 1) {
                    if (dfs(pairV[v])) {
                        pairV[v] = u;
                        pairU[u] = v;
                        return true;
                    }
                }
            }
            dist[u] = INF;
            return false;
        }
        return true;
    }

    public int maxMatching() {
        int matching = 0;

        while (bfs()) {
            for (int u = 1; u <= uSize; u++) {
                if (pairU[u] == NIL && dfs(u)) {
                    matching++;
                }
            }
        }

        return matching;
    }

    public static void main(String[] args) {
        // Example: Bipartite Graph with 4 nodes in U, 4 in V
        HopcroftKarp hk = new HopcroftKarp(4, 4);

        // Edges: U1 -> V1, V2 | U2 -> V1 | U3 -> V3 | U4 -> V4
        hk.addEdge(1, 1);
        hk.addEdge(1, 2);
        hk.addEdge(2, 1);
        hk.addEdge(3, 3);
        hk.addEdge(4, 4);

        System.out.println("Maximum Matching: " + hk.maxMatching());
    }
}